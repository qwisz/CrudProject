package com.andersen.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "developers")
public class Developer implements Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "speciality", nullable = false)
    private String speciality;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")})
    private Set<Skill> skills;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    public Developer() {

    }

    public Developer(String firstName, String lastName, String speciality, Set<Skill> skills, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.skills = skills;
        this.salary = salary;
    }

    public Developer(Long id, String firstName, String lastName, String speciality, Set<Skill> skills, BigDecimal salary) {
        this(firstName, lastName, speciality, skills, salary);
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Skill skill :
                skills) {
            builder.append(skill.getId()).append(",");
        }

        String skillsString = builder.substring(0, builder.length() - 1);

        return  id + ";" + firstName  + ";" + lastName + ";" + speciality + ";" + salary + ";" + skillsString;
    }
}
