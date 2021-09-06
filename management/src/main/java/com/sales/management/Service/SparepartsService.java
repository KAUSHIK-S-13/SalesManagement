package com.sales.management.Service;

import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.SparepartsDTO;
import com.sales.management.Model.Spareparts;
import com.sales.management.Model.Sparepartstype;
import com.sales.management.Repository.SparepartsRepository;
import com.sales.management.Repository.SparepartstypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional

public class SparepartsService implements SparepartsInterface {

    @Autowired
    private SparepartsRepository sparepartsRepository;

    @Autowired
    private SparepartstypeRepository sparepartstypeRepository;

    @Override
    public BaseResponse addSpareparts(SparepartsDTO sparepartsDTO) {
        Spareparts spareparts = new Spareparts();
        BaseResponse baseResponse = new BaseResponse();
        spareparts.setSparepartsName(sparepartsDTO.getSparepartsName());
        Optional<Sparepartstype> obj=sparepartstypeRepository.findBySparepartstypeId(sparepartsDTO.getSparepartstypeId());
        if(obj.isPresent())
        {
            spareparts.setSparepartstypeId(obj.get());
            sparepartsRepository.save(spareparts);
        }
        else
        {
            throw new RuntimeException("not found");
        }
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("sucess");
        baseResponse.setData(spareparts);
        return baseResponse;
    }

    @Override
    public APIResponse<Spareparts> GetSparepartsWithPagination(int offset, int pageSize, String sparepartsName) {
        Pageable paging= PageRequest.of(offset, pageSize);
        Page<Spareparts> Sparepartss = sparepartsRepository.searchAllBySparepartsNameLike("%" + sparepartsName + "%", paging);
        APIResponse apiResponse=new APIResponse();
        apiResponse.setResponse(Sparepartss);
        apiResponse.setRecordCount(Sparepartss.getTotalPages());
        return apiResponse;
    }

    @Override
    public BaseResponse updateSpareparts(SparepartsDTO sparepartsDTO) {
        Optional<Spareparts> existSpareparts= sparepartsRepository.findById(sparepartsDTO.getSparepartsId());
        if(existSpareparts.isPresent())
        {
            existSpareparts.get().setSparepartsName(sparepartsDTO.getSparepartsName());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        Optional<Sparepartstype> obj=sparepartstypeRepository.findBySparepartstypeId(sparepartsDTO.getSparepartstypeId());
        if(obj.isPresent())
        {
            existSpareparts.get().setSparepartstypeId(obj.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        sparepartsRepository.save(existSpareparts.get());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("sucess");
        baseResponse.setData(existSpareparts);
        return baseResponse;
    }

    @Override
    public BaseResponse<Spareparts> findSparepartsById(int id) {
        Optional<Spareparts> Sparepartss=sparepartsRepository.findById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("success");
        baseResponse.setData(Sparepartss);
        return baseResponse;
    }

    @Override
    public BaseResponse deleteSpareparts(SparepartsDTO sparepartsDTO) {
        Optional<Spareparts> existpareparts = sparepartsRepository.findById(sparepartsDTO.getSparepartsId());
        if(existpareparts.isPresent())
        {
            existpareparts.get().setIsDelete(1);
            sparepartsRepository.save(existpareparts.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("success");
        baseResponse.setData(existpareparts);
        return baseResponse;
    }
}
