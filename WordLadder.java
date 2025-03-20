/*
 * Leetcode: https://leetcode.com/problems/word-ladder/
 */


import java.util.*;

class Solution {
	class WordStepPair {
		String word;
		int step;

		WordStepPair(String str, int nStep) {
			this.word = str;
			this.step = nStep;
		}
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		Set<String> wordSet = new HashSet<>(wordList);

		if (!wordSet.contains(endWord))
			return 0;

		Queue<WordStepPair> queue = new ArrayDeque<>();

		queue.offer(new WordStepPair(beginWord, 1));

		while (!queue.isEmpty()) {

			WordStepPair wsPair = queue.poll();

			char[] wordArr = wsPair.word.toCharArray();

			for (int i = 0; i < wordArr.length; i++) {

				char originalChar = wordArr[i];

				for (char j = 'a'; j <= 'z'; j++) {
					if (wordArr[i] == j)
						continue;

					wordArr[i] = j;

					String newWordString = new String(wordArr);

					if (newWordString.equals(endWord)) {
						return wsPair.step + 1;
					}

					if (wordSet.contains(newWordString)) {

						wordSet.remove(newWordString);

						queue.add(new WordStepPair(newWordString, wsPair.step + 1));

					}
				}
				wordArr[i] = originalChar;
			}
		}
		return 0;
	}
}
