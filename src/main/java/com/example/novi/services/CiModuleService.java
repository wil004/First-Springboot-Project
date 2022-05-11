package com.example.novi.services;

import com.example.novi.model.CiModule;
import com.example.novi.model.dto.CiModuleDto;
import com.example.novi.model.dto.CreateCiModuleDto;
import com.example.novi.model.dto.RemoteDto;
import com.example.novi.repository.CiModuleRepository;
import com.example.novi.repository.RemoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiModuleService {


    private final CiModuleRepository ciModuleRepository;

    @Autowired
    public CiModuleService(CiModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CiModuleDto> getAllCiModules() {
        List<CiModule> allCiModule = new ArrayList<>(ciModuleRepository.findAll());
        List<CiModuleDto> allCiModuleDto = new ArrayList<>();
        for (int i = 0; i < allCiModule.size(); i++) {
            allCiModuleDto.add(transferToCiModuleDto(allCiModule.get(i)));
        }
        return allCiModuleDto;
    }

    public CiModuleDto createCiModule(CreateCiModuleDto createCiModuleDto) {
        CiModule ciModule = transferToCiModule(createCiModuleDto);
        final CiModule savedCiModule = ciModuleRepository.save(ciModule);
        return transferToCiModuleDto(savedCiModule);
    }

    private CiModule transferToCiModule(CreateCiModuleDto createCiModuleDto) {
        final CiModule newCiModule = new CiModule();
        newCiModule.setName(createCiModuleDto.getName());
        newCiModule.setPrice(createCiModuleDto.getPrice());
        newCiModule.setType(createCiModuleDto.getType());

        return newCiModule;
    }

    public CiModuleDto transferToCiModuleDto(CiModule ciModule) {
        CiModuleDto ciModuleDto = new CiModuleDto();
        ciModuleDto.setId(ciModule.getId());
        ciModuleDto.setName(ciModule.getName());
        ciModuleDto.setPrice(ciModule.getPrice());
        ciModuleDto.setType(ciModule.getType());
        if(ciModule.getTelevision() != null) {
            ciModuleDto.setTelevisionDtoId(ciModule.getTelevision().getId());
        }
        return ciModuleDto;
    }
}
