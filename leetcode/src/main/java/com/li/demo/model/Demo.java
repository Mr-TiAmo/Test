package com.li.demo.model;

import com.li.demo.model.ListNode;

import java.util.*;

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
            map.put(c, i);
        }

        return length;
    }

    /**
     * nums1 = [1,3], nums2 = [2] 合并数组 = [1,2,3] ，中位数 2
     * nums1 = [1,2], nums2 = [3,4]  合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * nums1 = [0,0], nums2 = [0,0]  0.00000
     * nums1 = [], nums2 = [1]   1.00000
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int length = nums1.length + nums2.length;
        double n = 0;
        int[] arrays = new int[length];
        int n1 = 0, n2 = 0;
        for (int i = 0; i < length; i++) {
            if (n1 < nums1.length && n2 < nums2.length) {
                if (nums1[n1] <= nums2[n2]) {
                    arrays[i] = nums1[n1];
                    n1++;
                } else {
                    arrays[i] = nums2[n2];
                    n2++;
                }
            } else {
                if (n2 < nums2.length) {
                    arrays[i] = nums2[n2];
                    n2++;
                } else {
                    arrays[i] = nums1[n1];
                    n1++;
                }
            }
        }
        if (length % 2 == 0) {
            //偶数 0,1,2,3, 0 1 2 3 4 5
            n = Double.valueOf(arrays[length / 2] + arrays[length / 2 - 1]) / 2;
        } else {
            //奇数
            n = Double.valueOf(arrays[length / 2]);
        }

        return n;
    }

    /**
     * nums1 = [1,3], nums2 = [2] 合并数组 = [1,2,3] ，中位数 2
     * nums1 = [1,2], nums2 = [3,4]  合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * nums1 = [0,0], nums2 = [0,0]  0.00000
     * nums1 = [], nums2 = [1]   1.00000
     * 中位数定义：当 m + n 为奇数是， 中位数为 第 (m+n) /2 个元素，当 m + n 为偶数是， 中位数为 第 (m+n) /2 个元素 和 第 (m+n) /2 +1个元素的平均值
     * 即寻找 两个有序数组中第 k小的数， k为 第 (m+n) /2 个元素 或第 (m+n) /2 +1个元素
     * 要找到第 k小的数，只需要比较 A[K/2 -1]和 B[K/2 -1],对于 A[k/2-1]A[k/2−1] 和 B[k/2-1]B[k/2−1] 中的较小值，
     * 最多只会有 (k/2−1)+(k/2−1)≤k−2 个元素比它小，那么它就不能是第 k 小的数了
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        double median = 0D;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            median = getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
        }
        return median;
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    public String longestPalindrome(String s) {
        String str = "";

        return str;
    }

    /**
     * 二叉树的根节点 root ，返回它节点值的 前序遍历
     * 根左右
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> num = new ArrayList<>();
        preorder(root, num);
        return num;

    }
    /**
     * 递归
     * @param root
     * @param num
     */
    public static void preorder(TreeNode root, List<Integer> num) {
        if (root != null) {
            num.add(root.getValue());
            preorder(root.getLeft(), num);
            preorder(root.getRight(), num);
        }
    }

    /**
     * 二叉树的根节点 root ，返回它节点值的 中序遍历
     * 左根右
     * @param root
     * @return
     */
    public static List<Integer> midOrderTraversal(TreeNode root) {
        List<Integer> num = new ArrayList<>();
        midOrder(root, num);
        return num;
    }
    public static void midOrder(TreeNode root, List<Integer> num) {
        if(root != null) {
            midOrder(root.getLeft(),num);
            num.add(root.getValue());
            midOrder(root.getRight(),num);
        }
    }

    /**
     * 二叉树的根节点 root ，返回它节点值的 层次遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> num = new ArrayList<>();
        levelOrder(root, num);
        return num;
    }
    public static void levelOrder(TreeNode root, List<Integer> num) {
        Queue queue = new LinkedList<TreeNode>();
        if(root != null) {
            queue.offer(root);
            num.add(root.getValue());
            levelOrder(root.getLeft(),num);
            queue.poll();
            levelOrder(root.getRight(),num);
        }
        queue.poll();
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
