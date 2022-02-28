## PreOrder
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
        if (root == null) {
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

public static void preOrder(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> res = new ArrayList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        res.add(root.val); // visit
        if (root.right != null) { // push right
            stack.push(root.right);
        }
        if (root.left != null) { // push left
            root = root.left;
        } else {
            root = stack.pop(); // pop the left first
        }
    }
}


```

## inorder
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

// iterative
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return res;
        }
        stack.push(root);
        while (!stack.isEmpty()) { // 注意条件
            root = stack.pop();
            res.add(root.val); // 先把parent放进来
            if (root.right != null) { // 先进后出，先放右，后放左
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
            
        }
        return res;
    }

// iterative
  public List<Integer> inorderTraversal(TreeNode root) {
        // stack iteratively traverse
        // go to the bottom left
        // pop the node, add, go to right
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        while (!stack.isEmpty() || root != null) { // 容易忽视||的情况
            while (root != null) { // keep going left
                stack.push(root);
                root = root.left;
            }
            // reach the bottom left
            root = stack.pop(); // fetch the node
            res.add(root.val); // add to the res
            root = root.right; // go to the right
        }
        return res;
        
    }
```
## Post
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

```

## level-order(BFS)
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

//  双Queue法，牢记于心
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) { // 外层判断是否有元素
            List<Integer> oneRes = new ArrayList<>();
            Queue<TreeNode> queue2 = new LinkedList<>(); // 外层中新建queue
            while (!queue.isEmpty()) { // 内层while形成一整层
                TreeNode top = queue.poll(); // 拿出来，内层中拿出来那一整层
                if (top.left != null) { // 放到Queue2里
                    queue2.offer(top.left);
                }
                if (top.right != null) {
                    queue2.offer(top.right);
                }
                oneRes.add(top.val); //形成一整层
            }
            res.add(oneRes); // 加上一整层
            queue = queue2;
        }
        return res;
    }
}
```