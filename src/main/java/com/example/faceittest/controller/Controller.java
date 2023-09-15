package com.example.faceittest.controller;

import com.example.faceittest.service.CrudService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public abstract class Controller<T, D> {

  private final CrudService<T, D> service;

  @GetMapping
  public ResponseEntity<List<D>> getAll() {
    List<D> result = service.getAll();
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<D> getCuisineById(@PathVariable Long id) {
    D result = service.getById(id);
    if (result != null) {
      return ResponseEntity.ok(result);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<T> createCuisine(@RequestBody D input) {
    T savedResult = service.create(input);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedResult);
  }

//  @PutMapping("/{id}")
//  public ResponseEntity<T> updateCuisine(@PathVariable Long id, @RequestBody D updatedCuisine) {
//    D existingResult = service.getById(id);
//    if (existingResult != null) {
//      existingResult.setName(updatedCuisine.getName());
//      // Оновлюємо інші поля за необхідності
//      Cuisine savedCuisine = cuisineRepository.save(cuisine);
//      return ResponseEntity.ok(savedCuisine);
//    } else {
//      return ResponseEntity.notFound().build();
//    }
//  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCuisine(@PathVariable Long id) {
    D result = service.getById(id);
    if (result != null) {
      service.delete(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
