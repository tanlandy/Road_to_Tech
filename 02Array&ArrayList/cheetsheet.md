## Create an array
```
String[] friendArray = new String[10];
String[] friendArray2 = {"Hello","Wow","Earth"};
```
## Access items in an array
```
friendArray[0] = "Hello";
System.out.println(friendArray[0]);
friendArray2[0] = "World"; //overwrite
```
## Get length
```
length = friendArray.length;
int highestIndex = length - 1;
// to iterate over all items in an array:
for (int i = 0; i < friendArray.length; i++) {
    System.out.println(friendArray[i]);
}
```

## Insert
```
// Say we want to insert the element at index 2.
// First, we will have to create space for the new element.
for (int i = 4; i >= 2; i--)
{
    // Shift each element one position to the right.
    intArray[i + 1] = intArray[i];
}

// Now that we have created space for the new element,
// we can insert it at the required index.
intArray[2] = 30;
```

check the content of a for loop to see if there's the out of bounds error;

## Delete
### Delete the first element
```
// Starting at index 1, we shift each element one position
// to the left.
for (int i = 1; i < length; i++) {
    // Shift each element one position to the left
    int_array[i - 1] = int_array[i];
}

// Note that it's important to reduce the length of the array by 1.
// Otherwise, we'll lose consistency of the size. This length
// variable is the only thing controlling where new elements might
// get added.
length--;
```

### Delete the last element 
```
// Deletion from the end is as simple as reducing the length
// of the array by 1.
length--;
```

## Search