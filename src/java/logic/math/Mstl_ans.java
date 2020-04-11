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
public class Mstl_ans {
    private Double[] result;
    private String message;
    private boolean b;
    
    public Mstl_ans(Double[] result, String message, boolean b){
        this.result = result;
        this.message = message;
        this.b = b;
    }

    public Double[] getResult() {
        return result;
    }

    public void setResult(Double[] result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
}
