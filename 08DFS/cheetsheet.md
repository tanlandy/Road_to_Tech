## usage
1. Recursion and backtrace
2. Stack

## DFS for tree
Sub-problem 
Base case 
Recursion rule 
ALWAYS think about two branches (left and right), pseudo code is helpful.

## Templates
```Java
// number of islands
    public int numIslands(char[][] grid) {
        int res = 0; // 设置返回值
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0'; // 设置为0，不走回头路
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }


```