'''
GFG: BFS of a graph
'''


from typing import *
from collections import deque

# User function Template for python3


class Solution:
    # Function to return Breadth First Traversal of given graph.
    def visit(self, node: int, adj: List[List[int]], result: List[int], visited: set):
        q = deque([node])

        while q:
            current: int = q.popleft()

            if current not in visited:
                visited.add(current)
                result.append(current)

                if current < len(adj):
                    for next in adj[current]:
                        q.append(next)

    def bfsOfGraph(self, adj: List[List[int]]) -> List[int]:
        if adj is None or len(adj) == 0:
            return []

        result = []
        visited = set()  # a set or an array can be used

        for node in range(len(adj)):
            if node not in visited:
                self.visit(node, adj, result, visited)
                break  # since it is undirected

        return result
