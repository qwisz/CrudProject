package com.andersen.model;

import java.math.BigDecimal;
import java.util.Set;

public class Developer implements Identifier {

    private Long id;
    private String firstName;
    private String lastName;
    private String speciality;
    private Set<Skill> skills;
    private BigDecimal salary;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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
