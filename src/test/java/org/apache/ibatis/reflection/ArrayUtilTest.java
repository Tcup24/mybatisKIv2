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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ArrayUtilTest {

  // @Test
  // void testHashCode() {
  // Object arr;
  // arr = new long[] { 1 };
  // assertEquals(Arrays.hashCode((long[]) arr), ArrayUtil.hashCode(arr));
  // arr = new int[] { 1 };
  // assertEquals(Arrays.hashCode((int[]) arr), ArrayUtil.hashCode(arr));
  // arr = new short[] { 1 };
  // assertEquals(Arrays.hashCode((short[]) arr), ArrayUtil.hashCode(arr));
  // arr = new char[] { 1 };
  // assertEquals(Arrays.hashCode((char[]) arr), ArrayUtil.hashCode(arr));
  // arr = new byte[] { 1 };
  // assertEquals(Arrays.hashCode((byte[]) arr), ArrayUtil.hashCode(arr));
  // arr = new boolean[] { true };
  // assertEquals(Arrays.hashCode((boolean[]) arr), ArrayUtil.hashCode(arr));
  // arr = new float[] { 1f };
  // assertEquals(Arrays.hashCode((float[]) arr), ArrayUtil.hashCode(arr));
  // arr = new double[] { 1d };
  // assertEquals(Arrays.hashCode((double[]) arr), ArrayUtil.hashCode(arr));
  // arr = new Object[] { "str" };
  // assertEquals(Arrays.hashCode((Object[]) arr), ArrayUtil.hashCode(arr));
  //
  // assertEquals(0, ArrayUtil.hashCode(null));
  // assertEquals("str".hashCode(), ArrayUtil.hashCode("str"));
  // assertEquals(Integer.valueOf(1).hashCode(), ArrayUtil.hashCode(1));
  // }
  //
  // @Test
  // void testequals() {
  // assertTrue(ArrayUtil.equals(new long[] { 1 }, new long[] { 1 }));
  // assertTrue(ArrayUtil.equals(new int[] { 1 }, new int[] { 1 }));
  // assertTrue(ArrayUtil.equals(new short[] { 1 }, new short[] { 1 }));
  // assertTrue(ArrayUtil.equals(new char[] { 1 }, new char[] { 1 }));
  // assertTrue(ArrayUtil.equals(new byte[] { 1 }, new byte[] { 1 }));
  // assertTrue(ArrayUtil.equals(new boolean[] { true }, new boolean[] { true }));
  // assertTrue(ArrayUtil.equals(new float[] { 1f }, new float[] { 1f }));
  // assertTrue(ArrayUtil.equals(new double[] { 1d }, new double[] { 1d }));
  // assertTrue(ArrayUtil.equals(new Object[] { "str" }, new Object[] { "str" }));
  //
  // assertFalse(ArrayUtil.equals(new long[] { 1 }, new long[] { 2 }));
  // assertFalse(ArrayUtil.equals(new int[] { 1 }, new int[] { 2 }));
  // assertFalse(ArrayUtil.equals(new short[] { 1 }, new short[] { 2 }));
  // assertFalse(ArrayUtil.equals(new char[] { 1 }, new char[] { 2 }));
  // assertFalse(ArrayUtil.equals(new byte[] { 1 }, new byte[] { 2 }));
  // assertFalse(ArrayUtil.equals(new boolean[] { true }, new boolean[] { false }));
  // assertFalse(ArrayUtil.equals(new float[] { 1f }, new float[] { 2f }));
  // assertFalse(ArrayUtil.equals(new double[] { 1d }, new double[] { 2d }));
  // assertFalse(ArrayUtil.equals(new Object[] { "str" }, new Object[] { "rts" }));
  //
  // assertTrue(ArrayUtil.equals(null, null));
  // assertFalse(ArrayUtil.equals(new long[] { 1 }, null));
  // assertFalse(ArrayUtil.equals(null, new long[] { 1 }));
  //
  // assertTrue(ArrayUtil.equals(1, 1));
  // assertTrue(ArrayUtil.equals("str", "str"));
  // }
  //
  // @Test
  // void testToString() {
  // Object arr;
  // arr = new long[] { 1 };
  // assertEquals(Arrays.toString((long[]) arr), ArrayUtil.toString(arr));
  // arr = new int[] { 1 };
  // assertEquals(Arrays.toString((int[]) arr), ArrayUtil.toString(arr));
  // arr = new short[] { 1 };
  // assertEquals(Arrays.toString((short[]) arr), ArrayUtil.toString(arr));
  // arr = new char[] { 1 };
  // assertEquals(Arrays.toString((char[]) arr), ArrayUtil.toString(arr));
  // arr = new byte[] { 1 };
  // assertEquals(Arrays.toString((byte[]) arr), ArrayUtil.toString(arr));
  // arr = new boolean[] { true };
  // assertEquals(Arrays.toString((boolean[]) arr), ArrayUtil.toString(arr));
  // arr = new float[] { 1f };
  // assertEquals(Arrays.toString((float[]) arr), ArrayUtil.toString(arr));
  // arr = new double[] { 1d };
  // assertEquals(Arrays.toString((double[]) arr), ArrayUtil.toString(arr));
  // arr = new Object[] { "str" };
  // assertEquals(Arrays.toString((Object[]) arr), ArrayUtil.toString(arr));
  //
  // assertEquals(Integer.toString(1), ArrayUtil.toString(1));
  // assertEquals("null", ArrayUtil.toString(null));
  // }

  // KItest
  @Test
  public void testHashCode() {
    // Test: Hashcode für verschiedene Array-Typen vergleichen
    int[] intArray = { 1, 2, 3 };
    assertEquals(Arrays.hashCode(intArray), ArrayUtil.hashCode(intArray));

    long[] longArray = { 1L, 2L, 3L };
    assertEquals(Arrays.hashCode(longArray), ArrayUtil.hashCode(longArray));

    String[] stringArray = { "a", "b", "c" };
    assertEquals(Arrays.hashCode(stringArray), ArrayUtil.hashCode(stringArray));

    // Test: hashCode für null-Wert
    assertEquals(0, ArrayUtil.hashCode(null));

    // Test: hashCode für ein Nicht-Array-Objekt
    String str = "Hello";
    assertEquals(str.hashCode(), ArrayUtil.hashCode(str));
  }

  @Test
  public void testEquals() {
    // Test: Gleichheitsprüfung für Arrays
    int[] intArray1 = { 1, 2, 3 };
    int[] intArray2 = { 1, 2, 3 };
    assertTrue(ArrayUtil.equals(intArray1, intArray2));

    int[] intArray3 = { 1, 2, 4 };
    assertFalse(ArrayUtil.equals(intArray1, intArray3));

    // Test: Gleichheitsprüfung für null-Werte
    assertTrue(ArrayUtil.equals(null, null));
    assertFalse(ArrayUtil.equals(intArray1, null));
    assertFalse(ArrayUtil.equals(null, intArray2));

    // Test: Gleichheitsprüfung für Nicht-Array-Objekte
    assertTrue(ArrayUtil.equals("test", "test"));
    assertFalse(ArrayUtil.equals("test", "Test"));
  }

  @Test
  public void testToString() {
    // Test: toString für Arrays
    int[] intArray = { 1, 2, 3 };
    assertEquals(Arrays.toString(intArray), ArrayUtil.toString(intArray));

    String[] stringArray = { "foo", "bar" };
    assertEquals(Arrays.toString(stringArray), ArrayUtil.toString(stringArray));

    // Test: toString für Nicht-Array-Objekte
    String str = "Hello";
    assertEquals(str.toString(), ArrayUtil.toString(str));

    // Test: toString für null-Wert
    assertEquals("null", ArrayUtil.toString(null));
  }

  // //Mini
//   @Test
//   public void testHashCodeTwoMini() {
//   // Test für unterschiedliche Array-Typen
//   int[] intArray = { 1, 2, 3 };
//   assertEquals(Arrays.hashCode(intArray), ArrayUtil.hashCode(intArray));
//
//   double[] doubleArray = { 1.0, 2.0, 3.0 };
//   assertEquals(Arrays.hashCode(doubleArray), ArrayUtil.hashCode(doubleArray));
//
//   // Test für null
//   assertEquals(0, ArrayUtil.hashCode(null));
//
//   // Test für Nicht-Array-Objekt
//   String str = "Hello";
//   assertEquals(str.hashCode(), ArrayUtil.hashCode(str));
//   }
//
//   // Test für die Methode equals
//   @Test
//   public void testEqualsTwoMini() {
//   // Test für Arrays mit gleichen Inhalten
//   int[] intArray1 = { 1, 2, 3 };
//   int[] intArray2 = { 1, 2, 3 };
//   assertTrue(ArrayUtil.equals(intArray1, intArray2));
//
//   // Test für Arrays mit unterschiedlichen Inhalten
//   int[] intArray3 = { 1, 2, 4 };
//   assertFalse(ArrayUtil.equals(intArray1, intArray3));
//
//   // Test für null-Werte
//   assertTrue(ArrayUtil.equals(null, null));
//   String str = "Hello";
//   assertFalse(ArrayUtil.equals(null, str));
//   assertFalse(ArrayUtil.equals(str, null));
//
//   // Test für Nicht-Array-Objekte
//   String str1 = "Hello";
//   String str2 = "Hello";
//   assertTrue(ArrayUtil.equals(str1, str2));
//
//   String str3 = "World";
//   assertFalse(ArrayUtil.equals(str1, str3));
//   }
//
//   // Test für die Methode toString
//   @Test
//   public void testToStringTwoMini() {
//   // Test für Umwandlung eines Arrays in eine Zeichenkette
//   int[] intArray = { 1, 2, 3 };
//   assertEquals(Arrays.toString(intArray), ArrayUtil.toString(intArray));
//
//   // Test für Nicht-Array-Objekte
//   String str = "Hello World";
//   assertEquals(str.toString(), ArrayUtil.toString(str));
//
//   // Test für null
//   assertEquals("null", ArrayUtil.toString(null));
//   }

}
