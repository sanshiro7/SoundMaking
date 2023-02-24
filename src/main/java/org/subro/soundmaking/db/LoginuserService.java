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
public class LoginuserService {

    private static final Logger logger = LoggerFactory.getLogger(LoginuserService.class);
    private final Object _LOCK_OBJECT = new Object();

    // ログインユーザーIDでユーザーを検索
    public Loginuser getLoginuserById(int id) {

        Loginuser loginuser = new Loginuser();
        int count = 0;

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select * from loginuser where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                loginuser.setId(result.getInt("id"));
                loginuser.setUsername(result.getString("username"));
                loginuser.setPassword(result.getString("password"));
                loginuser.setGroupname(result.getString("groupname"));
                count++;
            }

            result.close();
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(getLoginuserById)");
            logger.debug(e.toString());
        }

        // データベースに登録されていないユーザーの場合
        if (count == 0) {
            return null;
        } // 1件だけ取れるはずが2件以上あった場合
        else if (count > 2) {
            logger.warn("loginuserテーブルからID(" + loginuser.getId() + ")指定で2件以上が取得されました");
            return null;
        } // 正常な場合
        else {
            return loginuser;
        }
    }

    public List<Loginuser> getLoginuserAll() {

        List<Loginuser> allLoginusers = new ArrayList<Loginuser>();

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select * from loginuser order by id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Loginuser loginuser = new Loginuser();
                loginuser.setId(result.getInt("id"));
                loginuser.setUsername(result.getString("username"));
                loginuser.setPassword(result.getString("password"));
                loginuser.setGroupname(result.getString("groupname"));
                allLoginusers.add(loginuser);
            }

            result.close();
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(getLoginuserAll)");
            logger.debug(e.toString());
        }

        return allLoginusers;
    }

    public Loginuser getLoginuserByUsername(String username) {

        Loginuser loginuser = new Loginuser();
        int count = 0;

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select * from loginuser where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username.trim());
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                loginuser.setId(result.getInt("id"));
                loginuser.setUsername(result.getString("username"));
                loginuser.setPassword(result.getString("password"));
                loginuser.setGroupname(result.getString("groupname"));
                count++;
            }

            result.close();
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(getLoginuserByUsername)");
            logger.debug(e.toString());
        }

        // データベースに登録されていないユーザーの場合
        if (count == 0) {
            return null;
        } // 1件だけ取れるはずが2件以上あった場合
        else if (count > 2) {
            logger.warn("loginuserテーブルからユーザー名(" + loginuser.getUsername() + ")指定で2件以上が取得されました");
            return null;
        } // 正常な場合
        else {
            return loginuser;
        }
    }

    public boolean checkLoginuserByUsername(String username) {

        int count = 0;

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            String sql = "select * from loginuser where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, username.trim());
            ResultSet result = ps.executeQuery();

            // 最終行に移動
            result.last();
            // 件数取得
            count = result.getRow();

            result.close();
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(checkLoginuserByUsername)");
            logger.debug(e.toString());
            count = 2;
        }

        if (count == 0) {
            return false;
        } else if (count == 1) {
            return true;
        } else {
            logger.warn("2件以上が取得されました(checkLoginuserByUsername)");
            return true;
        }
    }

    public void createLoginuser(String username, String password, String groupname) {

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            // IDの空きを取る作業が他と重なってはいけない
            synchronized (_LOCK_OBJECT) {

                String sql = "select id from loginuser order by id";
                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet result = ps.executeQuery();

                // IDの空きを探す
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

                sql = "insert into loginuser values( ?, ?, ?, ? )";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, targetID);
                ps.setString(2, username.trim());
                ps.setString(3, password.trim());
                ps.setString(4, groupname.trim());

                ps.executeUpdate();

            }

        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(createLoginuser)");
            logger.debug(e.toString());
        }
    }

    public void deleteLoginuser(int id) {

        // ログインユーザーとそれに紐づいたサウンドセッティングを削除する
        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            try {
                //　トランザクション処理のためオートコミットを無効にする
                conn.setAutoCommit(false);

                String sql1 = "delete from soundsetting where userid = ?";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, id);
                ps1.executeUpdate();

                String sql2 = "delete from loginuser where id = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, id);
                ps2.executeUpdate();

                // 例外がなければコミット
                conn.commit();

            } catch (Exception e) {
                // 例外発生時はロールバック
                conn.rollback();
                // 上位に同じエラーをスロー
                throw e;
            } finally {
                // 例外があっても無くてもこのコネクションのオートコミット設定は
                // 元の有効に戻す
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(deleteLoginuser)");
            logger.debug(e.toString());
        }
    }

    public void updateLoginuser(Loginuser loginuser) {

        try (Connection conn = DatabaseConnectionProxy.getConnection()) {

            int id = loginuser.getId();
            String username = loginuser.getUsername();
            String password = loginuser.getPassword();
            String groupname = loginuser.getGroupname();

            String sql = "update loginuser set username = ?, groupname = ?, password = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username.trim());
            ps.setString(2, groupname.trim());
            ps.setString(3, password.trim());
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (Exception e) {
            logger.error("SQLの実行に失敗しました(updateLoginuser)");
            logger.debug(e.toString());
        }
    }

}
