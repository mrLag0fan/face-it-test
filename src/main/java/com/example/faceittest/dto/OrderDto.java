package com.example.faceittest.dto;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderDto {

  private Long id;
  private List<Long> dishesIds;
  private List<Long> drinksIds;
}
