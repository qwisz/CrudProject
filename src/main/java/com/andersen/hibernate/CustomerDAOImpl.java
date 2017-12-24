package com.andersen.hibernate;

import com.andersen.HibernateWorker;
import com.andersen.idao.ICustomerDAO;
import com.andersen.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAOImpl implements ICustomerDAO {

    private Session session;

    public CustomerDAOImpl() {
        session = HibernateWorker.getSessionFactory().openSession();
    }

    @Override
    public boolean save(Customer customer) {
        return saveAs(customer);
    }

    @Override
    public String read(Long id) {
        Customer customer = session.load(Customer.class, id);
        return customer.toString();
    }

    @Override
    public boolean update(Long id, Customer newCustomer) {
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Customer oldCustomer = (Customer) session.get(Customer.class, id);
        oldCustomer.setFirstName(newCustomer.getFirstName());
        oldCustomer.setLastName(newCustomer.getLastName());
        oldCustomer.setAddress(newCustomer.getAddress());
        oldCustomer.setProjects(newCustomer.getProjects());

        session.update(oldCustomer);
        transaction.commit();
        return oldCustomer != null;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, id);
        session.delete(customer);
        transaction.commit();
    }

    public boolean isExist(Long id) {
        return session.get(Customer.class, id) != null;
    }

    public Customer getById(Long id) {
        return session.get(Customer.class, id);
    }
}
