Each node contains data and two links (or pointers) to the next and previous nodes in the list. The head node is the node at the beginning of the list, and the tail node is the node at the end of the list. The head node’s previous pointer is set to null and the tail node’s next pointer is set to null.

## Common operations
1. adding nodes to both ends of the list
2. removing nodes from both ends of the list
3. finding, and removing, a node from anywhere in the list
4. traversing (or traveling through) the list

### Adding to the Head
When adding to the head of the doubly linked list, we first need to check if there is a current head to the list. If there isn’t, then the list is empty, and we can simply make our new node both the head and tail of the list and set both pointers to null. If the list is not empty, then we will:

1. Set the current head’s previous pointer to our new head
2. Set the new head’s next pointer to the current head
3. Set the new head’s previous pointer to null

### Adding to the Tail
Similarly, there are two cases when adding a node to the tail of a doubly linked list. If the list is empty, then we make the new node the head and tail of the list and set the pointers to null. If the list is not empty, then we:

1. Set the current tail’s next pointer to the new tail
2. Set the new tail’s previous pointer to the current tail
3. Set the new tail’s next pointer to null

### Removing the Head
Removing the head involves updating the pointer at the beginning of the list. We will set the previous pointer of the new head (the element directly after the current head) to null, and update the head property of the list. If the head was also the tail, the tail removal process will occur as well.

### Removing the Tail
Similarly, removing the tail involves updating the pointer at the end of the list. We will set the next pointer of the new tail (the element directly before the tail) to null, and update the tail property of the list. If the tail was also the head, the head removal process will occur as well.

### Removing from the Middle of the List
It is also possible to remove a node from the middle of the list. Since that node is neither the head nor the tail of the list, there are two pointers that must be updated:

1. We must set the removed node’s preceding node’s next pointer to its following node
2. We must set the removed node’s following node’s previous pointer to its preceding node

# Doubly Linked Lists:

- Are comprised of nodes that contain links to the next and previous nodes
- Are bidirectional, meaning it can be traversed in both directions
- Have a pointer to a single head node, which serves as the first node in the list
- Have a pointer to a single tail node, which serves as the last node in the list
- Require the pointers at the head of the list to be updated after addition to or removal of the head
- Require the pointers at the tail of the list to be updated after addition to or removed of the tail
- Require the pointers of the surrounding nodes to be updated after removal from the middle of the list