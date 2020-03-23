/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.algorithm;

/**
 *
 * @author araki
 */
public class L_Edge {
    int x;
    int y;
    int connectx;
    int connecty;
    int cost = 1;
    
    public L_Edge(int y, int x, int connecty, int connectx){
        this.y = y;
        this.x = x;
        this.connecty = connecty;
        this.connectx = connectx;
    }
    
    @Override
    public String toString(){
        return "own : " + y + "," + x + " ,  connect : " + connecty + "," + connectx;
    }
}
