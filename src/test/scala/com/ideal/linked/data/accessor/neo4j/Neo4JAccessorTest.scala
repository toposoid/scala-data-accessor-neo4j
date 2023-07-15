/*
 * Copyright 2021 Linked Ideal LLC.[https://linked-ideal.com/]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ideal.linked.data.accessor.neo4j

import org.neo4j.driver.{Record, Result}

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll}
import org.scalatest.flatspec.AnyFlatSpec

class Neo4JAccessorTest extends AnyFlatSpec  with BeforeAndAfter with BeforeAndAfterAll {
  val re = "UNION ALL\n$".r
  before {
    Neo4JAccessor.delete()
  }

  after {
    Neo4JAccessor.delete()
  }

  override def beforeAll(): Unit = {
    Neo4JAccessor.delete()
  }

  override def afterAll(): Unit = {
    Neo4JAccessor.delete()
  }

  "Create Node" should "Search Created Node" in {
    Neo4JAccessor.executeQuery("CREATE (movie:Movie { title:\"The Matrix\",released:1997 })")
    val result:Result = Neo4JAccessor.executeQueryAndReturn("MATCH (n:Movie) RETURN n")
    while (result.hasNext()) {
      val record:Record = result.next()
      assert(record.get("n").get("title").asString().equals("The Matrix"))
    }
    Neo4JAccessor.close()
  }

  "Create Relation" should "Search Created Relation" in {
    Neo4JAccessor.executeQuery("CREATE (:Movie { title:\"Pirates of the Caribbean\",released:2003 })\n CREATE (:Movie { title:\"Charlie and the Chocolate Factory\",released:2005 })")
    Neo4JAccessor.executeQuery("MATCH (s:Movie {released: 2003}), (d:Movie {released: 2005}) MERGE (s)<-[:MovieEdge {actor:'Johnny Depp'}]-(d)")
    val result:Result = Neo4JAccessor.executeQueryAndReturn("MATCH (d:Movie{released:2003})-[e:MovieEdge]-(s:Movie{released:2005}) RETURN e")
    while (result.hasNext()) {
      val record:Record = result.next()
      assert(record.get("e").get("actor").asString().equals("Johnny Depp"))
    }
    Neo4JAccessor.close()
  }

  "Create Node And Relation" should "Search Created Relation" in {

    val query1 =
    """|MERGE (:PremiseNode {nodeName: "黒", nodeId:'eb82e130-cb89-464c-9b28-164d37eb2912-1', propositionId:'9b59028e-f878-4afe-af12-91a0e8a57138', currentId:'1', parentId:'-1', isMainSection:'true', surface:"黒髪ではない。", normalizedName:"黒", dependType:'D', caseType:'文末', namedEntity:'', rangeExpressions:'{"":{}}', categories:'{"黒":"色","髪":"動物-部位"}', domains:'{"":""}', isDenialWord:'true',isConditionalConnection:'false',normalizedNameYomi:'くろかみ',surfaceYomi:'くろかみではない。',modalityType:'-',logicType:'-',lang:'ja_JP', extentText:'{}' })
       |MERGE (:SynonymNode {nodeId:'黒色_eb82e130-cb89-464c-9b28-164d37eb2912-1', nodeName:'黒色', propositionId:'9b59028e-f878-4afe-af12-91a0e8a57138'})
       |UNION ALL
       |MATCH (s:SynonymNode {nodeId: '黒色_eb82e130-cb89-464c-9b28-164d37eb2912-1'}), (d:PremiseNode {nodeId: 'eb82e130-cb89-464c-9b28-164d37eb2912-1'}) MERGE (s)-[:SynonymEdge {similality:0.5}]->(d)
       |UNION ALL
       |MERGE (:PremiseNode {nodeName: "-", nodeId:'eb82e130-cb89-464c-9b28-164d37eb2912-0', propositionId:'9b59028e-f878-4afe-af12-91a0e8a57138', currentId:'0', parentId:'1', isMainSection:'false', surface:"Ｂは", normalizedName:"-", dependType:'D', caseType:'未格', namedEntity:'', rangeExpressions:'{"":{}}', categories:'{"":""}', domains:'{"":""}', isDenialWord:'false',isConditionalConnection:'false',normalizedNameYomi:'',surfaceYomi:'Ｂは',modalityType:'-',logicType:'-',lang:'ja_JP', extentText:'{}' })
       |MERGE (:SynonymNode {nodeId:'‐_eb82e130-cb89-464c-9b28-164d37eb2912-0', nodeName:'‐', propositionId:'9b59028e-f878-4afe-af12-91a0e8a57138'})
       |UNION ALL
       |MATCH (s:SynonymNode {nodeId: '‐_eb82e130-cb89-464c-9b28-164d37eb2912-0'}), (d:PremiseNode {nodeId: 'eb82e130-cb89-464c-9b28-164d37eb2912-0'}) MERGE (s)-[:SynonymEdge {similality:0.5}]->(d)
       |UNION ALL
       |MERGE (:SynonymNode {nodeId:'～_eb82e130-cb89-464c-9b28-164d37eb2912-0', nodeName:'～', propositionId:'9b59028e-f878-4afe-af12-91a0e8a57138'})
       |UNION ALL
       |MATCH (s:SynonymNode {nodeId: '～_eb82e130-cb89-464c-9b28-164d37eb2912-0'}), (d:PremiseNode {nodeId: 'eb82e130-cb89-464c-9b28-164d37eb2912-0'}) MERGE (s)-[:SynonymEdge {similality:0.5}]->(d)
       |UNION ALL
       |MERGE (:SynonymNode {nodeId:'–_eb82e130-cb89-464c-9b28-164d37eb2912-0', nodeName:'–', propositionId:'9b59028e-f878-4afe-af12-91a0e8a57138'})
       |UNION ALL
       |MATCH (s:SynonymNode {nodeId: '–_eb82e130-cb89-464c-9b28-164d37eb2912-0'}), (d:PremiseNode {nodeId: 'eb82e130-cb89-464c-9b28-164d37eb2912-0'}) MERGE (s)-[:SynonymEdge {similality:0.5}]->(d)
       |UNION ALL
       |MERGE (:SynonymNode {nodeId:'－_eb82e130-cb89-464c-9b28-164d37eb2912-0', nodeName:'－', propositionId:'9b59028e-f878-4afe-af12-91a0e8a57138'})
       |UNION ALL
       |MATCH (s:SynonymNode {nodeId: '－_eb82e130-cb89-464c-9b28-164d37eb2912-0'}), (d:PremiseNode {nodeId: 'eb82e130-cb89-464c-9b28-164d37eb2912-0'}) MERGE (s)-[:SynonymEdge {similality:0.5}]->(d)
       |UNION ALL
       |"""

    Neo4JAccessor.executeQuery(re.replaceAllIn(query1.toString().stripMargin, ""))
    val result: Result = Neo4JAccessor.executeQueryAndReturn("MATCH (s:PremiseNode {nodeId: 'eb82e130-cb89-464c-9b28-164d37eb2912-0'}), (d:PremiseNode {nodeId: 'eb82e130-cb89-464c-9b28-164d37eb2912-1'}) RETURN s, d")
    while (result.hasNext()) {
      val record: Record = result.next()
      assert(record.get("s").get("nodeId").asString().equals("eb82e130-cb89-464c-9b28-164d37eb2912-0"))
    }
    Neo4JAccessor.close()

  }
}
