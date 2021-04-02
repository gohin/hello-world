package com.kure.test.leetcode;

import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {2,4,5,7,11};
        Solution solution = new Solution();
        int[] result = solution.twoSum(nums, 9);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public int[] twoSum(int[] nums,int target){
        int[] res = new int[2];
        if(nums == null || nums.length<=1){
            return res;
        }
        //map中的key存放数组中的数字值，val存放对应数字在数组中的索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            int val = target - num;
            if(map.containsKey(val)){
                res[0] = i;
                res[1] = map.get(val);
                return res;
            }else{
                map.put(num, i);
            }
        }
        return res;
    }

}
