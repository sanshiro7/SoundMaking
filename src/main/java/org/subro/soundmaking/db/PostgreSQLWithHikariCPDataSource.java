/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.subro.soundmaking.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author subro
 */
public class PostgreSQLWithHikariCPDataSource {

    final private static PostgreSQLWithHikariCPDataSource pds = new PostgreSQLWithHikariCPDataSource();
    private static HikariDataSource hds;
    private static final Logger logger = LoggerFactory.getLogger(PostgreSQLWithHikariCPDataSource.class);

    private PostgreSQLWithHikariCPDataSource() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("PostgreSQLのJDBCドライバをロードできません");
        }

        HikariConfig conf = new HikariConfig();
        conf.setJdbcUrl("jdbc:postgresql://UbuntuServer2204:5432/postgres");
        conf.setUsername("postgres");
        conf.setPassword("postgres");
        //conf.setConnectionTestQuery("select 1");

        PostgreSQLWithHikariCPDataSource.hds = new HikariDataSource(conf);
    }

    public static Connection getConnection() {
        try {
            return PostgreSQLWithHikariCPDataSource.hds.getConnection();
        } catch (SQLException e) {
            logger.error("コネクションの取得に失敗しました");
            logger.debug(e.toString());
            return null;
        }
    }

    public static boolean isRunning() {
        return PostgreSQLWithHikariCPDataSource.hds.isRunning();
    }

    public static void close() {
        if (PostgreSQLWithHikariCPDataSource.hds.isClosed()) {
            logger.info("コネクションプールはクローズされています");
        } else {
            PostgreSQLWithHikariCPDataSource.hds.close();
            logger.info("コネクションプールをクローズしました");
        }
    }

}
