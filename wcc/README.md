## Building

`$ mvn install`

## Launching

`$ ./dist/wcc.sh --graph dist/graph`

* exemplaty graph from dist/graph file is taken from [here](https://www.greatandlittle.com/studios/public/blowup-images/Dart/.directed_graph_sccs_m.jpg) except for that (1)->(1) loop edge

## Complexity Test

`$ ./dist/wcc.sh --test`

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