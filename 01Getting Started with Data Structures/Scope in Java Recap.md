# Scope in Java

In Java, scope defines where a certain variable or method is accessible in a program. Variables can be defined as having one of three types of scope:

1. Class level scope (instance variables): any variable declared within a class is accessible by all methods in that class. Depending on its access modifier (ie. public or private), it can sometimes be accessed outside the class.

2. Method level scope (local variables): any variable declared within a method, arguments included, is NOT accessible outside that method.

3. Block scope (loop variables): any variable declared in a for loop condition is not accessible after the loop, unless you defined it beforehand.

## Access Modifiers
In Java, there are four access modifiers that restrict the accessibility of the method or variable to which the modifier is applied. They are only used within classes, not within methods. public and private are the most relevant modifiers to our work, but we will briefly discuss all of them.
1. private: The most restrictive modifier. It limits access to methods and variables to the class in which they are declared. private is chosen when there is no need to use certain methods or variables outside the class.
2. default: Allows access only from within the current package. If there is no specified access modifier, the method or variable will take on this one.
3. protected: Allows access to a method or variable only from within the current package, unless it is accessed through a child class outside of the package.
4. public: The least restrictive modifier. It allows access to a class, method or variable not only from within the class in which it is declared, but outside as well. This is the modifier we will most commonly use, but to understand the scenarios in which to use the others, check out the Oracle documentation.

## Static
static is a keyword that can be added after the access modifier of a method or variable. It indicates that the declared entity is the same across all instances of that class and that it can be accessed even before an object of that class is created. static methods and variables are initialized only once upon execution and are shared by all instances of the class. You may have noticed that the .main() method of a class is always declared as static; this is because this method is the starting point of the program. The compiler needs to be able to call the .main() method before the creation of an instance of that class, so it’s declared static. An example using a static variable can be found in the Instance Variables section of this article.

## Method Overriding and Method Overloading
### Method Overriding
Method overriding is a topic that comes up a few times in this path. It is a feature in Java that allows a subclass to have a method with the same name and parameters as one declared in its parent class. This is handy because it allows a subclass to implement a specific behavior for that method. The version of the method used is determined by the object that is used to call it. 


### Mathod Overloading
Method overloading is similar to overriding in that it involves methods with the same name. However with overloading, a single Java class can have multiple methods with the same name if they have different parameter lists. Overloaded methods are distinguished by their number and type of parameters.

### Constructor Overloading
Constructor overloading is a type of method overloading in which there are multiple constructors in a class. This gives the option to instantiate an object with different sets of arguments. A keyword this() is used in constructor overloading

## Instance Variables
Instance variables, also called instance fields, are data associated with a class object. They make up the state that each instance will possess. 

### Usage of "this"
1 ."this" is used within any class method or constructor to reference the current object. You can reference any instance variable of a class, from within the class, using this. It cannot be used in a static context. Using this easily clarifies which variables are being referenced.

2. Call a current class method with this

3. Call a current class constructor using this() (constructor overloading)
```
public class Car {
    public String color;
    public int speed;
    public static String defaultColor = "black";
    public static int defaultSpeed = 30;
   
    public Car() {
        this(defaultColor, defaultSpeed); // Third usage
    }
 
    public Car(String color, int speed) {
        this.color = color;
        this.speed = speed; // First usage
        this.race(speed); // Second usage: Use keyword this to call class method, race()
    }
 
    public void race(int speed) {
        System.out.println("Raced at " + speed + " mph");
    }
}
```