
public class Heaps {

	#339
	class Solution {
	    int[] kLargest(int[] arr, int n, int k) {
	        
	        int ans[]= new int[k];
	        if(n<k||n==0) return ans;
	        PriorityQueue<Integer> pq= new PriorityQueue<>();
	        for(int i=0;i<k;i++){
	            pq.add(arr[i]);
	        }
	        for(int i=k;i<n;i++){
	            if(arr[i]>pq.peek()){
	                pq.remove();
	                pq.add(arr[i]);
	            }
	        }
	        for(int i=k-1;i>=0;i--){
	            ans[i]=pq.remove();
	        }
	        return ans;
	        
	    }
	}


	#340
	[similar question as above]

	#341
	class Solution
	{
	    static class Pair implements Comparable<Pair> {
	        int li;
	        int idx;
	        int val;
	        
	        Pair(int li, int idx, int val){
	            this.li=li;
	            this.idx=idx;
	            this.val=val;
	        }
	        
	        public int compareTo(Pair o){
	            return this.val-o.val;
	        }
	    }
	    //Function to merge k sorted arrays.
	    public static ArrayList<Integer> mergeKArrays(int[][] arr,int k) 
	    {
	        ArrayList<Integer> ans= new ArrayList<>();
	        
	        if(arr.length==0) return ans;
	        
	        PriorityQueue<Pair> pq= new PriorityQueue<>();
	        
	        for(int i=0;i<k;i++){
	            pq.add(new Pair(i,0,arr[i][0]));
	        }
	        
	        while(pq.size()>0){
	            
	            Pair temp= pq.remove();
	            int tli=temp.li;
	            int tidx=temp.idx;
	            int tval=temp.val;
	            tidx++;
	            ans.add(tval);
	            
	            if(tidx<k){
	              Pair node= new Pair(tli,tidx,arr[tli][tidx]);
	              pq.add(node);  
	            } 
	            
	            
	        }
	        
	        
	        return ans;
	        
	        
	        
	    }
	}



	#344
	class Pair implements Comparable<Pair>{
	    char ch;
	    int count;
	    
	    Pair(char ch, int count){
	        this.ch=ch;
	        this.count=count;
	    }
	    
	    public int compareTo(Pair o){
	        return this.count-o.count;
	    }
	}
	class Solution {
	    
	    public String reorganizeString(String s) {
	        
	        if(s.length()<=1) return s;
	        
	        PriorityQueue<Pair> pq= new PriorityQueue<>(Collections.reverseOrder());
	        
	        HashMap<Character, Integer> map= new HashMap<>();
	        
	        for(char c: s.toCharArray()){
	            if(map.containsKey(c)){
	                map.put(c,map.get(c)+1);
	            }
	            else{
	                map.put(c,1);
	            }
	        }
	        
	        for(char c: map.keySet()){
	            pq.add(new Pair(c,map.get(c)));
	        }
	        
	        StringBuilder ans= new StringBuilder();
	        while(pq.size()>1){
	            Pair c1= pq.remove();
	            Pair c2= pq.remove();
	            ans.append(c1.ch);
	            ans.append(c2.ch);
	            
	            c1.count--;
	            c2.count--;
	            if(c1.count>0) pq.add(c1);
	            if(c2.count>0) pq.add(c2);
	        }
	        
	        if(!pq.isEmpty()){
	            if(pq.peek().count>1) return "";
	            ans.append(pq.remove().ch);
	        }
	        
	        return ans.toString();
	        
	    }
	}




	#345
	class Solution
	{
	    class Pair implements Comparable<Pair>{
	        int li;
	        Node node;
	        Pair(int li, Node node){
	            this.li=li;
	            this.node=node;
	        }
	        public int compareTo(Pair o){
	           return this.node.data - o.node.data;
	        }
	    }
	    
	    Node mergeKList(Node[]arr,int K)
	    {
	        if(K==0) return null;
	        
	        PriorityQueue<Pair> pq= new PriorityQueue<>();
	        Node head=null;
	        Node tail=null;
	        boolean isFirst=true;
	        
	        for(int i=0;i<K;i++){
	            pq.add(new Pair(i,arr[i]));
	        }
	        
	        while(pq.size()>0){
	            
	            Pair temp= pq.remove();
	            Node temp_node=temp.node;
	            if(isFirst){
	                head=temp_node;
	                tail=temp_node;
	                isFirst=false;
	            }
	            else{
	                tail.next=temp_node;
	                tail=tail.next;
	            }
	            
	            if(temp.node.next!=null) pq.add(new Pair(temp.li,temp.node.next));
	        }
	        
	        return head;
	        
	    }
	}


	#337
	class GfG
	{
	    void buildHeap(int arr[], int n)
	    {
	       if(n<=1) return;
	       
	       for(int i=(n-2)/2;i>=0;i--)
	       {
	           heapify(arr,n,i);
	       }
	       
	        
	     
	    }
	 
	    // To heapify a subtree rooted with node i which is
	    // an index in arr[]. n is size of heap
	    void heapify(int arr[], int n, int i)
	    {
	        int left= 2*i+1;
	        int right=2*i+2;
	        int largest= i;
	        
	        if(left<n && arr[left]>arr[largest])
	        largest=left;
	        if(right<n && arr[right]>arr[largest])
	        largest=right;
	        
	        if(largest!=i)
	        {
	            int temp=arr[largest];
	            arr[largest]=arr[i];
	            arr[i]=temp;
	            heapify(arr,n,largest);
	        }
	    }
	    
	 }
	 
	 

	#338
	class Solution {
	    public int[] maxSlidingWindow(int[] nums, int k) {
	        
	        int n=nums.length;
	        int arr[]= new int[n-k+1];
	        if(n==0) return arr;
	        
	        PriorityQueue<Integer> pq= new PriorityQueue<>((a,b) -> {
	           return nums[b]-nums[a];
	        });
	        int idx=0;
	        for(int i=0;i<n;i++){
	            
	            while(!pq.isEmpty() && pq.peek()<=i-k){
	                pq.remove();
	            }
	            pq.add(i);
	            if(i>=k-1)
	            arr[idx++]=nums[pq.peek()];
	        }
	        
	        return arr;
	    }
	}


	#346
	class smallestRangeFromKLists
	{
	   static class Pair implements Comparable<Pair>{
	        int li;
	        int idx;
	        int val;
	        
	        Pair(int li, int idx, int val){
	            this.li=li;
	            this.idx=idx;
	            this.val=val;
	        }
	        
	        public int compareTo(Pair o){
	            return this.val-o.val;
	        }
	    }
		static int[] findSmallestRange(int[][] arr,int n,int k)
		{
		    int ans[]= new int[2];
		    if(n==0|| k==0) return ans;
		    int max=Integer.MIN_VALUE;
		    
		    PriorityQueue<Pair> pq= new PriorityQueue<>();
		    
		    for(int i=0;i<arr.length;i++)
		       { pq.add(new Pair(i,0,arr[i][0]));
		         max=Math.max(max,arr[i][0]);       
		       }
		       
		       int min=pq.peek().val;
		       int range=max-min;
		       int range_min=min;
		       int range_max=max;
		   
		    while(pq.size()>0){
		        Pair temp= pq.remove();
		        temp.idx++;
		        if(temp.idx<n){
		            max=Math.max(max,arr[temp.li][temp.idx]);
		            pq.add(new Pair(temp.li,temp.idx,arr[temp.li][temp.idx]));
		        }
		        else{
		            break;
		        }
		        min=pq.peek().val;
		        if(range>max-min){
		            range=max-min;
		            range_min=min;
		            range_max=max;
		        }
		    }
		    
		    ans[0]=range_min;
		    ans[1]=range_max;
		    return ans;
		}
	}









	#348
	class Solution
	{
		boolean isHeap(Node root)
		{
		    if(root==null) return true;
		    
		    boolean left=isHeap(root.left);
		    boolean right=isHeap(root.right);
		    
		    if(left && right){
		        if(root.left!=null && root.right!=null){
		            if(root.left.data<root.data && root.right.data<root.data)
		                return true;
		        }
		        else if(root.left!=null){
		            if(root.left.data<root.data)
		                return true;
		        }
		        else if(root.right!=null){
		            return false;
		        }
		        else{
		            return true;
		        }
		    }
		    
		    return false;
		}
	}


	#349
	class Solution
	{
	    //Function to return the minimum cost of connecting the ropes.
	    long minCost(long arr[], int n) 
	    {
	        if(n==0||n==1) return 0;
	        long cost=0;
	        PriorityQueue<Long> pq= new PriorityQueue<>();
	        
	        for(long a: arr){
	            pq.add(a);
	        }
	        
	        while(pq.size()>1){
	            long l1=pq.remove();
	            long l2= pq.remove();
	            
	            cost+=l1+l2;
	            
	            pq.add(l1+l2);
	        }

	        return cost;
	    }
	}


	#350
	[first copy the values in inorder in array , then just do preorder traversal and store the values]

	#351
	[max heapify the first half elements in downheapify manner]
	[complexity is O(N) , not O(NLogN)]


	#353 [just need to change the conversion of int, SUM OF STRINGS ]
	class Solution {
	    String solve(int[] arr, int n) {
	        if(n==0||n==1) return "";
	        
	        PriorityQueue<Integer> pq= new PriorityQueue<>();
	        String a="";
	        String b="";
	        
	        for(int ele:arr){
	            pq.add(ele);
	        }
	        
	        while(pq.size()>1){
	            
	            a+=Integer.toString(pq.remove());
	            b+=Integer.toString(pq.remove());
	            
	        }
	        
	        if(pq.size()!=0){
	            a+=Integer.toString(pq.remove());
	        }
	        
	        int aval= Integer.parseInt(a);
	        int bval= Integer.parseInt(b);
	        
	        String ans = Integer.toString(aval+bval);
	        return ans;
	    }
	}


	#347
	class MedianFinder {

	    PriorityQueue<Integer> left;
	    PriorityQueue<Integer> right;
	    /** initialize your data structure here. */
	    public MedianFinder() {
	       right = new PriorityQueue<>();
	       left= new PriorityQueue<>(Collections.reverseOrder());
	    }
	    
	    public void addNum(int num) {
	        
	        if(right.size()>0 && right.peek()<num){
	            right.add(num);
	        }
	        else{
	            left.add(num);
	        }
	        
	        if(left.size()-right.size()==2){
	            right.add(left.remove());
	        }
	        else if(right.size()-left.size()==2){
	            left.add(right.remove());
	        }
	    }
	    
	    public double findMedian() {

	        if(left.size()==0) return 0;
	        if(left.size()==right.size()){
	            double ans= (double)(left.peek()+right.peek());
	            ans/=2;
	            return ans;
	        }
	        else if(left.size()>right.size())
	            return (double)left.peek();
	        else
	            return (double)right.peek();
	    }
	}


}
