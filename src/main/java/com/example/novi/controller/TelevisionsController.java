package com.example.novi.controller;

import com.example.novi.controller.exceptions.RecordNotFoundException;
import com.example.novi.domain.Television;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class TelevisionsController {
    final Television samsung = new Television("Samsung");
    final Television philips = new Television("Philips");
    final Television sony = new Television("Sony");
    List<Television> allTelevision = List.of(samsung, philips, sony);

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> printTelevision() {

        return ResponseEntity.ok(allTelevision);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable String id) {
        for (int i = 0; i < allTelevision.size(); i++) {
            if (allTelevision.get(i).getTelevision().equalsIgnoreCase(id)) {
                return ResponseEntity.ok(allTelevision.get(i));
            }
        }
        throw new RecordNotFoundException("Television not found!");
    }

    @PostMapping(value = "/television",
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Television> create(@RequestBody Television television) {
        System.out.println("Creating" + television.getTelevision());
        return ResponseEntity.created(null).build();
    }

    @PutMapping(value = "/televisions/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Television> updateTelevision(@PathVariable String id, @RequestBody String updateTelevision) {
        for (int i = 0; i < allTelevision.size(); i++) {
            if (allTelevision.get(i).getTelevision().equalsIgnoreCase(id)) {
                allTelevision.get(i).setTelevision(updateTelevision);
                System.out.println(id + " updated to " + allTelevision.get(i).getTelevision());
                return ResponseEntity.noContent().build();
            }
        }
        throw new RecordNotFoundException("Object not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable String id) {
        int deleteThisObject = -1;
        for (int i = 0; i < allTelevision.size(); i++) {
            if (allTelevision.get(i).getTelevision().equalsIgnoreCase(id)) {
                System.out.println(allTelevision.get(i).getTelevision() + " has been removed from the list!");
                allTelevision.get(i).setTelevision("DELETED!");
                deleteThisObject = i;
            }
        } if (deleteThisObject >= 0) {
            allTelevision.remove(deleteThisObject);
            // Gives an internal server error in postman but still deletes the object!
            // Send twice to check if object is deleted!.
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("You can't delete a television that doesn't exist");
        }
        }


}
