package com.example.novi.services;

import com.example.novi.controller.exceptions.RecordNotFoundException;
import com.example.novi.model.Television;
import com.example.novi.model.TelevisionAndWallBrackets;
import com.example.novi.model.WallBracket;
import com.example.novi.model.dto.TelevisionDto;
import com.example.novi.model.dto.WallBracketDto;
import com.example.novi.repository.TelevisionAndWallBracketsRepository;
import com.example.novi.repository.TelevisionRepository;
import com.example.novi.repository.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TelevisionAndWallBracketsService {

    private final TelevisionRepository televisionRepository;
    private final WallBracketRepository wallBracketRepository;
    private final TelevisionService televisionService;
    private final WallBracketService wallBracketService;

    public TelevisionAndWallBracketsService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository, TelevisionAndWallBracketsRepository televisionAndWallBracketsRepository, TelevisionService televisionService, WallBracketService wallBracketService) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.televisionService = televisionService;
        this.wallBracketService = wallBracketService;
    }

    public TelevisionAndWallBrackets connectTelevisionsAndWallBrackets(Long televisionId, Long wallBracketId) {
        TelevisionAndWallBrackets televisionAndWallBrackets = new TelevisionAndWallBrackets();
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElseThrow();
        Television television = televisionRepository.findById(televisionId).orElseThrow();
        boolean wallBracketAndTelevisionCombinationAlreadyExist = false;

        for (int i = 0; i < wallBracket.getTelevisions().size(); i++) {
            if (Objects.equals(wallBracket.getTelevisions().get(i).getId(), televisionId)) {
                wallBracketAndTelevisionCombinationAlreadyExist = true;
            }
        }
        if (!wallBracketAndTelevisionCombinationAlreadyExist) {
            List<Television> wallBracketsWithTelevisions = wallBracket.getTelevisions();
            wallBracketsWithTelevisions.add(television);
            wallBracket.setTelevisions(wallBracketsWithTelevisions);
            wallBracketRepository.save(wallBracket);

            List<WallBracket> televisionsWithWallBrackets = television.getWallBrackets();
            televisionsWithWallBrackets.add(wallBracket);
            television.setWallBrackets(televisionsWithWallBrackets);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException("These objects are already connected to eachother!");
        }
        return televisionAndWallBrackets;
        }


    public List<TelevisionDto> allTelevisionsWithWallBrackets() {
        List<Television> televisionList = new ArrayList<>(televisionRepository.findAll());
        List<TelevisionDto> televisionDtoList = new ArrayList<>();
        for (int i = 0; i < televisionList.size(); i++) {
            List<WallBracketDto> wallBracketDtoList = new ArrayList<>();
            televisionDtoList.add(televisionService.transferToTelevisionDto(televisionList.get(i)));
            for (int j = 0; j < televisionList.get(i).getWallBrackets().size(); j++) {
                wallBracketDtoList.add(wallBracketService.transferToWallBracketDto(televisionList.get(i).getWallBrackets().get(j)));
            }
            televisionDtoList.get(i).setWallBracketDto(wallBracketDtoList);
        }
        return televisionDtoList;
    }

    public List<WallBracketDto> allWallBracketsWithTelevisions() {
        List<WallBracket> wallBracketList = new ArrayList<>(wallBracketRepository.findAll());
        List<WallBracketDto> wallBracketDtoList = new ArrayList<>();
        for (int i = 0; i < wallBracketList.size(); i++) {
            List<TelevisionDto> televisionDtoList = new ArrayList<>();
            wallBracketDtoList.add(wallBracketService.transferToWallBracketDto(wallBracketList.get(i)));
            for (int j = 0; j < wallBracketList.get(i).getTelevisions().size(); j++) {
                televisionDtoList.add(televisionService.transferToTelevisionDto(wallBracketList.get(i).getTelevisions().get(j)));
            }
            wallBracketDtoList.get(i).setTelevisionDto(televisionDtoList);
        }
        return wallBracketDtoList;
    }
}
