package com.example.faceittest;

import com.example.faceittest.dto.CuisineDto;
import com.example.faceittest.dto.DishDto;
import com.example.faceittest.entity.Cuisine;
import com.example.faceittest.entity.Dish;
import com.example.faceittest.service.impl.CuisineService;
import com.example.faceittest.service.impl.DishService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class DishTests {

  @Autowired
  private DishService dishService;
  @Autowired
  private CuisineService cuisineService;

  @BeforeAll
  static void init(@Autowired CuisineService cuisineService) {
    CuisineDto cuisineDto = new CuisineDto();
    cuisineDto.setName("Polish");
    cuisineService.create(cuisineDto);
  }

  @Test
  void createTest() {
    DishDto input = buildInput();
    Dish expected = new Dish();
    expected.setId(2L);
    expected.setPrice(1.0);
    expected.setName("Pizza");
    expected.setCuisine(buildICuisineExpected());

    Dish actual = dishService.create(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdTest() {
    Long input = 1L;
    DishDto expected = buildInput();
    dishService.create(expected);
    expected.setId(input);

    DishDto actual = dishService.getById(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdNullTest() {
    DishDto actual = dishService.getById(-1L);

    Assertions.assertNull(actual);
  }

  @Test
  void deleteTest() {
    DishDto input = buildInput();
    dishService.create(input);
    Integer expected = dishService.getAll().size() - 1;

    dishService.delete(3L);
    Integer actual = dishService.getAll().size();

    Assertions.assertEquals(expected, actual);
  }

  private DishDto buildInput() {
    DishDto dishDto = new DishDto();
    dishDto.setName("Pizza");
    dishDto.setPrice(1.0);
    dishDto.setCuisineId(1L);
    return dishDto;
  }

  private Cuisine buildICuisineExpected() {
    Cuisine cuisine = new Cuisine();
    cuisine.setId(1L);
    cuisine.setName("Polish");
    return cuisine;
  }
}
