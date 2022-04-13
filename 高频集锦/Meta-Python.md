[125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
用相向two pointer，当不是char时候就比较; s[i].isalnum() 看是否是string或者num; s[i].lower() 返回一个小写

时间：O(N)
空间：O(1)

```python
def isPalindrome(s: str) -> bool:
    i, j = 0, len(s) - 1
    while i < j:
        while i < j and not s[i].isalnum():
            i += 1
        while i < j and not s[j].isalnum():
            j -= 1
        if s[i].lower() != s[j].lower():
            return False
        i += 1
        j -= 1
    return True

print(isPalindrome("dafsad"))

```

```python
class IsValid:
    def isPalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        while i < j:
            while i < j and not s[i].isalnum():
                i += 1
            while i < j and not s[j].isalnum():
                j -= 1
            if s[i].lower() != s[j].lower():
                return False
            i += 1
            j -= 1
        return True

te = IsValid()
print(te.isPalindrome("dafsas"))
```


[1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)
用Stack，如果相同就pop，不同就放进来，最后转换成string

时间：O(N)
空间：O(N-D) D is len(duplicates)

```python
class Solution:
    def removeDuplicates(self, s: str) -> str:
        stack = []
        for ch in s:
            if stack and ch == stack[-1]: # 要在stack非空的时候
                stack.pop()
            else:
                stack.append(ch)
        return ''.join(stack) # stack导出为string的方法
```

[766. Toeplitz Matrix](https://leetcode.com/problems/toeplitz-matrix/)

比较m[i][j]和m[i+1][j+1]即可

时间：O(M*N)
空间：O(1)

```python
class Solution:
    def isToeplitzMatrix(self, m):
        for i in range(len(m) - 1):
            for j in range(len(m[0]) - 1):
                if m[i][j] != m[i + 1][j + 1]:
                    return False
        return True
```
Follow up
Hmm you can barely get one row into memory, the hashmap approach doesn't make sense to me. I am thinking of an alternative to approach which is rather straight forward, you need to know how interaction with filesystem works. Load row 1 and have pointers to each column and follow along:

For approach 1: Assuming we are dealing with example 1:

[[1,2,3,4],
[5,1,2,3],
[9,5,1,2]]
We go top down:
Step 1: load First row 1, 2, 3, 4
Step 2: 4 is valid so move col 3 pointer forward since thats valid 1, 2 , 3, 3;
Step 3: 3 & 3 are valid so move col 2 and col 3 : 1, 2, 2, 2;
Step 4: 2 & 2 & 2 are valid so move col 1, 2, 3 : 1 , 1, 1, null; (drop col 3);
Step 5: 1 & 1 & 1 are valid so move col 0, 1, 2: 5, 5, null, null (drop col 2);
Step 6: 5 & 5 are valid so move col 0, 1: 9, null (drop col 1);
Step 7: 9 is valid;

return valid;
At most we have one "row" in memory;
If you notice a pattern here that when moving from r->l we incrementally compare values;
I'll leave it to you to think how you can extend this to fewer columns.

[346. Moving Average from Data Stream](https://leetcode.com/problems/moving-average-from-data-stream/)

用queue，每次记录当前的windowSum，如果size满足的话，来一个新数字就弹出末尾的，同时加进来新的，最后返回windowSum/len即可：python用deque: from collections import deque；queue删除头部: queue.popleft()；queue加数字: queue.append(val)

时间：O(1)
空间：O(N)N is the size of window
```python
from collections import deque

class MovingAverage:

    def __init__(self, size: int):
        self.size = size
        self.queue = deque()
        self.windowSum = 0

    def next(self, val: int) -> float:
        if len(self.queue) == self.size:
            self.windowSum -= self.queue.popleft()
        self.queue.append(val)
        self.windowSum += val
        return self.windowSum / len(self.queue)

obj = MovingAverage(2)
param_1 = obj.next(3)
param_2 = obj.next(5)
param_3 = obj.next(9)
param_4 = obj.next(1)
param_5 = obj.next(2)
param_6 = obj.next(3)

print(param_3)


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
```

[246. Strobogrammatic Number](https://leetcode.com/problems/strobogrammatic-number/)
思路：
先确定满足条件的strobogrammatic nums; 然后用hashmap一一对应起来; 用相向two pointers一一比对，注意条件有2个：满足strobogrammatic num并且要pointers指向对应

时间：O(N)
空间：O(1)

```python
class Solution:
    def isStrobogrammatic(self, num: str) -> bool:
        rotated_digits = {'0': '0', '1': '1', '8': '8', '6': '9', '9': '6'}
        
        left = 0
        right = len(num) - 1
        
        while left <= right:
            if num[left] not in rotated_digits or rotated_digits[num[left]] != num[right]:
                return False
            left += 1
            right -= 1
        return True
```

[266. Palindrome Permutation](https://leetcode.com/problems/palindrome-permutation/)
放进map里{char: count}数个数，如果偶数就可以，奇数的话只能至多一个是奇数; 注意map[item] = map.get(item, 0) + 1的使用方法

时间：O(N)
空间：O(1) a map of constant size(128) is used
```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        map = {}
        for item in s:
            map[item] = map.get(item, 0) + 1
        
        count = 0
        for val in map.values():
            if val % 2 == 1:
                count += 1
            if count > 1:
                return False
        return True
```            

[953. Verifying an Alien Dictionary](https://leetcode.com/problems/verifying-an-alien-dictionary/)
用map存{letter: rank}，然后比较相邻的word，不符合的条件有两个：1，前面相同时len(words[i]) > len(words[i+1]；2，不同时候rank不对。如果不同但是满足，可以就直接break这两个word的比较；enumerate(string)返回(index, val)

时间：O(M)； M is total number of char in words
空间：O(1)

```python
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        letter_order = {}
        
        for idx, val in enumerate(order):
            letter_order[val] = idx
        
        # 两两比较
        for i in range(len(words) - 1):
            w1, w2 = words[i], words[i+1]
            min_len = min(len(w1), len(w2))
            # edge case：两个前缀一样，但是第一个更长
            if len(w1) > len(w2) and w1[:min_len] == w2[:min_len]:
                return False
            # 正常比较
            for j in range(min_len):
                if w1[j] != w2[j]:
                    if letter_order[w1[j]] > letter_order[w2[j]]:
                        return False
                    break
                    
        return True
```

[199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

from collections import deque; queue放元素：queue = deque([root])

时间：O(N)
空间：O(D)

```python
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []
        
        res = []
        
        queue = collections.deque([root])
        
        while queue:
            size = len(queue)
            for i in range(size):
                node = queue.popleft()
                
                if i == size - 1:
                    res.append(node.val)
                
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
        
        return res
```

Recursively solve it:
```python
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        res = []
        def dfs(node, depth):
            if not node:
                return
            
            if depth == len(res):
                res.append(node.val)
            
            dfs(node.right, depth + 1) # 如果先放进来node.left，就是左边视图
            dfs(node.left, depth + 1)
        
        dfs(root, 0)
        return res


```



[896. Monotonic Array](https://leetcode.com/problems/monotonic-array/)

思路一：
走两遍，all()函数返回真假all(nums[i]<=nums[i+1] for i in range(len(nums) - 1))
```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        return (all(nums[i] <= nums[i+1] for i in range(len(nums) - 1))) or all(nums[i] >= nums[i+1] for i in range(len(nums) - 1))
```

思路二：
走一遍，用两个boolean值increase和decrease并设置成true，如果不满足就设置成false，最后返回其中一个是true就可以
```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        increase = decrease = True
        for i in range(len(nums) - 1):
            if nums[i] < nums[i + 1]:
                decrease = False
            if nums[i] > nums[i + 1]:
                increase = False
        return increase or decrease
```

[824. Goat Latin](https://leetcode.com/problems/goat-latin/)
思路：
straight forward；str.split()方法的使用；' '.join(str)的使用
```python
class Solution:
    def toGoatLatin(self, sentence: str) -> str:
        res=[]
        for i in range(len(sentence.split())):
            word=sentence.split()[i]
            if word[0].lower() in ['a','e','i','o','u']: # 用了一个lower()用不用再加载AEIOU了
                word+="ma"+'a'*(i+1)
            else:
                word=word[1:]+word[0]+"ma"+'a'*(i+1)
            res.append(word)
        return ' '.join(res)
```

[724. Find Pivot Index](https://leetcode.com/problems/find-pivot-index/)

思路：
分别表示出来l_sum和r_sum
```python
class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        # 直接表示出来就行了
        l_sum = 0
        r_sum = sum(nums)
        for i in range(len(nums)):
            r_sum -= nums[i]
            if l_sum == r_sum:
                return i
            l_sum += nums[i]
        return -1
```

[163. Missing Ranges](https://leetcode.com/problems/missing-ranges/)

思路：
用一个函数来把需要补充的值放进去，注意可能是需要补充一个值，或者是一系列值；用cur=nums[i]，一次比较pre+1和cur-1的大小，然后把pre=cur；同时走到底之后,cur=upper+1；注意lower的起点是lower - 1
```python
class Solution:
    def findMissingRanges(self, nums: List[int], lower: int, upper: int) -> List[str]:
        def formatRange(lower, upper):
            if lower == upper:
                return str(lower)
            return str(lower) + "->" + str(upper)

        res = []
        prev = lower - 1
        for i in range(len(nums) + 1):
            cur = nums[i] if i < len(nums) else upper + 1
            if prev + 1 <= cur - 1:
                res.append(formatRange(prev + 1, cur - 1))
            prev = cur
        return res
```



[977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

思路：
相向two pointers，依次比较，res[]从后往前；声明大小为n的[]: res = [0] * n；从后往前for i in range(n-1, -1, -1): range(start, stop, step), stop not included

```python
class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        left = 0
        right = len(nums) - 1
        res = [0] * len(nums)
        for i in range(len(nums) - 1, -1, -1):
            if abs(nums[left]) < abs(nums[right]):
                square = nums[right]
                right -= 1
            else:
                square = nums[left]
                left += 1
            res[i] = square * square
        return res
```

[605. Can Place Flowers](https://leetcode.com/problems/can-place-flowers/)

首尾加0，用一个count来记录连续的0，如果count==3那就n-=1同时count=1; list.insert()

时间：O(N)
空间：O(1)

```python
    def canPlaceFlowers(self, flowerbed, n):
		
        flowerbed.insert(0, 0)
        flowerbed.append(0)
        count = 0
        for f in flowerbed:
            if f == 0:
                count += 1
            else:
                count = 0
            if count == 3:
                n -= 1
                count = 1
            if n == 0:
                return True
        return False
```

[415. Add Strings](https://leetcode.com/problems/add-strings/)

two pointers从后往前，用carry存进位的情况，value = (x1 + x2 + carry) % 10, carry = (x1 + x2 + carry) // 10. 走到头carry不为0就再append一下，最后reverse并且转换成string即可；ord(string)返回unicode值, x = ord(string) - ord('0')就把'5'存成5到x里；a // 10 地板除，向下取整; math.ceil(a/10)就是向上取整；res[]存的整数反过来导出成string: ''.join(str(x) for x in res[::-1])；要先更新val，再更新carry

时间：O(max(N1, N2)
空间：O(max(N1, N2))

```python
class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        res = []
        carry = 0
        p1 = len(num1) - 1
        p2 = len(num2) - 1
        while p1 >= 0 or p2 >= 0:
            x1 = ord(num1[p1]) - ord('0') if p1 >= 0 else 0
            x2 = ord(num2[p2]) - ord('0') if p2 >= 0 else 0
            value = (x1 + x2 + carry) % 10
            carry = (x1 + x2 + carry) // 10
            res.append(value)
            p1 -= 1
            p2 -= 1
        if carry:
            res.append(carry)
        
        return ''.join(str(x) for x in res[::-1])
```

[67. Add Binary](https://leetcode.com/problems/add-binary/)

和415一模一样，只是换成了2进制two pointers从后往前走直到头，res每次添加(p1+p2+carry)%2的，同时carry = (p1+p2+carry)//2，最后res翻转过来

```python
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        p1 = len(a) - 1
        p2 = len(b) - 1
        res = []
        carry = 0
        
        while p1 >= 0 or p2 >= 0 or carry:
            x1 = ord(a[p1]) - ord('0') if p1 >= 0 else 0
            x2 = ord(b[p2]) - ord('0') if p2 >= 0 else 0
            value = (x1 + x2 + carry) % 2
            carry = (x1 + x2 + carry) // 2
            res.append(value)
            p1 -=1
            p2 -=1
        if carry:
            res.append(carry)
        return ''.join(str(x) for x in res[::-1])
```

[1249. Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)

思路：
先把s变成list，用stack存inValid的'(',')'的index; 如何判断inValid：每次看到(就压栈，看到)要么弹要么直接换成""，最后多余在stack里的（直接换成空）。最后把s导出成string：直接换成空：s[idx] = ""， 最后list变成str: "".join(s)

时间：O(N)
空间：O(N)
```python
class Solution:
    def minRemoveToMakeValid(self, s: str) -> str:
        s = list(s)
        stack = []
        for index, letter in enumerate(s):
            if letter == "(":
                stack.append(index)
            elif letter == ")":
                if stack:
                    stack.pop()
                else:
                    s[index] = ""
        while stack:
            s[stack.pop()] = ""
            
        return "".join(s)
```

[680. Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/)

two pointers分别从两头往中间走，如果不满足就再给个机会
```python
class Solution:
    def validPalindrome(self, s: str) -> bool:        
        left = 0
        right = len(s) - 1
        
        def isValid(s, left, right): # 定义方式
                while left < right:
                    if s[left] == s[right]:
                        left += 1
                        right -= 1
                    else:
                        return False
                return True #不要忘记return True
                    
        while left < right:
            if s[left] == s[right]:
                left += 1
                right -= 1
            else:
                return isValid(s, left + 1, right) or isValid(s, left, right - 1)                 
        return True #不要忘记return True
```        

[314. Binary Tree Vertical Order Traversal](https://leetcode.com/problems/binary-tree-vertical-order-traversal/)

Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

思路：
Queue存((node, col)), 用一个map{col, oneRes}, 遍历的时候，更新HashMap,最后用HashMap来导出，但是不知道最小值最大值，所以实时更新一下，这样就不用sort，时间复杂度O(N),空间：O(N); colTable=defaultlist(list); queue=deque([(root, 0)]); queue.append((node,col-1))

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from collections import deque
class Solution:
    def verticalOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if root is None: # 不要忘了base case
            return []
        colTable = defaultdict(list) #When the list class is passed as the default_factory argument, then a defaultdict is created with the values that are list.
        min_col = max_col = 0
        queue = deque([(root, 0)]) # 初始化加是deque([(root, 0)])
        res = []
        while queue:
            node, col = queue.popleft()
            colTable[col].append(node.val)
            min_col = min(min_col, col)
            max_col = max(max_col, col)
            if node.left:
                queue.append((node.left, col - 1)) # 双(())
            if node.right:
                queue.append((node.right, col + 1)) 

        for i in range(min_col, max_col + 1): # 左开右闭，需要加一
            res.append(colTable[i])
        return res

        """
        如果是直接colTable = {}
            if col not in colTable:
                colTable[col] = [node.val]
            else:
                colTable[col].append(node.val)
        """
```

[987. Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/)

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

与上一题唯一不同就是每一层新建一个map，然后排序好之后加到最终的map里；走完一层如何放进来：one_res[col] += sorted(temp[col])
```python
class Solution(object):
    def verticalTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """    
        one_res = collections.defaultdict(list) 
        queue = collections.deque([(root, 0)])
        min_col, max_col = 0, 0
        while queue:
            tmp = collections.defaultdict(list)  # 每层之前先另外搞一个map
            for _ in range(len(queue)):
                node, col = queue.popleft()
                tmp[col].append(node.val) 
                min_col = min(min_col, col)
                max_col = max(max_col, col)

                if node.left:
                    queue.append((node.left, col - 1))
                if node.right: 
                    queue.append((node.right, col + 1)) 
                    
            for col in tmp: # 走完一层再把map按顺序加进去
                one_res[col] += sorted(tmp[col])

        res = []
        for col in range(min_col, max_col + 1): # 左开右闭，需要加一
            res.append(one_res[col])
        return res
```

[1762. Buildings With an Ocean View](https://leetcode.com/problems/buildings-with-an-ocean-view/)

There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

Input: heights = [4,2,3,1]
Output: [0,2,3]

从右往左走，每次记录最大值，比较最大值和当前值；当前值更大就更新最大值并且记录index；翻转list: res.reverse()，什么都不返回；反过来走：for i in range((len(heights)-1, -1, -1):
时间： O(n)
空间： O(1)
```python
class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:
        n = len(heights)
        res = []
        curMax = -1
        for i in range(len(heights)-1, -1, -1):
            if curMax < heights[i]:
                curMax = heights[i]
                res.append(i)
        res.reverse()
        return res
```


[1570. Dot Product of Two Sparse Vectors](https://leetcode.com/problems/dot-product-of-two-sparse-vectors/)

方法一：
直接按照array来算；
时间O(n) 空间O(1)
```python
class SparseVector:
    def __init__(self, nums: List[int]):
        self.array = nums

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: 'SparseVector') -> int:
        res = 0
        for num1, num2 in zip(self.array, vec.array): # zip function: Iterator objects that will be joined together, return a list of tuples
            res += num1 * num2
        return res
```

方法二：
确定数组到底是多大，如果不是特别大就可以用dict{index, num}，然后遍历(key, value)比较如果index也同时在vec.dict里面，就res+=；遍历dict：for key, value in dic.items()；查看某个key是否在dict里: if key in dict；从dict取key对应的value：dict[key]
时间：建立dict用O(N)，计算dot是O(L)L是非0个数
空间：建立dict是O(L)，计算dot是O(1)
```python
class SparseVector:
    def __init__(self, nums: List[int]):
        self.nonzeroes = {}
        for i, n in enumerate(nums):
            if n != 0:
                self.nonzeroes[i] = n

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: 'SparseVector') -> int:
        res = 0
        for i, n in self.nonzeroes.items(): #The items() method returns a view object that displays a list of a given dictionary's (key, value) tuple pair
            if i in vec.nonzeroes:
                res += n * vec.nonzeroes[i]
        return res
            

```

方法三：
同向双指针，存成pairs[(index, num)]只存不是0的index和num，当同时都没到终点，只有当pairs中的index相同才res+=，否则根据index大小移动指针；pairs添加数据：pairs.append([i, n])
时间：建立pairsO(N)，计算dot O(L1+L2)；其中L1, L2是非0个数
空间：建立pairsO(L)，计算dot O(1)
```python
class SparseVector:
    def __init__(self, nums: List[int]):
        self.pairs = []
        for i, n in enumerate(nums):
            if n != 0:
                self.pairs.append([i, n])

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: 'SparseVector') -> int:
        res = 0
        p = q = 0
        while p < len(self.pairs) and q < len(vec.pairs):
            if self.pairs[p][0] == vec.pairs[q][0]: #当index相同
                res += self.pairs[p][1] * vec.pairs[q][1]
                p += 1  # 不要忘了+= 1
                q += 1
            elif self.pairs[p][0] < vec.pairs[q][0]:
                p += 1
            else:
                q += 1
        return res
```

[938. Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/)

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

思路：
利用好BST的性质，只看小于大于就可以；需要用self.res的全局变量
时间：O(N)， N是nodes数量
空间：O(N)
```python
class Solution:
    def rangeSumBST(self, root: Optional[TreeNode], L: int, R: int) -> int:
        if not root:
            return 0
        res = 0
        def dfs(node):
            nonlocal res
            if node:
                if L <= node.val <= R:
                    res += node.val
                if L < node.val:
                    dfs(node.left)
                if node.val < R:
                    dfs(node.right)
        dfs(root)
        return res
```

[1650. Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/)

先求各自深度，再把深的往上走直到当前深度相同，最后一起往上走找parent；注意找深度是while p；深度就是层数root的深度是1
时间：O(H)
空间：O(1)
```python

    def lowestCommonAncestor(self, p: 'Node', q: 'Node') -> 'Node':
        def get_depth(p):
            depth = 0
            while p:
                p = p.parent
                depth += 1
            return depth
    
        d1 = get_depth(p)
        d2 = get_depth(q)
        
        while d1 > d2:
            p = p.parent
            d1 -= 1
                
        while d1 < d2:
            q = q.parent
            d2 -= 1
        
        while p != q:
            p = p.parent
            q = q.parent
        
        return p      
```

[528. Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/) (前缀和，可以先做一下LC53、523)


用list存所有的前缀和。概率是w[i]/total_sum，可以用找到第一个preSum来代替；用random.random()来获得[0,1);w:[1,3]-> pre_sums:[1, 4] -> target in randomly in [0, 4); find the first index in pre_sums s.t. target < pre_sums[idx]
时间：构造O(N)，找数O(N)
空间：构造O(N)，找数O(1)
```python
class Solution:

    def __init__(self, w: List[int]):
        self.prefix_sums = []
        pre_sum = 0
        for weight in w:
            pre_sum += weight
            self.prefix_sums.append(pre_sum)
        self.total_sum = pre_sum

    def pickIndex(self) -> int:
        target = self.total_sum * random.random()
        for i, pre_sum in enumerate(self.prefix_sums):
            if target < pre_sum:
                return i

# Your Solution object will be instantiated and called as such:
# obj = Solution(w)
# param_1 = obj.pickIndex()
```

用list存所有的前缀和。概率是w[i]/total_sum，可以用二分查找找到第一个preSum来代替；用random.random()来获得[0,1); 当右边左右的数都满足的时候，找最左满足的数，最后返回的是l
时间：构造O(N)，找数O(logN)
空间：构造O(N)，找数O(1)
```python 
class Solution:
    def __init__(self, w: List[int]):
        self.prefix_sums = []
        pre_sum = 0
        for weight in w:
            pre_sum += weight
            self.prefix_sums.append(pre_sum)
        self.total_sum = pre_sum

    def pickIndex(self) -> int:
        target = self.total_sum * random.random()
        l, r = 0, len(self.prefix_sums) - 1
        while l <= r:
            mid = l + (r - l) // 2 # 要地板除
            if (target > self.prefix_sums[mid]):
                l = mid + 1
            else: 
                r = mid - 1
        return l

# Your Solution object will be instantiated and called as such:
# obj = Solution(w)
# param_1 = obj.pickIndex()
```

[408. Valid Word Abbreviation](https://leetcode.com/problems/valid-word-abbreviation/)

从前往后依次比较：如果相同就往后走，如果j第一个是0就False，如果j是其他数字：当一直是数字的时候就一直往后走，同时记住总数字，然后把i也移动那么多；最后判断是否都走到底了；判断j是否是数字: abbr[i].isnumeric()，把string s="dd23ss"的转换成数字int(s[2:4])；注意if else关系；
时间：O(N), N is word.length()
空间：O(k), k is size of the largest num
```python
class Solution:
    def validWordAbbreviation(self, word, abbr):
        i = j = 0
        m, n = len(word), len(abbr)
        while i < m and j < n:
            if word[i] == abbr[j]:
                i += 1
                j += 1
            elif abbr[j] == "0":
                return False
            elif abbr[j].isnumeric():
                k = j
                while k < n and abbr[k].isnumeric():
                    k += 1
                i += int(abbr[j:k])
                j = k
            else:
                return False
        return i == m and j == n
```

[339. Nested List Weight Sum](https://leetcode.com/problems/nested-list-weight-sum/)

用dfs，如果是数字就total +=，如果不是就深度加一继续。
时间：O(N), N is size of nestedList
空间：O(K), K is largest List
```python
class Solution:
    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        def dfs(nestedList, depth):
            total = 0
            for nested in nestedList:
                if nested.isInteger():
                    total += nested.getInteger() * depth
                else:
                    total += dfs(nested.getList(), depth + 1)
            return total
        return dfs(nestedList, 1)
```

[921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/)

用left, right两个分别记录需要valid的'('和')'，如果见到'('就right++，如果见到')'就要么right--要么left++，最后返回left+right
时间O(N)
空间O(1)
```python
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        left = right = 0
        for c in s:
            if c == "(":
                right += 1
            elif c == ")":
                if right > 0:
                    right -= 1
                else:
                    left += 1
        return left + right
```

[139. Word Break](https://leetcode.com/problems/word-break/)

用dp[]存每个index能否满足条件，从后往前来更新dp[]，对于每个起点i，从长度满足的wordDict来找能走到的：s[i:i+len(w)] == w，找到就能退出看下一个i
时间：O(N*M*N) N is len(s), M is len(wordDict)
空间：O(1)
```python
class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        
        dp = [False] * (len(s) + 1)
        dp[len(s)] = True

        for i in range(len(s) - 1, -1, -1):
            for w in wordDict:
                if (i + len(w)) <= len(s) and s[i : i + len(w)] == w:
                    dp[i] = dp[i + len(w)]
                if dp[i]:
                    break
        return dp[0]
```

[140. Word Break II](https://leetcode.com/problems/word-break-ii/) 


```python
class Solution(object):
    def wordBreak(self, s, wordDict):
        def backtrack(res, one_res, s):
            if len(s) == 0:
                res.append(" ".join(one_res))
                return
            
            for w in wordDict:
                if s.startswith(w):
                    one_res.append(w)
                    backtrack(res, one_res, s[len(w):])
                    one_res.pop()
                    
        res = []
        backtrack(res, [], s)
        return res
```

[124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

dfs()在左右子树不分分叉的情况下，返回子树path最大值:return node.val + max(leftMax, rightMax); basecase是走到null的0，同时更新res[0], res[0]=max(res[0], node.val+leftMax+rightMax)
时间: O(N) n is num of nodes
空间: O(H) if balanced tree

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        res = float("-inf")
        
        # 子树本身，返回不分叉时候的最大值
        def dfs(node):
            nonlocal res
            
            if not node:
                return 0
            
            leftMax = max(dfs(node.left), 0)
            rightMax = max(dfs(node.right), 0)
            res = max(res, node.val + leftMax + rightMax)

            return node.val + max(leftMax, rightMax)
        
        dfs(root)
        return res
```

[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) 之后要看看heap的方法
bucket sort
i(count)  0 |  1  | 2  | 3 | 4 | 5 | ... | len(input) 
values       [100]     [1,2]

用map来记录num: count的数量，之后构建一个count:values的array，；最后array从后往前往res里加，直到len(res) == k；构建array: freq = [[] for i in range(len(nums) + 1)]; 从后往前遍历: for i in range(len(freq) -1, 0, -1)
时间：O(N)
空间：O(N)
```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # count: {each num:freq}
        count = collections.Counter(nums)
        # freq: [freq:[nums have same freq]]
        freq = [[] for i in range(len(nums) + 1)] # 大小是len(nums) + 1，注意如何构建values是list的list

        for n, count in count.items():
            freq[count].append(n)

        res = []
        for i in range(len(freq) -1, 0, -1): # 注意如何从后往前遍历
            for n in freq[i]:
                res.append(n)
                if len(res) == k:
                    return res 
```

heap
O(KlogN)
```python
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # count: {each num:freq}
        count = collections.Counter(nums)
        
        maxHeap = []
        
        for key in count:
            heapq.heappush(maxHeap, (-count[key], key))
        
        res = []
        while k > 0:
            value, key = heapq.heappop(maxHeap)
            res.append(key)
            k -= 1
        return res
```



[236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

自下而上，这个函数就返回自己左右子树满足条件的node：返回自己或者不为None的一边。base case就是找到了
时间：O(N)
空间：O(N)

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if root is None: # base case
            return root
        if root == p or root == q: # base case
            return root

        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)
        
        if left and right:
           return root
        else:
            return left or right
```

[560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

Input: nums = [1,1,1], k = 2
Output: 2

思路一：
双指针，第一个指针一次走一步，另一个指针走到头
时间：O(n^2)
空间：O(1)

用一个{}存{preSum:count}前缀和以及出现的次数，同时把{0:1}放进去，然后每次看curSum-K在不在map里，在的话就res+=count；更新res：res+=preSum.get(diff, 0); 形成{preSum:count}的dict：preSum[curSum]=1+preSum.get(curSum, 0)
时间：O(N)
空间：O(N)
```python
class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        res = 0
        curSum = 0
        prefixSums = { 0 : 1 }

        for n in nums:
            curSum += n
            diff = curSum - k

            res += prefixSums.get(diff, 0)
            prefixSums[curSum] = 1 + prefixSums.get(curSum, 0)

        return res 
```

[227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/)

用Stack存数字，每次如果是+-就直接压进去，如果是*/就压进去相对应的数，最后弹栈相加；-3//2地板除会得到-2而不是想要的-1，所以用int(-3/2)这样就可以得到-1;检查是否是数字: s[i].isdigit()；把长串string转成对应的数字num=num*10+int(s[i]);如果是"+-*/": if s[i] in "+-*/"；sign的条件：如果是sign或者走到最后一位；相加stack的所有数字：sum(stack)；每次检查完sign之后要更新num和sign；最后还有把最后的数放进stack里
时间：O(N)
空间：O(N)

```python
class Solution:
    def calculate(self, s):
        def update(sign, num):
            if sign == "+":
                stack.append(num)
            elif sign == "-":
                    stack.append(-num)
            elif sign == "*":
                stack.append(stack.pop()*num)
            else:
                stack.append(int(stack.pop()/num))

        idx, num, stack, sign = 0, 0, [], "+"
        while idx < len(s):
            if s[idx].isdigit():
                num = num * 10 + int(s[idx])
            elif s[idx] in "+-*/":
                update(sign, num)
                num = 0
                sign = s[idx]
            idx += 1
        update(sign, num)
        return sum(stack)
```

[224. Basic Calculator](https://leetcode.com/problems/basic-calculator/)

当看到"("就从下一位call自己，看到")"就返回"()"之间的值

```python
class Solution:
    def calculate(self, s):
        def update(sign, num):
            if sign == "+":
                stack.append(num)
            elif sign == "-":
                stack.append(-num)
        
        idx, num, stack, sign = 0, 0, [], "+"
        while idx < len(s):
            if s[idx].isdigit():
                num = num * 10 + int(s[idx])
            elif s[idx] in "+-":
                update(sign, num)
                num = 0
                sign = s[idx]
            elif s[idx] == "(":
                num, j = self.calculate(s[idx + 1:]) # 需要返回（）内部的值和当前的idx位置
                idx = idx + j
            elif s[idx] == ")":
                update(sign, num)
                return sum(stack), idx + 1 # 看到了“）”，返回（）里面的值和idx的位置
            idx += 1                       # 别忘了idx += 1
        update(sign, num)                  # 别忘了最后一个数的处理
        return sum(stack)
```

[772. Basic Calculator III](https://leetcode.com/problems/basic-calculator-iii/)

```python
class Solution:
    def calculate(self, s: str) -> int:
        def update(sign, num):
            if sign == "+":
                stack.append(num)
            elif sign == "-":
                stack.append(-num)
            elif sign == "*": # BC II,III
                stack.append(stack.pop() * num)
            elif sign == "/": # BC II,III
                stack.append(int(stack.pop() / num))
        
        idx, num, stack, sign = 0, 0, [], "+"
        
        while idx < len(s):
            if s[idx].isdigit():
                num = num * 10 + int(s[idx])
            elif s[idx] in "+-*/":
                update(sign, num)
                sign = s[idx]
                num = 0
            elif s[idx] == "(": # BC I,III
                num, j = self.calculate(s[idx+1:])
                idx += j
            elif s[idx] == ")": # BC I,III
                update(sign, num)
                return sum(stack), idx + 1
            idx += 1
        update(sign, num)
        return sum(stack)
```

[50. Pow(x, n)](https://leetcode.com/problems/powx-n/)

Input: x = 2.00000, n = 10
Output: 1024.00000

如果n < 0; n % 2 ==1, 返回x * pow(x, n-1); 如果是偶数返回pow(x*x, n/2)
A = x^n
n是偶数：x^2n = A * A
n是奇数：x^2n = A * A * x
时间O(logn)
空间O(logn)
```python
# if n % 2 == 0
# x^n = x**2 ^ (n/2)
# elif
# x^n = x * x^(n-1)

def pow(x, n):
    if n == 0:
        return 1
    if n < 0:
        return pow(1/x, -n)
    if n % 2 == 1:
        return x * pow(x, n-1)
    else:
        return pow(x * x, n /2)
```

[215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)
    

heapify这个array到minHeap：O(N)，然后pop()共n+1-k次=>时间O(N+(n+1-k)logN)

用minHeap，size总是k，这样堆顶就是kth largest，然后一个一个pop()共n+1-k次，每次pop()时间是logN
时间 O(N+Nlogk) 每次pop数字需要O(logk)，一共n次
空间 O(K)

```python

class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        minHeap = []
        for n in nums:
            minHeap.append(n)
        
        heapq.heapify(minHeap) # time: O(n)
        
        # 第2大，一共6个数字，就是第5小
        k = len(nums) + 1 - k
        
        while k > 1:
            heapq.heappop(minHeap)
            k -= 1
        
        return minHeap[0]    

```

用maxHeap；注意这个时候用heapq._heapify_max(maxHeap)和heapq._heappop_max(maxHeap)来进行对应的操作

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        maxHeap = []
        for n in nums:
            maxHeap.append(n)
        
        heapq._heapify_max(maxHeap) # time: O(n)
        
        while k > 1:
            heapq._heappop_max(maxHeap)
            k -= 1
        
        return maxHeap[0]
        
```

partition: cut to two halves，左边的数都比右边的小，pivot就选最右的数，这个数字就是左右两边数的分界: p从最左index开始一直往右走，如果这个数比pivot小，那就放进来，然后p+=1，最后把p和pivot互唤，效果就是pivot左边的数都比pivot小

Quickselect
时间 O(N)；如果每次的pivot都刚好是最大值，那每次都需要走一遍，所以那就是O(N^2)
空间 O(1)
```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        k = len(nums) - k # 把k变成sorted情况下的第k位

        # return p, p is pth smallest in nums     
        def partition(l, r):
            pivot, p = nums[r], l

            # nums before < nums[p] < nums after, based on pivot
            for i in range(l, r):
                if nums[i] <= pivot: # 如果当前这个数<=pivot，就放到左边
                    nums[p], nums[i] = nums[i], nums[p] # python不用一个swap()
                    p += 1
                    
            # nums before < nums[r] < nums after
            nums[p], nums[r] = nums[r], nums[p]
            return p

        def select(l, r): # l, r告诉跑quickSelect的范围
            p = partition(l, r)
            
            if k < p: 
                return select(l, p - 1)
            elif k > p:
                return select(p + 1, r)
            else:
                return nums[p]

        return select(0, len(nums) - 1)

```

[71. Simplify Path](https://leetcode.com/problems/simplify-path/)


用stack: 对于每个如果是.或者空，忽略; 如果是..，当非空的时候弹栈; 如果是文件夹，直接放进去; 分割: for part in path.split("/")；最后返回"/" + "/".join(stack)
时间：O(N)
空间：O(N)

```python
class Solution:
    def simplifyPath(self, path: str) -> str:
        stack = []
        for part in path.split("/"):
            if part == "..":
                if stack:
                    stack.pop()
            elif part == "." or not part: # 如果是空格
                continue
            else: # 说明是文件名字
                stack.append(part)
        res = "/" + "/".join(stack)
        return res
```

[973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)


minHeap，先计算每个点的距离。放进minHeap，然后pop K次; 初始化minHeap.append([dist, x, y]); heap化:heapq.heapify(minHeap)，弹出dist, x, y = heapq.heappop(minHeap)

时间：O(N + KlogN)
空间：O(K)
```python
class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        minHeap = []
        for x, y in points:
            dist = (x ** 2) + (y ** 2)
            minHeap.append([dist, x, y]) # dist是minHeap的key
        
        heapq.heapify(minHeap)
        res = []
        while k > 0:
            dist, x, y = heapq.heappop(minHeap)
            res.append([x, y])
            k -= 1
        return res
```

Quick select
```python
class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        
        def get_dist(point):
            return point[0] ** 2 + point[1] ** 2
        
        # return p, p is pth smallest in points
        def partition(l, r):
            pivot = points[r]
            p = l
            pivot_dist = get_dist(pivot)
            
            for i in range(l, r):
                if get_dist(points[i]) <= pivot_dist:
                    points[i], points[p] = points[p], points[i]
                    p += 1
            
            # before < get_dist(points[r]) < after
            points[r], points[p] = points[p], points[r]
            return p
        
        def select(l, r):
            if l > r:
                return points
            p = partition(l, r)

            if k < p:
                return select(l, p - 1)
            elif k > p:
                return select(p + 1, r)
            else:
                return points[:k]
        
        return select(0, len(points) - 1)
```

[791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/)

先统计s各个字母出现次数，然后根据order的顺序放进res里，最后把不在order里但在s里的放进去 ：统计次数：count = collections.Counter(s), count[c]就会返回char在s出现的次数；res.append(c * counter(c))

时间：O(len(s)+len(order))
空间：O(len(s))

```python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        count = collections.Counter(s) # count[char] will be the number of occurrences of 'char' in T.
        res = []
        # 走一遍order，根据order把对应的char更新到res里
        for c in order:
            res.append(c * count[c])
            count[c] = 0 # 因为最后还要把不在order里但在s里的放进去
        # 走一遍count，把不在order里但在s里的放进去    
        for c in count:
            res.append(c * count[c])
        return "".join(res)
```

[65. Valid Number](https://leetcode.com/problems/valid-number/)


要处理的东西 1. digits; 2. sign('+', '-')：必须出现在开头，或者紧跟在'e', 'E'后面; 3. exponent：必须前后都有digit，同时只出现一次； 4. dot：前面没有过expo，同时只出现一次；5. other

时间：O(N)
空间：O(1)
```python
class Solution:
    def isNumber(self, s: str) -> bool:
        seenDigit = seenExpo = seenDot = False
        for i, c in enumerate(s): # 因为要只要before的情况，所以需要idx
            if c.isdigit():
                seenDigit = True
            elif c in "+-":
                if i > 0 and s[i-1] != "e" and s[i-1] != "E":
                    return False
            elif c in "eE":
                if seenExpo or not seenDigit:
                    return False
                seenExpo = True
                seenDigit = False
            elif c == ".":
                if seenDot or seenExpo:
                    return False
                seenDot = True
            else:
                return False
        return seenDigit
```

[56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

先根据start把intervals排序，然后一个一个看，如果重叠就更新，不重叠就加进去；排序: intervals.sort(key = lambda i : i[0]); lastEnd = res[-1][1]；遍历的时候是for start, end in intervals[1:]:
时间：O(nlogn)
空间：O(logn) if sorted in place

```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort(key = lambda i : i[0])  # i代表interval，：后面表示按照排序i[0]
        res = [intervals[0]] # 一开始把第一个放进去

        for start, end in intervals[1:]: # 注意是 in intervals[1:]
            lastEnd = res[-1][1]
            if start <= lastEnd:
                res[-1][1] = max(lastEnd, end)
            else:
                res.append([start, end])
        
        return res 
```

[8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/)

用sign, res, idx，idx一直往后走，先删除空格，然后记录正负号， 最后处理数字，每次数字检查是否越界，否则res = 10*res + curDigit；越界的条件>INT_MAX // 10，或者== INT_MAX同时curDit>INT_MAX % 10，越界了就返回最大值或者最小值；最大值pow(2, 31)-1；最小值-pow(2, 31)

时间：O(n)
空间：O(1)

```python
class Solution:
    def myAtoi(self, s: str) -> int:
        sign = 1
        res = 0
        idx = 0
        n = len(s)
        
        INT_MAX = pow(2, 31) - 1
        INT_MIN = -pow(2, 31)
        
        # 先删除空格
        while idx < n and s[idx] == " ":
            idx += 1
        
        # 判断正负号
        if idx < n and s[idx] == "+":
            sign = 1
            idx += 1
        elif idx < n and s[idx] == "-":
            sign = -1
            idx += 1
    
        # 接下来都是数字
        while idx < n and s[idx].isdigit():
            digit = int(s[idx])
            # 检查过大或过小：如果过大或过小，就返回最大值或最小值
            # 1. res > Integer.MAX_VALUE / 10肯定会在下一个超过
            # 2. res < Integer.MAX_VALUE / 10就不用担心
            # 3. res == Integer.MAX_VALUE / 10，只有0-7可以满足，因为Integer.MAX_VALUE % 10 = 7
            if (res > INT_MAX // 10) or (res == INT_MAX // 10 and digit > INT_MAX % 10):
                return INT_MAX if sign == 1 else INT_MIN
            res = 10 * res + digit
            idx += 1
        return sign * res
```



[21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

用两个指针分别往后走，比较小的就放进来；用一个dummy，避免插入到空的链表中；需要注意其中一个没走到头。；别忘了更新自己pre = pre.next

时间：O(N+M)
空间：O(1)
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(-1)
        pre = dummy
        while l1 and l2:
            if l1.val <= l2.val:
                pre.next = l1
                l1 = l1.next
            else:
                pre.next = l2
                l2 = l2.next
            pre = pre.next
        
        pre.next = l1 if l1 is not None else l2
        
        return dummy.next
```



[23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

类似mergesort，当len(lists)>1的时候两两个分别比较：用一个新的onepassMerge[]然后merge到自己，这样lists的大小越来越小，时间O(logK * N)：比较了logK次，每次O(N)：最后返回lists[0]；每次走2步：for i in range(0, len(lists), 2);要判断l2不越界：l2 = lists[i + 1] if (i+1)<len(lists) else None

时间：O(NlogK)
空间：O(1)
```python
class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        if not lists or len(lists) == 0: # 检查是否符合条件：空或者不是list
            return None

        while len(lists) > 1:
            mergedLists = []

            for i in range(0, len(lists), 2): 
                l1 = lists[i]
                l2 = list[i + 1] if (i + 1) < len(lists) else None # edge case
                mergedLists.append(self.mergeList(l1, l2)) 

            lists = mergedLists
       
        return lists[0]


    def mergeList(self, l1, l2):
        dummy = ListNode(-1)
        pre = dummy

        while l1 and l2:
            if l1.val  <= l2.val:
                pre.next = l1
                l1 = l1.next
            else:
                pre.next = l2
                l2 = l2.next
            pre = pre.next
        
        if l1:
            pre.next = l1
        elif l2:
            pre.next = l2
        
        return dummy.next


```

[1891. Cutting Ribbons](https://leetcode.com/problems/cutting-ribbons/)

转化思路，题目要求最多切成n次，那n=1到max(ribbon)，这样满足条件的是n最大的那个时候，相当于每次都看是否满足条件，直到找到最后满足条件的值。可以用二分查找找最右侧边界；count >= k是满足的条件，count表示可以提供的数量；最后return right，因为跳出的时候left = right + 1了
时间：O(Nlog(max(Length))) 
空间：O(1)
```python
class Solution:
    def maxLength(self, ribbons: List[int], k: int) -> int:
        left = 1
        right = max(ribbons)
        
        while left <= right: # 左闭右闭
            mid = left + (right - left) // 2
            if self.isValid(ribbons, k, mid):
                left = mid + 1
            else:
                right = mid - 1
        
        return right # 最后要return right，因为while的终止条件是left += 1
    
    def isValid(self, ribbons, k, mid):
        count = 0
        for num in ribbons:
            count += num // mid
        return count >= k  # 满足的情况，count表示可以提供的数量
```

[146. LRU Cache](https://leetcode.com/problems/lru-cache/)

需要记录的：capacity;Node: key, val, pre, next; LRU class: cap, left, right, cache还要把left, right连起来 ;如果要get在O(1)：HashMap：{val: pointer to the node}；用left, right pointer来记录LRU和Most freqently used：double linkedlist;当第三个node来了：更新hashMap， 更新left, right pointer，更新第二使用的node和这个node的双向链接；每次get: 删除，添加操作；每次put：如果存在要删除，总要添加操作，如果大小不够，就找到lru(最左），然后删除

```python

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.cap = capacity
        self.cache = {} # map key to node

        self.left, self.right = Node(0, 0), Node(0, 0)
        self.left.next, self.right.prev = self.right, self.left # left = LRU, right = most recent

    # remove from the linkedlist
    def remove(self, node):
        prev, next = node.prev, node.next
        prev.next, next.prev = next, prev

    # insert node at right
    def insert(self, node):
        # insert at the right most position, before the right pointer
        prev, next = self.right.prev, self.right
        prev.next = next.prev = node
        node.next, node.prev = next, prev

    def get(self, key: int) -> int:
        if key in self.cache:
            # remove + insert为了更新顺序
            self.remove(self.cache[key])
            self.insert(self.cache[key])
            return self.cache[key].val
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.remove(self.cache[key]
        node = Node(key, value)
        self.cache[key] = node
        self.insert(node)

        if len(self.cache) > self.cap:
            # remove from the list and delete the LRU from the hashmap
            lru = self.left.next
            self.remove(lru)
            del self.cache[lru.key]



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
```

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

2个[]，分别存所有的start和end；双指针比较，如果start<end就count+=1同时start往后走；如果start>=end，就移动end的指针同时count -= 1；双指针走到头的情况就是start到头了；[]存start的方法：sorted([i[0] for i in intervals])

时间：O(NlogN)
空间：O(N)

```python
class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        start = sorted([i[0] for i in intervals])
        end = sorted([i[1] for i in intervals])

        res, count = 0, 0

        s, e = 0, 0

        while s < len(intervals):
            if start[s] < end[e]:
                count += 1
                s += 1
                res = max(res, count)
            else:
                e += 1
                count -= 1
        
        return res

```

[88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)

三个指针，每次把更小的数往前走，注意走到头的情况

时间：O(M+N)
空间：O(1)

```python
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        
        # Set p1 and p2 to point to the end of their respective arrays.
        p1 = m - 1
        p2 = n - 1
    
        # And move p backwards through the array, each time writing
        # the smallest value pointed at by p1 or p2.
        for p in range(n + m - 1, -1, -1):
            if p2 < 0:
                break
            if p1 >= 0 and nums1[p1] > nums2[p2]:
                nums1[p] = nums1[p1]
                p1 -= 1
            else:
                nums1[p] = nums2[p2]
                p2 -= 1
                
```
[249. Group Shifted Strings](https://leetcode.com/problems/group-shifted-strings/)

用map来存{(diff):[oneRes]}:diff是字母之间的区别比如:{(1,1):["abc", "efg"]}，最后直接导出list(map.values())就可以；key用元组：key = ()，添加的时候是key += (diff % 26,)；取得字母之间的区别：diff = ord(s[i+1]) - ord(s[i])；

时间：O(N*K), N is len(strings), K is max(len(one s))
空间：O(N*K), N is len(strings), K is max(len(one s))

```python

def groupStrings(self, strings: List[str]) -> List[List[str]]:
    """
    diff_res={diff:one_res}
    "abc", "bcd" -> diff = (1,1)
    """   
    diff_res = collections.defaultdict(list)

    # 对于每一个s：每一个字母串abd；或者dcg
    for s in strings:
        key = ()
        # 算出来这个字母串的key，然后添加到对应的map里
        for i in range(len(s) - 1):
            diff = ord(s[i+1]) - ord(s[i])
            key += (diff % 26,)
        diff_res[key].append(s)
    
    return list(diff_res.values())
```


[670. Maximum Swap](https://leetcode.com/problems/maximum-swap/)

先把num变成一个list，从后往前，i是index，如果这个值更小，就说明可以和max_idx互换，就把他们换一下；如果这个值更大，就说明更新max_idx；最后把list转换成num；num变成list：num = [int(x) for x in str(num)]；list变num：int("".join([str(x) for x in num])

时间：O(N)
空间：O(N)

```python
class Solution:
    def maximumSwap(self, num: int) -> int:
        num = [int(x) for x in str(num)]
        max_idx = len(num) - 1
        
        x_min = 0
        x_max = 0
        
        # 从后往前，i是index，如果这个值更小，就说明可以和max_idx互换，就把他们换一下；如果这个值更大，就说明更新max_idx
        for i in range(len(num) - 1, -1, -1):
            # 如果这个值更大，就更新max的idx
            if num[i] > num[max_idx]:
                max_idx = i
            # 如果这个值更小，就说明可以和max_idx互换，就把他们换一下
            elif num[i] < num[max_idx]:
                x_min = i
                x_max = max_idx
        
        num[x_min], num[x_max] = num[x_max], num[x_min]
        
        return int("".join([str(x) for x in num]))
```


[138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)

Two Passes: 第一遍只复制node，不管指针，形成一个map{old : new}；第二遍把node的指针连起来；注意连的map里没考虑最后是None的情况，所以一开始map={ None : None}；遍历是while cur

时间：O(N)
空间：O(N)

```python
class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        oldToCopy = {None : None} # 为了在复制的时候，如果cur.next是None， copy.next也可以是None

        cur = head
        while cur:
            copy = Node(cur.val)
            oldToCopy[cur] = copy
            cur = cur.next
        
        cur = head
        while cur:
            copy = oldToCopy[cur]
            copy.next = oldToCopy[cur.next]
            copy.random = oldToCopy[cur.random]
            cur = cur.next

        return oldToCopy[head]
```

[200. Number of Islands](https://leetcode.com/problems/number-of-islands/)

用set()记录走过的点，然后如果是“1”而且没走过，就从该点开始bfs()：把这个点放到queue里，然后如果queue不为空，就走上下左右四个方向，四个方向走的条件是不越界，是“1”而且没走过；把2d的点放进queue的办法：queue.append((r, c))；visit = set(), visit.add((r, c))；最开始检查是否是grid: if not grid: return 0；上下左右到处走的条件: for dr, dc in dirs: r, c = row + dr, col + dc; if (r in range(rows) and c in range(cols) and grid[r][c] == "1" and (r, c) not in visit

时间：O(M*N)
空间：O(min(M, N))

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid:
            return 0
        
        rows, cols = len(grid), len(grid[0])
        visit = set()
        count = 0

        def bfs(r, c):
            queue = collections.deque()
            visit.add((r, c))
            queue.append((r,c))

            while queue:
                row, col = queue.popleft()
                directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]

                for dr, dc in directions:
                    r, c = row + dr, col + dc
                    if (r in range(rows) and
                        c in range(cols) and
                        grid[r][c] == "1" and
                        (r, c) not in visit):
                        queue.append((r, c))
                        visit.add((r, c))

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == "1" and (r, c) not in visit:
                    bfs(r, c)
                    count += 1
        return count

```

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        rows, cols = len(grid), len(grid[0])
        visit = set()
        
        def dfs(r, c):
            if (r < 0 or r == rows or c < 0 or c == cols or (r, c) in visit or grid[r][c] != "1"):
                return
            
            visit.add((r, c))
            dfs(r + 1, c)
            dfs(r - 1, c)
            dfs(r, c + 1)
            dfs(r, c - 1)
            
        count = 0
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == "1" and (r, c) not in visit:
                    dfs(r, c)
                    count += 1
        
        return count
```



[133. Clone Graph](https://leetcode.com/problems/clone-graph/)

HashMap:{oldNode:newNode}；dfs(node)返回node对应的copy， 每次如果在map里面就直接返回copy后的node，如果不在就copy然后copy自己的neighbors；复制neighbors: for nei in node.neighbors: copy.neighbors.append(dfs(nei))

时间：O(V+E)
空间：O(V)

```python
class Solution:
    def cloneGraph(self, node: 'Node') -> 'Node':
        if node is None:
            return None
        oldToNew = {}

        def dfs(node):
            if node in oldToNew:
                return oldToNew[node]

            copy = Node(node.val)
            oldToNew[node] = copy

            for nei in node.neighbors:
                copy.neighbors.append(dfs(nei))
            
            return copy
        
        return dfs(node)
```

[695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)

在每个点都做一遍dfs，一个dfs返回这个点对应岛的大小：因为每个点都走一遍，所以判断是否是0就在base case来判断；dfs返回值：return (1 + dfs(r + 1, c) + dfs(r - 1, c) + dfs(r, c + 1) + dfs(r, c - 1))；每个点走的时候就area = max(area, dfs(r, c))

时间：O(M*N)
空间：O(M*N)
```python
class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        visit = set()

        # dfs返回area
        def dfs(r, c):
            # base case
            if (r < 0 or r == rows or c < 0 or c == cols or 
            grid[r][c] == 0 or (r, c) in visit):
                return 0
            
            visit.add((r, c))

            return (1 + dfs(r + 1, c) +
                        dfs(r - 1, c) +
                        dfs(r, c + 1) +
                        dfs(r, c - 1))
        
        area = 0
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1 and (r, c) not in visit:
                    area = max(area, dfs(r, c))

        return area
```

[417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

分别从pacific和atlantic ocean来找能到的点，而不是从每个点出发来找能否同时到ocean；然后同时pacific和Atlantic能到的点就是要找的点：从第一行来看其他行能否走到Pacific ocean， 从最后一行开始来看其他行能否走到Atlantic ocean：能否走到的条件就在base case里: heights[r][c] < preHeight；dfs参数：dfs(r, c, visited, preHeight)

时间：O(N*M)
空间：O(N*M)

```python
class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        rows, cols = len(heights), len(heights[0])
        pac, atl = set(), set()

        def dfs(r, c, visit, prevHeight):
            if ((r, c) in visit or r < 0 or r == rows or c < 0 or c == cols or heights[r][c] < prevHeight):
                return
            visit.add((r, c))
            dfs(r + 1, c, visit, heights[r][c])
            dfs(r - 1, c, visit, heights[r][c])
            dfs(r, c + 1, visit, heights[r][c])
            dfs(r, c - 1, visit, heights[r][c])

        for c in range(cols): 
            dfs(0, c, pac, heights[0][c]) # 走第一行
            dfs(rows - 1, c, atl, heights[rows - 1][c]) # 走最后一行
        
        for r in range(rows):
            dfs(r, 0, pac, heights[r][0]) # 第一列
            dfs(r, cols - 1, atl, heights[r][cols - 1]) # 最后一列
        
        res = []
        for r in range(rows):
            for c in range(cols):
                if (r, c) in pac and (r, c) in atl:
                    res.append([r, c])
        
        return res
```

[286. Walls and Gates](https://leetcode.com/problems/walls-and-gates/)

从Gate来做bfs。先把Gate都放在queue和Visit里面，然后逐层bfs，每一层bfs做的就是取出来queue的一层的值然后变成dist，然后addRoom周围四个点，一层走完dist += 1

时间：O(M*N)
空间：O(M*N)

```python
class Solution:
    def wallsAndGates(self, rooms: List[List[int]]) -> None:
        """
        Do not return anything, modify rooms in-place instead.
        """
        rows, cols = len(rooms), len(rooms[0])
        visit = set()
        queue = collections.deque()

        def addRoom(r, c):
            if (r < 0 or r == rows or c < 0 or c == cols or (r, c) in visit or rooms[r][c] == -1):
                return 
            visit.add((r, c))
            queue.append((r, c))

        for r in range(rows):
            for c in range(cols):
                if rooms[r][c] == 0:
                    queue.append([r, c])
                    visit.add((r, c))
        
        dist = 0
        while queue:
            size = len(queue)
            for i in range(size): # Gate layer
                r, c = queue.popleft()
                rooms[r][c] = dist
                addRoom(r + 1, c) # 把周围四个room放到queue里
                addRoom(r - 1, c)
                addRoom(r, c + 1)
                addRoom(r, c - 1)
            dist += 1 # 第二层: 距离Gate为1的layer

```

[127. Word Ladder](https://leetcode.com/problems/word-ladder/)

先用nested loop建一个adjacent list：O(N*M ^ 2)，然后用BFSO(N^2\*M)；adj: {pattern: [words]} : {\*ot: [hot, dot, lot]}；找pattern = word[:j] + "*" + word[j+1:]；res起点是1；visited和queue一开始要把beginWord放进来：visit.add([beginWord]); queue.append([beginWord])

时间：O(M^2*N), M is len(word)
空间：O(M^2*N)

```python
class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        if endWord not in wordList:
            return 0
        
        nei = collections.defaultdict(list)
        wordList.append(beginWord)
        
        # 对于wordList的每个word，把每一个pattern都找到，然后构建adj
        for word in wordList:
            for j in range(len(word)):
                pattern = word[:j] + "*" + word[j+1:]
                nei[pattern].append(word)
        
        visit = set([beginWord])
        queue = collections.deque([beginWord])
        res = 1
        
        while queue:
            for i in range(len(queue)):
                word = queue.popleft()
                if word == endWord:
                    return res
                
                # 对于每一个word, 如果在map对应的pattern里面，说明是一个选择，就queue加进去visited加进去
                for j in range(len(word)):
                    pattern = word[:j] + "*" + word[j+1:]
                    for neiWord in nei[pattern]:
                        if neiWord not in visit:
                            visit.add(neiWord)
                            queue.append(neiWord)
            res += 1
        
        return 0
```

[778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water/)

Dijkstra's：用一个minHeap存Front-tier:(Height, r, c)，再用一个visit来存走过的，每次存的Height是见过的最大值；每次minHeap pop出来之后看四周的neighbor；Heap取出来数字：heapq.heappop(minH)；Heap加东西：heapq.heappush(minH, [要加的])

时间：O(N^2logN)
空间：O(N^2)

```python
class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        N = len(grid)
        visit = set()
        minH = [[grid[0][0], 0, 0]]  # (max_height, r, c)
        visit.add((0,0))
        dirs = [[0, 1], [0, -1], [-1, 0], [1, 0]]

        while minH:
            height, r, c = heapq.heappop(minH) # 总是弹出来当前的最小值的点

            if r == N - 1 and c == N - 1:
                return height
            
            for dr, dc in dirs:
                neiR, neiC = r + dr, c + dc
                if neiR in range(N) and neiC in range(N) and (neiR, neiC) not in visit:  
                    visit.add((neiR, neiC))
                    heapq.heappush(minH, [max(height, grid[neiR][neiC]), neiR, neiC])
        
```

[269. Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) 先做LC953

先建adj{ch:set()}：两两word比较，得到两两letter的顺序；之后用postDFS放进来，DFS需要一个visit{ch:T/F}，每次看是否在里面，在的话就返回visit[c]，然后在dfs内部看ch的nei，如果dfs(nei)返回true就说明这条路不通，最后把res加进去；从adj的任意一个ch走dfs，最后reverse这个结果

时间：O(M), M is number of char of words，决定了graph大小
空间：O(1)

```python
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        # adj list存储两两字母之间的order
        # 对于words里的每个w，对于w里的每个character
        # {c : set()}
        adj = {c:set() for w in words for c in w}

        for i in range(len(words) - 1):
            w1, w2 = words[i], words[i+1]
            minLen = min(len(w1), len(w2))
            if len(w1) > len(w2) and w1[:minLen] == w2[:minLen]:
                return ""
            for j in range(minLen):
                if w1[j] != w2[j]:
                    adj[w1[j]].add(w2[j])
                    break
        
        # DFS来遍历postorder，根据排好的顺序来画图
        # 用visit来看是否有loop->
        # visit{character: False/True} 给每个字母一个映射
        # False说明已经visit过了
        # True说明是在当前路径里
        visit = {} # False = visited, True = visited & current path
        res = []

        # post-order traversal
        def dfs(c): # 如果dfs返回True，说明这个ch已经看过并且是在当前路径，也就是cycle
            if c in visit:
                return visit[c] #如果返回true: cycle

            visit[c] = True

            for nei in adj[c]: # 看这个ch的每一个neighbor
                if dfs(nei):
                    return True
            
            visit[c] = False # 已经看过，但是不在当前路径了就
            res.append(c)
        
        for c in adj:
            if dfs(c):
                return ""
        
        res.reverse()
        return "".join(res)
```

[332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/)

建立adj list{source:[des]]}，同时[des]需要排好序；更简单的是先把input排序，然后放进adj，这样就自动排好序了；建好表之后DFS：返回能否找到validPath，从每个src走，然后遍历这个src的nei；遍历nei的时候回溯；删除特定位置的值adj[src].pop(idx)，在特定位置插入adj[src].insert(idx, nei)

时间：O(V+E)^2
空间：O(E)

```python
class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        adj = {src:[] for src, dst in tickets}

        tickets.sort()
        for src, dst in tickets:
            adj[src].append(dst)
        
        res = ["JFK"]
        # 返回是否找到valid path
        def dfs(src):
            if len(res) == len(tickets) + 1:
                return True
            if src not in adj:
                return False
            
            temp = list(adj[src]) # 因为需要变更adj，所以遍历一个copy
            for idx, nei in enumerate(temp):
                adj[src].pop(idx)
                res.append(nei)

                if dfs(nei):
                    return True
                
                adj[src].insert(idx, nei)
                res.pop()
            
            return False
        
        dfs("JFK")
        return res



```

[42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

对于位置i能存储的水: min(maxL, maxR) - h[i]；相向双指针，加上两个变量maxL, maxR来时刻保存左右两边的最大值；每次移动maxL, maxR之间较小那个数的指针，然后新位置i能存储的水：被移动指针之前的值-h[i]：不用考虑另外一个值，因为那个值肯定比较大；移动指针之后计算这个指针所在位置能存储的水

时间：O(N)
空间：O(1) -> two pointers
```python
class Solution:
    def trap(self, height: List[int]) -> int:
        res = 0
        if not height: # input is empty
            return res
        
        l, r = 0, len(height) - 1
        max_l, max_r = height[l], height[r]
        
        while l < r:
            if max_l < max_r:
                l += 1
                max_l = max(max_l, height[l])
                res += max_l - height[l]
            else:
                r -= 1
                max_r = max(max_r, height[r])
                res += max_r - height[r]
        
        return res
```

[543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)

从leaf来找每个节点的diameter和height；最下面的leaf的height=0；Diameter = leftHeight + rightHeight + 1 + 1(两个edge);dfs()返回树高max(leftHeight, rightHeight) + 1

时间：O(N)
空间：O(1)
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        res = 0

        def height(node):
            nonlocal res
            if not node:
                return -1 # return height of an empty tree
            
            left_hei = height(node.left) # 找到左子树的高
            right_hei = height(node.right)
            
            # the "2+" accounts for the edge on the left plus the edge on the right. 
            diameter = 2 + left_hei + right_hei
            res[0] = max(res, diameter)

            return 1 + max(left_hei, right_hei)
        
        height(root)
        return res


```

[523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

map:{remainder:end_index} remainder是前缀和的mod的结果，最开始map={0:-1}这样第一个数如果能整除但不会返回true；对于每个数，计算前缀和的mod结果，如果不在map里就加进来，如果在的话：只有距离大于1才是真的满足

时间：O(N)
空间：O(N)

```python
class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        remainder = {0: -1} # map remainder -> end index
        presum = 0
        
        for i, n in enumerate(nums):
            presum += n
            r = presum % k
            if r not in remainder:
                remainder[r] = i
            elif i - remainder[r] > 1: # r在remiander里
                return True
        
        return False

```

[173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/)

inorder遍历；用一个栈，建立obj的时候就把所有左子树放进来；next()就是弹栈，然后把右子节点的左右左子树压栈；只要stack不为空，就说明hasNext()

时间：hasNext是O(1);next平均是O(1)，最坏是O(N)
空间：O(H)当树是链表时候

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator:

    def __init__(self, root: Optional[TreeNode]):
        self.stack = []

        while root:
            self.stack.append(root)
            root = root.left

    def next(self) -> int:
        node = self.stack.pop()
        cur = node.right
        while cur:
            self.stack.append(cur)
            cur = cur.left
        return node.val

    def hasNext(self) -> bool:
        return self.stack != []


# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()

```


[297. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)

preorder遍历

时间：O(N)
空间：

```python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        res = []

        def dfs(node):
            if not node:
                res.append("N")
                return
            res.append(str(node.val))
            dfs(node.left)
            dfs(node.right)
        
        dfs(root)
        return ",".join(res)

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        vals = data.split(",")
        self.i = 0

        def dfs():
            if vals[self.i] == "N":
                self.i += 1
                return None
            node = TreeNode(int(vals[self.i]))
            self.i += 1
            node.left = dfs()
            node.right = dfs()
            return node
        
        return dfs()


        

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))

```

[129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/)

preorder因为要先处理父节点，dfs()返回sum值
时间：O(N)
空间：O(H)

```python
class Solution:
    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        def dfs(cur, num):
            if not cur:
                return 0
            
            num = num * 10 + cur.val
            if not cur.left and not cur.right:
                return num
            
            left = dfs(node.left, num)
            right = dfs(node.right, num)
            
            return left + right
        
        return dfs(root, 0)

```

[647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)

从每个位置开始，两个指针从该位置往左往右走，这样只走了所有的奇数，然后再走所有的偶数

时间：O(N^2)奇数 + O(N^2)偶数
空间：O(1)

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        res = 0
        
        def countPali(s, l, r):
            res = 0
            while l >= 0 and r < len(s) and s[l] == s[r]:
                res += 1
                l -= 1
                r += 1
            return res
        
        for i in range(len(s)):
            l = r = i
            res += countPali(s, l ,r)
            l = i
            r = i + 1
            res += countPali(s, l ,r)
        
        return res
            

```


[658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/)

二分查找；r = len(arr) - k, 如果x > (arr[mid] + arr[mid+10] // 2，就l+=1

时间：O(logN)
空间：O(1)

```python
class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        l, r = 0, len(arr) - k
        
        while l < r:
            mid = l + (r - l) // 2
            # x > (arr[mid] + arr[mid+10] // 2
            if x - arr[mid] > arr[mid + k] - x:
                l = mid + 1
            else:
                r = mid
        
        return arr[l:l+k]

```

[78. Subsets](https://leetcode.com/problems/subsets/)（子集 元素无重不可复选）
   
Given an integer array nums of unique elements, return all possible subsets (the power set).
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]   

backtrack基本结构，区别有：每次调用都装进来，为了剪枝：for i in range(start, len(nums))

时间：O(N*2^N)
空间：O(N)

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        oneRes = []
        res = []
        
        def backtrack(start):
            res.append(oneRes.copy())
            
            for i in range(start, len(nums)):
                oneRes.append(nums[i])
                backtrack(i+1)
                oneRes.pop()
        
        backtrack(0)
        return res
```



[77. Combinations](https://leetcode.com/problems/combinations/) （组合 元素无重不可复选）

Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]


```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        def backtrack(start):
            if len(oneRes) == k:
                res.append(oneRes.copy())
                return
            
            for i in range(start, n + 1):
                oneRes.append(i)
                backtrack(i+1)
                oneRes.pop()
                
        oneRes = []
        res = []
        backtrack(1)
        return res

```

[46. Permutations](https://leetcode.com/problems/permutations/) 排列（元素无重不可复选）

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []
        oneRes = []
        n = len(nums)
        
        def backtrack():
            if len(oneRes) == n:
                res.append(oneRes.copy())
                return
            
            for i in range(0, n):
                if nums[i] in oneRes: # 如果没有的话，结果有[1,1,1],[1,1,2]...
                    continue
                
                oneRes.append(nums[i])
                backtrack()
                oneRes.pop()
                
        backtrack()
        return res
```


[90. Subsets II](https://leetcode.com/problems/subsets-ii/)

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

需要先进行排序，让相同的元素靠在一起，如果发现nums[i] == nums[i-1]，则跳过


```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        oneRes = []
        res = []
        
        def backtrack(start):
            res.append(oneRes.copy()) # 每次都加进来
            
            for i in range(start, len(nums)): # i = start：子集问题
                if i > start and nums[i] == nums[i-1]:
                    continue
                oneRes.append(nums[i])
                backtrack(i+1)
                oneRes.pop()
        
        backtrack(0)
        return res
```


[380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/)

用HashMap存{数字:index}，这样查看是否存在的时间是O(1)，index是用一个list存的数字对应的位置。删除的操作：拿到list里的位置，然后把list最后一个数覆盖到这个要删除的树上，然后更新hashmap里最后一个数对应的index

```python
class RandomizedSet:

    def __init__(self):
        self.num_map = {}
        self.num_list = []

    def insert(self, val: int) -> bool:
        not_in_map = val not in self.num_map
        if not_in_map:
            self.num_map[val] = len(self.num_list) # val会放到list的最后一位，然后对应的index就是len(self.num_list)
            self.num_list.append(val)
        return not_in_map

    def remove(self, val: int) -> bool:
        in_map = val in self.num_map
        if in_map:
            idx = self.num_map[val]
            last_val = self.num_list[-1]
            self.num_list[idx] = last_val
            self.num_list.pop()
            self.num_map[last_val] = idx
            del self.num_map[val]

        return in_map

    def getRandom(self) -> int:
        return random.choice(self.num_list)

    # 随机数的另一个办法
    # def getRandom(self) -> int:
    #     idx = random.randint(0, len(self.num_list) - 1)
    #     return self.num_list[idx]

# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()

```


[34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

二分查找，用一个boolean来判断是查左边还是右边，如果是左边就r=mid-1；另外用一个idx来记录最终值

```python
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        left = self.binSearch(nums, target, True)
        right = self.binSearch(nums, target, False)
        return [left, right]

    def binSearch(self, nums, target, firstPos):
        l, r = 0, len(nums) - 1
        i = -1
        while l <= r:
            mid = l + (r - l) // 2
            if target > nums[mid]:
                l = mid + 1
            elif target < nums[mid]:
                r = mid - 1
            else:
                i = mid
                if firstPos:
                    r = mid - 1
                else:
                    l = mid + 1
        return i

```

[739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)

单调递减栈（非增）monotonic decreasing stack；如果下一个数更大，就一直弹栈，直到找到能把这个数放进去；弹栈的时候就可以idx的差值就是被删除栈的output；如果下一个数更大，就压栈

时间：O(N)
空间：O(N)

```python
class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        res = [0] * len(temperatures)
        stack = [] # pair:[temp, index]

        for idx, t = enumerate(temperatures):
            while stack and t > stack[-1][0]:
                stackTemp, stackIdx = stack.pop()
                res[stackIdx] = (i - stackIdx)
            stack.append([t, idx])
        
        return res    
```

[43. Multiply Strings](https://leetcode.com/problems/multiply-strings/)

81%10=1, carry:81//10=8 用array来存结果；计算数字：取出来digit，然后res[i1+i2]+=digit, res[i1+i2+1]=res[i1+i2]//10, res[i1+i2]%=10

时间：O(N*M)
空间：O(N+M)

```python 
class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        if "0" in [num1, num2]:
            return "0"
        
        res = [0] * (len(num1) + len(num2))
        num1, num2 = num1[::-1], num2[::-1] # 把num翻过来

        for i1 in range(len(num1)):
            for i2 in range(len(num2)):
                digit = int(num1[i1]) * int(num2[i2])
                res[i1+i2] += digit
                res[i1+i2+1] += res[i1+i2] // 10
                res[i1+i2] = res[i1+i2] % 10
        
        res = res[::-1]

        # 处理前面是0的情况
        idx = 0
        while idx < len(res) and res[idx] == 0:
            idx += 1
        
        res = map(str, res[idx:])
        return "".join(res)
```


[76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)

用一个dict记录每个字母的出现次数，然后用counter记录剩下需要的，滑动窗口如果dict剩余的次数>0就counter-=1，每次也要更新dict；初始化countMap = collections.Counter(t)

时间：O(S+T)
空间：O(S+T)

```python
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        countMap = collections.Counter(t)
        
        l = r = 0
        minStart = 0
        minLen = float("inf")
        counter = len(t)
        
        while r < len(s):
            ch = s[r]
            if countMap[ch] > 0:
                counter -= 1
            countMap[ch] -= 1
            r += 1
            
            while counter == 0:
                if minLen > r - l:
                    minStart = l
                    minLen = r - l
                ch2 = s[l]
                countMap[ch2] += 1
                if countMap[ch2] > 0:
                    counter += 1
                l += 1
        
        return s[minStart: minStart + minLen] if minLen != float("inf") else ""
        
```

[426. Convert Binary Search Tree to Sorted Doubly Linked List](https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/)

inorder遍历。用一个全局的prev来作为prev; 用dummy来防止为空的情况；nonlocal prev来帮助遍历里面；最后把最开始和最后的连起来: dummy.right.left = prev, prev.right = dummy.right

时间：O(N)
空间：O(N)

```python
class Solution:
    def treeToDoublyList(self, root: 'Optional[Node]') -> 'Optional[Node]':
        dummy = Node(-1)
        prev = dummy
        
        def inorder(node):
            nonlocal prev
            if not node:
                return
            inorder(node.left)
            prev.right = node
            node.left = prev
            prev = node
            inorder(node.right)
        
        inorder(root)
        dummy.right.left = prev
        prev.right = dummy.right
        
        return dummy.right
```

[31. Next Permutation](https://leetcode.com/problems/next-permutation/)

从后往前找，找到下降序列左边的值k；找到k右边最后一个比k小的数，交换；把k右边的数倒序排列

时间：O(N)
空间：O(1)
```python
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        i = len(nums)-1
        
        # step1: find the last "ascending" position: k
        while i > 0 and nums[i-1] >= nums[i]:
            i -= 1 
        if i == 0:   # nums are in descending order
            nums.reverse()
            return 
        k = i - 1   
        
        # step2: 找到k右边比k小的第一个数，和k交换
        j = len(nums) - 1
        while nums[j] <= nums[k]:
            j -= 1 
        nums[k], nums[j] = nums[j], nums[k]  
        
        # step3: 把k右边的树倒序排列
        l, r = k+1, len(nums)-1  
        while l < r:
            nums[l], nums[r] = nums[r], nums[l]
            l +=1 ; r -= 1

```


[827. Making A Large Island](https://leetcode.com/problems/making-a-large-island/)

先计算每个点对应island的area的值，然后计算所有0的附近左右岛面积的和；计算每个点对应island的值的时候，只覆盖该点的岛屿的idx，然后用map映射岛的大小idx_area{idx:area}。等计算0周围的和的时候，就可以直接用set来看是否是不同的岛，然后用idx_area[idx]来相加;res = max(idx_area.values() or [0])

时间：O(N*N)
空间：O(N*N)

```python 
class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        N = len(grid) # size of N*N
        idx = 2
        # idx_area -> {idx : area}
        idx_area = collections.defaultdict(int)
        dirs = [[1,0],[-1,0],[0,1],[0,-1]]

        # dfs把每个位置都变成了该小岛的idx
        def dfs(r, c, idx):
            if r < 0 or r == N or c <  0 or c == N or grid[r][c] != 1:
                return
            grid[r][c] = idx
            idx_area[idx] += 1
            for dr, dc in dirs:
                dfs(r + dr, c + dc, idx)
        
        for r in range(N):
            for c in range(N):
                if grid[r][c] == 1:
                    dfs(r, c, idx)
                    idx += 1
        
        res = max(idx_area.values() or [0])
        # 对于所有的“0”，把四周小岛加起来
        for r in range(N):
            for c in range(N):
                if grid[r][c] == 0:
                    # 用set()，避免重复加同一个岛，set()里面放所有的idx                    
                    nei_idx = set()
                    for dr, dc in dirs:
                        nei_r, nei_c = r + dr, c + dc
                        if nei_r in range(N) and nei_c in range(N) and grid[nei_r][nei_c] != 0:
                            nei_idx.add(grid[nei_r][nei_c])
                    
                    one_res = 1
                    for idx in nei_idx:
                    # 只用加idx对应的area
                        one_res += idx_area[idx]
                    res = max(one_res, res)
        
        return res
```

[1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

就是八个方向的dfs,queue里面放dist：queue=deque([(0,0,1)])

时间：O(N*N)
空间：O(N*N)

```python
class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        n = len(grid)
        if grid[0][0] or grid[n-1][n-1]:
            return -1
        
        dirs = [[1,0],[-1,0],[0,1],[0,-1],[1,1],[1,-1],[-1,-1],[-1,1]]
        queue = collections.deque([(0,0,1)])
        visit = set()
        visit.add((0,0))
        
        while queue:
            r, c, dist = queue.popleft()
            if r == n - 1 and c == n - 1:
                return dist
            
            for dr, dc in dirs:
                nei_r, nei_c = r + dr, c + dc
                
                if nei_r in range(n) and nei_c in range(n) and grid[nei_r][nei_c] == 0 and (nei_r, nei_c) not in visit:
                    queue.append((nei_r, nei_c, dist + 1))
                    visit.add((nei_r, nei_c))
        
        return -1
```

[162. Find Peak Element](https://leetcode.com/problems/find-peak-element/)

二分查找。当nums[mid]>nums[mid+1]时候，r=mid；否则l = mid + 1

时间：O(logN)
空间：O(1)
```python
class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = l + (r - l) // 2
            if nums[mid] > nums[mid + 1]: # 左边必定有一个peak
                r = mid
            else: #右边必定有一个peak
                l = mid + 1
        return l     
```

[636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/)

用stack，如果是start，如果stack非空，就更新之前的res，然后把值放进来；如果end：取出来最后的值，更新res。如果这时候stack非空，就把这个值的[1]跳过到最新；stack存([fun_id, fun_time])

时间：O(N)
空间：O(D) D is function calls

  [0,1,2,3,4,5,6]
0  - -         -
1      - - - -
[3,4]
```python
class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        stack = []
        res = [0] * n
        
        for log in logs:
            fun_id, event, fun_time = log.split(":")
            fun_id, fun_time = int(fun_id), int(fun_time)

            # 如果是start            
            if event == "start":
                # 如果非空，就要计算之前Fun的长度
                if stack:
                    res[stack[-1][0]] += fun_time - stack[-1][1]
                # 放进来
                stack.append([fun_id, fun_time])
            
            # 如果是end
            else:
                # 计算该Fun长度，需要+1
                last_id, last_time = stack.pop()
                res[last_id] += fun_time - last_time + 1
                if stack:
                    stack[-1][1] = fun_time + 1
        
        return  res
```


[301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/)

```python
class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        # initialize a set with one element
        # set is used here in order to avoid duplicate element
        
        def isValid(s):
            count = 0
            for c in s:
                if c == '(':
                    count += 1
                elif c == ')':
                    count -= 1
                    if count < 0:
                        return False
            return count == 0
        
        level = {s}
        while True:
            valid = []
            for elem in level:
                if isValid(elem):
                    valid.append(elem)
            if valid:
                return valid
            # initialize an empty set
            new_level = set()
            # BFS
            for elem in level:
                for i in range(len(elem)):
                    new_level.add(elem[:i] + elem[i + 1:])
            level = new_level
    
        
```


1.  [721. Accounts Merge](https://leetcode.com/problems/accounts-merge/)

Here N is the number of accounts and K is the maximum length of an account.
时间：O(NKlogNK) 所有email都是同一个人的名下
空间：O(NK) adjList O(NK) visited O(NK) DFS O(NK)

1. 建adjList，对每个email，建立对应account_id的邻接表

emails_accounts_map of email to account ID
{
  "johnsmith@mail.com": [0, 2],
  "john00@mail.com": [0],
  "johnnybravo@mail.com": [1],
  "john_newyork@mail.com": [2],
  "mary@mail.com": [3]
}

2. DFS每个account，把所有有相同的连起来
3. sort并导出

https://leetcode.com/problems/accounts-merge/discuss/109161/Python-Simple-DFS-with-explanation!!! 

```python
class Solution(object):
    def accountsMerge(self, accounts):
        visited_accounts = [False] * len(accounts)
        emails_accounts_map = collections.defaultdict(list)
        res = []
        # Build up the graph.
        for i, account in enumerate(accounts):
            for j in range(1, len(account)):
                email = account[j]
                emails_accounts_map[email].append(i)
        # DFS code for traversing accounts.
        def dfs(i, emails):
            if visited_accounts[i]:
                return
            visited_accounts[i] = True
            for j in range(1, len(accounts[i])):
                email = accounts[i][j]
                emails.add(email)
                for neighbor in emails_accounts_map[email]:
                    dfs(neighbor, emails)
        # Perform DFS for accounts and add to results.
        for i, account in enumerate(accounts):
            if visited_accounts[i]:
                continue
            name, emails = account[0], set()
            dfs(i, emails)
            res.append([name] + sorted(emails))
        return res

```

[498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/)

对角线的点有相同的sum(idx)，用dict存相同sum对应的所有值: {diag:[3,5,7]}；最后反向添加：[res.append(x) for x in eles[::-1]]；正向添加：[res.append(x) for x in eles]

时间：O(N*M)
空间：O(N*M)

```python
class Solution:
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        """
        same_diag: r + c are the same
        d: {same_diag:[vals]}
        """
        d = collections.defaultdict(list)
        rows, cols = len(mat), len(mat[0])
        
        # 把对角线的点都放进来
        for r in range(rows):
            for c in range(cols):
                d[r + c].append(mat[r][c])
        
        res = []
        
        for idx, eles in d.items():
            # 当是偶数的时候，就要reverse
            if idx % 2 == 0:
                [res.append(x) for x in eles[::-1]]
            else:
                [res.append(x) for x in eles]
        
        return res
```


[317. Shortest Distance from All Buildings](https://leetcode.com/problems/shortest-distance-from-all-buildings/)

```python
class Solution:
    def shortestDistance(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        dirs = [[1,0],[-1,0],[0,1],[0,-1]]
        
        # 全为0的大小是rows*cols的矩阵，存的是所有房子到每个点的距离
        total_sum = [[0] * cols for _ in range(rows)]
        
        def bfs(row, col, curr_count):
            min_distance = math.inf
            queue = deque()
            queue.append([row, col, 0])
            while queue:
                r, c, curr_step = queue.popleft()
                for dr, dc in dirs:
                    nei_r, nei_c = r + dr, c + dc
                    
                    if 0 <= nei_r < rows and 0 <= nei_c < cols and grid[nei_r][nei_c] == -curr_count:
                        total_sum[nei_r][nei_c] += curr_step + 1
                        min_distance = min(min_distance, total_sum[nei_r][nei_c])
                        grid[nei_r][nei_c] -= 1
                        queue.append([nei_r, nei_c, curr_step + 1])
            return min_distance
                
        count = 0
        for row in range(rows):
            for col in range(cols):
                # 从每一个房子出发
                if grid[row][col] == 1:
                    min_distance = bfs(row, col, count)
                    count += 1
                    if min_distance == math.inf:
                        return -1
        
        return min_distance

```

[708. Insert into a Sorted Circular Linked List](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/)

用两个指针pre和cur遍历。分2种情况，一个是新Node在二者之前，另一个是新Node在两个之外，找到break的点然后break，最后插入就行了；注意一开始的head空情况；遍历终止条件是pre.next != head

时间：O(N)
空间：O(1)

```python
class Solution:
    def insert(self, head: 'Optional[Node]', insertVal: int) -> 'Node':
        node = Node(insertVal)
        
        if not head:
            node.next = node
            return node
        
        pre, cur = head, head.next
        
        while pre.next != head:
            # [1,2,4] insert 3
            if pre.val <= node.val <= cur.val:
                break
            # [3,5,7] insert 2, 8
            # pre.val = 7, cur.val = 3
            if pre.val > cur.val and (node.val > pre.val or node.val < cur.val):
                break
            
            pre = pre.next
            cur = cur.next
        
        node.next = cur
        pre.next = node
        return head
```

[986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)

交叉的起点是max(Astart, Bstart，终点是min(Aend, Bend);指针移动是根据终点大小

时间：O(N)
空间：O(1)

```python
class Solution:
    def intervalIntersection(self, firstList: List[List[int]], secondList: List[List[int]]) -> List[List[int]]:
        res = []
        i = j = 0
        
        while i < len(firstList) and j < len(secondList):
            start_1, end_1 = firstList[i][0], firstList[i][1]
            start_2, end_2 = secondList[j][0], secondList[j][1]
 
            start = max(start_1, start_2)
            end = min(end_1, end_2)
            
            if start <= end:
                res.append([start, end])
            
            if end_1 < end_2:
                i += 1
            else:
                j += 1
        
        return res

```


[1539. Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/)


arr[idx]的应该是1+idx，所以missing个数是arr[idx] - 1 - idx； 找到missing个数在k左的：missing of arr[idx] < k < missing of arr[idx+1]；然后往右加差的数字：arr[idx] + k - missing of arr[idx] => arr[idx] + k - arr[idx] + 1 + idx=> k + 1 + idx
[2,3,4,7,11], k = 5
点11: missing = 11 - 1 - 4 =  6
点7：missing = 7 - 1 - 3 = 3
要返回的点7 + 5 - 3 = 9
二分查找找到7，返回7+k-3

时间：O(logN)
空间：O(1)

```python
class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:
        #arr[idx]'s missing value is arr[idx] - 1 - idx
        # find the left most one that missing of arr[idx] < k < missing of arr[idx+1]
        # return arr[idx] + k - missing of arr[idx] => arr[idx] + k - arr[idx] + 1 + idx
        # return k + 1 + idx
        l, r = 0, len(arr) - 1
        
        while l <= r:
            mid = l + (r - l) // 2
            if (arr[mid] - 1 - mid < k):
                l = mid + 1
            else:
                r = mid - 1
        
        return k + 1 + r   
```

[1868. Product of Two Run-Length Encoded Arrays](https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/)

双指针；每次算出来min(f1, f2)然后把f1,f2剪掉，指针移动的条件是f==0；res更新要么+=freq，要么搞一个新的

时间：O(N+M)
空间：O(N+M)

```python
class Solution:
    def findRLEArray(self, encoded1: List[List[int]], encoded2: List[List[int]]) -> List[List[int]]:
        res = []

        i = 0
        j = 0

        while i < len(encoded1) and j < len(encoded2):
            # 取出来对应的value和frequency
            v1, f1 = encoded1[i]
            v2, f2 = encoded2[j]

            # 计算当前的值，和对应的frequency
            product_val = v1 * v2
            product_freq = min(f1, f2)

            # 把用掉的frequency给减掉
            encoded1[i][1] -= product_freq
            encoded2[j][1] -= product_freq

            # 指针移动的条件是freq==0
            if encoded1[i][1] == 0:
                i += 1

            if encoded2[j][1] == 0:
                j += 1

            # res更新product的方法
            if res and res[-1][0] == product_val:
                res[-1][1] += product_freq
            else:
                res.append([product_val, product_freq])

        return res
```


[270. Closest Binary Search Tree Value](https://leetcode.com/problems/closest-binary-search-tree-value/)

inorder遍历；更新res：if abs(node.val - target) < abs(res - target): res = node.val

时间：O(H) 一遍从头到脚
空间：O(1)

```python
class Solution:
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        """inorder traversal"""
        r = root.val
        while root:
            if abs(root.val - target) < abs(r - target):
                r = root.val
                
            if target < root.val:
                root = root.left
            else:
                root = root.right
                
        return r      
```
solve it recursivly 
```python
class Solution:
    def closestValue(self, root, target):
        """
        :type root: TreeNode
        :type target: float
        :rtype: int
        """
        res = float("inf")
        
        def dfs(root):
            nonlocal res

            if not root:
                return

            if abs(root.val - target) < abs(res - target):
                res = root.val
                
            # Target should be located on left subtree
            if target < root.val:
                dfs(root.left)
                
            # target should be located on right subtree
            if target > root.val:
                dfs(root.right)
        
        dfs(root)
        return res
```


[1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)

sliding window，弄清楚移动的条件和结果
时间：O(N)
空间：O(1)

```python
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        """
        sliding window:
        move r: 
            condition: k >= 0
                consequence: 
                    if nums[r] == 1:
                        res += 1
                    else:
                        k -= 1
                        
        move l:
            condition: k < 0
                consequence:
                    if nums[l] == 1:
                        res -= 1
                    else:
                        res -= 1
                        k += 1
        """
        l = r  = 0
        res = 0
        while r < len(nums):
            if nums[r] == 0:
                k -= 1
            r += 1
            
            if k < 0:
                if nums[l] == 0:
                    k += 1
                l += 1
            res = max(res, r - l)

        return res

```

[348. Design Tic-Tac-Toe](https://leetcode.com/problems/design-tic-tac-toe/)

用hori, ver, diag1, diag2来记录对应的次数，如果下在那个位置下了棋，就在该位置+=1或+= -1，如果最后这个位置==n,说明赢了；对角线的位置row+col == n-1

时间：O(1)
空间：O(n)

```python
class TicTacToe:

    def __init__(self, n: int):
        self.n = n
        self.hori = [0] * n
        self.ver = [0] * n
        self.diag1 = 0
        self.diag2 = 0

    def move(self, row: int, col: int, player: int) -> int:
        n = self.n
        move = 1
        if player == 2:
            move = -1
        
        self.hori[col] += move
        self.ver[row] += move
        
        if row == col:
            self.diag1 += move
        if row + col == (n-1):
            self.diag2 += move

        if abs(self.hori[col]) == n or abs(self.ver[row]) == n or abs(self.diag1) == n or abs(self.diag2) == n:
            return player
        
        return 0

# Your TicTacToe object will be instantiated and called as such:
# obj = TicTacToe(n)
# param_1 = obj.move(row,col,player)
```

[721. Accounts Merge](https://leetcode.com/problems/accounts-merge/)

分别建立一个emailToName的dict和emailToEmail的图；然后dfs遍历所有emailToName的email，看图里有没有，有的话就加进来。最后要sorted一下；graph = defaultdict(set) 避免重复；seen=set()记录见过的email

时间：O(NKlogNK) N是num of accounts, K是len(email)
空间：O(NKlogNK)

```python
class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        # 建立emailToName和email-email的图
        graph = defaultdict(set) 
        emailToName = {}

        for acct in accounts:
            name = acct[0]

            # build edge for all emails
            email1 = acct[1]
            emailToName[email1] = name
            for email2 in acct[2:]:
                graph[email1].add(email2)
                graph[email2].add(email1)
                emailToName[email2] = name

        res = []
        seen = set()

        for email in emailToName:
            if email not in seen:
                stack = [email]
                seen.add(email)
                emails = []

                while stack:
                    cur = stack.pop()
                    emails.append(cur)

                    for nei in graph[cur]:
                        if nei not in seen:
                            stack.append(nei)
                            seen.add(nei)

                res.append([emailToName[email]] + sorted(emails))
        
        return res
        
```

Union find
```python
class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:

        def find(x):
            if parents[x] != x:
                parents[x] = find(parents[x])

            return parents
        
        def union(x, y):
            r1 = find(x)
            r2 = find(y)

            if r1 != r2:
                parents[r2] = r1

        parents = {}
        emailToName = {}

        for acct in accounts:
            name = acct[0]
            for email in acct[1:]:
                emailToName[email] = name
                parent[email] = email

        for acct in accounts:
            email1 = acct[1]
            for email2 in acct[2:]:
                union(email1, email2)
        
        groups = defaultdict(list)

        for email in parents:
            r = find(email)
            groups[r].append(email)
        
        res = []

        for key in groups:
            res.append([emailToName[key]] + sorted(groups[key]))

        return res

```


[515. Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/)

level-order遍历，每层track最大值；python的“最小值”: float("-inf")

时间：O(N)
空间：O(H)

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def largestValues(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []
        res = []
        queue = collections.deque([root])
        
        while queue:
            one_res = float("-inf")
            
            for _ in range(len(queue)):
                node = queue.popleft()
                one_res = max(one_res, node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
                
            res.append(one_res)
        
        return res
```


dfs方法

```python
class Solution:
    def largestValues(self, root: Optional[TreeNode]) -> List[int]:
        res = []
        
        def dfs(node, level):
            if not node:
                return
            
            if len(res) - 1 < level:
                res.append(node.val)
            else:
                res[level] = max(node.val, res[level])
            dfs(node.left, level + 1)
            dfs(node.right, level + 1)
        
        dfs(root, 0)
        return res
```


[616. Add Bold Tag in String](https://leetcode.com/problems/add-bold-tag-in-string/)

先遍历整个words，记录下来所有s中能找到的位置，记作bold[i] = True；然后遍历s，如果是要bold，就加进来"<b>"，然后走到不为bold，加进来"</b>"；最后list转为string就可以；在s找到word之后，接下来继续从start+1找：start = s.find(word, start+1)

时间：O(SWD) s is len(s), w is len(max(word)), d is len(words)
空间：O(S)

```python
class Solution:
    def addBoldTag(self, s: str, words: List[str]) -> str:
        bold = [False] * len(s)
        
        # 对words里面的每个word，如果在s找到就给相应位置的bold标记true
        for word in words:
            start = s.find(word)
            while start != -1: # 找不到的话，s.find()返回-1
                for i in range(start, len(word)+start):
                    bold[i] = True
                # 继续往后找这个word，可能多次
                start = s.find(word, start + 1)
        
        res = []
        i = 0
        
        while i < len(s):
            if bold[i]:
                res.append("<b>")
                # 找到结尾
                while i < len(s) and bold[i]:
                    res.append(s[i])
                    i += 1
                res.append("</b>")
            else:
                res.append(s[i])
                i += 1
        
        return "".join(res)

```


[1382. Balance a Binary Search Tree](https://leetcode.com/problems/balance-a-binary-search-tree/)

inorder traverse to an array, then convert the array to a BST；array里面存的是node；built_tree的时候用i,j双指针，如果i>j就返回。build_tree返回的是root

时间：O(N)
空间：O(N)

```python
class Solution:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        nodes = []
        
        def inorder(node):
            if node is None:
                return
            inorder(node.left)
            nodes.append(node)
            inorder(node.right)
        
        inorder(root)

        def build_tree(i, j):
            if i > j:
                return None
            mid = (i + j) // 2
            root = nodes[mid]
            root.left = build_tree(i, mid -1)
            root.right = build_tree(mid + 1, j)
            return root
        
        return build_tree(0, len(nodes) - 1)

```

[273. Integer to English Words](https://leetcode.com/problems/integer-to-english-words/)

分成三部分：小于20， 小于100：二十到九十， 大于100:千，百万，十亿；如果数字是20以下，就直接返回to19数组的数；如果是100以下，就是[tens[num//10]+word(num%10)；如果1000以下，就是word(num//100)+["hundred"]+word(num%100)；如果是1000000（6个0）以下，就是word(num/1000)+["thousand"]+word(num%1000);换句话说，//和%后面的数是百、千、百万、十亿；num<1000的数是下一个位置的百、千、百万、十亿；最后" ".join(word(num))

时间：O(N)
空间：O(1)

```python
class Solution:
    def numberToWords(self, num: int) -> str:
        to19 = 'One Two Three Four Five Six Seven Eight Nine Ten Eleven Twelve ' \
           'Thirteen Fourteen Fifteen Sixteen Seventeen Eighteen Nineteen'.split()
        tens = 'Twenty Thirty Forty Fifty Sixty Seventy Eighty Ninety'.split()
        
        def word(num):
            if num == 0:
                return []
            if num < 20:
                return [to19[num-1]]
            if num < 100:
                return [tens[num//10-2]] + word(num%10)
            if num < 1000:
                return word(num//100) + ['Hundred'] + word(num%100)
            if num < 1000000:
                return word(num//1000) + ['Thousand'] + word(num%1000)
            if num < 1000000000:
                return word(num//1000000) + ['Million'] + word(num%1000000)
            else:
                return word(num//1000000000) + ['Billion'] + word(num%1000000000)
        
        return ' '.join(word(num)) or 'Zero'
```

[1011. Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)

l=max(weights), r=sum(weights),最后返回的是最左侧边界l；isValid: r = mid - 1；计算isValid：用一个cur，每次cur+=w，先检查是否cur+w>cap，是的话就days_need+=1, cur = 0

时间：O(logN)
空间：O(1)

```python
class Solution:
    def shipWithinDays(self, weights: List[int], days: int) -> int:
        # capacity is res, res+1, res+2, ..., 
        # binary search from max(weights), to sum(weights)
        # if valid, r = mid - 1
        l, r = max(weights), sum(weights)
        
        def isvalid(cap): # 注意如何计算满足
            day_need = 1
            cur = 0
            for w in weights:
                if cur + w > cap: # 每次更新有的，直到大于cap
                    day_need += 1
                    cur = 0
                cur += w
            
            return day_need <= days
        
        while l <= r:
            mid = l + (r - l) // 2
            if isvalid(mid):
                r = mid - 1
            else:
                l = mid + 1
        
        return l
        
```


[863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)

建立node和node.parent的关系，之后bfs从target找周围的k个距离的点就行

时间：O(N)
空间：O(N)

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        def set_parent(root, parent):
            if not root:
                return None
            root.parent = parent
            set_parent(root.left, root)
            set_parent(root.right, root)
        
        set_parent(root, None)
        
        queue = collections.deque([(target, 0)])
        visited = set()
        
        res = []
        while queue:
            node, dist = queue.popleft()
            if node in visited:
                continue
            visited.add(node)
            
            if dist == k:
                res.append(node.val)
            
            elif dist < k:
                if node.left:
                    queue.append((node.left, dist+1))
                if node.right:
                    queue.append((node.right, dist+1))
                if node.parent:
                    queue.append((node.parent, dist+1))
        
        return res
```


[398. Random Pick Index](https://leetcode.com/problems/random-pick-index/)


reservor sampling，当val == target的时候count+=1，然后从randint(1, count)==1来更新结果

时间：O(N)
空间：O(1)

```python
class Solution:

    def __init__(self, nums: List[int]):
        self.nums = nums

    def pick(self, target: int) -> int:
        count = 0
        idx = 0
        for i, val in enumerate(self.nums):
            if val == target:
                count += 1
                if random.randint(1, count) == count:
                    idx = i
        return idx
```


用hashmap
时间：O(N)
空间：O(N)

```python
class Solution:

    def __init__(self, nums: List[int]):
        self.numToIdx = collections.defaultdict(list)
        for i, val in enumerate(nums):
            self.numToIdx[val].append(i)

    def pick(self, target: int) -> int:
        size = len(self.numToIdx[target]) - 1
        idx = random.randint(0, size)
        res = self.numToIdx[target][idx]
        
        return res
```


[1428. Leftmost Column with at Least a One](https://leetcode.com/problems/leftmost-column-with-at-least-a-one/)

每行一个二分查找最左出现，然后更新

时间：O(NlogM) N:row, M:cols
空间：O(1)

```python
class Solution:
    def leftMostColumnWithOne(self, binaryMatrix: 'BinaryMatrix') -> int:
        rows, cols = binaryMatrix.dimensions()
        
        right_most = cols
        
        for row in range(rows):
            l, r = 0, min(cols-1, right_most)
            while l <= r:
                mid = l + (r- l) // 2
                if binaryMatrix.get(row, mid) == 0:
                    l = mid + 1
                else:
                    r = mid - 1
            if l < cols and binaryMatrix.get(row, l) == 1:
                right_most = min(right_most, l)
                
        return right_most if right_most != cols else -1
```


[536. Construct Binary Tree from String](https://leetcode.com/problems/construct-binary-tree-from-string/)

用i遍历；先找到数字，形成节点，然后如果是(就放左边，然后如果再次出现(就放右边
时间：O(N)
空间：O(H)

```python
class Solution:
    def str2tree(self, s: str) -> TreeNode:
        if not s or len(s) == 0:
            return None
        
        def helper(i):
            start = i
            while i < len(s) and (s[i] == '-' or s[i].isdigit()): # negative sign or digit
                i += 1
            node = TreeNode(int(s[start : i]))
            if i < len(s) and s[i] == '(':
                i += 1 # skip '('
                node.left, i = helper(i)
                i += 1 # skip ')'
            if i < len(s) and s[i] == '(': # still has '(', create right tree
                i += 1 # skip '('
                node.right, i = helper(i)
                i += 1 # skip ')'
            return node, i
        
        root, _ = helper(0)
        return root
```

[1344. Angle Between Hands of a Clock](https://leetcode.com/problems/angle-between-hands-of-a-clock/)

分针的角度：min * 6; 小时的角度：hour%12 * 30 + angle_min / 60 * 30

时间：O(1)
空间：O(1)

```python
class Solution:
    def angleClock(self, hour: int, minutes: int) -> float:
        """
        an_per_min = 360 / 60 = 6
        an_per_hour = 360 / 12 = 30       
        """
        
        an_min = minutes * 6
        an_hour = hour%12 * 30 + minutes * 30 / 60
        
        diff = abs(an_min - an_hour)
        return min(diff, 360-diff)
```

[825. Friends Of Appropriate Ages](https://leetcode.com/problems/friends-of-appropriate-ages/)

排序之后；对于每一个age找到自己的位置，然后找到最小的朋友的位置，之间的差就是可以添加的好友

时间：O(logN)
空间：O(1)

```python
class Solution:
    def numFriendRequests(self, ages: List[int]) -> int:
        res = 0
        N = len(ages)
        ages.sort()
        for i in range(N):
            a = ages[i]
            idx1 = bisect.bisect(ages, a)
            idx2 = bisect.bisect(ages, 0.5 * a + 7)
            res += max(0, idx1 - idx2 - 1) 
        return res
```

[556. Next Greater Element III](https://leetcode.com/problems/next-greater-element-iii/)

找到最右下降序列的第一位，然后找到左边的那个数，从后面再找比左边那个数大的，交换。之后逆向后面的数，最后输；int变list：nums=list(str(n))

时间：O(N)
空间：O(N)

```python
class Solution:
    def nextGreaterElement(self, n):
        nums = list(str(n))
        i = len(nums) - 1
        
        while i>0 and nums[i-1] >= nums[i]:
            i -= 1
        
        if i == 0:
            return -1
        
        k = i - 1
        j = len(nums) - 1
        while nums[j] <= nums[k]:
            j -= 1
        nums[j], nums[k] = nums[k], nums[j]
        
        l, r = k + 1, len(nums) - 1
        while l < r:
            nums[l], nums[r] = nums[r], nums[l]
            l += 1
            r -= 1
            
        res = int("".join(nums)) 
        if res >= 2**31:
            return -1
        return res
        

```

[1060. Missing Element in Sorted Array](https://leetcode.com/problems/missing-element-in-sorted-array/)

nums[i]之前的missing个数是nums[i] - nums[0] - i; 找到nums[i] < k < nums[i+1]的missing个数的位置; 返回nums[i] + k - (nums[i] - nums[0] - i) = k + nums[0] + i

时间：O(logN)
空间：O(1)

```python
class Solution:
    def missingElement(self, nums: List[int], k: int) -> int:
        l, r = 0, len(nums) - 1
        while l <= r:
            mid = l + (r - l) // 2
            if nums[mid] - nums[0] - mid < k:
                l = mid + 1
            else:
                r = mid - 1
        return k + nums[0] + r

```



[958. Check Completeness of a Binary Tree](https://leetcode.com/problems/check-completeness-of-a-binary-tree/)

BFS层序遍历，另外用一个boolean记录是否有过空，如果有过2次就说明False

时间：O(logN)
空间：O(N)

```python
class Solution:
    def isCompleteTree(self, root: Optional[TreeNode]) -> bool:
        if not root:
            return False
        queue = collections.deque([root])
        end = False
        
        while queue:
            for _ in range(len(queue)): # 对于每层
                node = queue.popleft()
                if not node:
                    end = True
                else:
                    if end:
                        return False
                    queue.append(node.left)
                    queue.append(node.right) 
        
        return True
```


[463. Island Perimeter](https://leetcode.com/problems/island-perimeter/)

每次见到1就+=4，然后如果左边也是就-=2，如果上面也是就-=2
时间：O(N)
空间：O(1)

```python
class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        rows = len(grid)
        cols = len(grid[0])
        
        result = 0
        
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 1:
                    result += 4
                    
                    if r > 0 and grid[r-1][c] == 1:
                        result -= 2
                        
                    if c > 0 and grid[r][c-1] == 1:
                        result -= 2
        
        return result

```


[104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

注意base case之后，求左边和右边的深度；左边是1+max_height(left)；返回的是max(left, right)

时间：O(N)
空间：O(N)

```python
class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        if not root.left and not root.right:
            return 1
        left_height = 1 + self.maxDepth(root.left)
        right_height = 1 + self.maxDepth(root.right)        
        
        return max(left_height, right_height)
```

[24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)

```python
# swap nodes in pairs: [1,2,3,4] -> [2,1,4,3]
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return None
        
        dummy = ListNode(-1)
        dummy.next = head
        pre = dummy
        
        while pre.next != None and pre.next.next != None: # 因为second = pre.next.next
        # 从[pre,1,2,3]变成[2,1,3]
            first = pre.next # first = 1
            second = pre.next.next # second = 2
            first.next = second.next # 1 -> 3
            second.next = first # 2 -> 1
            pre.next = second # pre -> 2 要把2连起来
            pre = first # pre = 1
        
        return dummy.next
```


[1644. Lowest Common Ancestor of a Binary Tree II](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/)

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/1011154/Failed-This-Question-In-Two-Mock-Interview-So-Post-This-To-Remind-myself-specifically 



