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
## Templates
```Java
// swap nodes in pairs: (1,2,3,4) -> (2,1,4,3)
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode pre = dummy;
    while (pre.next != null && pre.next.next != null) { // 因为second = pre.next.next
        ListNode first = pre.next; // first = 1
        ListNode second = pre.next.next; // second = 2;
        first.next = second.next; // 1 -> 3;
        second.next = first; // 2 -> 1;
        pre.next = second; // pre -> 2;
        pre = first; // pre = 1;
    }
    return dummy.next;
}




```