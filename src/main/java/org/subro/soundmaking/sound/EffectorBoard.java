/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public class EffectorBoard {

    private String inputSound;
    private int[] effectorIds;
    private Effector[] effectors;
    private boolean[] effectOnOffs;

    // コンストラクタでボードに並べられるエフェクター数を指定する
    public EffectorBoard(int num) {

        this.effectors = new Effector[num];
        this.effectorIds = new int[num];
        this.effectOnOffs = new boolean[num];

        for (int i = 0; i < num; i++) {
            this.effectors[i] = null;
            this.effectorIds[i] = 0;
            this.effectOnOffs[i] = true;
        }

    }

    // 入力音
    public void setSound(String sound) {
        this.inputSound = sound;
    }

    public void setEffector(int num, Effector effector) {
        this.effectors[num] = effector;
        this.effectorIds[num] = effector.getEffectorId();
        this.effectOnOffs[num] = effector.getPedal();
    }

    public void removeEffector(int num) {
        this.effectors[num] = null;
        this.effectorIds[num] = 0;
    }

    public int getEffectId(int num) {
        if (this.effectors[num] == null) {
            return 0;
        } else {
            return this.effectors[num].getEffectorId();
        }
    }

    public void pedalOn(int num) {
        this.effectors[num].pedalOn();
        this.effectOnOffs[num] = true;
    }

    public void pedalOff(int num) {
        this.effectors[num].pedalOff();
        this.effectOnOffs[num] = false;
    }

    public boolean getPedal(int num) {
        if (this.effectors[num] == null) {
            return false;
        } else {
            return this.effectors[num].getPedal();
        }
    }

    public int[] getEffectorIds() {
        return this.effectorIds;
    }

    public boolean[] getEffectOnOffs() {
        return this.effectOnOffs;
    }

    public String getSound() {

        String sound = this.inputSound;

        for (int i = 0; i < this.effectors.length; i++) {
            if (this.effectors[i] != null) {
                this.effectors[i].setSound(sound);
                sound = this.effectors[i].getSound();
            }
        }

        return sound;
    }

}
