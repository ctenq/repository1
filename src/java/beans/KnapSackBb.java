/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import logic.algorithm.KnapSack;

/**
 *
 * @author araki
 */
@Named
@ViewScoped
public class KnapSackBb implements Serializable {

    private int knapsack;
    private List<KSBaggage> bagList;
    private List<KSBaggage> selectedBags;
    private int sumBagsVolume;
    private int sumBagsWorth;
    private int sumAllBagsWorth;
    private boolean ans;

    {
        bagList = new ArrayList<>();
        bagList.add(new KSBaggage(0, 0, 0));
    }

    public void clear() {
        bagList.clear();
        bagList.add(new KSBaggage(0, 0, 0));
        ans = false;
    }

    public void calc() {
        for (int i = 0; i < bagList.size(); i++) {
            bagList.get(i).setNumber(i + 1);
        }

        for (KSBaggage bag : bagList) {
            sumAllBagsWorth += bag.getWorth();
        }

        KnapSack ks = new KnapSack();
        selectedBags = ks.dpCalc(bagList, knapsack);

        for (KSBaggage bag : selectedBags) {
            sumBagsVolume += bag.getVolume();
            sumBagsWorth += bag.getWorth();
        }

        ans = true;
    }

    public void addBaggage() {
        if (bagList.size() < 15) {
            bagList.add(new KSBaggage(0, 0, 0));
        }
    }

    public void removeBaggage(KSBaggage b) {
        if (bagList.size() > 1) {
            bagList.remove(b);
        }
    }

    //--------------------------------------------------------------------------
    //getter and setter
    public int getKnapsack() {
        return knapsack;
    }

    public void setKnapsack(int knapsack) {
        this.knapsack = knapsack;
    }

    public List<KSBaggage> getBagList() {
        return bagList;
    }

    public void setBagList(List<KSBaggage> bagList) {
        this.bagList = bagList;
    }

    public List<KSBaggage> getSelectedBags() {
        return selectedBags;
    }

    public void setSelectedBags(List<KSBaggage> selectedBags) {
        this.selectedBags = selectedBags;
    }

    public int getSumBagsVolume() {
        return sumBagsVolume;
    }

    public void setSumBagsVolume(int sumBagsVolume) {
        this.sumBagsVolume = sumBagsVolume;
    }

    public int getSumBagsWorth() {
        return sumBagsWorth;
    }

    public void setSumBagsWorth(int sumBagsWorth) {
        this.sumBagsWorth = sumBagsWorth;
    }

    public int getSumAllBagsWorth() {
        return sumAllBagsWorth;
    }

    public void setSumAllBagsWorth(int sumAllBagsWorth) {
        this.sumAllBagsWorth = sumAllBagsWorth;
    }

    public boolean isAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }
}
