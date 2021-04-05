package com.ender.staticproxy;

public class HandShake {
    public static void countPopulation(int shakeSum){
        int temp = shakeSum * 2;
        int up = shakeSum;
        int down = 1;
        int ans = -1;
        while(down < up){
            int mid = (up + down)/2;
            int midSum = (mid-1)*mid;
            if(midSum > temp)
                up = mid -1;
            else if(midSum < temp)
                down = mid + 1;
            else {
                ans = mid;break;
            }
        }
        if(ans == -1)
            System.out.println("没有答案");
        else
            System.out.println(ans);
    }
}
