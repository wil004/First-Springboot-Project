package com.example.novi.services;

import com.example.novi.controller.exceptions.RecordNotFoundException;
import com.example.novi.model.Remote;
import com.example.novi.model.Television;
import com.example.novi.model.dto.CreateRemoteDto;
import com.example.novi.model.dto.RemoteDto;
import com.example.novi.model.dto.TelevisionDto;
import com.example.novi.repository.RemoteRepository;
import com.example.novi.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteService {

    private final RemoteRepository remoteRepository;
    private final TelevisionRepository televisionRepository;
    private final TelevisionService televisionService;

    @Autowired
    public RemoteService(RemoteRepository remoteRepository, TelevisionRepository televisionRepository, TelevisionService televisionService) {
        this.remoteRepository = remoteRepository;
        this.televisionRepository = televisionRepository;
        this.televisionService = televisionService;
    }

    public List<RemoteDto> getAllRemotes () {
        List<Remote> allRemote = new ArrayList<>(remoteRepository.findAll());
        List<RemoteDto> allRemoteDto = new ArrayList<>();
        for (int i = 0; i < allRemote.size(); i++) {
            allRemoteDto.add(transferToRemoteDto(allRemote.get(i)));
            if (allRemote.get(i).getTelevisionWithRemote() != null) {
               allRemoteDto.get(i).setTelevisionDto(televisionService.transferToTelevisionDto(allRemote.get(i).getTelevisionWithRemote()));
            }
        }
        return allRemoteDto;
    }

    public RemoteDto createRemote (CreateRemoteDto createRemoteDto) {
        Television television = televisionRepository.findById(createRemoteDto.getTelevisionId())
                .orElseThrow();
        if(television.getRemote() == null) {
            Remote remote = transferToRemote(createRemoteDto);
            remote.setTelevisionWithRemote(television);
            final Remote savedRemote = remoteRepository.save(remote);

            televisionService.transferToTelevisionDto(television);

            RemoteDto remoteDto = transferToRemoteDto(savedRemote);
            remoteDto.setTelevisionDto(televisionService.transferToTelevisionDto(savedRemote.getTelevisionWithRemote()));
            return remoteDto;
        }  else {
            throw new RecordNotFoundException("There is already a remote connected to this television");
        }
    }

    private Remote transferToRemote(CreateRemoteDto createRemoteDto) {
        final Remote newRemote = new Remote();
        newRemote.setCompatibleWith(createRemoteDto.getCompatibleWith());
        newRemote.setBatteryType(createRemoteDto.getBatteryType());
        newRemote.setName(createRemoteDto.getName());
        newRemote.setBrand(createRemoteDto.getBrand());
        newRemote.setPrice(createRemoteDto.getPrice());
        newRemote.setOriginalStock(createRemoteDto.getOriginalStock());

        return newRemote;
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
