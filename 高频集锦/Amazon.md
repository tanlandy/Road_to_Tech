## 面经必看

### OA1
https://www.1point3acres.com/bbs/thread-844232-1-1.html 
https://coda.io/d/Offer_dm5fzh3MwOp/OA1_suMAY#OA1_tua4n/r5 
https://www.1point3acres.com/bbs/thread-851767-1-1.html 全
https://www.1point3acres.com/bbs/thread-844232-1-1.html 
https://www.1point3acres.com/bbs/thread-855709-1-1.html 
https://www.1point3acres.com/bbs/thread-859427-1-1.html 
https://www.1point3acres.com/bbs/thread-859785-1-1.html 
https://www.1point3acres.com/bbs/thread-859207-1-1.html 
https://www.1point3acres.com/bbs/thread-858506-1-1.html 
https://www.1point3acres.com/bbs/thread-857570-1-1.html 


1. 移动数据

```Python
def dataMovement(data,movefrom,moveto):
    s=set(data)
    for destination, origination in zip(movefrom,moveto):
        s.remove(movefrom)
        s.add(moveto)
    return sorted(list(s))
```
2. 图片最大灰度

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
3. Search word + 1
4. stock max
5. 统计出现次数
6. 一滴血 ng
7. 一个卡车k个空 ng
8. 温度差
```
给一个integer array表示温度，然后告诉你一个测量标准，对第X天的标准值是前X天的温度和(包括第X天)与X天后的温度和(包括第X天)的差的绝对值。也就是说 measure(X) = abs(sum(weather[:X+1]) - sum(weather[X:]))。然后让你找出哪一天的测量标准的值最大。
一个货车，车上装了一堆货，已知每个货物运费=重量，而且重量unique。给一个K值，找出最低运费能运走的额外K个货物。简单说就是，给一个integer array，每个值都是unique的。然后给一个K, 找出K个最小的不在这个array里面的值，然后加起来就是答案。
第一题用了个prefix sum就做出来了。第二题就是一个for loop去check在不在，提早把array变成set就好。给定2个小时，10分钟做完交卷。
```
9.  parcel 
10. 内存占用

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
12. 九宫格

大概解题思路就是数每个字符出现的次数，然后从多到少排序。前9个字母每次出现只要按1次 （sum+出现次数*1），之后的9个字母每次出现按2次（sum+各自出现次数*2）。。。然后和就是答案。


### OA2
https://www.1point3acres.com/bbs/thread-859323-3-1.html 
https://www.1point3acres.com/bbs/thread-474434-1-1.html 
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=474434&highlight=amazon 
1. Coding:
(1) Find substrings with k distinct letters. Attention: length of substring has to be k as well.
(2) K closest points to origin.
单选题 https://www.1point3acres.com/bbs/interview/amazon-software-engineer-557398.html 

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

### Work simulation
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