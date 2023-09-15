package com.example.faceittest.service.impl;

import com.example.faceittest.dto.OrderDto;
import com.example.faceittest.dto.converter.DtoToEntityConverter;
import com.example.faceittest.entity.Dish;
import com.example.faceittest.entity.Drink;
import com.example.faceittest.entity.Order;
import com.example.faceittest.service.CrudService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudService<Order, OrderDto> {

  private final CrudRepository<Drink, Long> drinkRepository;
  private final CrudRepository<Dish, Long> dishRepository;

  public OrderService(CrudRepository<Order, Long> repository,
      DtoToEntityConverter<Order, OrderDto> dtoToEntityConverter,
      CrudRepository<Drink, Long> drinkRepository,
      CrudRepository<Dish, Long> dishRepository) {
    super(repository, dtoToEntityConverter);
    this.dishRepository = dishRepository;
    this.drinkRepository = drinkRepository;
  }

  public Order create(OrderDto dto) {
    Order dtoToEntity = dtoToEntityConverter.convertDtoToEntity(dto, Order.class);
    dtoToEntity.setDrinks(((List<Drink>) drinkRepository.findAll()).stream().filter((d) -> {
      return dto.getDrinksIds().contains(d.getId());
    }).collect(Collectors.toList()));
    dtoToEntity.setDishes(((List<Dish>) dishRepository.findAll()).stream().filter((d) -> {
      return dto.getDishesIds().contains(d.getId());
    }).collect(Collectors.toList()));
    return repository.save(dtoToEntity);
  }

  public OrderDto getById(Long id) {
    Order result = repository.findById(id).orElse(null);
    OrderDto dto = null;
    if (result != null) {
      dto = dtoToEntityConverter.convertEntityToDto(result, OrderDto.class);
      dto.setDrinksIds(result.getDrinks().stream().map(Drink::getId).collect(Collectors.toList()));
      dto.setDishesIds(result.getDishes().stream().map(Dish::getId).collect(Collectors.toList()));
    }
    return dto;
  }
}
