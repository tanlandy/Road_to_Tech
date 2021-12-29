# HashMap
In computing, a hash table (hash map) is a data structure used to implement an associative array, a structure that can map keys to values.
mapping relationship (dictionaries)

## When to use
1. when time-consuming to find something in Array
2. need to calculate the index by the object

## Initiate
`Map<Object, Object> map = new HashMap<>(); // <Key, Value>`
## Methods
```
put(key, value); // O(1)
get(key); // O(1)
containsKey(key); // O(1)
remove(key); // O(1)
keySet(); // return Set<key> 输出含有key的数组，同时key都是唯一的
entrySet(); // return Set<Map.Entry<key, value>> 输出
size();
isEmpty();
containsValue(value); // 不常用
```

# HashSet
## Initiate
` Set<Integer> numSet = new HashSet<>();`
## Methods
```
max = Collections.max(numSet) // get max
min = Collections.min(numSet) // get min
size = numSet.size() // get size
numSet.add(5) // add 5
numSet.remove(5) // remove 5
```


## Memo
### Collision
1. Avoid collision: Expand the space
2. Resolve collision:
- Open hashing 开散列：重复的变链表，在同一个index后
- Closed hashing 闭散列：重复的往后放