/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class CompressorEffector extends Effector {

    public CompressorEffector() {
        super.effectorId = 3;
        super.effectName = "Compressor";
    }

    @Override
    public String getSound() {

        if (super.onoff == true) {
            // アタックを消します
            return super.inputSound.substring(1);
        } else {
            // そのまま
            return super.inputSound;
        }
    }
}
