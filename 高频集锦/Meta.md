## 面经必看
https://www.1point3acres.com/bbs/thread-856086-1-1.html 
LC230
LC3
2月15日完成的Meta OA，70分钟4道题。
整体感觉Meta调整了难度，比年初面经里的题简单一点点。可能是发现70分钟做不完吧哈哈。
分享一下前三题，第四题太长了，我也没有拍照片，感觉说不清楚。
其实遇不到原题的，但大家可以熟悉一下难度。
如果对大家有一点点帮助，求求给加大米呀！
1. num of odd occurrence of zeros

2. 是给一个 array 给定特定的算法要求实现 简单题

3. memory allocate and delete

4. operation array [0,a,b] a‍n‍d [1,a,b] 返回 boolean


---------------------------------------------------------------
1. 给一个字符串，返回所有长度为3的连续子字符串的个数，要求子字符串中char各不相同 (pairwise distinct)。
说白了就是(i, i + 1, i + 2)各不相同的个数。
---------------------------------------------------------------
2. 给一个由数字构成的字符串，比如“111000041289”，我们叫它str_num吧。
    再给一个K。
        - If len(str_num) <= K, return str_num.
        - If len(str_num) > K,
              a. split str_num into sub-strings of size K,
              b. do digit sum and return the result,
              c. replace the original chunk in str_num with the digit sum result.
"13426501197" + K=3
-> "134" + "265" + "011" + "97"
-> "8" + "13" + "2" + "16"
-> "813216"
-> "813" + "216"
-> "12" + "9"
-> "129" return
---------------------------------------------------------------
3. 给一个Matrix，推箱子。
先往左推，再往下推，直到遇到障碍物（”*“），或者遇到其他箱子（”#“）。
        - ”#“：箱子
        - ”."：空地方
        - “*”：障碍物
输入：
[['.', '.', '*', '#', '.'],
['#', '#', '.', '*', '.'],
['.', '.', '.', '.', '*'],
['.', '.', '*', '.', '#'],
['#', '.', '#', '.', '*'],
['.', '.', '.', '.', '.']]
输出：
[['.', '.', '*', '.', '.'],
['.', '.', '.', '*', '#'],
['.', '.', '#', '.', '*'],
['.', '.', '*', '.', '#'],
['.', '.', '.', '.', '*'],
['.', '#', '#', '#', '.']]
---------------------------------------------------------------
4. 一个request parse的题，但是是parse number。可以参考一‍‌‌‌‌‍‍‍‍‍‌‍‌‍‌‍‌‍‌‌下亚麻的那个registration request parse的题。
题老长老长了，没有过所有test case哈哈。
不是很难想，但是要cover所有edge cases感觉也挺要求功力。

codesignal oa
1. num of odd occurrence of zeros
2. 是给一个array 给定特定的算法要求实现 简单题
3. memory allocate and delete
4. operation array [0,a,b] and [1,a,b] 返回boole‍‌‌‌‌‍‍‍‍‍‌‍‌‍‌‍‌‍‌‌an



### 电面
https://www.1point3acres.com/bbs/thread-859864-1-1.html 

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

12. [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

Input: nums = [1,1,1], k = 2
Output: 2

```Java



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

3. [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

Given a roman numeral, convert it to an integer.

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

s = "CMXCIV:
Output: 994
Explanation:
The symbols barely look sorted at all here—from left-to-right we have 100, 1000, 10, 100, 1, 5. Do not panic though, we just need to look for each occurrence of a smaller symbols preceding a bigger symbol. The first, third, and fifth symbols are all smaller than their next symbol. Therefore they are all going to be subtracted from their next.

The first two symbols are CM. This is M - C = 1000 - 100 = 900
The second two symbols are XC. This is C - X = 100 - 10 = 90.
The final two symbols are IV. This is V - I = 5 - 1 = 4.
Like we did above, we add these together. (M - C) + (C - X) + (V - I) = 900 + 90 + 4 = 994.

```Java
// clarification: input always valid?
// larger num always comes earlier
    static Map<Character, Integer> map = new HashMap<>();
    
    static {
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
    }

    public int romanToInteger(String s) {
        // go from left to right
        // if s.charAt(i) < i+1 -> sum -= num
        // else sum += num
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            char c2 = s.charAt(i + 1);
            if (map.get(c) < map.get(c2)) {
                sum -= map.get(c);
            } else {
                sum += map.get(c);
            }
        }
        char cLast = s.charAt(s.length() - 1);
        sum += map.get(cLast);
        return sum;
    }
```

4. [12. Integer to Roman](https://leetcode.com/problems/integer-to-roman/)

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

Given an integer, convert it to a roman numeral.

Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

```Java
// largest symble comes first
// go from left to right, update from largest to next largest
    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
    
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
      
    }

```

### 滑动窗口
#### 模板
```Java
int left = 0, right = 0;

while (right < s.size()) {
    // 增大窗口
    window.add(s[right]);
    right++;

    while (window needs shrink) {
        // 缩小窗口
        window.remove(s[left]);
        left++;
    }
}


```
1、当移动right扩大窗口，即加入字符时，应该更新哪些数据？

2、什么条件下，窗口应该暂停扩大，开始移动left缩小窗口？

3、当移动left缩小窗口，即移出字符时，应该更新哪些数据？

4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？

// 完全体大模板
```Java
class Solution {
    public String minWindow(String s, String t) {
        // map[letter] = count
        int [] map = new int[128];
        // 目标放进去，最终目标是map[c] == 0
        for (char c : t.toCharArray()) {
          map[c]++;
        }
        // 左右指针
        int l = 0, r = 0;
        // 最小串索引
        int minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (r < s.length()) {
            // 增大窗口，放入c1
            char c1 = s.charAt(r);
            if (map[c1] > 0) {
                counter--; // 直到counter是0了就达成目标了
            }
            map[c1]--;
            r++;
            System.out.println("Window is [" + l + ' ' + r + ')');
            // 缩小窗口
            while (counter == 0) { // valid的window
                if (minLen > r - l) {
                    minStart = l;
                    minLen = r - l;
                }
                // 移出c2
                char c2 = s.charAt(l);
                map[c2]++; // 与上面是对称的，要先判断
                if (map[c2] > 0) {
                    counter++;
                }
                l++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
  }
}

```
#### 例题

1. [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)

解答见模板

2. [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

```Java
   public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[128];
        for (char c : s1.toCharArray()) {
            map[c]++;
        }
        int l = 0, r = 0;
        int count = s1.length();
        while (r < s2.length()) {
            // 往右走，更新map
            char c = s2.charAt(r);
            if (map[c] > 0) {
                count--;
            }
            map[c]--;
            r++;
            // 左边走，时机是看窗口大小
            // System.out.println("Window is " + l + " " + r);
            // System.out.println("Count is " + count);
            while (r - l >= s1.length()) { // valid的window条件
                if (count == 0) {
                    return true;
                }
                char c2 = s2.charAt(l);
                map[c2]++;
                if (map[c2] > 0) {
                    count++;
                }
                l++;
            }
        }
        return false;
    }
```
3. [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

```Java
// 和上题一模一样，只是需要一个List<Integer>来记录导出的数据
    public List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[128];
        for (char c : p.toCharArray()) {
            map[c]++;
        }
        int count = p.length();
        int l = 0, r = 0;
        List<Integer> res = new ArrayList<>(); // 唯一不同
        while (r < s.length()) {
            char c1 = s.charAt(r);
            if (map[c1] > 0) {
                count--;
            }
            map[c1]--;
            r++;
            while (r - l >= p.length()) {
                if (count == 0) {
                    res.add(l); // 唯一不同
                }
                char c2 = s.charAt(l);
                map[c2]++;
                if (map[c2] > 0) {
                    count++;
                }
                l++;
            }
        }
        return res;
    }
```

4. [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

Given a string s, find the length of the longest substring without repeating characters.

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

```Java
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int l = 0, r = 0;
        int count = 0;
        while (r < s.length()) {
            // r move to the right
            char c1 = s.charAt(r);
            r++;
            map[c1]++;
            // System.out.println("Window is " + l + ' ' + r);
            while (map[c1] > 1) { // valid window: if duplicate
                // move left to the right
                char c2 = s.charAt(l);
                map[c2]--;
                l++;
            }
            count = Math.max(count, r - l);
        }
        return count;
    }



```

### Binary Search
#### 模板
1. General
``` Java
int binarySearch(int[] nums, int taget) {
    int l = 0, r = ...; // ...是不同的细节
    while(...) { // 看细节
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            l = ...
        } else if (nums[mid] > target) {
            r = ...
        }
    }
    return ...;
}
```
2. 找一个数
``` Java
int binary_search(int[] nums, int target) {
    int left = 0, right = nums.length - 1; 
    while(left <= right) { // 最后一次搜索的是left == right的情况，是[left, right]，因为right定义
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1; // mid已经搜索过了，所以+1
        } else if (nums[mid] > target) {
            right = mid - 1; 
        } else if(nums[mid] == target) {
            // 直接返回
            return mid;
        }
    }
    // 直接返回
    return -1;
}

// 本算法缺陷是不能找到边界的那个数
// 比如说给你有序数组nums = [1,2,2,2,3]，target为 2，此算法返回的索引是 2，没错。但是如果我想得到target的左侧边界，即索引 1，或者我想得到target的右侧边界，即索引 3
```
3. 寻找左侧边界
```Java
int left_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {  // [left, right]
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定左侧边界
            right = mid - 1;
        }
    }
    // 最后要检查 left 越界的情况
    if (left >= nums.length || nums[left] != target)
        return -1;
    return left;
}

```
4. 寻找右侧边界
``` Java
int right_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) { // [left, right]
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定右侧边界
            left = mid + 1;
        }
    }
    // 最后要检查 right 越界的情况
    if (right < 0 || nums[right] != target)
        return -1;
    return right;
}

```

#### 例题
1. [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

```Java
    public int search(int[] nums, int target) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) { // [l, r]
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[l]) { // 第一种情况，mid在左边
                if (nums[l] <= target && target < nums[mid]) { // target在mid左边
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] < nums[l]) { // 第二种情况，mid在右边
                if (nums[mid] < target && target <= nums[r]) {// target在mid右边
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
```


2. [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/)

A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

```Java
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) { // [l, r) 最后一次看 l = r - 1的情况
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) { // not valid, move r to make it smaller
                r = mid;
            } else if (nums[mid] <= nums[mid + 1]) { // valid, move l
                l = mid + 1;
            }
        }
        return l;
    }
```

### 拓扑排序

### Todo
[二分查找子序列](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484479&idx=1&sn=31a3fc4aebab315e01ea510e482b186a&scene=21#wechat_redirect)
[括号相关](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487246&idx=1&sn=4a514020ce9dc8777e2d1d503188b62b&scene=21#wechat_redirect)
[最长回文字串](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484471&idx=1&sn=7c26d04a1f035770920d31377a1ebd42&scene=21#wechat_redirect)
[接雨水](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494095&idx=5&sn=8a69b2ca4d48e8b4db2b153a405c6e52&scene=21#wechat_redirect)
[计算器](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484903&idx=1&sn=184beaad36a71c9a8dd93c41a8ba74ac&scene=21#wechat_redirect)
[DFS岛屿](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247492234&idx=1&sn=fef28b1ca7639e056104374ddc9fbf0b&scene=21#wechat_redirect)
[二叉树](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494655&idx=1&sn=f3445112d7322ea8491073fd2d19f25c&scene=21#wechat_redirect)
