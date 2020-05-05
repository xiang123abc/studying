package cn.ecut.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;

import cn.ecut.demo.TreeNode;

public class 算法 {
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(5);
		node.next.next.next = new ListNode(3);
		node.next.next.next.next = new ListNode(4);
		sortList3(node);
	}
	
		/**
		 * 删除链表中重复的结点
		 * @see https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
		 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
		 * @param pHead
		 *借助辅助头结点，可避免单独讨论头结点的情况。设置两个结点 pre 和 cur，当 cur 和 cur.next 值相等，
			cur 一直向前走，直到不等退出循环，这时候 cur 指的值还是重复值，调整 cur 和 pre 的指针再次判断
		 * @return
		 ListNode node = new ListNode(1);
		 node.next = new ListNode(2);
		 node.next.next = new ListNode(3);
		 node.next.next.next = new ListNode(3);
		 node.next.next.next.next = new ListNode(4);
		 deleteDuplication(node);
		 */
		public static ListNode deleteDuplication(ListNode pHead){
	        if(pHead == null || pHead.next == null){
	            return pHead;
	        }
	        //  	pre  curr
	        //head  ---- ---- ---- ----
	        //自己构建辅助头结点  可避免单独讨论头结点的情况
	        ListNode head = new ListNode(Integer.MIN_VALUE);
	        head.next = pHead;
	        ListNode pre = head;
	        ListNode curr = head.next;
	        while(curr!=null) {
	        	// 相同结点一直前进
	        	if(curr.next!=null&&curr.next.val==curr.val) {
	        		while(curr.next!=null&&curr.next.val==curr.val) {
	        			curr = curr.next;
	        		}
	        		// 退出循环时，cur 指向重复值，也需要删除，而 cur.next 指向第一个不重复的值
	               // cur 继续前进
	        		curr = curr.next;
	        		// pre 连接新结点   重点 ！！！！！！！！！！！
	        		pre.next = curr;
	        	}else {
	        		pre = curr;
	        		curr = curr.next;
	        	}
	        }
	        return head.next;
	    }
		
	 /**
	  * 深度优先搜索
	  */
	 int ans = 0;
	 public void dfs(TreeNode node, int L, int R) {
	        if (node != null) {
	            if (L <= node.val && node.val <= R)
	                ans += node.val;
	            if (L < node.val)
	                dfs(node.left, L, R);
	            if (node.val < R)
	                dfs(node.right, L, R);
	        }
	    }
	/**
	 * 面试题55 - II. 平衡二叉树        判断是否是平衡二叉树
	 * @see https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
	 * @param root
	 * @return
	 */
	 public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
	}
	/**
	 * 计算树 root 的深度
	 * @param root
	 * @return
	 */

	private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
	/**
	 * 后续遍历
	 * @param root
	 * @return
	 */
	public static List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<TreeNode> list = new LinkedList();
		LinkedList<Integer> list2 = new LinkedList();
		list.add(root);
		if( root== null)
			return list2;
		while(!list.isEmpty()) {
			TreeNode node = list.pollLast();
			list2.addFirst(node.val);
			if(node.left!=null)
				list.add(node.left);
			if(node.right!=null)
				list.add(node.right);
		}
		return list2;
	}
	/**
	 * 华为机考1
	 */
	private static void huawei( String s) {
		Map<String,String> map = new HashMap();
		map.put("a", "A");
		map.put("e", "E");
		map.put("i", "I");
		map.put("o", "O");
		map.put("u", "U");
		//String [] str = {"A","E","I","O","U"};
		//List list = Arrays.asList(str);
		//map.containsValue(value)
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<s.length();i++) {
			if(map.containsKey(s.substring(i,i+1))) {
				 sb.append(map.get(s.substring(i, i+1)));
			}else {
				//if(s.charAt(i)>='A' && s.charAt(i)<='Z' && !list.contains(s.substring(i, i+1)) ) {
				if(s.charAt(i)>='A' && s.charAt(i)<='Z' && !map.containsValue(s.substring(i, i+1)) ) {
				char temp = (char) (s.charAt(i)+32);
					String temps = temp+"";
					sb.append(temps);
				}else {
					sb.append(s.substring(i,i+1));
				}
			}
		}
		System.out.println(sb.toString());
	}
	/**
	 * 表现良好的最长时间段
	 * @see  https://leetcode-cn.com/problems/longest-well-performing-interval/
	 * 
	 * hours = [9,9,6,0,6,6,9]
	 * hours = [1,1,-1,-1,-1,-1,0]
	 * 求听数组中子串连续和大于0的最大子串长度
	 * 
	 */
	public static  int longestWPI(int[] hours) {
        int n = 0;
        if(hours.length==0)
        	return n;
        int [] t = new int [hours.length];
        int  res =0;
        for(int i = 0;i<hours.length;i++) {
        	t[i] = hours[i]>8 ?1:-1;
        }
       //求听t中 和大于0的最上子串
        for(int i = 0;i<hours.length;i++) {
        	int count = 0;
        	for(int j = i;j<hours.length;j++) {
        		count +=t[j];
        		if(count > 0)
                    res = Math.max(res, j - i + 1);
        	}
        	
        }
        return res;
    }
	/**
	 * 反转一个单链表
	 * @see https://leetcode-cn.com/problems/reverse-linked-list/solution/
	 * @param head
	 * @return
	 * 
	 */
	public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while(curr!=null){
           ListNode temp =   curr.next;
           curr.next = pre;
           pre = curr;
           curr = temp;
        }
        // 
        return pre;
    }
	public static ListNode reverseList2(ListNode head) {
		ListNode thead = new ListNode(Integer.MIN_VALUE);
		thead.next = head;
		ListNode pre = thead;
		ListNode curr  = thead.next;
		while(curr!=null) {
			ListNode temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}
		//注意不是返回  thead.next ！！！！！！！
        return pre;
    }
	/**
	 * 层序遍历
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList();
		ArrayList<List<Integer>> res = new ArrayList();
		if(root==null) 
			return res;
		queue.add(root);
		//queue.element();
		 int level = 0;
		while(!queue.isEmpty()) {
			res.add(new ArrayList());
			for(int i = 0 ;i<queue.size();i++) {
				TreeNode node = queue.remove();
				res.get(level).add(node.val);
				if(node.left!=null) {
					queue.add(node.left);
				}
				if(node.right!=null) {
					queue.add(node.right);
				}
			}
			level++;
		}
		return res;
    }
	/**
	 * 最大子串长度
	 * @param A
	 * @param n
	 * @param B
	 * @param m
	 * @return
	 */
	 public static int findLCS2(String A, int n, String B, int m) {
		  int[] []  dp = new int [n][m];
		  //初始化
		  for(int i = 1;i<=n;i++) {
			  for(int j = 1;j<=m;j++) {
				  dp[i][j] = 0;
			  }
		  }
		  for(int i = 1;i<=n;i++) {
			  for(int j = 1;j<=m;j++) {
				  if(A.charAt(i-1)==B.charAt(j-1)) {
					  dp[i][j] = dp[i-1][j-1] +1;
				  }else {
					  dp[i][j] =  dp[i][j-1]>dp[i-1][j]?dp[i][j-1]:dp[i-1][j];
				  }
			  }
		  }
		  return dp[n][m];
	  }
	 
	/**
	 * 148. 排序链表
	 * @see https://leetcode-cn.com/problems/sort-list/submissions/
	 * @param head
	 * @return
	 * Collections 中   Timsort用二分插入算法  O( n log n)
	 * 分析：  用 Arraylist  存储， 再用 Collections.sort 方法排序，再创建node 放值
	 */
	 public static ListNode sortList3(ListNode head) {
		 ListNode res = null;
		 ArrayList<ListNode> tree = new ArrayList();
		   while(head!=null){
			   tree.add(head);
	            head = head.next;
	        }
		   Collections.sort(tree, new Comparator<ListNode>() {
			   public int compare(ListNode o1, ListNode o2) {
				   return o1.val - o2.val;
			   };
		   });
		   ListNode aa = new ListNode(Integer.MIN_VALUE);
		   res = aa; 
		   Iterator<ListNode> it =  tree.iterator();
		   while(it.hasNext()){
			   ListNode node = it.next();
			   ListNode temp  = new ListNode(node.val);
			   res.next = temp;
			   res = res.next;
		   }
		   return aa.next;
	    }
	 public static ListNode sortList(ListNode head) {
		 ListNode res = null;
		 TreeSet<ListNode> treeset = new TreeSet(
				   new Comparator<ListNode>() {
					@Override
					public int compare(ListNode o1, ListNode o2) {
						return o1.val-o2.val;
					}
				   });
		   while(head!=null){
			   treeset.add(head);
	            head = head.next;
	        }
		   ListNode aa = null;
		   Iterator<ListNode> it =  treeset.iterator();
		   boolean flag = true;
		   while(it.hasNext()){
			   ListNode node = it.next();
			   if(flag) { 
			       res = node;
			       aa = res; 
			       flag = false;
			   }
			   else {
				   ListNode temp  = new ListNode(node.val);
				   res.next = temp;
				   res = res.next;
			   }
		   }
		return aa;
	    }
	/**
	 * @see https://leetcode-cn.com/problems/reorder-list/
	 *  143. 重排链表
	    ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		reorderList(node);
	 * @param head
	 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
	 */
	public static void reorderList(ListNode head) {
		  if (head == null) {
		        return;
		 }
	      List<ListNode> list = new ArrayList();
	      List<ListNode> list2 = new ArrayList();
	      int index = 0;
	      while(head!=null){
	          list.add(head);
	          head = head.next;
	      }
	      int i = 0,j=list.size()-1;
	      while(i<j) {
	    	  list.get(i).next = list.get(j);
	    	  i++;
	    	  //偶数个节点的情况，会提前相遇
	          if (i == j) {
	              break;
	          }
	    	  list.get(j).next = list.get(i);
	    	  j--;
	      }
	      list.get(i).next = null;
	     System.out.println();
	    }
}
