package com.dispenser.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dispenser.coffee.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
    
}
