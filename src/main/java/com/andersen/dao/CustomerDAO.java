package com.andersen.dao;

import com.andersen.DBWorker;
import com.andersen.model.Customer;
import com.andersen.model.Project;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CustomerDAO implements CrudDAO {

    private static final String INSERT = "INSERT INTO customers (first_name, last_name, address) VALUES(?, ?, ?)";
    private static final String INSERT_CP = "INSERT INTO customers_projects (customer_id, project_id) VALUES(?, ?)";
    private static final String SELECT = "SELECT * FROM customers  WHERE id = ?";
    private static final String SELECT_CP = "SELECT * FROM customers_projects WHERE customer_id = ?";
    private static final String UPDATE = "UPDATE customers SET first_name = ?, last_name = ?, address = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM customers WHERE id = ?";
    private static final String EXISTS = "SELECT EXISTS(SELECT id FROM customers WHERE id = ?)";
    private Connection connection = DBWorker.getConnection();


    public boolean save(Customer entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            PreparedStatement statementTD = connection.prepareStatement(INSERT_CP);

            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getAddress());
            statement.execute();

            String query = "select * from customers where (first_name like ?) and (last_name like ?) and (address like ?)";
            PreparedStatement helpStatement = connection.prepareStatement(query);
            helpStatement.setString(1, entity.getFirstName());
            helpStatement.setString(2, entity.getLastName());
            helpStatement.setString(3, entity.getAddress());
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
        Customer customer = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            String firstName = null;
            String lastName = null;
            String address = null;
            while (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                address = rs.getString("address");
            }

            PreparedStatement statementCP = connection.prepareStatement(SELECT_CP);
            statementCP.setLong(1, id);
            ResultSet resultSet = statementCP.executeQuery();
            Set<Project> projects = new HashSet<>();
            while (resultSet.next()) {
                Long projectId = resultSet.getLong("project_id");
                String query = "select * from projects where id = " + projectId;
                Statement st = connection.createStatement();
                ResultSet projectsSet = st.executeQuery(query);
                while (projectsSet.next()) {
                    projects.add(new Project(projectId, projectsSet.getString("name"), new HashSet<>()));
                }
            }

            customer = new Customer(id, firstName, lastName, address, projects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customer == null) {
            return "Team with such id doesn't exist";
        }
        return customer.toString();
    }

    public boolean update(Long id, Customer entity) {
        try {
            PreparedStatement statementT = connection.prepareStatement(UPDATE);
            statementT.setString(1, entity.getFirstName());
            statementT.setString(2, entity.getLastName());
            statementT.setString(3, entity.getAddress());
            statementT.setLong(4, id);

            String queryDel = "delete from customers_projects where customer_id = " + id;
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
}
