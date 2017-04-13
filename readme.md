Given task is a classical problem of finding shortest path between two vertices of an oriented, weighted graph.
Furthermore, in given domain, graph should not contain any loops with negative weight which simplifies the task a bit.
Representation of a task in *.csv files looks pretty much like an adjacency list, so I had to only complete it with 
vertices without any connections to be able to work with it. 

There are number of well-know algorithms to trace paths in different types of graphs and among them I choose
Bellman-Ford algorithm. It is simple and powerful enough for the task assuming that there would be no negative loops in 
graphs. With that assumption made, the algorithm was even implemented only partially as the part of checking graph for
negative loops was skipped.

Bellman-Ford algorithm traces through all edges of a graph times the number of vertices and incrementally improves
shortest path to each vertex from the starting point. Each vertex keeps a track of it's predecessor in a shortest path 
and the weight of all previous edges. Given that, after algorithm is finished we have a shortest path in a graph from 
starting point through all the vertices of a graph. Given, that we know shortest path from s to vN, which is 
{s, v0, v1, .... , vN-1, vN}, the part of it, {v1, .... , vN-1} would also be the shortest path from v1 to vN-1. Hence,
after tracing shortest path through entire graph, we can state that any part of it would be the shortest path to
particular vertex from the same source.

The application is implemented without any third-party libraries, apart from JUnit. Code is fairly primitive, it
doesn't have any proper logging and outputs messages to System.out or System.error. For the sake of operating with 
infinite numbers, all numeric variables are the type of Double. The Object-oriented approach is not strict enough from
my point of view, but I had to stop myself from overdoing this)

## USAGE

To run the application you should have Oracle JRE 1.7 or higher and Maven installed.
Git is not required as you can just download *.zip archive from GitHub
There are two ways of testing the application working:

1. in project directory, run ```mvn clean install -DskipTests```, after that, 
   ```java -jar target/volkovskyi-1.0-SNAPSHOT.jar```. Application takes one argument, which is path to directory 
   with *.csv files for test. If no argument given, it will look into working directory. When input files found, 
   application will print scenarios with calculated costs of shipment included.
   Skipping tests to build an application is required unless you have a directory configured in unit test present, 
   more on that in option 2.
2. in project directory, run ```mvn clean test```. Maven should run single unit test in a package, which will look into
   directory "/tmp/allpago" and validate code against every scenario in every *.csv file it finds. Unfortunately, to 
   change the directory, you'll have to go into the code and change it there in parameters section. It was done that
   way for the purpose of unit test being a bundle on itself and not dependant from outer inputs.