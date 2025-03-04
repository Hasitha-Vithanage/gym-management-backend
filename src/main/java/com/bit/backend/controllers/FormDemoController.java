package com.bit.backend.controllers;

import com.bit.backend.dtos.FormDemoDto;
import com.bit.backend.entities.FormDemoEntity;
import com.bit.backend.repositories.FormDemoRepository;
import com.bit.backend.services.FormDemoServiceI;
import com.bit.backend.services.impl.FormDemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class FormDemoController {

    private final FormDemoServiceI formDemoServiceI;
    private final FormDemoRepository formDemoRepository;

    public FormDemoController(FormDemoServiceI formDemoServiceI, FormDemoRepository formDemoRepository) {
        this.formDemoServiceI = formDemoServiceI;
        this.formDemoRepository = formDemoRepository;
    }

    @PostMapping("/form-demo")
    public ResponseEntity<FormDemoDto> addForm(@RequestBody FormDemoDto formDemoDto) {
        FormDemoDto formDemoDtoResponse = formDemoServiceI.addFormDemoEntry(formDemoDto);
        return ResponseEntity.created(URI.create("/form-demo"+formDemoDtoResponse.getFirstName())).body(formDemoDtoResponse);
    }


    @GetMapping("/form-demo")
    public ResponseEntity<List<FormDemoDto>> getAllFormDemos() {
        List<FormDemoDto> formDemoDtoList = formDemoServiceI.getAllFormDemoEntries();
        return ResponseEntity.ok(formDemoDtoList);
    }
}
