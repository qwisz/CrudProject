package com.andersen.hibernate;

import com.andersen.HibernateWorker;
import com.andersen.idao.IDeveloperDAO;
import com.andersen.model.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeveloperDAOImpl implements IDeveloperDAO {

    private Session session;

    public DeveloperDAOImpl() {
        session = HibernateWorker.getSessionFactory().openSession();
    }

    @Override
    public boolean save(Developer developer) {
        return saveAs(developer);
    }

    @Override
    public String read(Long id) {
        Developer dev = session.load(Developer.class, id);
        return dev.toString();
    }

    @Override
    public boolean update(Long id, Developer newDev) {
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Developer oldDev = (Developer) session.get(Developer.class, id);
        oldDev.setFirstName(newDev.getFirstName());
        oldDev.setLastName(newDev.getLastName());
        oldDev.setSpeciality(newDev.getSpeciality());
        oldDev.setSalary(newDev.getSalary());
        oldDev.setSkills(newDev.getSkills());

        session.update(oldDev);
        transaction.commit();
        return oldDev != null;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer dev = (Developer) session.get(Developer.class, id);
        session.delete(dev);
        transaction.commit();
        session.close();
    }

    public boolean isExist(Long id) {
        return session.get(Developer.class, id) != null;
    }

    public Developer getById(Long id) {
        return session.get(Developer.class, id);
    }
}
