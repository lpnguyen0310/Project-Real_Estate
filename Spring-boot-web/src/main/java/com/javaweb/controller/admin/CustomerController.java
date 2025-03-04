package com.javaweb.controller.admin;

import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerResponseDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.service.ICustormerService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value="customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private ICustormerService custormerService;


    @GetMapping("admin/customer-list")
    public ModelAndView getCustomerPage(@ModelAttribute("modelSearch") CustomerSearchRequest params, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        params.setTableId("customer");
        // Xử lý thông tin phân trang từ request
        DisplayTagUtils.of(request, params);
        // phân trang từ request
        Pageable pageable = PageRequest.of(params.getPage() - 1, params.getMaxPageItems());
        // Lấy danh sách khách hàng theo điều kiện tìm kiếm và phân trang
        List<CustomerResponseDTO> customers = custormerService.findAllCustomer(params, pageable);


        params.setListResult(customers);
        int total = custormerService.countTotalBuilding(params);
        params.setTotalItems(total);
        mav.addObject("customers", customers);
        mav.addObject("status", Status.getStatus());
        return mav;
    }
}
