package com.example.faceittest;

import com.example.faceittest.dto.CuisineDto;
import com.example.faceittest.dto.DishDto;
import com.example.faceittest.dto.DrinkDto;
import com.example.faceittest.dto.OrderDto;
import com.example.faceittest.dto.converter.DtoToEntityConverter;
import com.example.faceittest.entity.Dish;
import com.example.faceittest.entity.Drink;
import com.example.faceittest.entity.Order;
import com.example.faceittest.service.impl.CuisineService;
import com.example.faceittest.service.impl.DishService;
import com.example.faceittest.service.impl.DrinkService;
import com.example.faceittest.service.impl.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderTests {

  @Autowired
  private OrderService orderService;
  @Autowired
  private DishService dishService;
  @Autowired
  private DrinkService drinkService;
  @Autowired
  private DtoToEntityConverter<Dish, DishDto> dtoToEntityConverterDish;
  @Autowired
  private DtoToEntityConverter<Drink, DrinkDto> dtoToEntityConverterDrink;

  @BeforeAll
  static void init(@Autowired CuisineService cuisineService,
      @Autowired DishService dishService,
      @Autowired DrinkService drinkService) {
    CuisineDto cuisineDto = new CuisineDto();
    cuisineDto.setName("Polish");
    cuisineService.create(cuisineDto);

    DishDto dish1 = new DishDto();
    dish1.setName("Pierogi");
    dish1.setCuisineId(1L);
    dish1.setPrice(10.99);
    dishService.create(dish1);

    DishDto dish2 = new DishDto();
    dish2.setName("Bigos");
    dish2.setCuisineId(1L);
    dish2.setPrice(12.99);
    dishService.create(dish2);

    DishDto dish3 = new DishDto();
    dish3.setName("Kielbasa");
    dish3.setCuisineId(1L);
    dish3.setPrice(8.99);
    dishService.create(dish3);

    DrinkDto drink1 = new DrinkDto();
    drink1.setName("Vodka");
    drink1.setCuisineId(1L);
    drink1.setPrice(9.99);
    drinkService.create(drink1);

    DrinkDto drink2 = new DrinkDto();
    drink2.setName("Beer");
    drink2.setCuisineId(1L);
    drink2.setPrice(5.99);
    drinkService.create(drink2);

    DrinkDto drink3 = new DrinkDto();
    drink3.setName("Wine");
    drink3.setCuisineId(1L);
    drink3.setPrice(14.99);
    drinkService.create(drink3);
  }

  @Test
  void createTest() {
    OrderDto input = buildInput();
    Order expected = new Order();
    expected.setId(2L);
    expected.setDishes(dishService.getAll().stream()
        .map(dishDto -> dtoToEntityConverterDish.convertDtoToEntity(dishDto, Dish.class)).toList());
    expected.setDrinks(drinkService.getAll().stream()
        .map(drinkDto -> dtoToEntityConverterDrink.convertDtoToEntity(drinkDto, Drink.class))
        .toList());

    Order actual = orderService.create(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdTest() {
    Long input = 1L;
    OrderDto expected = buildInput();
    orderService.create(expected);
    expected.setId(input);

    OrderDto actual = orderService.getById(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdNullTest() {
    OrderDto actual = orderService.getById(-1L);

    Assertions.assertNull(actual);
  }

  @Test
  void deleteTest() {
    OrderDto input = buildInput();
    orderService.create(input);
    Integer expected = orderService.getAll().size() - 1;

    orderService.delete(3L);
    Integer actual = orderService.getAll().size();

    Assertions.assertEquals(expected, actual);
  }

  private OrderDto buildInput() {
    OrderDto orderDto = new OrderDto();
    List<Long> dishIds = new ArrayList<>();
    dishIds.add(1L);
    dishIds.add(2L);
    dishIds.add(3L);
    List<Long> drinkIds = new ArrayList<>();
    drinkIds.add(1L);
    drinkIds.add(2L);
    drinkIds.add(3L);
    orderDto.setDishesIds(dishIds);
    orderDto.setDrinksIds(drinkIds);
    return orderDto;
  }
}
