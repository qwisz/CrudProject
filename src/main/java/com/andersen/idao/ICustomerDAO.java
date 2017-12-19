package com.andersen.idao;

import com.andersen.model.Customer;

public interface ICustomerDAO extends DAO<Customer> {

    boolean save(Customer customer);

    boolean update(Long id, Customer newCustomer);
}
