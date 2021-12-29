# String
## Initiate
`String s1 = "Hello World";`
## Methods
```
return s1.equals("HELLO"); // Compare function
s1[5] = 'x'; // Error: string in Java is immutable
s1 += '!'; // concatenate
s1.indexOf('o'); // find first 'o'; O(n)
s1.lastIndexOf('o'); // find last 'o'; O(n)
String s2 = s1.substring(1,5) // get substring; O(n)
s1.length(); // get length
s1.charAt(0); // get char at index 0
s = s1.trim(; // trim all extra spaces in s1
String[] words = s1.split(" ") // 按空格分开
```

# StringBuilder
## Initiate
`StringBuilder word = new StringBuilder();`
