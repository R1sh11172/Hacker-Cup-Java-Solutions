import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProblemA {
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
			String s = in.next();
			
			int[] counter = new int[s.length()];
			int index = 0;
			while (index < s.length()) {
				int count = 0;
				char c = s.charAt(index);
				boolean isCharVowel = isVowel(c);
				for (int j = 0; j < s.length(); j++) {
					if (c == s.charAt(j))
						;
					else if (isVowel(s.charAt(j)) == isCharVowel) {
						count += 2;
					} else {
						count++;
					}
				}
				counter[index] = count;
				index++;
			}
			
			if (isSameType(s))
				out.println(s.length());
			else {
				int minMoves = Integer.MAX_VALUE;
				for (int z = 0; z < counter.length; z++) {
					if (counter[z] < minMoves) {
						minMoves = counter[z];
					}
				}
				out.println(minMoves);
			}
		}
	}

	public static boolean isSameType(String s) {
		if(s.length() ==1) return false;
		boolean vowelFirst = isVowel(s.charAt(0));
		for (int i = 1; i < s.length() - 1; i++) {
			if (isVowel(s.charAt(i + 1)) != vowelFirst) {
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