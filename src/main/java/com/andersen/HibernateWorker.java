package com.andersen;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateWorker {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable e) {
                System.err.println("Initial SessionFactory creation failed. " + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
}
