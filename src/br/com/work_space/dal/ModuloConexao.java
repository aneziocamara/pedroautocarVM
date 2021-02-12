/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.work_space.dal;

import java.sql.*;

/**
 *
 * @author anezi
 */
public class ModuloConexao {
    // método responsável por restabelecer a conexão com o BD.

    public static Connection conector() {
        java.sql.Connection conexao = null;
        // a linha chama o driver.
        String driver = "com.mysql.jdbc.Driver";
        //  armazenando informações referentes ao BD.
        String url = "jdbc:mysql://localhost:3306/dbpedroautocar";
        String user = "root";
        String password = "";
        // Estabelecendo a conexão com o BD.
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            // a linha abaixo serve de apoio para esclarecer o erro
            //System.out.println(e);
            return null;
        }
    }
}
