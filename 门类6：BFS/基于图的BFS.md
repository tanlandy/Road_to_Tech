## 模板
为了不走回头路，用一个visited记录走过的路
二叉树不用担心因为永远不会走回头路
```Java
/**
 * Return the length of the shortest path between root and target node.
 */
int BFS(Node root, Node target) {
    Queue<Node> queue;  // store all nodes which are waiting to be processed
    Set<Node> visited;  // store all the nodes that we've visited
    int step = 0;       // number of steps neeeded from root to current node
    // initialize
    add root to queue;
    add root to visited;
    // BFS
    while (queue is not empty) {
        // iterate the nodes which are already in the queue
        int size = queue.size();
        for (int i = 0; i < size; ++i) {
            Node cur = the first node in queue;
            return step if cur is target;
            for (Node next : the neighbors of cur) {
                if (next is not in visited) {
                    add next to queue;
                    add next to visited;
                }
            }
            remove the first node from queue;
        }
        step = step + 1;
    }
    return -1;          // there is no path from root to target
}
```

另一个模板：8个方向
```Java

directions = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]

define function get_neighbors(row, col):
neighbors = a container to put the neighbors of (row, col) in
    for each (row_direction, col_direction) pair in directions:
        neighbor_row = row + row_direction
        neighbor_col = col + col_direction
        if (neighbor_row, neighbor_col) is NOT over the edge of the grid AND is 0:
            add (neighbor_row, neighbor_col) to neighbors
    return neighbors
```


1. [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

思路一：

直接在原来的grid上改，每个格子记录当前的distance，每次走一个就+1
queue每次把满足条件的放进来，放进来之后更新这个格子的distance
时间：O(n)
空间：O(n)
```Java
    private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }
        
        // Set up the BFS.
        Queue<int[]> queue = new ArrayDeque<>();
        grid[0][0] = 1;
        queue.add(new int[]{0, 0});
        
        // Carry out the BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int col = cell[1];
            int distance = grid[row][col];
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return distance;
            }
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                queue.add(new int[]{neighbourRow, neighbourCol});
                grid[neighbourRow][neighbourCol] = distance + 1;
            }
        }
        
        // The target was unreachable.
        return -1;
    }
    
    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length 
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;    
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours; 
     }
```


思路一overwriting的弊端：

1. That the algorithm is running in a *multithreaded* environment, and it does not have exclusive access to the grid. Other threads might need to read the grid too, and might not expect it to be modified.
在多线程环境中读取，不止有自己在读，所以不能更改


2. That there is only a single thread or the algorithm has exclusive access to the grid while running, but the grid might need to be reused later or by another thread once the lock has been released.
这个grid之后可能被复用


思路二： 
用一个visited，并且把distance直接存储在queue里

```Java 
    private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }
        
        // Set up the BFS.
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 1}); // Put distance on the queue
        boolean[][] visited = new boolean[grid.length][grid[0].length]; // Used as visited set.
        visited[0][0] = true;
        
        // Carry out the BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int col = cell[1];
            int distance = cell[2];
            // Check if this is the target cell.
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return distance;
            }
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                if (visited[neighbourRow][neighbourCol]) {
                    continue;
                }
                visited[neighbourRow][neighbourCol] = true;
                queue.add(new int[]{neighbourRow, neighbourCol, distance + 1});
            }
        }
        
        // The target was unreachable.
        return -1;  
    }
    
    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length 
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;    
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours; 
    }
```


2. [463. Island Perimeter](https://leetcode.com/problems/island-perimeter/)

方法一：
只计算和水相邻的边

时间：O(mn)
空间：O(1)
```Java
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
    
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) num++; // up
                    if (j == 0 || grid[i][j - 1] == 0) num++; // left
                    if (i == rows -1 || grid[i + 1][j] == 0) num++; // DOWN
                    if (j == cols -1 || grid[i][j + 1] == 0) num++; // RIGHT
                }
            }
        }
        return num;
    }
```

3. [133. Clone Graph](https://leetcode.com/problems/clone-graph/)

方法一：BFS
map<原来node, 对应的复制的node>，防止成环带来的重复
遍历每个节点的neighbor
要是没见过，就新建一个
要是见过，就直接加到这个neighbor的neighbor里
时间：O(N+M) N是node数量，M是edges数量
空间：O(N)
```Java
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        HashMap<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, new Node(node.val, new ArrayList()));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node neighbor : cur.neighbors) {
                if (!visited.containsKey(neighbor)) { // Clone the neighbor and put in the visited, if not present already
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    queue.offer(neighbor);
                }
                visited.get(cur).neighbors.add(visited.get(neighbor)); // Add the clone of the neighbor to the neighbors of the clone node "n".
            }
        }
        return visited.get(node);
    }
```

4. [317. Shortest Distance from All Buildings](https://leetcode.com/problems/shortest-distance-from-all-buildings/)

5. [127. Word Ladder](https://leetcode.com/problems/word-ladder/)

6. [1293. Shortest Path in a Grid with Obstacles Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)