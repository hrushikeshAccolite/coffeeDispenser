package com.dispenser.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dispenser.coffee.models.Beverage;

public interface BeverageRepository extends JpaRepository<Beverage, Long>{
    
}
