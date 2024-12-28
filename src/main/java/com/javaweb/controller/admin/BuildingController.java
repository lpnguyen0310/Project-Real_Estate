package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingResponseDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private UserService userService;

    @GetMapping("admin/building-list")
    public ModelAndView getBuildingPage(@ModelAttribute("modelSearch") BuildingSearchRequest params){
        ModelAndView mav = new ModelAndView("admin/building/list");

        Map<Long,String> staffMap = userService.getListStaff();
        mav.addObject("staffMap",staffMap);
        mav.addObject("districts", District.getDistrict());
        mav.addObject("typeCodes", TypeCode.type());

        List<BuildingResponseDTO> buildings = buildingService.findAll(params);

        mav.addObject("buildings", buildings);
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
