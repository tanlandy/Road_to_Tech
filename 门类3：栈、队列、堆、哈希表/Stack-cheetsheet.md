# Stack
## Initiate
`Stack<String> stack = new Stack<>();`
## Methods
```Java
// 栈顶栈底
stack.push() // O(1) 放入栈顶
stack.pop() // O(1) 删除并且return栈顶
stack.peek() // O(1)
```



[232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)

思路：一个入栈，一个出栈。每次需要出的时候，把入栈都推送到出栈。每次入的时候，只推到入栈

```Java
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }
    
    public void push(int x) {
        stackIn.push(x);
    }
    
    public int pop() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }
    
    public int peek() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }
    
    public boolean empty() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```


[225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)

思路：随时保持queue1和栈的顺序一样

```Java
class MyStack {

    Queue<Integer> queue1; // 保持一样元素的队列
    Queue<Integer> queue2; // 辅助用来复制的队列
    
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    public void push(int x) {
        // 放的时候，每次不一样
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> queueTemp;
        queueTemp = queue1;
        queue1 = queue2;
        queue2 = queueTemp;
    }
    
    public int pop() {
        return queue1.poll();
    }
    
    public int top() {
        return queue1.peek();
    }
    
    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```

[1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)

Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

```Java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        // top 是sb的长度
        int top = -1;
        for (char c : s.toCharArray()) {
            // 当栈中有字符时候，如果当前字符和栈顶相等，就弹出，同时top--
            if (top >= 0 && c == sb.charAt(top)) {
                sb.deleteCharAt(top);
                top--;
            } else {
                sb.append(c);
                top++;
            }
        }
        return sb.toString();
    }
}
```

[150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

```Java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (!"+-*/".contains(token)) { // 遇到数字就加进去
                stack.push(Integer.valueOf(token));
                continue;
            }
            int num2 = stack.pop();
            int num1 = stack.pop();
            int res = 0;
            switch (token) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    res = num1 / num2;
                    break;
            }
            stack.push(res);
        }
        return stack.pop();
    }
}
```

[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

方法一：
1. 统计出现次数
2. 元素进行排序
3. 小顶堆
4. 变
时间复杂度：O(NlogK)
空间：O(N+K)
```Java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        
        // 1，统计元素出现频率
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
          count.put(n, count.getOrDefault(n, 0) + 1);
        }
        
        // init heap 'the less frequent element first' 
        // 2. 对元素进行排序        
        Queue<Integer> heap = new PriorityQueue<>(
            (n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        // 3. 构建小顶堆
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
          heap.add(n);
          if (heap.size() > k) heap.poll();    
        }

        // 4. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
}
```