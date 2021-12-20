public class Node {
    // The class has two properties, a String data, and a Node next. At the top of
    // the class, declare both variables as public.
    public String data;
    public Node next;

    // Since nodes contain data, we want the constructor to expect a data String to
    // be passed in.

    // Declare a constructor for the Node class that takes the data as a parameter.
    public Node(String data) {
        // set the class’s data variable to the passed in data
        // set the next property to null
        // Each new Node should have its next property defaulted to null since it hasn’t
        // been assigned a next Node yet.
        this.data = data;
        this.next = null;
    }

    // Create the .setNextNode() method in the Node class. It should take a Node
    // named node as an argument.
    public void setNextNode(Node node) {
        // set the Node’s next property to the node parameter.
        this.next = node;
    }

    // create a simple .getNextNode() method that will return the next node
    // property.
    public Node getNextNode() {
        return this.next;
    }

    public static void main(String[] args) {
        // create a new Node named firstNode with the argument "I am a Node!".
        Node firstNode = new Node("I am a Node!");
        System.out.println(firstNode.data);
        System.out.println(firstNode.next);

        // set the next node
        Node secondNode = new Node("I am the second Node!");
        firstNode.setNextNode(secondNode);
        System.out.println(firstNode.next.data);

    }
}