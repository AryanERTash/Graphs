# Graphs

A comprehensive collection of graph algorithms and implementations in Java. This repository provides solutions to common graph problems and serves as a reference for graph-based interview questions.

## Overview

This repository contains various graph algorithms implemented in Java, focusing on different graph traversal techniques, shortest path algorithms, and other common graph operations. The code is structured to help developers understand and practice graph algorithms.

## Table of Contents

- [Data Structures](#data-structures)
- [Algorithms Implemented](#algorithms-implemented)
- [How to Use](#how-to-use)
- [Contributing](#contributing)
- [License](#license)

## Data Structures

The repository implements graphs using multiple representations:

### Adjacency List
```java
Map<Integer, List<Integer>> graph = new HashMap<>();
```

### Graph Node
```java
class Node {
    int val;
    List<Node> neighbors;
    
    Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }
    
    Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }
    
    Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
```

## Algorithms Implemented

### Graph Traversals
- **Depth-First Search (DFS)**: Explores as far as possible along each branch before backtracking
- **Breadth-First Search (BFS)**: Explores all neighbors at the present depth before moving to nodes at the next depth level

### Shortest Path Algorithms
- **Dijkstra's Algorithm**: Finds the shortest path from a source node to all other nodes
- **Bellman-Ford Algorithm**: Computes shortest paths from a single source vertex to all other vertices, handling negative edge weights
- **Floyd-Warshall Algorithm**: Finds shortest paths between all pairs of vertices

### Minimum Spanning Tree
- **Kruskal's Algorithm**: Finds a minimum spanning forest of an undirected edge-weighted graph
- **Prim's Algorithm**: Finds a minimum spanning tree for a weighted undirected graph

### Graph Properties
- Cycle detection in directed and undirected graphs
- Topological sorting
- Connectivity testing
- Strongly Connected Components (Kosaraju's algorithm)

### Advanced Algorithms
- Network flow (Ford-Fulkerson algorithm)
- Bipartite graph checking
- A* search algorithm

## How to Use

1. Clone the repository:
   ```
   git clone https://github.com/AryanERTash/Graphs.git
   ```

2. Import the code into your Java development environment (Eclipse, IntelliJ, etc.)

3. Use the provided implementations as reference or directly in your projects:
   ```java
   // Example: Creating a simple undirected graph
   Map<Integer, List<Integer>> graph = new HashMap<>();
   graph.put(0, Arrays.asList(1, 2));
   graph.put(1, Arrays.asList(0, 2));
   graph.put(2, Arrays.asList(0, 1, 3));
   graph.put(3, Arrays.asList(2));
   
   // Example: Running BFS
   List<Integer> result = bfs(graph, 0);
   ```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.