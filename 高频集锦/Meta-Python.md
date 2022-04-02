[125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
思路：用相向two pointer，当不是char时候就比较
s[i].isalnum() 看是否是string或者num
s[i].lower() 返回一个小写
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