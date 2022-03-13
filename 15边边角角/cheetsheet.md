
3. [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

Given a roman numeral, convert it to an integer.

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

s = "CMXCIV:
Output: 994
Explanation:
The symbols barely look sorted at all here—from left-to-right we have 100, 1000, 10, 100, 1, 5. Do not panic though, we just need to look for each occurrence of a smaller symbols preceding a bigger symbol. The first, third, and fifth symbols are all smaller than their next symbol. Therefore they are all going to be subtracted from their next.

The first two symbols are CM. This is M - C = 1000 - 100 = 900
The second two symbols are XC. This is C - X = 100 - 10 = 90.
The final two symbols are IV. This is V - I = 5 - 1 = 4.
Like we did above, we add these together. (M - C) + (C - X) + (V - I) = 900 + 90 + 4 = 994.

```Java
// clarification: input always valid?
// larger num always comes earlier
    static Map<Character, Integer> map = new HashMap<>();
    
    static {
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
    }

    public int romanToInteger(String s) {
        // go from left to right
        // if s.charAt(i) < i+1 -> sum -= num
        // else sum += num
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            char c2 = s.charAt(i + 1);
            if (map.get(c) < map.get(c2)) {
                sum -= map.get(c);
            } else {
                sum += map.get(c);
            }
        }
        char cLast = s.charAt(s.length() - 1);
        sum += map.get(cLast);
        return sum;
    }
```

4. [12. Integer to Roman](https://leetcode.com/problems/integer-to-roman/)

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

Given an integer, convert it to a roman numeral.

Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

```Java
// largest symble comes first
// go from left to right, update from largest to next largest
    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
    
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
      
    }

```

