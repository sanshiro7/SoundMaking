/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.user;

/**
 *
 * @author subro
 */
public class OriginalEncriptPassword implements EncriptPassword {

    // 文字コードを1つ後ろにずらす
    @Override
    public String getEncriptedPassword(String password) {

        if (password != null) {
            char[] passwordCharcters = password.toCharArray();
            char[] encriptedPasswordCharcters = new char[passwordCharcters.length];

            for (int i = 0; i < passwordCharcters.length; i++) {
                encriptedPasswordCharcters[i] = (char) (passwordCharcters[i] + 1);
            }

            return new String(encriptedPasswordCharcters);
        } else {
            return null;
        }
    }

    @Override
    // 文字コードを1つ前にずらす
    public String getPassword(String encriptedPassword) {

        if (encriptedPassword != null) {
            char[] encriptedPasswordCharcters = encriptedPassword.toCharArray();
            char[] passwordCharcters = new char[encriptedPasswordCharcters.length];

            for (int i = 0; i < encriptedPasswordCharcters.length; i++) {
                passwordCharcters[i] = (char) (encriptedPasswordCharcters[i] - 1);
            }

            return new String(passwordCharcters);

        } else {
            return null;
        }
    }
}
