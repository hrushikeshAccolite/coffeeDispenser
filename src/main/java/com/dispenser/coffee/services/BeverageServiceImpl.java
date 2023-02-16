package com.dispenser.coffee.services;

import com.dispenser.coffee.exception.DispenserException;
import com.dispenser.coffee.models.Beverage;
import com.dispenser.coffee.models.Ingredients;
import com.dispenser.coffee.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BeverageServiceImpl implements BeverageServices{

    @Autowired
    private BeverageRepository beverageRepository;

    @Override
    public Beverage addBeverage(Beverage beverage) {
        return beverageRepository.save(beverage);
    }

    @Override
    public List<Beverage> getAllBeverage() {
        return this.beverageRepository.findAll();
    }

    @Override
    public Beverage getBeverageById(Long beverageId) {
        Optional<Beverage> beverageObj = this.beverageRepository.findById(beverageId);
        if(beverageObj.isPresent()){
            return beverageObj.get();
        } else {
            throw new DispenserException("Beverage not found with id" + beverageId);
        }
    }

    @Override
    public Beverage updateBeverage(Beverage beverage) {
        Optional<Beverage> beverageObj = this.beverageRepository.findById(beverage.getBeverageId());

        if(beverageObj.isPresent()){
            Beverage updatedBeverage = beverageObj.get();
            updatedBeverage.setBeverageName(beverage.getBeverageName());
            updatedBeverage.setIngredients(beverage.getIngredients());
            return this.beverageRepository.save(updatedBeverage);
        } else {
            throw new DispenserException("Beverage not found with id "+ beverage.getBeverageId());
        }
    }

    @Override
    public void deleteBeverage(Long beverageId) {
        Optional<Beverage> beverageObj = this.beverageRepository.findById(beverageId);

        if(beverageObj.isPresent()){
            this.beverageRepository.deleteById(beverageId);
        } else {
            throw new DispenserException("Beverage not found with id "+ beverageId);
        }
    }

    @Override
    public boolean checkAvailability(Long beverageId) {
        Beverage beverage = getBeverageById(beverageId);
        return beverage.isAvailable();
    }

    public List<Beverage> checkAvailability() throws Exception {
        List<Beverage> beverageList = getAllBeverage();
        beverageList.removeIf(beverage -> (!beverage.isAvailable()));
        if(beverageList.isEmpty())
            throw new Exception("No Beverages are available");
        return beverageList;
    }

    public void changeAvailability(Beverage beverage){
        List<Ingredients> ingredientsList = beverage.getIngredients();
        for(Ingredients i : ingredientsList) {
            if(i.getStock().getQuantity() < i.getQuantityRequired()) {
                beverage.setAvailable(false);
                break;
            }
        }
        beverageRepository.save(beverage);
    }

    @Override
    public void updateAvailability() throws Exception {
        List<Beverage> beverageList = checkAvailability();
        for(Beverage b : beverageList) {
            changeAvailability(b);
        }
    }
}
