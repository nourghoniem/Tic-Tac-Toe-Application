/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverside;


public class Invites {
    private int p1;
    private int p2;
    private String invite_status;
    
    private Invites(){
     this.p1 = 0;
     this.p2 = 0;
     this.invite_status = "";
    }

    public Invites(int p1, int p2, String invite_status) {
        this.p1 = p1;
        this.p2 = p2;
        this.invite_status = invite_status;
    }
     public Invites(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
      
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public String getInvite_status() {
        return invite_status;
    }

    public void setInvite_status(String invite_status) {
        this.invite_status = invite_status;
    }
    
   
}
