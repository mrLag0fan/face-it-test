package com.example.faceittest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityConverterImpl<T, D> implements DtoToEntityConverter<T, D> {

  private final ModelMapper modelMapper;

  public DtoToEntityConverterImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public T convertDtoToEntity(D dto, Class<T> entityClass) {
    return modelMapper.map(dto, entityClass);
  }

  public D convertEntityToDto(T entity, Class<D> dtoClass) {
    return entity != null ? modelMapper.map(entity, dtoClass) : null;
  }
}
