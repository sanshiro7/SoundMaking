/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author subro
 */
public class SoundSettingService {

    private static final Logger logger = LoggerFactory.getLogger(SoundSettingService.class);
    private final Object _LOCK_OBJECT = new Object();

    public List<SoundSetting> getSoundSettingAll() {

        List<SoundSetting> allSoundSettings = new ArrayList<SoundSetting>();

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select * from soundsetting order by id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                SoundSetting soundsetting = new SoundSetting();

                soundsetting.setId(result.getInt("id"));
                soundsetting.setSoundName(result.getString("soundname"));
                soundsetting.setUserId(result.getInt("userid"));
                soundsetting.setEffectorId1(result.getInt("effectorid1"));
                soundsetting.setEffectorOnOff1(result.getBoolean("effectorOnOff1"));
                soundsetting.setEffectorId2(result.getInt("effectorid2"));
                soundsetting.setEffectorOnOff2(result.getBoolean("effectorOnOff2"));
                soundsetting.setEffectorId3(result.getInt("effectorid3"));
                soundsetting.setEffectorOnOff3(result.getBoolean("effectorOnOff3"));
                soundsetting.setEffectorId4(result.getInt("effectorid4"));
                soundsetting.setEffectorOnOff4(result.getBoolean("effectorOnOff4"));
                soundsetting.setEffectorId5(result.getInt("effectorid5"));
                soundsetting.setEffectorOnOff5(result.getBoolean("effectorOnOff5"));

                allSoundSettings.add(soundsetting);
            }

            result.close();
        } catch (Exception e) {
            logger.info("SQLの実行に失敗しました(getSoundSettingAll)");
            logger.debug(e.toString());
        }

        return allSoundSettings;
    }

    public List<SoundSetting> getSoundSettingAllByLoginuserId(int loginuserid) {

        List<SoundSetting> allSoundSettings = new ArrayList<SoundSetting>();

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select * from soundsetting where userid = ? order by id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, loginuserid);
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                SoundSetting soundsetting = new SoundSetting();

                soundsetting.setId(result.getInt("id"));
                soundsetting.setSoundName(result.getString("soundname"));
                soundsetting.setUserId(result.getInt("userid"));
                soundsetting.setEffectorId1(result.getInt("effectorid1"));
                soundsetting.setEffectorOnOff1(result.getBoolean("effectorOnOff1"));
                soundsetting.setEffectorId2(result.getInt("effectorid2"));
                soundsetting.setEffectorOnOff2(result.getBoolean("effectorOnOff2"));
                soundsetting.setEffectorId3(result.getInt("effectorid3"));
                soundsetting.setEffectorOnOff3(result.getBoolean("effectorOnOff3"));
                soundsetting.setEffectorId4(result.getInt("effectorid4"));
                soundsetting.setEffectorOnOff4(result.getBoolean("effectorOnOff4"));
                soundsetting.setEffectorId5(result.getInt("effectorid5"));
                soundsetting.setEffectorOnOff5(result.getBoolean("effectorOnOff5"));

                allSoundSettings.add(soundsetting);
            }

            result.close();
        } catch (Exception e) {
            logger.info("SQLの実行に失敗しました(getSoundSettingAllByLoginuserId)");
            logger.debug(e.toString());
        }

        return allSoundSettings;
    }

    // 既にある名前か確認(全体で)
    public boolean checkSoundSettingByName(String soundname) {

        int count = 0;

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select id from soundsetting where soundname= ?";
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, soundname.trim());
            ResultSet result = ps.executeQuery();

            // 最終行に移動
            result.last();
            // 件数取得
            count = result.getRow();

            result.close();
        } catch (Exception e) {
            logger.info("SQLの実行に失敗しました(checkSoundSettingByName)");
            logger.debug(e.toString());
            count = 2;
        }

        if (count == 0) {
            return false;
        } else if (count == 1) {
            return true;
        } else {
            logger.warn("2件以上が取得されました(checkSoundSettingByName)");
            return true;
        }
    }

    // 既にある名前か確認(ユーザー単位で)
    public boolean checkSoundSettingByLoginuserIdAndName(int loginuserId, String soundname) {

        int count = 0;

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select id from soundsetting where userid = ? and soundname= ?";
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, loginuserId);
            ps.setString(2, soundname.trim());
            ResultSet result = ps.executeQuery();

            // 最終行に移動
            result.last();
            // 件数取得
            count = result.getRow();

            result.close();

        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(checkSoundSettingByLoginuserIdAndName)");
            logger.debug(e.toString());
            count = 2;
        }

        if (count == 0) {
            return false;
        } else if (count == 1) {
            return true;
        } else {
            logger.warn("2件以上が取得されました(checkSoundSettingByLoginuserIdAndName)");
            return true;
        }
    }

    public SoundSetting getSoundSettingById(int soundsettingId) {

        SoundSetting soundsetting = new SoundSetting();
        int count = 0;

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select * from soundsetting where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, soundsettingId);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                soundsetting.setId(result.getInt("id"));
                soundsetting.setSoundName(result.getString("soundname"));
                soundsetting.setUserId(result.getInt("userid"));
                soundsetting.setEffectorId1(result.getInt("effectorid1"));
                soundsetting.setEffectorOnOff1(result.getBoolean("effectorOnOff1"));
                soundsetting.setEffectorId2(result.getInt("effectorid2"));
                soundsetting.setEffectorOnOff2(result.getBoolean("effectorOnOff2"));
                soundsetting.setEffectorId3(result.getInt("effectorid3"));
                soundsetting.setEffectorOnOff3(result.getBoolean("effectorOnOff3"));
                soundsetting.setEffectorId4(result.getInt("effectorid4"));
                soundsetting.setEffectorOnOff4(result.getBoolean("effectorOnOff4"));
                soundsetting.setEffectorId5(result.getInt("effectorid5"));
                soundsetting.setEffectorOnOff5(result.getBoolean("effectorOnOff5"));
                count++;
            }

            result.close();

        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(getSoundSettingById)");
            logger.debug(e.toString());
        }

        // データベースに登録されていないユーザーの場合
        if (count == 0) {
            return null;
        } // 1件だけ取れるはずが2件以上あった場合
        else if (count > 2) {
            logger.warn("soundsettingテーブルからID(" + soundsettingId + ")指定で2件以上が取得されました");
            return null;
        } // 正常な場合
        else {
            return soundsetting;
        }
    }

    public void createSoundSetting(SoundSetting soundsetting) {

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            synchronized (_LOCK_OBJECT) {
                String sql = "select id from soundsetting order by id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet result = ps.executeQuery();

                int id1 = 0;
                while (result.next()) {
                    int id2 = result.getInt("id");
                    if ((id2 - id1) > 1) {
                        break;
                    } else {
                        id1 = id2;
                    }
                }
                int targetID = id1 + 1;

                result.close();

                sql = "insert into soundsetting values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, targetID);
                ps.setString(2, soundsetting.getSoundName().trim());
                ps.setInt(3, soundsetting.getUserId());
                ps.setInt(4, soundsetting.getEffectorId1());
                ps.setBoolean(5, soundsetting.isEffectorOnOff1());
                ps.setInt(6, soundsetting.getEffectorId2());
                ps.setBoolean(7, soundsetting.isEffectorOnOff2());
                ps.setInt(8, soundsetting.getEffectorId3());
                ps.setBoolean(9, soundsetting.isEffectorOnOff3());
                ps.setInt(10, soundsetting.getEffectorId4());
                ps.setBoolean(11, soundsetting.isEffectorOnOff4());
                ps.setInt(12, soundsetting.getEffectorId5());
                ps.setBoolean(13, soundsetting.isEffectorOnOff5());

                ps.executeUpdate();
            }
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(createSoundSetting)");
            logger.debug(e.toString());
        }
    }

    public void deleteSoundSetting(int soundsettingId) {
        try (Connection conn = DatabaseConnectionProxy.getConnection()) {
            String sql = "delete from soundsetting where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, soundsettingId);
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(deleteSoundSetting)");
            logger.debug(e.toString());
        }
    }

}
