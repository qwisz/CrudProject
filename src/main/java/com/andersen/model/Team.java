package com.andersen.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.Set;

@Data
@Entity
@Table(name = "teams")
public class Team implements Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teams_developers",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "developer_id", referencedColumnName = "id")})
    private Set<Developer> developers;

    public Team() {

    }

    public Team(String name, Set<Developer> developers) {
        this.name = name;
        this.developers = developers;
    }

    public Team(Long id, String name, Set<Developer> developers) {
        this(name, developers);
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Developer dev:
                developers) {
            builder.append(dev.getId()).append(",");
        }

        String developersString = builder.substring(0, builder.length() - 1);

        return  id + ";" + name + ";" + developersString;
    }
}