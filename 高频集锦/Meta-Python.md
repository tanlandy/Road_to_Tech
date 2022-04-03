[125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
思路：用相向two pointer，当不是char时候就比较; s[i].isalnum() 看是否是string或者num; s[i].lower() 返回一个小写
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

思路：
用queue，每次记录当前的windowSum，如果size满足的话，来一个新数字就弹出末尾的，同时加进来新的，最后返回windowSum/len即可

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
```python
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        order_map = {}
        for index, val in enumerate(order):
            order_map[val] = index
        
        for i in range(len(words) - 1):
            for j in range(len(words[i])):
                if j >= len(words[i + 1]): # 要有'='，因为words[i+1]取不到len，只能到len-1
                    return False
                if words[i][j] != words[i + 1][j]:
                    if order_map[words[i][j]] > order_map[words[i + 1][j]]:
                        return False
                    break
        return True
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

思路：
首位加[0]，然后检查bed[i], bed[i-1], bed[i+1]是否相等且为0，如果是就n-=1，然后把bed[i]=1

```python
class Solution:
    def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
        s = len(flowerbed)
        bed = [0] + flowerbed + [0]
        for i in range(1, s + 1):
            if bed[i] == bed[i-1] == bed[i+1] == 0:
                bed[i] = 1
                n -= 1
            if n <= 0:
                return True
        return False
```

思路二：
也是首尾加0，用一个count来记录连续的0，如果count==3那就n-=1同时count=1

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

two pointers从后往前，用carry存进位的情况，value = (x1 + x2 + carry) % 10, carry = (x1 + x2 + carry) // 10. 走到头carry不为0就再append一下，最后reverse并且转换成string即可；ord(string)返回unicode值, x = ord(string) - ord('0')就把'5'存成5到x里；a // 10 地板除，向下取整; math.ceil(a/10)就是向上取整；res[]存的整数反过来导出成string: ''.join(str(x) for x in res[::-1])
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
用stack存inValid的'(',')'的index; 如何判断inValid：每次看到(就压栈，看到)要么弹要么直接放到set里，或者直接换成""，再把多余的(都放到set里面（或者多余的（直接换成空）。最后把s导出成string
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
                queue.append((node.left, col - 1)) # 双[[]]
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

[1762. Buildings With an Ocean View](https://leetcode.com/problems/buildings-with-an-ocean-view/)

There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

Input: heights = [4,2,3,1]
Output: [0,2,3]

从右往左走，每次记录最大值，比较最大值和当前值；当前值更大就更新最大值并且记录index；反过来走for index in reversed(range(len(list));翻转list: res.reverse()
时间： O(n)
空间： O(1)
```python
class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:
        n = len(heights)
        res = []
        curMax = -1
        for cur in reversed(range(n)):
            if curMax < heights[cur]:
                curMax = heights[cur]
                res.append(cur)
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
同向双指针，存成pairs[(index, num)]只存不是0的index和num，当同时都没到终点，只用pairs中的index相同就res+=，否则根据index大小移动指针；
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
        if root is None:
            return 0
        self.res = 0
        def dfs(node):
            if node:
                if L <= node.val <= R:
                    self.res += node.val
                if L < node.val:
                    dfs(node.left)
                if node.val < R:
                    dfs(node.right)
        dfs(root)
        return self.res
```

[1650. Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/)

先求各自深度，再把深的往上走直到当前深度相同，最后一起往上走找parent
时间：O(H)
空间：O(1)
```python
   def lowestCommonAncestor(self, p: 'Node', q: 'Node') -> 'Node':
        def get_depth(node):
            depth = 0
            while node.parent: # 条件是node.parent
                node = node.parent
                depth += 1
            return depth
        
        dp, dq = get_depth(p), get_depth(q)
        if dp < dq:
            for _ in range(dq - dp):
                q = q.parent
        else:
            for _ in range(dp - dq):
                p = p.parent
        # now p and q are the same level
        while p != q:
            p = p.parent
            q = q.parent
        return p
```

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


用list存所有的前缀和。概率是w[i]/total_sum，可以用找到第一个preSum来代替；用random.random()来获得[0,1)
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

用list存所有的前缀和。概率是w[i]/total_sum，可以用二分查找找到第一个preSum来代替；用random.random()来获得[0,1)
时间：构造O(N)，找数O(NlogN)
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
        low, high = 0, len(self.prefix_sums) - 1
        while low <= high:
            mid = low + (high - low) // 2 # 要地板除
            if (target > self.prefix_sums[mid]):
                low = mid + 1
            else: 
                high = mid - 1
        return low

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
        """
        :type s: str
        :type wordDict: Set[str]
        :rtype: List[str]
        """
        return self.helper(s, wordDict, {})

    def helper(self, s, wordDict, memo):
        if s in memo: return memo[s]
        if not s: return []

        res = []
        for word in wordDict:
            if not s.startswith(word):
                continue
            if len(word) == len(s):
                res.append(word)
            else:
                resultOfTheRest = self.helper(s[len(word):], wordDict, memo)
                for item in resultOfTheRest:
                    item = word + ' ' + item
                    res.append(item)
        memo[s] = res
        return res
```

[124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

dfs()在左右子树不分分叉的情况下，返回左子树path最大值和右子树path最大值:return node.val + max(leftMax, rightMax); basecase是走到null的0，同时更新res[0], res[0]=max(res[0], node.val+leftMax+rightMax)
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
        res = [root.val]
        
        # 子树本身，返回不分叉时候的最大值
        def dfs(node) -> int:
            if not node:
                return 0
            leftMax = max(dfs(node.left), 0)
            rightMax = max(dfs(node.right), 0)
            res[0] = max(res[0], node.val + leftMax + rightMax)
            return node.val + max(leftMax, rightMax)
        dfs(root)
        return res[0]
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
        count = {}
        freq = [[] for i in range(len(nums) + 1)] # 大小是len(nums) + 1，注意如何构建values是list的list

        for n in nums:
            count[n] = count.get(n, 0) + 1
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
        
        if left is None:
            return right
        elif right is None:
            return left
        else:
            return root
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