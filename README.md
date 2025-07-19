# scala-data-accessor-neo4j
This is a common library used by Linked Ideal LLC. in Scala projects.
The main implementation of this project is the neo4j driver Wrapper.

[![Unit Test Action](https://github.com/toposoid/scala-data-accessor-neo4j/actions/workflows/action.yml/badge.svg)](https://github.com/toposoid/scala-data-accessor-neo4j/actions/workflows/action.yml)

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
This program is offered under a commercial and under the AGPL license.
For commercial licensing, contact us at https://toposoid.com/contact.  For AGPL licensing, see below.

AGPL licensing:
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

## Author
* Makoto Kubodera([Linked Ideal LLC.](https://linked-ideal.com/))

Thank you!
