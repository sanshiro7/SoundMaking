/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.subro.soundmaking.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.subro.soundmaking.db.Loginuser;
import org.subro.soundmaking.db.SoundSetting;
import org.subro.soundmaking.db.SoundSettingService;
import org.subro.soundmaking.sound.EffectorBoard;
import org.subro.soundmaking.sound.EffectorFactory;


/**
 *
 * @author subro
 */
@WebServlet(name = "SoundManager", urlPatterns = {"/sound/soundmanager"})
public class SoundManager extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoundManager() {
        super();
    }

    /**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Loginuser loginuser = (Loginuser) session.getAttribute("loginuser");

        // サウンドセッティングサービスインスタンス作成
        SoundSettingService sss = new SoundSettingService();

        // サウンドセッティング全件読み込み
        List<SoundSetting> soundSettings = sss.getSoundSettingAllByLoginuserId(loginuser.getId());

        // リクエストスコープにサウンドセッティング全件を乗せる
        request.setAttribute("soundsettings", soundSettings);

        // soundmanager.jsp表示
        request.getRequestDispatcher("/WEB-INF/view/soundmanager.jsp").forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // soundsettging.jpsでした作業を取得
        String action = request.getParameter("action");

        // エラーメッセージ
        String err = null;

        // 新しいサウンドセッティングの登録の場合
        if (action.equals("create")) {

            String soundSettingName = (String) request.getParameter("soundname").trim();

            // おとの名前が空の場合
            if (soundSettingName.equals("")) {
                err = "名前を空にはできません";
            } // おとの名前が既に使われている場合
            else {
                HttpSession session = request.getSession();

                // セッションからログインユーザーIDを取得
                Loginuser loginuser = (Loginuser) session.getAttribute("loginuser");

                SoundSettingService sss = new SoundSettingService();

                if (soundSettingName.length() > 30) {
                    err = "名前は30文字までです";
                } else if (sss.checkSoundSettingByLoginuserIdAndName(loginuser.getId(), soundSettingName)) {    // サウンドセッティング名が既に存在している場合はエラー
                    err = "その名前は既に使われています";
                } // サウンドセッティング名が存在していなければ登録
                else {
                    // セッションから現在編集中のエフェクターボード状態を取得
                    EffectorBoard effectorboard = (EffectorBoard) session.getAttribute("EffectorBoard");

                    // SoundSettingクラスインスタンスを1つ作る
                    SoundSetting ss = new SoundSetting();

                    // 現在編集中のエフェクターボード状態とログインユーザーIDを設定
                    ss.setUserId(loginuser.getId());
                    ss.setSoundName(soundSettingName);
                    ss.setEffectorId1(effectorboard.getEffectId(0));
                    ss.setEffectorOnOff1(effectorboard.getPedal(0));
                    ss.setEffectorId2(effectorboard.getEffectId(1));
                    ss.setEffectorOnOff2(effectorboard.getPedal(1));
                    ss.setEffectorId3(effectorboard.getEffectId(2));
                    ss.setEffectorOnOff3(effectorboard.getPedal(2));
                    ss.setEffectorId4(effectorboard.getEffectId(3));
                    ss.setEffectorOnOff4(effectorboard.getPedal(3));
                    ss.setEffectorId5(effectorboard.getEffectId(4));
                    ss.setEffectorOnOff5(effectorboard.getPedal(4));

                    // サウンドセッティングをDBに登録
                    sss.createSoundSetting(ss);
                }
            }
        } // 削除ボタンを押した場合
        else if (action.equals("delete")) {
            int soundsettingId = Integer.parseInt(request.getParameter("soundsettingId"));
            SoundSettingService sss = new SoundSettingService();

            // サウンドセッティングIDでDBから1件削除
            sss.deleteSoundSetting(soundsettingId);
        } // 呼出ボタンを押した場合
        else if (action.equals("load")) {

            int soundsettingId = Integer.parseInt(request.getParameter("soundsettingId"));

            SoundSettingService sss = new SoundSettingService();
            SoundSetting ss = sss.getSoundSettingById(soundsettingId);

            EffectorBoard effectorboard = new EffectorBoard(5);

            HttpSession session = request.getSession();
            session.setAttribute("EffectorBoard", effectorboard);

            int[] effectorId = new int[5];
            effectorId[0] = ss.getEffectorId1();
            effectorId[1] = ss.getEffectorId2();
            effectorId[2] = ss.getEffectorId3();
            effectorId[3] = ss.getEffectorId4();
            effectorId[4] = ss.getEffectorId5();

            boolean[] effectorOnOff = new boolean[5];
            effectorOnOff[0] = ss.isEffectorOnOff1();
            effectorOnOff[1] = ss.isEffectorOnOff2();
            effectorOnOff[2] = ss.isEffectorOnOff3();
            effectorOnOff[3] = ss.isEffectorOnOff4();
            effectorOnOff[4] = ss.isEffectorOnOff5();

            for (int i = 0; i < 5; i++) {

                if (effectorId[i] != 0) {
                    effectorboard.setEffector(i, EffectorFactory.getEffectorById(effectorId[i]));

                    if (effectorOnOff[i] == true) {
                        effectorboard.pedalOn(i);
                    } else {
                        effectorboard.pedalOff(i);
                    }
                }

            }
        } // actionが nullの場合(想定していない) 
        else {
            err = "操作が指定されていません";
        }

        // エラーがあった場合
        if (err != null) {
            request.setAttribute("err", err);
        }

        doGet(request, response);
    }

}
