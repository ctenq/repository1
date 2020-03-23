/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Arrays;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import logic.algorithm.Mstl;
import logic.algorithm.Mstl_ans;

/**
 *
 * @author araki
 */
@Named
@ViewScoped
public class MstlBb implements Serializable {

    private int size = 3;
    private Double[][] coefs;
    private Double[] consts;
    private Double[] result;
    private boolean viewAns;
    private String[] roundedAns;
    Mstl_ans ans;

    {
        coefs = new Double[size][size];
        consts = new Double[size];
        fillzero(coefs, consts);
    }

    public void calc() {
        Double[][] newCoef = new Double[size][size + 1];
        for (int i = 0; i < size; i++) {
            newCoef[i][size] = consts[i];
            for (int j = 0; j < size; j++) {
                newCoef[i][j] = coefs[i][j];
            }
        }

        result = new Double[size];
        Mstl mstl = new Mstl(size, newCoef, result);
        ans = mstl.gauss();
        roundedAns = new String[size];

        if (ans.isB()) {
            for (int i = 0; i < ans.getResult().length; i++) {
                roundedAns[i] = String.format("%.2f", ans.getResult()[i]);
            }
        }

        viewAns = true;
    }

    public void fillzero(Double[][] dd, Double[] d) {
        for (Double[] var : dd) {
            Arrays.fill(var, 0.0);
        }
        Arrays.fill(d, 0.0);
    }

    //リセット
    public void clear() {
        coefs = new Double[size][size];
        consts = new Double[size];
    }

    //値を保持してリサイズ
    public void resize(boolean expnad) {
        Double[][] newCoef = new Double[size][size];
        Double[] newConsts = new Double[size];
        fillzero(newCoef, newConsts);
        if (expnad) {
            for (int i = 0; i < coefs.length; i++) {
                newConsts[i] = consts[i];
                for (int j = 0; j < coefs.length; j++) {
                    newCoef[i][j] = coefs[i][j];
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                newConsts[i] = consts[i];
                for (int j = 0; j < size; j++) {
                    newCoef[i][j] = coefs[i][j];
                }
            }
        }

        coefs = newCoef;
        consts = newConsts;
    }

    //--------------------------------------------------------------------------
    //サイズ変更
    public void expand() {
        if (size < 8) {
            size++;
            resize(true);
        }
    }

    public void reduce() {
        if (size > 2) {
            size--;
            resize(false);
        }
    }

    //--------------------------------------------------------------------------
    //getter and setter
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Double[][] getCoefs() {
        return coefs;
    }

    public void setCoefs(Double[][] coefs) {
        this.coefs = coefs;
    }

    public Double[] getConsts() {
        return consts;
    }

    public void setConsts(Double[] consts) {
        this.consts = consts;
    }

    public Double[] getResult() {
        return result;
    }

    public void setResult(Double[] result) {
        this.result = result;
    }

    public Mstl_ans getAns() {
        return ans;
    }

    public void setAns(Mstl_ans ans) {
        this.ans = ans;
    }

    public boolean isViewAns() {
        return viewAns;
    }

    public void setViewAns(boolean viewAns) {
        this.viewAns = viewAns;
    }

    public String[] getRoundedAns() {
        return roundedAns;
    }

    public void setRoundedAns(String[] roundedAns) {
        this.roundedAns = roundedAns;
    }
}
