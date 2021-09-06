package com.sales.management.Service;

import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.UserDTO;
import com.sales.management.Model.User;

public interface UserInterface {
    BaseResponse addUserdetail(UserDTO userDTO);

    APIResponse<User> GetUserWithPagination(int offset, int pageSize, String userName);

    BaseResponse updateUser(UserDTO userDTO);

    BaseResponse<User> FindByUserId(int id);

    BaseResponse deleteSoft(UserDTO userDTO);
}
