package com.andersen.hibernate;

import com.andersen.HibernateWorker;
import com.andersen.idao.ICompanyDAO;
import com.andersen.model.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompanyDAOImpl implements ICompanyDAO {

    private Session session;

    public CompanyDAOImpl() {
        session = HibernateWorker.getSessionFactory().openSession();
    }

    @Override
    public boolean save(Company company) {
        return saveAs(company);
    }

    @Override
    public String read(Long id) {
        Company company = session.load(Company.class, id);
        return company.toString();
    }

    @Override
    public boolean update(Long id, Company newCompany) {
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Company oldCompany = (Company) session.get(Company.class, id);
        oldCompany.setName(newCompany.getName());
        oldCompany.setProjects(newCompany.getProjects());

        session.update(oldCompany);
        transaction.commit();
        return oldCompany != null;
    }

    @Override
    public void delete(Long id) {

        Transaction transaction = null;

        transaction = session.beginTransaction();
        Company company = (Company) session.get(Company.class, id);
        session.delete(company);
        transaction.commit();
    }

    public boolean isExist(Long id) {
        return session.get(Company.class, id) != null;
    }

    public Company getById(Long id) {
        return session.get(Company.class, id);
    }
}
