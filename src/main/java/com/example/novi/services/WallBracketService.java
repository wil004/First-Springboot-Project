package com.example.novi.services;

import com.example.novi.model.Remote;
import com.example.novi.model.WallBracket;
import com.example.novi.model.dto.CreateRemoteDto;
import com.example.novi.model.dto.CreateWallBracketDto;
import com.example.novi.model.dto.RemoteDto;
import com.example.novi.model.dto.WallBracketDto;
import com.example.novi.repository.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    @Autowired
    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<WallBracketDto> getAllWallBrackets () {
        List<WallBracket> allWallBrackets = new ArrayList<>(wallBracketRepository.findAll());
        List<WallBracketDto> allWallBracketDto = new ArrayList<>();
        for (int i = 0; i < allWallBrackets.size(); i++) {
            allWallBracketDto.add(transferToWallBracketDto(allWallBrackets.get(i)));
        }
        return allWallBracketDto;
    }

    public WallBracketDto createWallBracket (CreateWallBracketDto createWallBracketDto) {
        WallBracket wallBracket = transferToWallBracket(createWallBracketDto);
        final WallBracket savedWallBracket = wallBracketRepository.save(wallBracket);
        return transferToWallBracketDto(savedWallBracket);
    }

    private WallBracket transferToWallBracket(CreateWallBracketDto createWallBracketDto) {
        final WallBracket newWallBracket = new WallBracket();

        newWallBracket.setSize(createWallBracketDto.getSize());
        newWallBracket.setName(createWallBracketDto.getName());
        newWallBracket.setPrice(createWallBracketDto.getPrice());
        newWallBracket.setAdjustable(createWallBracketDto.isAdjustable());

        return newWallBracket;
    }

    public WallBracketDto transferToWallBracketDto (WallBracket wallBracket) {
        WallBracketDto wallBracketDto = new WallBracketDto();
        wallBracketDto.setId(wallBracket.getId());
        wallBracketDto.setSize(wallBracket.getSize());
        wallBracketDto.setName(wallBracket.getName());
        wallBracketDto.setPrice(wallBracket.getPrice());
        wallBracketDto.setAdjustable(wallBracket.isAdjustable());

        return wallBracketDto;
    }
}
