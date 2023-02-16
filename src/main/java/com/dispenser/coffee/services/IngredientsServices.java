package com.dispenser.coffee.services;

import com.dispenser.coffee.models.Ingredients;

import java.util.List;

public interface IngredientsServices {
    Ingredients createIngredient(Ingredients ingredient);
    Ingredients updateIngredient(Ingredients ingredient);
    List<Ingredients> getAllIngredients();
    Ingredients getIngredientById(Long ingredientId);
    void deleteIngredient(Long ingredientId);
}
