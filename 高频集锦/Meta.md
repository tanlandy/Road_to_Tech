## Top 60
8. [528. Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/) (前缀和，可以先做一下LC53、523)

You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.

You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).

Example
Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

```Java
// clarify with more example: [1, 4] -> larger number has more weights
// edge case [], [0,1]
class PickWithWeight{
    private int[] prefixSums;
    private int totalSum;

    public PickWithWeight(int[] w) { // Time: O(n), Space: O(n)
        // generate prefixSums and totalSum
        this.prefixSums = new int[w.length];
        int curSum = 0;
        for (int i = 0; i < w.length; i++) {
            curSum += w[i];
            prefixSums[i] = curSum;
        }
        this.totalSum = curSum;
    }

    public int pickIndex() { // Time: O(n), Space: O(1)
        // generate target using Math.random() -> [0,1)
        // probability is w[i] / totalSum, which is the same as find the first curSum
        double target = totalSum * Math.random();
        for (int i = 0; i < prefixSums.length; i++) {
            if (target < prefixSums[i]) {
                return i;
            }
        }
        return -1;
    }

    public int pickIndex() { // Time: O(logn) Space: O(1)
        // generate random num [0,1)
        // find the first prefix sum that's larger than target
        // return that index
        double target = this.totalSum * Math.random();
        return binarySearch(target, 0, prefixSums.length);
    }
    
    private int binarySearch(double target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > this.prefixSums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }



}

```
9. [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/)

A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

Input: s = "((("
Output: 3

```Java
// edge case: ")))((("
// clarify: only ()? what is a valid one
class MakeParenthesisValid{
    public int minAddToMakeValid(String s) {
        // left: ( need to add
        // right: ) need to add
        // see a '(': right++
        // see a ')': right--, or left++
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                right++;
            } else if (right > 0) {
                right--;
            } else {
                left++;
            }
        } 
        return left + right;
    }
}
```

10. [408. Valid Word Abbreviation](https://leetcode.com/problems/valid-word-abbreviation/)

A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.

For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)
The following are not valid abbreviations:

"s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
"s010n" (has leading zeros)
"s0ubstitution" (replaces an empty substring)

```Java
// Iterate over the characters of abbr and skip number of characters of word. Then compare ith chracter of word with jth character of abbr.
// when encounter a number: keep going until the end
class CheckValidAbbr{
    public boolean isValid(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) <= '9' && abbr.charAt(j) >= '0') {
                j++;
            }
            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }
}
// Time: O(n) n is word.length()
// Space: O(k) k is size of larget num

```
11. [339. Nested List Weight Sum](https://leetcode.com/problems/nested-list-weight-sum/)
You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.

Return the sum of each integer in nestedList multiplied by its depth.

Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.

```Java
    public int depthSum(List<NestedInteger> nestedList) {
        // recursively solve
        // if isInteger: sum += num * depth
        // if not: go deeper
        return dfs(nestedList, 1);
    }
    
    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger nested : nestedList) {
            if (nested.isInteger()) {
                sum += nested.getInteger() * depth;
            } else {
                sum += dfs(nested.getList(), depth + 1);
            }
        }
        return sum;   
    }
    // Time: O(n), n is size of nestedList
    // Space: O(k), k is largest List


```



## 查缺补漏
### 前缀和Prefix sum
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