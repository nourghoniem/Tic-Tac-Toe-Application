//
//package serverside;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author nghon
// */
//public class DatabaseConnection {
//    Connection conn;
//    ArrayList<Players> players;
//    ArrayList<Players> viewPlayers;
//    String username;
//    String password;
//    String status;
//    String score;
//   
//    public DatabaseConnection(){
//      try{
//      DriverManager.registerDriver(new org.postgresql.Driver());
//      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123nour");
//     
//      //System.out.print("xbashhjcbjcbsdvg");
//     }catch(SQLException e){e.getMessage();}
//   }
//
//   public List<Players> getPlayers(){
//     players = new ArrayList<Players>();
//     try{
//      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//      String queryString = new String("select * from players");
//      ResultSet rs = s.executeQuery(queryString);
//      while(rs.next()){
//      username = rs.getString("username");
//      password = rs.getString("password");
//      status = rs.getString("status");
//      score = rs.getString("score");
//      Players p = new Players(username, password, status, score);
//      players.add(p);
//     }
//       s.close();
//       conn.commit();
//     }catch(SQLException e){e.getMessage();}
//     return players;
//   }
//    public List<Players> viewPlayers(String my_username){
//     viewPlayers = new ArrayList<Players>();
//     try{
//      Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//      String queryString = new String("select * from players where username != "+my_username+" ");
//      ResultSet rs = s.executeQuery(queryString);
//      while(rs.next()){
//      username = rs.getString("username");
//      password = rs.getString("password");
//      status = rs.getString("status");
//      score = rs.getString("score");
//      Players p = new Players(username, password, status, score);
//      viewPlayers.add(p);
//     }
//       s.close();
//       conn.commit();
//     }catch(SQLException e){e.getMessage();}
//     return viewPlayers;
//   }
//   
//   
//   
//   public void insertPlayer(Players p){
//    try{ 
//     PreparedStatement pst= conn.prepareStatement("insert into players (username, password, status, score) values(?,?,?,?)");
//     pst.setString(1, p.getUsername());
//     pst.setString(2, p.getPassword());
//     pst.setString(3, "Offline");
//     pst.setString(4, null);
//     int rows  = pst.executeUpdate();
//     pst.close();
//     System.out.print(rows);
//    }catch(SQLException e){e.getMessage();}
//  }
//    
//   public boolean checkIfExists(String r_username){
//     try{
//       Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//       String queryString = new String("select * from players");
//       ResultSet rs = s.executeQuery(queryString);
//       while(rs.next()){
//          username = rs.getString("username");
//          if(username.equals(r_username)){
//            return true;
//          } 
//       }
//     }catch(SQLException e){e.getMessage();}
//     return false;
//   }
//   public boolean checkIfPlayerExists(String r_username, String r_password){
//     try{
//       Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//       String queryString = new String("select * from players");
//       ResultSet rs = s.executeQuery(queryString);
//       while(rs.next()){
//          username = rs.getString("username");
//          password = rs.getString("password");
//          if(username.equals(r_username) && password.equals(r_password)){
//            return true;
//          } 
//       }
//     }catch(SQLException e){e.getMessage();}
//     return false;
//   }
//
//   public static void main(String[] args) {
//        //ApplicationServer server = new ApplicationServer();
//         DatabaseConnection database = new DatabaseConnection();
//    }
//   
//}
