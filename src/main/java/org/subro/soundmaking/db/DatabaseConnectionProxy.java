/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.db;

import java.sql.Connection;

/**
 *
 * @author subro
 */
public class DatabaseConnectionProxy {

    public static Connection getConnection() {
        return PostgreSQLWithHikariCPDataSource.getConnection();
    }

}
