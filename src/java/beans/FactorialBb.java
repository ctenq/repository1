/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import logic.algorithm.Factorial;

/**
 *
 * @author araki
 */
@Named
@RequestScoped
public class FactorialBb {
    @NotNull @Min(0) @Max(25)
    private long n;
    private long result;
    private boolean active;
    Factorial f = new Factorial();
    
    public void calc(){
        this.result = f.factorial(n);
        this.active = true;
    }
    
    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
