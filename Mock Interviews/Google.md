# Question1:
// input: integer N
// output: date of Nth event in calender
// question: given the calendar app, and a specific date, return the nearest event

examples:
// recurring event: every Monday morning at 9:00 am


-> 给brute force的思路，说出Time and space complexity的结果和原因   
-> Optimum: nearest-> binary search as has upper bound and lower bound.
-> 问觉得怎么样 how do you think this solution
-> 敲代码
-> 结束之后回顾过一个test case，看edge cases和typo

clarification: 输入输出、类型

除了tree 和 graph，binary search都要先用iteration，而不是recurrsion

总结binary search的first, nearest, last的模板

考前3天：
1. leetcode google tasks 6 months by frequency, 做前200
2. 一亩三分地面经前2页

# Question2
A domino has the face value of a pair of numbers (a, b) where a, b ∈ {1 ... 6}. We want to make a chain of dominoes so that
the sequence will look like (a0, a1), (a1, a2), (a2, a3), ... that is, we can only connect two dominoes if the numbers being 
connected are the same.

The question is, given a small stack of randomly drawm dominoes, what is the longest chain that can be made?


(0, 1), (0, 0), (1, 2) (1, 3) -> 3


# Question3 Jan.11.2022
## Question:
Whether the binary tree are identical
Example:
   1
  / \
 /   \
3     2
and 
   1
  / \
 /   \
2     3
are not identical. But
   1
  / \
 /   \
2     2
and 
   1
  / \
  \ / 
   2
are NOT identical.

Also   
      1 
     /  \
    /     \
   2     \
  /  \     /
 /    \  /
2     2 
 \
   \
    2 
and
      1
     /  \
    /     \
   2      \
  /  \       \
 /    \       \
2     2     /
 \        /
   \    /
    2
are not identical.

preorder (root is pre)
1,2,2,2,2,2
1,2,2,2,2,2

Use set to mark visited
use attribute in the node(not enough)
--> keep the first visited number, keep the inorder number
any questions for me?

feedback:
1. 基本数据结构和算法的概念，要熟悉 tree traversal之类的要bug free而且neat
2. 最优解：keep 所有preorder number

BQ 考察是否fit in google culture, positve impact, collaborate, (leadership)