/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import TicTacToeClients.Players;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nghon
 */
public class DatabaseConnection {
    Connection conn;
    ArrayList<Players> players;
    int id;
    String username;
    String password;
    String status;
    String score;
   
    public DatabaseConnection(){
      try{
      DriverManager.registerDriver(new org.postgresql.Driver());
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123nour");
     
      //System.out.print("xbashhjcbjcbsdvg");
     }catch(SQLException e){e.getMessage();}
   }

   public List<Players> getPlayers(){
     players = new ArrayList<Players>();
     try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("select * from players");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      id = rs.getInt("id");
      username = rs.getString("username");
      password = rs.getString("password");
      status = rs.getString("status");
      score = rs.getString("score");
      Players p = new Players(id, username, password, status, score);
      players.add(p);
      //System.out.print(username);
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return players;
   }
   public static void main(String[] args) {
        //ApplicationServer server = new ApplicationServer();
         DatabaseConnection database = new DatabaseConnection();
    }
   
}
