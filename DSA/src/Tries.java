
public class Tries {


	#402
	
	//Function to insert string into TRIE.
	static void insert(TrieNode root, String key) 
	{
	    TrieNode curr=root;
	    for(char c:key.toCharArray()){
	        if(curr.children[c-'a']==null){
	            curr.children[c-'a']=new TrieNode();
	        }
	        curr=curr.children[c-'a'];
	    }
	    
	    curr.isEndOfWord=true;
	}
	
	//Function to use TRIE data structure and search the given string.
	static boolean search(TrieNode root, String key)
	{
	    TrieNode curr=root;
	    for(char c:key.toCharArray()){
	        if(curr.children[c-'a']!=null){
	            curr=curr.children[c-'a'];
	        }
	        else
	            return false;
	    }
	    
	    return curr.isEndOfWord;
	}
	
	
	#403
	public class Solution {
	    
	    public class TrieNode{
	        char ch;
	        int freq;
	        TrieNode children[]=new TrieNode[26];
	        
	        TrieNode(char ch){
	            this.ch=ch;
	            freq=0;
	            for(TrieNode c:children){
	                c=null;
	            }
	        }
	    }
	    
	    public void insert(TrieNode root, String key) 
	    {
	        TrieNode curr=root;
	        for(char c:key.toCharArray()){
	            if(curr.children[c-'a']==null){
	                curr.children[c-'a']=new TrieNode(c);
	            }
	            curr.freq++;
	            curr=curr.children[c-'a'];
	            
	        }
	        
	       
	    }
	
	    
	    public String[] prefix(String[] A) {
	        
	        String ans[]=new String[A.length];
	        
	        TrieNode root= new TrieNode('_');
	        
	        for(String s:A){
	            insert(root,s);
	        }
	        
	        int index=0;
	        for(String str:A){
	            TrieNode curr=root;
	            StringBuilder str_ans=new StringBuilder();
	            for(char ch: str.toCharArray()){
	                if(curr.children[ch-'a']!=null && curr.freq>1){
	                    str_ans.append(ch);
	                    curr=curr.children[ch-'a'];
	                }
	                else {
	                    break;
	                }
	            }
	            ans[index++]= str_ans.toString();
	        }
	        
	        return ans;
	        
	    }
	}
	
	
	#404
	
	class Solution {
	    
	    public class TrieNode{
	        char ch;
	        boolean we;
	        TrieNode children[]= new TrieNode[26];
	        
	        TrieNode(char ch){
	            this.ch=ch;
	            we=false;
	            for(TrieNode child:children) child=null;
	        }
	    }
	    
	    public void insert(TrieNode root, String s){
	        TrieNode curr=root;
	        
	        for(char c:s.toCharArray()){
	            if(curr.children[c-'a']==null) curr.children[c-'a']= new TrieNode(c);
	            curr=curr.children[c-'a'];
	        }
	        curr.we=true;
	    }
	    
	    public boolean search(TrieNode root, String s){
	        TrieNode curr=root;
	        
	        for(char c:s.toCharArray()){
	            if(curr.children[c-'a']!=null){
	                curr=curr.children[c-'a'];
	            }
	            else
	                return false;
	        }
	        return curr.we;
	    }
	    
	    public boolean word_Break(String s, TrieNode root){
	        
	        if(s.length()==0) return true;
	        
	        for(int i=1;i<=s.length();i++){
	            
	            if(search(root,s.substring(0,i))==true && word_Break(s.substring(i,s.length()),root)==true) 
	                return true;
	        }
	        
	        return false;
	    }
	    
	    public boolean wordBreak(String s, List<String> wordDict) {
	        
	        
	        TrieNode root= new TrieNode('_');
	        
	        for(String str:wordDict){
	            insert(root,str);
	        }
	        
	        return word_Break(s,root);
	        
	    }
	}
	
	
	
	#407
	class GfG
	{
	     public static class TrieNode{
	        int ele;
	        boolean we;
	        TrieNode children[]= new TrieNode[10];
	        
	        TrieNode(int ele){
	            this.ele=ele;
	            we=false;
	            for(TrieNode child:children) child=null;
	        }
	    }
	
	    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][],int r, int c)
	    {
	        TrieNode root= new TrieNode(-1);
	        for(int i=0;i<r;i++){
	            TrieNode curr=root;
	            for(int j=0;j<c;j++){
	                if(curr.children[a[i][j]]==null){
	                    curr.children[a[i][j]]=new TrieNode(a[i][j]);
	                }
	                curr=curr.children[a[i][j]];
	            }
	            curr.we=true;
	        }
	        
	        ArrayList<ArrayList<Integer>> ans= new ArrayList<>();
	        
	        for(int i=0;i<r;i++){
	            ArrayList<Integer> small_ans= new ArrayList<>();
	            TrieNode curr=root;
	            for(int j=0;j<c;j++){
	                if(curr.children[a[i][j]]!=null){
	                    small_ans.add(a[i][j]);
	                    curr=curr.children[a[i][j]];
	                }
	                
	                if(curr.we==true){
	                    ans.add(small_ans);
	                }
	                curr.we=false;
	            }
	        }
	        
	        return ans;
	    }
	}
	
	
	
	#405


}
