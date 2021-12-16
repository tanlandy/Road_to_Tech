### Casting
Type casting is the process of converting a value of one primitive data type to another primitive data type. Data types are classified as “lower” if they hold less data and “higher” if they hold more data. 
Lowest to highest primitive data types: Byte-->Short-->Int-->Long-->Float-->Double

int intNum = (int) doubleNum indicates that doubleNum is being converted to an int.

A String that holds an integer value can be converted to an int using the Integer class method .parseInt():
```
String s = "12";
int intNum = Integer.parseInt(s);
```
An int value can be converted to a String using the String class method .valueOf():
```
int intNum = 12;
String s = String.valueOf(intNum);
```

## Dictionaries
In Java, a Dictionary is an abstract class that stores key-value pairs. Given a key, you can retrieve the value associated with that key. Dictionaries are instantiated with one data type for the keys and one for the values, both of which can be any data type. Every key is associated with at most one value.

Dictionary is the abstract parent class of Hashtable, a data structure that stores key-value pairs.

### Hashtable
Hashtable is a data structure that stores key-value pairs in a hashtable, very similar to HashMap. One difference is that Hashtable does not allow any null key or value. In a Hashtable, each key is hashed (using a hash function) to get a hash code, which is then used as the index at which the corresponding value is stored in the hashtable.

We will create an instance of Hashtable, which extends the Dictionary key-value behavior. Here, the keys are of data type String, and values are of data type Integer:

```
public class DictionaryCreation {
    public static void main(String[]args) {
        Dictionary<Integer,String> dict = new Hashtable<>();
        // Add key-value pairs to dict
        dict.put(1, "hello");
        dict.put(5, "goodbye");
        // Access the values using the keys
        System.out.println("Value at key 1: " + dict.get(1));
        System.out.println("Value at key 5: " + dict.get(5));
    }
}
```

### interface
An interface is a collection of abstract methods which lay out the required behavior of any class that implements it. Recently, a Map interface was created; it maps keys to values in a similar manner to Dictionary. This more robust interface was created for data structures such as HashMap and Hashtable to implement. It can only be used with a class that implements its interface.
```
Map<String, Integer> m1 = new Hashtable<>();
Map<String, Integer> m2 = new HashMap<>();
```
