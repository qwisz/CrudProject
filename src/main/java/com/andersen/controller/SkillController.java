package com.andersen.controller;

import com.andersen.jdbc.SkillDAO;
import com.andersen.model.Skill;

import java.io.IOException;

public class SkillController {

    private SkillDAO dao = new SkillDAO();

    public boolean create(String name) throws IOException {
        return !dao.isExist(name) && dao.save(new Skill(name));
    }

    public String read(Long id) throws IOException {
        return dao.read(id);
    }

    public boolean update(Long id, String name) throws IOException {
        return !dao.isExist(name) && dao.update(id, new Skill(name));
    }

    public void delete(Long id) throws IOException {
        dao.delete(id);
    }

    public boolean isExist(Long id) throws IOException {
        return dao.isExist(id);
    }
}
