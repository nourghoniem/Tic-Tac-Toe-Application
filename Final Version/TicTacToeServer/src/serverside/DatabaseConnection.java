
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverside;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    Connection conn;
    ArrayList<Players> players;
    ArrayList<Players> viewPlayers;
    int id;
    String username;
    String password;
    String status;
    String score;
   
    public DatabaseConnection(){
      try{
      DriverManager.registerDriver(new org.postgresql.Driver());
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123nour");
    
     }catch(SQLException e){e.getMessage();}
   }

   public List<Players> getPlayers(){
     players = new ArrayList<Players>();
     try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("select * from players");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      username = rs.getString("username");
      password = rs.getString("password");
      status = rs.getString("status");
      score = rs.getString("score");
      Players p = new Players(username, password, status, score);
      players.add(p);
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return players;
   }
    public List<Players> viewPlayers(String my_username){
     viewPlayers = new ArrayList<Players>();
     try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("select * from players where not (username = '"+my_username+"') ");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      username = rs.getString("username");
      password = rs.getString("password");
      status = rs.getString("status");
      score = rs.getString("score");
      Players p = new Players(username, password, status, score);
      viewPlayers.add(p);
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return viewPlayers;
   }
   public List<Players> viewAllPlayers(){
     viewPlayers = new ArrayList<Players>();
     try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("select * from players");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      username = rs.getString("username");
      password = rs.getString("password");
      status = rs.getString("status");
      score = rs.getString("score");
      Players p = new Players(username, password, status, score);
      viewPlayers.add(p);
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return viewPlayers;
   }
   public List<Players> viewOnlinePlayers(String my_username){
     viewPlayers = new ArrayList<Players>();
     try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("select * from players where status = 'Online' AND not (username = '"+my_username+"') ");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      username = rs.getString("username");
      password = rs.getString("password");
      status = rs.getString("status");
      score = rs.getString("score");
      Players p = new Players(username, password, status, score);
      viewPlayers.add(p);
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return viewPlayers;
   }
   public String getLastInserted(){
      try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("SELECT username FROM players WHERE id = ( SELECT MAX(id) FROM players )");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      username = rs.getString("username");
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return username;
   }
    public String getLastInvite(){
      try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("SELECT id FROM invites WHERE id = ( SELECT MAX(id) FROM players )");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      username = rs.getString("username");
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return username;
   }

 
   public void insertPlayer(Players p){
    try{ 
     PreparedStatement pst= conn.prepareStatement("insert into players (username, password, status, score) values(?,?,?,?)");
     pst.setString(1, p.getUsername());
     pst.setString(2, p.getPassword());
     pst.setString(3, "Offline");
     pst.setString(4, "0");
     int rows  = pst.executeUpdate();
     pst.close();
     System.out.print(rows);
    }catch(SQLException e){e.getMessage();} 
  }
  public void insertPlayerScore(String username, String score){
     try{ 
     PreparedStatement pst= conn.prepareStatement("update players set score = 10 + "+score+" where username ='"+username+"'");
     int rows  = pst.executeUpdate();
     pst.close();
     System.out.print(rows);
    }catch(SQLException e){e.getMessage();} 
   }
  public String getScore(String username){
     try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("select score from players where username = '"+username+"'");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
      score = rs.getString("score");
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
     return score;
  }
  public void insertInvites(Invites i){
    try{ 
     PreparedStatement pst= conn.prepareStatement("insert into invites (player1_id, player2_id, invites_status) values(?,?,?)");
     pst.setInt(1, i.getP1());
     pst.setInt(2, i.getP2());
     pst.setString(3, "Pending");
     int rows  = pst.executeUpdate();
     pst.close();
     System.out.print(rows);
    }catch(SQLException e){e.getMessage();} 
   }
  
  public int getIdByUsername(String username){
      try{
      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      String queryString = new String("SELECT id FROM players WHERE username = '"+username+"'");
      ResultSet rs = s.executeQuery(queryString);
      while(rs.next()){
       id = rs.getInt("id");
     }
       s.close();
       conn.commit();
     }catch(SQLException e){e.getMessage();}
    return id;
   }
   
  public void updateStatus(String username){
    try{ 
     PreparedStatement pst= conn.prepareStatement("update players set status = ? where username = '"+username+"'");
     pst.setString(1, "Online");
     int rows  = pst.executeUpdate();
     pst.close();
     System.out.print(rows);
    }catch(SQLException e){e.getMessage();}
 }
    
   public boolean checkIfExists(String r_username){
     try{
       Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
       String queryString = new String("select * from players");
       ResultSet rs = s.executeQuery(queryString);
       while(rs.next()){
          username = rs.getString("username");
          if(username.equals(r_username)){
            return true;
          } 
       }
     }catch(SQLException e){e.getMessage();}
     return false;
   }
   public boolean checkIfPlayerExists(String r_username, String r_password){
     try{
       Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
       String queryString = new String("select * from players");
       ResultSet rs = s.executeQuery(queryString);
       while(rs.next()){
          username = rs.getString("username");
          password = rs.getString("password");
          if(username.equals(r_username) && password.equals(r_password)){
            return true;
          } 
       }
     }catch(SQLException e){e.getMessage();}
     return false;
   }

   
}

