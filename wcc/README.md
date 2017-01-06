## Building

`$ mvn install`

## Launching

`$ cd dist/`

`$ ./wcc.sh --graph graph`

There are two exemplry graph prepared (files: dist/graph and dist/graph2)

* exemplary graph from dist/graph file is taken from [here](https://www.greatandlittle.com/studios/public/blowup-images/Dart/.directed_graph_sccs_m.jpg)
* exemplary graph from dist/graph2 file is taken from [here](http://phrogz.net/images/netmind/traversingdirectedgraph/samplegraph.gif) (its vertices are mapped to numbers)
    
Program output:
```
[[4], [1, 2, 3], [5, 6, 7, 8, 9, 10], [11, 12, 13, 14, 15]]
```
Program output consists of single line, which indicates two weakly connected components found.

What's more, in each line, vertices are divided into groups, which indicate strongly connected components.

So, for instance, line
```
[[4], [11, 12, 13, 14, 15]]
```
indicates weakly connected component consisting of 6 vertices, namely: 4, 11, 12, 13, 14, 15.
Those vertices are grouped into two strongly connected components: [4] and [11, 12, 13, 14, 15].

## Complexity Test

`$ ./wcc.sh --test`

Tests are done for multiple graphs with different sum of vertices and edges. Description of an idea for complexity test comes from [here](http://stackoverflow.com/a/3983413)

Exemplary test results:
```
 Final results:
	| |V|+|E|   | time      | time/(|V|+|E|)
	| 2800      | 37        | .01321
	| 5600      | 16        | .00286
	| 11200     | 27        | .00241
	| 22400     | 67        | .00299
	| 33600     | 67        | .00199
	| 44800     | 73        | .00163
	| 67200     | 133       | .00198
	| 134400    | 239       | .00178
	| 179200    | 694       | .00387
	| 358400    | 536       | .00150
	| 716800    | 1284      | .00179
	| 1433600   | 2653      | .00185
```
* First column indicates sum of vertices and edges (predicted algorithm complexity) of the graph that was used as an algorithm input.
* Second column indicates execution time. Its in millis, but unit is not important.
* Third column shows time-to-predicted-complexity ratio

As we can see, ratio converges to positive constant, which indicates that algorithm is of O(|V|+|E|) complexity.