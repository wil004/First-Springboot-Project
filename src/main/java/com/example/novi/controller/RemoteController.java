package com.example.novi.controller;

import com.example.novi.model.Remote;
import com.example.novi.model.dto.CreateRemoteDto;
import com.example.novi.model.dto.RemoteDto;
import com.example.novi.model.dto.TelevisionDto;
import com.example.novi.services.RemoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/remote")
public class RemoteController {
    private RemoteService remoteService;

    public RemoteController(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    @GetMapping()
    public ResponseEntity<List<RemoteDto>> printAllRemotes() {
        return ResponseEntity.ok(remoteService.getAllRemotes());
    }

    @PostMapping
    public ResponseEntity<RemoteDto>
    createRemote(@RequestBody CreateRemoteDto createRemoteDto) {

        final RemoteDto remoteDto = remoteService.createRemote(createRemoteDto);
        final URI location = URI.create("/remote" + remoteDto.getId());
        return ResponseEntity.created(location).body(remoteDto);
    }
}
