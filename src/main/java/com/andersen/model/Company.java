package com.andersen.model;

import java.util.Set;

public class Company implements Identifier {

    private Long id;
    private String name;
    private Set<Project> projects;

    public Company(String name, Set<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    public Company(Long id, String name, Set<Project> projects) {
        this(name, projects);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
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
