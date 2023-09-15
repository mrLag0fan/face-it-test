package com.example.faceittest;

import com.example.faceittest.dto.CuisineDto;
import com.example.faceittest.entity.Cuisine;
import com.example.faceittest.service.impl.CuisineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CuisineTests {

  @Autowired
  private CuisineService cuisineService;

  @BeforeEach
  void clear() {
    cuisineService.getAll().forEach(c -> cuisineService.delete(c.getId()));
  }

  @Test
  void createTest() {
    CuisineDto input = buildInput();
    Cuisine expected = new Cuisine();
    expected.setId(2L);
    expected.setName("Polish");

    Cuisine actual = cuisineService.create(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdTest() {
    Long input = 1L;
    CuisineDto expected = buildInput();
    cuisineService.create(expected);
    expected.setId(input);

    CuisineDto actual = cuisineService.getById(input);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByIdNullTest() {
    CuisineDto actual = cuisineService.getById(-1L);

    Assertions.assertNull(actual);
  }

  @Test
  void deleteTest() {
    CuisineDto input = buildInput();
    cuisineService.create(input);
    Integer expected = cuisineService.getAll().size() - 1;

    cuisineService.delete(3L);
    Integer actual = cuisineService.getAll().size();

    Assertions.assertEquals(expected, actual);
  }

  private CuisineDto buildInput() {
    CuisineDto cuisineDto = new CuisineDto();
    cuisineDto.setName("Polish");
    return cuisineDto;
  }
}
