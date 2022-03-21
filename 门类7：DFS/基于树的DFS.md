
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

1. [958. Check Completeness of a Binary Tree](https://leetcode.com/problems/check-completeness-of-a-binary-tree/)

1. [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)

2. [865. Smallest Subtree with all the Deepest Nodes](https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/)

