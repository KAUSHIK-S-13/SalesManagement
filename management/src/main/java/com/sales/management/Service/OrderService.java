package com.sales.management.Service;

import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.OrderDTO;
import com.sales.management.Model.Order;
import com.sales.management.Model.Spareparts;
import com.sales.management.Model.User;
import com.sales.management.Repository.OrderRepository;
import com.sales.management.Repository.SparepartsRepository;
import com.sales.management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class OrderService implements OrderInterface{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SparepartsRepository sparepartsRepository;

    @Override
    public BaseResponse addOrder(OrderDTO orderDTO) {
        Order order=new Order();
        order.setOrderQuantity(orderDTO.getOrderQuantity());
        order.setOrderDestination(orderDTO.getOrderDestination());
        Order finalOrder = order;
        orderDTO.getSparepartsId().forEach(sparepartsDTO -> {
            Optional<Spareparts> sparepartss=sparepartsRepository.findBySparepartsId(sparepartsDTO.getSparepartsId());
            if(sparepartss.isPresent())
            {
             finalOrder.setSparepartss(sparepartss.get());
             }
            else
            {
               throw new RuntimeException("data not found");
            }
        });
        Order finalOrder1 = order;
        orderDTO.getUserId().forEach(userDTO -> {
            Optional<User> Users=userRepository.findByUserId(userDTO.getUserId());

            if(Users.isPresent())
            {
                finalOrder1.setUsers(Users.get());
            }
            else
            {
                throw new RuntimeException("data not found");
            }
        });
        order=orderRepository.save(order);
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("sucess");
        baseResponse.setData(order);
        return baseResponse;
    }

    @Override
    public BaseResponse updateOrder(OrderDTO orderDTO) {
        Optional<Order> existOrder= orderRepository.findById(orderDTO.getOrderId());
        if(existOrder.isPresent())
        {
            existOrder.get().setOrderQuantity(orderDTO.getOrderQuantity());
            existOrder.get().setOrderDestination(orderDTO.getOrderDestination());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        orderDTO.getSparepartsId().forEach(sparepartsDTO -> {
            Optional<Spareparts> sparepartss=sparepartsRepository.findBySparepartsId(sparepartsDTO.getSparepartsId());
            if(sparepartss.isPresent())
            {
                existOrder.get().setSparepartss(sparepartss.get());
            }
            else
            {
                throw new RuntimeException("not found");
            }
        });
        orderDTO.getUserId().forEach(userDTO -> {
            Optional<User> Users=userRepository.findByUserId(userDTO.getUserId());
            if(Users.isPresent())
            {
                existOrder.get().setUsers(Users.get());
            }
            else
            {
                throw new RuntimeException("not found");
            }
        });
        orderRepository.save(existOrder.get());
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("sucess");
        baseResponse.setData(existOrder);
        return baseResponse;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public BaseResponse deleteOrder(OrderDTO orderDTO) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<Order> existOrder= orderRepository.findById(orderDTO.getOrderId());
        if(existOrder.isPresent())
        {
            existOrder.get().setIsDelete(1);
            orderRepository.save(existOrder.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        baseResponse.setStatusCode("OK");
        baseResponse.setStatusMsg("success");
        baseResponse.setData(existOrder);
        return baseResponse;
    }
}

