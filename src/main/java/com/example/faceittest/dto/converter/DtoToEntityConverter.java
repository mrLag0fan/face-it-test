package com.example.faceittest.dto.converter;

public interface DtoToEntityConverter<T, D> {

  T convertDtoToEntity(D dto, Class<T> entityClass);

  D convertEntityToDto(T entity, Class<D> dtoClass);
}
