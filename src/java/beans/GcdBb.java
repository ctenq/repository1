/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import logic.algorithm.Gcd;

/**
 *
 * @author araki
 */
@Named
@RequestScoped
public class GcdBb implements Serializable{
    @NotNull @Min(1)
    private int x, y;
    {
        x = y = 1;
    }
    private int result;
    private boolean active;
    Gcd g = new Gcd();
    
    public void calc(){
        int min, max;
        min = (x < y)? x : y;
        max = (x < y)? y : x;
        
        result = g.gcd(max, min);
        
        active = true;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
