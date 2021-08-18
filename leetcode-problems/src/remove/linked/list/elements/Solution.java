package remove.linked.list.elements;

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list
 * that has Node.val == val, and return the new head.
 *
 * Example 1:
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 *
 * Example 2:
 * Input: head = [], val = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 */
public class Solution {

    public static void main(String[] args) {
        var head = new ListNode(
                1,
                new ListNode(
                        2,
                        new ListNode(
                                3,
                                new ListNode(
                                        2,
                                        new ListNode(
                                                5,
                                                null
                                        )
                                )
                        )
                )
        );


        var next = removeElements(head, 2);
        if (next == null) {
            System.out.println("[]");
        }
        while (next != null) {
            System.out.println(next.val);
            next = next.next;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        var current = head;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        if (head.val == val) {
            return head.next != null ? head.next : null;
        } else {
            return head;
        }
    }
}
