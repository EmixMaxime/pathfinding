# Java Pathfinding
This projet implements several pathfinding and graph algorithms in Java.
But the aim is to be language agnostic, If you're a JavaScript developer, you could understand the projet (at least, this is the goal). The main focus is to have a strong OO (oriented object) architecture! A rich documentation will be available soon explaining it.

[Here is a little video presenting the UI.](https://youtu.be/axH5D7__i-4)
You cannot choose the algorithm because it miss the feature. It's by default [`BreadthFirst`](https://github.com/mxmaxime/pathfinding/blob/master/src/main/java/fr/mx/pathfinding/GUIController.java#L131).

## Why this projet?
Okay, we can find a lot of algorithms / library that does almost the same thing, so why do I reinvent the wheel?


I found a lot of articles / code talking about these algorithms. But, something annoys me a lot, that's the code quality and reusability, I couldn't find any good code on the Internet so I made mine.

## Algorithms
### Informed search algorithm
- [A*](https://en.wikipedia.org/wiki/A*_search_algorithm): like Dijkstra but achieves better performance by using heuristics to guide its search (that's why it's informed).

### Undirected graphs
- [BFS](https://en.wikipedia.org/wiki/Breadth-first_search): explore nearest successors first, then widen the search.
- [DFS](https://en.wikipedia.org/wiki/Depth-first_search): explore a graph by going as far as possible, then backtrack.

## Status
I'm working on documentation. I want this project to be very accessible. All of my choices, my errors, difficulties will be written so my work can be used to study.
