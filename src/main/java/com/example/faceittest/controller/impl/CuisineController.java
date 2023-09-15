package com.example.faceittest.controller.impl;

import com.example.faceittest.controller.Controller;
import com.example.faceittest.dto.CuisineDto;
import com.example.faceittest.entity.Cuisine;
import com.example.faceittest.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuisines")
public class CuisineController extends Controller<Cuisine, CuisineDto> {

  public CuisineController(CrudService<Cuisine, CuisineDto> service) {
    super(service);
  }
}
