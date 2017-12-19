package com.andersen.jdbc;

import com.andersen.DBWorker;
import com.andersen.idao.ICompanyDAO;
import com.andersen.model.Company;
import com.andersen.model.Project;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CompanyDAO implements ICompanyDAO {

    private static final String INSERT = "INSERT INTO companies (name) VALUES(?)";
    private static final String INSERT_CP = "INSERT INTO companies_projects (company_id, project_id) VALUES(?, ?)";
    private static final String SELECT = "SELECT * FROM companies  WHERE id = ?";
    private static final String SELECT_CP = "SELECT * FROM companies_projects WHERE company_id = ?";
    private static final String UPDATE = "UPDATE companies SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM companies WHERE id = ?";
    private static final String EXISTS = "SELECT EXISTS(SELECT id FROM companies WHERE id = ?)";
    private static final String EXISTS_BY_ARGS = "SELECT EXISTS(SELECT id FROM companies WHERE name = ?)";
    private Connection connection = DBWorker.getConnection();
    private ProjectDAO projectDAO = new ProjectDAO();

    public boolean save(Company entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            PreparedStatement statementTD = connection.prepareStatement(INSERT_CP);

            statement.setString(1, entity.getName());
            statement.execute();

            String query = "select * from companies where name like ?";
            PreparedStatement helpStatement = connection.prepareStatement(query);
            helpStatement.setString(1, entity.getName());
            ResultSet resultSet = helpStatement.executeQuery();
            Long id = null;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }

            Set<Project> projects = entity.getProjects();
            if (id != null) {
                for (Project project: projects) {
                    statementTD.setLong(1, id);
                    statementTD.setLong(2, project.getId());
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
        Company company = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            while (rs.next()) {
                name = rs.getString("name");
            }

            PreparedStatement statementCP = connection.prepareStatement(SELECT_CP);
            statementCP.setLong(1, id);
            ResultSet resultSet = statementCP.executeQuery();
            Set<Long> ids = new HashSet<>();
            while (resultSet.next()) {
                Long projectId = resultSet.getLong("project_id");
                ids.add(projectId);
            }

            Set<Project> projects = projectDAO.getMappingProjects(ids);

            company = new Company(id, name, projects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (company == null) {
            return "Team with such id doesn't exist";
        }
        return company.toString();
    }


    public boolean update(Long id, Company entity) {
        try {
            PreparedStatement statementT = connection.prepareStatement(UPDATE);
            statementT.setString(1, entity.getName());
            statementT.setLong(2, id);

            String queryDel = "delete from companies_projects where company_id = " + id;
            Statement statementDSDel = connection.createStatement();
            statementDSDel.execute(queryDel);

            PreparedStatement statementCPIns = connection.prepareStatement(INSERT_CP);
            Set<Project> projects = entity.getProjects();
            for (Project project: projects) {
                statementCPIns.setLong(1, id);
                statementCPIns.setLong(2, project.getId());
                statementCPIns.execute();
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
}
