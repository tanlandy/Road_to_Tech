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

1. [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

2. [463. Island Perimeter](https://leetcode.com/problems/island-perimeter/)

3. [133. Clone Graph](https://leetcode.com/problems/clone-graph/)

4. [317. Shortest Distance from All Buildings](https://leetcode.com/problems/shortest-distance-from-all-buildings/)

5. [127. Word Ladder](https://leetcode.com/problems/word-ladder/)

