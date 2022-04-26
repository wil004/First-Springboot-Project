package com.example.novi.services;

import com.example.novi.controller.exceptions.RecordNotFoundException;
import com.example.novi.domain.Television;
import com.example.novi.domain.dto.TelevisionDto;
import com.example.novi.domain.dto.TelevisionInputDto;
import com.example.novi.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


    public List<TelevisionDto> getAllTelevisions() {
        List<Television> allTelevision = new ArrayList<>(televisionRepository.findAll());
        List<TelevisionDto> allTelevisionDto = new ArrayList<>();
        for (int i = 0; i < allTelevision.size(); i++) {
           TelevisionDto televisionDto = new TelevisionDto();
            televisionDto.setName(allTelevision.get(i).getName());
            televisionDto.setPrice(allTelevision.get(i).getPrice());
            televisionDto.setBrand(allTelevision.get(i).getBrand());
            televisionDto.setType(allTelevision.get(i).getType());
            televisionDto.setAvailableSize(allTelevision.get(i).getAvailableSize());
            televisionDto.setReshRate(allTelevision.get(i).getReshRate());
            televisionDto.setScreenType(allTelevision.get(i).getScreenType());
            televisionDto.setScreenQuality(allTelevision.get(i).getScreenQuality());
            televisionDto.setSmartTv(allTelevision.get(i).isSmartTv());
            televisionDto.setHdr(allTelevision.get(i).isHdr());
            televisionDto.setWifi(allTelevision.get(i).isWifi());
            televisionDto.setBluetooth(allTelevision.get(i).isBluetooth());
            televisionDto.setAmbiLight(allTelevision.get(i).isAmbiLight());
            allTelevisionDto.add(televisionDto);
        }
        return allTelevisionDto;
    }

    public TelevisionDto getTelevisionById(Long id) {
        if (televisionRepository.findById(id).isPresent()) {
            Television television = televisionRepository.findById(id).get();
            TelevisionDto televisionDto = new TelevisionDto();
            televisionDto.setName(television.getName());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setType(television.getType());
            televisionDto.setAvailableSize(television.getAvailableSize());
            televisionDto.setReshRate(television.getReshRate());
            televisionDto.setScreenType(television.getScreenType());
            televisionDto.setScreenQuality(television.getScreenQuality());
            televisionDto.setSmartTv(television.isSmartTv());
            televisionDto.setHdr(television.isHdr());
            televisionDto.setWifi(television.isWifi());
            televisionDto.setBluetooth(television.isBluetooth());
            televisionDto.setAmbiLight(television.isAmbiLight());
            return televisionDto;
        } else {
            throw new RecordNotFoundException("Television not found!");
        }
        }

    public TelevisionDto createTelevision(TelevisionInputDto televisionInputDto) {

        Television television = new Television();

        television.setName(televisionInputDto.getName());
        television.setPrice(televisionInputDto.getPrice());
        television.setBrand(televisionInputDto.getBrand());
        television.setType(televisionInputDto.getType());
        television.setAvailableSize(televisionInputDto.getAvailableSize());
        television.setReshRate(televisionInputDto.getReshRate());
        television.setScreenType(televisionInputDto.getScreenType());
        television.setScreenQuality(televisionInputDto.getScreenQuality());
        television.setSmartTv(televisionInputDto.isSmartTv());
        television.setHdr(televisionInputDto.isHdr());
        television.setWifi(televisionInputDto.isWifi());
        television.setBluetooth(televisionInputDto.isBluetooth());
        television.setAmbiLight(televisionInputDto.isAmbiLight());

        final Television savedTelevision = televisionRepository.save(television);

        TelevisionDto televisionDto = new TelevisionDto();
        televisionDto.setId(savedTelevision.getId());
        televisionDto.setName(savedTelevision.getName());
        televisionDto.setPrice(savedTelevision.getPrice());
        televisionDto.setBrand(savedTelevision.getBrand());
        televisionDto.setType(savedTelevision.getType());
        televisionDto.setAvailableSize(savedTelevision.getAvailableSize());
        televisionDto.setReshRate(savedTelevision.getReshRate());
        televisionDto.setScreenType(savedTelevision.getScreenType());
        televisionDto.setScreenQuality(savedTelevision.getScreenQuality());
        televisionDto.setSmartTv(savedTelevision.isSmartTv());
        televisionDto.setHdr(savedTelevision.isHdr());
        televisionDto.setWifi(savedTelevision.isWifi());
        televisionDto.setBluetooth(savedTelevision.isBluetooth());
        televisionDto.setAmbiLight(savedTelevision.isAmbiLight());

        return televisionDto;
    }

    public TelevisionDto changeTelevision(Long id, String type, TelevisionInputDto televisionInputDto) {
        TelevisionDto televisionDto = new TelevisionDto();
        if (televisionRepository.findById(id).isPresent()) {
            Television chosenTelevision = televisionRepository.findById(id).get();
            if(type.equalsIgnoreCase("name")) {
                chosenTelevision.setName(televisionInputDto.getName());
                Television savedTelevision = televisionRepository.save(chosenTelevision);
                televisionDto.setName(savedTelevision.getName());
            } else if (type.equalsIgnoreCase("price")) {
                chosenTelevision.setPrice(televisionInputDto.getPrice());
                Television savedTelevision = televisionRepository.save(chosenTelevision);
                televisionDto.setPrice(savedTelevision.getPrice());
            } else if (type.equalsIgnoreCase("originalStock")) {
                chosenTelevision.setOriginalStock(televisionInputDto.getOriginalStock());
                Television savedTelevision = televisionRepository.save(chosenTelevision);
                televisionDto.setOriginalStock(savedTelevision.getOriginalStock());
            } else if (type.equalsIgnoreCase("sold")) {
                chosenTelevision.setSold(televisionInputDto.getSold());
                Television savedTelevision = televisionRepository.save(chosenTelevision);
                televisionDto.setSold(savedTelevision.getSold());
            } else {
                throw new RecordNotFoundException("Type to change not found");
            }
        } else {
            throw new RecordNotFoundException("Object not found");
        }
        return televisionDto;
        }

        public void deleteTelevision(Long id) {
        if (televisionRepository.findById(id).isPresent()) {
            televisionRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("You cannot delete a television that does not exists");
        }
        }
}
