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

## Top 1-10
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

## Top 11-20
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


13. [426. Convert Binary Search Tree to Sorted Doubly Linked List](https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/)

```Java
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node(-1, null, null);
        Node pre = dummy;
        pre = inorderDFS(root, pre);
        pre.right = dummy.right; // 连起来,dummy.right就是head
        dummy.right.left = pre;
        return dummy.right;
    }
    
    private Node inorderDFS(Node node, Node pre) {
        if (node == null) { // 最后为空的时候要返回pre
            return pre;
        }
        pre = inorderDFS(node.left, pre);
        node.left = pre;
        pre.right = node;
        pre = inorderDFS(node.right, node); //这时候node就是pre
        return pre;
    }

```
14. [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)



15. [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

16. [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)


17. [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

```Java

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort by ascending starting point
        Arrays.sort(intervals,
                    new Comparator<int[]>() {
                        public int compare(int[] a, int[] b) {
                            return a[0] - b[0];
                        }
                    }
                );        
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval : intervals) {
            if (newInterval[1] >= interval[0]) { // Overlapping intervals, move the end if needed
            // 如果新的更远，那么就更新一下
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            } else { // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
        
        
    }
}
```

Follow up: How do you add intervals and merge them for a large stream of intervals?


We need to have two functions for the tree (add interval and query tree).

Implementation Details
TreeNode - On top of the left child, right child, start boundary, and end boundary, we have a middle field that determines whether a new interval goes to the left child, right right or merged with the current node.

add - If the new interval touches or crosses the middle of the current node, we update the current node. Otherwise, we put the new interval into the left subtree or right subtree.

Why do we use middle for comparison and not start or end boundaries?
The reason is that we can use merge-sort technique to query the merged intervals result when the left subtree does not overlap with the right subtree.
query - Use merge-sort technique by retrieving the merged intervals of the left subtree (i.e. left_intervals) and those of the right subtree (i.e. right_intervals). Because of the implementation of add, we can guarantee that

if there's an interval in the left_intervals that overlaps with the current node, then we know that all the intervals after that interval overlaps with the current node.
The first few intervals or zero intervals in the right_intervals overlap with the current node.

```Java
public int[][] merge(int[][] intervals) {
		Itnode root=new Itnode(intervals[0][0], intervals[0][1]);
		
		for(int i=1;i<intervals.length;i++) {
			add(root, intervals[i]);
		}
		
		List<int[]> list=merge(root);
		int[][] ans=new int[list.size()][2];
		for(int i=0;i<ans.length;i++) {
			ans[i]=list.get(i);
		}
		return ans;
	}
	
	List<int[]> merge(Itnode root){
		List<int[]> res=new Vector<>();
		if(root==null) return res;
		
		List<int[]> left=merge(root.left);
		List<int[]> right=merge(root.right);
		
		boolean inserted=false;
		for(int[] i:left) {
			if(i[1]>=root.low) {
				inserted=true;
				i[0]=Math.min(i[0], root.low);
				i[1]=Math.max(i[1], root.high);
				root=new Itnode(i[0], i[1]);
			}
			res.add(i);
		}
		
		if(!inserted) res.add(new int[] {root.low,root.high});
		
		for(int[] i:right) {
			if(i[0]<=root.high) {
				inserted=true;
				i[0]=Math.min(i[0], root.low);
				i[1]=Math.max(i[1], root.high);
				res.remove(res.size()-1);
			}
			res.add(i);
		}
		
		return res;
		
	}
	
	Itnode add(Itnode root,int[] i) {
		if(root==null) return new Itnode(i[0], i[1]);
		
		if(i[0]<root.low)
			root.left=add(root.left, i);
		else
			root.right=add(root.right,i);
		return root;
	}
	
	static class Itnode{
		int low,high,max;
		Itnode left,right;
		Itnode(int l,int h){
			this.low=l;this.high=h;this.max=h;
			this.left=this.right=null;
		}
	}
```
## Top 21-30

21. [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)
[1,2,3,4] -> [2,1,4,3]

时间O(n)
空间O(1)
```Java
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode pre = dummy;
    while (pre.next != null && pre.next.next != null) { // 因为second = pre.next.next
    // 从[pre,1,2,3]变成[2,1,3]
        ListNode first = pre.next; // first = 1
        ListNode second = pre.next.next; // second = 2;
        first.next = second.next; // 1 -> 3;
        second.next = first; // 2 -> 1;
        pre.next = second; // pre -> 2;要把2连起来
        pre = first; // pre = 1;
    }
    return dummy.next;
}

```

22. [207. Course Schedule](https://leetcode.com/problems/course-schedule/)
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

思路：
graph记录依赖关系
preNum记录依赖数量
    对于依赖数量为0的，从依赖关系找到课程，更新该课程的依赖数量
[0,1],[2,1],[3,1],[3,2]
graph: from to 
1->0, 2, 3
2->3
preNum: to
0: 1
1: 0
2: 1
3: 2
开始上课
```Java
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        // 建图：几门课graph里就有几个起点
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        // 邻接表：记录依赖数量
        int [] preNum = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            preNum[prerequisites[i][0]]++;
        }
        // 拓扑排序：开始上课
        for (int i = 0; i < numCourses; i++) {
            boolean available = false;
            for (int j = 0; j < numCourses; j++) {
                // 先找入边为0的
                if (preNum[j] == 0) {
                    for (int k : graph.get(j)) {
                        preNum[k]--;
                    }
                    available = true;
                    preNum[j] = -1;
                    break;
                }
            }
            if (!available) {
                return false;
            }
        }
        return true;
    }
```


### Todo
[二分查找子序列](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484479&idx=1&sn=31a3fc4aebab315e01ea510e482b186a&scene=21#wechat_redirect)
[括号相关](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487246&idx=1&sn=4a514020ce9dc8777e2d1d503188b62b&scene=21#wechat_redirect)
[最长回文字串](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484471&idx=1&sn=7c26d04a1f035770920d31377a1ebd42&scene=21#wechat_redirect)
[接雨水](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494095&idx=5&sn=8a69b2ca4d48e8b4db2b153a405c6e52&scene=21#wechat_redirect)
[计算器](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484903&idx=1&sn=184beaad36a71c9a8dd93c41a8ba74ac&scene=21#wechat_redirect)
[DFS岛屿](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247492234&idx=1&sn=fef28b1ca7639e056104374ddc9fbf0b&scene=21#wechat_redirect)
[二叉树](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247494655&idx=1&sn=f3445112d7322ea8491073fd2d19f25c&scene=21#wechat_redirect)
