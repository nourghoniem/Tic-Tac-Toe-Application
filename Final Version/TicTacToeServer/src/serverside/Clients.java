/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverside;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nghon
 */
public class Clients {
    private String username;
    private Socket user_socket;
    private String ip_address;
    static HashMap<String, Clients> clientsMap;
    List<String> getInvitedUsernames = new ArrayList<String>();
    public static List<String> getSignedUpUsers = new ArrayList<String>();
   public Clients(){  
       this.username = "";
       this.user_socket = null;
       this.ip_address = "";  
       clientsMap = new HashMap<String, Clients>();
   }
   public Clients(String username, Socket user_socket, String ip_address){
        this.username = username;
        this.user_socket = user_socket;
        this.ip_address = ip_address;
   }

    public String getUsername() {
        return username;
    }
   
    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getUser_socket() {
        return user_socket;
    }

    public void setUser_socket(Socket user_socket) {
        this.user_socket = user_socket;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
   
    public List<String> getUsernameInvites(String username){
         getInvitedUsernames.add(username); 
         return getInvitedUsernames;  
    }
    public static void addToMap(HashMap<String, Clients> map, String username, Socket socket, String ip){
          Clients c = new Clients(username, socket, ip);
          map.put(username, c);
          clientsMap = map;
          //return clientsMap;
    }
    
  
}
