# Usage
1. Find minimum path/distance
2. Queue

# Template
```Java
// two queues zigzagOrder遍历二叉树
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
            Queue<TreeNode> queue2 = new LinkedList<>(); // the second queue，存下一层
            while (!queue.isEmpty()) { // 存满下一层，一次走一个节点
                TreeNode top = queue.poll();
                if (top.left != null) {
                    queue2.offer(top.left);
                }
                if (top.right != null) {
                    queue2.offer(top.right);
                }
                oneRes.add(top.val);
            }
            
            if (!isOdd) {
                Collections.reverse(oneRes);
            }
            
            res.add(oneRes); // 把res加进来
            isOdd = !isOdd; // 反转
            queue = queue2; // queue存下一层
            
        }
        return res;
    }

// Serialize and Deserialize Binary Tree
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