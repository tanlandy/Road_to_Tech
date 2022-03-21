
## 二叉树遍历

### level-order(BFS)
1. []()
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
            int size = queue.size();
            for (int i = 0; i < size; i++) { // 一次走一个node
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

2. [103](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)

```Java
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOdd = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> oneRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                oneRes.add(node.val);
            }
            if (!isOdd) {
                Collections.reverse(oneRes);
            }
            isOdd = !isOdd;
            res.add(oneRes);
        }
        return res;
    }
```

3. [297](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)

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

4. [314. Binary Tree Vertical Order Traversal](https://leetcode.com/problems/binary-tree-vertical-order-traversal/)

Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

思路：
Queue存<node, col>
用一个HashMap<col, oneRes>
遍历的时候，更新HashMap
最后用HashMap来导出，但是不知道最小值最大值，所以实时更新一下
时间：O(N)
空间：O(N)

```Java
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // HashMap<col, oneRes>
        // Queue<TreeNode, col>
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>(); // 注意如何定义
        Map<Integer, ArrayList> map = new HashMap<>();
        int col = 0;
        int minCol = 0;
        int maxCol = 0;
        queue.offer(new Pair(root, col)); // 注意如何offer pair
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> top = queue.poll();
                TreeNode node = top.getKey();
                col = top.getValue();
                if (!map.containsKey(col)) {
                    map.put(col, new ArrayList<>());
                }
                map.get(col).add(node.val);
                if (node.left != null) {
                    queue.offer(new Pair(node.left, col - 1)); // 加左边
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, col + 1)); // 加右边
                }
                minCol = Math.min(minCol, col); // 为了帮助最后的导出
                maxCol = Math.max(maxCol, col);
            }                               
        }
        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }
```
1. [958. Check Completeness of a Binary Tree](https://leetcode.com/problems/check-completeness-of-a-binary-tree/)

5. [515. Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/)

