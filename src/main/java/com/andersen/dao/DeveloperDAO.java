package com.andersen.dao;

import com.andersen.DBWorker;
import com.andersen.model.Developer;
import com.andersen.model.Skill;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DeveloperDAO implements CrudDAO {
    private static final String INSERT = "INSERT INTO developers (first_name, last_name, speciality, salary) VALUES(?, ?, ?, ?)";
    private static final String INSERT_DS = "INSERT INTO developers_skills (developer_id, skill_id) VALUES(?, ?)";
    private static final String SELECT = "SELECT * FROM developers WHERE id = ?";
    private static final String SELECT_DS = "SELECT * FROM developers_skills WHERE developer_id = ?";
    private static final String UPDATE = "UPDATE developers SET first_name = ?, last_name = ?, speciality = ?, salary = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM developers WHERE id = ?";
    private static final String EXISTS = "SELECT EXISTS(SELECT id FROM developers WHERE id = ?)";
//    private static final String DELETE_HELP_TABLE = "DELETE FROM developers_skills WHERE developer_id = ?";
//    private static final String DELETE_SEC_HELP_TABLE = "DELETE FROM teams_developers WHERE developer_id = ?";
    private Connection connection = DBWorker.getConnection();
    private SkillDAO skillDAO = new SkillDAO();


    public boolean save(Developer entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            PreparedStatement statementDS = connection.prepareStatement(INSERT_DS);

            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getSpeciality());
            statement.setBigDecimal(4, entity.getSalary());
            statement.execute();

            String query = "select * from developers where (first_name like ?) and (last_name like ?) and (speciality like ?) and (salary=?)";
            PreparedStatement helpStatement = connection.prepareStatement(query);
            helpStatement.setString(1, entity.getFirstName());
            helpStatement.setString(2, entity.getLastName());
            helpStatement.setString(3, entity.getSpeciality());
            helpStatement.setBigDecimal(4, entity.getSalary());
            ResultSet resultSet = helpStatement.executeQuery();
            Long id = null;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }

            Set<Skill> skills = entity.getSkills();
            if (id != null) {
                for (Skill skill : skills) {
                    statementDS.setLong(1, id);
                    statementDS.setLong(2, skill.getId());
                    statementDS.execute();
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String read(Long id) {
        Developer dev = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String firstName = null;
            String lastName = null;
            String speciality = null;
            BigDecimal salary = null;
            while (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                speciality = rs.getString("speciality");
                salary = rs.getBigDecimal("salary");
            }

            PreparedStatement statementDS = connection.prepareStatement(SELECT_DS);
            statementDS.setLong(1, id);
            ResultSet resultSet = statementDS.executeQuery();
            Set<Skill> skills = new HashSet<>();
            while (resultSet.next()) {
                Long skillId = resultSet.getLong("skill_id");
                String query = "select * from skills where id = " + skillId;
                Statement st = connection.createStatement();
                ResultSet skillsSet = st.executeQuery(query);
                while (skillsSet.next()) {
                    skills.add(new Skill(skillId, skillsSet.getString("name")));
                }
            }

            dev = new Developer(id, firstName, lastName, speciality, skills, salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (dev == null) {
            return "Dev with such id doesn't exist";
        }
        return dev.toString();
    }

    public boolean update(Long id, Developer developer) {
        try {
            PreparedStatement statementDev = connection.prepareStatement(UPDATE);
            statementDev.setString(1, developer.getFirstName());
            statementDev.setString(2, developer.getLastName());
            statementDev.setString(3, developer.getSpeciality());
            statementDev.setBigDecimal(4, developer.getSalary());
            statementDev.setLong(5, id);
            int res = statementDev.executeUpdate();

            String queryDel = "delete from developers_skills where developer_id = " + id;
            Statement statementDSDel = connection.createStatement();
            statementDSDel.execute(queryDel);

            PreparedStatement statementDSIns = connection.prepareStatement(INSERT_DS);
            Set<Skill> skills = developer.getSkills();
            for (Skill skill : skills) {
                statementDSIns.setLong(1, id);
                statementDSIns.setLong(2, skill.getId());
                statementDSIns.execute();
            }
            return res > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(Long id) {

        delete(id, DELETE);
    }

    public boolean isExist(Long id) {
        return isExist(id, EXISTS);
    }

    public Developer getById(Long id) {
        Developer dev = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String firstName = null;
            String lastName = null;
            String speciality = null;
            BigDecimal salary = null;
            while (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                speciality = rs.getString("speciality");
                salary = rs.getBigDecimal("salary");
            }

            PreparedStatement statementDS = connection.prepareStatement(SELECT_DS);
            statementDS.setLong(1, id);
            ResultSet resultSet = statementDS.executeQuery();
            Set<Skill> skills = new HashSet<>();
            while (resultSet.next()) {
                Long skillId = resultSet.getLong("skill_id");
                Skill skill = skillDAO.getById(skillId);
                skills.add(skill);
            }

            dev = new Developer(id, firstName, lastName, speciality, skills, salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dev;
    }
}
