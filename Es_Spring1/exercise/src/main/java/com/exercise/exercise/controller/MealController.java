package com.exercise.exercise.controller;

import com.exercise.exercise.service.Meal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MealController {

    List<Meal> mealList = new ArrayList<>();

    List<Meal> mealList1 = Arrays.asList(
            new Meal("Carbonara", "Good", 8.99),
            new Meal("Bistecca", "NotBad", 12.56),
            new Meal("Caviale", "Delicious", 45.8)
    );

    @GetMapping(value = "/meals")

    public ResponseEntity<List<Meal>> getMeallist() {

        return ResponseEntity.ok(mealList);
    }

    @PutMapping(value = "/meal")

    public ResponseEntity<String> putMeal(@RequestBody Meal meal) {
        this.mealList.addAll(mealList1);
        this.mealList.add(meal);
        return ResponseEntity.ok("Aggiunto Meal");

    }

    //Ripartire da qui provando Spring
    @PostMapping(value = "/meals/post/{Mealname}")

    public ResponseEntity<String> postMeal(@PathVariable String Mealname, @RequestBody Meal meal) {

        for (Meal meal1 : mealList) {

            if (meal1.getName().equals(Mealname)) {

                meal1.setName(meal.getName());
                meal1.setrating(meal.getrating());
                meal1.setPrice(meal.getPrice());

                return ResponseEntity.ok("Aggiornato Meal");
            }

        }

        return ResponseEntity.badRequest().body("Meal inesistente");
    }


    @DeleteMapping(value = "/meals/delete/{Mealname}")

    public ResponseEntity<String> deleteMeal(@PathVariable String Mealname) {

        this.mealList.removeIf(meal -> meal.getName().equals(Mealname));

        return ResponseEntity.ok("Cancellato Meal");

    }

    @DeleteMapping(value = "/meals/deleteAll/{Price}")

    public ResponseEntity<String> deleteAllforPrice(@PathVariable Double Price) {

        this.mealList.removeIf(meal->meal.getPrice()<=Price);

        return ResponseEntity.ok("Cancellato secondo il Prezzo");

    }

    @PutMapping(value = "/meals/{MealName}/price")

    public ResponseEntity<String> updateMeal(@PathVariable String MealName,@RequestBody Meal meal){

        Meal meal1 = mealList.stream().filter(m->m.getName().contains(MealName)).findFirst().get();
        meal1.setPrice(meal.getPrice());

        return  ResponseEntity.ok("Aggiornato prezzo");

    }


}