## String
1. [Reconstruct Original Digits from English](https://leetcode.com/problems/reconstruct-original-digits-from-english/)


## Templates
```Java
// 遍历复制一个String
String strOri = "Hello world";
StringBuilder sb = new StringBuilder();
for (int i = 0; i < strOri.length(); i++) {
    char c = strOri.charAt(i);
    sb.append(c);
}
String strNew = sb.toString();






```