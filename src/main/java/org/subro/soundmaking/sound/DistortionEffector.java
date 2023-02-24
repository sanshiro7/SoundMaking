/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class DistortionEffector extends Effector {

    public DistortionEffector() {
        super.effectorId = 1;
        super.effectName = "Distortion";
    }

    @Override
    public String getSound() {

        if (super.onoff == true) {
            // 歪ませます
            return "ギュ" + super.inputSound;
        } else {
            // そのまま
            return super.inputSound;
        }
    }
}
