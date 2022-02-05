/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverside;

import java.net.Socket;

/**
 *
 * @author nghon
 */
public class Clients {
    private String username;
    private Socket user_socket;
    private String ip_address;

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
   
}
