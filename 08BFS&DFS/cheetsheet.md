# Usage
1. Find minimum path/distance
2. Queue

## DFS for tree
Sub-problem 
Base case 
Recursion rule 
ALWAYS think about two branches (left and right), pseudo code is helpful.


# Template
## 遍历树
1. two queues zigzagOrder遍历二叉树
``` Java
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>(); // store result
        // use two queues to traverse
        Queue<TreeNode> queue = new LinkedList<>(); // 存一层
        if (root != null) {
            queue.offer(root); // 第一层(root)
        }
        boolean isOdd = true; // to see if need to reverse
        while (!queue.isEmpty()) {  // 一次走一层
            List<Integer> oneRes = new ArrayList<>(); // one result
            int n = queue.size();
            for (int i = 0; i < n; i++) { // 存满一层
                TreeNode top = queue.poll();
                if (top.left != null) {
                    queue.offer(top.left);
                }
                if (top.right != null) {
                    queue.offer(top.right);
                }
                oneRes.add(top.val);
            }
           
            if (!isOdd) {
                Collections.reverse(oneRes);
            }
            
            res.add(oneRes); // 把res加进来
            isOdd = !isOdd; // 反转            
        }
        return res;
    }
```
2. Serialize and Deserialize Binary Tree

```Java
   // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        // use queue to serialize
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root); // 把根节点放进来
        while (!queue.isEmpty()) { // 一次走一个节点
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null ");
                continue;
            }
            sb.append(node.val + " ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 先把string分割成int
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        
        // 放进来第一个
        queue.offer(root);
        for (int i = 1; i < values.length; i++) { // 每次走一个非空节点
            TreeNode top = queue.poll();
            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                top.left = left;
                queue.offer(left); // 把节点加进来
            }
            if (!values[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                top.right = right;
                queue.offer(right);
            }
        }
        return root;
        
    }    

```

3. 最浅深度

```Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 1;
        while (!queue.isEmpty()) {
            Queue<TreeNode> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode top = queue.poll();
                if (top.left == null & top.right == null) { // 与普通遍历唯一不同之处，提前退出即可
                    return res;
                }
                if (top.left != null) {
                    queue2.offer(top.left);
                }
                if (top.right != null) {
                    queue2.offer(top.right);
                }
            }
            queue = queue2;
            res++;
        }
        return res;
    }
}
```


4. [752. Open the Lock](https://leetcode.com/problems/open-the-lock/)

You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation: 
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

```Java
class Solution {
    public int openLock(String[] deadends, String target) {
        // use a queue to iterate solutions
        // check the deadend during iteration
        Queue<String> queue = new LinkedList<>();
        Set<String> deadend = new HashSet<>();
        for (String s : deadends) {
            deadend.add(s);
        }
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            Queue<String> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                String cur = queue.poll();
                if (deadend.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        queue2.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j) ;
                    if (!visited.contains(down)) {
                        queue2.offer(down);
                        visited.add(down);
                    }
                }
            }
            queue = queue2;
            step++;
        }
        return -1;
    }
    
    private String plusOne(String s, int j) {
        char[] c = s.toCharArray();
        if (c[j] == '9') {
            c[j] = '0';
        } else {
            c[j] += 1;
        }
        return new String(c);
    }
    
    private String minusOne(String s, int j) {
        char[] c = s.toCharArray();
        if (c[j] == '0') {
            c[j] = '9';
        } else {
            c[j] -= 1;
        }
        return new String(c);
    }
}
```

## 遍历图

1. [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates/)

```Java

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = m == 0 ? 0 : rooms[0].length;
        int[][] dirs = {{-1,0}, {0,1}, {0,-1}, {1,0}};
        Queue<int[]> queue = new LinkedList<>();
        // add all gates to the queue
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (rooms[i][j] == 0) {
                    // queue存的是x, y
                    queue.offer(new int[] {i,j});
                }
            }
        }
        // update distance from gates
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] dir: dirs) {
                // 新x和y
                int X = curPos[0] + dir[0];
                int Y = curPos[1] + dir[1];
                if (X<0 || Y <0 || X >= m || Y >= n || rooms[X][Y] != Integer.MAX_VALUE) continue;
                // 距离+1
                rooms[X][Y] = rooms[curPos[0]][curPos[1]]+1;
                // 成功更新之后，要放回来，之后继续走
                queue.offer(new int[] {X, Y});
            }
        }
    }
```

2. [Number of Islands](https://leetcode.com/problems/number-of-islands/)

方法一：不用visited[][]
```Java
    // 因为要考虑count，所以用了一个bfs函数来遍历
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    queue.offer(new int[]{i, j});
                    bfs(grid, queue);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void bfs(char[][] grid, Queue<int[]> queue) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] curP = queue.poll();
            for (int[] dir : dirs) {
                int x = curP[0] + dir[0];
                int y = curP[1] + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != '1') {
                    continue;
                }
                grid[x][y] = '0';
                queue.offer(new int[]{x, y});
            }
        }
    }

```

方法二：用一个visited[][]存已经走过的点
```Java
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    queue.offer(new int[]{i, j});
                    bfs(grid, queue, visited);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, Queue<int[]> queue, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] curP = queue.poll();
            for (int[] dir : dirs) {
                int x = curP[0] + dir[0];
                int y = curP[1] + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != '1') {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
```

DFS
方法一：不用visited[][]
```Java
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0' ) {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
```

方法二，用visited[][]
```Java
   public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' & !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(grid, i + 1, j, visited);
        dfs(grid, i - 1, j, visited);
        dfs(grid, i, j + 1, visited);
        dfs(grid, i, j - 1, visited);
    }
```