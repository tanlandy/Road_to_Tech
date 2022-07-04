## init
```Java
// Comparator比较器-ListNode
PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
    lists.length,
    new Comparator<ListNode>() {
        public int compare(ListNode a, ListNode b) {
            return a.val - b.val;
        }
    }
);

// Comparator比较器-Integer
PriorityQueue<Integer> minHeap = new PriorityQueue<>(
    intervals.length,
    new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }
);



// 构建minHeap
for (int i = 0; i < lists.length; i++) {
    if (lists[i] != null) {
        minHeap.add(lists[i]);
    }
}


// Compare 
```

## methods
```Java
minHeap.add(lists[i]);
minHeap.poll(); // 取出来并删除




```