# Inorder traverse
平衡二叉树的中序遍历，结果是从小到大顺次排列
```Java
 public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root = null) { //判断edge case
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) { //先把所有最左走完
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); //第一个数取出来
            res.add(root.val); //第一个数加进来
            root = root.right; //中序遍历，立刻走到右边
        }
        return res;
    }
```