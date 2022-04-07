
## 二叉树遍历
每次写递归，都按照这三要素来写，可以保证大家写出正确的递归算法！

1. 确定递归函数的参数和返回值： 确定哪些参数是递归的过程中需要处理的，那么就在递归函数里加上这个参数， 并且还要明确每次递归的返回值是什么进而确定递归函数的返回类型。

2. 确定终止条件： 写完了递归算法, 运行的时候，经常会遇到栈溢出的错误，就是没写终止条件或者终止条件写的不对，操作系统也是用一个栈的结构来保存每一层递归的信息，如果递归没有终止，操作系统的内存栈必然就会溢出。

3. 确定单层递归的逻辑： 确定每一层递归需要处理的信息。在这里也就会重复调用自己来实现递归的过程。



### PreOrder
[144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)
``` python

class Solution:
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        res = []
        self.dfs(root, res)
        return res
    
    def dfs(self, root, res):
        if root is None:
            return
        
        res.append(root.val)
        self.dfs(root.left, res)
        self.dfs(root.right, res)
```

### inorder
```python
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        res = []
        self.dfs(root, res)
        return res
    
    def dfs(self, root, res):
        if root is None:
            return
        
        self.dfs(root.left, res)
        res.append(root.val)
        self.dfs(root.right, res)
```

iterative:关键是什么时候压栈，什么时候弹栈
压栈：走左子树的时候，非空就加进来
弹栈：左子树空的时候，同时加到res，然后走右子树，同时加到res

一直走左子树，边走边压栈。直到左边走空了，pop并加到res里，然后走一个右子树，然后压一个右节点进栈

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        res = []
        stack = []
        cur = root

        while cur or stack:
            while cur:
                stack.append(cur)
                cur = cur.left
            cur = stack.pop()
            res.append(cur.val)
            cur = cur.right

        return res
```


### Post
```python
class Solution:
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        res = []
        self.dfs(root, res)
        return res
    
    def dfs(self, root, res):
        if root is None:
            return
        
        self.dfs(root.left, res)
        self.dfs(root.right, res)
        res.append(root.val)
```

### Level order

```Java

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }
    
    private void dfs(TreeNode node, int level, List<List<Integer>> res) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        if (node.left != null) {
            dfs(node.left, level + 1, res);
        }
        if (node.right != null) {
            dfs(node.right, level + 1, res);
        }
    }

```


## 例题
1. [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)

```Java
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root, root);
    }
    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val) && dfs(left.right, right.left)
            && dfs(left.left, right.right);
    }
```

2. [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }
    private void dfs(TreeNode node, int level, List<Integer> res) {
        if (res.size() == level) { // 每次就取第一次遇见的那一个
            res.add(node.val);
        }
        
        // 如果是leftside view，就先看左边
        if (node.right != null) { // 先看右边
            dfs(node.right, level + 1, res);
        }
        
        if (node.left != null) {
            dfs(node.left, level + 1, res);
        }
        return;
    }
}
```

2. [863. All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

思路：
用一个HashMap<>存储<Parent Node, cur Node>
然后针对从target，找出来距离为K的每一个点
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
class Solution {
   
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        dfs(root, null, parent);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    private void dfs(TreeNode node, TreeNode par, Map<TreeNode, TreeNode> parent) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node, parent);
            dfs(node.right, node, parent);
        }
    }
}

```


3. [865. Smallest Subtree with all the Deepest Nodes](https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/)

## 公共祖先

1. [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

用到BST的性质，直接比较大小就可以了
从根节点开始考察，如果p,q都比root小，则root移动至其左子树；否则，root移动至其右子树。直到发现p,q在root节点的两侧，则root是最低共同节点。

```Java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }    
    }
```

4. [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

分别往左右走，每次都返回这个点或者是p,q

```Java

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //base case
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //result
        if(left == null) {
            return right;
        }
        else if(right == null) {
            return left;
        }
        else { //both left and right are not null, we found our result
            return root;
        }
    }

```