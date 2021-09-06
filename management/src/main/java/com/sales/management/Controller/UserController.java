package com.sales.management.Controller;


import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.UserDTO;
import com.sales.management.Model.User;
import com.sales.management.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public BaseResponse adduserdetail(@Valid @RequestBody UserDTO userDTO) {
        return userService.addUserdetail(userDTO);
    }


    @GetMapping("/{offset}/{pageSize}/{userName}")
     private APIResponse<User> getUserWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String userName) {
     return userService.GetUserWithPagination(offset, pageSize, userName);
    }

   @PutMapping("/updater")
    public BaseResponse updateuser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @GetMapping("/{id}")
    public BaseResponse<User> findByUserId(@PathVariable int id) {
        return userService.FindByUserId(id);
    }

    @PutMapping("/delete")
    public BaseResponse deletesoft(@RequestBody UserDTO userDTO) {
        return userService.deleteSoft(userDTO);
    }




}
