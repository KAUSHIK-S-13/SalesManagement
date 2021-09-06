package com.sales.management.Service;


import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.UserDTO;
import com.sales.management.Model.User;
import com.sales.management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional

public class UserService  implements UserInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse addUserdetail(UserDTO userDTO) {
        User user = new User();
        BaseResponse baseResponse = new BaseResponse();
        user.setUserName(userDTO.getUserName());
        user.setPassWord(userDTO.getPassWord());
        userRepository.save(user);
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("sucess");
        baseResponse.setData(user);
        return baseResponse;
    }

    @Override
    public APIResponse<User> GetUserWithPagination(int offset, int pageSize, String userName) {
        Pageable paging=PageRequest.of(offset, pageSize);
        Page<User> Users = userRepository.searchAllByUserNameLike("%" + userName + "%", paging);
        APIResponse apiResponse=new APIResponse();
        apiResponse.setResponse(Users);
        apiResponse.setRecordCount(Users.getTotalPages());
        return apiResponse;
    }

    @Override
    public BaseResponse updateUser(UserDTO userDTO) {
        Optional<User> existUser = userRepository.findById(userDTO.getUserId());
        if(existUser.isPresent())
        {
            existUser.get().setUserName(userDTO.getUserName());
            existUser.get().setPassWord(userDTO.getPassWord());
            userRepository.save(existUser.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("success");
        baseResponse.setData(existUser);
        return baseResponse;
    }

    @Override
    public BaseResponse<User> FindByUserId(int id) {
        Optional<User> users=userRepository.findById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("success");
        baseResponse.setData(users);
        return baseResponse;
    }

    @Override
    public BaseResponse deleteSoft(UserDTO userDTO) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<User> existUser = userRepository.findById(userDTO.getUserId());
        if(existUser.isPresent())
        {
        existUser.get().setIsDelete(1);
        userRepository.save(existUser.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("success");
        baseResponse.setData(existUser);
        return baseResponse;
    }
}
