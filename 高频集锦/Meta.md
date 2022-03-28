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


# VO
目标：
3.14 30 - 35
3.15 45 - 55
3.16 60 - 75
3.17 75 - 95
3.18 90 - 120
3.19 105 - 140
3.20 120 - 160
3.21 135 - 180
3.22 150 - 200
3.23 165 - 220
3.24 180 - 240
3.25 每个专题做2道

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

    // [1,3,5] -> [1,4,9]
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

    public int pickIndex() {
        double target = sum * Math.random();
        return binarySearch(target, 0, preSum.length - 1);
    }
    
    private int binarySearch(double target, int l, int r) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (preSum[mid] == target) {
                return mid;
            } else if (preSum[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
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

时间O(n)
空间O(1)
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
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') { // 有一个=0， 因为不能是先为0
                return false;
            }
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) <= '9' && abbr.charAt(j) >= '0') {
                j++;
            }
            int num = Integer.valueOf(abbr.substring(start, j)); // 要走10几步
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



13. [426. Convert Binary Search Tree to Sorted Doubly Linked List](https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/)

inorder每次返回pre的那个node，传进去当前和pre两个node

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



16. [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

时间：O(nlogn)
空间：O(logn) if sorted in place
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
        res.add(newInterval); // 先把第一个给放进去
        for (int[] interval : intervals) {
            if (newInterval[1] >= interval[0]) { // Overlapping intervals, move the end if needed
            // 如果新的更远，那么就更新一下
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            } else { // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]); // 还要处理返回值
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

23. [50. Pow(x, n)](https://leetcode.com/problems/powx-n/)

Input: x = 2.00000, n = 10
Output: 1024.00000

// 第一个方法 O(n)的时间复杂度 O(1)空间
```Java
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++) {
            ans = ans * x;
        }
        return ans;
    }
```

// 第二个方法
思路：
A = x^n
n是偶数：x^2n = A * A
n是奇数：x^2n = A * A * 2
时间O(logn)
空间O(logn)
```Java
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }
        return logPow(x, N);
    }
    private double logPow(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double half = logPow(x, N / 2);
        if (N %2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
```

// 第三个方法
x^(a + b) = x^a * x^b
x^2n = A * A
```Java
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }
        double ans = 1;
        double curX = x;
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 == 1) {
                ans = curX * ans;
            }
            curX = curX * curX;
        }
        return ans;
    }

```
24. [31. Next Permutation](https://leetcode.com/problems/next-permutation/)

A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

暴力解：
时间O(n!)
空间O(1)

时间O(n)
空间O(1)
```Java
// 先找到最右的下降子序列
// 再交换该子序列左边的数，和子序列中刚好比它大的数
// 最后把下降子序列倒序
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return;
        }
        // 从后往前，找到下降序列的起点
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 此时i是最右下降序列的index-1
        if (i >= 0) {
            // 从后往前，找到下降序列中刚好比i大的点，然后交换
            int j = nums.length - 1;
            while (nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 交换下降序列
        reverse(nums, i + 1, nums.length - 1);
    }
    
    // 交换两个点
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // 交换后续下降序列
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
```



1.  [1762. Buildings With an Ocean View](https://leetcode.com/problems/buildings-with-an-ocean-view/)

There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

Input: heights = [4,2,3,1]
Output: [0,2,3]

思路：从右往左走，每次记录最大值，比较最大值和当前值
时间： O(n)
空间： O(n)
```Java
    public int[] findBuildings(int[] heights) {
        int curMax = Integer.MIN_VALUE;
        List<Integer> li = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > curMax) {
                li.add(i);
                curMax = heights[i];
            }
        }
        int[] res = new int[li.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = li.get(li.size() - i - 1);
        }
        return res;
    }
```

27. [263. Ugly Number](https://leetcode.com/problems/ugly-number/)

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return true if n is an ugly number.

Input: n = 6
Output: true
Explanation: 6 = 2 × 3

时间 O(logn)
```Java
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] divisors = new int[]{2, 3, 5};
        for (int d : divisors) {
            while (n % d == 0) {
                n /= d;
            }
        }
        return n == 1;
    }
```

28. [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]

时间O(n)
空间O(1)
```Java
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >=0 && nums1[p1] >= nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
        }  
    }
```

29. [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)
    
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

思路1: 用heap，size总是k，这样堆顶就是kth largest
时间 O(nlogk) 每次放数字需要O(logk)，一共n次
空间 O(K)
```Java
    public int findKthLargest(int[] nums, int k) {
        // min-heap 注意定义方式
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // 堆顶总是kth largest
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }
```

思路2: Quickselect
时间 O(n)
空间 O(1)
```Java
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int j = partition(nums, l, r);
            if (j < k) {
                l = j + 1;
            } else if (j > k) {
                r = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    
    private int partition(int[] nums, int l, int r) {
        int mid = nums[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] <= mid) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, r);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    } 

```


## Top 31-40
31. [1344. Angle Between Hands of a Clock](https://leetcode.com/problems/angle-between-hands-of-a-clock/)

Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

时间O(1)
空间O(1)
```Java
    public double angleClock(int hour, int minutes) {
        // 1min angle: 360 / 60 = 6
        // 1hour angle: 350 / 12 = 30
        // correct 12-hour: (hour mod 12) * 30
        // take into account of min: (hour mod 12 + min/60) * 30
        int perMinAngle = 360 / 60;
        int perHourAngle = 360 / 12;
        
        double minAngle = minutes * perMinAngle;
        double hourAngle = (hour % 12 + minutes / 60.00) * perHourAngle;
        
        double diff = Math.abs(hourAngle - minAngle);
        return Math.min(diff, 360 - diff);
    }

```

32. [398. Random Pick Index](https://leetcode.com/problems/random-pick-index/)

Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the array nums.
int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.

Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.

```Java
    private int[] nums;
    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        // pick the target, return its index
        int count = 0;
        int index = -1;
        for (int i = 0; i < this.nums.length; i++) {
            if (this.nums[i] == target) {
                count++;
                // choose the current num with 1 / count probability
                // rand.nextInt(5) return [0, 5)
                // 最后一个假设count = 10，这时候拿它的概率就是1/10
                if (rand.nextInt(count) == 0) {
                    index = i;
                }
            }
        }
        return index;
    }
```

33. [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)

Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

方法一：大小顶堆
小堆的数字都比大顶堆大
每次先到minHeap，再到maxHeap，同时均衡size
如果最后奇数（minHeap.size()更大)就弹出
如果是偶数就取平均

时间：O(logn) add O(1) find
空间：O(n)
```Java
class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
 
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        // 大顶堆是倒着建立的
        maxHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);
    }
 
    public void addNum(int num) {
        // 先到minHeap，再到maxHeap，大顶堆的所有数字都比小顶堆小
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());
 
        // 均衡
        if(minHeap.size()<maxHeap.size()){
            minHeap.offer(maxHeap.poll());
        }
    }
 
    public double findMedian() {
        // 奇数
        if(minHeap.size() > maxHeap.size()){
            return minHeap.peek();
        }else {// 偶数
            return (minHeap.peek()+maxHeap.peek())/2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

Follow-up
1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?

We can maintain an integer array of length 100 to store the count of each number along with a total count. Then, we can iterate over the array to find the middle value to get our median.

Time and space complexity would be O(100) = O(1).

2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

In this case, we need an integer array of length 100 and a counter for these numbers that are not in [0,100].

I dont' think we need hashmap.
As 99% is between 0-100. So can keep a counter for less_than_hundred and greater_than_hundred.



## 3.15 
16. [721. Accounts Merge](https://leetcode.com/problems/accounts-merge/)

Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

如果数据不是实时变化，本类问题可以用BFS或者DFS的方式遍历，如果数据实时变化（data stream）则并查集每次的时间复杂度可以视为O（1）；需要牢记合并与查找两个操作的模板

方法一： DFS
Here N is the number of accounts and K is the maximum length of an account.
时间：O(NKlogNK) 所有email都是同一个人的名下
空间：O(NK) adjList O(NK) visited O(NK) DFS O(NK)


```Java
// 1. 建adjList，对每个用户，name -> emails
// 2. 对每个用户，从第一个点开始DFS
// 3. 在DFS时候，存每个信息
// 4. DFS结束时候，sort并且连到用户上
// 5. 把用户按要求存起来
    public List<List<String>> accountsMerge3(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // step 1: build graph that connects all emails have relationships
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                graph.putIfAbsent(account.get(i), new HashSet<>());
                emailToName.put(account.get(i), name);
                if (i != 1) {
                    // email相互连起来
                    graph.get(account.get(i)).add(account.get(i - 1));
                    graph.get(account.get(i - 1)).add(account.get(i));
                }
            }
        }

        // step 2: DFS traversal to traverse all nodes in every single component and generate each result list individually
        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                visited.add(email);
                List<String> newList = new ArrayList<>();
                dfs(newList, graph, visited, email);
                Collections.sort(newList);
                newList.add(0, emailToName.get(newList.get(0)));
                result.add(newList);
            }
        }
        return result;
    }

    public void dfs(List<String> result, Map<String, Set<String>> graph, Set<String> visited, String curPoint) {
        result.add(curPoint);
        Set<String> neighbors = graph.get(curPoint);
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                dfs(result, graph, visited, neighbor);
            }
        }
    }

```


方法二：Union Find
```
```

17. [323. Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)

You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

思路一：DFS
无向图中每个顶点都可以搜索到达
每个图执行一次DFS搜索

Here E = Number of edges, V = Number of vertices.
时间：O(E+V)     ->    邻接表O(E)，DFS O(E+V)
空间：O(E+V)     ->   邻接表O(E)，visited O(V), DFS O(V)
```Java
// 1. 建立邻接表，每个顶点都存上相连的顶点，买个顶点->相关的点
// 2. visited记录走过的顶点
// 3. counter记录数量
// 4. DFS遍历
    public int countComponents(int n, int[][] edges) {
        // n是顶点数量vertices
        int count = 0;
        List<Integer>[] adjList = new ArrayList[n]; // 注意右边的构造
        // 建空表
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 表放数据 [1,2] -> 1连2， 2连1
        for (int i = 0; i < edges.length; i++) {
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }
        // visited记录这个点走过没有
        boolean[] visited = new boolean[n];
        // DFS遍历，每个点都执行一次//实际上是每个图执行一次
        for (int i = 0; i < n; i++) {
            // 如果没走过
            if (!visited[i]) {
                count++;
                dfs(adjList, visited, i);
            }
        }
        return count;
    }
    
    private void dfs(List<Integer>[] adjList, boolean[] visited, int start) {
        visited[start] = true;
        // 选中一个点之后，把邻接表中和该点有联系的所有点都走一遍
        for (int i = 0; i < adjList[start].size(); i++) {
            // 如果和这个点有联系的点还没走过
            if (!visited[adjList[start].get(i)]) {
                // 走那个有联系的点
                dfs(adjList, visited, adjList[start].get(i));
            }
        }
    }
```

思路二：Union find

时间复杂度: O(V + ElogV).

Iterating over every edge requires O(E)O(E) operations, and for every operation, we are performing the combine method which is O(α(n))O(α(n)), where α(n) is the inverse Ackermann function.

空间复杂度: O(V)
```Java
// 1. count = n
// 2. 一边遍历节点，一边combine。如果见过就跳过，如果没见过，count--
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int components = n;
        for (int[] e : edges) {
            int p1 = findParent(parent, e[0]);
            int p2 = findParent(parent, e[1]);
            if (p1 != p2) {
                parent[p1] = p2; // Union 2 component
                components--;
            }
        }
        return components;
    }
    private int findParent(int[] parent, int i) {
        if (i == parent[i]) return i;
        return parent[i] = findParent(parent, parent[i]); // Path compression O(logV)
    }
```

## 3.16

18. [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/)

A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
You must write an algorithm that runs in O(log n) time.

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.

思路一（不满足时间复杂度）：
linear scan 时间复杂度O(n)，，空间O(1)

思路二（binary search)
时间O(logn)
空间O(1)
```Java
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) { // [)条件，因为不会是最后一个
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // 不能mid + 1，因为用到了mid + 1
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l; // 最后返回l
    }
```

19. [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

```Java
class Solution {
    private int dir[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}};

    public int shortestPathBinaryMatrix(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        if(grid[0][0]==1 || grid[m-1][n-1]==1) {
            return -1;
        }

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});

        int ans=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] pop = queue.remove();
                if(pop[0]==m-1 && pop[1]==n-1) {
                    return ans+1;
                }
                for (int k=0;k<8;k++) {
                    int nextX = dir[k][0]+pop[0];
                    int nextY = dir[k][1]+pop[1];

                    if(nextX>=0 && nextX<m && nextY>=0 && nextY<n && !visited[nextX][nextY] && grid[nextX][nextY]==0) {
                        queue.add(new int[]{nextX,nextY});
                        visited[nextX][nextY]=true;
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}
```


# 查缺补漏
## 3.16
1. [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates/)

BFS图的遍历

思路：Push all gates into queue first. Then for each gate update its neighbor cells and push them to the queue.

Repeating above steps until there is nothing left in the queue.

```Java
public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = m == 0 ? 0 : rooms[0].length;
        int[][] dirs = {{-1,0}, {0,1}, {0,-1}, {1,0}};
        Queue<int[]> queue = new LinkedList<>();
        // add all gates to the queue
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i,j});
                }
            }
        }
        // update distance from gates
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] dir: dirs) {
                int X = curPos[0] + dir[0];
                int Y = curPos[1] + dir[1];
                if (X<0 || Y <0 || X >= m || Y >= n || rooms[X][Y] != Integer.MAX_VALUE) continue;
                rooms[X][Y] = rooms[curPos[0]][curPos[1]]+1;
                queue.offer(new int[] {X, Y});
            }
        }
    }

```

## 3.19
1. [148. Sort List](https://leetcode.com/problems/sort-list/)
Top down Merge Sort排序链表
思路：切段，然后先排左边，再排右边，最后合并
时间O(nlogn)
空间O(logn)
```Java
// merge sort: O(nlogn)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) // 不要忘了边界条件
            return head;
            
        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 这时候prev.next = slow
        prev.next = null; // 把前后切断了

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // 用dummy来存头的位置
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }
```

2. [179. Largest Number](https://leetcode.com/problems/largest-number/)

Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

Input: nums = [3,30,34,5,9]
Output: "9534330"

思路：Sorting via Custom Comparator
First, we convert each integer to a string. Then, we sort the array of strings.

edge case: when the array consists of only zeroes, so if the most significant number is 00, we can simply return 00. Otherwise, we build a string out of the sorted array and return it.

时间：O(nlogn) 排序
空间：O(n) allocate space for final return string

```Java

    public String largestNumber(int[] nums) {
        // 数字转String[]
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }
        // String排序
        Arrays.sort(asStrs, 
                   new Comparator<String>() {
                       public int compare(String a, String b) {
                           return (b + a).compareTo(a + b);
                       }
                   }
                   );
        // edge case
        if (asStrs[0].equals("0")) {
            return "0";
        }
        // String[]转String
        String largetNum = new String();
        for (String s : asStrs) {
            largetNum += s;
        }
        return largetNum;
    }

```

3. [75. Sort Colors](https://leetcode.com/problems/sort-colors/)

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

方法一：统排
思路：数一遍然后输出
时间：O(n)
空间：O(1)
```Java
    public void sortColors(int[] nums) {
        int[] count = {0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        for (int i = 0; i < count[0]; i++) {
            nums[i] = 0;
        }
        for (int i = count[0]; i < count[0] + count[1]; i++) {
            nums[i] = 1;
        }
        for (int i = count[0] + count[1]; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
```

方法二：Two Pointers
时间：O(n)
空间：O(1)
```Java
    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int i = 0;
        while (i <= r) { // 注意终止条件
            if (nums[i] == 0) { // l, i一起走
                nums[i] = nums[l];
                nums[l] = 0;
                l++;
                i++;
            } else if (nums[i] == 2) { // 只有r走
                nums[i] = nums[r];
                nums[r] = 2;
                r--;
            } else {
                i++;
            }
        }
    }
```


3. [426. Convert Binary Search Tree to Sorted Doubly Linked List](https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/)

思路：
Inorder traversal, sorted asending. 每次更新最后一个节点。
需要记录最开始和最后信息
Processing here is basically to link the previous node with the current one, and because of that one has to track the last node which is the largest node in a new doubly linked list so far.
One more detail : one has to keep the first, or the smallest, node as well to close the ring of doubly linked list. 
```Java
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node(-1, null, null); // 最开始
        Node pre = dummy; // 最新节点
        pre = inorderDFS(root, pre); // 记得这个函数有返回值
        pre.right = dummy.right; // 全部结束，首位连起来,dummy.right就是head
        dummy.right.left = pre;
        return dummy.right;
    }
    
    private Node inorderDFS(Node node, Node pre) {
        if (node == null) { // 最后node为空的时候要返回pre
            return pre;
        }
        pre = inorderDFS(node.left, pre);
        node.left = pre;
        pre.right = node;
        pre = inorderDFS(node.right, node); //这时候node就是pre
        return pre;
    }
```


4. [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)

思路一：
An intuitive solution is to keep a hash table for each node in the list, via which we just need to iterate the list in 2 rounds respectively to create nodes and assign the values for their random pointers. As a result, the space complexity of this solution is O(N), although with a linear time complexity.
用HashMap记录换过的，然后不断递归更新

时间：O(n)
空间：O(n)
```Java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
public class Solution {
  // HashMap which holds old nodes as keys and new nodes as its values.
  HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
  public Node copyRandomList(Node head) {

    if (head == null) {
      return null;
    }

    // If we have already processed the current node, then we simply return the cloned version of it.
    if (this.visitedHash.containsKey(head)) {
      return this.visitedHash.get(head);
    }

    // Create a new node with the value same as old node. (i.e. copy the node)
    Node node = new Node(head.val, null, null);

    // Save this value in the hash map. This is needed since there might be
    // loops during traversal due to randomness of random pointers and this would help us avoid them.
    this.visitedHash.put(head, node);

    // Recursively copy the remaining linked list starting once from the next pointer and then from the random pointer.
    node.next = this.copyRandomList(head.next);
    node.random = this.copyRandomList(head.random);

    return node;
  }
}
```

思路二：
The idea is to associate the original node with its copy node in a single linked list. In this way, we don't need extra space to keep track of the new nodes.

The algorithm is composed of the follow three steps which are also 3 iteration rounds.

1. Iterate the original list and duplicate each node. The duplicate of each node follows its original immediately.
2. Iterate the new list and assign the random pointer for each duplicated node.
3. Restore the original list and extract the duplicated nodes.

```Java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        // [1,2,3,4]
        // [1,1',2,2',3,3',4,4']
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        
        // random加上
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        
        // 分隔开
        // 奇数偶数
        cur = head;
        Node copyHead = head.next;
        Node copy = copyHead;
        while (copy.next != null) { // 这个条件不要忘
            cur.next = cur.next.next;
            cur = cur.next;
            copy.next = copy.next.next;
            copy = copy.next;
        }
        cur.next = cur.next.next; // 这句不要忘
        return copyHead;
    }
}
```

5. [708. Insert into a Sorted Circular Linked List](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/)

思路：3个可能性
时间：O(n)
空间：O(1)
```Java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }
        Node cur = head;
        // case1: [1,2,4], insert 3
        // case2: [3,5,7], insert 2 or 8
        // case3: [1,1,1], insert 1
        while (cur.next != head) {
            if (cur.val <= cur.next.val) {
                if (cur.val <= insertVal && insertVal <= cur.next.val) { // case 1, 3
                    break;
                }
            } else { 
                if (insertVal >= cur.val || insertVal <= cur.next.val) { // case 2
                    break;
                }
            }
            cur = cur.next;
        }
        insertNode.next = cur.next;
        cur.next = insertNode;
        return head;
    }  
}
```

6. [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

```Java
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // find the Nth node
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy.next; // cur = head算长度
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        int delete = length - n;
        cur = dummy;     // cur = dummy确定删除的点
        while (delete > 0) {
            cur = cur.next;
            delete--;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }
```

7. [116. Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)

思路：
横向遍历，在中间的时候加，走到最后就不要加
时间：O(n)
空间：O(n)
```Java
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }
```

思路二：
站在上一层，操作下一层
时间：O(n)
空间：O(1)
```Java
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node levelStart = root;
        while (levelStart.left != null) {
            Node head = levelStart;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) { // 如果右边有东西的话
                    head.right.next = head.next.left;
                }
                head = head.next; // 走到右边
            }
            levelStart = levelStart.left; // 走到下一层
        }
        return root;
    }
```

8. [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)

9. [109. Convert Sorted List to Binary Search Tree](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/)


## 3.20
1. [1428. Leftmost Column with at Least a One](https://leetcode.com/problems/leftmost-column-with-at-least-a-one/)

方法一：
每行一个二分查找最左出现，然后更新
时间：O(NlogM) N:row, M:cols
空间：O(1)
```Java
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int smallestIndex = cols;
        for (int row = 0; row < rows; row++) {
            int l = 0;
            int r = Math.min(smallestIndex, cols - 1); // 缩小查找范围
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (binaryMatrix.get(row, mid) == 0) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (l < cols && binaryMatrix.get(row, l) == 1) { // 排除left走到最右边的情况，这时候target>所有
                smallestIndex = Math.min(smallestIndex, l);
            }
        }
        return smallestIndex == cols ? -1 : smallestIndex;
    }
}
```

方法二：
从右上往左下走：
是0就往下走
是1就往左走
时间：O(N+M)
空间：O(1)

```Java
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        
        int curRow = 0;
        int curCol = cols - 1;
        
        while (curRow < rows && curCol >= 0) { // 走到左下角
            if (binaryMatrix.get(curRow, curCol) == 0) { // 是0就往下
                curRow++;
            } else { // 是1就往左
                curCol--;
            }
        }
        // 如果还是curCol == cols - 1，就说明走到右下角还是没找到
        // 返回的时候是curCol + 1，因为最后一次找到的时候curCol--
        return (curCol == cols - 1) ? -1 : curCol + 1;
    }
}
```
2. [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) 复习了sliding window之后再看

Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


3. [1891. Cutting Ribbons](https://leetcode.com/problems/cutting-ribbons/)

思路：找最右侧边界。一个一个往右尝试
时间：O(Nlog(max(Length))) 
空间：O(1)
```Java
class Solution {
    public int maxLength(int[] ribbons, int k) {
        int max = Integer.MIN_VALUE;
        for (int num : ribbons) {
            max = Math.max(num, max);
        }
        int l = 1;
        int r = max;
        while (l <= r) { // 左闭右闭
            int mid = l + (r - l) / 2;
            if (isValid(ribbons, k, mid)) {                             
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (r < 0) { // 右侧边界走到最左边的情况
            return 0;
        }
        return r;
    }
    
    private boolean isValid(int[] ribbons, int k, int mid) {
        int count = 0;
        for (int num : ribbons) {
            count += num / mid;
        }
        return count >= k; // 满足的情况
    }
}
```


4. [1011. Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)

思路：找最左侧边界
上界是sum
下界是max value
```Java
    public int shipWithinDays(int[] weights, int days) {
        int l = 0;
        int r = 0;
        for (int num : weights) { // upper bound, lower bound
            l = Math.max(l, num);
            r += num;
        }
        while (l <= r) {  // 找最左侧边界
            int mid = l + (r - l) / 2;
            if (isValid(weights, days, mid)) { // 满足的话，移动r，找最左侧边界
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l; // 最左侧边界
    }
    
    private boolean isValid(int[] weights, int days, int mid) {
        int needDays = 1;
        int cur = 0;
        for (int w: weights) {
            if (cur + w > mid) {
                needDays += 1;
                cur = 0;
            }
            cur += w;
        }
        return needDays <= days; // 满足的情况
    }

```

5. [1539. Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/)

Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

思路：
arr[idx]的missing个数是arr[idx] - 1 - idx
找到missing个数包括k的，然后往右加差的树
[2,3,4,7,11], k = 5
点11: missing = 11 - 1 - 4 =  6
点7：missing = 7 - 1 - 3 = 3
要返回的点7 + 5 - 3 = 9
二分查找找到7，返回7+k-3
        // missing of arr[i] = arr[i] - i - 1
        // find the arr[i] < k < arr[i + 1]
        // return arr[i] + k - (arr[i] - i - 1) = k + 1 + i
```Java

    public int findKthPositive(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // arr[idx] 位置上missing是 arr[idx] - idx - 1.
            // If number of positive integer which are missing before arr[mid] is less than k --> continue to search
            if (arr[mid] - mid - 1 < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        // 最后返回arr[right]位置上的missing，即是 arr[right] - right - 1
        // return arr[r] + k - (arr[r] - r - 1);        
        return l + k;
    }


```

6. [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water/) BFS图遍历+堆；之后再看


7. [1060. Missing Element in Sorted Array](https://leetcode.com/problems/missing-element-in-sorted-array/)
Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k, return the kth missing number starting from the leftmost number of the array.

Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.

思路：
nums[i]之前的missing个数是nums[i] - nums[0] - i
找到nums[i] < k < nums[i+1]的missing个数的位置
返回nums[i] + k - (nums[i] - nums[0] - i) = k + nums[0] + i
```Java

    public int missingElement(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] - nums[0] - mid < k) {
                l = mid + 1; 
            } else {
                r = mid - 1;
            }
        }
        return k + nums[0] + r;
    }

```

8. [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

```Java

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        // find left most
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        int[] res = new int[2];
        res[0] = l;
        if (l >= nums.length || nums[l] != target) {
            res[0] = -1;
        }
        
        // find right most
        l = 0;
        r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                l = mid + 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        res[1] = r;
        if (r < 0 || nums[r] != target) {
            res[1] = -1;
        }
        return res;
    }
}

```

9. [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)

```Java
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[l]) { // 判断出来左边sorted
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
```

10. [81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

```Java
    public boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > nums[l] || nums[mid] > nums[r]) { // 判断左边是sorted，与上题的判断方式有所区别
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] < nums[l] || nums[mid] < nums[r]) { // 右边是sorted
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else { // 唯一区别，考虑都是相同点的情况
                l++;
            }
        }
        return false;
    }   
```

11. [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) 378的前序题目

思路一：看作array，取数字的时候就是matrix[mid / cols][mid % cols]
时间：O(logmn)
```Java
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0, rows = matrix.length, cols = matrix[0].length;
        int end = rows * cols - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid / cols][mid % cols] == target) {
                return true;
            } 
            if (matrix[mid / cols][mid % cols] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
```

思路二：分别按照行、列，用2次二分查找
时间：O(logm) + O(logn)
```Java
// TL, DR

```


12.  [378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) 做完LC74，复习Heap之后，看掌握2种方法

Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

思路：

```Java

```

13. [825. Friends Of Appropriate Ages](https://leetcode.com/problems/friends-of-appropriate-ages/)


14. [297](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)

```Java
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) { // 不能少
            return "";
        }
        // use queue to serialize
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root); // 把根节点放进来
        while (!queue.isEmpty()) { // 一次走一个节点
            TreeNode node = queue.poll();
            if (node == null) { // 如果是空，就放进来空，然后跳过这个节点
                sb.append("null ");
                continue;
            }
            sb.append(node.val + " ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString();
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") { // 不能少
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        String[] values = data.split(" ");                          // 先搞一个数组存起来
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));  // String转成数字的方法
        queue.offer(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode top = queue.poll();
            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                top.left = left;
                queue.offer(left);
            }
            if (!values[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                top.right = right;
                queue.offer(right);
            }
        }
        return root;
        
    }
```

15. [314. Binary Tree Vertical Order Traversal](https://leetcode.com/problems/binary-tree-vertical-order-traversal/) DONE

Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

思路：
Queue存<node, col>
用一个HashMap<col, oneRes>
遍历的时候，更新HashMap
最后用HashMap来导出，但是不知道最小值最大值，所以实时更新一下
时间：O(N)
空间：O(N)

```Java
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // HashMap<col, oneRes>
        // Queue<TreeNode, col>
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>(); // 注意如何定义
        Map<Integer, ArrayList> map = new HashMap<>();
        int col = 0;
        int minCol = 0;
        int maxCol = 0;
        queue.offer(new Pair(root, col)); // 注意如何offer pair
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> top = queue.poll();
                TreeNode node = top.getKey();
                col = top.getValue();
                if (!map.containsKey(col)) {
                    map.put(col, new ArrayList<>());
                }
                map.get(col).add(node.val);
                if (node.left != null) {
                    queue.offer(new Pair(node.left, col - 1)); // 加左边
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, col + 1)); // 加右边
                }
                minCol = Math.min(minCol, col); // 为了帮助最后的导出
                maxCol = Math.max(maxCol, col);
            }                               
        }
        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }
```

16. [987. Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/)

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

思路：
与上一题唯一不同就是需要排个序
方法就是每一层用一个rowMap，把rowMap排序好之后再放进大的map里。因此都不需要minmaxCol，
```Java
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // HashMap<col, oneRes>
        // Queue<TreeNode, col>
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>(); // 注意如何定义，注意注意是Queue<Pair<TreeNode, Integer>>
        Map<Integer, ArrayList> map = new HashMap<>();
        int col = 0;
        int minCol = 0;
        int maxCol = 0;
        queue.offer(new Pair(root, col)); // 注意如何offer pair
        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, ArrayList> rowMap = new HashMap<>(); // 唯一不同，每一层新建一个rowMap帮助排序
            
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> top = queue.poll();
                TreeNode node = top.getKey();
                col = top.getValue();
                if (!rowMap.containsKey(col)) {
                    rowMap.put(col, new ArrayList<>());
                }
                rowMap.get(col).add(node.val);
                
                if (node.left != null) {
                    queue.offer(new Pair(node.left, col - 1)); // 加左边
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, col + 1)); // 加右边
                }
                minCol = Math.min(minCol, col); // 为了帮助最后的导出
                maxCol = Math.max(maxCol, col);
            }
            
            for (int key : rowMap.keySet()) { // 唯一不同，排序之后加到map里面
                if (!map.containsKey(key)) { // 没有的话加进来
                    map.put(key, new ArrayList<>());
                }
                ArrayList tmp = rowMap.get(key);  // 提取出来oneRes
                Collections.sort(tmp);
                map.get(key).addAll(tmp);     // 注意addAll方法
            }                                      
        }
        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }
```

## 3.22
1. [339. Nested List Weight Sum](https://leetcode.com/problems/nested-list-weight-sum/)

Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.

方法一：BFS
时间：O(n)
空间：O(n)

```Java
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList); // 注意这个addAll方法
        int depth = 1;
        int count = 0;
        while (!queue.isEmpty()) { // 一次走一层
            int size = queue.size();
            for (int i = 0; i < size; i++) { // 一次走一个点
                NestedInteger nest = queue.poll();
                if (nest.isInteger()) {
                    count += depth * nest.getInteger();
                } else {
                    queue.addAll(nest.getList());
                }
            }
            depth++;
        }
        return count;
    }
```

2. [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

思路：BFS level order遍历，当走到最右的时候，更新res
```Java
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;        
        }
        queue.offer(root);     
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;      
    }
```

思路二：DFS
先走右边，再走左边，当res.size() == level的时候说明是第一次遇见，就添加进去
     1
   /   \
  2     3
 / \   /
4   5 6 
   /
  7
node        1    3      6          2        5       7        4
res         []  [1]   [1,3]     [1,3,6]  [1,3,6] [1,3,6]  [1,3,6,7]
res.size()  0    1      2          3        3       3        4
level       0    1      2          1        2       3        2
res         [1]  [1,3] [1,3,6]  [1,3,6]  [1,3,6] [1,3,6,7] [1,3,6,7]
```Java
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode node, int level, List<Integer> res) {
        if (res.size() == level) {
            res.add(node.val);
        }
        
        if (node.right != null) { // 先看右边
            dfs(node.right, level + 1, res);
        }
        
        if (node.left != null) {
            dfs(node.left, level + 1, res);
        }
        return;
    }


```

# 3.22
1. [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)

思路：
用一个comparator，排序之后一个一个比较
时间：O(NlogN) 排序用到的时间
空间：O(1)
```Java
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, 
                   new Comparator<int[]>() {
                       public int compare(int[] a, int[] b) {
                           return a[0] - b[0];
                       }
                   });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i + 1][0] < intervals[i][1]) {
                return false;
            }
        }
        return true;
    }
```

2. [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

思路：
用一个minHeap存储当前的end值，每次遇见一个新的meeting，和最小值来比较，最后minHeap的size就是room数量

时间：O(NlogN)
空间：O(N)

```Java

    public int minMeetingRooms(int[][] intervals) {
        // 先sort一下intervals
        Arrays.sort(intervals,
                   new Comparator<int[]>() {
                       public int compare(int[] a, int[] b) {
                           return a[0] - b[0];
                       }
                   });
        // 用minHeap，把end放在堆顶，每次都更新end
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(
            intervals.length,                               // 注意初始化方式
            new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {  // 注意这里用的是Integer
                    return a - b;
                }
            });
        
        minHeap.add(intervals[0][1]);                       // 先放进去第一个end
        for (int i = 1; i < intervals.length; i++) {       
            if (intervals[i][0] >= minHeap.peek()) {        // 如果不冲突，就可以释放一个房间
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);                   // 每次都更新end值
        }
        return minHeap.size();
    }

```

3. [273. Integer to English Words](https://leetcode.com/problems/integer-to-english-words/)

```Java

    private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num);
    }
    
    private String helper(int num) {
        String res = new String();
        if (num < 10) {
            res = belowTen[num];
        } else if (num < 20) {
            res = belowTwenty[num - 10];
        } else if (num < 100) {
            res = belowHundred[num / 10] + " " + helper(num % 10);
        } else if (num < 1000) {
            res = helper(num / 100) + " Hundred " + helper(num % 100); // 1000 以下用百计数
        } else if (num < 1000000) {
            res = helper(num / 1000) + " Thousand " + helper(num % 1000);
        } else if (num < 1000000000) {
            res = helper(num / 1000000) + " Million " + helper(num % 1000000);
        } else {
            res = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
        }
        return res.trim(); // 把开头结尾的空格给删掉
    }

```


4. [332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/)

First keep going forward until you get stuck. That's a good main path already. Remaining tickets form cycles which are found on the way back and get merged into that main path. By writing down the path backwards when retreating from recursion, merging the cycles into the main path is easy - the end part of the path has already been written, the start part of the path hasn't been written yet, so just write down the cycle now and then keep backwards-writing the path.

```Java

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }

```

# 3.23

1. [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)

Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]


思路：

"aab" 
"a"       |"aa" | "aab"
"a", "ab" |"b"
"b"

时间：O(2^N) 当所有的substrings都是palindrome
空间：O(N) n is s.length()
```Java

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, s, 0, new ArrayList<String>());
        return res;
    }
    private void dfs(List<List<String>> res, String s, int start, List<String> oneRes) {
        if (s.length() == start) {
            res.add(new ArrayList<>(oneRes));
            return;
        }

        for (int end = start; end < s.length(); i++) { 
            String curS = s.substring(start, end + 1);       
            if (isValid(curS)) {
                oneRes.add(curS);
                dfs(res, s, end + 1, oneRes);
                oneRes.remove(oneRes.size() - 1);
            }
        }
    }
    private boolean isValid(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


```


1. [415. Add Strings](https://leetcode.com/problems/add-strings/)

Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.

Input: num1 = "456", num2 = "77"
Output: "533"


2. [346]




4. [146]


5. [1331]


6. [1868. Product of Two Run-Length Encoded Arrays](https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/)

```Java
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        
        List<List<Integer>> result = new ArrayList<>();
        
        int e1 = 0, e2 = 0;
        
        while(e1 < encoded1.length) {
            int[] first = encoded1[e1];
            int[] second = encoded2[e2];
            
            
            int common = Math.min(first[1], second[1]);
            int mul = first[0] * second[0];
            
            if(!result.isEmpty() && result.get(result.size() - 1).get(0) == mul) {
                List<Integer> prev = result.get(result.size() - 1);
                int prevFreq = prev.get(1);
                prev.set(1, prevFreq + common);
                result.set(result.size() - 1, prev);
            } else {
                List<Integer> current = new ArrayList<>();
                current.add(mul);
                current.add(common);
                result.add(current);
            }
            
            first[1] -= common;
            second[1] -= common;
            
            if(first[1] == 0) e1++;
            if(second[1] == 0) e2++;
        }
        
        return result;
    }
```


7. [1570. Dot Product of Two Sparse Vectors](https://leetcode.com/problems/dot-product-of-two-sparse-vectors/)

方法一：
最朴实无华的
```Java
class SparseVector {
    int[] array;
    
    SparseVector(int[] nums) {
        array = nums;
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res += array[i] * vec.array[i];
        }
        return res;
    }
}
```

方法二：
用双指针
用ArrayList<>()存一个int[]， [idx, nums[idx]]然后双指针比较idx
```Java
    // use ArrayList to store res
    List<int[]> pairs;
    
    // [[0,1], [3,2],[4,3]]
    // [[1,3],[3,4]]
    SparseVector(int[] nums) {
        pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[]{i, nums[i]});
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    // [[0,1], [3,2],[4,3]]
    //           p1
    // [[1,3], [3,4]]
    //           p2
    public int dotProduct(SparseVector vec) {
        int res = 0;
        int p1 = 0;
        int p2 = 0;
        while (p1 < this.pairs.size() && p2 < vec.pairs.size()) {
            if (this.pairs.get(p1)[0] == vec.pairs.get(p2)[0]) {
                res += this.pairs.get(p1)[1] * vec.pairs.get(p2)[1];
                p1++;
                p2++;
            } else if (this.pairs.get(p1)[0] > vec.pairs.get(p2)[0]) {
                p2++;
            } else {
                p1++;
            }
        }
        return res;
    }
```

1. [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)

```Java
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }
```
9. [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)

```Java
class Solution {
    public int diameter;
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        dfs(root);
        return diameter;
    }
    
    private int dfs(TreeNode node) { // 返回该节点左右子树的最大深度
        if (node == null) {
            return 0;
        }
        int leftPath = dfs(node.left);
        int rightPath = dfs(node.right);
        diameter = Math.max(diameter, leftPath + rightPath);
        return Math.max(leftPath, rightPath)  + 1;
    }
}

```

## 3.24


2. [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

思路：
用一个HashMap<>存储<Parent Node, cur Node>
然后针对从target，找出来距离为K的每一个点
```Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
   
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        dfs(root, null, parent);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    private void dfs(TreeNode node, TreeNode par, Map<TreeNode, TreeNode> parent) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node, parent);
            dfs(node.right, node, parent);
        }
    }
}

```


3. [865. Smallest Subtree with all the Deepest Nodes](https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/)


4. [680. Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/)

Two Pointer
```Java
class Solution {
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return isValid(s, l + 1, r) || isValid(s, l, r - 1);
            }
        }
        return true;
    }
    
    private boolean isValid(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
```

Follow up：如果可以删除n个
```Java

class Solution {
    public boolean validPalindrome(String s) {
        return helper(s, 0, s.length() - 1, 1);
    }
    
    boolean helper(String s, int i, int j, int count) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (count == 0) return false; 
                return helper(s, i + 1, j, count - 1) || helper(s, i, j - 1, count - 1);
            } 
            i++;
            j--;
        }
        return true;
    }
}
```
5. [938. Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/)

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

思路：
利用好BST的性质，只看小于大于就可以
```Java
    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] sum = {0};
        dfs(root, low, high, sum);
        return sum[0];
    }
    
    private void dfs(TreeNode node, int low, int high, int[] sum) {
        if (node == null) {
            return;
        }
        
        if (low <= node.val && node.val <= high) {
            sum[0] += node.val;
        }
        
        if (low < node.val) {
            dfs(node.left, low, high, sum);
        }
        
        if (node.val < high) {
            dfs(node.right, low, high, sum);
        }   
    }
```

6. [1650. Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/)

求各自深度，到相同深度，然后往上走

```Java
    public Node lowestCommonAncestor(Node p, Node q) {
        int pDepth = getDepth(p);
        int qDepth = getDepth(q);
        // get depth
        // make them at the same level
        // go up to find LCA
        while (pDepth > qDepth) {
            p = p.parent;
            pDepth--;
        }
        while (pDepth < qDepth) {
            q = q.parent;
            qDepth--;
        }
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }
    
    private int getDepth(Node node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }
```

7. [1249. Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)

思路：
用stack存inValid的'(',')'的index
如何判断inValid：每次看到(就压栈，看到)要么弹要么直接放到set里
再把多余的(都放到set里面
最后用stringbuilder来构建新的东东
```Java
class Solution {
    public String minRemoveToMakeValid(String s) {
        // find out the invalid index
        // build s
        // use stack: see '(': push the index into it
                    //see ')': delete the '(' OR store its invalid index
        // finally may have extra '(', keep track of their indexes
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } if (c == ')') {
                if (stack.isEmpty()) {
                    // store its index
                    set.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        
        // extra '('s in the stack
        while (!stack.isEmpty()) {
            set.add(stack.pop());
        }
        
        // build a new string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

8. [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

```Java

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //base case
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //result
        if(left == null) {
            return right;
        }
        else if(right == null) {
            return left;
        }
        else { //both left and right are not null, we found our result
            return root;
        }
    }

```

9. [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

Input: nums = [1,1,1], k = 2
Output: 2

思路一：
双指针，第一个指针一次走一步，另一个指针走到头
时间：O(n^2)
空间：O(1)
```Java

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

```

思路二：
HashMap存前缀和以及和的个数
每次看前缀和-k是否在HashMap里面，如果在的话就说明找到了
时间：O(N)
空间：O(N)

```Java
    public int subarraySum(int[] nums, int k) {
        int count = 0; 
        int curSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if (map.containsKey(curSum - k)) {
                count += map.get(curSum - k);
            }
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }
```

10. [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/)

思路一：
用Stack存数字，每次如果是+-就直接压进去，如果是*/就压进去相对应的数，最后弹栈相加
时间：O(N)
空间：O(N)

```Java
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int curNum = 0;
        char ope = '+';
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = (curNum * 10) + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == len - 1) { // 注意条件：不是数字，不是空格，也不是最后一位
                if (ope == '-') {
                    stack.push(-curNum);
                } else if (ope == '+') {
                    stack.push(curNum);
                } else if (ope == '*') {
                    stack.push(stack.pop() * curNum);
                } else if (ope == '/') {
                    stack.push(stack.pop() / curNum);
                }
                ope = c;
                curNum = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
```

思路二：
用一个lastNum来记录上一个数
时间：O(N)
空间：O(1)

```Java
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int res = 0;
        int lastNum = 0;
        Stack<Integer> stack = new Stack<>();
        int curNum = 0;
        char ope = '+';
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = (curNum * 10) + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == len - 1) {
                
                if (ope == '-') {
                    res += lastNum;
                    lastNum = -curNum;
                } else if (ope == '+') {
                    res += lastNum;
                    lastNum = curNum;
                } else if (ope == '*') {
                    lastNum *= curNum;
                } else if (ope == '/') {
                    lastNum /= curNum;
                }
                ope = c;
                curNum = 0;
            }
        }
        res += lastNum;
        return res;
    }

```

11. [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)

方法一： maxHeap
只保留Heap size是k的大小
时间：O(NlogK)
空间：O(k)
```Java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Use a lambda comparator to sort the PQ by farthest distance
        Queue<int[]> maxPQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < points.length; i++) {
            int[] entry = {squaredDistance(points[i]), i};
            if (maxPQ.size() < k) {
                // Fill the max PQ up to k points
                maxPQ.add(entry);
            } else if (entry[0] < maxPQ.peek()[0]) {
                // If the max PQ is full and a closer point is found,
                // discard the farthest point and add this one
                maxPQ.poll();
                maxPQ.add(entry);
            }
        }
        
        // Return all points stored in the max PQ
        int[][] answer = new int[k][2];
        for (int i = 0; i < k; i++) {
            int entryIndex = maxPQ.poll()[1];
            answer[i] = points[entryIndex];
        }
        return answer;
    }
    
    private int squaredDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
};
```

# 3.26 
1. [791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/)

Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

先统计s各个字母出现次数，然后根据order的顺序放进res里，最后把剩下的放进来
```Java
class Solution {
    public String customSortString(String order, String s) {
        int[] count = new int[26];
        // 先统计s里面有啥东西
        // 走一遍s: count数组一个slot对应一个字母出现的次数
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        // 走一遍Order: 按照order的顺序，一个一个往结果里面放
        for (char c : order.toCharArray()) {
            // 决定了这个c放进去几次
            for (int i = 0; i < count[c - 'a']; i++) {
                sb.append(c);
            }
            // 这个c彻底放完了，就把次数变成0
            count[c - 'a'] = 0;
        }
        // 走一遍letter: 把没有order的放进来
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < count[c - 'a']; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

# 3.28 
1. [71. Simplify Path](https://leetcode.com/problems/simplify-path/)

Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.

In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.

The canonical path should have the following format:

The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.

思路：
用一个stack
先根据/分割
如果是.或者空格，忽略
如果是..，当非空的时候弹栈
如果是文件夹，直接放进去
最后从stack构建回String
时间：
空间：
```Java
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");
        for (String s : components) {
            if (s.equals(".") || s.isEmpty()) {
                continue;
            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.add(s);
            }
        }
        
        StringBuilder res = new StringBuilder();
        for (String s : stack) {
            res.append("/");
            res.append(s);
        }
        
        return res.length() > 0 ? res.toString() : "/";
    }
}
```

2. [8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/)

Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

The algorithm for myAtoi(string s) is as follows:

Read in and ignore any leading whitespace.
Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
Return the integer as the final result.

Input: s = "   -42"
Output: -42

Input: s = "4193 with words"
Output: 4193

思路：
把String从前往后
curRes = curRes * 10 + curDigit
要处理的东西：
1. 空格
2. 数字
3. 符号
4. 其他

时间：O(n)
空间：O(1)
```Java
class Solution {
    public int myAtoi(String s) {
        // 注意如何从String取数字，以及如何转化为数字， 以及如何处理overflow
        int sign = 1;
        int res = 0;
        int index = 0;
        int n = s.length();
        // 先删除之前的空格
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        // 判断正负号
        if (index < n && s.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && s.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        
        // 接下来是数字的时候，往后走
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0'; // 注意是如何取数字的
            // 检查过大或过小
            // 1. res > Integer.MAX_VALUE / 10肯定会在下一个超过
            // 2. res < Integer.MAX_VALUE / 10就不用担心
            // 3. res == Integer.MAX_VALUE / 10，只有0-7可以满足，因为Integer.MAX_VALUE % 10 = 7
            if ((res > Integer.MAX_VALUE / 10) ||
                (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = 10 * res + digit;
            index++;
        }
        return sign * res;
    }
}
```



3. [65. Valid Number](https://leetcode.com/problems/valid-number/)

A valid number can be split up into these components (in order):

A decimal number or an integer.
(Optional) An 'e' or 'E', followed by an integer.
A decimal number can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One of the following formats:
One or more digits, followed by a dot '.'.
One or more digits, followed by a dot '.', followed by one or more digits.
A dot '.', followed by one or more digits.
An integer can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One or more digits.
For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.

思路：
要处理的东西
1. digits
2. sign('+', '-')：必须出现在开头，或者紧跟在'e', 'E'后面
3. exponent：必须第一次见，而且见过digit
4. dot：必须第一次见，而且不能在exponent后面
5. other

用seenDigit, seenExponent, seenDot来记录是否见过
1. 见到digit
2. 见到exponent
3. 见到dot


```Java

    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenExponent = false;
        boolean seenDot = false;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                seenDigit = true;
            } else if (cur == '+' || cur == '-') { // 判断‘+', '-’
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (cur == 'e' || cur == 'E') { // 判断'e', 'E'
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false;
            } else if (cur == '.') {               // 判断'.'
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else {
                return false;
            }
        }
        return seenDigit;
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




# BQ
1. Q:‍‌‌‌‌‍‍‍‍‍‌‍‌‍‌‍‌‍‌‌ 你觉得自己有什么方向需要提升，你是怎么去做的？
A: 我看了本书讲xxx，想往这个方向提升，方法是1.看书 2. 多和experienced sde沟通
                                他就会问一个follow up：那第二点你做了吗？你去找其他sde聊了吗？哪个公司的？都聊了些什么？

2. Q: 和队友conflict，需要细节到你用几次meeting解决了？每次大概都聊了什么？有其他人engage吗？怎么解决第三人的问题




## 3.25 OA1

### Q1
We have two sorted arrays of integers: A and B. A has empty slots at the end of it. 
It has exactly as many empty slots as there are elements in B.
Your goal is to merge the elements from B into A so that array A contains all of the elements in sorted order. 
Input:
A = [1, 2, 3, _, _, _, _]
B = [2, 4, 6, 100]
Expected output:
A = [1, 2, 2, 3, 4, 6, 100]


3 pointers: Time: O(n) Space: O(1)

A = [1, 2, 3, _, _, _, _]
    pA  p
        2   2   3  4   6 100
B = [2, 4, 6, 100]
  pB

```Java

public int[] mergeArrays(int[] A, int[] B) {
    int n = B.length;
    int m = A.length - n; // m is valid
    int pA = m - 1;
    int pB = n - 1;
    int p = A.length - 1;
    for (; p >= 0; p--) {
      if (pA < 0 || pB < 0) {
        break;
      }
      if (A[pA] >= B[pB]) {
        A[p] = A[pA];
        pA--;
      } else {
        A[p] = B[pB];
        pB--;
      }
    }
    while (pB > 0) {
      A[p] = B[pB];
      p--;
      pB--;
    }
    return A;
}

```

### Q2

You're given a calendar year represented as a char array that contains either H or W where:
H = Holiday W = Workday
Given a number of Personal Time-Off days (PTO), maximize the length of the longest consecutive vacation you can take.
*Example*
Start with an example: [W, H, H, W, W, H, W], PTO = 2 --> Your maximum consecutive vacation is 5 days.

// right: (PTO > 0 )when H: count++
          when W: PTO--; count++
// move left: (PTO != 0)when W: PTO++, count--; stop
          when H: count--
Time: O(N)
Space: O(1)


[W, H, H, W, W, H, W], PTO = 2 
    l
             r
PTO = 0
count = 4
max = 4

```Java

public int getLongestVacation(int[] days, int PTO) {
  int l = 0;
  int r = 0;
  int count = 0;
  int max = 0;
  while (r < days.length) {
      // move r to the right
      while (PTO >= 0 && r < days.length) {
        if (days.charAt(r) == 'H') {
          count++;
        } else {
          count++;
          PTO--;
        }
        max = Math.max(max, count);
        r++;
      }
      // move l to the right
      while (PTO != 0) {
        if (days.charAt(l) == 'H') {
          count--;
        } else {
          count--;
          PTO++;
        }
        l++;
      }
  }
  return max;
}

```


