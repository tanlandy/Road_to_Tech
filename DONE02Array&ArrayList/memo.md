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

**Whenever you're trying to solve an array problem in-place, always consider the possibility of iterating backwards instead of forwards through the array. It can completely change the problem, and make it a lot easier.**