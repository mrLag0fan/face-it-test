package com.example.faceittest.controller.impl;

import com.example.faceittest.controller.Controller;
import com.example.faceittest.dto.DishDto;
import com.example.faceittest.entity.Dish;
import com.example.faceittest.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dish")
public class DishController extends Controller<Dish, DishDto> {

  public DishController(CrudService<Dish, DishDto> service) {
    super(service);
  }
}
