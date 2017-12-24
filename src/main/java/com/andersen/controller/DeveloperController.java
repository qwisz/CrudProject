package com.andersen.controller;

import com.andersen.hibernate.DeveloperDAOImpl;
import com.andersen.hibernate.SkillDAOImpl;
import com.andersen.jdbc.DeveloperDAO;
import com.andersen.jdbc.SkillDAO;
import com.andersen.model.Developer;
import com.andersen.model.Skill;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DeveloperController {

    private DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
    private SkillDAOImpl skillDAO = new SkillDAOImpl();

    public boolean create(String firstName, String lastName, String speciality,
                          Set<Long> ids, BigDecimal salary) throws IOException {

        Set<Skill> skills = new HashSet<>();

        for (Long id : ids) {
            if (!skillDAO.isExist(id))
                return false;
            skills.add(skillDAO.getById(id));
        }

        developerDAO.save(new Developer(firstName, lastName, speciality, skills, salary));

        return true;
    }

    public String read(Long id) throws IOException {

        return developerDAO.read(id);
    }

    public boolean isExist(Long id) throws IOException {
        return developerDAO.isExist(id);
    }

    public boolean update(Long id, String firstName, String lastName, String speciality,
                          Set<Long> ids, BigDecimal salary) throws IOException {

        Set<Skill> skills = new HashSet<>();

        for (Long idd : ids) {
            if (!skillDAO.isExist(idd))
                return false;
            skills.add(skillDAO.getById(idd));
        }

        Developer developer = new Developer(firstName, lastName, speciality, skills, salary);
        return developerDAO.update(id, developer);
    }

    public void delete(Long id) throws IOException {
        developerDAO.delete(id);
    }
}
