import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProblemB {
	static ArrayList<Integer> rowIndex = new ArrayList<Integer>(); 
	static final StdIn in = new StdIn();
	static final PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {
		int t = in.nextInt();
		for (int index = 1; index <= t; ++index) {
			out.print("Case #" + index + ": ");
			new Answer();
		}
		out.close();
	}


	public static class Answer {
		Answer() {
			int n = in.nextInt(); 
			char[][] board = new char[n][n]; 
			for (int g = 0; g < n; g++) {
				board[g] = in.next().toCharArray();
			}
			
			int minMoves = Integer.MAX_VALUE; 
			int numWays = 0; 
			
			for (int row = 0; row < board.length; row++) {
				if (!isRowBlocked(board[row]) && numDots(board[row]) < minMoves) {
					minMoves = numDots(board[row]); 
					numWays = 1; 
					rowIndex.clear(); 
					rowIndex.add(row); 
				} else if (!isRowBlocked(board[row]) && numDots(board[row]) == minMoves) {
					rowIndex.add(row); 
					numWays++; 
				}
			}
			
			for (int col = 0; col < board[0].length; col++) {
				if (!isColumnBlocked(board, col) && numDotsInCol(board, col) < minMoves) {
					minMoves = numDotsInCol(board, col); 
					numWays = 1; 
				} else if (!isColumnBlocked(board, col) && numDotsInCol(board, col) == minMoves) {
					numWays++; 
				}
			}
			
			if (numWays == 0 && minMoves == Integer.MAX_VALUE) {
				out.println("Impossible"); 
			} else {
				out.println(minMoves + " " + numWays);
			}
		}
	}

	
	
	
	
	
	public static boolean isRowBlocked(char[] arr) {
		for (int index = 0; index < arr.length; index++) {
			if (arr[index] == 'O') {
				return true; 
			}
		}
		return false; 
	}
	
	public static boolean isColumnBlocked(char[][] arr, int c) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][c] == 'O') {
				return true; 
			} 
			else if (rowIndex.contains(i)) {
				if (numDots(arr[i]) == 1 && numDotsInCol(arr, c) == 1 && arr[i][c] == '.') {
					return true; 
				}
			}
		}
		return false; 
	}
	
	public static int numDots(char[] arr) {
		int count = 0; 
		for (int index = 0; index < arr.length; index++) {
			if (arr[index] == '.') {
				count++; 
			}
		}
		return count; 
	}
	
	public static int numDotsInCol(char[][] arr, int c) {
		int count = 0; 
		for (int index = 0; index < arr.length; index++) {
			if (arr[index][c] == '.') {
				count++; 
				
			}
		}
		return count; 
	}
	
	
	
	
	
	
	static class StdIn {
		//https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdIn.html#:~:text=StdIn%20is%20a%20set%20of,delimiter%20pattern%20that%20separates%20tokens.
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public StdIn() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public StdIn(InputStream in) {
			try {
				din = new DataInputStream(in);
			} catch (Exception e) {
				throw new RuntimeException();
			}
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String next() {
			int c;
			while ((c = read()) != -1 && (c == ' ' || c == '\n' || c == '\r'))
				;
			StringBuilder s = new StringBuilder();
			while (c != -1) {
				if (c == ' ' || c == '\n' || c == '\r')
					break;
				s.append((char) c);
				c = read();
			}
			return s.toString();
		}

		public String nextLine() {
			int c;
			while ((c = read()) != -1 && (c == ' ' || c == '\n' || c == '\r'))
				;
			StringBuilder s = new StringBuilder();
			while (c != -1) {
				if (c == '\n' || c == '\r')
					break;
				s.append((char) c);
				c = read();
			}
			return s.toString();
		}

		public int nextInt() {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				ret = ret * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public int[] readIntArray(int n, int os) {
			int[] ar = new int[n];
			for (int i = 0; i < n; ++i)
				ar[i] = nextInt() + os;
			return ar;
		}

		public long nextLong() {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				ret = ret * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public long[] readLongArray(int n, long os) {
			long[] ar = new long[n];
			for (int i = 0; i < n; ++i)
				ar[i] = nextLong() + os;
			return ar;
		}

		public double nextDouble() {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				ret = ret * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (c == '.')
				while ((c = read()) >= '0' && c <= '9')
					ret += (c - '0') / (div *= 10);
			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() {
			try {
				if (bufferPointer == bytesRead)
					fillBuffer();
				return buffer[bufferPointer++];
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}