package com.andersen.dao;

import com.andersen.DBWorker;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CrudDAO {

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
        if (count != null)
            return count > 0;
        return false;
    }
}
