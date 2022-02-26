看课件视频之前看这个
https://www.youtube.com/watch?v=9Jry5-82I68&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=5 


## init
```Java
// Comparator比较器-ListNode
Comparator<ListNode> comparator = new Comparator<ListNode>() {
    public int compare(ListNode node1, ListNode node2) {
        return node1.val - node2.val;
    }
};
        
PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.length, comparator);

// 构建minHeap
for (int i = 0; i < lists.length; i++) {
    if (lists[i] != null) {
        minHeap.add(lists[i]);
    }
}
```

## methods
```Java
minHeap.add(lists[i]);
minHeap.poll(); // 取出来并删除




```