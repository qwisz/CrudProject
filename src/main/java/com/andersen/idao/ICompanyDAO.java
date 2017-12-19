package com.andersen.idao;

import com.andersen.model.Company;

public interface ICompanyDAO extends DAO<Company> {

    boolean save(Company company);

    boolean update(Long id, Company newCompany);
}
