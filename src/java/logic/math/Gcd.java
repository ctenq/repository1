/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.math;

/**
 *
 * @author araki
 */
public class Gcd {
    public int gcd(int x, int y){
        return (x % y == 0) ? y : gcd(y, x % y);
    }
}
