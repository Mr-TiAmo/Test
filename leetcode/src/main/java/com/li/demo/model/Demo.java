package com.li.demo.model;

import com.li.demo.model.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @program: Test
 * @description:
 * @author: li
 * @create: 2021-03-23 09:27
 **/
public class Demo {

    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表
     * l1 243  l2 564  -->708
     * 342 + 465 = 807
     */

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while (null != l1 || null != l2) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (null == head) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            // 进位
            carry = sum > 9 ? 1 : 0;
            // 移动指针
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }
        //最后 节点相加 需要进位
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    /**
     * 给定一个字符串，请你找出其中不含重复字符的最长子串的长度
     * a b c a b c d  --> abc 3
     * p w w k e w
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            //加入set
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    if (set.size() > length) {
                        length = set.size();
                    }
                    set.clear();
                    break;
                } else {
                    set.add(s.charAt(j));
                }
            }
            //内层遍历完重置set
            if (set.size() > 0) {
                if (set.size() > length) {
                    length = set.size();
                }
                set.clear();
            }

        }
        return length;
    }

    /**
     * 官方解答
     * 给定一个字符串，请你找出其中不含重复字符的最长子串的长度
     * a b c a b c d  --> abc 3
     * a b b b b b a
     * p w w k e w
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        HashSet<Character> set = new HashSet<>();
        int n = s.length();
        int length = 0, right = -1;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (right + 1 < n && !set.contains(s.charAt(right + 1))) {
                set.add(s.charAt(right + 1));
                right++;
            }
            length = Math.max(length, right - i + 1);
        }
        return length;
    }

    /**
     * 给定一个字符串，请你找出其中不含重复字符的最长子串的长度
     * a b c a b c d  --> abc 3
     * a b b b b b a
     * p w w k e w
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int length = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                //左指针右移，获取之前出现当前字符的下标
                //Math.max 规避哈希表查询到在滑动窗口左边的重复字符的,锁定住滑动窗口的左边界
                // wkwsvwekw   k...k 中间有重复字符串，防止start 回到下标1
                start = Math.max(map.get(c) + 1, start);
            }
            length = Math.max(length, i - start + 1);
            map.put(c,i);
        }

        return length;
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        System.out.println(list.contains("c"));
        int i = 0;
        int n = i++;
        int x = ++i;
        System.out.println(n);
        System.out.println(x);
        System.out.println(5 / 2);
        System.out.println(5 % 2);
    }
    /**
     *  /运算  > 效率
     */
//    public static void main(String[] args) {
//        long s1 = System.currentTimeMillis();
//        System.out.println(s1);
//        int a = 0;
//        int b = 0;
//        for (Long i =0L; i<1000000000L; i++) {
//            a = a + (15 / 10);
//        }
//        System.out.println("a=" + a);
//        long s2 = System.currentTimeMillis();
//        System.out.println(s2);
//        System.out.println(s2 -s1);
//        for (long i =0L; i<1000000000L; i++) {
//            b = b + (15 > 9 ? 1 : 0);
//        }
//        System.out.println("b=" + b);
//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis() -s2);
//    }
}
