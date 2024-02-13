/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.test3;

/**
 *
 * @author Jose
 */
public enum SQL {
    //SQL SERVER LOG IN INFORMATION
    JDBCURL("jdbc:mysql://localhost:3306/users?useSSL=false"),
    JDBCUSERNAME("root"),
    JDBCPASSWORD("1191108"),
    
    //BELOW ARE THE DIFFERENT QUERIES TO USE IN JDBCUTILS
    DEFAULTQUERY("select idnum,first_name,last_name,username,password from user where idnum =?"),
    DEFAULTQUERY1("select * from user"),
    USERSFULLNAMES("SELECT * from user"),
    INSERTUSER("INSERT INTO user " + "(idnum, first_name, last_name, username, password) VALUES " + " (?, ?, ?, ?, ?);");
    
    final String sql;
    
    SQL(String sql) {
        this.sql = sql;
    }
    
    @Override
    public String toString(){
        return sql;
    }
}
