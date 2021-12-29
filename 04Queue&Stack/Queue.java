public class Queue {
    private int size;
    private int capacity;
    private int front;
    private int rear;
    private String[] elementData;

    public Queue(int capacity_) {
        capacity = capacity_;
        size = 0;
        front = 0;
        rear = 0;
        elementData = new String[capacity];
    }

    public boolean add(String item) {
        if (size == capacity) {
            System.out.println("Error");
            return false;
        }
        elementData[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
        return true;
        
    }

    public String poll() {
        if (size == capacity) {
            System.out.println("Error");
            return "Error";
        }
        String obj = elementData[front];
        front = (front + 1) % capacity;
        size--;
        return obj;
    }

    public String element() {
        if (size == capacity) {
            System.out.println("Error");
            return "Error";
        }
        return elementData[front];
    }


    
}
