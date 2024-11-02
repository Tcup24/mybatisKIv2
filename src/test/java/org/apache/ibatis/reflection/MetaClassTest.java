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
package org.apache.ibatis.reflection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.ibatis.domain.misc.RichType;
import org.apache.ibatis.domain.misc.generics.GenericConcrete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MetaClassTest {

  // @Test
  // void shouldTestDataTypeOfGenericMethod() {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(GenericConcrete.class, reflectorFactory);
  // assertEquals(Long.class, meta.getGetterType("id"));
  // assertEquals(Long.class, meta.getSetterType("id"));
  // }
  //
  // @Test
  // void shouldThrowReflectionExceptionGetGetterType() {
  // try {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(RichType.class, reflectorFactory);
  // meta.getGetterType("aString");
  // org.junit.jupiter.api.Assertions.fail("should have thrown ReflectionException");
  // } catch (ReflectionException expected) {
  // assertEquals(
  // "There is no getter for property named \'aString\' in \'class org.apache.ibatis.domain.misc.RichType\'",
  // expected.getMessage());
  // }
  // }
  //
  // @Test
  // void shouldCheckGetterExistance() {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(RichType.class, reflectorFactory);
  // assertTrue(meta.hasGetter("richField"));
  // assertTrue(meta.hasGetter("richProperty"));
  // assertTrue(meta.hasGetter("richList"));
  // assertTrue(meta.hasGetter("richMap"));
  // assertTrue(meta.hasGetter("richList[0]"));
  //
  // assertTrue(meta.hasGetter("richType"));
  // assertTrue(meta.hasGetter("richType.richField"));
  // assertTrue(meta.hasGetter("richType.richProperty"));
  // assertTrue(meta.hasGetter("richType.richList"));
  // assertTrue(meta.hasGetter("richType.richMap"));
  // assertTrue(meta.hasGetter("richType.richList[0]"));
  //
  // assertEquals("richType.richProperty", meta.findProperty("richType.richProperty", false));
  //
  // assertFalse(meta.hasGetter("[0]"));
  // }
  //
  // @Test
  // void shouldCheckSetterExistance() {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(RichType.class, reflectorFactory);
  // assertTrue(meta.hasSetter("richField"));
  // assertTrue(meta.hasSetter("richProperty"));
  // assertTrue(meta.hasSetter("richList"));
  // assertTrue(meta.hasSetter("richMap"));
  // assertTrue(meta.hasSetter("richList[0]"));
  //
  // assertTrue(meta.hasSetter("richType"));
  // assertTrue(meta.hasSetter("richType.richField"));
  // assertTrue(meta.hasSetter("richType.richProperty"));
  // assertTrue(meta.hasSetter("richType.richList"));
  // assertTrue(meta.hasSetter("richType.richMap"));
  // assertTrue(meta.hasSetter("richType.richList[0]"));
  //
  // assertFalse(meta.hasSetter("[0]"));
  // }
  //
  // @Test
  // void shouldCheckTypeForEachGetter() {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(RichType.class, reflectorFactory);
  // assertEquals(String.class, meta.getGetterType("richField"));
  // assertEquals(String.class, meta.getGetterType("richProperty"));
  // assertEquals(List.class, meta.getGetterType("richList"));
  // assertEquals(Map.class, meta.getGetterType("richMap"));
  // assertEquals(List.class, meta.getGetterType("richList[0]"));
  //
  // assertEquals(RichType.class, meta.getGetterType("richType"));
  // assertEquals(String.class, meta.getGetterType("richType.richField"));
  // assertEquals(String.class, meta.getGetterType("richType.richProperty"));
  // assertEquals(List.class, meta.getGetterType("richType.richList"));
  // assertEquals(Map.class, meta.getGetterType("richType.richMap"));
  // assertEquals(List.class, meta.getGetterType("richType.richList[0]"));
  // }
  //
  // @Test
  // void shouldCheckTypeForEachSetter() {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(RichType.class, reflectorFactory);
  // assertEquals(String.class, meta.getSetterType("richField"));
  // assertEquals(String.class, meta.getSetterType("richProperty"));
  // assertEquals(List.class, meta.getSetterType("richList"));
  // assertEquals(Map.class, meta.getSetterType("richMap"));
  // assertEquals(List.class, meta.getSetterType("richList[0]"));
  //
  // assertEquals(RichType.class, meta.getSetterType("richType"));
  // assertEquals(String.class, meta.getSetterType("richType.richField"));
  // assertEquals(String.class, meta.getSetterType("richType.richProperty"));
  // assertEquals(List.class, meta.getSetterType("richType.richList"));
  // assertEquals(Map.class, meta.getSetterType("richType.richMap"));
  // assertEquals(List.class, meta.getSetterType("richType.richList[0]"));
  // }
  //
  // @Test
  // void shouldCheckGetterAndSetterNames() {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(RichType.class, reflectorFactory);
  // assertEquals(5, meta.getGetterNames().length);
  // assertEquals(5, meta.getSetterNames().length);
  // }
  //
  // @Test
  // void shouldFindPropertyName() {
  // ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  // MetaClass meta = MetaClass.forClass(RichType.class, reflectorFactory);
  // assertEquals("richField", meta.findProperty("RICHfield"));
  // }

  // KItest
//  private MetaClass metaClass;
//  private ReflectorFactory reflectorFactory;
//
//  @BeforeEach
//  public void setUp() {
//    reflectorFactory = new DefaultReflectorFactory();
//  }
//
//  @Test
//  public void shouldTestDataTypeOfGenericMethod() {
//    metaClass = MetaClass.forClass(GenericConcrete.class, reflectorFactory);
//
//    // Test the data type for getter
//    Class<?> getterType = metaClass.getGetterType("id");
//    assertEquals(Long.class, getterType, "Getter type should be Long");
//
//    // Test the data type for setter
//    Class<?> setterType = metaClass.getSetterType("id");
//    assertEquals(Long.class, setterType, "Setter type should be Long");
//  }
//
//  @Test
//  public void shouldThrowReflectionExceptionGetGetterType() {
//    metaClass = MetaClass.forClass(RichType.class, reflectorFactory);
//
//    Exception exception = assertThrows(RuntimeException.class, () -> {
//      metaClass.getGetInvoker("nonExistingProperty");
//    });
//
//    String expectedMessage = "nonExistingProperty";
//    String actualMessage = exception.getMessage();
//
//    assertTrue(actualMessage.contains(expectedMessage), "Exception message should reference the non-existing property");
//  }
//
//  @Test
//  public void shouldCheckGetterExistance() {
//    metaClass = MetaClass.forClass(RichType.class, reflectorFactory);
//
//    assertTrue(metaClass.hasGetter("richProperty"), "richProperty getter should exist");
//    assertFalse(metaClass.hasGetter("nonExistingProperty"), "nonExistingProperty getter should not exist");
//
//    assertTrue(metaClass.hasGetter("richType.richProperty"), "Nested getter should exist");
//    assertFalse(metaClass.hasGetter("richType.nonExistingProperty"), "Nested non-existing getter should not exist");
//  }
//
//  @Test
//  public void shouldCheckSetterExistance() {
//    metaClass = MetaClass.forClass(RichType.class, reflectorFactory);
//
//    assertTrue(metaClass.hasSetter("richProperty"), "richProperty setter should exist");
//    assertFalse(metaClass.hasSetter("nonExistingProperty"), "nonExistingProperty setter should not exist");
//
//    assertTrue(metaClass.hasSetter("richType.richProperty"), "Nested setter should exist");
//    assertFalse(metaClass.hasSetter("richType.nonExistingProperty"), "Nested non-existing setter should not exist");
//  }
//
//  @Test
//  public void shouldCheckTypeForEachGetter() {
//    metaClass = MetaClass.forClass(RichType.class, reflectorFactory);
//
//    assertEquals(String.class, metaClass.getGetterType("richProperty"), "Type of richProperty getter should be String");
//    assertEquals(List.class, metaClass.getGetterType("richList"), "Type of richList getter should be List");
//
//    assertEquals(String.class, metaClass.getGetterType("richType.richProperty"), "Nested getter type should be String");
//  }
//
//  @Test
//  public void shouldCheckTypeForEachSetter() {
//    metaClass = MetaClass.forClass(RichType.class, reflectorFactory);
//
//    assertEquals(String.class, metaClass.getSetterType("richProperty"), "Type of richProperty setter should be String");
//    assertEquals(List.class, metaClass.getSetterType("richList"), "Type of richList setter should be List");
//
//    assertEquals(String.class, metaClass.getSetterType("richType.richProperty"), "Nested setter type should be String");
//  }
//
//  @Test
//  public void shouldCheckGetterAndSetterNames() {
//    metaClass = MetaClass.forClass(RichType.class, reflectorFactory);
//
//    String[] getterNames = metaClass.getGetterNames();
//    String[] setterNames = metaClass.getSetterNames();
//
//    // Erwarte nur die Getter-Methoden für die direkten Felder in RichType
//    assertEquals(5, getterNames.length, "Anzahl der Getter-Namen entspricht nicht der erwarteten Anzahl");
//
//    // Erwarte nur die Setter-Methoden für die direkten Felder in RichType
//    assertEquals(5, setterNames.length, "Anzahl der Setter-Namen entspricht nicht der erwarteten Anzahl");
//  }
//
//  @Test
//  public void shouldFindPropertyName() {
//    metaClass = MetaClass.forClass(RichType.class, reflectorFactory);
//
//    // Suchen Sie nach der Eigenschaftsname ohne Berücksichtigung der Groß- und Kleinschreibung
//    String foundProperty = metaClass.findProperty("RICH_property", true);
//
//    assertNotNull(foundProperty, "The found property should not be null");
//    assertEquals("richProperty", foundProperty, "Property name should be resolved correctly (case insensitive)");
//  }

  // Mini

   private MetaClass metaClass;

   @BeforeEach
   void setUp() {
   // Hier erstellen wir eine Instanz von MetaClass basierend auf der Concrete-Klasse GenericConcrete.
   ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
   metaClass = MetaClass.forClass(GenericConcrete.class, reflectorFactory);
   }

   @Test
   void shouldTestDataTypeOfGenericMethod() {
   Class<?> returnType = metaClass.getGetterType("id");
   assertEquals(Long.class, returnType);
   }

   @Test
   void shouldCheckGetterExistance() {
   assertTrue(metaClass.hasGetter("id")); // Vorhandener Getter
   assertFalse(metaClass.hasGetter("nonExistingProperty")); // Nicht vorhandener Getter
   }

   @Test
   void shouldCheckSetterExistance() {
   assertTrue(metaClass.hasSetter("id")); // Vorhandener Setter
   assertFalse(metaClass.hasSetter("nonExistingProperty")); // Nicht vorhandener Setter
   }

   @Test
   void shouldCheckTypeForEachGetter() {
   assertEquals(Long.class, metaClass.getGetterType("id")); // Überprüfen des Getter-Typs
   }

   @Test
   void shouldCheckTypeForEachSetter() {
   assertEquals(Long.class, metaClass.getSetterType("id")); // Überprüfen des Setter-Typs
   }

   @Test
   void shouldCheckGetterAndSetterNames() {
   String[] getterNames = metaClass.getGetterNames();
   String[] setterNames = metaClass.getSetterNames();

   assertArrayEquals(new String[] { "id" }, getterNames); // Die erwarteten Getter-Namen
   assertArrayEquals(new String[] { "id" }, setterNames); // Die erwarteten Setter-Namen
   }

   @Test
   void shouldFindPropertyName() {
   String foundProperty = metaClass.findProperty("id");
   assertEquals("id", foundProperty); // Überprüfen des gefundenen Eigenschaftsnames
   assertNull(metaClass.findProperty("nonExistingField")); // Nicht vorhandene Eigenschaft sollte null zurückgeben
   }

}// KItest:1-3 8/8 ; Mini: 1-4 7/8
