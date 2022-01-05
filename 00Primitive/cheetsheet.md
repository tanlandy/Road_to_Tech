# String
## Initiate
`String s1 = "Hello World";`
## Methods
```Java
return s1.equals("HELLO"); // Compare function
s1[5] = 'x'; // Error: string in Java is immutable
s1 += '!'; // concatenate
s1.indexOf('o'); // find first 'o'; O(n)
s1.lastIndexOf('o'); // find last 'o'; O(n)
String s2 = s1.substring(1,5) // get substring; O(n)
s1.length(); // get length
s1.charAt(0); // get char at index 0
s = s1.trim(); // trim all extra spaces in s1
String[] words = s1.split(" ") // 按空格分开，存到array中
char[] charS = s1.toCharArray(); charS = {'H', 'e', 'l',...,'l', 'd'};  converts the given string into a sequence of characters
String s2 = String.valueOf(charS); // s2 = "Hello world"; returns the string representation

// 将String排序
String str = new String("Hefasf");
char[] charS = str.toCharArray();
String newStr = String.valueOf(charS);


// 将String[]排序
String[] strs = new String["Hello", "sadfa", "adsfe", "asefa"];
for (String s : strs) {
    char[] charS = s.toCharArray(); // string -> char[]
    Arrays.sort(charS); // sort char[]
    String newS = String.valueOf(charS); // char[] -> string
}




```

# StringBuilder
## Initiate
`StringBuilder word = new StringBuilder();`
## Method
```Java
word.append("4");
word.append(" ");
word.toString(); // return String


```

# Integer
## Methods
```Java
Integer.toString(5); // return "5"

```

# ASCII
'a'in Java is 97 -> nums['a'] -> nums[97]
`s.charAt(i) - 'a'` shift the ascii value so that **a-z** have values 0 - 25

`s.charAt(i) - 'A'` shift the ascii value so that **A-Z** have values 0 - 25