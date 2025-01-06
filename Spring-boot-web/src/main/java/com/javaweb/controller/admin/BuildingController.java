package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingResponseDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private UserService userService;

    @GetMapping("admin/building-list")
    public ModelAndView getBuildingPage(@ModelAttribute("modelSearch") BuildingSearchRequest params, HttpServletRequest request){
            ModelAndView mav = new ModelAndView("admin/building/list");
            params.setTableId("building-list");
            // Xử lý thông tin phân trang từ request
            DisplayTagUtils.of(request, params);
            System.out.println("Table ID: " + params.getTableId());
            System.out.println("Page: " + params.getPage());
            // Lấy danh sách nhân viên và các thông tin liên quan
            Map<Long, String> staffMap = userService.getListStaff();
            mav.addObject("staffMap", staffMap);
            mav.addObject("districts", District.getDistrict());
            mav.addObject("typeCodes", TypeCode.type());

            // Áp dụng phân trang
            Pageable pageable = PageRequest.of(params.getPage() - 1, params.getMaxPageItems());

            // Lấy danh sách tòa nhà theo điều kiện tìm kiếm và phân trang
            List<BuildingResponseDTO> buildings = buildingService.findAll(params, pageable);
             params.setListResult(buildings);
            // Đếm tổng số lượng tòa nhà dựa trên điều kiện tìm kiếm
            int totalBuildings = buildingService.countTotalBuilding(params);
            params.setTotalItems(totalBuildings);


            System.out.println("Total items: " + params.getTotalItems());
            System.out.println("Page size: " + params.getMaxPageItems());
            System.out.println("Total pages: " + params.getTotalPages());

            System.out.println("Pageable: " + pageable.getPageNumber() + ", Size: " + pageable.getPageSize());
            System.out.println("Current Page: " + params.getPage());
            System.out.println("Max Items per Page: " + params.getMaxPageItems());
            // Truyền dữ liệu vào view
            mav.addObject("modelSearch", params);

            return mav;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("building") BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", District.getDistrict());
        mav.addObject("typeCodes", TypeCode.type());
        mav.addObject("building", buildingDTO);
        return mav;
    }
    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        // Gọi service để lấy thông tin tòa nhà theo id và truyền vào view
        // building entity ==> building dto
        BuildingDTO buildingDTO = buildingService.findBuildingById(id);
        mav.addObject("districts", District.getDistrict());
        mav.addObject("typeCodes", TypeCode.type());
        mav.addObject("building", buildingDTO);

        return mav;
    }



}
