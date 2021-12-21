### find the digit of a number
```
//10 again and again:
for (int i = 0; i < nums.length; i++) {
    int temp = nums[i];
    int digit = 1;
    while (temp >= 10) {
        temp /= 10;
        digit++;
    }
    if (digit % 2 == 0) res++;
    
}
```