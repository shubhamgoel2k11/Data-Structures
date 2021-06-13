import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class BinaryTrees {
	
	class Node{
		Node left;
		Node right;
		int data;
		
		public Node(int data) {
			this.data=data;
			left=null;
			right=null;
		}
	}

	//177
	static ArrayList <Integer> levelOrder(Node node) 
	    {
	        ArrayList<Integer> ans= new ArrayList<>();
	        Queue<Node> q= new LinkedList<>(); 
	        q.add(node);
	        while(!q.isEmpty()){
	            Node temp= q.poll();
	            ans.add(temp.data);
	            if(temp.left!=null){
	                q.add(temp.left);
	            }
	            if(temp.right!=null){
	                q.add(temp.right);
	            }
	        }
	        return ans;
	    }


	//178
	class Tree
	{
	    public ArrayList<Integer> reverseLevelOrder(Node root) 
	    {
	        ArrayList<Integer> ans= new ArrayList<>();
	        if(root==null){
	            return ans;
	        }
	        Queue<Node> q= new LinkedList<>();
	        q.add(root);
	        while(!q.isEmpty()){
	            Node temp= q.poll();
	            ans.add(temp.data);
	            if(temp.right!=null) q.add(temp.right);
	            if(temp.left!=null) q.add(temp.left);
	        }
	        Collections.reverse(ans);
	        return ans; 
	    }
	}      


	//179
	 int height(Node node) 
	    {
	        if(node==null)
	        return 0;
	        int left= height(node.left);
	        int right=height(node.right);
	        return Math.max(left,right)+1;
	    }


	//180
	class Solution 
	{
	    class Pair {
	       int dia;
	       int ht;
	       Pair(int dia, int ht){
	           this.dia=dia;
	           this.ht=ht;
	       }
	   }
	    Pair helper(Node root){ 
	        if(root==null){
	            return new Pair(0,0);
	        }
	        Pair left= helper(root.left);
	        Pair right= helper(root.right);
	        int max1= Math.max(left.dia,right.dia);
	        int max2= Math.max(left.ht+right.ht+1,max1);
	        Pair ans= new Pair(max2,Math.max(left.ht,right.ht)+1);
	        return ans;
	    }
	        int diameter(Node root) {
	        Pair ans= helper(root);
	        return ans.dia;
	    }
	}


	//181
	static Node mirrorify(Node root)
	{
	    if (root == null)
	    {
	        return null;
	         
	    }
	 
	    // Create new mirror node from original tree node
	    Node mirror = createNode(root.data);
	    mirror.right = mirrorify(root.left);
	    mirror.left = mirrorify(root.right);
	    return mirror;
	}


	//182/183/184
	class Solution
	{
	    class Pair{
	        int level;
	        Node node;
	        Pair(Node node,int level){
	            this.node=node;
	            this.level=level;
	        }
	    }
	     ArrayList<Integer> inOrder(Node root)
	    {
	        ArrayList<Integer> ans = new ArrayList<>(); 
	        if(root==null){
	            return new ArrayList<>();
	        }
	        Stack<Pair> st= new Stack<>();
	        st.push(new Pair(root,1));
	        while(!st.empty()){
	            Pair temp= st.peek();
	            if(temp.level==1){
	                temp.level++;
	                if(temp.node.left!=null){
	                    st.push(new Pair(temp.node.left,1));
	                }
	            }
	            else if(temp.level==2){
	                ans.add(temp.node.data);
	                temp.level++;
	                if(temp.node.right!=null){
	                    st.push(new Pair(temp.node.right,1));
	                }       
	            }
	            else
	            {
	                st.pop();
	            }
	        }
	        return ans;  
	    }  
	}


	#185/186

	class Tree
	{
	    //Function to return list containing elements of left view of binary tree.
	    ArrayList<Integer> leftView(Node root)
	    {
	      ArrayList<Integer> ans= new ArrayList<>();
	      if(root==null){
	          return ans;
	      }
	      Queue<Node> q= new LinkedList<>();
	      q.add(root);
	      while(!q.isEmpty()){
	          int size=q.size();
	          Boolean flag= true;
	          while(size>0){
	              Node temp= q.peek();
	              if(flag==true){
	                  ans.add(temp.data);
	                  flag=false;
	              }
	              q.remove();
	              if(temp.left!=null) q.add(temp.left);
	              if(temp.right!=null) q.add(temp.right);
	              size--;
	          }
	      }
	      return ans;
	    }
	}


	#187
	class Solution
	{
	    //Function to return a list of nodes visible from the top view 
	    //from left to right in Binary Tree.
	    static class Pair{
	        int ht;
	        Node node;
	        Pair(int ht, Node node){
	            this.ht=ht;
	            this.node=node;
	        }
	    }
	    
	    static void width(Node root, int ht, int minMax[]){
	        if(root==null) return;
	        minMax[0]= Math.min(minMax[0],ht);
	        minMax[1]= Math.max(minMax[1],ht);
	        width(root.left,ht-1,minMax);
	        width(root.right,ht+1,minMax);
	    }
	    
	    
	    static ArrayList<Integer> topView(Node root)
	    {
	        ArrayList<Integer> ans= new ArrayList<>();
	        if(root==null) return ans;
	        int minMax[]= new int[2];
	        width(root,0,minMax);
	        Queue<Pair> q= new LinkedList<>();
	        for(int i=0;i<minMax[1]-minMax[0]+1;i++) ans.add(null);
	        q.add(new Pair(Math.abs(minMax[0]) ,root));
	        while(!q.isEmpty()){
	                Pair temp= q.poll();
	                if(ans.get(temp.ht)==null) ans.set(temp.ht,temp.node.data);
	                if(temp.node.left!=null) q.add(new Pair(temp.ht-1,temp.node.left));
	                if(temp.node.right!=null) q.add(new Pair(temp.ht+1,temp.node.right));
	        }
	        return ans;
	    }
	}


	#188
	class Tree
	{
	    class Pair{
	        Node node;
	        int ht;
	        Pair(Node node,int ht){
	            this.node=node;
	            this.ht=ht;
	        }
	    }
	    public void width(Node root, int ht, int minmax[]){
	        if(root==null) return;
	        minmax[0]=Math.min(minmax[0],ht);
	        minmax[1]=Math.max(minmax[1],ht);
	        width(root.left,ht-1,minmax);
	        width(root.right,ht+1,minmax);
	    }
	    public ArrayList <Integer> bottomView(Node root)
	    {
	         ArrayList<Integer> ans= new ArrayList<>();
	         if(root==null) return ans;
	         int minmax[]= new int[2];
	         width(root,0,minmax);
	         int len=minmax[1]-minmax[0]+1;
	         for(int i=0;i<len;i++) ans.add(0);
	         Queue<Pair> q= new LinkedList<>();
	         q.add(new Pair(root,Math.abs(minmax[0])));
	         while(!q.isEmpty()){
	             Pair temp= q.poll();
	             ans.set(temp.ht,temp.node.data);
	             if(temp.node.left!=null) q.add(new Pair(temp.node.left,temp.ht-1));
	             if(temp.node.right!=null) q.add(new Pair(temp.node.right,temp.ht+1));
	         }
	         return ans;  
	    }
	}


	#189
	class GFG
	{
	    //Function to store the zig zag order traversal of tree in a list.
		ArrayList<Integer> zigZagTraversal(Node root)
		{
		    ArrayList<Integer> ans= new ArrayList<>();
		    Queue<Node> q= new LinkedList<>();
		    Stack<Node> s= new Stack<>();
		    if(root==null) return ans;
		    q.add(root);
		    boolean flag=false;
		    while(!q.isEmpty()){
		       int size=q.size();
		       while(size-->0)
		       { 
		        Node t=q.poll();
		        if(t.left!=null) q.add(t.left);
		        if(t.right!=null) q.add(t.right);
		        if(flag==true){
		            s.push(t);
		        }
		        else{
		            ans.add(t.data);
		        }
		       }
		       if(!s.empty()){
		           while(!s.empty()){
		           ans.add(s.pop().data);
		           }
		           flag=false;
		       }
		       else
		       flag=true;
		    }
		    return ans;
		}
	}


	#190
	class Tree
	{
	    class Pair{
	        int ht;
	        boolean isBal;
	        Pair(int ht, boolean isBal){
	            this.ht=ht;
	            this.isBal=isBal;
	        }
	    }   
	    Pair isBal(Node root){
	        if(root==null){
	            return new Pair(0,true);
	        }
	        Pair left=isBal(root.left);
	        Pair right=isBal(root.right);
	        boolean b=false;
	        if(left.isBal==true && right.isBal==true && Math.abs(left.ht-right.ht)<=1){
	            b=true;
	        }
	        return new Pair(Math.max(left.ht,right.ht)+1,b);
	    }
	    boolean isBalanced(Node root)
	    {
		   Pair ans= isBal(root);
		   return ans.isBal;
	    }
	}



	#191
	class Tree
	{
	     public ArrayList<Integer> diagonal(Node root)
	      {
	        ArrayList<Integer> ans= new ArrayList<>();
	        if(root==null) return ans;
	        Queue<Node> q= new LinkedList<>();
	        q.add(root);
	        while(!q.isEmpty()){
	            Node temp= q.poll();
	            while(temp!=null){
	                if(temp.left!=null) q.add(temp.left);
	                ans.add(temp.data);
	                temp=temp.right;
	            }
	        }
	        return ans;
	      }
	}

	#192
	class Solution
	{
	    
	    void printLeft(Node root,ArrayList<Integer> ans){
	        
	       if(root==null) return ;
	       if(root.left!=null){
	           ans.add(root.data);
	           printLeft(root.left,ans);
	       }
	       else if(root.right!=null){
	           ans.add(root.data);
	           printLeft(root.right,ans);
	       }
	       else
	            return;
	    }
	    
	    void printRight(Node root,ArrayList<Integer> ans){
	        
	        if(root==null) return;
	        if(root.right!=null){
	            printRight(root.right,ans);
	            ans.add(root.data);
	        }
	        else if(root.left!=null){
	            printRight(root.left,ans);
	            ans.add(root.data);
	        }
	        else
	            return;
	        
	    }
	    
	    void printLeaves(Node root,ArrayList<Integer> ans){
	        
	        if(root==null) return;
	        
	        if(root.left==null && root.right==null)
	        {
	            ans.add(root.data);
	            return;
	        }
	        printLeaves(root.left,ans);
	        printLeaves(root.right,ans);
	    }
	    
		ArrayList <Integer> printBoundary(Node root)
		{
		    if(root==null) return new ArrayList<Integer>();
		    ArrayList<Integer> ans= new ArrayList<>();
		    ans.add(root.data);
		    
		    printLeft(root.left,ans);
		    printLeaves(root.left,ans);
		    printLeaves(root.right,ans);
		    printRight(root.right,ans);
		    
		    return ans;
		}
	}


	#194
	class Solution
	{
	    Node head=null;
	    Node prev=null;
	    boolean isHead=true;
	    
	    void solve(Node root){
	        
	        if(root==null) return;
	        solve(root.left);
	        if(isHead==true){
	            head=root;
	            prev=root;
	            isHead=false;
	        }
	        else {
	            prev.right=root;
	            root.left=prev;
	            prev=root;
	        }
	        solve(root.right);
	    }
	    
	    Node bToDLL(Node root)
	    {
	        if(root==null) return null;
	        solve(root);
	        return head;
	        
	    }
	}


	#195
	class Solution{
	    
	    public int toSumTrees(Node root){
	        if(root==null) return 0;
	        int leftSum= toSumTrees(root.left);
	        int rightSum= toSumTrees(root.right);
	        int originalRootValue=root.data;
	        root.data= leftSum+rightSum;
	        return originalRootValue+root.data;
	    }
	    
	    public void toSumTree(Node root){
	        toSumTrees(root);
	    }
	}

	#196
	class Solution
	{
	    public static Node buildTree(int inorder[],int inS,int inE, int preorder[], int preS, int preE){

	        if(inS>inE) return null;
	        int idx= inS;
	        while(inorder[idx]!=preorder[preS]){
	            idx++;
	        }
	        int count=idx-inS;
	        Node root= new Node(preorder[preS]);
	        root.left= buildTree(inorder,inS,idx-1,preorder,preS+1,preS+count);
	        root.right=buildTree(inorder,idx+1,inE,preorder,preS+count+1,preE);
	        return root;
	    }
	    
	    public static Node buildTree(int inorder[], int preorder[], int n)
	    {
	        if(n==0) return null;
	        Node root= buildTree(inorder,0,inorder.length-1,preorder,0,preorder.length-1);
	        return root;
	        
	    }
	    
	}


	#198
	class Solution
	{
	    boolean isSum=true;
	    
	    int helper(Node root){
	        if(root==null) return 0;
	        if(root.left==null && root.right==null) return root.data;
	        if(isSum==false) return 0;
	        int left= helper(root.left);
	        int right=helper(root.right);
	        if(left+right !=root.data) {
	            isSum=false;
	        }
	        return left+right+root.data;
	    }
	    
	    
		boolean isSumTree(Node root)
		{
	            helper(root);
	            return isSum;
		}
	}



	#199
	class Solution
	{
	    boolean isSame=true;
	    int ht=-1;
	    void helper(Node root,int h){
	        if(root==null) return;
	        if(root.left==null && root.right==null){
	            if(ht==-1) ht= h;
	            else if(ht!=h) {
	                isSame=false;
	                return;
	            }
	        }
	        helper(root.left,h+1);
	        helper(root.right,h+1);
	    }
	    
	    boolean check(Node root)
	    {
		helper(root,0);
		return isSame;
	    }
	}


	#200
	class Solution {
	    
	    HashSet<String> map= new HashSet<>();
	    int answer=0;
	    String duplicate(Node root){
	        if(root==null) return "";
	        if(root.left==null && root.right==null){
	           String leaf= String.valueOf(root.data);
	           return leaf;
	        }
	        String left=duplicate(root.left);
	        String right=duplicate(root.right);
	        String rootstr= String.valueOf(root.data);
	        String ans= rootstr+left+right;
	        if(map.contains(ans)){
	            answer=1;
	        }
	        else 
	        {
	            map.add(ans);
	        }
	        return ans;
	    }
	    
	    int dupSub(Node root) {
	        duplicate(root);
	        return answer;
	    }
	}


	#202
	class GfG
	{
	    class Pair{
	        int ht;
	        int sum;
	        Pair(int ht, int sum){
	            this.ht=ht;
	            this.sum=sum;
	        }
	    }
	    
	    public Pair helper(Node root){
	        
	        if(root==null) return new Pair(0,0);
	        Pair left= helper(root.left);
	        Pair right=helper(root.right);
	        if(left.ht> right.ht){
	            return new Pair(left.ht+1,left.sum+root.data);
	        }
	        else if(left.ht==right.ht){
	            if(left.sum>right.sum) return new Pair(left.ht+1,left.sum+root.data);
	            else return new Pair(right.ht+1,right.sum+root.data);
	        }
	        
	         return new Pair(right.ht+1,right.sum+root.data);

	    }
	    
	    public int sumOfLongRootToLeafPath(Node root)
	    {
	        Pair ans= helper(root);
	        return ans.sum;
	    }
	}


	#204
	class GFG {
		int maxSum=0;
		
		public int helper(Node root){
		    if(root==null) return 0;
		    
		    int left= helper(root.left);
		    int right=helper(root.right);
		    int sum= left+right+root.data;
		    
		    if(sum>maxSum) maxSum=sum;
		    
		    return sum;
		}
		
		public int maxSubTree(Node root){
		    
		    helper(root);
		    
		    return maxSum;
		}
	}


	#205
	class GFG {
		class Pair{
		    int sum1;
		    int sum2;
		    Pair(int sum1, int sum2){
		        this.sum1=sum1;
		        this.sum2=sum2;
		    }
		    
		}
	    Pair maxSum(Node root){
	        if(root==null) return new Pair(0,0);
	        
	        Pair left= maxSum(root.left);
	        Pair right= maxSum(root.right);
	        
	        
	        //included
	        int incSum= left.sum2+right.sum2+root.data;
	        //not included
	        int notIncSum= Math.max(left.sum1,left.sum2) + Math.max(right.sum1,right.sum2);
	        
	        return new Pair(incSum,notIncSum);
	        
	    }
	    
	    int sum(Node root){
	        Pair answer=maxSum(root);
	        return Math.max(answer.sum1,answer.sum2);
	    }
	}


	#206
	class GFG {
		
		ArrayList<Integer> arr= new ArrayList<>();
		ArrayList<ArrayList<Integer>> ans= new ArrayList<>();
		
		public void helper(Node root, int k){
		    if(root==null) return;
		    
		    arr.add(root.data);
		    
		    helper(root.left,k);
		    helper(root.right,k);
		    
		    int count=0;
		    for(int i=arr.size()-1;i>=0;i--){
		        count+=arr.get(i);
		        
		        if(count==k){
		            //print from i to arr.size()
		        }
		    }
		    
		    arr.remove(arr.size()-1);
		}
		
		public void sets(Node root, int k){
		    helper(root,k);
		    
		    //print ans ;
		}
	}


	#207
	class Solution
	{
	    //Function to return the lowest common ancestor in a Binary Tree.
		Node lca(Node root, int n1,int n2)
		{
			if(root==null) return null;
			if(root.data==n1||root.data==n2) return root;
			Node left= lca(root.left,n1,n2);
			Node right= lca(root.right,n1,n2);
			if(left!=null && right!=null){
			    return root;
			}
			if(left!=null){
			    return left;
			}
			else
			{
			    return right;
			}
		}
	}


	#208


	#209
	#210
	class Solution {
	    
	    HashMap<String, Integer> map= new HashMap<>();
	    public String helper(Node root){
	        if(root==null) return "";
	        
	        String root_str=String.valueOf(root.data);
	        
	        String left=helper(root.left);
	        String right=helper(root.right);
	        
	        String str=root_str+" "+left+" "+right;
	        if(map.containsKey(str)){
	            map.put(str,map.get(str)+1);
	        }
	        else {
	            map.put(str,1);
	        }
	        
	        return str;
	    }
	    
	    public void printDup(Node root){
	        
	        helper(root);
	        boolean flag=false;
	        ArrayList<Integer> list= new ArrayList<>();
	        for(String str: map.keySet()){
	            if(map.get(str)>1){
	                String ans[]=str.split(" ");
	                list.add(Integer.valueOf(ans[0]));
	                flag=true;
	            }
	        }
	        
	        if(flag==false) {
	            System.out.println("-1");
	        }
	        
	         Collections.sort(list);
	         
	         for(int i:list){
	             System.out.print(i+" ");
	         }
	         System.out.println();
	    }
	    
	   
	}

	#211

	class Solution  
	{ 
	    boolean isIsomorphic(Node root1, Node root2)  
	    { 
	         if(root1==null && root2==null) return true;
	         if(root1==null) return false;
	         if(root2==null) return false;
	         
	         if(root1.data!=root2.data) return false;
	         
	         return (isIsomorphic(root1.right,root2.left)&&isIsomorphic(root1.left,root2.right))||(isIsomorphic(root1.left,root2.left)&&isIsomorphic(root1.right,root2.right));
	    }
	    
	}  


	#212
	class BinaryTree
	{
	    //Function to return a list containing the preorder traversal of the tree.
	    static ArrayList<Integer> preorder(Node root)
	    {
	        ArrayList<Integer> ans= new ArrayList<>();
	        if(root==null) return ans;
	        Stack<Node> st= new Stack<>();
	        
	        st.push(root);
	        Node curr=root;
	        
	        while(!st.empty()){
	            while(curr!=null){
	                ans.add(curr.data);
	                if(curr.right!=null)
	                st.push(curr.right);
	            
	                curr=curr.left;
	            }
	            if(!st.empty())
	            curr=st.pop();
	            
	        }
	        
	        return ans;
	        
	    }

	}

}
