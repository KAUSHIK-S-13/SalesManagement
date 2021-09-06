package com.sales.management.Service;

import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.SparepartstypeDTO;
import com.sales.management.Model.Sparepartstype;

import java.util.List;

public interface SparepartstypeInterface {
    BaseResponse addSparepartstype(SparepartstypeDTO sparepartstypeDTO);

    BaseResponse updateSparepartstype(SparepartstypeDTO sparepartstypeDTO);

    List<Sparepartstype> listall();

    BaseResponse deleteSparepartstype(SparepartstypeDTO sparepartstypeDTO);
}
