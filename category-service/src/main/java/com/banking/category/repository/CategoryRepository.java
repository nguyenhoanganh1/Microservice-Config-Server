package com.banking.category.repository;

import com.banking.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(Integer integer);
}
