# 模板
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
    // 最后要检查 left 越界的情况，当target比所有元素都大的情况
    if (left >= nums.length || nums[left] != target)
        return -1;
    return left; // 最后返回left
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
    // 最后要检查 right 越界的情况，即当target比所有元素都小的情况
    if (right < 0 || nums[right] != target)
        return -1;
    return right;
}

```

# 例题
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
