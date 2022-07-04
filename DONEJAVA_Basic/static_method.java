Math.min(2,5) ;
// To use static methods in Math class, we don't need to create an
// object of this class

// static methods are associated with an entire class, not a specific object of that class
//a static method, you’ll use the name of the class to call the method, not an object of the class.
//a static method can’t interact with a non-static instance variable.
//Both static methods and non-static methods can interact with static variables.


// Useful methods:
Math.min(a ,b) //return smaller one
Math.abs(a) // return abs of a, no matter it's an int or double
Math.pow(a, b) // return a ** b (a * a * a...)
Math.sqrt(a)
Math.random() // return a double value that 0 <= value < 1.0
Integer.MAX_VALUE
Integer.MIN_VALUE

// to have double random number from 0 to 9:
Math.random() * 10
int(Math.random() * 10) // retunn an int one

// to have random int number 1-10:
int(Math.rendom() * 10 + 1)


//from 1 to 10 included
0 + Math.randoma() * 10 ) + 1

// for every random range
(Math.random() * (maxValue - minValue + 1)) + minValue.