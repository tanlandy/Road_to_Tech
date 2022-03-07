# Usage
1. Find minimum path/distance
2. Queue

# Template

1. two queues zigzagOrder遍历二叉树
``` Java
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
```
2. Serialize and Deserialize Binary Tree

```Java
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

3. 最浅深度

```Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 1;
        while (!queue.isEmpty()) {
            Queue<TreeNode> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode top = queue.poll();
                if (top.left == null & top.right == null) { // 与普通遍历唯一不同之处，提前退出即可
                    return res;
                }
                if (top.left != null) {
                    queue2.offer(top.left);
                }
                if (top.right != null) {
                    queue2.offer(top.right);
                }
            }
            queue = queue2;
            res++;
        }
        return res;
    }
}
```


4. [752. Open the Lock](https://leetcode.com/problems/open-the-lock/)

You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation: 
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

```Java
class Solution {
    public int openLock(String[] deadends, String target) {
        // use a queue to iterate solutions
        // check the deadend during iteration
        Queue<String> queue = new LinkedList<>();
        Set<String> deadend = new HashSet<>();
        for (String s : deadends) {
            deadend.add(s);
        }
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            Queue<String> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                String cur = queue.poll();
                if (deadend.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        queue2.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j) ;
                    if (!visited.contains(down)) {
                        queue2.offer(down);
                        visited.add(down);
                    }
                }
            }
            queue = queue2;
            step++;
        }
        return -1;
    }
    
    private String plusOne(String s, int j) {
        char[] c = s.toCharArray();
        if (c[j] == '9') {
            c[j] = '0';
        } else {
            c[j] += 1;
        }
        return new String(c);
    }
    
    private String minusOne(String s, int j) {
        char[] c = s.toCharArray();
        if (c[j] == '0') {
            c[j] = '9';
        } else {
            c[j] -= 1;
        }
        return new String(c);
    }
}




```