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
package org.apache.ibatis.executor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResultExtractorTest {

  // private ResultExtractor resultExtractor;
  //
  // @Mock
  // private Configuration configuration;
  // @Mock
  // private ObjectFactory objectFactory;
  //
  // @BeforeEach
  // void setUp() {
  // resultExtractor = new ResultExtractor(configuration, objectFactory);
  // }
  //
  // @Test
  // void shouldExtractNullForNullTargetType() {
  // final Object result = resultExtractor.extractObjectFromList(null, null);
  // assertThat(result).isNull();
  // }
  //
  // @Test
  // void shouldExtractList() {
  // final List<Object> list = Arrays.asList(1, 2, 3);
  // final Object result = resultExtractor.extractObjectFromList(list, List.class);
  // assertThat(result).isInstanceOf(List.class);
  // final List resultList = (List) result;
  // assertThat(resultList).isEqualTo(list);
  // }
  //
  // @Test
  // void shouldExtractArray() {
  // final List<Object> list = Arrays.asList(1, 2, 3);
  // final Object result = resultExtractor.extractObjectFromList(list, Integer[].class);
  // assertThat(result).isInstanceOf(Integer[].class);
  // final Integer[] resultArray = (Integer[]) result;
  // assertThat(resultArray).isEqualTo(new Integer[] { 1, 2, 3 });
  // }
  //
  // @Test
  // void shouldExtractSet() {
  // final List<Object> list = Arrays.asList(1, 2, 3);
  // final Class<Set> targetType = Set.class;
  // final Set set = new HashSet();
  // final MetaObject metaObject = mock(MetaObject.class);
  // when(objectFactory.isCollection(targetType)).thenReturn(true);
  // when(objectFactory.create(targetType)).thenReturn(set);
  // when(configuration.newMetaObject(set)).thenReturn(metaObject);
  //
  // final Set result = (Set) resultExtractor.extractObjectFromList(list, targetType);
  // assertThat(result).isSameAs(set);
  //
  // verify(metaObject).addAll(list);
  // }
  //
  // @Test
  // void shouldExtractSingleObject() {
  // final List<Object> list = Collections.singletonList("single object");
  // assertThat((String) resultExtractor.extractObjectFromList(list, String.class)).isEqualTo("single object");
  // assertThat((String) resultExtractor.extractObjectFromList(list, null)).isEqualTo("single object");
  // assertThat((String) resultExtractor.extractObjectFromList(list, Integer.class)).isEqualTo("single object");
  // }
  //
  // @Test
  // void shouldFailWhenMutipleItemsInList() {
  // final List<Object> list = Arrays.asList("first object", "second object");
  // Assertions.assertThrows(ExecutorException.class, () -> resultExtractor.extractObjectFromList(list, String.class));
  // }

  // KItest
//  private ResultExtractor resultExtractor;
//  private Configuration configuration;
//  private ObjectFactory objectFactory;
//
//  @BeforeEach
//  public void setUp() {
//    configuration = mock(Configuration.class);
//    objectFactory = mock(ObjectFactory.class);
//    resultExtractor = new ResultExtractor(configuration, objectFactory);
//  }
//
//  @Test
//  public void testReturnsSingleElementWhenTargetTypeIsNull() {
//    List<Object> list = Collections.singletonList(1); // Liste mit einem einzigen Element
//    Object result = resultExtractor.extractObjectFromList(list, null);
//    assertEquals(1, result); // Erwartet, dass das Element zurückgegeben wird
//  }
//
//  @Test
//  public void testReturnsListWhenTargetTypeIsList() {
//    List<Object> list = Arrays.asList(1, 2, 3);
//    Object result = resultExtractor.extractObjectFromList(list, List.class);
//    assertTrue(result instanceof List);
//    assertEquals(list, result);
//  }
//
//  @Test
//  public void testConvertsListToArray() {
//    List<Object> list = Arrays.asList(1, 2, 3);
//    Object result = resultExtractor.extractObjectFromList(list, Integer[].class);
//    assertTrue(result instanceof Integer[]);
//    assertArrayEquals(new Integer[] { 1, 2, 3 }, (Integer[]) result);
//  }
//
//  @Test
//  public void testConvertsListToSetUsingMetaObjectThree() {
//    when(objectFactory.isCollection(Set.class)).thenReturn(true);
//    Set<Object> mockedSet = mock(Set.class);
//    when(objectFactory.create(Set.class)).thenReturn(mockedSet);
//
//    MetaObject mockedMetaObject = mock(MetaObject.class);
//    when(configuration.newMetaObject(mockedSet)).thenReturn(mockedMetaObject);
//
//    List<Object> list = Arrays.asList(1, 2, 3);
//    Object result = resultExtractor.extractObjectFromList(list, Set.class);
//
//    assertTrue(result instanceof Set);
//    // Verify that addAll was actually called on the MetaObject, we need to
//    // ensure our mocks/setup are correct
//    Mockito.verify(mockedMetaObject).addAll(list);
//  }
//
//  @Test
//  public void testReturnsSingleElementFromList() {
//    List<Object> list = Collections.singletonList(1);
//    Object result = resultExtractor.extractObjectFromList(list, Integer.class);
//    assertEquals(1, result);
//  }
//
//  @Test
//  public void testThrowsExceptionForMultipleElementsWithSingleElementTargetType() {
//    List<Object> list = Arrays.asList(1, 2);
//
//    assertThrows(ExecutorException.class, () -> {
//      resultExtractor.extractObjectFromList(list, Integer.class);
//    });
//  }

  // Mini
   private Configuration configuration;
   private ObjectFactory objectFactory;
   private ResultExtractor resultExtractor;

   @BeforeEach
   void setUp() {
   configuration = new Configuration();
   objectFactory = new DefaultObjectFactory();
   resultExtractor = new ResultExtractor(configuration, objectFactory);
   }

   @Test
   void testExtractObjectFromList_withNullTargetType_returnsNull() {
   // Eine leere Liste, um den Fall zu simulieren, wenn kein Zieltypen definiert ist.
   List<Object> list = Collections.emptyList();
   Object result = resultExtractor.extractObjectFromList(list, null);
   Assertions.assertNull(result, "Expected result to be null when targetType is null");
   }

   @Test
   void testExtractObjectFromList_withListTargetType_returnsList() {
   List<Object> list = Arrays.asList("element1", "element2");
   Object result = resultExtractor.extractObjectFromList(list, List.class);
   Assertions.assertEquals(list, result, "Expected result to be the same list");
   }

   @Test
   void testExtractObjectFromList_withArrayTargetType_returnsArray() {
   List<Object> list = Arrays.asList("element1", "element2");
   Object result = resultExtractor.extractObjectFromList(list, String[].class);
   String[] expected = list.toArray(new String[0]);
   Assertions.assertArrayEquals(expected, (Object[]) result, "Expected result to be an array");
   }

   @Test
   void testExtractObjectFromList_withSetTargetType_returnsSet() {
   List<Object> list = Arrays.asList("element1", "element2", "element1");
   Object result = resultExtractor.extractObjectFromList(list, HashSet.class);
   Assertions.assertTrue(((HashSet<?>) result).contains("element1"));
   Assertions.assertTrue(((HashSet<?>) result).contains("element2"));
   Assertions.assertEquals(2, ((HashSet<?>) result).size(), "Expected result to be a set with unique elements");
   }

   @Test
   void testExtractObjectFromList_withSingleElementList_returnsElement() {
   List<Object> list = Collections.singletonList("singleElement");
   Object result = resultExtractor.extractObjectFromList(list, String.class);
   Assertions.assertEquals("singleElement", result, "Expected result to be the single element");
   }

   @Test
   void testExtractObjectFromList_withMultipleElementsAndSingleElementTargetType_throwsException() {
   List<Object> list = Arrays.asList("element1", "element2");
   Assertions.assertThrows(ExecutorException.class, () -> {
   resultExtractor.extractObjectFromList(list, String.class);
   }, "Expected ExecutorException to be thrown");
   }

} // KItest: 1-4 6/6; Mini: 1-4 6/6
