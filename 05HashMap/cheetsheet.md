# HashMap
- The hash map is one of the implementations of a map data structure to store (key, value) pairs.
- Mapping relationship (dictionaries)
- Stores a relationship, both key and value are important
- 技巧：心里想着pair，形成一个映射的图形，从而能更好应用函数
- 用来计数<element, count>
`map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);`


## When to use
1. when time-consuming to find something in Array
2. need to calculate the index by the object
3. support quick insertion and search.

1. need more information rather than only the key.
2. Another frequent scenario is to aggregate all the information by key. 

## Initiate
`Map<Integer, Integer> map = new HashMap<>(); // <Key, Value>`
## Methods
```
map.putIfAbsent(0, 0); // insert a new (key, value) pair
map.put(1, 1) // insert a new pair
map.put(1, 2) // update the pair
map.get(1) // return 2, get the value of specific key
map.getOrDefault(c, 0) //
map.remove(0) // remove a key
map.containsKey(0) // return false, check if a key is in the hash map
map.size() // get size
map.clear() // clear the hash map
map.isEmpty() 

// iterate
for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    System.out.print("(" + entry.getKey() + "," + entry.getValue() + ") ");
}

keySet(); // return Set<key> 输出含有key的数组，同时key都是唯一的
```

# HashSet
- The hash set is one of the implementations of a set data structure to store no repeated values.
- Only stores values(key) // 存HashMap里面的key
- 制作sliding window
```
set.add(nums[i]);
if (set.size() > k) set.remove(nums[i - k]);
```
## When to use
- Primary task is search/contains
- Sometimes used to deduplicate
- support quick insertion and search.

## Initiate
` Set<Integer> numSet = new HashSet<>();`
## Methods
```
max = Collections.max(numSet) // get max
min = Collections.min(numSet) // get min
size = numSet.size() // get size
numSet.add(5) // add 5
numSet.add(3)
numSet.remove(5) // remove 5
numSet.contains(3) //
numSet.size() 
numSet.clear() // clear the hash set
numSet.isEmpty()


// iterate
for (Integer i : numSet) {
    System.out.print(i + " ");
}
```


## Memo
### Collision
1. Avoid collision: Expand the space
2. Resolve collision:
- Open hashing 开散列：重复的变链表，在同一个index后
- Closed hashing 闭散列：重复的往后放

### hashCode & equals
当equals override时，hashCode也需要override
override: 子class的方法可以继承自父class，而具体内容有所不同
overload：方法名字相同，但有多个不同的参数组合方式

### equals & ==
Only use == when comparing primitive types:
1. long, integer, short, byte, char
2. double, float
3. boolean
Always use euqals for reference types, even for primitive wrapper classes
Long, Integer, etc

### Template
1. need more information rather than only the key.


```
/*
 * Template for using hash map to find duplicates.
 * Replace ReturnType with the actual type of your return value.
 */
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
    // Replace Type and InfoType with actual type of your key and value
    Map<Type, InfoType> hashmap = new HashMap<>();
    for (Type key : keys) {
        if (hashmap.containsKey(key)) {
            if (hashmap.get(key) satisfies the requirement) {
                return needed_information;
            }
        }
        // Value can be any information you needed (e.g. index)
        hashmap.put(key, value);    
    }
    return needed_information;
}
```
2. Another frequent scenario is to aggregate all the information by key. 

···
/*
 * Template for using hash map to find duplicates.
 * Replace ReturnType with the actual type of your return value.
 */
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
    // Replace Type and InfoType with actual type of your key and value
    Map<Type, InfoType> hashmap = new HashMap<>();
    for (Type key : keys) {
        if (hashmap.containsKey(key)) {
            hashmap.put(key, updated_information);
        }
        // Value can be any information you needed (e.g. index)
        hashmap.put(key, value);    
    }
    return needed_information;
}
···