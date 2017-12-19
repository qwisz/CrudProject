package com.andersen.idao;

import com.andersen.model.Project;

public interface IProjectDAO extends DAO<Project> {

    boolean save(Project project);

    boolean update(Long id, Project newProject);
}
