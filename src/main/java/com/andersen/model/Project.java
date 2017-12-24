package com.andersen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project implements Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "projects_teams",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")})
    private Set<Team> teams;

    public Project(String name, Set<Team> teams) {
        this.name = name;
        this.teams = teams;
    }

    public Project(Long id, String name, Set<Team> teams) {
        this(name, teams);
        this.id = id;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        for (Team team:
                teams) {
            builder.append(team.getId()).append(",");
        }

        String teamsString = builder.substring(0, builder.length() - 1);

        return  id + ";" + name + ";" + teamsString;
    }
}
