package com.example.novi.controller;

import com.example.novi.model.TelevisionAndWallBrackets;
import com.example.novi.model.WallBracket;
import com.example.novi.model.dto.TelevisionAndWallBracketsDto;
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

    @GetMapping(value = "/tvid={televisionId}")
    public ResponseEntity<List<WallBracketDto>> printWallBracketsFromOneTelevision(@PathVariable Long televisionId) {
        return ResponseEntity.ok(televisionAndWallBracketsService.findTelevisionWithWallBracketsById(televisionId));
    }

    @GetMapping(value = "/wbid={wallBracketId}")
    public ResponseEntity<List<TelevisionDto>> printTelevisionsFromOneWallBracket(@PathVariable Long wallBracketId) {
        return ResponseEntity.ok(televisionAndWallBracketsService.findWallBracketWithTelevisionById(wallBracketId));
    }


    @PutMapping(value = "{televisionId}/{wallBracketId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void connectTelevisionsAndWallBrackets(@PathVariable Long televisionId , @PathVariable Long wallBracketId) {
      televisionAndWallBracketsService.connectTelevisionsAndWallBrackets(televisionId, wallBracketId);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TelevisionAndWallBracketsDto> connectTelevisionsAndWallBracketsInModel(@RequestBody TelevisionAndWallBracketsDto televisionAndWallBracketsDto) {
        return ResponseEntity.ok(televisionAndWallBracketsService.createTelevisionAndWallBracket(televisionAndWallBracketsDto));
    }

}
