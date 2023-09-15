package com.example.faceittest.service.impl;

import com.example.faceittest.dto.DishDto;
import com.example.faceittest.dto.converter.DtoToEntityConverter;
import com.example.faceittest.entity.Cuisine;
import com.example.faceittest.entity.Dish;
import com.example.faceittest.service.CrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DishService extends CrudService<Dish, DishDto> {

  private final CrudRepository<Cuisine, Long> cuisineRepository;

  public DishService(CrudRepository<Dish, Long> repository,
      DtoToEntityConverter<Dish, DishDto> dtoToEntityConverter,
      CrudRepository<Cuisine, Long> cuisineRepository) {
    super(repository, dtoToEntityConverter);
    this.cuisineRepository = cuisineRepository;
  }

  public Dish create(DishDto dto) {
    Dish dtoToEntity = dtoToEntityConverter.convertDtoToEntity(dto, Dish.class);
    dtoToEntity.setCuisine(cuisineRepository.findById(dto.getCuisineId()).orElse(null));
    return repository.save(dtoToEntity);
  }
}
