class Solution:

    def visit(self, adj,node: int, visited, result):

        if not visited[node]:
            visited[node] = True

            result.append(node)


            for V in adj[node]:
                if not visited[V]:
                    self.visit(adj, V, visited, result)

    # Function to return a list containing the DFS traversal of the graph.
    def dfsOfGraph(self, adj):
        # the given graph is undirected and connected
        n = len(adj)
        result = []

        if n == 0:
            return result

        visited = [False] * len(adj)

        self.visit(adj, 0, visited, result) # since the graph is connected we can only visit one node and all other node will be visited

        return result
