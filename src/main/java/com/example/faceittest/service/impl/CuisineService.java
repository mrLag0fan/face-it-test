package com.example.faceittest.service.impl;

import com.example.faceittest.dto.CuisineDto;
import com.example.faceittest.dto.converter.DtoToEntityConverter;
import com.example.faceittest.entity.Cuisine;
import com.example.faceittest.service.CrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CuisineService extends CrudService<Cuisine, CuisineDto> {

  public CuisineService(CrudRepository<Cuisine, Long> repository,
      DtoToEntityConverter<Cuisine, CuisineDto> dtoToEntityConverter) {
    super(repository, dtoToEntityConverter);
  }
}
