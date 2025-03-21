import java.util.*;

class Solution {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

		Set<String> wordSet = new HashSet<>(wordList);
		LinkedList<String> list = new LinkedList<>();

		Map<String, Integer> wordLevelMap = new HashMap<>();

		list.offer(beginWord);
		wordLevelMap.put(beginWord, 1);

		while (!list.isEmpty()) {

			int size = list.size();

			// iterate to all elmenet in list for a laevel
			for (int item = 0; item < size; item++) {

				String currString = list.poll();
				char[] currStringArr = currString.toCharArray();

				// iterate to every index in string
				for (int charIndex = 0; charIndex < currStringArr.length; charIndex++) {

					char orig = currStringArr[charIndex];

					for (int replacement = 'a'; replacement <= 'z'; replacement++) { // find replacement character

						if (replacement == currStringArr[charIndex])
							continue;

						currStringArr[charIndex] = (char) replacement;
						String transformedString = new String(currStringArr);

						if (wordSet.contains(transformedString) && !wordLevelMap.containsKey(transformedString)) {
							list.offer(transformedString);
							wordLevelMap.put(transformedString,
									wordLevelMap.get(currString) + 1);
							wordSet.remove(transformedString);

						}

					}

					currStringArr[charIndex] = orig;
				}
			}

		}

		list.clear();
		List<List<String>> result = new ArrayList<>();

		dfs(beginWord, endWord, list, result, wordLevelMap);

		return result;

	}

	public static void printList(List<List<String>> list) {
		// for testing
		for (List<String> innerList : list)
			System.out.println(innerList);

	}

	public static void dfs(String currStr, String endString,
			LinkedList<String> list,
			List<List<String>> result,
			Map<String, Integer> wordLevelMap) {

		list.addLast(currStr);
		if (currStr.equals(endString)) {
			result.add(new ArrayList<>(list));
			list.removeLast();
			return;
		}

		char[] currStrArr = currStr.toCharArray();
		int currLevel = wordLevelMap.get(currStr);

		for (int charInd = 0; charInd < currStr.length(); charInd++) {
			char orig = currStrArr[charInd];
			for (int replace = 'a'; replace <= 'z'; replace++) {

				if (replace == currStrArr[charInd])
					continue;

				currStrArr[charInd] = (char) replace;

				String nextStr = new String(currStrArr);

				int nextLevel = wordLevelMap.getOrDefault(nextStr, -1);

				if (currLevel + 1 == nextLevel) {
					dfs(nextStr, endString, list, result, wordLevelMap);
				}

			}

			currStrArr[charInd] = orig;
		}

		list.removeLast();

	}

	// public static void main(String[] args) {
	// Solution s = new Solution();

	// String beginWord = "hit";
	// String endWord = "cog";
	// List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log",
	// "cog");

	// printList(s.findLadders(beginWord, endWord, wordList));
	// }

}