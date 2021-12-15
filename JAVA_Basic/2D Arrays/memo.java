// As we have learned previously, an array is a group of data consisting of the same type. This means that we can have an array of primitive data types (such as integers):

// we can have 2D arrays which are not rectangular in shape. These are called jagged arrays:

// The only downside is that once initialized, no new rows or columns can be added or removed without copying the data to a newly initialized 2D array. This is because the length of arrays in Java are immutable (unable to be changed after creation).

//Declaration, initialization, and assignment
int[][] intArray2;
intArray2 = new int[3][5];

// There are three situations in which we can use initializer lists for 2D arrays:

// In the case where the variable has not yet been declared, we can provide an abbreviated form since Java will infer the data type of the values in the initializer lists:

double[][] doubleValues = {{1.5, 2.6, 3.7}, {7.5, 6.4, 5.3}, {9.8,  8.7, 7.6}, {3.6, 5.7, 7.8}};

// If the variable has already been declared, you can initialize it by creating a new 2D array object with the initializer list values:
String[][] stringValues;
stringValues = new String[][] {{"working", "with"}, {"2D", "arrays"}, {"is", "fun"}};

// The previous method also applies to assigning a new 2D array to an existing 2D array stored in a variable.

// Print a 2D array:
System.out.println(Arrays.deepToString(intArray2));
System.out.println(Arrays.toString(intArray2[0]) + "\n");

// Remember that the syntax for enhanced for loops looks like so: for( datatype elementName : arrayName){
char[][] charData = {{'a', 'b', 'c', 'd', 'e', 'f'},{'g', 'h', 'i', 'j', 'k', 'l'}};

for(char[] charRow : charData) {
    for(char c : charRow) {
        System.out.print(c + " ");
    }
    System.out.println();
}
// a b c d e f 
// g h i j k l

