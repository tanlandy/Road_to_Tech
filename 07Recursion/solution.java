import java.util.ArrayList;

class solution{
// 0-1 Knapsack
// Given a knapsack which can hold s pounds of items, and a set of items with weight w1, w2, ... wn. 
// Return whether we can pick speciﬁc items so that their total weight s.s = 20; w = [14, 8, 7, 5, 3];
// pick: (s - w[i], w - w[i])
// not pick: (s, w - w[i])
    public boolean knapsack(int s, int[] weight, int index) {
        if (s == 0) {
            return true;
        }

        if (index == weight.length || s < 0) {
            return false;
        }

        return (knapsack(s - weight[i], weight, index + 1) ||
        knapsack(s, weight, index + 1));

    }

/*
Given a set of candidate numbers (C) and a target number (T), ﬁnd all unique combinations in C 
where the candidate numbers sums to T.

All candidate numbers are unique.

The same repeated number may be chosen from C unlimited number of times

pick: (s - w[i], w, index)
not pick: (s, w, index + 1)

*/

    public ArrayList<ArrayList<Integer>> knapsack(int s, int[] weight) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> curr = new ArrayList<>();
        helper(s, weight, curr, res, 0);
        return res;
    }

    public void helper(int s, int[] weight, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> res, int index) {
        if (s == 0) {
            res.add(new ArrayList<Integer>(curr));
            return;
        }

        if (s < 0 || index == weight.length) {
            return;
        }

        //pick:
        curr.add(weight[index]);
        helper(s - weight[index], weight, curr, res, index);

        // not pick:
        curr.remove(curr.size() - 1);
        helper(s, weight, curr, res, index + 1);




    }


}