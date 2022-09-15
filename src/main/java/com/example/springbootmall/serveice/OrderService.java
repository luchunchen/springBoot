package com.example.springbootmall.serveice;

import com.example.springbootmall.dto.CreateOrderRequest;
import com.example.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
