package com.andersen.controller;

import com.andersen.jdbc.DeveloperDAO;
import com.andersen.jdbc.TeamDAO;
import com.andersen.model.Developer;
import com.andersen.model.Team;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TeamController {

    private TeamDAO teamDAO = new TeamDAO();
    private DeveloperDAO developerDAO = new DeveloperDAO();

    public boolean create(String name, Set<Long> ids) throws IOException {

        Set<Developer> developers = new HashSet<>();

        for (Long id : ids) {
            if (!developerDAO.isExist(id))
                return false;
            developers.add(developerDAO.getById(id));
        }

        teamDAO.save(new Team(name, developers));

        return true;
    }

    public String read(Long id) throws IOException {

        return teamDAO.read(id);
    }

    public boolean update(Long id, String name, Set<Long> ids) throws IOException {

        Set<Developer> developers = new HashSet<>();

        for (Long idd : ids) {
            if (!developerDAO.isExist(idd))
                return false;
            developers.add(developerDAO.getById(idd));
        }

        return teamDAO.update(id, new Team(name, developers));
    }

    public void delete(Long id) throws IOException {
        teamDAO.delete(id);
    }

    public boolean isExist(Long id) throws IOException {
        return teamDAO.isExist(id);
    }
//
//    private Set<Developer> getDevelopersByIds(Set<Long> ids) throws IOException {
//        Set<Developer> developers = new HashSet<>();
//
//        for (Long id : ids) {
//            developers.add(developerDAO.getById(id));
//        }
//
//        return developers;
//    }
}
