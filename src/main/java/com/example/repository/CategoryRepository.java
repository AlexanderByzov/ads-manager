package com.example.repository;

import com.example.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Override
    Collection<Category> findAll();
}
