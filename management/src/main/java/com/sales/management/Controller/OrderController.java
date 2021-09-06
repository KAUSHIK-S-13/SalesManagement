package com.sales.management.Controller;

import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.OrderDTO;
import com.sales.management.Model.Order;
import com.sales.management.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public BaseResponse addorder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }
    @PutMapping("/update")
    public BaseResponse updateorder(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }
    @GetMapping("/getAll")
    public List<Order> list(){
        return orderService.getAllOrder();
    }

    @PutMapping("/delete")
    public BaseResponse deleteorder(@RequestBody  OrderDTO orderDTO) {
        return orderService.deleteOrder(orderDTO);
    }

}
