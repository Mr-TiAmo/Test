package com.li.demo.day01.model;

/**
 * @program: Test
 * @description:
 * @author: li
 * @create: 2021-03-23 09:29
 **/
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
