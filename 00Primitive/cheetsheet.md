# Print
```Java
// To print a double with 6 decimal
double n = 3.1313131;
System.out.printf("%f%n", n);
System.out.printf("%7.6f%n", n); // 总共7位数，6位小数

System.out.println(max + " " + min); // 打印出来12 15
```

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
String s2 = s1.substring(1,3) // get substring; O(n)// s2 = "el", [1,3)
s1.length(); // get length
s1.charAt(0); // get char at index 0
s = s1.trim(); // trim all extra spaces in s1
String[] words = s1.split(" ") // 按空格分开，存到array中

// String -> Char[]; Char[] -> String
char[] charS = s1.toCharArray(); // charS = {'H', 'e', 'l',...,'l', 'd'};  converts the given string into a sequence of characters
String s2 = String.valueOf(charS); // s2 = "Hello world"; returns the string representation

// 将String排序
String str = new String("Hefasf");
char[] charS = str.toCharArray();
Arrays.sort(charS);
String newStr = String.valueOf(charS);

// 将String[]排序
String[] strs = new String["Hello", "sadfa", "adsfe", "asefa"];
for (String s : strs) {
    char[] charS = s.toCharArray(); // string -> char[]
    Arrays.sort(charS); // sort char[]
    String newS = String.valueOf(charS); // char[] -> string
}



// "04:12:34PM" to "16:12:34" 
String s = "04:12:34PM";
int first = Integer.parseInt(s.substring(0,2)) + 12;
String n = first + s.substring(2,8);



```

# StringBuilder

## Initiate
```Java
StringBuilder word = new StringBuilder();
StringBuilder res = new StringBuilder(strStack.pop());

public static String timeConversion(String s) {

    if(s.charAt(s.length()-2) == 'A'){
        if((s.substring(0,2)).equals("12"))
            return("00"+s.substring(2,8));

    }else{
        if(!(s.substring(0,2)).equals("12"))
            return(Integer.parseInt(s.substring(0,2))+12+s.substring(2,8));

    }
    return(s.substring(0,8));

}

```

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
int left = Integer.MIN_VALUE;
int right = Integer.MAX_VALUE;

long sum; // 64 bits, primitive Java types
Integer sum; // 64 bits, non-primitive Java types, can be null


```

# Character
## Initiate
`char c = s.charAt(index);`
## Method
```Java
Character.isDigit(c); // check if c is digit
Character.isLetterOrDigit(c); // Check if its space, return false
Character.toLowerCase(c); // c to lower case, return 'h'

// char[] 存储'a' - 'z'
char[] letters = new char[26];
index = 0;
for (char i = 'a'; i < 'z'; i++) {
    char[index] = i;
    index++;
}

```

# ASCII
```Java
// 'a'in Java is 97 -> nums['a'] -> nums[97]
`s.charAt(i) - 'a'` // shift the ascii value so that **a-z** have values 0 - 25

`s.charAt(i) - 'A'` // shift the ascii value so that **A-Z** have values 0 - 25


```


# Math
```Java
int a = Math.ceil(4.5) // a = 5
double b = (double) 5 / 2 // b = 2.5

```