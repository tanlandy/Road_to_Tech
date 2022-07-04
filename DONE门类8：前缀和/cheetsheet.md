## 基础知识
前缀和本质上是在一个list当中，用O（N）的时间提前算好从第0个数字到第i个数字之和，在后续使用中可以在O（1）时间内计算出第i到第j个数字之和

## Templates
1. [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```Java
// Clarify: will there be all negative nums? Null? Need to return the index?
// Assumption
// Brute force: two for loops
    // first one go from each index: i = 0 to nums.length 
        // int curArray = 0 -> find the maximum subarray start at that index
    // second go from that index: j = i to nums.length
        // curA+=num[j]; maxA=max(curA, maxA)-> update the overallMax
    // Time: O(n^2) Space: O(1)

// DP -> Use it when asked for max or min -> when a negative num is worth keeping -> when the subArray is negative, it's not worth
// curA = max(nums[i], precurA + sum)
// [-2,1,-3,4,-1,2,1] curA = -2, maxA = -2
//  |
// [-2,1,-3,4,-1,2,1] curA = 1, maxA = 1
//     |
// [-2,1,-3,4,-1,2,1] curA = 1, maxA = 1
//        |
// [-2,1,-3,4,-1,2,1] curA = 4, maxA = 4
//          |
// [-2,1,-3,4,-1,2,1] curA = 3, maxA = 4
//             |
// [-2,1,-3,4,-1,2,1] curA = 5, maxA = 5
//               |
// [-2,1,-3,4,-1,2,1]
// [-2,1,-3,4,-1,2,1]

class FindMaxSubarray {
    public int maxSubarray(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int maxS = nums[0];
        int curS = nums[0];
        for (int i = 0; i < nums.length; i++) {
            curS = Math.max(nums[i], nums[i] + curS);
            maxS = Math.max(maxS, curS);
        }
        return maxS;
    }
}
// Time: O(n), Space: O(1)

```
2. [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

```Java
// clarify: k = 0? single element? array is null?
// Brute forth: iterate all subarrays and see if the mod(k) is 0 or not 
    // O(n^2): outer loop i = 0 to n; inner loop: j = i to n

// Iterate once, keep trace of sum of curSubArray
// For e.g. in case of the array [23,2,6,4,7], k = 6, the running sum is [23,25,31,35,42] and the remainders are [5,1,1,5,0]. We got remainder 5 at index 0 and at index 3. That means, in between these two indexes we must have added a number which is multiple of the k.
// current one: sum_i = m*k + modk
// previous one: sum_j = n*k + modk 
// As modk == modk
// Thus, sum_i - sum_j = (m - n) * k -> find it

class CheckArraySum {
    public boolean isSumValid(int[] nums, int k) {
        // map<curSum, index>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//(0, -1) can allow it to return true when the curSum%k=0,
        // In addition, it also avoids the first element of the array is the multiple of k, since 0-(-1)=1 is not greater than 1.
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) { // when == 1, means nums[i] % k == 0
                    return true;
                }
            } else map.put(sum, i);
        }
        return false;
    }
}


```


```