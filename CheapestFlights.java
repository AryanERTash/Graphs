import java.util.*;

class Solution {
	class DestPricePair {
		int to, price;

		DestPricePair() {
		}

		DestPricePair(int to, int price) {
			this.to = to;
			this.price = price;
		}
	}

	class Tuple {
		int city, stops, price;

		Tuple() {
		}

		Tuple(int city, int stops, int price) {
			this.city = city;
			this.stops = stops;
			this.price = price;
		}
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

		ArrayList<ArrayList<DestPricePair>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		for (int[] ticket : flights)
			adj.get(ticket[0]).add(new DestPricePair(ticket[1], ticket[2])); //

		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		Queue<Tuple> queue = new ArrayDeque<>();
		queue.offer(new Tuple(src, 0, 0));

		while (!queue.isEmpty()) {
			Tuple tpl = queue.poll();

			if (tpl.stops > k)
				continue;

			for (DestPricePair dpPairNext : adj.get(tpl.city)) {
				int nextPrice = tpl.price + dpPairNext.price;
				if (nextPrice < dist[dpPairNext.to] && tpl.stops == k) {
					dist[dpPairNext.to] = nextPrice;
					
				} else if(nextPrice <  dist[dpPairNext.to] && tpl.stops < k) {
					dist[dpPairNext.to] = nextPrice;
					queue.offer(new Tuple(dpPairNext.to, tpl.stops+1, nextPrice));
				}

			}
		}

		return (dist[dst] != Integer.MAX_VALUE) ? dist[dst] : -1;

	}
}