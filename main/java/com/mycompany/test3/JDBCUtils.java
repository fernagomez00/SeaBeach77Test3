/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.test3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jose
 */
public class JDBCUtils {
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(SQL.JDBCURL.toString(), SQL.JDBCUSERNAME.toString(), SQL.JDBCPASSWORD.toString());
            System.out.println("Connected!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    public static void selectArg(int index){
        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.DEFAULTQUERY.toString());) {
            preparedStatement.setInt(1, index);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("idnum");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(id + "," + firstname + "," + lastname + "," + username + "," + password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }
    
    public static void selectUsers(){
        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.DEFAULTQUERY1.toString());) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("idnum");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(id + "," + firstname + "," + lastname + "," + username + "," + password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }
    
     public static void selectUsersFullNames(){
        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.USERSFULLNAMES.toString());) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                System.out.println(firstname +" "+ lastname);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }
    
    public static void insertRecord(int idnum, String firstname, String lastname, String username, String password) throws SQLException {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.INSERTUSER.toString())) {
            preparedStatement.setInt(1, idnum);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
     
    private static final String createTableSQL = """
                                                   create table user (\r
                                                   idnum  int(4) primary key,\r
                                                   first_name varchar(35),\r
                                                   last_name varchar(35),\r
                                                   username varchar(35),\r
                                                   password varchar(35)\r
                                                   );""";
    public static void createTable(){
        System.out.println(createTableSQL);
        
        try (Connection connection = JDBCUtils.getConnection();
            
            Statement statement = connection.createStatement();) {
            
            statement.execute(createTableSQL);
            
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
        
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
