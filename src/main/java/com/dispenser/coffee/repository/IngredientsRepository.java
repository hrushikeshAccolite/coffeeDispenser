package com.dispenser.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dispenser.coffee.models.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long>{
    
}
