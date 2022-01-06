# Leetcode Tutorial
再看看complexity analysis

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

## 例题整理

### Maze

1. Maze

Given a maze and a start point and a target point, return whether the target can be reached.

```Java
public boolean solveMaze(char[][]maze, int startX, int startY, int targetX, int targetY) {
    // base case
    if (startX == targetX && startY == targetY) {
        return true;
    }
    
    // conditions: reach the wall or visited before
    if (startX < 0 || startY < 0 || startX >= maze.length || startY >= maze[0].length
        || maze[startX][startY] == 'X') {
        return false;
    }
    
    // visited before
    maze[startX][startY] = "x";
    
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    
    for (int i = 0; i < 4, i++) {
        if (solveMaze(maze, startX + dx[i], startY + dy[i], targetX, targetY)) {
            return true;
        }
    }
    return false; 
}
```

2. Maze print

Given a maze and a start point and a target point, print out the path to reach the target.
```Java
public boolean solveMaze(char[][]maze, int startX, int startY, int targetX, int targetY, String path) {
    // As needed to print out the path, we need to initiate the path and store it

    // base case
    if (startX == targetX && startY == targetY) {
        System.out.println(path);
        return true;
    }
    
    // conditions
    if (startX < 0 || startY < 0 || startX > maze.length || startY > maze[0].length ||
        maze[startX][startY] == 'X') {
        return false;
    }

    // visited before
    maze[startX][startY] = 'X';
    int[] dx = {1. 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    // initiate the directions
    char[] direc = {'D', 'R', 'U', 'L'};
    
    for (int i = 0; i < 4; i++) {
        // initiate the path each time, in this case, we don't need to remove the visited path after each recursion
        // all newPaths won't affect each other
        String newPath = path + direc[i] + " ";
        if (solveMaze(maze, startX, startY, targetX, targetY, newPath)) {
            return true;
        }
    }
    return false;       
}
```

3. Maze return path

Given a maze and a start point and a target point, return the path to reach the target.
```Java
public void mazeSolve(int[][]maze, int startX, int startY, int targetX, int targetY, ArrayList<Character> path) {
    // As needed to return the path, we put inititate the path in main function

    //base case
    if (startX == targetX && startY == targetY) {
        return true;
    }
    
    // conditions
    if (startX < 0 || startY < 0 || startX > maze.length || startY > maze[0].length 
        || maze[startX][startY] == 'X') {
        return false;
    }
    maze[startX][startY] = 'X';
    
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    char[] direc = {'D', 'R', 'U', 'L'};
    
    for (int i = 0; i < 4; i++) {
        
        path.add(direc[i]);
        if (mazeSolve(maze, startX, startY, targetX, targetY, path)) {
            return true;
        }
        // as path is edited each time, after each call, it has to back to the previous status
        path.remove(path.size() - 1);
    }
    return false;
    
}
```
### Knapsack
1. 0-1 Knapsack
Given a knapsack which can hold s pounds of items, and a set of items with weight w1, w2, ... wn. 
Return whether we can pick speciﬁc items so that their total weight s.
s = 20; w = [14, 8, 7, 5, 3];
```Java
public boolean knapsack(int s, int[] weight, int index) {
    // pick: (s - w[i], w - w[i])
    // not pick: (s, w - w[i])

    // base case
    if (s == 0) {
        return true;
    }

    // conditions
    if (index == weight.length || s < 0) {
        return false;
    }

    return (knapsack(s - weight[i], weight, index + 1) ||
    knapsack(s, weight, index + 1));

}
```

2. 0-1 Knapsack II
Try to put items into the pack as many as possible, return the largest weight we can get in the knapsack
```Java
public int knapsack(int s, int[] weights, int index) {
    // base case
    if (s == 0 || index == weights.length) {
        return 0;
    }

    if (weights[index] > s) {
        return knapsack(s, weights, index + 1);
    }
    return Math.max(knapsack(s, weights, index + 1), 
                    weights[index] + knapsack(s - weights[index], weights, index + 1))
}

```



3. Knapsack: use unlimited number of times
Given a set of candidate numbers (C) and a target number (T), ﬁnd all unique combinations in C where the candidate numbers sums to T.

All candidate numbers are unique.

The same repeated number may be chosen from C unlimited number of times


```Java
public ArrayList<ArrayList<Integer>> knapsack(int s, int[] weight) {
 
    // need to return the value, initiate the res in main fun
    // use curr to store the value in each recursion

    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> curr = new ArrayList<>();
    helper(s, weight, curr, res, 0);
    return res;
}

public void helper(int s, int[] weight, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> res, int index) {
    // base case
    if (s == 0) {
        // finally, add the curr to the res
        // as curr are affected with each other, have to use `new` 
        res.add(new ArrayList<Integer>(curr));
        return;
    }

    // conditions
    if (s < 0 || index == weight.length) {
        return;
    }
 
    // pick:(s - w[i], w, index)
    curr.add(weight[index]);
    helper(s - weight[index], weight, curr, res, index);

    // not pick:(s, w, index + 1)
    curr.remove(curr.size() - 1);
    helper(s, weight, curr, res, index + 1);

}
```
3. Kanpsack II: duplicate + use once
Candidate numbers may contain duplicate.
Each number in C may only be used once in the combination.

```Java
public List<List<Integer>> knapsack(int s, int[] weights) {
    Arrays.sort(weights);
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> curr = new ArrayList<>();

    helper(s, weights, curr, res, 0);
    return res;
}

public void helper(int s, int[] weights, List<Integer> curr, List<List<Integer>> res, int index) {
    // base case
    if (s < 0) return;
    if (s == 0) {
        res.add(new ArrayList<Integer>(curr));
        return;
    }

    // for loop, in this case, don't need to call helper() when not pick
    for (int i = index; i < weights.length; i++) {
        curr.add(weights[i]);
        // pick: (s - w[i], index + 1)
        helper(s - weights[i], weights, curr, res, index + 1);
        // not pick: (s, index +++++)
        curr.remove(curr.size() - 1);
        while (i < weights.length - 1 && weights[i] == weights[i + 1]) {
            i++;
        }
    }
}
```



### Backtracking
1. [Subsets](https://leetcode.com/problems/subsets/)

Given an integer array nums of unique elements, return all possible subsets (the power set).
Input: nums = [1,2,3]
Output: \[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

```Java
    public List<List<Integer>> subsets(int[] nums) {
        // initate the res
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        ArrayList<Integer> curr = new ArrayList<>();
        backtrack(res, curr, nums, 0);
        return res;
    }
    
    private void backtrack(List<List<Integer>> res , List<Integer> curr, int [] nums, int index){
        res.add(new ArrayList<>(curr));
        for(int i = index; i < nums.length; i++){
            curr.add(nums[i]);
            backtrack(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
```
2. [Subsets II (contains duplicates)](https://leetcode.com/problems/subsets-ii/)
Input: nums = [1,2,2]
Output: \[[],[1],[1,2],[1,2,2],[2],[2,2]]
```Java
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        ArrayList<Integer> curr = new ArrayList<>();
        backtrack(list, curr, nums, 0);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> curr, int [] nums, int start){
        list.add(new ArrayList<>(curr));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            curr.add(nums[i]);
            backtrack(list, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    } 
```
3. [Permutations](https://leetcode.com/problems/permutations/)
Input: nums = [1,2,3]
Output: \[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

```Java
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
     }
     
     private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        // base case
        if(tempList.size() == nums.length){
           list.add(new ArrayList<>(tempList));
           return;
        } 
        for(int i = 0; i < nums.length; i++){ 
            if(tempList.contains(nums[i])) continue; // element already exists, skip
            tempList.add(nums[i]);
            backtrack(list, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
     } 
```
4. [Permutations II (contains duplicates)](https://leetcode.com/problems/permutations-ii/)
Input: nums = [1,1,2]
Output:\[[1,1,2], [1,2,1], [2,1,1]]
```Java
     public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        // base case
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
Input: candidates = [2,3,6,7], target = 7
Output: \[[2,2,3],[7]]

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
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:\[[1,1,6], [1,2,5], [1,7], [2,6]]

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

A palindrome string is a string that reads the same backward as forward.
Input: s = "aab"
Output: \[["a","a","b"],["aa","b"]]
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