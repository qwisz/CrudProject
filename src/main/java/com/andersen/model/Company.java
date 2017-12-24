package com.andersen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company implements Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "companies_projects",
            joinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")})
    private Set<Project> projects;

    public Company(String name, Set<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    public Company(Long id, String name, Set<Project> projects) {
        this(name, projects);
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Project project:
                projects) {
            builder.append(project.getId()).append(",");
        }

        String projectsString = builder.substring(0, builder.length() - 1);

        return  id + ";" + name + ";" + projectsString;
    }
}
