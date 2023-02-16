package com.dispenser.coffee.services;

import com.dispenser.coffee.models.Beverage;

import java.util.List;

public interface BeverageServices {
    Beverage addBeverage(Beverage beverage);
    List<Beverage> getAllBeverage();
    Beverage getBeverageById(Long beverageId);
    Beverage updateBeverage(Beverage beverage);
    void deleteBeverage(Long beverageId);

    boolean checkAvailability(Long beverageId);

    void changeAvailability(Beverage beverage);

    void updateAvailability() throws Exception;
}
