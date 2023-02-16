package com.dispenser.coffee.services;

import com.dispenser.coffee.exception.DispenserException;
import com.dispenser.coffee.models.Ingredients;
import com.dispenser.coffee.repository.IngredientsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientsServiceImpl implements IngredientsServices{

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Override
    public Ingredients createIngredient(Ingredients ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    @Override
    public Ingredients updateIngredient(Ingredients ingredient) {
        Optional<Ingredients> ingredientsObj = this.ingredientsRepository.findById(ingredient.getIngredientId());
        if(ingredientsObj.isPresent()){
            Ingredients updatedIngredient = ingredientsObj.get();
            updatedIngredient.setBeverage(ingredient.getBeverage());
            updatedIngredient.setQuantityRequired(ingredient.getQuantityRequired());
            updatedIngredient.setStock(ingredient.getStock());
            return this.ingredientsRepository.save(updatedIngredient);
        } else {
            throw new DispenserException("Ingredient not found with id " + ingredient.getIngredientId());
        }
    }

    @Override
    public List<Ingredients> getAllIngredients() {
        return this.ingredientsRepository.findAll();
    }

    @Override
    public Ingredients getIngredientById(Long ingredientId) {
        Optional<Ingredients> ingredientsObj = this.ingredientsRepository.findById(ingredientId);
        if(ingredientsObj.isPresent()){
            return ingredientsObj.get();
        } else {
            throw new DispenserException("Ingredient not found with id " + ingredientId);
        }
    }

    @Override
    public void deleteIngredient(Long ingredientId) {
    Optional<Ingredients> ingredientsObj = this.ingredientsRepository.findById(ingredientId);
    if(ingredientsObj.isPresent()){
        this.ingredientsRepository.deleteById(ingredientId);
    } else {
        throw new DispenserException("Ingredient not found with id " + ingredientId);
    }
    }
}
