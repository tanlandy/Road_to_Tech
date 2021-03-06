# HashMap
- The hash map is one of the implementations of a map data structure to store (key, value) pairs.
- Mapping relationship (dictionaries)
- Stores a relationship, both key and value are important
- 技巧：心里想着pair，形成一个映射的图形，从而能更好应用函数
- 用来计数<element, count>


## When to use
1. when time-consuming to find something in Array
2. need to calculate the index by the object
3. support quick insertion and search.

1. need more information rather than only the key.
2. Another frequent scenario is to aggregate all the information by key. 

## Design the key
- [Design the key](https://leetcode.com/explore/learn/card/hash-table/185/hash_table_design_the_key/1128/)
1. When the order of each element in the string/array doesn't matter, you can use the sorted string/array as the key.
2. If you only care about the offset of each value, usually the offset from the first value, you can use the offset as the key.
3. In a tree, you might want to directly use the TreeNode as key sometimes. But in most cases, the serialization of the subtree might be a better idea.
4. In a matrix, you might want to use the row index or the column index as key.
5. In a Sudoku, you can combine the row index and the column index to identify which block this element belongs to.
6. Sometimes, in a matrix, you might want to aggregate the values in the same diagonal line. 

## Initiate
`Map<Integer, Integer> map = new HashMap<>(); // <Key, Value>`
## Methods
```Java
map.putIfAbsent(0, 0); // insert a new (key, value) pair
map.put(1, 1) // insert a new pair
map.put(1, 2) // update the pair
map.put(nums[i], map.getOrDefault(nums[i], 0) + 1); // 加进来
map.get(1) // return 2, get the value of specific key
map.getOrDefault(c, 0) //
map.remove(0) // remove a key
map.containsKey(0) // return false, check if a key is in the hash map
map.containsValue(2) // O(n)
map.size() // get size
map.clear() // clear the hash map
map.isEmpty() 
map.values(); return all values

// iterate 要什么有什么，就只用这个来遍历
for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    System.out.print("Key: " + entry.getKey() + " Value: " + entry.getValue() + ") ");
}

keySet(); // return Set<key> 输出含有key的数组，同时key都是唯一的
```

# HashSet
- The hash set is one of the implementations of a set data structure to store no repeated values.
- Only stores values(key) // 存HashMap里面的key
- 制作sliding window
```Java
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
```Java
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

## Template
1. Using hash map to find duplicates.
```Java
/*
 * 
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

2. Using HashMap to store and update `<String, List<String>> `pair

```Java
public List<List<String>> groupStrings(String[] strings) {
    Map<String, List<String>> map = new HashMap<>();

    for(String s : strings) {
        String key = getKey(s);
        List<String> list = map.getOrDefault(key, new ArrayList<>());
        list.add(s);
        map.put(key, list);
    }
    return new ArrayList<>(map.values());
}
```

