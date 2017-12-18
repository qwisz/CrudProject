package com.andersen.dao;

import com.andersen.DBWorker;
import com.andersen.model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillDAO implements CrudDAO {

    private static final String INSERT_SKILL = "INSERT INTO skills (name) VALUES(?)";
    private static final String SELECT_SKILL = "SELECT * FROM skills WHERE id = ?";
    private static final String UPDATE_SKILL = "UPDATE skills SET name=? WHERE id = ?";
    private static final String EXISTS = "SELECT EXISTS(SELECT id FROM skills WHERE id = ?)";
    private static final String DELETE_SKILL = "DELETE FROM skills WHERE id = ?";
//    private static final String DELETE_HELP_TABLE = "DELETE FROM developers_skills WHERE skill_id = ?";
    private Connection connection = DBWorker.getConnection();

    public boolean save(Skill entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_SKILL);
            statement.setString(1, entity.getName());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String read(Long id) {
        Skill skill = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_SKILL);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                skill = new Skill(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (skill == null) {
            return "Skill with such id doesn't exist";
        }
        return skill.toString();
    }

    public boolean update(Long id, Skill entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SKILL);
            statement.setString(1, entity.getName());
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(Long id) {
        delete(id, DELETE_SKILL);
    }

    public boolean isExist(Long id) {
        return isExist(id, EXISTS);
    }

    public Skill getById(Long id) {
        Skill skill = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_SKILL);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                skill = new Skill(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skill;
    }
}
