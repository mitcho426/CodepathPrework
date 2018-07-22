package InterviewBitCheckPoints;
import java.util.*;

public class Solution {

    public ArrayList<ArrayList<Integer>> prettyPrint(int A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (A <= 0) {
            return result;
        }
        int n = 2 * A - 1;
        int[][] matrix = new int[n][n];

        int startRow = 0;
        int startCol = 0;
        int endRow = n-1;
        int endCol = n-1;
        int number = A;

        int i, j;

        while(number >= 1) {


            for(j = startCol; j <= endCol; j++) matrix[startRow][j] = number;
            for(i = startRow; i <= endRow; i++) matrix[i][endCol] = number;
            for(j = endCol; j >= startCol; j--) matrix[endRow][j] = number;
            for(i = endRow; i >= startRow; i--) matrix[i][startCol] = number;

            startCol++;
            startRow++;
            endCol--;
            endRow--;
            number--;
        }

        for(i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(j = 0; j < n; j++) list.add(matrix[i][j]);
            result.add(list);
        }


        return result;
    }

    public int kthsmallest(final List<Integer> A, int B) {
        if(B > A.size()) return -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);


        for(int k = 0; k < B; k++) {
            pq.add(A.get(k));
        }

        for(int i = B; i < A.size(); i++) {
            if(A.get(i) < pq.peek()) {
                pq.poll();
                pq.add(A.get(i));
            }
        }
        return pq.poll();
    }

    public ListNode subtract(ListNode A) {
        if(A == null) return null;

        ListNode slw = A;
        ListNode fst = A;
        ListNode halfPointer = null;
        while(slw != null && fst != null && fst.next != null) {
            halfPointer = slw;
            slw = slw.next;
            fst = fst.next.next;
        }
        //If there is one element
        if(slw == fst) return A;

        //check if even or odd
        if(fst != null) {
            halfPointer = slw;
            slw = slw.next;
            halfPointer.next = null;
        }

        ListNode h2 = reverse(slw);
        ListNode repairNode = h2;
        ListNode h1 = A;

        while(h2 != null && h1 != null) {
            h1.val = (h2.val-h1.val);
            h2 = h2.next;
            h1 = h1.next;
        }

        halfPointer.next = reverse(repairNode);
        return A;
    }

    public ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode next = node;
        while(node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }


    public int longestConsecutive(final List<Integer> A) {
        if(A == null || A.size() == 0) return 0;
        Set<Integer> set = new HashSet<>();
        int res = 0;
        int count = 1;
        for(Integer number : A) set.add(number);

        for(Integer number : A) {
            if(!set.contains(number)) continue;
            int left = number-1;
            while(set.contains(left)) {
                set.remove(left);
                left--;
                count++;
            }
            int right = number+1;
            while(set.contains(right)) {
                set.remove(right);
                right++;
                count++;
            }
            if(count > res) res = count;
            count = 1;
        }
        return res;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
