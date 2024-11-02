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
package org.apache.ibatis.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.apache.ibatis.annotations.CacheNamespace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link ResolverUtil}.
 *
 * @author Pi Chen
 *
 * @since 3.5.2
 */

class ResolverUtilTest {
  // private static ClassLoader currentContextClassLoader;
  //
  // @BeforeAll
  // static void setUp() {
  // currentContextClassLoader = Thread.currentThread().getContextClassLoader();
  // }
  //
  // @Test
  // void getClasses() {
  // assertEquals(new ResolverUtil<>().getClasses().size(), 0);
  // }
  //
  // @Test
  // void getClassLoader() {
  // assertEquals(new ResolverUtil<>().getClassLoader(), currentContextClassLoader);
  // }
  //
  // @Test
  // void setClassLoader() {
  // ResolverUtil resolverUtil = new ResolverUtil();
  // AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
  // resolverUtil.setClassLoader(new ClassLoader() {
  // });
  // return null;
  // });
  // assertNotEquals(resolverUtil.getClassLoader(), currentContextClassLoader);
  // }
  //
  // @Test
  // void findImplementationsWithNullPackageName() {
  // ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
  // resolverUtil.findImplementations(VFS.class, (String[]) null);
  // assertEquals(resolverUtil.getClasses().size(), 0);
  // }
  //
  // @Test
  // void findImplementations() {
  // ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
  // resolverUtil.findImplementations(VFS.class, "org.apache.ibatis.io");
  // Set<Class<? extends VFS>> classSets = resolverUtil.getClasses();
  // // org.apache.ibatis.io.VFS
  // // org.apache.ibatis.io.DefaultVFS
  // // org.apache.ibatis.io.JBoss6VFS
  // assertEquals(classSets.size(), 3); // fail if add a new VFS implementation in this package!!!
  // classSets.forEach(c -> assertTrue(VFS.class.isAssignableFrom(c)));
  // }
  //
  // @Test
  // void findAnnotatedWithNullPackageName() {
  // ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
  // resolverUtil.findAnnotated(CacheNamespace.class, (String[]) null);
  // assertEquals(resolverUtil.getClasses().size(), 0);
  // }
  //
  // @Test
  // void findAnnotated() {
  // ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
  // resolverUtil.findAnnotated(CacheNamespace.class, this.getClass().getPackage().getName());
  // Set<Class<?>> classSets = resolverUtil.getClasses();
  // // org.apache.ibatis.io.ResolverUtilTest.TestMapper
  // assertEquals(classSets.size(), 1);
  // classSets.forEach(c -> assertNotNull(c.getAnnotation(CacheNamespace.class)));
  // }
  //
  // @Test
  // void find() {
  // ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
  // resolverUtil.find(new ResolverUtil.IsA(VFS.class), "org.apache.ibatis.io");
  // Set<Class<? extends VFS>> classSets = resolverUtil.getClasses();
  // // org.apache.ibatis.io.VFS
  // // org.apache.ibatis.io.DefaultVFS
  // // org.apache.ibatis.io.JBoss6VFS
  // assertEquals(classSets.size(), 3);
  // classSets.forEach(c -> assertTrue(VFS.class.isAssignableFrom(c)));
  // }
  //
  // @Test
  // void getPackagePath() {
  // ResolverUtil resolverUtil = new ResolverUtil();
  // assertNull(resolverUtil.getPackagePath(null));
  // assertEquals(resolverUtil.getPackagePath("org.apache.ibatis.io"), "org/apache/ibatis/io");
  // }
  //
  // @Test
  // void addIfMatching() {
  // ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
  // resolverUtil.addIfMatching(new ResolverUtil.IsA(VFS.class), "org/apache/ibatis/io/DefaultVFS.class");
  // resolverUtil.addIfMatching(new ResolverUtil.IsA(VFS.class), "org/apache/ibatis/io/VFS.class");
  // Set<Class<? extends VFS>> classSets = resolverUtil.getClasses();
  // assertEquals(classSets.size(), 2);
  // classSets.forEach(c -> assertTrue(VFS.class.isAssignableFrom(c)));
  // }
  //
  // @Test
  // void addIfNotMatching() {
  // ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
  // resolverUtil.addIfMatching(new ResolverUtil.IsA(VFS.class), "org/apache/ibatis/io/Xxx.class");
  // assertEquals(resolverUtil.getClasses().size(), 0);
  // }
  //
  // @Test
  // void testToString() {
  // ResolverUtil.IsA isa = new ResolverUtil.IsA(VFS.class);
  // assertTrue(isa.toString().contains(VFS.class.getSimpleName()));
  //
  // ResolverUtil.AnnotatedWith annotatedWith = new ResolverUtil.AnnotatedWith(CacheNamespace.class);
  // assertTrue(annotatedWith.toString().contains("@" + CacheNamespace.class.getSimpleName()));
  // }
  //
  // @CacheNamespace(readWrite = false)
  // private interface TestMapper {
  // // test ResolverUtil.findAnnotated method
  // }
  //
  // //KItest
//  @Test
//  void testGetClassesReturnsEmptySetIfNoClassesLoaded() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    Set<Class<? extends Object>> classes = resolverUtil.getClasses();
//    assertTrue(classes.isEmpty(), "Expected an empty set of classes");
//  }
//
//  @Test
//  void testDefaultClassLoaderIsUsedIfNoneSet() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    ClassLoader classLoader = resolverUtil.getClassLoader();
//    assertEquals(Thread.currentThread().getContextClassLoader(), classLoader,
//        "Expected the current context class loader");
//  }
//
//  @Test
//  void testCanSetAndGetDifferentClassLoader() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    ClassLoader newClassLoader = Mockito.mock(ClassLoader.class);
//    resolverUtil.setClassLoader(newClassLoader);
//    assertEquals(newClassLoader, resolverUtil.getClassLoader(), "Expected the set class loader");
//  }
//
//  @Test
//  void testFindImplementationsWithoutPackageYieldsNoClasses() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    resolverUtil.findImplementations(Object.class);
//    assertTrue(resolverUtil.getClasses().isEmpty(), "Expected no classes found");
//  }
//
//  @Test
//  void testFindAnnotatedClassesWithoutPackageYieldsNoClasses() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    resolverUtil.findAnnotated(CacheNamespace.class);
//    assertTrue(resolverUtil.getClasses().isEmpty(), "Expected no classes found");
//  }
//
//  @Test
//  void testGetPackagePathReturnsNullIfNoPackageName() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    String path = resolverUtil.getPackagePath(null);
//    assertNull(path, "Expected path to be null");
//  }
//
//  @Test
//  void testAddMatchingClassIncludesInResult() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    resolverUtil.addIfMatching(new ResolverUtil.IsA(Number.class), "java/lang/Integer.class");
//    assertTrue(resolverUtil.getClasses().contains(Integer.class), "Expected Integer class in result");
//  }
//
//  @Test
//  void testAddNonMatchingClassExcludesFromResult() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//    resolverUtil.addIfMatching(new ResolverUtil.IsA(String.class), "java/lang/Integer.class");
//    assertFalse(resolverUtil.getClasses().contains(Integer.class), "Expected Integer class to not be in result");
//  }
//
//  @Test
//  void testToStringMethodsContainCorrectDescriptions() {
//    ResolverUtil.IsA isATest = new ResolverUtil.IsA(Number.class);
//    ResolverUtil.AnnotatedWith annotatedWithTest = new ResolverUtil.AnnotatedWith(CacheNamespace.class);
//
//    assertEquals("is assignable to Number", isATest.toString());
//    assertEquals("annotated with @CacheNamespace", annotatedWithTest.toString());
//  }
//
//  @Test
//  void testFindAnnotatedClassesInPackageYieldsCorrectAnnotations() {
//    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
//
//    // Verwende ein Beispielpaket, von dem bekannt ist, dass es keine annotierten Klassen zurückgibt
//    resolverUtil.findAnnotated(Deprecated.class, "java.util"); // Verwenden einer verbreiteten Annotation und Paket
//    // Da wir in einem Basis-Java-Paket nach Annotierten Klassen suchen, erwarten wir nicht, dass spezifische Klassen
//    // zurückgegeben werden
//    assertTrue(resolverUtil.getClasses().isEmpty(),
//        "Expected no classes to have Deprecated annotation in java.util package");
//  }

  // //Mini
   private ResolverUtil<Object> resolverUtil;

   @BeforeEach
   void setUp() {
   resolverUtil = new ResolverUtil<>();
   }

   @Test
   void testGetClassesWhenNoneLoaded() {
   // Test that an empty set of classes is returned when no classes have been loaded.
   Set<Class<? extends Object>> classes = resolverUtil.getClasses();
   assertTrue(classes.isEmpty(), "Expected an empty set when no classes are loaded");
   }

   @Test
   void testGetClassLoaderReturnsDefault() {
   // Test that the current class loader is returned as the default class loader.
   ClassLoader classLoader = resolverUtil.getClassLoader();
   assertNotNull(classLoader, "Expected the default class loader to be returned");
   }

   @Test
   void testSetClassLoader() {
   // Test that the class loader of a ResolverUtil object can be changed successfully.
   ClassLoader newClassLoader = new ClassLoader() {
   };
   resolverUtil.setClassLoader(newClassLoader);
   assertEquals(newClassLoader, resolverUtil.getClassLoader(), "Expected the set class loader to be returned");
   }

   @Test
   void testFindImplementationsWithoutPackageReturnsEmpty() {
   // Test that searching for implementations of a certain class without specifying a package returns no classes.
   resolverUtil.findImplementations(Object.class);
   Set<Class<? extends Object>> classes = resolverUtil.getClasses();
   assertTrue(classes.isEmpty(), "Expected no classes found when searching without package name");
   }


   @Test
   void testFindAnnotatedClassesWithoutPackageReturnsEmpty() {
   // Test that searching for classes with a specific annotation without specifying a package returns no classes.
   resolverUtil.findAnnotated(CacheNamespace.class);
   Set<Class<? extends Object>> classes = resolverUtil.getClasses();
   assertTrue(classes.isEmpty(),
   "Expected no classes found when searching for annotated classes without package name");
   }

  // -------------------------------------------------------

  // @Test
  // void testFindImplementationsWithPackageReturnsMatchingClasses() {
  // // Test that implementations of a specific class within a defined package are indeed found.
  // resolverUtil.findImplementations(Object.class, "java.lang");
  // Set<Class<? extends Object>> classes = resolverUtil.getClasses();
  // assertFalse(classes.isEmpty(), "Expected classes that match Object to be found in the specified package");
  // }

  // @Test
  // void testFindAnnotatedClassesWithPackageReturnsMatchingClasses() {
  // // Test that classes annotated with a specific annotation within a defined package are found.
  // resolverUtil.findAnnotated(CacheNamespace.class, "org.example"); // Change to existing package
  // Set<Class<? extends Object>> classes = resolverUtil.getClasses();
  // assertFalse(classes.isEmpty(), "Expected classes annotated with CacheNamespace to be found in the specified
  // package");
  // }

  // @Test
  // void testFindClassesMatchingCondition() {
  // // Test that classes matching a specific condition have been found.
  // ResolverUtil.Test myCondition = type -> type.getName().contains("String");
  // resolverUtil.find(myCondition, "java.lang");
  // Set<Class<? extends Object>> classes = resolverUtil.getClasses();
  // assertFalse(classes.isEmpty(), "Expected classes matching the condition to be found");
  // }

  // @Test
  // void testAddClassesMatchingCondition() {
  // // Test that classes are added to the result set when they meet a specific condition.
  // ResolverUtil.Test myCondition = type -> type.isInterface();
  // resolverUtil.find(myCondition, "java.util");
  // Set<Class<? extends Object>> classes = resolverUtil.getClasses();
  // assertFalse(classes.isEmpty(), "Expected classes that are interfaces to be added");
  // }
  // -------------------------------------------------------

   @Test
   void testGetPackagePathReturnsCorrectlyFormattedPath() {
   // Test that the path for a given package is correctly formatted and returns null when no package name is given.
   String path = resolverUtil.getPackagePath("org.example");
   assertEquals("org/example", path, "Expected the package path to be formatted correctly");

   assertNull(resolverUtil.getPackagePath(null), "Expected null to be returned for null package name");
   }


   @Test
   void testDoNotAddClassesNotMatchingCondition() {
   // Test that classes are not added to the result set when they do not meet a specific condition.
   ResolverUtil.Test myCondition = type -> type.getName().equals("InvalidClassName");
   resolverUtil.find(myCondition, "java.util");
   Set<Class<? extends Object>> classes = resolverUtil.getClasses();
   assertTrue(classes.isEmpty(), "Expected no classes to be added that do not match the condition");
   }

   @Test
   void testToStringMethodContainsClassName() {
   // Test that the toString methods of certain inner classes contain the correct class names in their descriptions.
   assertEquals("is assignable to Object", new ResolverUtil.IsA(Object.class).toString(),
   "Expected the toString method to contain the correct class name");
   }

   @Test
   void testToStringMethodContainsAnnotation() {
   // Test that the toString methods of certain inner classes contain the correct annotation in their descriptions.
   assertEquals("annotated with @CacheNamespace", new ResolverUtil.AnnotatedWith(CacheNamespace.class).toString(),
   "Expected the toString method to contain the correct annotation");
   }
}

// KItest: 1-4 10/12;Mini: 3-4 8/12
