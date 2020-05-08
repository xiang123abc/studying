package cn.ecut.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import cn.ecut.demo.TreeNode;

public class 算法 {
	/*
	 ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(3);
		node.next.next.next.next = new ListNode(4);
		ListNode t = deleteDuplication2(node);
		System.out.println();
		
		TreeNode node = new TreeNode(1);
		 node.left = new TreeNode(2);
		 node.right = new TreeNode(3);
		 node.left.left = new TreeNode(4);
		 node.right.right = new TreeNode(5);
		 node.right.left = new TreeNode(6);
	 */
	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		 node.left = new TreeNode(2);
		 node.right = new TreeNode(3);
		 node.left.left = new TreeNode(4);
		 node.right.right = new TreeNode(5);
		 node.right.left = new TreeNode(6);
		 inorderTraversal4(node);
	}
	public int strStr3(String haystack, String needle) {
        int hLen = haystack.length(), nLen = needle.length();
        for (int i = 0; i <= hLen - nLen; i++){ // 等于，适用于两者长度相等的情况下（包含都为 ”“）
            int j = 0;
            for (;j < nLen; j++){
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == nLen){
                return i;
            }
        }
        return -1;
    }
	/** 
	 * KMP
	 *  实现 strStr()  indexOf  
	 * @see https://leetcode-cn.com/problems/implement-strstr/submissions/
	 * @param haystack
	 * @param needle
	 * 输入: haystack = "hello", needle = "ll"
	        输出: 2
	 * @return
	 */
	// "",""  "abcd","abcd"
	public int strStr(String haystack, String needle) {
		if(haystack.equals("") && needle.equals("")) 
			return 0;
		int hLen = haystack.length(),nLen = needle.length();
        for (int i = 0; i <= hLen - nLen; i++){
            int j = 0;
            for( ;j<needle.length();j++) {
            	String n = needle.substring(j,j+1);
            	if(!haystack.substring(i+j,i+j+1).equals(n)) {
            		break;
            	}
            }
            if(j==needle.length())
            	return i;
        }
       return -1;
    }
	/**
	 * 20. 有效的括号
	 * @see https://leetcode-cn.com/problems/valid-parentheses/
	 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * "()[]{}"  true   "([)]"  false
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
        if(s == null) return true;
        //key ( [ value ) ]
        Map<String,String> map = new HashMap();
        map.put("(",")");
        map.put("[","]");
        map.put("{","}");
        // value  ( [ key ) ]
        Map<String,String> map2 = new HashMap();
        map2.put(")","(");
        map2.put("]","[");
        map2.put("}","{");
        Stack<String> stack = new Stack();
        for(int i =0;i<s.length();i++){
            String t = s.substring(i,i+1);
            if(!stack.isEmpty() && map.containsValue(t)){
                if(!stack.peek().equals(map2.get(t)))
                    return false;
                else
                    stack.pop();
            }else{
                stack.add(t);  
            }
        }
        return stack.isEmpty();
    }
	int post_idx;
	  int[] postorder;
	  int[] inorder;
	  HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

	  public TreeNode helper(int in_left, int in_right) {
	    // if there is no elements to construct subtrees
	    if (in_left > in_right)
	      return null;

	    // pick up post_idx element as a root
	    int root_val = postorder[post_idx];
	    TreeNode root = new TreeNode(root_val);

	    // root splits inorder list
	    // into left and right subtrees
	    int index = idx_map.get(root_val);

	    // recursion 
	    post_idx--;
	    // build right subtree
	    root.right = helper(index + 1, in_right);
	    // build left subtree
	    root.left = helper(in_left, index - 1);
	    return root;
	  }
	  /**
	   * 从中序与后序遍历序列构造二叉树
	   * 中序遍历 inorder = [9,3,15,20,7]
		  后序遍历 postorder = [9,15,7,20,3]
	   * @see https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
	   * @param inorder
	   * @param postorder
	   * @return
	   */
	  public TreeNode buildTree(int[] inorder, int[] postorder) {
	    this.postorder = postorder;
	    this.inorder = inorder;
	    // start from the last postorder element
	    post_idx = postorder.length - 1;
	    // build a hashmap value -> its index
	    int idx = 0;
	   // 创建 hashmap 存储中序序列：value -> its index 。
	    for (Integer val : inorder)
	      idx_map.put(val, idx++);
	    return helper(0, inorder.length - 1);
	  }
	  public TreeNode helper2(int in_left, int in_right) {
		  if (in_left > in_right)
		      return null;
		   int  val = postorder[post_idx];
		  TreeNode node = new TreeNode(val);
		  post_idx--;
		  int index  = idx_map.get(val);// 
		  node.left = helper2(in_left,index-1);
		  node.right = helper2(index+1,in_right);
		  return node;
	  }
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		 ListNode res = new ListNode(Integer.MIN_VALUE);
		 int sum = 0;
		 StringBuffer sb = new StringBuffer();
		 StringBuffer sb2 = new StringBuffer();

		 //遍历 相加
		 while(l1!=null) {
			 sb.append(l1.val);
			 l1 = l1.next;
		 }
		 while(l2!=null) {
			 sb2.append(l2.val);
			 l2 = l2.next;
		 }
		 int a = Integer.valueOf(sb.toString());
		 int b = Integer.valueOf(sb2.toString());
		 sum = a+b;
		 //得到9，1，2数字， 插入一个空链表返回
		 String s = String.valueOf(sum);
		 ListNode pre = res;
		 for(int i = s.length()-1;i>=0;i--) {
			 ListNode t = new ListNode(Integer.valueOf(s.substring(i,i+1)));
			 pre.next = t;
		     pre = t;
		 }
		 return res.next;
	 }
	
	//找到中间节点
	public ListNode getMid(ListNode head){      
		ListNode fast=head;        
		ListNode slow=head;             
		while(fast!=null){            
			fast=fast.next;             
			if(fast==null){                
				break;             
				}            
			slow=slow.next;      
			fast=fast.next;        
			}        
		return slow;
    }        //逆置从中间节点之后的链表
    public ListNode reverse(ListNode head){     
    	ListNode result=null;       
    	ListNode cur=head;     
    	while(cur!=null){          
    		ListNode next=cur.next;            
    		cur.next=result;          
    		result=cur;                       
    		cur=next;        
    	}        
    	return result;
    }      //然后判断相等
    /**
	 * 判断一个链表是否是回文链表
	 * @param head
	 * @return
	 */
    public boolean checkPalindromeList(ListNode head){      
    	ListNode mid=getMid(head);     
    	ListNode node=reverse(mid);      
    	ListNode p1=head;      
    	ListNode p2=node;   //中间的后半段    
    	while(p2!=null&&p1!=null){   
    		if(p1.val!=p2.val){       
    			return false;        
    			}         
    		p1=p1.next;  
    		p2=p2.next;    
    		}     
    	return true;
    }
    
	 public boolean isValidBST(TreeNode root) {
	        if(root == null) 
	        	return true;
	        if(root.left!=null)
	        	if(root.left.val>root.val) {
	        		return false;
	        	}
	        
	        if(root.right!=null)
	    	     if(root.right.val>root.val)
	    	    	 return false;
	        isValidBST(root.left);
	        isValidBST(root.right);
			return true;
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
		public static ListNode deleteDuplication2(ListNode pHead){
			if(pHead==null) return null;
			List<ListNode> list  =  new ArrayList();
			List<Integer> numList = new ArrayList();
			//key number, value 次数
			Map<Integer,Integer> map = new HashMap();
			while(pHead!=null) {
				list.add(pHead);
				map.putIfAbsent(pHead.val, 0);
				map.put(pHead.val, map.get(pHead.val)+1);
				numList.add(pHead.val);
				pHead = pHead.next;
			}
			//找到 需要删除的下标
			List<Integer> remove = new ArrayList();
			map.forEach((key,value)->{
				if(value>1)
					remove.add(key);
			});
			ListNode res = new ListNode(Integer.MIN_VALUE);
			ListNode pre = res;
			for(ListNode t: list) {
				if(remove.contains(t.val)) {
					continue;
				}
				pre.next = t;
				pre = t;
			}
			//1，2，3，3，4，5，5 防止 末尾重复  ，最后pre在4位置
			pre.next = null;
//			while(curr!=null) {
//				if(curr.next!=null) {
//					while(remove.contains(curr.next.val)) {
//						curr = curr.next;
//					}
//					 curr = curr.next;
//					  pre = curr;
//				}else {
//					//curr  =curr.next;
//					//pre = curr;
//				}
//			}
			return res.next;
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
	 * @see https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
	 * 144. 二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> res = new LinkedList();
        if(root == null) return res;
        stack.add(root);
        while(!stack.isEmpty()){
           TreeNode node =  stack.pop();// not poll
           res.add(node.val);
           if(node.right!=null)
            stack.add(node.right);
           if(node.left!=null)
            stack.add(node.left);
        }
        return res;
        // LinkedList<Integer> res  = new LinkedList();
        //  if(root == null) return res;
        // LinkedList<TreeNode> list  = new LinkedList();
        // list.add(root);
        // while(!list.isEmpty()){
        //     TreeNode node = list.pollLast();
        //     res.add(node.val);
        //     if(node.right!=null)
        //         list.add(node.right);
        //      if(node.left!=null)
        //         list.add(node.left);
        // }
        // return res;
    }
	 public List < Integer > inorderTraversal(TreeNode root) {
	        List < Integer > res = new ArrayList < > ();
	        helper(root, res);
	        return res;
	    }
	public void helper(TreeNode root, List < Integer > res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }
	public static List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }
	/**
	 * 94. 二叉树的中序遍历
	 * @see https://leetcode-cn.com/problems/binary-tree-inorder-traversal/comments/
	 * @param root
	 * @return
	 */
	 public List<Integer> inorderTraversal5(TreeNode root) {
	        List<Integer> list = new ArrayList<>();
	        Stack<TreeNode> stack = new Stack<>();
	        while(root!=null||!stack.isEmpty()){
	            while(root!=null){
	                stack.push(root);
	                root = root.left;
	            }
	            root = stack.pop();
	            list.add(root.val);
	            root = root.right;
	        }
	        return list;
	    }
	 
	/**
	 * @see https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
	 * 后序遍历
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
			list2.addFirst(node.val);  ///---------- res  addFirst 
			if(node.left!=null)
				list.add(node.left);
			if(node.right!=null)
				list.add(node.right);
		}
		return list2;
	}
	public static List<Integer> postorderTraversal2(TreeNode root) {
		LinkedList<Integer> res = new LinkedList();
		if(root==null) return res;
		Stack<TreeNode> stack = new Stack();
		stack.add(root);
		while(!stack.isEmpty()) {
			TreeNode node = stack.pop();
			res.addFirst(node.val);
			if(node.right!=null)
				stack.add(node.right);
			if(node.left!=null)
				stack.add(node.left);
		}
		return res;
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
        // //注意不是返回  thead.next ！！！！！！！
        return pre;
	}
	// 1,2,3,4,5
	public static ListNode reverseList2(ListNode head) {
		if(head == null) return null;
        List<ListNode> list = new  ArrayList();
        while(head!=null) {
        	list.add(head);
        	head = head.next;
        }
        int i = 0,j= list.size()-1;
        while(i<j) {
        	list.get(j).next = list.get(j-1);
        	j--;
        }
        // 1.next要封掉
        list.get(i).next = null;
        // 
        return list.get(list.size()-1);
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
	 * @param n  为A字符串的最大下标  A.length()-1
	 * @param B
	 * @param m	为B字符串的最大下标   B.length()-1
	 * @return
	 */
	 public static int findLCS2(String A, int n, String B, int m) {
		 //注意不是  new int [n][m];
		  int[][]  dp = new int [n+1][m+1];
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
		  //装所有ListNode
	      List<ListNode> list = new ArrayList();
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
