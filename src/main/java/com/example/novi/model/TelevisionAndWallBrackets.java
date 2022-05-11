package com.example.novi.model;

import javax.persistence.*;

@Entity
public class TelevisionAndWallBrackets {
    @Id
    @GeneratedValue
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
