import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProblemA2 {
	static final StdIn in = new StdIn();
	static final PrintWriter out = new PrintWriter(System.out);
	static ArrayList<Integer> list = new ArrayList<Integer>(); 
	static int minChanges = 0; 
	

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
			String s = in.next(); 
			int k = in.nextInt(); 
			String[] arr = new String[k];
			
			for (int i = 0; i < k; i++) {
				arr[i] = in.next();
			}
			if (isConsistent(s)) {
				out.println(0);
			}
			else {
				String temp = s; 
				
				for (int i = 0; i < arr.length; i++) {
					temp = replace(temp, arr[i]); 
				}
				out.println("Min Changes: " + minChanges);
				out.println(isImpossible(temp, arr)? -1 : minChanges);
			
			}
			
			
		}
	}
	
	
	public static String replace(String s, String arrVal) {
	//	String str = s; 
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == arrVal.charAt(0)) {
				minChanges++; 
			//	s = s.substring(0, s.indexOf(arrVal.charAt(0))) + arrVal.charAt(1) + s.substring(s.indexOf(arrVal.charAt(0)) + 1);
				s = s.replace(s.charAt(i), arrVal.charAt(1));
				//out.println(str);
			}
		}
		out.println(s);
		return s; 
	}
	
	
	public static boolean isImpossible(String s, String[] arr) {
		//out.print(s);
		boolean b = true; 
		int index = 0; 
		while (index < arr.length && b) {
			if (!isConsistent(s) && s.indexOf(arr[index].charAt(0)) == -1) b = true; 
			index++; 
		}
//		for (int i = 0; i < arr.length; i++) {
//			if (!isConsistent(s) && s.indexOf(arr[i].charAt(0)) == -1) return true; 
//		}
		return b; 
	}
	
	public static boolean isConsistent(String s) {
		if (s.length() == 1)
			return true;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isVowel(char c) {
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			return true;
		return false;
	}

	static class StdIn {
		// https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdIn.html#:~:text=StdIn%20is%20a%20set%20of,delimiter%20pattern%20that%20separates%20tokens.
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