package com.dispenser.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dispenser.coffee.models.Beverage;
import com.dispenser.coffee.models.Ingredients;
import com.dispenser.coffee.models.Stock;
import com.dispenser.coffee.services.BeverageServices;
import com.dispenser.coffee.services.StockServices;

@RestController
public class BeverageController {
    @Autowired
    private BeverageServices beverageServices;
    private StockServices stockServices;

    @GetMapping("/beverages")
    private ResponseEntity<List<Beverage>> getAllBevs(){
        return ResponseEntity.ok().body(this.beverageServices.getAllBeverage());
    }

    @GetMapping("/beverage/{beverageId}")
    private Beverage getBevById(@PathVariable long beverageId){
        return this.beverageServices.getBeverageById(beverageId);
    }

    @PostMapping("/addBeverage")
    private ResponseEntity<Beverage> addBev(@RequestBody Beverage beverage){
        return ResponseEntity.ok().body(this.beverageServices.addBeverage(beverage));
    }

    @PutMapping("/updateBeverage/{beverageId}")
    private ResponseEntity<Beverage> updateBev(@PathVariable long beverageId, @RequestBody Beverage beverage){
        beverage.setBeverageId(beverageId);
        return ResponseEntity.ok().body(this.beverageServices.updateBeverage(beverage));
    }

    @DeleteMapping("/deleteBeverage/{beverageId}")
    private HttpStatus deleteBev(@PathVariable long beverageId){
        this.beverageServices.deleteBeverage(beverageId);
        return HttpStatus.OK;
    }

    @GetMapping("/order/{beverageId}")
    private ResponseEntity<Beverage> orderBev(@PathVariable long beverageId){
        if(!beverageServices.checkAvailability(beverageId)){
            Beverage beverage = beverageServices.getBeverageById(beverageId);
            List<Ingredients> ingredientsList = beverage.getIngredients();
            for(Ingredients i : ingredientsList) {
                Stock stock = stockServices.reduceStock(i.getStock(),i.getQuantityRequired());
                stockServices.updateStock(stock);
            }
            beverageServices.changeAvailability(beverage);
        }
        return ResponseEntity.ok().body(this.beverageServices.getBeverageById(beverageId)); 

    }
}
