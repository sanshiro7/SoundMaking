/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class OverdriveEffector extends Effector {

    public OverdriveEffector() {
        super.effectorId = 6;
        super.effectName = "Overdrive";
    }

    @Override
    public String getSound() {

        if (super.onoff == true) {
            // 歪ませます
            return "ジャ" + super.inputSound;
        } else {
            // そのまま
            return super.inputSound;
        }
    }
}
