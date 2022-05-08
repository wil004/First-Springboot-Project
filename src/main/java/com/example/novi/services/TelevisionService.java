package com.example.novi.services;

import com.example.novi.controller.exceptions.RecordNotFoundException;
import com.example.novi.model.Remote;
import com.example.novi.model.Television;
import com.example.novi.model.dto.RemoteDto;
import com.example.novi.model.dto.TelevisionDto;
import com.example.novi.model.dto.TelevisionInputDto;
import com.example.novi.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            allTelevisionDto.add(transferToTelevisionDto(allTelevision.get(i)));
            if (allTelevision.get(i).getRemote() != null) {
                allTelevisionDto.get(i).setRemoteDto(transferToRemoteDto(allTelevision.get(i).getRemote()));
            }
        }
        return allTelevisionDto;
    }

    public TelevisionDto getTelevisionById(Long id) {
        if (televisionRepository.findById(id).isPresent()) {
            Television television = televisionRepository.findById(id).get();
                return transferToTelevisionDto(television);
        } else {
            throw new RecordNotFoundException("Television not found!");
        }
        }

    public TelevisionDto createTelevision(TelevisionInputDto televisionInputDto) {
        Television tv = transferToTelevision(televisionInputDto);
        final Television savedTelevision = televisionRepository.save(tv);

        return transferToTelevisionDto(savedTelevision);
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

        public Television transferToTelevision(TelevisionInputDto televisionInputDto) {
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
            return television;
    }

    public TelevisionDto transferToTelevisionDto(Television television) {
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
    }

    public RemoteDto transferToRemoteDto (Remote remote) {
        RemoteDto remoteDto = new RemoteDto();
        remoteDto.setCompatibleWith(remote.getCompatibleWith());
        remoteDto.setBatteryType(remote.getBatteryType());
        remoteDto.setName(remote.getName());
        remoteDto.setBrand(remote.getBrand());
        remoteDto.setPrice(remote.getPrice());
        remoteDto.setOriginalStock(remote.getOriginalStock());
        return remoteDto;
    }

}
