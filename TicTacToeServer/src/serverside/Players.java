/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverside;


public class Players {

    private String username;
    private String password;
    private String status;
    private String score;
 
     
    public Players() {
        this.username = "";
        this.password = "";
        this.status = "";
        this.score = "";
    }
    public Players(String username, String password, String status, String score) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.score = score;
      
    }

    public static Players usernameStatus(String username, String status) {
       //this.username = username;
      final Players pl = new Players();
      pl.username = username;
      pl.status = status;
      return pl;
    }

    public Players(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setUsernameAndStatus(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    } 
 
}
