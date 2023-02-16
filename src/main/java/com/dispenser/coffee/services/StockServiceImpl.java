package com.dispenser.coffee.services;

import com.dispenser.coffee.exception.DispenserException;
import com.dispenser.coffee.models.Stock;
import com.dispenser.coffee.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockServiceImpl implements StockServices{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Stock stock) {
        Optional<Stock> stockObj = this.stockRepository.findById(stock.getStockId());
        if (stockObj.isPresent()) {
            Stock updatedStock = stockObj.get();
            updatedStock.setName(stock.getName());
            updatedStock.setQuantity(stock.getQuantity());
            return this.stockRepository.save(updatedStock);
        } else {
            throw new DispenserException("Stock not found with id " + stock.getStockId());
        }
    }

    @Override
    public List<Stock> getAllStock() {
        return this.stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long stockId) {
        Optional<Stock> stockObj = this.stockRepository.findById(stockId);
        if(stockObj.isPresent()){
            return stockObj.get();
        } else {
            throw new DispenserException("Stock not found with id" + stockId);
        }
    }

    @Override
    public void deleteStock(Long stockId) {
        Optional<Stock> stockObj = this.stockRepository.findById(stockId);
        if(stockObj.isPresent()){
            this.stockRepository.deleteById(stockId);
        } else {
            throw new DispenserException("Stock not found with id " + stockId);
        }
    }

    @Override
    public Stock reduceStock(Stock stock, int quantity) {
        stock.setQuantity(Math.abs(stock.getQuantity() - quantity));
        return stockRepository.save(stock);
    }
}
