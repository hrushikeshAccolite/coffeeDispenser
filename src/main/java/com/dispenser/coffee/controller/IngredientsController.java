package com.dispenser.coffee.controller;

import com.dispenser.coffee.models.Ingredients;
import com.dispenser.coffee.services.IngredientsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientsController {
    @Autowired
    private IngredientsServices ingredientsServices;

    @GetMapping("/ingredients")
    private ResponseEntity<List<Ingredients>> getAllIngredients(){
        return ResponseEntity.ok().body(this.ingredientsServices.getAllIngredients());
    }

    @GetMapping("/ingredient/{ingredientId}")
    private Ingredients getIngredientById(@PathVariable long ingredientId){
        return this.ingredientsServices.getIngredientById(ingredientId);
    }

    @PostMapping("/addIngredient")
    private ResponseEntity<Ingredients> addIngredient(@RequestBody Ingredients ingredients){
        return ResponseEntity.ok().body(this.ingredientsServices.createIngredient(ingredients));
    }

    @PutMapping("/updateIngredient/{ingredientId}")
    private ResponseEntity<Ingredients> updateIngredient(@PathVariable long ingredientId,@RequestBody Ingredients ingredients){
        ingredients.setIngredientId(ingredientId);
        return ResponseEntity.ok().body(this.ingredientsServices.updateIngredient((ingredients)));
    }

    @DeleteMapping("/deleteIngredient/{ingredientId}")
    private HttpStatus deleteIngredient(@PathVariable long ingredientId){
        this.ingredientsServices.deleteIngredient(ingredientId);
        return HttpStatus.OK;
    }
}
