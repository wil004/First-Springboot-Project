package com.example.novi.model;

import javax.persistence.*;

@Entity
public class TelevisionAndWallBrackets {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Television televisionId;

    @ManyToOne
    private WallBracket wallBracketId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Television getTelevisionId() {
        return televisionId;
    }

    public void setTelevisionId(Television televisionId) {
        this.televisionId = televisionId;
    }

    public WallBracket getWallBracketId() {
        return wallBracketId;
    }

    public void setWallBracketId(WallBracket wallBracketId) {
        this.wallBracketId = wallBracketId;
    }
}
