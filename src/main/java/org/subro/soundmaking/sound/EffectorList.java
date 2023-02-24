/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.sound;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author subro
 */
public class EffectorList {
    
       public static final Map<Integer, String> effectorList = new HashMap<Integer, String>();

        static { 
                // エフェクターID(int)、エフェクター名(String)のリスト
                // DBにはエフェクターIDが登録されるのでIDは変えないようにしましょう
                effectorList.put(0, "使わない");   // 固定(変えてはいけません)
                effectorList.put(1, "Distortion");
                effectorList.put(2, "Fuzz");
                effectorList.put(3, "Compressor");
                effectorList.put(4, "Delay");
                effectorList.put(5, "Wow");
                effectorList.put(6, "Overdrive");
        }

}
