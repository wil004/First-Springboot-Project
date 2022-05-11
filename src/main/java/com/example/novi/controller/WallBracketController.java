package com.example.novi.controller;

import com.example.novi.model.dto.CreateWallBracketDto;
import com.example.novi.model.dto.WallBracketDto;
import com.example.novi.services.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/WallBracket")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    @Autowired
    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }


    @GetMapping()
    public ResponseEntity<List<WallBracketDto>> printAllWallBrackets() {
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }

    @PostMapping
    public ResponseEntity<WallBracketDto>
    createWallBracket(@RequestBody CreateWallBracketDto createWallBracketDto) {

        final WallBracketDto wallBracketDto = wallBracketService.createWallBracket(createWallBracketDto);
        final URI location = URI.create("/wallBracket" + wallBracketDto.getId());
        return ResponseEntity.created(location).body(wallBracketDto);
    }
}

