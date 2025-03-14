package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
//Implementação completa para gerar uma conexao com o banco de dados
public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null){
            // Conectando com o banco
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){
        // Abrir o arquivo db.properties, ler os dados e guardar em um objeto
        // do tipo Properties
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    // Metodos auxiliares para fechar o Statement e o ResultSet
    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
}
