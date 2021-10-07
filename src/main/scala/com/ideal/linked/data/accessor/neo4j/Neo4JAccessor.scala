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

import com.typesafe.scalalogging.LazyLogging
import org.neo4j.driver.{AuthTokens, GraphDatabase, Result}
import com.ideal.linked.common.DeploymentConverter.conf

import scala.util.{Failure, Success, Try}

object Neo4JAccessor extends LazyLogging {

  val driver = GraphDatabase.driver("bolt://" + conf.getString("neo4j.address") + ":" + conf.getString("neo4j.port"), AuthTokens.basic(conf.getString("neo4j.id"), conf.getString("neo4j.password")))

  /**
   * Execute Cypher Query For Neo4J
   * @param query Cypher Query Strings
   */
  def executeQuery(query:String):Unit = Try {
    logger.trace(query)
    val session = driver.session
    session.run(query)
    session.close()
  }match {
    case Success(_) =>
    case Failure(e) => throw e
  }

  /**
   * Execute Cypher Query And Return Result For Neo4J
   * @param query　Cypher Query Strings
   * @return Object Of Result Type
   */
  def executeQueryAndReturn(query:String):Result = Try {
    logger.trace(query)
    val session = driver.session
    val result:Result = session.run(query)
    return result
  }match {
    case Failure(e) => throw e
  }

  /**
   * Delete All Data
   */
  def delete(): Unit = Try {
    val session = driver.session
    val deleteScript = """
                         |MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r;
                       """.stripMargin
    logger.trace(deleteScript)
    session.run(deleteScript)
    session.close()
  }match {
    case Success(_) =>
    case Failure(e) => throw e
  }

  /**
   * Closed Driver's Connection
   */
  def close(): Unit = Try {
    driver.session().close()
  }match {
    case Success(_) =>
    case Failure(e) => throw e
  }
}
