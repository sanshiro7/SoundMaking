/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class EffectorFactory {

    static public Effector getEffectorById(int effectorId) {

        Effector effector;

        switch (effectorId) {
            case 0:
                effector = null;
                break;
            case 1:
                effector = new DistortionEffector();
                break;
            case 2:
                effector = new FuzzEffector();
                break;
            case 3:
                effector = new CompressorEffector();
                break;
            case 4:
                effector = new DelayEffector();
                break;
            case 5:
                effector = new WowEffector();
                break;
            case 6:
                effector = new OverdriveEffector();
                break;
            default:
                effector = null;
        }

        return effector;
    }

}
