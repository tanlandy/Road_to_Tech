树的深度是从0开始

## 二叉树遍历

### level-order(BFS)

queue放元素：queue = deque([root])

1. []()
```python
class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def level_order_traversal(root: Node) -> List[List[int]]:
    res = [] 
    if root is None:
        return res
    
    queue = deque([root])
    
    while queue:
        oneRes = []
        size = len(queue)
        for _ in range(size):
            node = queue.popleft()
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
            oneRes.append(node.val)
        res.append(oneRes)
                      
    
    return res
```

[103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)

反转list: oneRes.reverse()；翻转isOdd: isOdd = not isOdd

```python
def zig_zag_traversal(root: Node) -> List[List[int]]:
    res = []
    isOdd = True
    
    if root is None:
        return res
    
    queue = deque([root])
    
    while queue:
        oneRes = []
        size = len(queue)
        
        for _ in range(size):
            node = queue.popleft()
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
            oneRes.append(node.val)
       
        if not isOdd:
            oneRes.reverse()
        
        isOdd = not isOdd
        res.append(oneRes)
     
    return res
```

1. [297. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)

```Java
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) { // 不能少
            return "";
        }
        // use queue to serialize
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root); // 把根节点放进来
        while (!queue.isEmpty()) { // 一次走一个节点
            TreeNode node = queue.poll();
            if (node == null) { // 如果是空，就放进来空，然后跳过这个节点
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
        if (data == "") { // 不能少
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        String[] values = data.split(" ");                          // 先搞一个数组存起来
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));  // String转成数字的方法
        queue.offer(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode top = queue.poll();
            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                top.left = left;
                queue.offer(left);
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


[199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

from collections import deque; queue放元素：queue = deque([root])

时间：O(N)
空间：O(D) D is diameter
```python
def binary_tree_right_side_view(root: Node) -> List[int]:
    # WRITE YOUR BRILLIANT CODE HERE
    res = []
    if root is None:
        return res
    
    queue = deque([root])
    
    while queue:
        size = len(queue)
        
        for i in range(size):
            node = queue.popleft()
            if i == size - 1:
                res.append(node.val)
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
    
    return res
```

binary tree min depth:

树的深度从0开始，所以res的起点是-1

```python
def binary_tree_min_depth(root: Node) -> int:
    res = -1
    if root is None:
        return res
    queue = deque([root])
    
    while queue:
        size = len(queue)
        res += 1
        for _ in range(size):
            node = queue.popleft()
            if node.left is None and node.right is None:
                return res
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
```