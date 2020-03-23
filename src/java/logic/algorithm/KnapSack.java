/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.algorithm;

import beans.KSBaggage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author araki
 */
public class KnapSack {
    @SuppressWarnings("SillyAssignment")
    public List<KSBaggage> dpCalc(List<KSBaggage> bagList, int knapsack){
        int quantity = bagList.size();
        long dp[][] = new long[quantity + 1][knapsack + 1];
        int[][] prev_v = new int[quantity + 1][knapsack + 1];
        
        for(int i = 0; i < quantity; i++){
            for(int sum = 0; sum <= knapsack; sum++){
                
                //bagList(i)はi + 1番目の荷物
                KSBaggage b = bagList.get(i);
                if(sum >= b.getVolume()){//そもそも仮容量sumよりi番目の荷物の容量が小さいか
                    
                    //仮容量sumにi番目の荷物を入れたとき,空きはsum - b.getVolume()
                    //その空き容量での最大の価値はdp[i][sum - b.getVolume()]にある
                    if(dp[i][sum] < b.getWorth() + dp[i][sum - b.getVolume()]){
                        dp[i + 1][sum] = b.getWorth() + dp[i][sum - b.getVolume()];
                        prev_v[i + 1][sum] = sum - b.getVolume();
                    }else{
                        dp[i + 1][sum] = dp[i][sum];
                        prev_v[i + 1][sum] = sum;
                    }
                }else{
                    dp[i + 1][sum] = dp[i][sum];
                    prev_v[i + 1][sum] = sum;
                }
            }
        }
        
        int cur_v = knapsack;
        List<KSBaggage> route = new ArrayList<>();
        for(int i = quantity; i > 0; i--){
            KSBaggage b = bagList.get(i - 1);
            if(prev_v[i][cur_v] == cur_v - b.getVolume()){
                route.add(bagList.get(i - 1));
            }
            
            cur_v = prev_v[i][cur_v];
        }
        
        return route;
    }
}
