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