# Classic problems
1. Search in Rotated Sorted Array

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```Java
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int pivot = l + (r - l) / 2;
            if (nums[pivot] == target) {
                return pivot;
            } else if (nums[pivot] >= nums[l]) { // 分割成两个binary search
                if (nums[l] <= target && target < nums[pivot]) { // pivot都不会'='，但是nums[l]或者nums[r]可以'='
                    r = pivot - 1;
                } else { // 包含了各种各样的情况，但都是l = pivot + 1这一结果
                    l = pivot + 1;
                }
            } else if (nums[pivot] < nums[l]) {
                if (target <= nums[r] && nums[pivot] < target) {
                    l = pivot + 1;
                } else {
                    r = pivot - 1;
                }
            }       
        }
        return -1;
    }
}
```