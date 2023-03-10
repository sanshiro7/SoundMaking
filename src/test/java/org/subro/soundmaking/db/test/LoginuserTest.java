/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.subro.soundmaking.db.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.subro.soundmaking.db.Loginuser;

/**
 *
 * @author subro
 */
public class LoginuserTest {

    @Test
    void testLoginuserId() {
        Loginuser loginuser = new Loginuser();
        loginuser.setId(1);
        assertEquals(loginuser.getId(), 1);
    }

    @Test
    void testLoginuserUsername() {
        Loginuser loginuser = new Loginuser();
        loginuser.setUsername("123456789012345678901234567890");
        assertEquals(loginuser.getUsername(), "123456789012345678901234567890");
    }

    @Test
    void testLoginuserPassword() {
        Loginuser loginuser = new Loginuser();
        loginuser.setPassword("123456789012345678901234567890");
        assertEquals(loginuser.getPassword(), "123456789012345678901234567890");
    }

    @Test
    void testLoginuserGroupname() {
        Loginuser loginuser = new Loginuser();
        loginuser.setGroupname("12345");
        assertEquals(loginuser.getGroupname(), "12345");
    }
}
