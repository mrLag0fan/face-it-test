package com.example.faceittest.service.impl;

import com.example.faceittest.dto.DrinkDto;
import com.example.faceittest.dto.converter.DtoToEntityConverter;
import com.example.faceittest.entity.Cuisine;
import com.example.faceittest.entity.Drink;
import com.example.faceittest.service.CrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DrinkService extends CrudService<Drink, DrinkDto> {

  private final CrudRepository<Cuisine, Long> cuisineRepository;

  public DrinkService(CrudRepository<Drink, Long> repository,
      DtoToEntityConverter<Drink, DrinkDto> dtoToEntityConverter,
      CrudRepository<Cuisine, Long> cuisineRepository) {
    super(repository, dtoToEntityConverter);
    this.cuisineRepository = cuisineRepository;
  }

  public Drink create(DrinkDto dto) {
    Drink dtoToEntity = dtoToEntityConverter.convertDtoToEntity(dto, Drink.class);
    dtoToEntity.setCuisine(cuisineRepository.findById(dto.getCuisineId()).orElse(null));
    return repository.save(dtoToEntity);
  }
}
