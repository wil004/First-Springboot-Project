package com.example.novi.controller;


import com.example.novi.model.dto.CiModuleDto;
import com.example.novi.model.dto.CreateCiModuleDto;
import com.example.novi.services.CiModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/CiModule")
public class CiModuleController {

    private CiModuleService ciModuleService;

    @Autowired
    public CiModuleController(CiModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping()
    public ResponseEntity<List<CiModuleDto>> printAllCiModules() {
        return ResponseEntity.ok(ciModuleService.getAllCiModules());
    }

    @PostMapping
    public ResponseEntity<CiModuleDto>
    createCiModule(@RequestBody CreateCiModuleDto createCiModuleDto) {

        final CiModuleDto ciModuleDto = ciModuleService.createCiModule(createCiModuleDto);
        final URI location = URI.create("/CiModule" + ciModuleDto.getId());
        return ResponseEntity.created(location).body(ciModuleDto);
    }
}
