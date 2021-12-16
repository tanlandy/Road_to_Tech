# Scope in Java

In Java, scope defines where a certain variable or method is accessible in a program. Variables can be defined as having one of three types of scope:

1) Class level scope (instance variables): any variable declared within a class is accessible by all methods in that class. Depending on its access modifier (ie. public or private), it can sometimes be accessed outside the class.
```
public class Car {
    public String color;
    private int speed;
 
    public Car(String color, int speed) {
        // Variables color and speed accessible here
    }
 
    public void drive(boolean fourWheel) {
        // Variables color and speed accessible here
    }
}
 
public class BuyCar {
    public static void main(String[]args) {
        Car carObject = new Car("blue", 70);
        // Can access the public variable, color, in this class
        String carColor = carObject.color;
        // Can’t access the private variable, speed, in this class
        // int carSpeed = carObject.speed -- This results in an error, can’t access speed here
    }
}
```

