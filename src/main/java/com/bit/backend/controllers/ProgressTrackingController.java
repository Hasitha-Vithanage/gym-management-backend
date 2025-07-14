package com.bit.backend.controllers;

import com.bit.backend.dtos.ProgressTrackingDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.ProgressTrackingServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
public class ProgressTrackingController {

    private ProgressTrackingServiceI progressTrackingServiceI;

    public ProgressTrackingController(ProgressTrackingServiceI progressTrackingServiceI) {
        this.progressTrackingServiceI = progressTrackingServiceI;
    }

    @PostMapping(value = {"/progress-tracking/{userName}"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProgressTrackingDto> addProgressTrackingEntity(@PathVariable String userName,
                                                                         @RequestPart("progressForm") ProgressTrackingDto progressTrackingDto,
                                                                         @RequestPart("frontImage") MultipartFile frontImage,
                                                                         @RequestPart("sideImage") MultipartFile sideImage,
                                                                         @RequestPart("backImage") MultipartFile backImage) {
        try {
            progressTrackingDto.setUserName(userName);

            progressTrackingDto.setFrontImage(frontImage.getBytes());
            progressTrackingDto.setFrontImageName(frontImage.getOriginalFilename());
            progressTrackingDto.setFrontImageType(frontImage.getContentType());

            progressTrackingDto.setSideImage(sideImage.getBytes());
            progressTrackingDto.setSideImageName(sideImage.getOriginalFilename());
            progressTrackingDto.setSideImageType(sideImage.getContentType());

            progressTrackingDto.setBackImage(backImage.getBytes());
            progressTrackingDto.setBackImageName(backImage.getOriginalFilename());
            progressTrackingDto.setBackImageType(backImage.getContentType());

            ProgressTrackingDto progressTrackingDtoResponse = progressTrackingServiceI.addProgressTrackingEntity(progressTrackingDto);
            return ResponseEntity.created(URI.create("/progress-tracking"+progressTrackingDtoResponse.getId())).body(progressTrackingDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
