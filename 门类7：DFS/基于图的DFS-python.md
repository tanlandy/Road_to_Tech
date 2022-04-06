
## 模板

时间：O(V + E)

```python
graph: {
    "A" = ["B", "G"]
    "G" = ["B", "G"]
    "D" = ["B", "G"]
    "P" = ["B", "G"]
    "H" = ["B", "G"]
    "C" = ["B", "G"]
}

def dfs(graph, node):
    visited = []
    stack = deque()

    visited.append(node)
    stack.append(node)

    while stack:
        s = stack.pop()
        print(s, end = " ")

        for n in reversed(graph[s]):
            if n not in visited:
                visited.append(n)
                stack.append(n)


```



1. [133. Clone Graph](https://leetcode.com/problems/clone-graph/)
方法二：DFS


1. [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)

2. [721. Accounts Merge](https://leetcode.com/problems/accounts-merge/)

3. [133. Clone Graph](https://leetcode.com/problems/clone-graph/)

4. [827. Making A Large Island](https://leetcode.com/problems/making-a-large-island/)

