package com.example.faceittest.service;

import com.example.faceittest.dto.converter.DtoToEntityConverter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;

@RequiredArgsConstructor
public abstract class CrudService<T, D> {

  protected final CrudRepository<T, Long> repository;
  protected final DtoToEntityConverter<T, D> dtoToEntityConverter;


  public T create(D dto) {
    Class<T> clazz = (Class<T>) getParameterizedType().getActualTypeArguments()[0];
    T dtoToEntity = dtoToEntityConverter.convertDtoToEntity(dto, clazz);
    return repository.save(dtoToEntity);
  }

  public D getById(Long id) {
    Class<D> clazz = (Class<D>) getParameterizedType().getActualTypeArguments()[1];
    return dtoToEntityConverter.convertEntityToDto(repository.findById(id).orElse(null), clazz);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public List<D> getAll() {
    Class<D> clazz = (Class<D>) getParameterizedType().getActualTypeArguments()[1];
    return ((List<T>) repository.findAll()).stream()
        .map(a -> dtoToEntityConverter.convertEntityToDto(a, clazz)).collect(
            Collectors.toList());
  }

  private ParameterizedType getParameterizedType() {
    Type type = getClass().getGenericSuperclass();
    ParameterizedType parameterizedType = (ParameterizedType) type;
    return parameterizedType;
  }
}
