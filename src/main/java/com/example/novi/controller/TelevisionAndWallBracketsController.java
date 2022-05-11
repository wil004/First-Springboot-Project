package com.example.novi.controller;

import com.example.novi.model.TelevisionAndWallBrackets;
import com.example.novi.model.dto.TelevisionDto;
import com.example.novi.model.dto.WallBracketDto;
import com.example.novi.services.TelevisionAndWallBracketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("TelevisionAndWallBracket")
public class TelevisionAndWallBracketsController {

    private final TelevisionAndWallBracketsService televisionAndWallBracketsService;

    @Autowired
    public TelevisionAndWallBracketsController(TelevisionAndWallBracketsService televisionAndWallBracketsService) {
        this.televisionAndWallBracketsService = televisionAndWallBracketsService;
    }

    @GetMapping(value = "/televisions")
    public ResponseEntity<List<TelevisionDto>> printAllTelevisionsWithWallBrackets() {
        return ResponseEntity.ok(televisionAndWallBracketsService.allTelevisionsWithWallBrackets());
    }

    @GetMapping(value = "/wallBrackets")
    public ResponseEntity<List<WallBracketDto>> printAllWallBracketsWithTelevisions() {
        return ResponseEntity.ok(televisionAndWallBracketsService.allWallBracketsWithTelevisions());
    }
    @PutMapping(value = "{televisionId}/{wallBracketId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TelevisionAndWallBrackets> connectTelevisionsAndWallBrackets(@PathVariable Long televisionId , @PathVariable Long wallBracketId) {
        TelevisionAndWallBrackets televisionAndWallBrackets = televisionAndWallBracketsService.connectTelevisionsAndWallBrackets(televisionId, wallBracketId);
        return ResponseEntity.ok(televisionAndWallBrackets);
    }
}
