/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class FuzzEffector extends Effector {

    public FuzzEffector() {
        super.effectorId = 2;
        super.effectName = "Fuzz";
    }

    @Override
    public String getSound() {

        if (super.onoff == true) {
            // ブー音で始まります
            return "ブブ" + super.inputSound;
        } else {
            // そのまま
            return super.inputSound;
        }
    }
}
