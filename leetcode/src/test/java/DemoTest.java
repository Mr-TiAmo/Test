import com.li.demo.model.Demo;
import com.li.demo.model.ListNode;
import com.li.demo.model.TreeNode;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @program: Test
 * @description: 测试
 * @author: li
 * @create: 2021-03-29 09:25
 **/
public class DemoTest {
    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表
     */
    @Test
    public void test1() {
        ListNode node = new ListNode(2);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(5);
        // l1 243
        ListNode l1 = node;
        node.next = node1;
        node1.next = node2;
        // l2 564
        ListNode l2 = node4;
        node4.next = node3;
        node3.next = node1;
        ListNode listNode = Demo.addTwoNumbers(l1, l2);
        ListNode temp = listNode;
        while (null != temp.next) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    @Test
    public void test2() {
        IntStream.range(0, 10).forEach(i -> System.out.println(i++));
        System.out.println("------------------------------------");
        IntStream.range(0, 10).forEach(i -> System.out.println(++i));
        System.out.println("------------------------------------");
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
        }
        System.out.println("------------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void test3() {
//        String str = " ";
//        String str = "aab";
//        String str = "dvdf";
//        String str = "a";
//        String str = "abcabcd";
        String str = "wkwsvwekw";
        System.out.println(str.substring(0, 0));
        System.out.println(str.contains("w"));
        System.out.println(str.contains("wk"));
        System.out.println(str.contains("wsk"));
        System.out.println(Demo.lengthOfLongestSubstring2(str));
        System.out.println(Demo.lengthOfLongestSubstring1(str));
        System.out.println(Demo.lengthOfLongestSubstring(str));
    }

    @Test
    public void test6() {
        System.out.println(2 / 2);
        System.out.println(2 % 2);
        System.out.println(5 / 2);
        System.out.println(5 % 2);
        System.out.println(0 % 2);
        System.out.println(Double.valueOf(5D / 2));
    }

    @Test
    public void test7() {
//        int[] nums1 = new int[]{1, 3};
//        int[] nums2 = new int[]{2};
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
//        int[] nums1 = new int[]{0,0};
//        int[] nums2 = new int[]{0,0};
//        int[] nums1 = new int[]{3};
//        int[] nums2 = new int[]{-2,-1};
        System.out.println(Demo.findMedianSortedArrays(nums1,nums2));
        System.out.println(Demo.findMedianSortedArrays1(nums1,nums2));
    }

    /**
     *      1
     *    2    3
     *      5 6  7
     *          8  9
     */
    @Test
    public void test8() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);
        treeNode7.setLeft(treeNode8);
        treeNode7.setRight(treeNode9);
        System.out.println(Demo.preorderTraversal(treeNode1));
        System.out.println(Demo.midOrderTraversal(treeNode1));
//commit 未push
        System.out.println(Demo.levelOrderTraversal(treeNode1));
    }
}
