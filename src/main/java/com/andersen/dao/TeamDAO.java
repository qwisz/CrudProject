package com.andersen.dao;

import com.andersen.DBWorker;
import com.andersen.model.Developer;
import com.andersen.model.Skill;
import com.andersen.model.Team;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TeamDAO implements CrudDAO {

    private static final String INSERT = "INSERT INTO teams (name) VALUES(?)";
    private static final String INSERT_TD = "INSERT INTO teams_developers (team_id, developer_id) VALUES(?, ?)";
    private static final String SELECT = "SELECT * FROM teams WHERE id = ?";
    private static final String SELECT_TD = "SELECT * FROM teams_developers WHERE team_id = ?";
    private static final String UPDATE = "UPDATE teams SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM teams WHERE id = ?";
    private static final String EXISTS = "SELECT EXISTS(SELECT id FROM teams WHERE id = ?)";
    private Connection connection = DBWorker.getConnection();
    private DeveloperDAO developerDAO = new DeveloperDAO();


    public boolean save(Team entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            PreparedStatement statementTD = connection.prepareStatement(INSERT_TD);

            statement.setString(1, entity.getName());
            statement.execute();

            String query = "select * from teams where name like ?";
            PreparedStatement helpStatement = connection.prepareStatement(query);
            helpStatement.setString(1, entity.getName());
            ResultSet resultSet = helpStatement.executeQuery();
            Long id = null;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }

            Set<Developer> devs = entity.getDevelopers();
            if (id != null) {
                for (Developer dev : devs) {
                    statementTD.setLong(1, id);
                    statementTD.setLong(2, dev.getId());
                    statementTD.execute();
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String read(Long id) {
        Team team = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            while (rs.next()) {
                name = rs.getString("name");
            }

            PreparedStatement statementTD = connection.prepareStatement(SELECT_TD);
            statementTD.setLong(1, id);
            ResultSet resultSet = statementTD.executeQuery();
            Set<Developer> devs = new HashSet<>();
            while (resultSet.next()) {
                Long devId = resultSet.getLong("developer_id");
                String query = "select * from developers where id = " + devId;
                Statement st = connection.createStatement();
                ResultSet devsSet = st.executeQuery(query);
                while (devsSet.next()) {
                    devs.add(new Developer(devId, devsSet.getString("first_name"), devsSet.getString("last_name"),
                            devsSet.getString("speciality"), new HashSet<Skill>(), devsSet.getBigDecimal("salary")));
                }
            }

            team = new Team(id, name, devs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (team == null) {
            return "Team with such id doesn't exist";
        }
        return team.toString();
    }

    public boolean update(Long id, Team entity) {
        try {
            PreparedStatement statementT = connection.prepareStatement(UPDATE);
            statementT.setString(1, entity.getName());
            statementT.setLong(2, id);

            String queryDel = "delete from teams_developers where team_id = " + id;
            Statement statementDSDel = connection.createStatement();
            statementDSDel.execute(queryDel);

            PreparedStatement statementTDIns = connection.prepareStatement(INSERT_TD);
            Set<Developer> devs = entity.getDevelopers();
            for (Developer dev: devs) {
                statementTDIns.setLong(1, id);
                statementTDIns.setLong(2, dev.getId());
                statementTDIns.execute();
            }
            return statementT.executeUpdate() > 0;
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

    public Team getById(Long id) {
        Team team = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            while (rs.next()) {
                name = rs.getString("name");
            }

            PreparedStatement statementTD = connection.prepareStatement(SELECT_TD);
            statementTD.setLong(1, id);
            ResultSet resultSet = statementTD.executeQuery();
            Set<Developer> devs = new HashSet<>();
            while (resultSet.next()) {
                Long devId = resultSet.getLong("developer_id");
                Developer developer = developerDAO.getById(devId);
                devs.add(developer);
            }

            team = new Team(id, name, devs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }
}
