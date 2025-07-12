/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnlaceBd {
    private static HikariDataSource dataSource;
    
    
   static {
        HikariConfig config = new HikariConfig();
        
          // Configuración básica
         config.setJdbcUrl("jdbc:mysql://156.67.76.8:3306/u689822279_inventory?" +
         "useSSL=false&" +
         "autoReconnect=true&" +
         "socketTimeout=30000&" +
         "connectTimeout=5000&" +
         "serverTimezone=UTC");
        
        config.setUsername("u689822279_cyberia");
        config.setPassword("naibedTI.1");

       
      // config.setJdbcUrl("jdbc:mysql://localhost:3306/bd_prueba");
      // config.setUsername("root");
      // config.setPassword(""); 
        
        // Ajustes para hosting compartido (Hostinger)
        config.setMaximumPoolSize(15);  // Más conservador para hosting compartido
        config.setMinimumIdle(3);      // Menos conexiones inactivas
        config.setConnectionTimeout(10000);  // 10 segundos para obtener conexión
        config.setIdleTimeout(30000);  // 30 segundos de inactividad (ajustado para wait_timeout típico de Hostinger)
        config.setMaxLifetime(1800000); // 30 minutos máximo de vida de conexión
        
        // Optimizaciones para MySQL en hosting compartido
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        
        // Configuración de validación más agresiva para hosting compartido
        config.setConnectionTestQuery("SELECT 1");
        config.setValidationTimeout(2500);  // 2.5 segundos para validación
        
        // Detección de fugas más sensible para debugging
        config.setLeakDetectionThreshold(60000);  // 60 segundos para detectar fugas
        
        // Crear el pool de conexiones
        dataSource = new HikariDataSource(config);
    }


    // Método para obtener una conexión usando HikariCP
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }

         public void closeResources(ResultSet rs, PreparedStatement ps, Connection con) {
    try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
}


}
