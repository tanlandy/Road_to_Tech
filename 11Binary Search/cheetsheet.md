# Sections
1. Pre-processing - Sort if collection is unsorted.
2. Binary Search - Using a loop or recursion to divide search space in half after each comparison.
3. Post-processing - Determine viable candidates in the remaining space.


# Templetes
1. Basic
- Search Condition can be determined without comparing to the element's neighbors (or use specific elements around it)
- No post-processing required because at each step, you are checking to see if the element has been found. If you reach the end, then you know the element is not found
```Java
class Solution {
    public int search(int[] nums, int target) {
        //必须已经排好序
        if (nums.length == 0 || nums = null) { // edge case不要忘记
            return -1;
        }

        int l = 0, r = nums.length - 1;
        while (l <= r) { // '='保证在只有一个数的情况下满足
            int pivot = l + (r - l) / 2;
            if (target == nums[pivot]) {
                return pivot;
            } else if (target < nums[pivot]) {
                r = pivot - 1;
            } else {
                l = pivot + 1;
            }
        }
        return -1; // 都不满足的时候
    }
}
```

2. return its immediate right neighbor's index