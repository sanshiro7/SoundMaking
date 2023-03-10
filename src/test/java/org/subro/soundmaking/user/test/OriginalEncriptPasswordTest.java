/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.user.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.subro.soundmaking.user.EncriptPassword;
import org.subro.soundmaking.user.OriginalEncriptPassword;

/**
 *
 * @author subro
 */
public class OriginalEncriptPasswordTest {

    @Test
    void testGetEncriptedPassword() {

        EncriptPassword encriptPassword = new OriginalEncriptPassword();

        String password = "あいうえお";
        String encriptedPassword = encriptPassword.getEncriptedPassword(password);

        assertEquals("ぃぅぇぉか", encriptedPassword);
    }

    @Test
    void testGetPassword() {

        EncriptPassword encriptPassword = new OriginalEncriptPassword();

        String encriptedPassword = "ぃぅぇぉか";
        String password = encriptPassword.getPassword(encriptedPassword);

        assertEquals("あいうえお", password);
    }
}
