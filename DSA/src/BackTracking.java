
public class BackTracking {


#275
class Solution {
    public static ArrayList<String> findPath(int[][] m, int n) {
        
        ArrayList<String> ans= new ArrayList<>();
        if(n==0) return ans;
        
        boolean isVisited[][]= new boolean[n][n];
        find(m, 0, 0, n, "", ans, isVisited);
        
        
        return ans;
    }
    
    public static void find(int grid[][], int row, int col, int len, String asf, ArrayList<String> ans, boolean isVisited[][])
    {
        if(row>=len || row<0 || col>=len || col<0 || grid[row][col]==0 || isVisited[row][col]==true){
            return;
        }
        
        if(row==len-1 && col==len-1){
            ans.add(asf);
            return;
        }
        
        isVisited[row][col]=true;
        find(grid, row+1, col, len, asf+"D", ans, isVisited);
        find(grid, row, col-1, len, asf+"L", ans, isVisited);
        find(grid, row, col+1, len, asf+"R", ans, isVisited);
        find(grid, row-1, col, len, asf+"U", ans, isVisited);
        isVisited[row][col]=false;
    }
}


#276
class Solution {
    
    List<List<String>> ans= new ArrayList<>();
    
    public List<List<String>> solveNQueens(int n) {
        
        boolean isPlaced[][]= new boolean[n][n];
        solve(0 , n , isPlaced , -1);
        
        return ans;
    }
    
    public void solve(int qpsf, int tq, boolean isPlaced[][], int lcno){
        
        if(qpsf==tq){
            
            List<String> list= new ArrayList<>();
            for(int i=0;i<isPlaced.length;i++){
                String str="";
                for(int j=0;j<isPlaced.length;j++){
                    if(isPlaced[i][j]==true){
                        str+="Q";
                    }
                    else{
                        str+=".";
                    }
                }
                list.add(str);
            }
            ans.add(list);
            return;
        }
        
        
        for(int i=lcno+1;i<isPlaced.length*isPlaced.length;i++){
            
            int row= i/isPlaced.length;
            int col= i%isPlaced.length;
            if(isSafe(isPlaced,row,col)){
            
            isPlaced[row][col]= true;
            solve(qpsf+1 , tq , isPlaced , i);
            isPlaced[row][col] = false;
            }
        }
    }
    
    public boolean isSafe(boolean chess[][], int row, int col){
        
        
        for(int i=row, j=col; i>=0 && j<chess.length ; i-- , j++){
            if(chess[i][j]==true){
                return false;
            }
        }
        
        for(int i=row, j=col; i>=0 ; i--){
            if(chess[i][j]==true){
                return false;
            }
        }
        
        for(int i=row, j=col; i>=0 && j>=0; i-- , j--){
            if(chess[i][j]==true){
                return false;
            }
        }
        
        for(int i=row, j=col;  j>=0 ; j--){
            if(chess[i][j]==true){
                return false;
            }
        }
        
        return true;
    }
}


#277
class Solution{
    
    static List<String> wordBreak(int n, List<String> dict, String s)
    {
        HashSet<String> set= new HashSet<>();
        List<String> listAns= new ArrayList<>();
        for(String str:dict){
            set.add(str);
        }
        
        word(s , set , "" ,listAns);
        
        return listAns;
        
    }
    
    static void word(String str, HashSet<String> set, String asf, List<String> listAns){
        
        if(str.length()==0){
            
            if(asf.length()>0)
            {
                listAns.add(asf.substring(0,asf.length()-1));
            }
	 	 return;
        }
        
        for(int i=0;i<str.length();i++){
            
                String left= str.substring(0,i+1);
                String right= str.substring(i+1);
                
                if(set.contains(left)){
                    word(right,set,asf+left+" ",listAns);
                }
        }
    }
}


#278
class Solution {
    
    List<String> ans= new ArrayList<>();
    
    public List<String> removeInvalidParentheses(String s) {
        
        if(s.length()==0) return ans;
        
        int count= getMin(s);
        
        remove(s,count, new HashSet<String>());
        
        return ans;
        
    }
    
    public void remove(String str, int count, HashSet<String> set){
        
        if(count==0){
            if(getMin(str)==0){
                if(!set.contains(str)){
                    ans.add(str);
                    set.add(str);
                }
            }
        }
        
        for(int i=0;i<str.length();i++){
            String left=str.substring(0,i);
            String right=str.substring(i+1);
            
            remove(left+right,count-1,set);
            
        }
        
    }
    
    public int getMin(String str){
        
        Stack<Character> st= new Stack<>();
        
        for(char c: str.toCharArray()){
            if(c=='('){
                st.push(c);
            } else if(c==')'){
                
                if(st.size()==0){
                    st.push(c);
                } else if(st.peek()=='('){
                    st.pop();
                } else{
                    st.push(c);
                }
            }
        }
        
        
        return st.size();
        
    }
}


#279
class Solution
{
    static boolean flag=false;
    static boolean SolveSudoku(int grid[][])
    {
        
        solve(grid,0,0);
        
        return flag;
    }
    
    static void solve(int grid[][], int row, int col){
        
        
        if(row==grid.length){
            printGrid(grid);
            flag=true;
            return;
        }
        
        int nr=0;
        int nc=0;
        if(col==grid.length-1){
            nr=row+1;
            nc=0;
        }
        else{
            nr=row;
            nc=col+1;
        }
        
        if(grid[row][col]!=0){
            solve(grid,nr,nc);
        }
        else{
            for(int i=1;i<=9;i++){
                if(isSafe(grid,i,row,col)){
                    grid[row][col]=i;
                    solve(grid,nr,nc);
                    grid[row][col]=0;
                }
            }
        }
    }
    
    static boolean isSafe(int grid[][], int num, int row, int col){
        
        for(int i=0;i<grid.length;i++){
            if(grid[i][col]==num)
                return false;
        }
        
        for(int j=0;j<grid.length;j++){
            if(grid[row][j]==num){
                return false;
            }
        }
        
        int r= row / 3 * 3;
        int c= col / 3 * 3;
        
        for(int i=r;i<r+3;i++){
            for(int j=c;j<c+3;j++){
                if(grid[i][j]==num)
                    return false;
            }
        }
        
        return true;
    }
    
    //Function to print grids of the Sudoku.
    static void printGrid (int grid[][])
    {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                if(grid[i][j]==0) return;
            }
        }
        
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                System.out.print(grid[i][j]+" ");
            }
        }
    }
}

#280

#281
class Solution {
    
    
    
    public List<List<String>> partition(String s) {
        
        List<List<String>> ans= new ArrayList<>();
        List<String> list= new ArrayList<>();
        parti(s,list,ans);
        
        return ans;
        
    }
    
    public void parti(String str, List<String> list,List<List<String>> ans){
        
        if(str.length()==0){
            
            if(list.size()>0)
                ans.add(list);
            
            return;
        }
        
        for(int i=0;i<str.length();i++){
            String left= str.substring(0,i+1);
            String right=str.substring(i+1);
            
            if(isPalindrome(left)){
                list.add(left);
                parti(right,list,ans);
                list.remove(list.size()-1);
            }
        }
    }
    
    public boolean isPalindrome(String str){
        
        int i=0;
        int j=str.length()-1;
        
        while(i<j){
            if(str.charAt(i)!=str.charAt(j)) 
                return false;
            i++;
            j--;
        }
        
        return true;
    }
}


#282
class Solution{
    
    static int equalPartition(int N, int arr[])
    {
        int sum=0;
        for(int ele:arr){
            sum+=ele;
        }
        
        if(sum%2==1) return 0;
        
        boolean flag=equal(arr,0,0,0);
        
        return flag==true?1:0;
        
        
    }
    
    static boolean equal(int arr[], int i, int set1, int set2){
        
        if(i==arr.length){
            if(set1==set2){
                return true;
            }
            return false;
        }
        
        boolean l1=equal(arr,i+1,set1+arr[i],set2);
        boolean l2=equal(arr,i+1,set1,set2+arr[i]);
        
        return l1||l2;
        
    }
}


#283
[EASY JUST NEED TO INCREASE THE MOVES AND BACKTRACK FOR ALL THE POSSIBLE MOVES]
public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner s= new Scanner(System.in);
        int n= s.nextInt();
        int row= s.nextInt();
        int col= s.nextInt();
        
        int chess[][]= new int[n][n];
        printKnightsTour(chess,row,col,1);
    }

    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {
        
        if(r<0 || c<0 || r>=chess.length || c>=chess.length || chess[r][c]>0)
            return;
            
        
        if(upcomingMove==chess.length*chess.length){
            chess[r][c]=upcomingMove;
            displayBoard(chess);
            chess[r][c]=0;
            return;
        }
        
        
        chess[r][c]=upcomingMove;
        printKnightsTour(chess,r-2,c+1,upcomingMove+1);
        printKnightsTour(chess,r-1,c+2,upcomingMove+1);
        printKnightsTour(chess,r+1,c+2,upcomingMove+1);
        printKnightsTour(chess,r+2,c+1,upcomingMove+1);
        printKnightsTour(chess,r+2,c-1,upcomingMove+1);
        printKnightsTour(chess,r+1,c-2,upcomingMove+1);
        printKnightsTour(chess,r-1,c-2,upcomingMove+1);
        printKnightsTour(chess,r-2,c-1,upcomingMove+1);
        chess[r][c]=0;
        
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}



#284
public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] arr = new int[scn.nextInt()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		solve(arr, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
		System.out.println(ans);
	}

	static int mindiff = Integer.MAX_VALUE;
	static String ans = "";

	public static void solve(int[] arr, int vidx, ArrayList<Integer> set1, ArrayList<Integer> set2, int soset1,
			int soset2) {
	    
	    
	    if(vidx==arr.length){
	        int min= Math.abs(soset1-soset2);
	        if(min<mindiff){
	            mindiff=min;
	            ans=set1+" "+set2;
	        }
	        return;
	    }
	    
	    
	    if(set1.size()<(arr.length+1)/2){
	        set1.add(arr[vidx]);
	        solve(arr,vidx+1,set1,set2,soset1+arr[vidx],soset2);
	        set1.remove(set1.size()-1);
	    }
	    
	    if(set2.size()<(arr.length+1)/2){
	        set2.add(arr[vidx]);
	        solve(arr,vidx+1,set1,set2,soset1,soset2+arr[vidx]);
	        set2.remove(set2.size()-1);
	    }
	    

	}

}


#285
#286
#287
class Solution
{
    static String ans;
    public static String findMaximumNum(String str, int k)
        {
           ans=str;
           find(str,0,k);
           
           return ans;
        }
        
    public static String swap(String str, int i, int j){
        String left=str.substring(0,i);
        String middle=str.substring(i+1,j);
        String right=str.substring(j+1);
        
        return left+str.charAt(j)+middle+str.charAt(i)+right;
    }
        
    public static void find(String str, int cc, int tc){
        
        if(Integer.parseInt(ans)<Integer.parseInt(str)){
            ans=str;
        }
        
        if(cc==tc){
            return;
        }
        
        for(int i=0;i<str.length();i++){
            for(int j=i+1;j<str.length();j++){
                if(str.charAt(j)>str.charAt(i)){
                    str= swap(str,i,j);
                    find(str,cc+1,tc);
                    str= swap(str,i,j);
                }
            }
        }
        
    }
}

#288
class Solution {
    public List<String> find_permutation(String S) {
        
        List<String> ans= new ArrayList<>();
        boolean isvis[]= new boolean[S.length()];
        find(S,0,"",ans,isvis);
        ans.sort(Comparator.naturalOrder());
        return ans;
    }
    
    public void find(String str, int idx, String asf,List<String> ans, boolean isVis[]){
        
        if(idx==str.length()){
            ans.add(asf);
            return;
        }
        
        for(int i=0;i<str.length();i++){
            
            if(!isVis[i])
            {
                isVis[i]=true;
                find(str,idx+1,asf+str.charAt(i),ans,isVis);
                isVis[i]=false;
            }
        }
    }
}


#289

#290
#291
#292
class Solution
{
    int flag=0;
    public boolean isKPartitionPossible(int a[], int n, int k)
    {
    		if(n<k) return false;
    		int sum=0;
    		for(int ele:a){
    		    sum+=ele;
    		}
    		isPossible(a,0,new int[k],0,k,sum);
    		
    		return flag==0?false:true;
    }
    
    public void isPossible(int arr[], int idx, int kSum[], int ssf, int k, int sum){
        
        if(idx==arr.length){
            
            if(ssf==k){
                for(int i=0;i<k;i++){
                    if(kSum[i]!=sum/k)
                        return;
                }
                flag++;
            }
            return;
        }
        
        for(int i=0;i<k;i++){
            
            if(kSum[i]==0){
                kSum[i]+=arr[idx];
                isPossible(arr,idx+1,kSum,ssf+1,k,sum);
                kSum[i]-=arr[idx];
                break;
            }
            else{
                kSum[i]+=arr[idx];
                isPossible(arr,idx+1,kSum,ssf,k,sum);
                kSum[i]-=arr[idx];
            }
            
        }
    }
}


#293


}
