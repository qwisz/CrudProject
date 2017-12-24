package com.andersen.idao;

import com.andersen.DBWorker;
import com.andersen.HibernateWorker;
import com.andersen.model.Identifier;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO<T extends Identifier> {

    boolean save(T entity);

    String read(Long id);

    boolean update(Long id, T entity);

    void delete(Long id);

//    T getById(Long id);

    default boolean saveAs(T entity) {
        Session session = HibernateWorker.getSessionFactory().openSession();
        Transaction transaction = null;
        Long id = null;

        transaction = session.beginTransaction();
        id = (Long) session.save(entity);
        transaction.commit();
        return id != null;
    }

    default void delete(Long id, String query) {
        try {
            Connection connection = DBWorker.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    default boolean isExist(Long id, String query) {
        Long count = null;
        try {
            Connection connection = DBWorker.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count != null && count > 0;
    }

    default boolean isExist(String name, String query) {
        Long count = null;
        try {
            Connection connection = DBWorker.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count != null && count > 0;
    }

    default boolean isExist(PreparedStatement statement) {
        Long count = null;
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count != null && count > 0;
    }
}
