package com.dispenser.coffee.services;

import com.dispenser.coffee.models.Stock;

import java.util.List;

public interface StockServices {
    Stock addStock(Stock stock);
    Stock updateStock(Stock stock);
    List<Stock> getAllStock();
    Stock getStockById(Long stockId);
    void deleteStock(Long stockId);

    Stock reduceStock(Stock stock, int count);
}
