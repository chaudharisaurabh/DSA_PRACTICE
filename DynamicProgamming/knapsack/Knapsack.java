package DynamicProgamming.knapsack;

import java.util.*;

public class Knapsack {

    public void diverCode() {
        int n = 5;
        int wt[] = new int[n];
        int[] val = new int[n];
        int W = 10;
        int[][] dp = new int[n + 1][W + 1];

        // Recursive code for knapsack

        System.out.println(knapsackRecursive(wt, val, W, n));
        System.out.println(knapsackMemoized(wt, val, W, n, dp));
        System.out.println(knapsackTabulation(wt, val, W, n, dp));
        

    }
    
    private int knapsackRecursive(int[] wt, int[] val, int W, int n) {

        if (n == 0 || W == 0) {
            return 0;
        }

        if (wt[n - 1] > W) {
            return knapsackRecursive(wt, val, W, n - 1);
        } else {
            return Math.max(
                    knapsackRecursive(wt, val, W, n - 1),
                    val[n - 1] + knapsackRecursive(wt, val, W - val[n - 1], n - 1));
        }

    }
    
    private int knapsackMemoized(int[] wt, int[] val, int W, int n, int[][] dp) {

        if (n == 0 || W == 0) {
            return 0;
        }

        if (wt[n - 1] > W) {
            dp[n][W] = knapsackMemoized(wt, val, W, n - 1, dp);
        } else {
            if (dp[n][W] == -1) {

                dp[n][W] = Math.max(
                        knapsackMemoized(wt, val, W, n - 1, dp),
                        val[n - 1] + knapsackMemoized(wt, val, W - val[n - 1], n - 1, dp));
            }
        }

        return dp[n][W];

    }
    
    private int knapsackTabulation(int[] wt, int[] val, int W, int n, int[][] dp) {

        initializeDP(dp, W, n);
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {

                if(wt[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max( val[i-1] + dp[i-1][j-wt[i-1]],dp[i-1][j] );

                }

            }
        }
        
        return dp[n][W];

    }
    
    private int[][] initializeDP(int[][] dp, int W, int n){
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = -1;
                }
            }
        }

        return dp;

    }
    

    
}
