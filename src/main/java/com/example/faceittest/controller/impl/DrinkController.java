package com.example.faceittest.controller.impl;

import com.example.faceittest.controller.Controller;
import com.example.faceittest.dto.DrinkDto;
import com.example.faceittest.entity.Drink;
import com.example.faceittest.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drink")
public class DrinkController extends Controller<Drink, DrinkDto> {

  public DrinkController(CrudService<Drink, DrinkDto> service) {
    super(service);
  }
}
