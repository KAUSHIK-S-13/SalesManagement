package com.sales.management.Service;

import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.OrderDTO;
import com.sales.management.Model.Order;

import java.util.List;

public interface OrderInterface {
    BaseResponse addOrder(OrderDTO orderDTO);

    BaseResponse updateOrder(OrderDTO orderDTO);

    List<Order> getAllOrder();

    BaseResponse deleteOrder(OrderDTO orderDTO);
}
