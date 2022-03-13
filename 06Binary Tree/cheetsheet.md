## 二叉树的定义
### TreeNode
```Java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

```

## 二叉树遍历
每次写递归，都按照这三要素来写，可以保证大家写出正确的递归算法！

1. 确定递归函数的参数和返回值： 确定哪些参数是递归的过程中需要处理的，那么就在递归函数里加上这个参数， 并且还要明确每次递归的返回值是什么进而确定递归函数的返回类型。

2. 确定终止条件： 写完了递归算法, 运行的时候，经常会遇到栈溢出的错误，就是没写终止条件或者终止条件写的不对，操作系统也是用一个栈的结构来保存每一层递归的信息，如果递归没有终止，操作系统的内存栈必然就会溢出。

3. 确定单层递归的逻辑： 确定每一层递归需要处理的信息。在这里也就会重复调用自己来实现递归的过程。
### PreOrder
[144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)
``` Java
// pre-order
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private void preorderTraversal(TreeNode root, List<Integer> answer) {
        if (root == null) { // 单层循环的逻辑
            return;
        }
        answer.add(root.val);                   // visit the root
        preorderTraversal(root.left, answer);   // traverse left subtree
        preorderTraversal(root.right, answer);  // traverse right subtree
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        preorderTraversal(root, answer);
        return answer;
    }
}

// iterative-Preorder
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return res;
        }
        stack.push(root);
        
        while (!stack.isEmpty()) { // 注意条件
            TreeNode node = stack.pop();
            res.add(node.val); // 先把parent放进来
            if (node.right != null) { // 先进后出，先放右，后放左
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }



```

### inorder
```Java

// recursive
class Solution {
    private void inorderTraversal(TreeNode root, List<Integer> answer) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, answer);   // traverse left subtree
        answer.add(root.val);                  // visit the root
        inorderTraversal(root.right, answer);  // traverse right subtree
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        inorderTraversal(root, answer);
        return answer;
    }
}

// iterative-Inorder 中序遍历顺序: 左-中-右 入栈顺序： 左-右
  public List<Integer> inorderTraversal(TreeNode root) {
        // stack iteratively traverse
        // go to the bottom left
        // pop the node, add, go to right
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode node = root;
        while (!stack.isEmpty() || node != null) { // 容易忽视||的情况
            while (node != null) { // keep going left
                stack.push(node);
                node = node.left;
            }
            // reach the bottom left
            node = stack.pop(); // fetch the node
            res.add(node.val); // add to the res
            node = node.right; // go to the right
        }
        return res;
        
    }
```
### Post
```Java
// post-order
class Solution {
    private void postorderTraversal(TreeNode root, List<Integer> answer) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, answer);   // traverse left subtree
        postorderTraversal(root.right, answer);  // traverse right subtree
        answer.add(root.val);                    // visit the root
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        postorderTraversal(root, answer);
        return answer;
    }
}

// postorder-iterative
// 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null){
                stack.push(node.left);
            }
            if (node.right != null){
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}

```

### level-order(BFS)
```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//  单Queue法，牢记于心
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) { // 一次走一层
            List<Integer> oneRes = new ArrayList<>(); // 走一层，那就要每次新建一层
            int n = queue.size();
            for (int i = 0; i < n; i++) { // 一次走一个node
                TreeNode node = queue.poll(); // 走一个node，那就要每次新建一个node
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                oneRes.add(node.val);
            }
            res.add(oneRes);
        }
        return res;
    }
}
```

## 二叉树的属性

### 对称
[226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)
```Java

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                swap(node); // 每到这一层的一个节点，交换他的指向
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
    private void swap(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
```

[101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)
```Java
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }
    private boolean compare(TreeNode left, TreeNode right) { // 参数和返回值
        // base case
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        // 单层逻辑
        return compare(left.left, right.right) && compare(left.right, right.left);
    }
```

[100]
```Java
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || p == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(q.right, p.right);
    }

```


### 最大、最小深度
[104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

思路：前序遍历求深度->中左右
```Java
    public int maxDepth(TreeNode root) {
        // 到底了
        if (root == null) {
            return 0;
        }
        // 左右中，最大深度就是根节点的高度
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
```

[111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)
```Java
    public int minDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }
```

### 平衡二叉树
[110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)
是否高度平衡

思路：后续遍历求高度->左右中，最后求中才能知道高度

```Java
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;
    }
    
    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左右中，后续遍历
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        // balance条件
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        // 中，求高度
        return Math.max(left, right) + 1;
    }

```

## 二叉树的修改和构造

### 路径
[257. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/)

```Java
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        dfs(root, "", paths);
        return paths;
    }
    private void dfs(TreeNode root, String onePath, List<String> paths) {
        if (root == null) {
            return;
        }
        // 每到一个新node，加进来
        onePath += Integer.toString(root.val);
        // 如果本node是leaf
        if (root.left == null && root.right == null) {
            paths.add(onePath);
        } else {
            onePath += "->";
            dfs(root.left, onePath, paths);
            dfs(root.right, onePath, paths);
        }
    }


```

### 翻转二叉树

### 中序后序遍历构造二叉树

### 合并二叉树

## 二叉树祖先

### 二叉树最近公共祖先

### 二叉搜索树最近公共祖先

## 二叉搜索树的属性

### 搜索

### 验证二叉搜索树

## 二叉搜索树的修改和构造

### 二叉搜索树插入

### 二叉搜索树删除