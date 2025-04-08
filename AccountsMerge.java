
/*
 * Leetcode: 721. Accounts Merge
*/
import java.util.*;

class DisjointSet {
	int parent[], rank[];

	DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;

		}
	}

	int findUltimateParent(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = findUltimateParent(parent[node]); // Path compression
	}

	public void union(int u, int v) {
		int pu = findUltimateParent(u);
		int pv = findUltimateParent(v);

		if (pu == pv)
			return; // Already in the same set

		if (rank[pu] == rank[pv]) {
			parent[pu] = pv;
			rank[pv]++; // Increase rank of the new root
		} else if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else {
			parent[pv] = pu;
		}
	}

}

class Solution {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		int n = accounts.size();

		Map<String, Integer> emailMap = new HashMap<>();
		DisjointSet djSet = new DisjointSet(n);

		for (int i = 0; i < n; i++) {

			List<String> record = accounts.get(i);

			for (int j = 1; j < record.size(); j++) {

				String email = record.get(j);

				if (!emailMap.containsKey(email)) {
					emailMap.put(email, i);
				} else {
					djSet.union(emailMap.get(email), i);
				}

			}
		}

		List<List<String>> tempList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			tempList.add(new ArrayList<>());
		}

		for (Map.Entry<String, Integer> emailMapEntry : emailMap.entrySet()) {

			int parent = djSet.findUltimateParent(emailMapEntry.getValue());

			tempList.get(parent).add(emailMapEntry.getKey());

		}

		List<List<String>> mergedList = new ArrayList<>();

		for (int i = 0; i < tempList.size(); i++) {

			if (tempList.get(i).size() == 0)
				continue;

			Collections.sort(tempList.get(i));

			List<String> mergedRecord = new ArrayList<>();
			mergedRecord.add(accounts.get(i).get(0));
			mergedList.add(mergedRecord);
			for (String s : tempList.get(i))
				mergedRecord.add(s);

		}

		return mergedList;

	}
}