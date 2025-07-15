package com.bit.backend.controllers;

import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.repositories.MealPlanUploadRepository;
import com.bit.backend.services.MealPlanServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class MealPlanController {

    private final MealPlanServiceI mealPlanServiceI;
    private final MealPlanUploadRepository mealPlanUploadRepository;

    public MealPlanController(MealPlanServiceI mealPlanServiceI, MealPlanUploadRepository mealPlanUploadRepository) {
        this.mealPlanServiceI = mealPlanServiceI;
        this.mealPlanUploadRepository = mealPlanUploadRepository;
    }

    @PostMapping("/nutrition&meal-plan")
    public ResponseEntity<MealPlanDto> addMealPlan(@RequestBody MealPlanDto mealPlanDto) {
        MealPlanDto mealPlanDtoResponse = mealPlanServiceI.addMealPlanEntry(mealPlanDto);
        return ResponseEntity.created(URI.create("/nutrition&meal-plan"+mealPlanDtoResponse.getUserId())).body(mealPlanDtoResponse);
    }

    @GetMapping("/nutrition&meal-plan")
    public ResponseEntity<List<MealPlanDto>> getAllMealPlans() {
        List<MealPlanDto> mealPlanDtoList = mealPlanServiceI.getAllMealPlanEntries();
        return ResponseEntity.ok(mealPlanDtoList);
    }

    @GetMapping("/nutrition&meal-plan/{userId}")
    public ResponseEntity<Boolean> hasExistingRequest(@PathVariable String userId) {
        boolean exists = mealPlanServiceI.hasRequestedMealPlan(userId);
        return ResponseEntity.ok(exists);
    }

}
