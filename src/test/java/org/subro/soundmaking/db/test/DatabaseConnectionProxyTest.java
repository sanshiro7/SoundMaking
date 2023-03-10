/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.subro.soundmaking.db.test;


import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.subro.soundmaking.db.DatabaseConnectionProxy;

/**
 *
 * @author subro
 */
public class DatabaseConnectionProxyTest {

    @Test
    void testGetConnection() {
        Connection connection = DatabaseConnectionProxy.getConnection();

        assertNotNull(connection);

        try {
            connection.close();
        } catch (SQLException e) {
            fail();
        }
    }
}
