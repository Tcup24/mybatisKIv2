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
package org.apache.ibatis.builder;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class ParameterExpressionTest {

  // @Test
  // void simpleProperty() {
  // Map<String, String> result = new ParameterExpression("id");
  // Assertions.assertEquals(1, result.size());
  // Assertions.assertEquals("id", result.get("property"));
  // }
  //
  // @Test
  // void propertyWithSpacesInside() {
  // Map<String, String> result = new ParameterExpression(" with spaces ");
  // Assertions.assertEquals(1, result.size());
  // Assertions.assertEquals("with spaces", result.get("property"));
  // }
  //
  // @Test
  // void simplePropertyWithOldStyleJdbcType() {
  // Map<String, String> result = new ParameterExpression("id:VARCHAR");
  // Assertions.assertEquals(2, result.size());
  // Assertions.assertEquals("id", result.get("property"));
  // Assertions.assertEquals("VARCHAR", result.get("jdbcType"));
  // }
  //
  // @Test
  // void oldStyleJdbcTypeWithExtraWhitespaces() {
  // Map<String, String> result = new ParameterExpression(" id : VARCHAR ");
  // Assertions.assertEquals(2, result.size());
  // Assertions.assertEquals("id", result.get("property"));
  // Assertions.assertEquals("VARCHAR", result.get("jdbcType"));
  // }
  //
  // @Test
  // void expressionWithOldStyleJdbcType() {
  // Map<String, String> result = new ParameterExpression("(id.toString()):VARCHAR");
  // Assertions.assertEquals(2, result.size());
  // Assertions.assertEquals("id.toString()", result.get("expression"));
  // Assertions.assertEquals("VARCHAR", result.get("jdbcType"));
  // }
  //
  // @Test
  // void simplePropertyWithOneAttribute() {
  // Map<String, String> result = new ParameterExpression("id,name=value");
  // Assertions.assertEquals(2, result.size());
  // Assertions.assertEquals("id", result.get("property"));
  // Assertions.assertEquals("value", result.get("name"));
  // }
  //
  // @Test
  // void expressionWithOneAttribute() {
  // Map<String, String> result = new ParameterExpression("(id.toString()),name=value");
  // Assertions.assertEquals(2, result.size());
  // Assertions.assertEquals("id.toString()", result.get("expression"));
  // Assertions.assertEquals("value", result.get("name"));
  // }
  //
  // @Test
  // void simplePropertyWithManyAttributes() {
  // Map<String, String> result = new ParameterExpression("id, attr1=val1, attr2=val2, attr3=val3");
  // Assertions.assertEquals(4, result.size());
  // Assertions.assertEquals("id", result.get("property"));
  // Assertions.assertEquals("val1", result.get("attr1"));
  // Assertions.assertEquals("val2", result.get("attr2"));
  // Assertions.assertEquals("val3", result.get("attr3"));
  // }
  //
  // @Test
  // void expressionWithManyAttributes() {
  // Map<String, String> result = new ParameterExpression("(id.toString()), attr1=val1, attr2=val2, attr3=val3");
  // Assertions.assertEquals(4, result.size());
  // Assertions.assertEquals("id.toString()", result.get("expression"));
  // Assertions.assertEquals("val1", result.get("attr1"));
  // Assertions.assertEquals("val2", result.get("attr2"));
  // Assertions.assertEquals("val3", result.get("attr3"));
  // }
  //
  // @Test
  // void simplePropertyWithOldStyleJdbcTypeAndAttributes() {
  // Map<String, String> result = new ParameterExpression("id:VARCHAR, attr1=val1, attr2=val2");
  // Assertions.assertEquals(4, result.size());
  // Assertions.assertEquals("id", result.get("property"));
  // Assertions.assertEquals("VARCHAR", result.get("jdbcType"));
  // Assertions.assertEquals("val1", result.get("attr1"));
  // Assertions.assertEquals("val2", result.get("attr2"));
  // }
  //
  // @Test
  // void simplePropertyWithSpaceAndManyAttributes() {
  // Map<String, String> result = new ParameterExpression("user name, attr1=val1, attr2=val2, attr3=val3");
  // Assertions.assertEquals(4, result.size());
  // Assertions.assertEquals("user name", result.get("property"));
  // Assertions.assertEquals("val1", result.get("attr1"));
  // Assertions.assertEquals("val2", result.get("attr2"));
  // Assertions.assertEquals("val3", result.get("attr3"));
  // }
  //
  // @Test
  // void shouldIgnoreLeadingAndTrailingSpaces() {
  // Map<String, String> result = new ParameterExpression(" id , jdbcType = VARCHAR, attr1 = val1 , attr2 = val2 ");
  // Assertions.assertEquals(4, result.size());
  // Assertions.assertEquals("id", result.get("property"));
  // Assertions.assertEquals("VARCHAR", result.get("jdbcType"));
  // Assertions.assertEquals("val1", result.get("attr1"));
  // Assertions.assertEquals("val2", result.get("attr2"));
  // }
  //
  // @Test
  // void invalidOldJdbcTypeFormat() {
  // try {
  // new ParameterExpression("id:");
  // Assertions.fail();
  // } catch (BuilderException e) {
  // Assertions.assertTrue(e.getMessage().contains("Parsing error in {id:} in position 3"));
  // }
  // }
  //
  // @Test
  // void invalidJdbcTypeOptUsingExpression() {
  // try {
  // new ParameterExpression("(expression)+");
  // Assertions.fail();
  // } catch (BuilderException e) {
  // Assertions.assertTrue(e.getMessage().contains("Parsing error in {(expression)+} in position 12"));
  // }
  // }

  // KItest
//  @Test
//  void testSimplePropertyParsingTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("myProperty");
//    assertEquals("myProperty", parameterExpression.get("property"));
//  }
//
//  @Test
//  void testPropertyWithWhitespaceTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression(" propertyWithSpaces ");
//    assertEquals("propertyWithSpaces", parameterExpression.get("property"));
//  }
//
//  @Test
//  void testOldJdbcTypeFormatTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("myProperty:OLD_JDBC_TYPE");
//    assertEquals("myProperty", parameterExpression.get("property"));
//    assertEquals("OLD_JDBC_TYPE", parameterExpression.get("jdbcType"));
//  }
//
//  @Test
//  void testJdbcTypeWithExtraWhitespaceTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("myProperty : JDBC_TYPE ");
//    assertEquals("myProperty", parameterExpression.get("property"));
//    assertEquals("JDBC_TYPE", parameterExpression.get("jdbcType"));
//  }
//
//  @Test
//  void testExpressionWithOldJdbcTypeTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("(expression):OLD_TYPE");
//    assertEquals("expression", parameterExpression.get("expression"));
//    assertEquals("OLD_TYPE", parameterExpression.get("jdbcType"));
//  }
//
//  @Test
//  void testSimplePropertyWithSingleAttributeTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("property,attr=value");
//    assertEquals("property", parameterExpression.get("property"));
//    assertEquals("value", parameterExpression.get("attr"));
//  }
//
//  @Test
//  void testExpressionWithSingleAttributeTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("(expression),attr=value");
//    assertEquals("expression", parameterExpression.get("expression"));
//    assertEquals("value", parameterExpression.get("attr"));
//  }
//
//  @Test
//  void testSimplePropertyWithMultipleAttributesTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("property,attr1=value1,attr2=value2");
//    assertEquals("property", parameterExpression.get("property"));
//    assertEquals("value1", parameterExpression.get("attr1"));
//    assertEquals("value2", parameterExpression.get("attr2"));
//  }
//
//  @Test
//  void testExpressionWithMultipleAttributesTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("(expression),attr1=value1,attr2=value2");
//    assertEquals("expression", parameterExpression.get("expression"));
//    assertEquals("value1", parameterExpression.get("attr1"));
//    assertEquals("value2", parameterExpression.get("attr2"));
//  }
//
//  @Test
//  void testSimplePropertyWithOldJdbcTypeAndMultipleAttributesTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression("property:OLD_JDBC,attr1=value1,attr2=value2");
//    assertEquals("property", parameterExpression.get("property"));
//    assertEquals("OLD_JDBC", parameterExpression.get("jdbcType"));
//    assertEquals("value1", parameterExpression.get("attr1"));
//    assertEquals("value2", parameterExpression.get("attr2"));
//  }
//
//  @Test
//  void testPropertyWithWhitespaceAndMultipleAttributesTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression(" property , attr1=value1, attr2=value2 ");
//    assertEquals("property", parameterExpression.get("property"));
//    assertEquals("value1", parameterExpression.get("attr1"));
//    assertEquals("value2", parameterExpression.get("attr2"));
//  }
//
//  @Test
//  void testLeadingAndTrailingWhitespaceIgnoredTwo() {
//    ParameterExpression parameterExpression = new ParameterExpression(" property , attr=value ");
//    assertEquals("property", parameterExpression.get("property"));
//    assertEquals("value", parameterExpression.get("attr"));
//  }
//
//  @Test
//  void testInvalidJdbcTypeFormatThrowsErrorThree() { // 4ter versuch
//    assertThrows(BuilderException.class, () -> {
//      // Hier führen wir eine Eingabe ein, die den Parsingmechanismus nicht erfüllt.
//      // Dies könnte eine doppelte Trennung sein, die im Test nicht erwartet wird.
//      new ParameterExpression("property: ");
//    });
//  }
//
//  @Test
//  void testInvalidExpressionFormatThrowsErrorTwo() {
//    assertThrows(RuntimeException.class, () -> {
//      new ParameterExpression("(unmatchedParenthesis");
//    });
//  }

  // Mini
   @Test
   public void testSimplePropertyCreation() {
   ParameterExpression param = new ParameterExpression("propertyName");
   assertEquals("propertyName", param.get("property"));
   }

   @Test
   public void testPropertyWithSpaces() {
   ParameterExpression param = new ParameterExpression(" property Name ");
   assertEquals("property Name", param.get("property"));
   }

   @Test
   public void testSimplePropertyWithOldJdbcType() {
   ParameterExpression param = new ParameterExpression("propertyName: OLD_TYPE");
   assertEquals("OLD_TYPE", param.get("jdbcType"));
   }

   @Test
   public void testPropertyWithSpacesAroundJdbcType() {
   ParameterExpression param = new ParameterExpression("propertyName : OLD_TYPE ");
   assertEquals("OLD_TYPE", param.get("jdbcType"));
   }

  // --------------------
  // @Test
  // public void testExpressionWithOldJdbcType() {
  // ParameterExpression param = new ParameterExpression("(propertyName: OLD_TYPE)");
  // assertEquals("propertyName", param.get("property")); // correction here
  // assertEquals("OLD_TYPE", param.get("jdbcType")); // this remains unchanged
  // }
  //
  // @Test
  // public void testSimplePropertyWithSingleAttribute() {
  // ParameterExpression param = new ParameterExpression("propertyName: jdbcType = value");
  // assertEquals("propertyName", param.get("property")); // correction here
  // assertEquals("jdbcType", param.get("jdbcType")); // this remains unchanged
  // assertEquals("value", param.get("value")); // Erwarte die spezifische Attributzuordnung
  // }
  //
  // @Test
  // public void testExpressionWithSingleAttribute() {
  // ParameterExpression param = new ParameterExpression("(propertyName: jdbcType = value)");
  // assertEquals("propertyName", param.get("property")); // correction here
  // assertEquals("jdbcType", param.get("jdbcType")); // this remains unchanged
  // assertEquals("value", param.get("value")); // this remains unchanged
  // }
  //
  // @Test
  // public void testSimplePropertyWithMultipleAttributes() {
  // ParameterExpression param = new ParameterExpression("propertyName: jdbcType = value1, anotherAttribute = value2");
  // assertEquals("propertyName", param.get("property")); // correction here
  // assertEquals("jdbcType", param.get("jdbcType")); // this remains unchanged
  // assertEquals("value1", param.get("value1")); // this remains unchanged
  // assertEquals("anotherAttribute", param.get("anotherAttribute")); // Ensure attribute name is correct
  // assertEquals("value2", param.get("value2")); // this remains unchanged
  // }
  //
  // @Test
  // public void testExpressionWithMultipleAttributes() {
  // ParameterExpression param = new ParameterExpression("(propertyName: jdbcType = value1, anotherAttribute =
  // value2)");
  //
  // // Überprüfen, ob die Eigenschaften korrekt abgerufen werden
  // assertEquals("propertyName", param.get("property")); // property sollte auch vorhanden sein
  // assertEquals("jdbcType", param.get("jdbcType")); // Dies war der Wert im ursprünglichen Test
  // assertEquals("value1", param.get("value1")); // Hier wird `value1` für jdbcType erwartet
  // assertEquals("anotherAttribute", param.get("anotherAttribute")); // Überprüfen auf den Attributnamen
  // assertEquals("value2", param.get("value2")); // Überprüfen auf den Wert des zweiten Attributs
  // }

  // @Test
  // public void testLeadingAndTrailingSpacesIgnored() {
  // ParameterExpression param = new ParameterExpression(" propertyName : jdbcType = value ");
  // assertEquals("jdbcType", param.get("jdbcType"));
  // assertEquals("value", param.get("value"));
  // }
  //
  // @Test
  // public void testInvalidJdbcTypeFormatThrowsError() {
  // Exception exception = assertThrows(BuilderException.class, () -> {
  // new ParameterExpression("propertyName: invalidJdbcType = "); // Ungültiges Format mit dem Fehlen des Wertes
  // });
  // assertTrue(exception.getMessage().contains("Parsing error"));
  // }
  // --------------------

   @Test
   public void testSimplePropertyWithOldJdbcTypeAndMultipleAttributes() {
   ParameterExpression param = new ParameterExpression("propertyName: OLD_TYPE, attr1=value1, attr2=value2");
   assertEquals("OLD_TYPE", param.get("jdbcType"));
   assertEquals("value1", param.get("attr1"));
   assertEquals("value2", param.get("attr2"));
   }

   @Test
   public void testPropertyWithSpacesAndMultipleAttributes() {
   ParameterExpression param = new ParameterExpression(" propertyName : OLD_TYPE , attr1 = value1 , attr2 = value2 ");
   assertEquals("OLD_TYPE", param.get("jdbcType"));
   assertEquals("value1", param.get("attr1"));
   assertEquals("value2", param.get("attr2"));
   }

   @Test
   public void testInvalidExpressionFormatThrowsError() {
   Exception exception = assertThrows(BuilderException.class, () -> {
   new ParameterExpression("propertyName: , :");
   });
   assertTrue(exception.getMessage().contains("Parsing error"));
   }

  // KItest: 1-3 14/14; Mini 1-4 7/14
}
