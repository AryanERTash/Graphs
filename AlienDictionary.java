import java.util.*;

class Solution {
	public static String getAlienLanguage(String[] dictionary, int k) {
		List<List<Integer>> adj = new ArrayList<>();

		int[] inDegree = new int[26];
		for (int i = 0; i < 26; i++) {
			adj.add(new ArrayList<>());
			inDegree[i] = -1;
		}

		for (int i = 0; i < dictionary.length-1; i++) {
			String s1 = dictionary[i];
			String s2 = dictionary[i + 1];

			if (s1.length() > s2.length() && s1.startsWith(s2))
				return "";

			for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {

				int ch1 = s1.charAt(j) - 'a';
				int ch2 = s2.charAt(j) - 'a';

				if (ch1 != ch2) {
					adj.get(ch1).add(ch2);

					if (inDegree[ch1] == -1)
						inDegree[ch1] = 0;
					if (inDegree[ch2] == -1)
						inDegree[ch2] = 0;

					inDegree[ch2]++;

					break;
				}
			}

		}

		Queue<Integer> queue = new LinkedList<>();
		int letterCount = 0;

		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);

			if (inDegree[i] != -1)
				letterCount++;
		}



		StringBuffer sb = new StringBuffer();


		while(!queue.isEmpty()) {
			int node = queue.poll();
			sb.append((char)(node + 'a'));

			for (int next : adj.get(node)) {
				if(--inDegree[next] == 0)
					queue.offer(next);
			}
		}


		return sb.length() == letterCount ? sb.toString() : "";
	}
}