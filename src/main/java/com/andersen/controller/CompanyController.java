package com.andersen.controller;

import com.andersen.jdbc.CompanyDAO;
import com.andersen.jdbc.ProjectDAO;
import com.andersen.model.Company;
import com.andersen.model.Project;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CompanyController {

    private CompanyDAO companyDAO = new CompanyDAO();
    private ProjectDAO projectDAO = new ProjectDAO();

    public boolean create(String name, Set<Long> ids) throws IOException {
        Set<Project> projects = new HashSet<>();

        for (Long id: ids) {
            if (!projectDAO.isExist(id))
                return false;
            projects.add(projectDAO.getById(id));
        }

        companyDAO.save(new Company(name, projects));

        return true;
    }

    public String read(Long id) throws IOException {
        return companyDAO.read(id);
    }

    public boolean update(Long id, String name, Set<Long> ids) throws IOException {
        Set<Project> projects = new HashSet<>();

        for (Long idd: ids) {
            if (!projectDAO.isExist(idd))
                return false;
            projects.add(projectDAO.getById(idd));
        }
        return companyDAO.update(id, new Company(name, projects));
    }

    public void delete(Long id) throws IOException {
        companyDAO.delete(id);
    }

    public boolean isExist(Long id) throws IOException {
        return companyDAO.isExist(id);
    }
}
