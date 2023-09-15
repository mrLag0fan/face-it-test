package com.example.faceittest.controller.impl;

import com.example.faceittest.controller.Controller;
import com.example.faceittest.dto.OrderDto;
import com.example.faceittest.entity.Order;
import com.example.faceittest.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController extends Controller<Order, OrderDto> {

  public OrderController(CrudService<Order, OrderDto> service) {
    super(service);
  }
}
