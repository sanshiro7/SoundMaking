/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.subro.soundmaking.db.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.subro.soundmaking.db.SoundSetting;

/**
 *
 * @author subro
 */
public class SoundSettingTest {

    @Test
    void testSoundSettingId() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setId(1);
        assertEquals(soundSetting.getId(), 1);
    }

    @Test
    void testSoundSettingSoundName() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setSoundName("123456789012345678901234567890");
        assertEquals(soundSetting.getSoundName(), "123456789012345678901234567890");
    }

    @Test
    void testSoundSettingUserId() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setUserId(2);
        assertEquals(soundSetting.getUserId(), 2);
    }

    @Test
    void testSoundSettingEffectorId1() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorId1(3);
        assertEquals(soundSetting.getEffectorId1(), 3);
    }

    @Test
    void testSoundSettingEffectorOnOff1On() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff1(true);
        assertEquals(soundSetting.isEffectorOnOff1(), true);
    }

    @Test
    void testSoundSettingEffectorOnOff1Off() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff1(false);
        assertEquals(soundSetting.isEffectorOnOff1(), false);
    }

    @Test
    void testSoundSettingEffectorId2() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorId2(4);
        assertEquals(soundSetting.getEffectorId2(), 4);
    }

    @Test
    void testSoundSettingEffectorOnOff2On() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff2(true);
        assertEquals(soundSetting.isEffectorOnOff2(), true);
    }

    @Test
    void testSoundSettingEffectorOnOff2Off() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff2(false);
        assertEquals(soundSetting.isEffectorOnOff2(), false);
    }

    @Test
    void testSoundSettingEffectorId3() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorId3(5);
        assertEquals(soundSetting.getEffectorId3(), 5);
    }

    @Test
    void testSoundSettingEffectorOnOff3On() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff3(true);
        assertEquals(soundSetting.isEffectorOnOff3(), true);
    }

    @Test
    void testSoundSettingEffectorOnOff3Off() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff3(false);
        assertEquals(soundSetting.isEffectorOnOff3(), false);
    }

    @Test
    void testSoundSettingEffectorId4() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorId4(6);
        assertEquals(soundSetting.getEffectorId4(), 6);
    }

    @Test
    void testSoundSettingEffectorOnOff4On() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff4(true);
        assertEquals(soundSetting.isEffectorOnOff4(), true);
    }

    @Test
    void testSoundSettingEffectorOnOff4Off() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff4(false);
        assertEquals(soundSetting.isEffectorOnOff4(), false);
    }

    @Test
    void testSoundSettingEffectorId5() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorId5(7);
        assertEquals(soundSetting.getEffectorId5(), 7);
    }

    @Test
    void testSoundSettingEffectorOnOff5On() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff5(true);
        assertEquals(soundSetting.isEffectorOnOff5(), true);
    }

    @Test
    void testSoundSettingEffectorOnOff5Off() {
        SoundSetting soundSetting = new SoundSetting();
        soundSetting.setEffectorOnOff5(false);
        assertEquals(soundSetting.isEffectorOnOff5(), false);
    }
}
