/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class DelayEffector extends Effector {

    public DelayEffector() {
        super.effectorId = 4;
        super.effectName = "Delay";
    }

    @Override
    public String getSound() {

        if (super.onoff == true) {
            // 最後の音を3回繰り返します
            String lastsound = super.inputSound.substring(super.inputSound.length() - 1);
            return super.inputSound + lastsound + lastsound + lastsound;
        } else {
            // そのまま
            return super.inputSound;
        }
    }
}
