package Leetcode_Solutions.TwoPointer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode_23 {

     // Definition for singly-linked list.
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            //    return sol1(lists);
            return sol2(lists);


        }


        private ListNode sol2(ListNode[] lists){

            ListNode head = null;
            ListNode itrPtr = null;
            Queue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

            for(ListNode node:lists){
                if (node!=null){
                    minHeap.add(node);
                }
            }

            while(!minHeap.isEmpty()){
                ListNode current = minHeap.poll();
                if(current.next!=null){
                    minHeap.add(current.next);
                }

                if(head == null){
                    head = current;
                    itrPtr = head;
                }else{
                    itrPtr.next = current;
                    itrPtr = itrPtr.next;
                }
            }

            return head;
        }


        private ListNode sol1(ListNode[] lists){
            if(lists == null || lists.length==0){
                return null;
            }

            while(lists.length>1){
                List<ListNode> mergedListNode = new ArrayList<>();
                for(int i=0;i<lists.length;i=i+2){
                    ListNode firstListNode = lists[i];
                    ListNode secondListNode = null;
                    if(i+1 < lists.length) {
                        secondListNode = lists[i+1];
                    }
                    mergedListNode.add(mergedList(firstListNode, secondListNode));
                }
                lists = mergedListNode.stream().toArray(ListNode[]::new);
            }

            return lists[0];
        }


        private ListNode mergedList(ListNode firstListNode, ListNode secondListNode){
            ListNode mergedList = new ListNode();
            ListNode dummy = new ListNode();
            dummy = mergedList;

            while(firstListNode !=null && secondListNode !=null){
                if(firstListNode.val < secondListNode.val){
                    mergedList.next = firstListNode;
                    firstListNode = firstListNode.next;
                }else{
                    mergedList.next = secondListNode;
                    secondListNode = secondListNode.next;
                }
                mergedList = mergedList.next;
            }

            if(firstListNode == null){
                mergedList.next = secondListNode;
            }else{
                mergedList.next = firstListNode;
            }

            return dummy.next;
        }
    }
}
