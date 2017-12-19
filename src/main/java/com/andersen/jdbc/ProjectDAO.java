package com.andersen.jdbc;

import com.andersen.DBWorker;
import com.andersen.idao.IProjectDAO;
import com.andersen.model.Developer;
import com.andersen.model.Project;
import com.andersen.model.Team;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ProjectDAO implements IProjectDAO {

    private static final String INSERT = "INSERT INTO projects (name) VALUES(?)";
    private static final String INSERT_PT = "INSERT INTO projects_teams (project_id, team_id) VALUES(?, ?)";
    private static final String SELECT = "SELECT * FROM projects WHERE id = ?";
    private static final String SELECT_PT = "SELECT * FROM projects_teams WHERE project_id = ?";
    private static final String UPDATE = "UPDATE projects SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";
    private static final String EXISTS = "SELECT EXISTS(SELECT id FROM projects WHERE id = ?)";
    private static final String EXISTS_BY_ARGS = "SELECT EXISTS(SELECT id FROM projects WHERE name = ?)";
    private Connection connection = DBWorker.getConnection();
    private TeamDAO teamDAO = new TeamDAO();

    public boolean save(Project entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            PreparedStatement statementTD = connection.prepareStatement(INSERT_PT);

            statement.setString(1, entity.getName());
            statement.execute();

            String query = "SELECT * FROM projects WHERE name LIKE ?";
            PreparedStatement helpStatement = connection.prepareStatement(query);
            helpStatement.setString(1, entity.getName());
            ResultSet resultSet = helpStatement.executeQuery();
            Long id = null;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }

            Set<Team> teams = entity.getTeams();
            if (id != null) {
                for (Team team : teams) {
                    statementTD.setLong(1, id);
                    statementTD.setLong(2, team.getId());
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
        Project project = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            while (rs.next()) {
                name = rs.getString("name");
            }

            PreparedStatement statementPT = connection.prepareStatement(SELECT_PT);
            statementPT.setLong(1, id);
            ResultSet resultSet = statementPT.executeQuery();
            Set<Team> teams = new HashSet<>();
            while (resultSet.next()) {
                Long teamId = resultSet.getLong("team_id");
                String query = "select * from teams where id = " + teamId;
                Statement st = connection.createStatement();
                ResultSet teamsSet = st.executeQuery(query);
                while (teamsSet.next()) {
                    teams.add(new Team(teamId, teamsSet.getString("name"), new HashSet<Developer>()));
                }
            }

            project = new Project(id, name, teams);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (project == null) {
            return "Team with such id doesn't exist";
        }
        return project.toString();
    }

    public boolean update(Long id, Project entity) {
        try {
            PreparedStatement statementT = connection.prepareStatement(UPDATE);
            statementT.setString(1, entity.getName());
            statementT.setLong(2, id);

            String queryDel = "delete from projects_teams where project_id = " + id;
            Statement statementDSDel = connection.createStatement();
            statementDSDel.execute(queryDel);

            PreparedStatement statementTDIns = connection.prepareStatement(INSERT_PT);
            Set<Team> teams = entity.getTeams();
            for (Team team : teams) {
                statementTDIns.setLong(1, id);
                statementTDIns.setLong(2, team.getId());
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

    public boolean isExist(String name) {
        return isExist(name, EXISTS_BY_ARGS);
    }

    public Project getById(Long id) {
        Project project = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            while (rs.next()) {
                name = rs.getString("name");
            }

            PreparedStatement statementPT = connection.prepareStatement(SELECT_PT);
            statementPT.setLong(1, id);
            ResultSet resultSet = statementPT.executeQuery();
            Set<Team> teams = new HashSet<>();
            while (resultSet.next()) {
                Long teamId = resultSet.getLong("team_id");
                Team team = teamDAO.getById(teamId);
                teams.add(team);
            }

            project = new Project(id, name, teams);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public Set<Project> getMappingProjects(Set<Long> ids) {

        Set<Project> projects = new HashSet<>();
        try {
            for (Long id : ids) {
                PreparedStatement statement = connection.prepareStatement(SELECT);
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    projects.add(new Project(id, resultSet.getString("name"), new HashSet<>()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
