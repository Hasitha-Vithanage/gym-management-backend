package com.bit.backend.controllers;

import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.repositories.MealPlanRepository;
import com.bit.backend.services.MealPlanServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MealPlanController {

    private final MealPlanServiceI mealPlanServiceI;
    private final MealPlanRepository mealPlanRepository;

    public MealPlanController(MealPlanServiceI mealPlanServiceI, MealPlanRepository mealPlanRepository) {
        this.mealPlanServiceI = mealPlanServiceI;
        this.mealPlanRepository = mealPlanRepository;
    }

    @PostMapping("/nutrition&meal-plan")
    public ResponseEntity<MealPlanDto> addMealPlan(@RequestBody MealPlanDto mealPlanDto) {
        MealPlanDto mealPlanDtoResponse = mealPlanServiceI.addMealPlanEntry(mealPlanDto);
        return ResponseEntity.created(URI.create("/nutrition&meal-plan"+mealPlanDtoResponse.getUsername())).body(mealPlanDtoResponse);
    }


    @GetMapping("/nutrition&meal-plan")
    public ResponseEntity<List<MealPlanDto>> getAllMealPlans() {
        List<MealPlanDto> mealPlanDtoList = mealPlanServiceI.getAllMealPlanEntries();
        return ResponseEntity.ok(mealPlanDtoList);
    }
}
