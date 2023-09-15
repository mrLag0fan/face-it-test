package com.example.faceittest.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class DishDto {

  private Long id;
  private String name;
  private Double price;
  private Long cuisineId;
}
