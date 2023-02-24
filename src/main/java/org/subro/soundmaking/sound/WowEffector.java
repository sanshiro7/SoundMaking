/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class WowEffector extends Effector {

    public WowEffector() {
        super.effectorId = 5;
        super.effectName = "Wow";
    }

    @Override
    public String getSound() {

        if (super.onoff == true) {
            // 頭と尻に「ゥ」を付けます
            return "ゥ" + super.inputSound + "ゥ";
        } else {
            // そのまま
            return super.inputSound;
        }
    }
}
