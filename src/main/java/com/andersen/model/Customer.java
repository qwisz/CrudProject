package com.andersen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_projects",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")})
    private Set<Project> projects;

    public Customer(String firstName, String lastName, String address, Set<Project> projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.projects = projects;
    }

    public Customer(Long id, String firstName, String lastName, String address, Set<Project> projects) {
        this(firstName, lastName, address, projects);
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

        return  id + ";" + firstName  + ";" + lastName + ";" + address + ";" + projectsString;
    }
}
