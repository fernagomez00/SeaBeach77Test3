/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.test3;

import java.sql.SQLException;

/**
 *
 * @author Jose
 */
public class Test3 {

    public static void main(String[] args) throws SQLException {
        JDBCUtils.getConnection();
        JDBCUtils.createTable();
        JDBCUtils.insertRecord(1, "Fernando","Gomez","fgomez5","1191108");
        JDBCUtils.insertRecord(2,"Jane", "Doe","JDoe99", "securepassword");
        JDBCUtils.insertRecord(3,"Bob", "Smith","meower333", "anotherpassword");
        JDBCUtils.selectUsers();
        JDBCUtils.selectUsersFullNames();
    }
}
