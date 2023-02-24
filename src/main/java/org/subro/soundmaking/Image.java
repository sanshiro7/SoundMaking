/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.subro.soundmaking;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author subro
 */
@WebServlet(name = "Image", urlPatterns = {"/img"})
public class Image extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(Image.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
    }

    /**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ServletのOutputStream取得
        try (ServletOutputStream sos = response.getOutputStream()) {

            String dir = getServletContext().getRealPath("/img");
            String filename = request.getParameter("name");

            response.setHeader("Content-Type", "image/png");

            try (FileInputStream fis = new FileInputStream(dir + "/" + filename)) {

                BufferedInputStream bis = new BufferedInputStream(fis);
                int buffer = 0;

                //画像データをレスポンスに送る
                while (buffer != -1) {
                    try {
                        buffer = bis.read();
                    } catch (IOException e) {
                        logger.error("ファイルの読み込みに失敗しました");
                        logger.debug(e.toString());
                    }
                    try {
                        sos.write(buffer);
                    } catch (IOException e) {
                        logger.error("レスポンスへのデータ投入に失敗しました");
                        logger.debug(e.toString());
                    }
                }
            } catch (FileNotFoundException e) {
                logger.error("ファイルが見つかりません");
                logger.debug(e.toString());
            } catch (SecurityException e) {
                logger.error("セキュリティによりファイルの利用ができません");
                logger.debug(e.toString());
            }

        } catch (IOException e) {
            logger.error("アウトプットストリームの取得に失敗しました");
            logger.debug(e.toString());
        }

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
        doGet(request, response);
    }

}
