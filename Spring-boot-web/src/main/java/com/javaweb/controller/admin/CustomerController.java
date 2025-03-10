package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerResponseDTO;
import com.javaweb.model.dto.TransactionResponseDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustormerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value="customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private ICustormerService custormerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ITransactionService transactionService;


    @GetMapping("admin/customer-list")
    public ModelAndView getCustomerPage(@ModelAttribute("modelSearch") CustomerSearchRequest params, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        params.setTableId("customer");
        DisplayTagUtils.of(request, params);
        // Nếu user là STAFF, tự động gán staffId mà không cần từ form
        if (SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            params.setStaffId(staffId);  // Gán ID nhân viên vào params
        }



        Pageable pageable = PageRequest.of(params.getPage() - 1, params.getMaxPageItems());
        List<CustomerResponseDTO> customers = custormerService.findAllCustomer(params, pageable);
        params.setListResult(customers);
        int total = custormerService.countTotalBuilding(params);
        params.setTotalItems(total);
        mav.addObject("customers", customers);
        mav.addObject("status", Status.getStatus());
        mav.addObject("modelSearch", params);

        return mav;
    }


    @GetMapping("admin/customer-edit")
    public ModelAndView getCustomerEditPage(@ModelAttribute("customer") CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("status", Status.getStatus());
        return mav;
    }
    @GetMapping("admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long customerId) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            if(!custormerService.findCustomerByIdAndStaffId(customerId, staffId)){
                return new ModelAndView("redirect:/error/404");

            }
        }
        // Lấy thông tin khách hàng
        CustomerDTO customer = custormerService.findCustomerById(customerId);
        mav.addObject("customer", customer);

        // Lấy danh sách giao dịch
        List<TransactionResponseDTO> transactions = transactionService.findAllTransaction(customerId);
        mav.addObject("transactions", transactions);
        // Truyền dữ liệu vào view
        mav.addObject("status", Status.getStatus());

        return mav;
    }
}
