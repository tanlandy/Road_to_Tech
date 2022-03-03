## 回溯集锦
1. [78. Subsets](https://leetcode.com/problems/subsets/)（子集 元素无重不可复选）
   
Given an integer array nums of unique elements, return all possible subsets (the power set).
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]   
```Java
//我们通过保证元素之间的相对顺序不变来防⽌出现重复的⼦集
    public List<List<Integer>> subsets(int[] nums) {
        // 主函数写中间结果和最终结果
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> oneRes = new ArrayList<>();
        // 调用backtrack
        backtrack(nums, res, oneRes, 0);
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> oneRes, int start) {
        // 添加条件：每个中间结果都是最终结果
        res.add(new ArrayList<Integer>(oneRes)); // 注意添加方式
        // for 选择 in 选择列表
        for (int i = start; i < nums.length; i++) { // i = start 子集问题
            // 排除不合法选择：无
            
            oneRes.add(nums[i]); // 做选择
            backtrack(nums, res, oneRes, i + 1); // backtrack
            // 如果上一行是start + 1, 结果会包含[3,2][3,2,1]
            oneRes.remove(oneRes.size() - 1); // 撤销选择
        }
    }

```

2. [77. Combinations](https://leetcode.com/problems/combinations/) （组合 元素无重不可复选）

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

``` Java
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> oneRes = new ArrayList<>();
        backtrack(res, oneRes, n, k, 1);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> oneRes, int n, int k, int start) {
        // base case
        if (oneRes.size() == k) { // 设置了添加方式
            res.add(new ArrayList<Integer>(oneRes));
            return;
        }
        for (int i = start; i <= n; i++) { // i = start 子集问题
            oneRes.add(i);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            backtrack(res, oneRes, n, k, i + 1); // 如果是start + 1，就会有[2,4], [4,2]
            oneRes.remove(oneRes.size() - 1);
        }
    }

```




3. [46. Permutations](https://leetcode.com/problems/permutations/) 排列（元素无重不可复选）

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

```Java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> oneRes = new ArrayList<>();
        backtrack(res, oneRes, nums);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> oneRes, int[] nums) {
        // base case
        if (oneRes.size() == nums.length) {
            res.add(new ArrayList<Integer>(oneRes));
            return;
        }
        // for 选择 in 选择列表
        for (int i = 0; i < nums.length; i++) { // i = 0 满树，排列问题
            // 不合法的选择
            if (oneRes.contains(nums[i])) { // 也可以用boolean[] visited来存
            //如果没有的话，结果有[1,1,1],[1,1,2]...
                continue;
            }
            oneRes.add(nums[i]);
            backtrack(res, oneRes, nums);
            oneRes.remove(oneRes.size() - 1);
        }
        
    }
}


```

4. [90. Subsets II](https://leetcode.com/problems/subsets-ii/)

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

```Java
// 需要先进行排序，让相同的元素靠在一起，如果发现nums[i] == nums[i-1]，则跳过：
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> oneRes = new ArrayList<>();
        // 需要排序，帮助剪枝
        Arrays.sort(nums);
        backtrack(res, oneRes, nums, 0);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> oneRes, int[] nums, int start) {
        res.add(new ArrayList<Integer>(oneRes));
        for (int i = start; i < nums.length; i++) { // i = start：子集问题
            // 排除不合法的选择
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            oneRes.add(nums[i]);
            backtrack(res, oneRes, nums, i + 1);
            oneRes.remove(oneRes.size() - 1);
        }
    }
}


```

5. [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

```Java
// 同第四题，只是base case不同

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 元素可重复，要排序
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> oneRes = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, oneRes, candidates, target, 0);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> oneRes, int[] candidates, int target, int start) {
        // base case
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<Integer>(oneRes));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            oneRes.add(candidates[i]);
            target -= candidates[i];
            backtrack(res, oneRes, candidates, target, i + 1);
            oneRes.remove(oneRes.size() - 1);
            target += candidates[i];
        }
    }
}

```

6. [47. Permutations II](https://leetcode.com/problems/permutations-ii/)

Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]

```Java
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> oneRes = new ArrayList<>();
        // use visited to keep track of visiting
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        backtrack(res, oneRes, nums, visited);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> oneRes, int[] nums, boolean[] visited) {
        if (oneRes.size() == nums.length) {
            res.add(new ArrayList<Integer>(oneRes));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
            // !visited[i - 1]保证相同元素在排列中的相对位置保持不变。
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            oneRes.add(nums[i]);
            backtrack(res, oneRes, nums, visited);
            oneRes.remove(oneRes.size() - 1);
            visited[i] = false;
        }
    }

```

7. [39. Combination Sum](https://leetcode.com/problems/combination-sum/)

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

```Java
class Solution {
    // 与区别只是2点：
    // 1. 不怕重复就不用条件
    // 2. backtrack内i+1变成i
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 元素可重复，要排序
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> oneRes = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, oneRes, candidates, target, 0);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> oneRes, int[] candidates, int target, int start) {
        // base case
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<Integer>(oneRes));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            // if (i > start && candidates[i] == candidates[i - 1]) {
            //     continue;
            // }
            oneRes.add(candidates[i]);
            target -= candidates[i];
            backtrack(res, oneRes, candidates, target, i); 
            oneRes.remove(oneRes.size() - 1);
            target += candidates[i];
        }
    }
}
```

## 回溯整理总结
### 形式一、元素无重不可复选，即nums中的元素都是唯一的，每个元素最多只能被使用一次

```Java
/* 组合/子集问题回溯算法框架 */
void backtrack(int[] nums, int start) {
    // 回溯算法标准框架
    for (int i = start; i < nums.length; i++) {
        // 做选择
        track.addLast(nums[i]);
        // 注意参数
        backtrack(nums, i + 1);
        // 撤销选择
        track.removeLast();
    }
}

/* 排列问题回溯算法框架 */
void backtrack(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        // 剪枝逻辑
        if (used[i]) {
            continue;
        }
        // 做选择
        used[i] = true;
        track.addLast(nums[i]);

        backtrack(nums);
        // 取消选择
        track.removeLast();
        used[i] = false;
    }
}
```

### 形式二、元素可重不可复选，即nums中的元素可以存在重复，每个元素最多只能被使用一次，其关键在于排序和剪枝
```Java
Arrays.sort(nums);
/* 组合/子集问题回溯算法框架 */
void backtrack(int[] nums, int start) {
    // 回溯算法标准框架
    for (int i = start; i < nums.length; i++) {
        // 剪枝逻辑，跳过值相同的相邻树枝
        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }
        // 做选择
        track.addLast(nums[i]);
        // 注意参数
        backtrack(nums, i + 1);
        // 撤销选择
        track.removeLast();
    }
}


Arrays.sort(nums);
/* 排列问题回溯算法框架 */
void backtrack(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        // 剪枝逻辑
        if (used[i]) {
            continue;
        }
        // 剪枝逻辑，固定相同的元素在排列中的相对位置
        if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
            continue;
        }
        // 做选择
        used[i] = true;
        track.addLast(nums[i]);

        backtrack(nums);
        // 取消选择
        track.removeLast();
        used[i] = false;
    }
}

```

### 形式三、元素无重可复选，即nums中的元素都是唯一的，每个元素可以被使用若干次，只要删掉去重逻辑即可
```Java
/* 组合/子集问题回溯算法框架 */
void backtrack(int[] nums, int start) {
    // 回溯算法标准框架
    for (int i = start; i < nums.length; i++) {
        // 做选择
        track.addLast(nums[i]);
        // 注意参数
        backtrack(nums, i);
        // 撤销选择
        track.removeLast();
    }
}


/* 排列问题回溯算法框架 */
void backtrack(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        // 做选择
        track.addLast(nums[i]);

        backtrack(nums);
        // 取消选择
        track.removeLast();
    }
}

```