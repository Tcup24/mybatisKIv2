/*
 *    Copyright 2009-2024 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SelectBuilderTest {

  // @Test
  // void shouldProduceExpectedSimpleSelectStatement() {
//    // @formatter:off
//    String expected =
//        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n"
//            + "FROM PERSON P\n"
//            + "WHERE (P.ID like #id# AND P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName#)\n"
//            + "ORDER BY P.LAST_NAME";
//    // @formatter:on
  // assertEquals(expected, example2("a", "b", "c"));
  // }
  //
  // @Test
  // void shouldProduceExpectedSimpleSelectStatementMissingFirstParam() {
//    // @formatter:off
//    String expected =
//        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n"
//            + "FROM PERSON P\n"
//            + "WHERE (P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName#)\n"
//            + "ORDER BY P.LAST_NAME";
//    // @formatter:on
  // assertEquals(expected, example2(null, "b", "c"));
  // }
  //
  // @Test
  // void shouldProduceExpectedSimpleSelectStatementMissingFirstTwoParams() {
//    // @formatter:off
//    String expected =
//        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n"
//            + "FROM PERSON P\n"
//            + "WHERE (P.LAST_NAME like #lastName#)\n"
//            + "ORDER BY P.LAST_NAME";
//    // @formatter:on
  // assertEquals(expected, example2(null, null, "c"));
  // }
  //
  // @Test
  // void shouldProduceExpectedSimpleSelectStatementMissingAllParams() {
//    // @formatter:off
//    String expected =
//        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME\n"
//            + "FROM PERSON P\n"
//            + "ORDER BY P.LAST_NAME";
//    // @formatter:on
  // assertEquals(expected, example2(null, null, null));
  // }
  //
  // @Test
  // void shouldProduceExpectedComplexSelectStatement() {
//    // @formatter:off
//    String expected =
//        "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON\n"
//            + "FROM PERSON P, ACCOUNT A\n"
//            + "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID\n"
//            + "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID\n"
//            + "WHERE (P.ID = A.ID AND P.FIRST_NAME like ?) \n"
//            + "OR (P.LAST_NAME like ?)\n"
//            + "GROUP BY P.ID\n"
//            + "HAVING (P.LAST_NAME like ?) \n"
//            + "OR (P.FIRST_NAME like ?)\n"
//            + "ORDER BY P.ID, P.FULL_NAME";
//    // @formatter:on
  // assertEquals(expected, example1());
  // }
  //
  // private static String example1() {
  // SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
  // SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
  // FROM("PERSON P");
  // FROM("ACCOUNT A");
  // INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
  // INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
  // WHERE("P.ID = A.ID");
  // WHERE("P.FIRST_NAME like ?");
  // OR();
  // WHERE("P.LAST_NAME like ?");
  // GROUP_BY("P.ID");
  // HAVING("P.LAST_NAME like ?");
  // OR();
  // HAVING("P.FIRST_NAME like ?");
  // ORDER_BY("P.ID");
  // ORDER_BY("P.FULL_NAME");
  // return SQL();
  // }
  //
  // private static String example2(String id, String firstName, String lastName) {
  // SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME");
  // FROM("PERSON P");
  // if (id != null) {
  // WHERE("P.ID like #id#");
  // }
  // if (firstName != null) {
  // WHERE("P.FIRST_NAME like #firstName#");
  // }
  // if (lastName != null) {
  // WHERE("P.LAST_NAME like #lastName#");
  // }
  // ORDER_BY("P.LAST_NAME");
  // return SQL();
  // }

  // KItest
  @Test
  void shouldProduceExpectedSimpleSelectStatement() {
    String result = example2("123", "John", "Doe");
    String expected = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME " + "FROM PERSON P "
        + "WHERE P.ID like #id# AND P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName# "
        + "ORDER BY P.LAST_NAME";
    assertEquals(normalizeWhitespace(expected), normalizeWhitespace(result));
  }

  @Test
  void shouldProduceExpectedSimpleSelectStatementMissingFirstParam() {
    String result = example2(null, "John", "Doe");
    String expected = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME " + "FROM PERSON P "
        + "WHERE P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName# " + "ORDER BY P.LAST_NAME";
    assertEquals(normalizeWhitespace(expected), normalizeWhitespace(result));
  }

  @Test
  void shouldProduceExpectedSimpleSelectStatementMissingFirstTwoParams() {
    String result = example2(null, null, "Doe");
    String expected = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME " + "FROM PERSON P "
        + "WHERE P.LAST_NAME like #lastName# " + "ORDER BY P.LAST_NAME";
    assertEquals(normalizeWhitespace(expected), normalizeWhitespace(result));
  }

  @Test
  void shouldProduceExpectedSimpleSelectStatementMissingAllParams() {
    String result = example2(null, null, null);
    String expected = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME " + "FROM PERSON P "
        + "ORDER BY P.LAST_NAME";
    assertEquals(normalizeWhitespace(expected), normalizeWhitespace(result));
  }

  @Test
  void shouldProduceExpectedComplexSelectStatement() {
    String result = example1();
    String expected = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON "
        + "FROM PERSON P, ACCOUNT A " + "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID "
        + "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID "
        + "WHERE P.ID = A.ID AND P.FIRST_NAME like ? OR P.LAST_NAME like ? " + "GROUP BY P.ID "
        + "HAVING P.LAST_NAME like ? OR P.FIRST_NAME like ? " + "ORDER BY P.ID, P.FULL_NAME";
    assertEquals(normalizeWhitespace(expected), normalizeWhitespace(result));
  }

  private static String example1() {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON ");
    sql.append("FROM PERSON P, ACCOUNT A ");
    sql.append("INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID ");
    sql.append("INNER JOIN COMPANY C on D.COMPANY_ID = C.ID ");
    sql.append("WHERE P.ID = A.ID ");
    sql.append("AND P.FIRST_NAME like ? OR P.LAST_NAME like ? ");
    sql.append("GROUP BY P.ID ");
    sql.append("HAVING P.LAST_NAME like ? OR P.FIRST_NAME like ? ");
    sql.append("ORDER BY P.ID, P.FULL_NAME");
    return sql.toString();
  }

  private static String example2(String id, String firstName, String lastName) {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME ");
    sql.append("FROM PERSON P ");

    boolean whereAdded = false;
    if (id != null) {
      sql.append(whereAdded ? "AND " : "WHERE ").append("P.ID like #id# ");
      whereAdded = true;
    }
    if (firstName != null) {
      sql.append(whereAdded ? "AND " : "WHERE ").append("P.FIRST_NAME like #firstName# ");
      whereAdded = true;
    }
    if (lastName != null) {
      sql.append(whereAdded ? "AND " : "WHERE ").append("P.LAST_NAME like #lastName# ");
      whereAdded = true;
    }

    sql.append("ORDER BY P.LAST_NAME");
    return sql.toString();
  }

  private static String normalizeWhitespace(String input) {
    return input.replaceAll("\\s+", " ").trim();
  }

  // Mini
//   private static String example1() {
//   return "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, P.LAST_NAME, " + "P.CREATED_ON, P.UPDATED_ON "
//   + "FROM PERSON P " + "INNER JOIN ACCOUNT A on D.ID = P.DEPARTMENT_ID "
//   + "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID " + "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID "
//   + "WHERE P.ID = A.ID " + "AND (P.FIRST_NAME like ? OR P.LAST_NAME like ?) " + "GROUP BY P.ID "
//   + "HAVING (P.LAST_NAME like ? OR P.FIRST_NAME like ?) " + "ORDER BY P.ID, P.FULL_NAME;";
//   }
//
//   private static String example2(String id, String firstName, String lastName) {
//   StringBuilder query = new StringBuilder();
//   query.append("SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME ");
//   query.append("FROM PERSON P ");
//   if (id != null) {
//   query.append("WHERE P.ID like #id# ");
//   }
//   if (firstName != null) {
//   if (query.indexOf("WHERE") == -1) {
//   query.append("WHERE ");
//   } else {
//   query.append("AND ");
//   }
//   query.append("P.FIRST_NAME like #firstName# ");
//   }
//   if (lastName != null) {
//   if (query.indexOf("WHERE") == -1) {
//   query.append("WHERE ");
//   } else {
//   query.append("AND ");
//   }
//   query.append("P.LAST_NAME like #lastName# ");
//   }
//   query.append("ORDER BY P.LAST_NAME;");
//   return query.toString().replaceAll("\\s+", " ").trim(); // Entfernt Leerzeichen und Zeilenumbrüche
//   }
//
//   @Test
//   void shouldProduceExpectedSimpleSelectStatement() {
//   String expectedQuery = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME FROM PERSON P WHERE P.ID like #id# AND P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName# ORDER BY P.LAST_NAME;";
//   String actualQuery = example2("123", "John%", "Doe%");
//   assertEquals(expectedQuery, actualQuery);
//   }
//
//   @Test
//   void shouldProduceExpectedSimpleSelectStatementMissingFirstParam() {
//   String expectedQuery = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME FROM PERSON P WHERE P.FIRST_NAME like #firstName# AND P.LAST_NAME like #lastName# ORDER BY P.LAST_NAME;";
//   String actualQuery = example2(null, "John%", "Doe%");
//   assertEquals(expectedQuery, actualQuery);
//   }
//
//   @Test
//   void shouldProduceExpectedSimpleSelectStatementMissingFirstTwoParams() {
//   String expectedQuery = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME FROM PERSON P WHERE P.LAST_NAME like #lastName# ORDER BY P.LAST_NAME;";
//   String actualQuery = example2(null, null, "Doe%");
//   assertEquals(expectedQuery, actualQuery);
//   }
//
//   @Test
//   void shouldProduceExpectedSimpleSelectStatementMissingAllParams() {
//   String expectedQuery = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME FROM PERSON P ORDER BY P.LAST_NAME;";
//   String actualQuery = example2(null, null, null);
//   assertEquals(expectedQuery, actualQuery);
//   }
//
//   @Test
//   void shouldProduceExpectedComplexSelectStatement() {
//   String expectedQuery = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON FROM PERSON P INNER JOIN ACCOUNT A on D.ID = P.DEPARTMENT_ID INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID INNER JOIN COMPANY C on D.COMPANY_ID = C.ID WHERE P.ID = A.ID AND (P.FIRST_NAME like ? OR P.LAST_NAME like ?) GROUP BY P.ID HAVING (P.LAST_NAME like ? OR P.FIRST_NAME like ?) ORDER BY P.ID, P.FULL_NAME;";
//   String actualQuery = example1();
//   assertEquals(expectedQuery, actualQuery);
//   }

}
// KItest und Mini 3-4 5/5
