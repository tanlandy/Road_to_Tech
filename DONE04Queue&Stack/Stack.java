class stack {
    private int size;
    private int capacity;
    private String[] elementData;

    public stack(int capacity_) {
        size = 0;
        capacity = capacity_;
        elementData = new String[capacity];
    }

    public String push(String item) {
        if (size == capacity) {
            resize();
        }
        elementData[size++] = item;
        return item;
    }

    public String pop() {
        if (size == 0) {
            System.out.println("Error");
            return "Error";
        }
        String obj = elementData[--size];
        return obj;
    }

    public String peek() {
        if (size == 0) {
            System.out.println("Error");
            return "Error";
        }
        return elementData[size - 1];
    }

    public void resize() {
        size *= 2;
    }

}