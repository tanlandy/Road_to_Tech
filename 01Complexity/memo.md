## Memorize:
- Θ(1) < Θ(log N) < Θ(N) < Θ(N log N) < Θ(N2) < Θ(2N) < Θ(N!)
- Sort: O(nlogn) --> Merge Sort
- Sort: O(nlogn) / O(n^2) --> Quick Sort average / worst cases
- Sort: O(n^2) --> Selection Sort
- Search: O(logn) --> Binary search
- Tree: ~O(logn)
- Hashmap: O(1)

we define how efficient a program is by its runtime.
We need a general way to define a program’s runtime across these variable factors. We do this with Asymptotic Notation.
In asymptotic notation, we define the size of the input as N. I may be looking through a collection of 10 elements, or 100 elements, but we only need to know how many steps are performed relative to the input so N is used in place of a specific number. If there is a second input, we may define the size of that input as M.

5N^2 + 2N +4 --> N^2
We use asymptotic notation to describe the runtime of a program. The three types of asymptotic notation are big Theta, big Omega, and big O.
We use big Theta (Θ) to describe the runtime if the runtime of the program is the same in every case.
The different common runtimes from fastest to slowest are: Θ(1), Θ(log N), Θ(N), Θ(N log N), Θ(N2), Θ(2N), Θ(N!).
We use big Omega (Ω) to describe the best-case running time of a program.
We use big O (O) to describe the worst-case running time of a program.
We typically describe a program’s running time in terms of big O.
When finding the runtime of a program with multiple steps, you can divide the program into different sections and add the runtimes of the various sections. You can then take the slowest runtime and use that runtime to describe the entire program.
When analyzing the runtime of a program, we care about which part of the program is the slowest.

As we know, a simple for loop that goes through every element in an array of size n has a linear runtime of O(n). However, this function takes O(1) space since no new variables are being created and therefore no more space must be allocated.


