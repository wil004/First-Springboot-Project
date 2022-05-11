package com.example.novi.services;

import com.example.novi.controller.exceptions.RecordNotFoundException;
import com.example.novi.model.CiModule;
import com.example.novi.model.Remote;
import com.example.novi.model.Television;
import com.example.novi.model.dto.CiModuleDto;
import com.example.novi.model.dto.TelevisionDto;
import com.example.novi.model.dto.TelevisionInputDto;
import com.example.novi.model.dto.WallBracketDto;
import com.example.novi.repository.CiModuleRepository;
import com.example.novi.repository.RemoteRepository;
import com.example.novi.repository.TelevisionRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteRepository remoteRepository;
    private final RemoteService remoteService;
    private final CiModuleRepository ciModuleRepository;
    private final CiModuleService ciModuleService;


    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, RemoteRepository remoteRepository, RemoteService remoteService, CiModuleRepository ciModuleRepository, CiModuleService ciModuleService) {
        this.televisionRepository = televisionRepository;
        this.remoteService = remoteService;
        this.remoteRepository = remoteRepository;
        this.ciModuleRepository = ciModuleRepository;
        this.ciModuleService = ciModuleService;
    }


    public List<TelevisionDto> getAllTelevisions() {
        List<Television> allTelevision = new ArrayList<>(televisionRepository.findAll());
        List<TelevisionDto> allTelevisionDto = new ArrayList<>();
        for (int i = 0; i < allTelevision.size(); i++) {
            allTelevisionDto.add(transferToTelevisionDto(allTelevision.get(i)));
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
        televisionDto.setId(television.getId());
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
        if (television.getRemote() != null) {
            televisionDto.setRemoteDto(remoteService.transferToRemoteDto(television.getRemote()));
        }
        if(television.getCiModule() != null) {
            List<CiModuleDto> ciModuleDtoList = new ArrayList<>();
            for(int i = 0; i < television.getCiModule().size(); i++) {
                ciModuleDtoList.add((ciModuleService.transferToCiModuleDto(television.getCiModule().get(i))));
            }
            televisionDto.setCiModule(ciModuleDtoList);
        }
        return televisionDto;
    }

    public TelevisionDto addRemoteToTelevision(Long televisionId, Long remoteId) {
        Remote remote = remoteRepository.findById(remoteId).orElseThrow();

        Television television = televisionRepository.findById(televisionId).orElseThrow();
        if(television.getRemote() == null) {
            if(remote.getTelevision() == null) {
                television.setRemote(remote);
                remote.setTelevision(television);
            } else {
                throw new RecordNotFoundException("Remote is already connected to a television!");
            }
        }
        else {
            throw new RecordNotFoundException("Television is already connected to a remote!");
        }

        remoteRepository.save(remote);
        final Television savedTelevision = televisionRepository.save(television);

        return transferToTelevisionDto(savedTelevision);
    }

    public TelevisionDto addCiModuleToTelevision(Long televisionId, Long ciModuleId) {
        CiModule ciModule = ciModuleRepository.findById(ciModuleId).orElseThrow();

        Television television = televisionRepository.findById(televisionId).orElseThrow();

        if(ciModule.getTelevision() == null) {
            List<CiModule> ciModuleList = new ArrayList<>(television.getCiModule());
            ciModuleList.add(ciModule);
            television.setCiModule(ciModuleList);
            ciModule.setTelevision(television);
        } else {
            throw new RecordNotFoundException("CiModule is already connected to another television!");
        }

        ciModuleRepository.save(ciModule);
        final Television savedTelevision = televisionRepository.save(television);

        return transferToTelevisionDto(savedTelevision);
    }


}
