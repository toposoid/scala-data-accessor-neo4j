/*
 * Copyright (C) 2025  Linked Ideal LLC.[https://linked-ideal.com/]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
