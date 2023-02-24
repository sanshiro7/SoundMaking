/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

/**
 *
 * @author subro
 */
public abstract class Effector {

    int effectorId;  // エフェクターのユニークなID
    String effectName;  // エフェクターの名前
    String inputSound;  // エフェクターに入力された音
    boolean onoff;       // ペダルのオンオフ　オンがtrue オフがfalse

    /*
         *   コンストラクタ
         *   
         *   ペダルはオンがデフォルト
     */
    public Effector() {
        this.effectName = null;
        this.inputSound = null;
        this.onoff = true;
    }

    /*
         * 音の入力
     */
    public void setSound(String sound) {
        this.inputSound = sound;
    }

    /*
         * ペダルのオン
     */
    public void pedalOn() {
        this.onoff = true;
    }

    /*
         * ペダルのオフ
     */
    public void pedalOff() {
        this.onoff = false;
    }

    /*
         *  ペダルの状態を「true」と「false」で取得
     */
    public boolean getPedal() {
        return this.onoff;
    }

    /*
         *  ペダルの状態を「On」と「Off」で取得
     */
    public String getOnOffPedal() {
        if (this.onoff == true) {
            return "On";
        } else {
            return "Off";
        }
    }

    abstract public String getSound();

    public int getEffectorId() {
        return this.effectorId;
    }

    /*
         *  エフェクターの名前を取得
     */
    public String getEffectName() {
        return this.effectName;
    }

}
