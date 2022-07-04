public class LinkedList {
    private ListNode head = null;
    private ListNode tail = null; 
    private int size = 0; 
    // Java will automatically generate a non-param constructor if you don't have other constructors.
    
    public void checkBoundsExclusive(int index) {
        if (index < 0 || index >= size) {
            // throw Exception
        }
    }

    public ListNode getEntry(int index) {
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public int get(int index) {
        checkBoundsExclusive(index);
        return getEntry(index).value;
    }

    public void set(int index, int value) {
        checkBoundsExclusive(index);
        ListNode node = getEntry(index);
        node.val = value;
    }

    public void add(int index, int value) {
        checkBoundsExclusive(index);
        size++;
        // Use dummy head to avoid checking head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (index-- != 0) { // getEntry() 的实现
            pre = pre.next;
        }
        ListNode newNode = new ListNode(value);
        newNode.next = pre.next;
        pre.next = newNode;
        head = dummy.next;

        // if (index == 0) {
        //     newNode.next = head; // head在右边就是取值
        //     head = newNode;  // head在左边就是给head赋值
        // }
        // ListNode pre = getEntry(index - 1);
        // newNode.next = pre.next; // .next在左边就是给该node赋值（让它指向），在右边就是取值（它的指向是什么）
        // pre.next = newNode;

    }

    public void remove(int index) {
        checkBoundsExclusive(index);
        size--;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (index-- != 0) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        head = dummy.next;

    }

    public void removeByValue(int value) {

    }
}
