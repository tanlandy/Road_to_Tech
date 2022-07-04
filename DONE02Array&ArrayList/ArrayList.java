public class ArrayList {
    // initialize with capacity
    // always check bounds
    private int capacity;
    private int size;
    private int[] data;

    public ArrayList(int capacity_) {
        capacity = capacity_;
        size = 0;
        data = new int[capacity];
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Error");
            // throw Exception
        }
        return data[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            System.out.println("Error");
            // throw Exception
        }
        data[index] = value;
    }

    public void add(int index, int value) {
        if (index < 0 || index >= size) {
            System.out.println("Error");
            // throw Exception
        }
        size++;
        for (int i = 0; i >= index + 1; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
    }

    public void resize(){
        capacity *= 2;
        int[] new_data = new int[capacity];
        for (int i = 0; i < size; i++) {
            new_data[i] = data[i];
        }
        data = new_data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Error");
            // throw Exception
        }
        size--;
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
    }

}
