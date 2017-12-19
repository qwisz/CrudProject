package com.andersen.controller;

import com.andersen.jdbc.ProjectDAO;
import com.andersen.jdbc.TeamDAO;
import com.andersen.model.Project;
import com.andersen.model.Team;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ProjectController {

    private ProjectDAO projectDAO = new ProjectDAO();
    private TeamDAO teamDAO = new TeamDAO();

    public boolean create(String name, Set<Long> ids) throws IOException {
        Set<Team> teams = new HashSet<>();

        for (Long id : ids) {
            if (!teamDAO.isExist(id))
                return false;
            teams.add(teamDAO.getById(id));
        }

        projectDAO.save(new Project(name, teams));

        return true;
    }

    public String read(Long id) throws IOException {
        return projectDAO.read(id);
    }

    public boolean update(Long id, String name, Set<Long> ids) throws IOException {

        Set<Team> teams = new HashSet<>();

        for (Long idd : ids) {
            if (!teamDAO.isExist(idd))
                return false;
            teams.add(teamDAO.getById(idd));
        }

        return projectDAO.update(id, new Project(name, teams));
    }

    public void delete(Long id) throws IOException {
        projectDAO.delete(id);
    }

    public boolean isExist(Long id) throws IOException {
        return projectDAO.isExist(id);
    }
}
