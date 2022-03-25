# Templetes
## Merge Sort
时间：最坏O(nlogn)
空间：O(n)
思路：Recursion, divide, and conquer
三个函数：
第一个壳子是mergeSort，参数是(数组)，但调用第二个函数
第二个函数是mergeSort，参数是(数组+左+右)，里面排序左边、右边，最后合并
第三个函数是合并，参数是(数组+左+中+右)，里面是双指针依次比较左和右的首位，第三个指针把大的数组从左往后写数据，最后把左右剩下的补上
```Java
public void mergeSort(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
}

public void mergeSort(int[] nums, int l, int r) {
    if (l < r) {
        int mid = l + (r - l) / 2;
        // 左闭右闭，全要选
        // 先排序左边的数组
        mergeSort(nums, l, mid);
        // 再排序后边的数组
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }
}

public void merge(int[] nums, int l, int mid, int r) {
    // find sizes of two subarrays
    int n1 = m - l + 1;
    int n2 = r - m;

    // create temp arrays
    int L[] = new int[n1];
    int R[] = new int[n2];

    // copy data to temp arrays
    for (int i = 0; i < n1; i++) {
        L[i] = arr[l + i];
    }
    for (int j = 0; j < n2; j++) { 
        R[j] = arr[m + 1 + j];
    }

    // merge temp arrays
    int i = 0, j = 0;
    
    // initial index of merged subarray array
    int k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    // copy remaining elements of L[] if any
    while (i < n1) {
        arr[k] = L[j];
        j++;
        k++;
    }

    // copy remaining elements of R[] if any
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

```
Top down Merge Sort排序链表
思路：切段，然后先排左边，再排右边，最后合并
时间O(nlogn)
空间O(logn)
```Java
// merge sort: O(nlogn)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) // 不要忘了边界条件
            return head;
            
        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 这时候prev.next = slow
        prev.next = null; // 把前后切断了

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // 用dummy来存头的位置
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }
```

## Quick Sort
时间复杂度：平均O(nlogn)，最坏O(n^2)
空间：O(1)
思路：Recursion, divide, and conqueer. Pivot
二个函数：
第一个函数是壳子：quicksort，参数是(数组，左，右)，取中pivotPos，然后分别调用自己排左边，排右边；
第二个函数是partition，参数是(数组，左，右)，随机选一个数，小于的数放左边，大于的放右边，从而找到该数字的正确位置
```Java
public void quicksort(int[] nums, int begin, int end) {
    if (begin >= end) {
        return;
    }
    int pivotPos = partition(nums, begin, end);
    // 左闭右闭，但是不用取自己
    quicksort(nums, begin, pivotPos - 1);
    quicksort(nums, pivotPos + 1, end);
}

public int partition(int[] nums, int begin, int end) {
    int pivot = nums[begin];
    while (begin < end) {
        // 找到相同的就换一下
        while (begin < end && nums[end] >= pivot) {
            end--;
        }
        nums[begin] = nums[end];
        while (begin < end && nums[begin] <= pivot) {
            begin++;
        }
        nums[end] = nums[begin];
    }
    nums[begin] = pivot;
    return begin;
}
```
## Bucket Sort 统排
给个数组，元素有范围且可以重复
利用一个count array，然后按照从小到大的顺序输出到数组里


## Comparator
```Java
// Sort a [][]: O(nlogn)
// intervals = [[0,30],[3,1],[4,7]] to be sorted:
   // method 1:
   Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0][]));
   // methos 2:
   Arrays.sort(
       intervals,
       new Comparator<int[]>() {
           public int compare(int[] a, int[] b) {
               return a[0] - b[0];
           }
       }
   );



// Merge interval
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval obj0, Interval obj1) {
                return obj0.start - obj1.start;
            }
        });

        List<Interval> res = new ArrayList<>();
        Interval prev = null;
        for (Interval inter : intervals) {
            if ( prev==null || inter.start>prev.end ) {
                res.add(inter);
                prev = inter;
            } else if (inter.end>prev.end) {
                // Modify the element already in list
                prev.end = inter.end;
            }
        }
        return res;
    }

// Sort string
    Arrays.sort(
        str,
        new Comparator<String>() {
            public int compare(String str1, String str2) {
                return (str2 + str1).compareTo(str1 + str2);
            }
        }
    );

```

# 典型题目

[179. Largest Number](https://leetcode.com/problems/largest-number/)

Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

Input: nums = [3,30,34,5,9]
Output: "9534330"

思路：Sorting via Custom Comparator
First, we convert each integer to a string. Then, we sort the array of strings.

edge case: when the array consists of only zeroes, so if the most significant number is 00, we can simply return 00. Otherwise, we build a string out of the sorted array and return it.

时间：O(nlogn) 排序
空间：O(n) allocate space for final return string

```Java

    public String largestNumber(int[] nums) {
        // 数字转String[]
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }
        // String排序
        Arrays.sort(asStrs, 
                   new Comparator<String>() {
                       public int compare(String a, String b) {
                           return (b + a).compareTo(a + b);
                       }
                   }
                   );
        // edge case
        if (asStrs[0].equals("0")) {
            return "0";
        }
        // String[]转String
        String largetNum = new String();
        for (String s : asStrs) {
            largetNum += s;
        }
        return largetNum;
    }

```

## Selection sort

[4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

[215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

思路1: 用heap，size总是k，这样堆顶就是kth largest
时间 O(nlogk) 每次放数字需要O(logk)，一共n次
空间 O(K)
```Java
    public int findKthLargest(int[] nums, int k) {
        // min-heap 注意定义方式
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // 堆顶总是kth largest
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }
```

思路2: Quickselect
时间 O(n)
空间 O(1)
```Java
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int j = partition(nums, l, r);
            if (j < k) {
                l = j + 1;
            } else if (j > k) {
                r = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    
    private int partition(int[] nums, int l, int r) {
        int mid = nums[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] <= mid) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, r);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    } 

```


[973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)


