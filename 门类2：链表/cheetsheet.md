## To pay attention 
### communicate before writing codes
1. size
2. index range
3. tail

### while coding
1. check null when using while
2. if (head == null) return false;
3. If data structure of the linkedlist will be changed, dummy node is needed.
4. If structure (including head) will be changed, dummy node is needed.
5. while (node != null) {node.next;}
6. while (pre.next != null) {pre.next, pre.next.next;}
7. **edge cases**
# Templetes

# List
## Initiate
` List<Integer> list = new LinkedList<>();`
## Methods
```Java
list.add(5) // add; O(1)
list.get(index) // get; O(n)
list.set(index, value) // set value at index; O(n)
list.add(opt_index, value) // add value at index; O(n)
list.remove(index,value) // O(n)
```
## 开头
```Java
// always check if null
    if (head == null) {
        return false;
    }
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode pre = dummy;
```

## 找中间点
```Java
// two pointers to find middleNode
public ListNode middleNode(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null) { 
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
```

## 链表反转
```Java
// reverse Linked List: [1,2,3,4,5] -> [5,4,3,2,1]
public ListNode reverseLinkedList(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
        ListNode temp = cur.next; // store 2
        cur.next = pre; // 1 -> null
        pre = cur; // 站到1，从而下一轮2 -> 1
        cur = temp; // 从1站到2
    }
    return pre;
}

// swap nodes in pairs: [1,2,3,4] -> [2,1,4,3]
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode pre = dummy;
    while (pre.next != null && pre.next.next != null) { // 因为second = pre.next.next
    // 从[pre,1,2,3]变成[2,1,3]
        ListNode first = pre.next; // first = 1
        ListNode second = pre.next.next; // second = 2;
        first.next = second.next; // 1 -> 3;
        second.next = first; // 2 -> 1;
        pre.next = second; // pre -> 2;要把2连起来
        pre = first; // pre = 1;
    }
    return dummy.next;
}

// [1,2,3,4,5] -> [1,4,3,2,5]
 
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) { // pre will stay on 1
            pre = pre.next;
        }
        ListNode first = pre.next; // first stays on 2
        ListNode second = first.next; // second stays on 3
        // [1,  2,  3,4,5] -> [1,3,2,4,5] -> [1,4,3,2,5]
        // pre fir sec
        for (int i = 0; i < right - left; i++) {
            first.next = second.next; // 2 -> 4
            second.next = pre.next; // 3 -> 2
            pre.next = second; // 1 -> 3
            second = first.next; // second = 4
        }
        return dummy.next;
        
    }

```
## 奇偶
```Java
// [1,2,3,4,5,6] -> [1,3,5,2,4,6]
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head;
        ListNode even = odd.next;
        ListNode evenHead = even;
        // [1, 2, 3, 4, 5, 6] -> [1,3][2,4,5,6]
        //       odd
        
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = odd.next;
        }
        odd.next = evenHead;
        return head;
    }

```
## 成环问题
```Java
//  Intersection of Two Linked Lists
 
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            // a + c + b == b + c + a
            // if pA to the end, set it to b
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

// [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            if (fast == null || fast.next == null) { // 先找到成环的点
                return null;
            } 
            fast = fast.next.next;
            slow = slow.next; 
            if (slow == fast) { // 找到后，从头往后走，同时slow往后走，遇见的地方就是交叉点
                ListNode temp = head;
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
```




## Course
### Count related
1. Linked list length
2. Kth node from the end
3. Mid node of the list
4. Whether circle exists
### Structure related
5. Remove Duplicate from Sorted List
6. Reverse Linked List
7. *[Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)*
8. Merge Sorted List
9. Delete Node in a Linked List
### Homework
1. [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
2. [Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/)
3. [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
4. [Odd Even Linked List](https://leetcode.com/problems/odd-even-linked-list/)
### Homework Optional
1. *[Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/)*
2. *[Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/)*
3. *[Reorder List](https://leetcode.com/problems/reorder-list/#/description)*
4. *[Rotate List](https://leetcode.com/problems/rotate-list/)*
5. *[Partition List](https://leetcode.com/problems/partition-list/#/description)*
6. [Palindrom Linked List](https://leetcode.com/problems/palindrome-linked-list/?tab=Description)
7. [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/#/submissions/1)
## OA
1. [Palindrom Linked List](https://leetcode.com/problems/palindrome-linked-list/?tab=Description)
2. [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
3. *[Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)*



