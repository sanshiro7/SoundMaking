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
import org.subro.soundmaking.sound.EffectorBoard;
import org.subro.soundmaking.sound.EffectorFactory;
import org.subro.soundmaking.sound.EffectorList;


/**
 *
 * @author subro
 */
@WebServlet(name = "Sound", urlPatterns = {"/sound"})
public class Sound extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sound() {
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

        // セッションデータからエフェクターボードの状態を取得
        HttpSession session = request.getSession();
        EffectorBoard effectorboard = (EffectorBoard) session.getAttribute("EffectorBoard");

        // リクエストにエフェクターボードの状態を設定
        request.setAttribute("effectorIds", effectorboard.getEffectorIds());
        request.setAttribute("effectOnOffs", effectorboard.getEffectOnOffs());
        request.setAttribute("effectorList", EffectorList.effectorList);

        // sound.jsp表示
        request.getRequestDispatcher("/WEB-INF/view/sound.jsp").forward(request, response);
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

        // セッションデータからエフェクターボードの状態を取得
        HttpSession session = request.getSession();
        EffectorBoard effectorboard = (EffectorBoard) session.getAttribute("EffectorBoard");

        // sound.jspにてどういう作業をしたかを取得
        String action = request.getParameter("action");

        // 音を出す場合
        if (action != null && action.equals("sound")) {
            effectorboard.setSound("ポーン");
            request.setAttribute("outsound", effectorboard.getSound());
        } // エフェクターを変えた場合
        else if (action != null && action.equals("effector")) {
            int effectId = Integer.parseInt(request.getParameter("effectId"));
            int boardNo = Integer.parseInt(request.getParameter("boaradNo"));

            if (effectId == 0) // 「使わない(エフェクトIDが0)」場合
            {
                effectorboard.removeEffector(boardNo);
            } else // 特定のエフェクターが選ばれた場合
            {
                effectorboard.setEffector(boardNo, EffectorFactory.getEffectorById(effectId));
            }
        } // ペダル操作をした場合
        else if (action != null && action.equals("pedal")) {

            int boardNo = Integer.parseInt(request.getParameter("boardNo"));    // どの位置のエフェクターか
            String onoff = request.getParameter("onoff");                         // ペダルの状態取得

            if (effectorboard.getEffectId(boardNo) != 0) {    // エフェクターが「使わない」ではない場合

                if (onoff.equals("on")) // onのとき
                {
                    effectorboard.pedalOn(boardNo);
                } else // offのとき
                {
                    effectorboard.pedalOff(boardNo);
                }
            }
        } // その他の作業(想定なし)
        else {
        }

        // sound.jspの再表示
        doGet(request, response);
    }

}
