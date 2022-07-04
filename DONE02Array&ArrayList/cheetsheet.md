# Array
数组是存放在连续内存空间上的相同类型数据的集合
## Initiate
```Java
String[] friendArray = new String[10];
friendArray[0] = "Hello";
int[] nums = new int[]{1,4,2,3};

String[] friendArray2 = {"Hello","Wow","Earth"};
friendArray2[0] = "World"; //overwrite
```
## Methods
```Java
friendArray[0]; // Access
friendArray.length // Get length

// Shift and insert
// Say we want to insert the element at index 2.
for (int i = 4; i >= 2; i--) {
    intArray[i + 1] = intArray[i];
}
intArray[2] = 30;
// Delete: Shift and edit length
for (int i = 1; i < length; i++) {
    int_array[i - 1] = int_array[i];
}
length--;

```
# Arrays
```Java
int[] nums = new int[]{1,2,3,4,5,6,7,8};
Arrays.sort(nums); // Sort
Arrays.copyOfRange(nums, 2, 5); // return [3,4,5]; creates a copy of elements nums[2,5)
Arrays.fill(nums, -1); // fill nums with -1
```

# ArrayList
## Initiate
`ArrayList<Integer> myNumbers = new ArrayList<>();`
## Methods
```Java
// get; O(1)
// set; O(1)
// add; O(n)
// remove; O(n)
// find; O(n)
myNumbers.add(1);
myNumbers.add(2);
myNumbers.add(3);
myNumbers.size() // get length
Collections.sort(myNumbers); // Sort
myNumbers.toArray(new String[myNumbers.size()]); // ArrayList to Array
ArrayList(map.values()); // 
myNumbers.set(0, 10); // {10, 2, 3}

Collections.reverse(myNumber); // reverse the items in myNumber

```

[1002. Find Common Characters](https://leetcode.com/problems/find-common-characters/)

Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
```Java
class Solution {
    public List<String> commonChars(String[] words) {
        List<String> res = new ArrayList<>();
        if (words.length == 0) {
            return res;
        }
        int[] map = new int[26];
        // 第一个字符串
        for (Character c : words[0].toCharArray()) {
            map[c - 'a']++;
        }
        // 其他字符串
        for (int i = 1; i < words.length; i++) {
            int[] map2 = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                map2[words[i].charAt(j) - 'a']++;
            }
            // 更新map，只留最少的
            for (int k = 0; k < 26; k++) {
                map[k] = Math.min(map[k], map2[k]);
            }
        }
        // map转化成输出形式
        for (int i = 0; i < 26; i++) {
            while (map[i] != 0) {
                char c = (char)(i + 'a');
                res.add(String.valueOf(c));
                map[i]--;
            }
        }
        return res;
    }
}
```


[349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/)
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
```Java
    public int[] intersection(int[] nums1, int[] nums2) {
        // 遍历nums1
        Set<Integer> s1 = new HashSet<>();
        for (int i : nums1) {
            s1.add(i);
        }
        // 遍历nums2
        Set<Integer> s2 = new HashSet<>();
        for (int j : nums2) {
            if (s1.contains(j)) {
                s2.add(j);
            }
        }
        // 把s2结果转到int[]
        int[] res = new int[s2.size()];
        int index = 0;
        for (int k : s2) {
            res[index] = k;
            index++;
        }
        return res;
    }


```


[202. Happy Number](https://leetcode.com/problems/happy-number/)
Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

```Java

    public boolean isHappy(int n) {
        // 如果出现重复就跳出
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
    
    // 求数位和
    private int getNext(int n) {
        int res = 0;
        while (n > 0) {
            int digit = n % 10; // 取余数
            res += digit * digit;
            n /= 10; // 变小
        }
        return res;
    }


```

[1. Two Sum](https://leetcode.com/problems/two-sum/)
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```Java

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        // (num, index)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                res[0] = map.get(complement);
                res[1] = i;
            }
            map.put(nums[i], i);
        }
        return res;
    }

```

[454. 4Sum II](https://leetcode.com/problems/4sum-ii/)
Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
```Java

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // <sum, count>
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int a : nums1) {
            for (int b : nums2) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }
        for (int c : nums3) {
            for (int d : nums4) {
                count += map.getOrDefault(-(c + d), 0);
            }
        }
        return count;
    }

```

[167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].

```Java

    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

```

[15. 3Sum](https://leetcode.com/problems/3sum/)
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```Java

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        // 固定第一个，然后从剩下的left, right找两数之和
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) { // 注意退出条件
            if (i == 0 || nums[i - 1] != nums[i]) { // 注意条件
                twoSum(nums, i, res);
            }
        }
        return res;
    }
    private void twoSum(int[] nums, int i, List<List<Integer>> res) {
        int l = i + 1; 
        int r = nums.length - 1;
        while (l < r) {
            int sum = nums[i] + nums[l] + nums[r];
            // 小于sum就往右走
            if (sum < 0) {
                l++;
                // 大于就往左右
            } else if (sum > 0) {
                r--;
            } else {
                res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                l++;
                r--;
                // 不出现重复
                while (l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
            }
        }
    }

```