package com.sales.management.Controller;

import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.SparepartstypeDTO;
import com.sales.management.Model.Sparepartstype;
import com.sales.management.Service.SparpartstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sparepartstype")
@RestController
public class SparepartstypeController {

    @Autowired
    private SparpartstypeService sparpartstypeService;

    @PostMapping("/add")
    public BaseResponse addsparepartstype(@RequestBody SparepartstypeDTO sparepartstypeDTO) {
        return sparpartstypeService.addSparepartstype(sparepartstypeDTO);
    }

    @PutMapping("/update")
    public BaseResponse updatesparepartstype(@RequestBody SparepartstypeDTO sparepartstypeDTO) {
        return sparpartstypeService.updateSparepartstype(sparepartstypeDTO);
    }

    @GetMapping("/getAll")
    public List<Sparepartstype> list(){
        return sparpartstypeService.listall();
    }

    @PutMapping("/delete")
    public BaseResponse deletesparepartstype(@RequestBody SparepartstypeDTO sparepartstypeDTO) {
        return sparpartstypeService.deleteSparepartstype(sparepartstypeDTO);
    }
}
