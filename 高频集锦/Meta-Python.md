[125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
思路：用相向two pointer，当不是char时候就比较
s[i].isalnum() 看是否是string或者num
s[i].lower() 返回一个小写
```python
class Solution:
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
        
```

[1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)
思路：用Stack，如果相同就pop，不同就放进来，最后转换成string

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


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
```

[246. Strobogrammatic Number](https://leetcode.com/problems/strobogrammatic-number/)
思路：
先确定满足条件的strobogrammatic nums
然后用hashmap一一对应起来
用相向two pointers一一比对，注意条件有2个：满足strobogrammatic num并且要pointers指向对应
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
思路：放进map里{char: count}数个数，如果偶数就可以，奇数的话只能至多一个是奇数

注意map[item] = map.get[item, 0] + 1的使用方法
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




