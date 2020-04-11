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
public class Factorial {
    public long factorial(long n){
        if(n <= 1) return 1;
        return n * factorial(n - 1);
    }
}
