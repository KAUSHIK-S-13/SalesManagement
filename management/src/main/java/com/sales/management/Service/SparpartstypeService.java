package com.sales.management.Service;

import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.SparepartstypeDTO;
import com.sales.management.Model.Sparepartstype;
import com.sales.management.Repository.SparepartstypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional

public class SparpartstypeService implements SparepartstypeInterface {

    @Autowired
    private SparepartstypeRepository sparepartstypeRepository;


    @Override
    public BaseResponse addSparepartstype(SparepartstypeDTO sparepartstypeDTO) {
        Sparepartstype sparepartstype = new Sparepartstype();
        BaseResponse baseResponse = new BaseResponse();
        sparepartstype.setSparepartstypeName(sparepartstypeDTO.getSparepartstypeName());
        sparepartstypeRepository.save(sparepartstype);
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("sucess");
        baseResponse.setData(sparepartstype);
        return baseResponse;
    }

    @Override
    public BaseResponse updateSparepartstype(SparepartstypeDTO sparepartstypeDTO) {
        Optional<Sparepartstype> existSparepartstype= sparepartstypeRepository.findById(sparepartstypeDTO.getSparepartstypeId());
        if(existSparepartstype.isPresent())
        {
            existSparepartstype.get().setSparepartstypeName(sparepartstypeDTO.getSparepartstypeName());
            sparepartstypeRepository.save(existSparepartstype.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("sucess");
        baseResponse.setData(existSparepartstype);
        return baseResponse;
    }

    @Override
    public List<Sparepartstype> listall() {
        return sparepartstypeRepository.findAll();
    }

    @Override
    public BaseResponse deleteSparepartstype(SparepartstypeDTO sparepartstypeDTO) {
        Optional<Sparepartstype> existSparepartstype = sparepartstypeRepository.findById(sparepartstypeDTO.getSparepartstypeId());
        if(existSparepartstype.isPresent())
        {
            existSparepartstype.get().setIsDelete(1);
            sparepartstypeRepository.save(existSparepartstype.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("success");
        baseResponse.setData(existSparepartstype);
        return baseResponse;
    }
}
