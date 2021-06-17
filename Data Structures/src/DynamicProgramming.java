import java.util.*;
import java.io.*;

public class DynamicProgramming {

	// Print Maze Paths
	public class PrintMazePaths {

		public void main(String[] args) {

			Scanner s = new Scanner(System.in);

			int n = s.nextInt();
			int m = s.nextInt();

			printMazePaths(0, 0, n - 1, m - 1, "");

		}

		// sr - source row
		// sc - source column
		// dr - destination row
		// dc - destination column

		// recursion
		public void printMazePaths(int sr, int sc, int dr, int dc, String psf) {

			if (sr < 0 || sr > dr || sc < 0 || sc > dc) {
				return;
			}

			if (sr == dr && sc == dc) {
				System.out.println(psf);
				return;
			}

			printMazePaths(sr, sc + 1, dr, dc, psf + "h");
			printMazePaths(sr + 1, sc, dr, dc, psf + "v");

		}

	}

	// maze path with jumps
	public class MPJ {

		public void main(String[] args) throws Exception {

			Scanner s = new Scanner(System.in);

			int n = s.nextInt();
			int m = s.nextInt();

			printMazePaths(0, 0, n - 1, m - 1, "");
		}

		// recursion
		public void printMazePaths(int sr, int sc, int dr, int dc, String psf) {

			if (sr < 0 || sr > dr || sc < 0 || sc > dc) {
				return;
			}

			if (sr == dr && sc == dc) {
				System.out.println(psf);
				return;
			}

			for (int j = 1; j <= dc - sc; j++) {
				printMazePaths(sr, sc + j, dr, dc, psf + "h" + j);
			}

			for (int j = 1; j <= dr - sr; j++) {
				printMazePaths(sr + j, sc, dr, dc, psf + "v" + j);
			}

			for (int j = 1; j <= dr - sr && j <= dc - sc; j++) {
				printMazePaths(sr + j, sc + j, dr, dc, psf + "d" + j);
			}

		}

	}

	// boardPath_DP [no. of ways to reach end of the array with given dice (1,6)] -
	// PENDING

	// 70. Climbing Stairs
	class ClimbingStairs {
		public int climbStairs(int n) {

			if (n == 0)
				return 1;
			if (n == 1)
				return 1;

			return climbStairs(n - 1) + climbStairs(n - 2);

		}

		public int climbStairsDP(int n) {

			int dp[] = new int[n + 1];
			dp[0] = 1;
			dp[1] = 1;
			return climbTab(n, dp);
		}

		public int climbMem(int n, int[] dp) {

			if (dp[n] != 0) {
				return dp[n];
			}

			dp[n] = climbMem(n - 1, dp) + climbMem(n - 2, dp);

			return dp[n];
		}

		public int climbTab(int n, int dp[]) {

			if (n == 1)
				return dp[n];

			for (int i = 2; i <= n; i++) {
				dp[i] = dp[i - 1] + dp[i - 2];
			}

			return dp[n];
		}
	}

	// minCostClimbingStairs leetcode 746
	class MinCost {
		public int minCostClimbingStairs(int[] cost) {

			int dp[] = new int[cost.length + 1];
			Arrays.fill(dp, -1);
			dp[0] = 0;
			dp[1] = 0;

			// return minCostRec(cost,cost.length);
			// return minCostMemo(cost,cost.length,dp);
			return minCostTab(cost, cost.length, dp);
		}

		public int minCostRec(int cost[], int n) {

			if (n <= 1)
				return 0;

			int minOne = cost[n - 1] + minCostRec(cost, n - 1);
			int minTwo = cost[n - 2] + minCostRec(cost, n - 2);

			return Math.min(minOne, minTwo);
		}

		public int minCostMemo(int cost[], int n, int dp[]) {

			if (n <= 1)
				return 0;

			if (dp[n] != -1) {
				return dp[n];
			}

			int minOne = cost[n - 1] + minCostMemo(cost, n - 1, dp);
			int minTwo = cost[n - 2] + minCostMemo(cost, n - 2, dp);

			dp[n] = Math.min(minOne, minTwo);

			return dp[n];

		}

		public int minCostTab(int cost[], int n, int dp[]) {

			if (n <= 1)
				return 0;

			for (int i = 2; i <= n; i++) {
				int minOne = cost[i - 1] + dp[i - 1];
				int minTwo = cost[i - 2] + dp[i - 2];

				dp[i] = Math.min(minOne, minTwo);
			}

			return dp[n];

		}
	}

	// minCostClimbingStairs with given moves
	public class minCostClimbingStairs {

		public void main(String[] args) throws Exception {

			Scanner s = new Scanner(System.in);

			int n = s.nextInt();

			int stairs[] = new int[n];

			for (int i = 0; i < stairs.length; i++) {
				stairs[i] = s.nextInt();
			}

			int dp[] = new int[n];
			// Arrays.fill(dp,-1);
			int ans = climbStairsTab(stairs, 0, dp);

			if (ans != Integer.MAX_VALUE)
				System.out.println(ans);

			else
				System.out.println("null");

		}

		// Recursion
		public int climbStairsRec(int stairs[], int idx) {

			if (idx >= stairs.length)
				return Integer.MAX_VALUE;

			if (idx == stairs.length - 1)
				return 0;

			if (stairs[idx] == 0)
				return Integer.MAX_VALUE;

			int count = Integer.MAX_VALUE;
			for (int i = 1; i <= stairs[idx]; i++) {
				count = Math.min(count, climbStairsRec(stairs, idx + i));
			}

			if (count != Integer.MAX_VALUE)
				return count + 1;

			return Integer.MAX_VALUE;

		}

		// Memoization
		public int climbStairsMemo(int stairs[], int idx, int dp[]) {

			if (idx >= stairs.length)
				return Integer.MAX_VALUE;

			if (idx == stairs.length - 1)
				return dp[idx] = 0;

			if (stairs[idx] == 0)
				return Integer.MAX_VALUE;

			if (dp[idx] != -1)
				return dp[idx];

			int count = Integer.MAX_VALUE;
			for (int i = 1; i <= stairs[idx]; i++) {
				count = Math.min(count, climbStairsMemo(stairs, idx + i, dp));
			}

			if (count != Integer.MAX_VALUE)
				return dp[idx] = count + 1;

			return dp[idx] = Integer.MAX_VALUE;

		}

		// tabulation
		public int climbStairsTab(int stairs[], int idx, int dp[]) {

			for (int j = stairs.length - 2; j >= 0; j--) {

				if (stairs[j] == 0) {
					dp[j] = Integer.MAX_VALUE;
					continue;
				}

				int count = Integer.MAX_VALUE;
				for (int i = 1; i <= stairs[j]; i++) {
					if (j + i < stairs.length)
						count = Math.min(count, dp[j + i]);
				}

				if (count != Integer.MAX_VALUE)
					dp[j] = count + 1;

			}

			return dp[0];
		}

	}

	// 64. Minimum Path Sum
	class MinPathSum {
		public int minPathSum(int[][] grid) {

			if (grid.length == 0)
				return 0;

			// return minPathRec(grid,0,0);
			// int dp[][]= new int[grid.length][grid[0].length];
			// return minPathMemo(grid,0,0,dp);
			return minPathTab(grid);

		}

		public int minPathRec(int grid[][], int row, int col) {

			if (row == grid.length - 1 && col == grid[0].length - 1) {
				return grid[row][col];
			}

			int dir[][] = { { 0, 1 }, { 1, 0 } };

			int min = Integer.MAX_VALUE;
			for (int d[] : dir) {
				int r = row + d[0];
				int c = col + d[1];

				if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length)
					min = Math.min(min, grid[row][col] + minPathRec(grid, r, c));
			}

			return min;
		}

		public int minPathMemo(int grid[][], int row, int col, int dp[][]) {

			if (row == grid.length - 1 && col == grid[0].length - 1) {
				return dp[row][col] = grid[row][col];
			}

			if (dp[row][col] != 0)
				return dp[row][col];

			int dir[][] = { { 0, 1 }, { 1, 0 } };

			int min = Integer.MAX_VALUE;
			for (int d[] : dir) {
				int r = row + d[0];
				int c = col + d[1];

				if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length)
					min = Math.min(min, grid[row][col] + minPathMemo(grid, r, c, dp));
			}

			dp[row][col] = min;
			return min;
		}

		public int minPathTab(int grid[][]) {

			int m = grid.length;
			int n = grid[0].length;
			int dp[][] = new int[m][n];

			for (int i = m - 1; i >= 0; i--) {
				for (int j = n - 1; j >= 0; j--) {

					if (i == m - 1 && j == n - 1) {
						dp[i][j] = grid[i][j];
						continue;
					}

					int dir[][] = { { 0, 1 }, { 1, 0 } };

					int min = Integer.MAX_VALUE;
					for (int d[] : dir) {
						int r = i + d[0];
						int c = j + d[1];

						if (r >= 0 && r < m && c >= 0 && c < n)
							min = Math.min(min, grid[i][j] + dp[r][c]);
					}

					dp[i][j] = min;

				}
			}

			return dp[0][0];

		}
	}

	// 1219. Path with Maximum Gold - PENDING

	// Friends Pairing
	public class FriendsPairing {

		public void main(String[] args) throws Exception {

			Scanner s = new Scanner(System.in);
			int n = s.nextInt();

			// int ans= friendsPairRec(n);
			int ans = friendsPairTab(n, new int[n + 1]);

			System.out.println(ans);
		}

		public int friendsPairRec(int n) {

			if (n <= 2)
				return n;

			int count = friendsPairRec(n - 1) + (n - 1) * friendsPairRec(n - 2);

			return count;
		}

		public int friendsPairMemo(int n, int dp[]) {

			if (n <= 2)
				return dp[n] = n;

			if (dp[n] != 0)
				return dp[n];

			dp[n] = friendsPairMemo(n - 1, dp) + (n - 1) * friendsPairMemo(n - 2, dp);

			return dp[n];
		}

		public int friendsPairTab(int n, int dp[]) {

			for (int i = 0; i <= n; i++) {

				if (i <= 2) {
					dp[i] = i;
					continue;
				}

				dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
			}

			return dp[n];
		}

	}

	// Partition a set into k subsets
	// Total count = k * S(n-1, k) + S(n-1, k-1). [Explaination in handwritten
	// notes]

}
