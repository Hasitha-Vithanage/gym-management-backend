package com.bit.backend.controllers;

import com.bit.backend.dtos.MemberNutritionProfileDto;
import com.bit.backend.services.MemberNutritionProfileServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/nutrition-profile")
public class MemberNutritionProfileController {

    private final MemberNutritionProfileServiceI service;

    public MemberNutritionProfileController(MemberNutritionProfileServiceI service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MemberNutritionProfileDto> addProfile(@RequestBody MemberNutritionProfileDto dto) {
        MemberNutritionProfileDto response = service.addProfile(dto);
        return ResponseEntity.created(URI.create("/nutrition-profile/" + response.getUserId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberNutritionProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(service.getAllProfiles());
    }

    @GetMapping("/exists/{userId}")
    public ResponseEntity<Boolean> hasProfile(@PathVariable String userId) {
        return ResponseEntity.ok(service.hasProfile(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberNutritionProfileDto> getProfileByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.getProfileByUserId(userId));
    }
}
