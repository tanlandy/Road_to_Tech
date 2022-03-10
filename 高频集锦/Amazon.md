## 面经必看
https://coda.io/d/Offer_dm5fzh3MwOp/OA1_suMAY#OA1_tua4n/r5 全
https://www.1point3acres.com/bbs/thread-855709-1-1.html 

### OA1
1. 移动数据
给了三个list, initinal locations, moveFrom, moveTo, 求从moveFrom 到 moveTo后，return各个物品的位置，从小到大
```Python
def dataMovement(data,movefrom,moveto):
    s=set(data)
    for destination, origination in zip(movefrom,moveto):
        s.remove(movefrom)
        s.add(moveto)
    return sorted(list(s))
```
```Java
// Set里面删除movefrom，添加moveto
// iterate
// for (Integer i : numSet) {
//     System.out.print(i + " ");
// }
// Set<String> s = new HashSet<String>();
// s.add("Geeks");
// s.add("for");

// int n = s.size();
// String arr[] = new String[n];

// int i = 0;
// for (String x : s)
//     arr[i++] = x;
```
2. 图片最大灰度
求array中灰度值最⼤的点的灰度值为多少，两层for循环分别 求出colsGrey\[]和rowsGrey[]，然后遍历一遍求max
遍历一下row和col，然后遍历数组里的每个元素进行运算
```python
def getMaximunGery(grid):
    prfSumRow = [0]*len(grid)
    prfSumCol = [0]*len(groud[0]
    for r in range(len(grid)):
        for c in range(len(grid[0])):
            if grid[r][c]=="1":  prfSumRow[r]+=1; prfSumCol‍‌‌‌‌‍‍‍‍‍‌‍‌‍‌‍‌‍‌‌[c]+=1
            else:                prfSumRow[r]-=1; prfSumCol[c]-=1
            
    return max(prfSumRow)+max(prfSumCol)

```
3. Search word
⼀个searchWord和⼀个resultWord，问最 少给searchWord末尾添加⼏个字符，可以 让resultWord成为它的⼀个 subsequence。举个栗⼦：search给 Word=“armaze”， resultWord=”amazon”，则应该返回2（添 加on）。思路是两个指针p1，p2分别遍历 两个字符串，如果指向的字符相同，则将双 指针同时向后移动⼀位，反之只移动指向 searchWord的指针，直到任意指针到达末 尾。返回resultString的长度与ResultString指针位置的差
```Java
    public static int findiff(String a,String b){
        int m = a.length(),n=b.length();
        int a1 = 0,b1=0;
        
        while(a1 < m&& b1 <n){
            if(a.charAt(a1) == b.charAt(b1)){
                b1+=1;
            };
            a1+=1;
        }
        
        return n -b1;
    }
```
4. Max average stock price
给⼀个⻓度为n的数组表示n个⽉的股价， 给定k值，给连续k⽉并且k个值各不⼀样的区间求和，找到最⼤和。例⼦：｛1，2， 3，4｝，k=2，那总共有(1,2) (2,3) (3,4) 三个⻓度为k的连续区间，最大和是7
```Java
    int getMaxStock(int[] stocks, int k) {     
// sliding window,      
// if meet duplicated number, move start to last pos in map, remove from map, cur     
// if window < k, add new one to cur, map, window++, if window == k, update res     
// if window == k, add new one to cur, map,  start++, remove old from map, cur, update res     
        if (stocks.length == 0 || k == 0) return 0;     
        int res = 0, start = 0, count = 0, cur = 0;     
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // stock => pos          
        for(int i = 0; i < stocks.length; i++) {       
            if (map.containsKey(stocks[i])) {         
                int finalStart = map.get(stocks[i]);         
                while(start <= finalStart) {           
                    cur -= stocks[start];           
                    map.remove(stocks[start]);           
                    count--;           
                    start++;         
                    }       
            // } else {         
            //     if (count < k) {           
            //         cur += stocks[i];           
            //         count++;          
            //         map.put(stocks[i], i);           
            //         if (count == k) {             
            //             res = Math.max(res, cur);           
            //         }         
            //     } 
            } else {           
                    cur += stocks[i] - stocks[start];          
                    map.put(stocks[i], i);           
                    map.remove(stocks[start]);           
                    start++;           
                    res = Math.max(res, cur);         
                }       
            }     
        }     
        return res;   
    }
```

方法二
```Java
    public static int MAS(int[] stock, int k) {

        int left = 0;
        int sum = 0;
        int re1s = 0;

        HashSet<Integer> map = new HashSet<>();

        for (int i = 0; i < stock.length; i++) {
            if (i - left == 3) {
                re1s = Math.max(re1s, sum);
                sum = sum - stock[left];
                map.remove(stock[left]);
                left++;
            }
            if (i - left < 3 && !map.contains(stock[i])) {
                sum = sum + stock[i];
                map.add(stock[i]);
                continue;
            }
            if (i - left < 3 && map.contains(stock[i])) {
                while (map.contains(stock[i])) {
                    map.remove(stock[left]);
                    sum = sum - stock[left];
                    left++;
                }
                map.add(stock[i]);
                sum = sum + stock[i];
                continue;
            }
        }
        return re1s;
    }
```

5. 统计出现次数
6. 一滴血 ng

findMinHealth, ⼀个游戏，输⼊⼀个数列代 表每关要掉的⾎🩸，还有⼀个值表示⼀个只 能⽤⼀次的可以挡最⾼伤害的盾，要求过完 全部关后还要留一滴血，求最开始要多少血
思路：sum（power）-min（max（power），armor）+ 1

7. 一个卡车k个空 ng

一个货车，车上装了一堆货，已知每个货物运费=重量，而且重量unique。给一个K值，找出最低运费能运走的额外K个货物。简单说就是，给一个integer array，每个值都是unique的。然后给一个K, 找出K个最小的不在这个array里面的值，然后加起来就是答案。方法就是一个for loop去check在不在，提早把array变成set就好。
```Java
    public static int getHeaviestPackage(int[] packages){
        int ans = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int i= packages.length - 1; i >= 0; i--) {
            int weight = packages[i];
            while (!stack.isEmpty() && weight < stack.peek()) {
                weight += stack.pop();
            }
            stack.push(weight);
        }
        while(!stack.isEmpty()){
            ans = Math.max(ans,stack.pop());
        }

        return ans;
    }
```
8. 温度差

给一个integer array表示温度，然后告诉你一个测量标准，对第X天的标准值是前X天的温度和(包括第X天)与X天后的温度和(包括第X天)的差的绝对值。也就是说 measure(X) = abs(sum(weather[:X+1]) - sum(weather[X:]))。然后让你找出哪一天的测量标准的值最大。
用prefix sum就可

```Java
    public static int Temprature(int[] nums) {
        int n = nums.length;
        int total = 0;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
            total += nums[i - 1];
        }
        int resl = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int pre = presum[i] + nums[i];
            int after = total - presum[i];
            int max = Math.max(pre, after);
            resl = Math.max(max, resl);
        }
        resl = Math.max(total, resl);
        return resl;
    }


```
9.  parcel 
10.  内存占用

```python
 给你一个数组代表process内存占用，要求删除其中连续K个使得释放的空间最大。比如[1, 3, 5, 7], k=2， 那么就是删除5,7，返回1+3 = 4。 sliding window每次删除最后一个加入一个新的就行很简单。
def max_release(processes, k):
    ans = cur = sum(processes[:k])
    for i, process in enumerate(processes[k:], k):
        cur = cur + process - processes[i-k]
        ans = max(ans, cur)
    return ans

```
11. moviesReward

```python
Amazon Prime Video is a subscription video-on-demand over-the-top streaming and rental service. The team at Prime Video is developing a method to divide movies into groups based on the number of awards they have won. A group can consist of any number of movies, but the difference in the number of awards won by any two movies in the group must not exceed k.
The movies can be grouped together irrespective of their initial order. Determine the minimum number of groups that can be formed such that each movie is in exactly orly group.
Example
The numbers of awards per movie are awards = [1, 5, 4, 6, 8, 9, 21, and the maximum allowed difference is k = 3.

One way to divide the movies into the minimum number of groups is:

The first group can contain [2, 1], The maximum difference between awards of any two movies is 1 which does not exceed K.
The second group can contain [5, 4, 6], The maximum difference between awards of any two movies is 2 which does not exceed k.
The third group can contain [8,9]. The maximum difference between awards of any two movies is 1 which does not exceed k.
The movies can be divided into a minimum of 3 groups.

Function Description
Complete the function minimumGroups in the
editor below.
minimum Groups has the following parameters:
int awards[n]; the number of awards each movie has earned
int k: the maximum difference in awards counts

给你一个数组代表movies获得rewards的数量，要求把他们分组且每组内reward的difference不大于k，求最少分几组。例‍‌‌‌‌‍‍‍‍‍‌‍‌‍‌‍‌‍‌‌如[1,5,6,10], k=2 分三组。先sort然后greedy过一遍分组就行，可以用反证证明正确性。
def arrange_rewards(rewards, k):
    rewards.sort()
    ans = []
    cur, low = [rewards[0]], rewards[0]
    for i, reward in enumerate(rewards[1:], 1):
        if reward - low > k:
            ans.append(cur[:])
            cur = [reward]
            low = reward
        else:
            cur.append(reward)
    else:
        ans.append(cur)
    return ans                    
rewards = [10, 1, 6, 5]
k = 2
arrange_rewards(rewards, k)

```
```Java
public static List<List<Integer>> getAwardsGroups(int[] awards, int k){
        List<List<Integer>> res = new ArrayList<>();
        
        Arrays.sort(awards);
        
        int i = 0;
        while(i < awards.length){
            int elem = awards[i];
            List<Integer> temp = new ArrayList<>();
            temp.add(elem);
            i++;
            while(i < awards.length && (awards[i] - elem)<k){
                temp.add(awards[i]);
                i++;
            }
            res.add(temp);
        }
        return res;
    }


```
13. 九宫格
给⼀串字符，"abcdefgabc"，然后⽤⼿机 九宫格打出。⼿机九宫格可以是任何字⺟组 合，唯⼀要求是每个键⾄少有两个字⺟，最 多三个字⺟。换句话说，这⾥的键位不⼀定 是我们常⻅的2=abc，3=def等等，可以是 1=agq，2=bhj...任何顺序都可以。要求
大概解题思路就是数每个字符出现的次数，然后从多到少排序。前9个字母每次出现只要按1次 （sum+出现次数*1），之后的9个字母每次出现按2次（sum+各自出现次数*2）。。。然后和就是答案。
```Java
    public static int minPress(String s){
        
        Map<Character,Integer> map = new HashMap<>();

        for(char c:s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        
        List<Character> charac =  new ArrayList<>((map.keySet()));
        Collections.sort(charac,(a,b)->(map.get(b)-map.get(a)));
        
        int min = 0;
        int count =0;
        
        for(char c:charac){
            if (count<9){
                min+= map.get(c);
            }
            else if(count>=9 && count<18){
                 min+= map.get(c)*2;
            }
            else{
                min+= map.get(c)*3;
            }
            count+=1;
        }
        return min;
    }
```

14. user system: register, login, logout
用两个hm，一个存username and password一个存是否已经登录即可。
```Java
public static List implementAPI(List logs) {
	HashMap<String,String[]> hm=new HashMap<>();
	ArrayList ans=new ArrayList();
	int n=logs.size();

	for(int i = 0;i < n; i++) {
		String s=logs.get(i);
		String inp[]=s.split(" ");
		String type=inp[0];
		String uname=inp[1];
		String pass= inp.size==3 ? inp[2] : "";
		
		if(type.equals("register"))	{
			if(hm.containsKey("uname")) {
				ans.add("Username already exists");
			}
			else {
				hm.put(uname, new String[]{pass,"0"});
				ans.add("Registered Successfully");
			}
		} else if(type.equals("login")) {
			if(!hm.containsKey(uname)) {
				ans.add("Login Unsuccessful");
			} else {
				String curr[]=hm.get(uname);
				
				if(curr[1].equals("1") || !curr[0].equals(pass)) {
					ans.add("Login Unsuccessful");
				} else {
					hm.put(uname, new String[]{curr[0],"1"});
					ans.add("Logged In Successfully");
				}
			}
		} else if(type.equals("logout")) {
			if(!hm.containsKey(uname)) {
				ans.add("Logout Unsuccessful");
			} else {
				String curr[]=hm.get(uname);
				if(curr[1].equals("0")) {
					ans.add("Logout Unsuccessful");
				} else {
					String curr[]=hm.get(uname);
					if(curr[1].equals("0")) {
						ans.add("Logout Unsuccessful");
					} else {
						hm.put(uname, new String[]{curr[0],"0"});
						ans.add("Logout Successful");
					}
				}
			}
		}
	}
}
```

15. S中的字母可以组成多少个T
给两个字符串s和t，求问使⽤s所有的字⺟最多能够重组出⼏个t。举个栗⼦： s = "mononom", t = "mon"，则答案是2
思路是用两个counter统计s和t的字母出现频率，然后遍历t的所有字母，找到s中出现次数/t中出现次数的最小值（向下取整）
```Java
  public static int Countst(String a,String b){
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        
        for(char c : a1){
            map1.put(c,map1.getOrDefault(c,0)+1);
        }
        System.out.println(map1);
        for(char c : b1){
            map2.put(c,map2.getOrDefault(c,0)+1);
        }
        System.out.println(map2);
        int count = Integer.MAX_VALUE;
        for (char c: b1){
            int a = map1.getOrDefault(c,0);
            int b = map2.getOrDefault(c,0);

            int times = a/b;
            count = Math.min(count,times);
        }
        return count;
}
```

16. The kth Factor of N
You are given two positive integers n and k. A factor of an integer n is defined as an integer i where n % i == 0.

Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.
Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.

```Java
    public int kthFactor(int n, int k) {
        for (int i = 1; i < n/2 + 1; i++) {
            if (n % i == 0) {
                k--;
                if (k == 0) {
                    return i;
                }
            }
        }
        return k == 1 ? n : -1;
    }
```

17. [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/)

You are given an array of positive integers arr. Perform some operations (possibly none) on arr so that it satisfies these conditions:

The value of the first element in arr must be 1.
The absolute difference between any 2 adjacent elements must be less than or equal to 1. In other words, abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed). abs(x) is the absolute value of x.
There are 2 types of operations that you can perform any number of times:

Decrease the value of any element of arr to a smaller positive integer.
Rearrange the elements of arr to be in any order.

Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.
```Java
  public int maximumElementAfterDecrementingAndRearranging(int[] A) {
        Arrays.sort(A);
        int pre = 0;
        for (int a: A)
            pre = Math.min(pre + 1, a);
        return pre;
    }
```

18. merge intervals

```Java
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length == 0){
            return intervals;
        }
        
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        int[] curr = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > curr[1]) {
                res.add(curr);
                curr = intervals[i];
            } else {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            }
        }

        res.add(curr);

        return res.toArray(new int[res.size()][2]);
    }
```

19. sumOfSubarray

```Java
   public static int sumSubarray(int[] A) {
        int res = 0;
        int res1 = 0;
        int mod = (int) 1e9 + 7;

        int n = A.length, left[] = new int[n], right[] = new int[n],
                left1[] = new int[n], right1[] = new int[n];

        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>(),
                s3 = new Stack<>(), s4 = new Stack();

        for (int i = 0; i < n; i++) {
            int count = 1;
            int count1 = 1;
            while (!s1.isEmpty() && s1.peek()[0] > A[i])
                count += s1.pop()[1];
            s1.push(new int[] { A[i], count });
            left[i] = count;

            while (!s3.isEmpty() && s3.peek()[0] < A[i])
                count1 += s3.pop()[1];
            s3.push(new int[] { A[i], count1 });
            left1[i] = count1;

        }

        for (int i = n - 1; i >= 0; i--) {
            int count = 1;
            int count1 = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= A[i])
                count += s2.pop()[1];
            s2.push(new int[] { A[i], count });
            right[i] = count;

            while (!s4.isEmpty() && s4.peek()[0] <= A[i])
                count1 += s4.pop()[1];
            s4.push(new int[] { A[i], count1 });
            right1[i] = count1;

        }

        for (int i = 0; i < n; i++) {
            res1 = res1 + A[i] * left[i] * right[i];
            res = res + A[i] * left1[i] * right1[i];
        }

        return res - res1;
    }

```
20. Array = [1, -1, -1, 1, 1, -1]找最长长度使subarray product = 1
思路：
先遍历一遍，找最左與最右-1的index 及所有-1的數量
若-1的數量為偶數，return array長度
若為基數, return max(len-(left_most + 1), right_most)

21. Maximum Character Frequency Deviation

给你一个字符串，对每一个子串定义一个deviation。deviation=子串里出现频率最高的字母的频率， 减去出现频率最低的字母的频率。
在所有子串里找出deviation的最大值。
举例： s=acccabb。子串accc的deviation=3-1=2。因为c出现的频率最高，是3。a出现的频率最低，是1，所以子串accc的deviation=3-1=2。其余子串‍‌‌‌‌‍‍‍‍‍‌‍‌‍‌‍‌‍‌‌的deviation都小于等于2，所以2是deviation中的最大值，返回2。


### OA2
https://www.1point3acres.com/bbs/thread-847083-1-1.html 
https://www.1point3acres.com/bbs/thread-686711-1-1.html 
https://www.1point3acres.com/bbs/thread-847083-1-1.html 
https://www.1point3acres.com/bbs/thread-859323-3-1.html 
https://www.1point3acres.com/bbs/thread-474434-1-1.html 
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=474434&highlight=amazon 
1. Coding:
(1) Find substrings with k distinct letters. Attention: length of substring has to be k as well.
(2) K closest points to origin.
单选题 https://www.1point3acres.com/bbs/interview/amazon-software-engineer-557398.html 

#### Work simulation
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=474434&highlight=amazon 
https://www.1point3acres.com/bbs/thread-474434-1-1.html 
Q1: Schedule the design reviewmeeting (1)
1 - We can take ourbest guess at an estimate on our own
2 - We should work fora couple of days to gauge our progress, and then complete our estimate fromthere
4 - We should consulta coworker who has more relevant experience on this type of task
3 - We should conductour own investigation utilizing online research materials and internaldocumentation
5 - Let's ask ourmanager how we should go about developing an estimate
Q2: Schedule thedesign review meeting (2)
3 - Ask all parties toidentify a back-up person who could meet during a designated time
3 - See if there is abackup person on the Localization Team that can meet
5 - Set-up videoconferencing to include all POC's regardless of their physical location
1 - Agree to postponethe design review for two weeks when all parties have more availability
2 - Discuss the designreview over email
4 - Agree to schedulethe meeting at Xavier's location an hour away
Q3: Response toRavi (1)
3 - We should miss theconference and increase the timeline to four weeks because we have four weeksof work
4 - Take a day toinvestigate whether adding additional resources would allow us to meet theoriginal timeline, and re-evaluate afterwards
1 - Tell theLocalization team if can't be done in the timeline, so we should go ahead withthe US launch and delay the global launch even though it means adding anadditional week of effort to the four week estimate
5 - Take two weeks tocreate a prototype of the feature to demo at the conference, then take theadditional two weeks needed to fully complete the feature
2 - We can still hitthe two week deadline without any changes by working harder and putting inovertime
Q4: Response toRavi (2)
Begin yourinvestigation using the old error logs, but tell Ravi he will need to run thenew logs if the old logs aren't useful
Q5: Response toAaron and Jacob (1)
Can you tell me moreabout what you're talking about?
Q6: Response toAaron and Jacob (2)
You said we have aninternal database of both digital and physical books. How did we get thephysical book data if the Book Database API doesn't give it to us?
Q7: Response toAaron and Jacob (3)
I recommend you gowith Jacob's solution. We should miss the deadline to build our own service andmeet all the requirements.
Q8: Roadmap
Since you know moreabout the programming language than anyone else, you revise the estimate forporting to Java.
Q9: Response toNadia
What were the internaltest case results?
Q10: Most likelycause of German language issue
Site is using proxyserver location to determine displayed language
Q11: Most likelycause of invalid recommendation issue
Database field storingusername is too short
Q12: Log traceinvestivation success
5 - Increase timealotted for testing in overall lifecycle
5 - Update automatedend-to-end tests to include broader data coverage
3 - Write more unittests to include edge cases
3 - Have team membersperform more manual testing before checking code in
1 - Increase the sizeof QA team
1 - Have more usertesting in beta phase
Q13: Response formeeting the deadline
2 - Work on theproject on your own, putting in extra effort to finish on time
3 - Work on theproject on your own until Priya is available, then continue to work on ittogether
4 - Work on theproject with Ben, being sure to watch his work closely because of his lack ofexperience
5 - Tell your manageryou will not be able to complete the project in the time avaliable
1 - Cut features fromthe product so you will be able to meet the two week deadline
3 - Start working onthe project right away with Ben. Then ask Priya to contribute what she can whenshe is avaliable
Q14: Response forcompleting this work on time
4 - Work with theCustomer Incentives Team to identify the critical features that they need bythe deadline, and focus on those
2 - Push the timelineback another week to ensure there is enough time for all work to be completedaccurately
3 - Ask your wholeteam for help, explaining the urgency that another team is blocked
5 - Ask your managerfor help in determining the best approach to meet the new deadline
1 - Put in extra hoursyourself to make sure everything gets done on time
Q15: Upgrade
4 - We should notperform this upgrade at this point in time. We promised the Retail Website Teamwe would have their new features complete by the proposed deadline. Let'spostpone the upgrade to another time
2 - We should notper‍‌‌‌‌‍‍‍‍‍‌‍‌‍‌‍‌‍‌‌form the upgrade because it will not have a significant impact on the RetailWebsite Team's experience. We should focus on the Retail Website Team'srequests
3 - We should notperform this upgrade at this point in time. Our top focus is meeting our agreedupon commitment with the Retail Website Team, so we should finish that first.We can focus on the upgrade afterwards by pushing our deadlines for some of ourother projects
1 - I think we shouldperform the upgrade. The right thing to do is push back on the Retail WebsiteTeam because it will keep our team from having to do the same work twice
5 - I think we shouldperform the upgrade. As a compromise, we can include the gift recommendationfeature the Retail Website Team wants by the deadline and then complete theupgrade. We can finish the seasonal-based gift recommendations feature afterthe deadline
2 - I think we shouldperform the upgrade. The right thing to do is push back on the Retail WebsiteTeam because it will allow us to more efficiently serve the customer and thecustomer will be helped in the long run.
Q16: New productdesign
2 - A, C, D, G
1 - A, C, D, G, H
4 - A, B, D
3 - A, C, F
5 - A, D, F
3 - F, G
Q17: ？
?
Q18: Problem withProduct.wasPurchasedByUser()
It has performanceissue
Q19: Most effectiveway of improving ShoppingCart()
Change the design ofShoppingCart by removing ShoppingCart user and making shopping cart a propertyof User instead
Q20: Five testswithin ShoppingCartTest()
Fail - Test1
Pass - Test2
Fail - Test3
Pass - Test4
Fail - Test5
Q21: Ask Jacob aquestion
3 - Do any otherprojects depend on fixing this problem?
5 - How many customersis this affecting?
5 - How does thisaffect customers
4 - Are we receivingcomplaints from customers?
2 - How long will ittake to solve this problem?
1 - If I help you withthis problem, will you help me finish my work today?

### VO
https://www.1point3acres.com/bbs/thread-859207-1-1.html 


### BQ
edge case you've think
challenge

a situation kind of pushback: you have an idea and other disgree, how did you solve
-> mysql or mongoDB

project planned for a certain timeline, and you find you couldn't finish
-> communication




https://www.1point3acres.com/bbs/thread-839216-1-1.html  
Tell me an example you helped peers
Tell me an example you do something out of your responsibility
Tell me a time you had conflict with coworkers/teammates.

