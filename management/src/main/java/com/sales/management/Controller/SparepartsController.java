package com.sales.management.Controller;

import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.SparepartsDTO;
import com.sales.management.Model.Spareparts;
import com.sales.management.Service.SparepartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/spareparts")
@RestController
public class SparepartsController {

    @Autowired
    private SparepartsService sparepartsService;

    @PostMapping("/add")
    public BaseResponse addspareparts(@RequestBody SparepartsDTO sparepartsDTO) {
        return sparepartsService.addSpareparts(sparepartsDTO);
    }

   @GetMapping("/{offset}/{pageSize}/{sparepartsName}")
   private APIResponse<Spareparts> getSparepartsWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String sparepartsName) {
       return sparepartsService.GetSparepartsWithPagination(offset, pageSize, sparepartsName);
   }


    @PutMapping("/update")
    public BaseResponse updatespareparts(@RequestBody SparepartsDTO sparepartsDTO) {
        return sparepartsService.updateSpareparts(sparepartsDTO);
    }

    @GetMapping("/{id}")
    public BaseResponse<Spareparts> findsparepartsById(@PathVariable int id) {
        return sparepartsService.findSparepartsById(id);
    }

    @PutMapping("/delete")
    public BaseResponse deletespareparts(@RequestBody SparepartsDTO sparepartsDTO) {
        return sparepartsService.deleteSpareparts(sparepartsDTO);
    }

}
