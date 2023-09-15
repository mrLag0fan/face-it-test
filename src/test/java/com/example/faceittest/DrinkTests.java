package com.example.faceittest;

import com.example.faceittest.dto.CuisineDto;
import com.example.faceittest.dto.DrinkDto;
import com.example.faceittest.entity.Cuisine;
import com.example.faceittest.entity.Drink;
import com.example.faceittest.service.impl.CuisineService;
import com.example.faceittest.service.impl.DrinkService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class DrinkTests {

  @Autowired
  private DrinkService drinkService;
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
    DrinkDto input = buildInput();
    Drink expected = new Drink();
    expected.setId(2L);
    expected.setPrice(1.0);
    expected.setName("Cola");
    expected.setCuisine(buildICuisineExpected());
    expected.setWithLemon(true);
    expected.setWithIceCubes(false);

    Drink actual = drinkService.create(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdTest() {
    Long input = 1L;
    DrinkDto expected = buildInput();
    drinkService.create(expected);
    expected.setId(input);

    DrinkDto actual = drinkService.getById(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdNullTest() {
    DrinkDto actual = drinkService.getById(-1L);

    Assertions.assertNull(actual);
  }

  @Test
  void deleteTest() {
    DrinkDto input = buildInput();
    drinkService.create(input);
    Integer expected = drinkService.getAll().size() - 1;

    drinkService.delete(3L);
    Integer actual = drinkService.getAll().size();

    Assertions.assertEquals(expected, actual);
  }

  private DrinkDto buildInput() {
    DrinkDto drinkDto = new DrinkDto();
    drinkDto.setName("Cola");
    drinkDto.setPrice(1.0);
    drinkDto.setCuisineId(1L);
    drinkDto.setWithLemon(true);
    drinkDto.setWithIceCubes(false);
    return drinkDto;
  }

  private Cuisine buildICuisineExpected() {
    Cuisine cuisine = new Cuisine();
    cuisine.setId(1L);
    cuisine.setName("Polish");
    return cuisine;
  }
}
