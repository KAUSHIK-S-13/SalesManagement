package com.sales.management.Service;

import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.SparepartsDTO;
import com.sales.management.Model.Spareparts;

public interface SparepartsInterface {
    BaseResponse addSpareparts(SparepartsDTO sparepartsDTO);

    APIResponse<Spareparts> GetSparepartsWithPagination(int offset, int pageSize, String sparepartsName);

    BaseResponse updateSpareparts(SparepartsDTO sparepartsDTO);

    BaseResponse<Spareparts> findSparepartsById(int id);

    BaseResponse deleteSpareparts(SparepartsDTO sparepartsDTO);
}
