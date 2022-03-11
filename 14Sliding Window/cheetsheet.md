# 模板
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
# 例题

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
