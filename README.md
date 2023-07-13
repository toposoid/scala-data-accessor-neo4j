# scala-data-accessor-neo4j
This is a common library used by Linked Ideal LLC. in Scala projects.
The main implementation of this project is the neo4j driver Wrapper.

[![Unit Test Action](https://github.com/toposoid/scala-data-accessor-neo4j/actions/workflows/action.yml/badge.svg?branch=main)](https://github.com/toposoid/scala-data-accessor-neo4j/actions/workflows/action.yml)

## Requirements
Scala version 2.13.x,   
Sbt version 1.9.0.
Neo4j version 4.x

## Setup
Set application.conf in follows
```
local{
  neo4j {
    address = ${?GRAPHDB_HOST}
    port = "7687"
    id = ${YOUR-NEO4J_USERID}
    password = ${YOUR_NEO4J_PASSWORD}
  }
}
```
Set the Neo4J host name or IP address in the environment variable GRAPHDB_HOST.
sbt publishLocal

## Usage
For example
```scala
import com.ideal.linked.data.accessor.neo4j.Neo4JAccessor
Neo4JAccessor.executeQuery("CREATE (movie:Movie { title:\"The Matrix\",released:1997 })")
val result:Result = Neo4JAccessor.executeQueryAndReturn("MATCH (n:Movie) RETURN n")
while (result.hasNext()) {
  val record:Record = result.next()
  assert(record.get("n").get("title").asString().equals("The Matrix"))
}
Neo4JAccessor.close()
```
## Note

## License
toposoid/scala-data-accessor-neo4j is Open Source software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).

## Author
* Makoto Kubodera([Linked Ideal LLC.](https://linked-ideal.com/))

Thank you!
