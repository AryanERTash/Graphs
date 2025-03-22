
/*
 * DijkstraAlgo using priority queue
 * 
 */
import java.util.*;

class iPair {
	int first, second;

	iPair(int first, int second) {
		this.first = first;
		this.second = second; // second is weight
	}
}

// User function Template for Java
class Solution {
	// Function to find the shortest distance of all the vertices
	// from the source vertex src.
	ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {

		Queue<iPair> queue = new PriorityQueue<>(new Comparator<iPair>() {
			@Override
			public int compare(iPair p1, iPair p2) {
				return Integer.compare(p1.second, p2.second);
			}
		});

		ArrayList<Integer> dist = new ArrayList<>();
		for (int i = 0; i < adj.size(); i++)
			dist.add((int) 1e9); // may overflow if adding to Integer.MAX_VALUE thats why use 1e9

		dist.set(src, 0);

		queue.add(new iPair(src, 0));

		while (!queue.isEmpty()) {
			iPair currIPair = queue.poll();

			if (dist.get(currIPair.first) < currIPair.second)
				continue;

			for (iPair nextPair : adj.get(currIPair.first)) {
				int upDit = currIPair.second + nextPair.second;
				if (upDit < dist.get(nextPair.first)) {
					dist.set(nextPair.first, upDit);
					queue.offer(new iPair(nextPair.first, upDit));
				}

			}
		}

		return dist;
	}
}