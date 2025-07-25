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
     //    config.setJdbcUrl("jdbc:mysql://156.67.76.8:3306/u689822279_inventory?" +
     //    "useSSL=false&" +
     //    "autoReconnect=true&" +
     //    "socketTimeout=30000&" +
     //    "connectTimeout=5000&" +
     //    "serverTimezone=UTC");
        
     //  config.setUsername("u689822279_cyberia");
     //  config.setPassword("naibedTI.1");

       
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bd_prueba");
        config.setUsername("root");
        config.setPassword(""); 
        
        // Ajustes para hosting compartido (Hostinger)
        config.setMaximumPoolSize(20);  // Más conservador para hosting compartido
        config.setMinimumIdle(3);      // Menos conexiones inactivas
        config.setConnectionTimeout(10000);  // 10 segundos para obtener conexión
        config.setIdleTimeout(600000); // 10 minutos
        config.setMaxLifetime(1800000); // 30 minutos
        
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
    int maxIntentos = 4;
    int intento = 0;
    while (intento < maxIntentos) {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            intento++;
            System.err.println("Intento " + intento + " fallido al conectar a la BD: " + e.getMessage());

            // Esperar un poco antes de reintentar (por ejemplo, 2 segundos)
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // Buenas prácticas
                throw new RuntimeException("El intento de conexión fue interrumpido", ie);
            }
        }
    }

    // Si llega aquí, es porque falló definitivamente
    throw new RuntimeException("No se pudo obtener una conexión con la base de datos después de " + maxIntentos + " intentos.");
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
