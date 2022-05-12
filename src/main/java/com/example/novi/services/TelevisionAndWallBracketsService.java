package com.example.novi.services;

import com.example.novi.controller.exceptions.RecordNotFoundException;
import com.example.novi.model.Television;
import com.example.novi.model.TelevisionAndWallBrackets;
import com.example.novi.model.WallBracket;
import com.example.novi.model.dto.TelevisionAndWallBracketsDto;
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
    private final TelevisionAndWallBracketsRepository televisionAndWallBracketsRepository;

    public TelevisionAndWallBracketsService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository, TelevisionService televisionService, WallBracketService wallBracketService, TelevisionAndWallBracketsRepository televisionAndWallBracketsRepository) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.televisionService = televisionService;
        this.wallBracketService = wallBracketService;
        this.televisionAndWallBracketsRepository = televisionAndWallBracketsRepository;
    }

    public void connectTelevisionsAndWallBrackets(Long televisionId, Long wallBracketId) {
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


    public TelevisionAndWallBracketsDto createTelevisionAndWallBracket(TelevisionAndWallBracketsDto televisionAndWallBracketsDto) {
        boolean televisionExists = false;
        int existingTelevisionIndex = 0;
        boolean wallBracketExists = false;
        int existingWallBracketIndex = 0;

        List<Television> television = televisionRepository.findAll();
        List<WallBracket> wallBracket = wallBracketRepository.findAll();

        for (int i = 0; i < television.size(); i++) {
            if(Objects.equals(television.get(i).getId(), televisionAndWallBracketsDto.getTelevisionId())) {
                televisionExists = true;
                existingTelevisionIndex = i;
            }
        }

        for (int i = 0; i < wallBracket.size(); i++) {
            if (Objects.equals(wallBracket.get(i).getId(), televisionAndWallBracketsDto.getWallBracketId())) {
                wallBracketExists = true;
                existingWallBracketIndex = i;
            }
        }


        if (televisionExists && wallBracketExists) {
            boolean relationAlreadyExists = false;

            TelevisionAndWallBrackets televisionAndWallBrackets = new TelevisionAndWallBrackets();
            Television newTelevision = television.get(existingTelevisionIndex);
            WallBracket newWallBracket = wallBracket.get(existingWallBracketIndex);

            List<TelevisionAndWallBrackets> televisionAndWallBracketsListTelevisions = newTelevision.getTelevisionAndWallBrackets();
            List<TelevisionAndWallBrackets> televisionAndWallBracketsListWallBrackets = newWallBracket.getTelevisionAndWallBrackets();

            for (int i = 0; i < televisionAndWallBracketsListTelevisions.size(); i++) {
                if(Objects.equals(televisionAndWallBracketsListTelevisions.get(i).getTelevisionId().getId(), televisionAndWallBracketsDto.getTelevisionId())
                && Objects.equals(televisionAndWallBracketsListTelevisions.get(i).getWallBracketId().getId(), televisionAndWallBracketsDto.getWallBracketId())) {
                    relationAlreadyExists = true;
                }
            }
            if (relationAlreadyExists) {
                throw new RecordNotFoundException("This relation already exists");
            }

            televisionAndWallBracketsListTelevisions.add(televisionAndWallBrackets);
            televisionAndWallBracketsListWallBrackets.add(televisionAndWallBrackets);

            newTelevision.setTelevisionAndWallBrackets(televisionAndWallBracketsListTelevisions);
            newWallBracket.setTelevisionAndWallBrackets(televisionAndWallBracketsListWallBrackets);

            televisionAndWallBrackets.setTelevisionId(newTelevision);
            televisionAndWallBrackets.setWallBracketId(newWallBracket);


            televisionRepository.save(newTelevision);
            wallBracketRepository.save(newWallBracket);
            televisionAndWallBracketsRepository.save(televisionAndWallBrackets);

            return televisionAndWallBracketsDto;
        } else {
            throw new RecordNotFoundException("Television Id Or WallBracket Id is incorrect!");
        }
    }

    public List<WallBracketDto> findTelevisionWithWallBracketsById(Long televisionId) {
        Television television = televisionRepository.findById(televisionId).orElseThrow();
        List<WallBracketDto> wallBracketDtoList = new ArrayList<>();
        for(int i = 0; i < television.getTelevisionAndWallBrackets().size(); i++) {
            wallBracketDtoList.add(wallBracketService.transferToWallBracketDto(television.getTelevisionAndWallBrackets().get(i).getWallBracketId()));
        }
        return wallBracketDtoList;
    }

    public List<TelevisionDto> findWallBracketWithTelevisionById(Long wallBracketId) {
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElseThrow();
        List<TelevisionDto> televisionDtoList = new ArrayList<>();
        for(int i = 0; i < wallBracket.getTelevisionAndWallBrackets().size(); i++) {
            televisionDtoList.add(televisionService.transferToTelevisionDto(wallBracket.getTelevisionAndWallBrackets().get(i).getTelevisionId()));
        }
        return televisionDtoList;
    }
}
