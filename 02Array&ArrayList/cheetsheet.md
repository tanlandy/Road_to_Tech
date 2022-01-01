# Array
## Initiate
```
String[] friendArray = new String[10];
friendArray[0] = "Hello";
int[] nums = new int[]{1,4,2,3};

String[] friendArray2 = {"Hello","Wow","Earth"};
friendArray2[0] = "World"; //overwrite
```
## Methods
```
friendArray[0]; // Access
friendArray.length // Get length
// Shift and insert
// Say we want to insert the element at index 2.
for (int i = 4; i >= 2; i--) {
    intArray[i + 1] = intArray[i];
}
intArray[2] = 30;
// Delete: Shift and edit length
for (int i = 1; i < length; i++) {
    int_array[i - 1] = int_array[i];
}
length--;

```
# Arrays
```
int[] nums = new int[]{1,2,3,4,5,6,7,8};
Arrays.sort(nums); // Sort
Arrays.copyOfRange(nums, 2, 5); // return [3,4,5]; creates a copy of elements nums[2,5)
Arrays.fill(nums, -1); // fill nums with -1
```

# ArrayList
## Initiate
`ArrayList<Integer> myNumbers = new ArrayList<>();`
## Methods
```
// get; O(1)
// set; O(1)
// add; O(n)
// remove; O(n)
// find; O(n)
myNumbers.size() // get length
Collections.sort(myNumbers); // Sort
myNumbers.toArray(new String[myNumbers.size()]); // ArrayList to Array
ArrayList(map.values()); // 
```

