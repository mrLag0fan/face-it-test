package com.example.faceittest.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class DrinkDto {

  private Long id;
  private String name;
  private Double price;
  private boolean isWithIceCubes;
  private boolean isWithLemon;
  private Long cuisineId;
}
