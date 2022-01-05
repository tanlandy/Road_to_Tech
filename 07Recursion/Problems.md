# Course
## Examples
1. Gray Code
2. 0-1 Knapsack
3. Maze(Template)
4. Maze print
5. Maze return path
6. Knapsack: use unlimited number of times
7. Kanpsack II: duplicate + use once
8. 0-1 Knapsack II: return the largest weight
9. [Permutations](https://leetcode.com/problems/permutations/)
10. [Permutations II](https://leetcode.com/problems/permutations-ii/)
11. Combination
12. Lucky Numbers

## HW
1. [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
2. [Subsets](https://leetcode.com/problems/subsets/)
3. [N-Queens II](https://leetcode.com/problems/n-queens-ii/)
4. [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)
5. [Subsets II](https://leetcode.com/problems/subsets-ii/)
6. [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)
7. [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)
8. [Word Search](https://leetcode.com/problems/word-search/)
9. [Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/)

## OA


## Backtracking
1. [Subsets](https://leetcode.com/problems/subsets/)

Given an integer array nums of unique elements, return all possible subsets (the power set).
Input: nums = [1,2,3]
Output: \[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

```Java
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
```
1. [Subsets II (contains duplicates)](https://leetcode.com/problems/subsets-ii/)

```Java
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    } 
```
3. [Permutations](https://leetcode.com/problems/permutations/)


```Java
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
     }
     
     private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
           list.add(new ArrayList<>(tempList));
        } else{
           for(int i = 0; i < nums.length; i++){ 
              if(tempList.contains(nums[i])) continue; // element already exists, skip
              tempList.add(nums[i]);
              backtrack(list, tempList, nums);
              tempList.remove(tempList.size() - 1);
           }
        }
     } 
```
4. [Permutations II (contains duplicates)](https://leetcode.com/problems/permutations-ii/)

```Java
     public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true; 
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false; 
                tempList.remove(tempList.size() - 1);
            }
        }
    }
```
5. [Combination Sum](https://leetcode.com/problems/combination-sum/)

```Java
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{ 
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
```
6. [Combination Sum II (can't reuse same element)](https://leetcode.com/problems/combination-sum-ii/)


```Java
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
        
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1); 
            }
        }
    } 
```
7. [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)

```Java
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
     }
     
     public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if(start == s.length())
           list.add(new ArrayList<>(tempList));
        else{
           for(int i = start; i < s.length(); i++){
              if(isPalindrome(s, start, i)){
                 tempList.add(s.substring(start, i + 1));
                 backtrack(list, tempList, s, i + 1);
                 tempList.remove(tempList.size() - 1);
              }
           }
        }
     }
     
     public boolean isPalindrome(String s, int low, int high){
        while(low < high)
           if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
     } 
    
}
```