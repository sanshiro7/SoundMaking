/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.subro.soundmaking.db.PostgreSQLWithHikariCPDataSource;

/**
 *
 * @author subro
 */
@WebListener
public class ApplicationStartListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // コネクションプールの破棄
        System.out.println("アプリケーション終了: コネクションプールをクローズします。");
        PostgreSQLWithHikariCPDataSource.close();
    }

}
