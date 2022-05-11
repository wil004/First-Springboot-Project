package com.example.novi.services;

import com.example.novi.model.Remote;
import com.example.novi.model.dto.CreateRemoteDto;
import com.example.novi.model.dto.RemoteDto;

import com.example.novi.repository.RemoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteService {

    private final RemoteRepository remoteRepository;

    @Autowired
    public RemoteService(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public List<RemoteDto> getAllRemotes () {
        List<Remote> allRemote = new ArrayList<>(remoteRepository.findAll());
        List<RemoteDto> allRemoteDto = new ArrayList<>();
        for (int i = 0; i < allRemote.size(); i++) {
            allRemoteDto.add(transferToRemoteDto(allRemote.get(i)));
        }
        return allRemoteDto;
    }

    public RemoteDto createRemote (CreateRemoteDto createRemoteDto) {
            Remote remote = transferToRemote(createRemoteDto);
            final Remote savedRemote = remoteRepository.save(remote);
            return transferToRemoteDto(savedRemote);
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
        remoteDto.setId(remote.getId());
        remoteDto.setCompatibleWith(remote.getCompatibleWith());
        remoteDto.setBatteryType(remote.getBatteryType());
        remoteDto.setName(remote.getName());
        remoteDto.setBrand(remote.getBrand());
        remoteDto.setPrice(remote.getPrice());
        remoteDto.setOriginalStock(remote.getOriginalStock());
        return remoteDto;
    }

}
