package com.dispenser.coffee.controller;

import com.dispenser.coffee.models.Stock;
import com.dispenser.coffee.services.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockServices stockServices;

    @GetMapping("/allstock")
    private ResponseEntity<List<Stock>> getAllStock(){
        return ResponseEntity.ok().body(this.stockServices.getAllStock());
    }

    @GetMapping("/stock/{stockId}")
    private Stock getStockById(@PathVariable long stockId){
        return this.stockServices.getStockById(stockId);
    }

    @PostMapping("/addStock")
    private ResponseEntity<Stock> addStock(@RequestBody Stock stock){
        return ResponseEntity.ok().body(this.stockServices.addStock(stock));
    }

    @PutMapping("/updateStock/{stockId}")
    private ResponseEntity<Stock> updateStock(@PathVariable long stockId, @RequestBody Stock stock){
        stock.setStockId(stockId);
        return ResponseEntity.ok().body(this.stockServices.updateStock(stock));
    }

    @DeleteMapping("/deleteStock/{stockId}")
    private HttpStatus deleteStock(@PathVariable long stockId){
        this.stockServices.deleteStock(stockId);
        return HttpStatus.OK;
    }
}
