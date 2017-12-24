package com.andersen.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "skills")
public class Skill implements Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Skill() {

    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return  id + ";" + name;
    }
}
