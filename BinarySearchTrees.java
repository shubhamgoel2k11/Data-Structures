
public class BinarySearchTrees {

	#215
	class Solution {
	    
	    public int max(TreeNode root){
	        if(root==null) return 0;
	        if(root.right!=null){
	            return max(root.right);
	        }
	        else {
	            return root.val;
	        }
	    }
	    
	    public TreeNode deleteNode(TreeNode root, int key) {
	        
	        if(root==null) return null;
	        
	        if(root.val>key){
	            root.left= deleteNode(root.left,key);
	        }
	        else if(root.val<key){
	            root.right= deleteNode(root.right,key);
	        }
	        else{
	            if(root.left!=null && root.right!=null){
	                int maxNode=max(root.left);
	                root.val= maxNode;
	                root.left= deleteNode(root.left,maxNode);
	                return root;
	            }
	            else if(root.left!=null){
	                return root.left;
	            }
	            else if(root.right!=null){
	                return root.right;
	            }
	            else{
	                return null;
	            }
	        }
	        
	        return root;
	    }
	}



	#216
	class Tree
	{
	    //Function to find the minimum element in the given BST.
	    int minValue(Node root)
	    {
	        if(root==null) return 0;
	        if(root.left!=null) return minValue(root.left);
	        else{
	            return root.data;
	        }
	    }
	}


	#218
	public class Solution
	{
	   
	    boolean helper(Node root, int min , int max){
	        if(root==null) return true;
	        boolean left= helper(root.left,min,root.data);
	        boolean right=helper(root.right,root.data,max);
	        if(root.data>min && root.data<max && left && right){
	            return true;
	        }
	        return false;
	    }
	   
	    boolean isBST(Node root)
	    {
	        return helper(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	    }
	}


	#217
	class GfG
	{
	    public static void findPreSuc(Node root, Res p, Res s, int key)
	    {
	      if(root==null) return;
	      
	      if(root.data<key){
	          p.pre=root;
	          findPreSuc(root.right,p,s,key);
	      }
	      else if(root.data>key){
	          s.succ=root;
	          findPreSuc(root.left,p,s,key);
	      }
	      else {
	          
	          if(root.left!=null){
	              Node curr=root.left;
	              while(curr.right!=null){
	                  curr=curr.right;
	              }
	              p.pre=curr;
	          }
	          
	          if(root.right!=null){
	              Node curr= root.right;
	              while(curr.left!=null){
	                  curr=curr.left;
	              }
	              s.succ=curr;
	          }
	      }
	      
	    }
	}


	#219
	class GFG
	{
	    static Node succ=null;
	    public static void helper(Node root){
	       
	        if(root==null) return;
	        
	        populateNext(root.right);
	        
	        root.next=succ;
	        succ=root;
	        
	        populateNext(root.left);
	        
	    }
	    public static void populateNext(Node root)
	    {
	       helper(root);
	    }
	}



	#220
	class BST
	{   
	    //Function to find the lowest common ancestor in a BST. 
		Node LCA(Node root, int n1, int n2)
		{
	        if(root==null) return null;
	        if(root.data<n1 && root.data<n2){
	            return LCA(root.right,n1,n2);
	        }
	        else if(root.data>n1 && root.data>n2){
	            return LCA(root.left,n1,n2);
	        }
	        else{
	            return root;
	        }
	    }
	    
	}


	#223
	class Solution {
	    
	    ArrayList<Integer> arr= new ArrayList<>();
	    public void helper(TreeNode root){
	        if(root==null) return;
	        helper(root.left);
	        arr.add(root.val);
	        helper(root.right);
	    }
	    
	    public TreeNode buildBST(int s, int e){
	        if(s>e) return null; 
	        
	        int mid= (s+e)/2;
	        TreeNode node= new TreeNode(arr.get(mid));
	        node.left= buildBST(s,mid-1);
	        node.right= buildBST(mid+1,e);
	        
	        return node;
	    }
	    public TreeNode balanceBST(TreeNode root) {
	        
	        helper(root);
	        
	        return buildBST(0,arr.size()-1);
	    }
	}



	#221
	class Solution {
	    
	    int idx=0;
	    public TreeNode bst(int pre[], int min, int max){
	        
	        if(idx>=pre.length||pre[idx]<min||pre[idx]>max){
	            return null;
	        }
	        
	        TreeNode root= new TreeNode(pre[idx]);
	        idx++;
	        
	        root.left= bst(pre,min,root.val);
	        root.right=bst(pre,root.val,max);
	        
	        return root;
	    }
	    
	    public TreeNode bstFromPreorder(int[] preorder) {
	        
	        return bst(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
	        
	    }
	}


	#228 // MORRIS INORDER TRAVERSAL
	class Solution {
	    public List<Integer> inorderTraversal(TreeNode root) {
	        
	        List<Integer> ans= new ArrayList<>();
	        if(root==null) return ans;
	        
	        TreeNode curr= root;
	        
	        while(curr!=null){
	            
	            if(curr.left==null){
	                ans.add(curr.val);
	                curr=curr.right;
	            }
	            else{
	                
	                TreeNode pre_dec= curr.left;
	                
	                while(pre_dec.right!=null && pre_dec.right!=curr){
	                    pre_dec=pre_dec.right;
	                }
	                
	                
	                if(pre_dec.right==null){
	                    pre_dec.right=curr;
	                    curr=curr.left;
	                }
	                else{
	                    pre_dec.right=null;
	                    ans.add(curr.val);
	                    curr=curr.right;
	                }
	                
	            }
	        }
	        
	        return ans;
	    }
	}


	#222
	class Solution
	{
	    ArrayList<Integer> arr= new ArrayList<>();
	    
	    void store(Node root){
	        if(root==null) return;
	        
	        store(root.left);
	        arr.add(root.data);
	        store(root.right);
	    }
	    
	    Node built(int s, int e){
	        if(s>e) return null;
	        
	        int m= (s+e)/2;
	        Node root= new Node(arr.get(m));
	        root.left=built(s,m-1);
	        root.right=built(m+1,e);
	        return root;
	    }
	    
	    Node binaryTreeToBST(Node root)
	    {
	       if(root==null) return null;
	       store(root);
	       Collections.sort(arr);
	       return built(0,arr.size()-1);
	    }
	}



	#225
	class Solution
	{
	    int count=0;
	    int ans=0;
	    public void inorder(Node root, int K){
	        if(root==null) return;
	        
	        inorder(root.right,K);
	        count++;
	        if(count==K){
	            ans=root.data;
	        }
	        inorder(root.left,K);
	    }
	    public int kthLargest(Node root,int K)
	    {
	        inorder(root,K);
	        return ans;
	    }
	}


	#226
	class Solution
	{
	    int count=0;
	    int ans=-1;
	    public void inorder(Node root, int K){
	        if(root==null) return;
	        inorder(root.left,K);
	        count++;
	        if(count==K){
	            ans=root.data;
	        }
	        inorder(root.right,K);
	    }
	    
	    public int KthSmallestElement(Node root, int K) 
	    {
	        inorder(root,K);
	        return ans;
	    }
	}


	#227
	class GfG
	{
	    public static int countPairs(Node root1, Node root2, int x)
	    {
	       if(root1==null || root2==null) return 0;
	       
	       Stack<Node> s1= new Stack<>();
	       Stack<Node> s2= new Stack<>();
	       int count=0;
	       
	       while(true){
	           
	           while(root1!=null){
	               s1.push(root1);
	               root1=root1.left;
	           }
	           
	           while(root2!=null){
	               s2.push(root2);
	               root2=root2.right;
	           }
	           
	           if(s1.empty()||s2.empty())
	                break;
	                
	            Node top1= s1.peek();
	            Node top2= s2.peek();
	           
	           if(top1.data+ top2.data== x){
	               count++;
	               s1.pop();
	               s2.pop();
	               
	               root1= top1.right;
	               root2= top2.left;
	               
	           }
	           else if(top1.data+ top2.data< x){
	               
	               s1.pop();
	               root1=top1.right;
	           }
	           else{
	               s2.pop();
	               root2=top2.left;
	           }
	       }
	       
	       return count;
	    }
	}


	#229
	class Tree
	{
	    int getCount(Node root,int l, int h)
	    {
	        if(root==null) return 0;
	        
	        if(root.data>=l && root.data<=h){
	            return getCount(root.left,l,h)+getCount(root.right,l,h)+1;
	        }
	        else if(root.data<=l){
	            return getCount(root.right,l,h);
	        }
	        else {
	            return getCount(root.left,l,h);
	        }
	    }
	}


	#232


	#234
	class Solution{
	    
	    static class Pair{
	        boolean isBST;
	        int size;
	        int min;
	        int max;
	        
	        Pair(boolean isBST, int size, int min, int max){
	            this.isBST=isBST;
	            this.size=size;
	            this.min=min;
	            this.max=max;
	        }
	    }
	    
	    static Pair isBST(Node root){
	        if(root==null) return new Pair(true,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
	        
	        Pair left= isBST(root.left);
	        Pair right= isBST(root.right);
	        
	        boolean isBST=false;
	        int size=0;
	        isBST= left.isBST && right.isBST && (left.max<root.data) && (right.min>root.data);
	        
	        if(isBST){
	            size=left.size+right.size+1;
	        }
	        else if(left.size>right.size){
	            size=left.size;
	        }
	        else {
	            size=right.size;
	        }
	        int min= Math.min(left.min,Math.min(root.data,right.min));
	        int max= Math.max(left.max,Math.max(root.data,right.max));
	        return new Pair(isBST,size,min,max);
	    }
	    static int largestBst(Node root)
	    {
	        Pair ans= isBST(root);
	        return ans.size;
	        
	    }
	    
	}


	#235
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
	            prev.left=null;
	 prev.right=root;
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


}
