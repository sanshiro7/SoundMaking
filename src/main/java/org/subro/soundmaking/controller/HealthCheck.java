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
import java.io.IOException;
import org.subro.soundmaking.db.PostgreSQLWithHikariCPDataSource;

/**
 *
 * @author subro
 */
@WebServlet(name = "HealthCheck", urlPatterns = {"/healthCheck"})
public class HealthCheck extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthCheck() {
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

        response.setContentType("application/json");
        response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");

        String json = "{\"status\":\"ok\",";

        if (PostgreSQLWithHikariCPDataSource.isRunning()) {
            json = json + "\"connection_pool\":\"ok\"}";
        } else {
            json = json + "\"connection_pool\":\"ng\"}";
        }

        response.getWriter().append(json);
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
