import java.util.*;

class Solution {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordSet = new HashSet<>(wordList);
		if (!wordSet.contains(endWord))
			return new ArrayList<>();

		// Bid-dir bfs
		Map<String, List<String>> parentMap = new HashMap<>();
		Set<String> startSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();

		startSet.add(beginWord);
		endSet.add(endWord);
		boolean found = bfs(startSet, endSet, wordSet, parentMap, false);

		List<List<String>> result = new ArrayList<>();
		if (found) {
			List<String> path = new ArrayList<>();
			dfs(beginWord, endWord, parentMap, path, result);
		}
		return result;
	}

	private boolean bfs(Set<String> startSet, Set<String> endSet, Set<String> wordSet,
			Map<String, List<String>> parentMap, boolean flip) {
		if (startSet.isEmpty())
			return false;

		if (startSet.size() > endSet.size())
			return bfs(endSet, startSet, wordSet, parentMap, !flip); 

		wordSet.removeAll(startSet);
		Set<String> nextSet = new HashSet<>();
		boolean found = false;

		for (String word : startSet) {
			char[] wordArr = word.toCharArray();

			for (int i = 0; i < wordArr.length; i++) {
				char original = wordArr[i];

				for (char ch = 'a'; ch <= 'z'; ch++) {
					if (ch == original)
						continue;

					wordArr[i] = ch;
					String newWord = new String(wordArr);

					if (!wordSet.contains(newWord))
						continue;

					nextSet.add(newWord);
					String key = flip ? newWord : word;
					String val = flip ? word : newWord;

					parentMap.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
					if (endSet.contains(newWord))
						found = true;
				}
				wordArr[i] = original; 
				
			}
		}

		return found || bfs(nextSet, endSet, wordSet, parentMap, flip);
	}

	private void dfs(String word, String endWord, Map<String, List<String>> parentMap,
			List<String> path, List<List<String>> result) {
		path.add(word);
		if (word.equals(endWord)) {
			result.add(new ArrayList<>(path));
		} else {
			for (String next : parentMap.getOrDefault(word, new ArrayList<>())) {
				dfs(next, endWord, parentMap, path, result);
			}
		}
		path.remove(path.size() - 1);
	}

	// public static void main(String[] args) {
	// Solution sol = new Solution();
	// String beginWord = "hit";
	// String endWord = "cog";
	// List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log",
	// "cog");

	// List<List<String>> result = sol.findLadders(beginWord, endWord, wordList);
	// for (List<String> path : result) {
	// System.out.println(path);
	// }
	// }
}
