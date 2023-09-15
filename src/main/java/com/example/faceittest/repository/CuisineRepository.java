package com.example.faceittest.repository;

import com.example.faceittest.entity.Cuisine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends CrudRepository<Cuisine, Long> {

}
