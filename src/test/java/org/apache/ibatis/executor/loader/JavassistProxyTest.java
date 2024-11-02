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
package org.apache.ibatis.executor.loader;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javassist.util.proxy.Proxy;

import org.apache.ibatis.domain.blog.Author;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JavassistProxyTest extends SerializableProxyTest {

  @BeforeAll
  static void createProxyFactory() {
    proxyFactory = new JavassistProxyFactory();
  }

  @Test
  void shouldCreateAProxyForAPartiallyLoadedBean() throws Exception {
    ResultLoaderMap loader = new ResultLoaderMap();
    loader.addLoader("id", null, null);
    Object proxy = proxyFactory.createProxy(author, loader, new Configuration(), new DefaultObjectFactory(),
        new ArrayList<>(), new ArrayList<>());
    Author author2 = (Author) deserialize(serialize((Serializable) proxy));
    assertTrue(author2 instanceof Proxy);
  }

  @Test
  void shouldFailCallingAnUnloadedProperty() {
    // yes, it must go in uppercase
    HashMap<String, ResultLoaderMap.LoadPair> unloadedProperties = new HashMap<>();
    unloadedProperties.put("ID", null);
    Author author2 = (Author) ((JavassistProxyFactory) proxyFactory).createDeserializationProxy(author,
        unloadedProperties, new DefaultObjectFactory(), new ArrayList<>(), new ArrayList<>());
    Assertions.assertThrows(ExecutorException.class, author2::getId);
  }

  @Test
  void shouldLetCallALoadedProperty() {
    Author author2 = (Author) ((JavassistProxyFactory) proxyFactory).createDeserializationProxy(author, new HashMap<>(),
        new DefaultObjectFactory(), new ArrayList<>(), new ArrayList<>());
    assertEquals(999, author2.getId());
  }

  @Test
  void shouldSerizalizeADeserlizaliedProxy() throws Exception {
    Object proxy = ((JavassistProxyFactory) proxyFactory).createDeserializationProxy(author, new HashMap<>(),
        new DefaultObjectFactory(), new ArrayList<>(), new ArrayList<>());
    Author author2 = (Author) deserialize(serialize((Serializable) proxy));
    assertEquals(author, author2);
    assertNotEquals(author.getClass(), author2.getClass());
  }

  // KItest
  @Test
  void testPartialObjectProxyCreation() {
    Author author = new Author(1, "testUser", "testPass", "test@mail.com", "Bio", null);
    ResultLoaderMap lazyLoader = new ResultLoaderMap();
    Configuration configuration = new Configuration();
    ObjectFactory objectFactory = new DefaultObjectFactory();

    // Create a proxy for the Author object
    JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
    Object proxyObject = proxyFactory.createProxy(author, lazyLoader, configuration, objectFactory,
        Collections.emptyList(), Collections.emptyList());

    assertNotNull(proxyObject);
    assertNotSame(author, proxyObject);
    assertTrue(proxyObject instanceof Serializable, "The proxy object should be serializable");
  }

  @Test
  void testLoadedPropertyAccess() {
    Author author = new Author(1, "testUser", "testPass", "test@mail.com", "Bio", null);

    // Assume the username property is pre-loaded
    assertEquals("testUser", author.getUsername(), "The username should match the expected value");
  }

  @Test
  void testLazyLoadTriggerError() {
    // Setup: Author object with some initial properties.
    Author author = new Author(1, null, null, null, null, null);

    // Initialize the ResultLoaderMap and mark the "username" property as not loaded
    ResultLoaderMap lazyLoader = new ResultLoaderMap();

    // Simulate that the loader doesn't have a proper loader set up which will throw an exception
    ResultLoaderMap.LoadPair loadPair = new ResultLoaderMap.LoadPair("username", null, null);
    lazyLoader.addLoader("username", null, null);

    // Create the proxy for the author object
    JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
    ObjectFactory objectFactory = new DefaultObjectFactory();
    Author proxyAuthor = (Author) proxyFactory.createProxy(author, lazyLoader, new Configuration(), objectFactory,
        Collections.emptyList(), Collections.emptyList());

    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
      // Try to access the 'username' property, which should trigger an exception
      proxyAuthor.getUsername();
    });

    assertEquals("metaResultObject is null", thrown.getMessage());
  }

  @Test
  void testProxySerializationDeserialization() throws Exception {
    Author author = new Author(1, "testUser", "testPass", "test@mail.com", "Bio", null);
    ResultLoaderMap lazyLoader = new ResultLoaderMap();
    Configuration configuration = new Configuration();
    ObjectFactory objectFactory = new DefaultObjectFactory();

    JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
    Object proxyObject = proxyFactory.createProxy(author, lazyLoader, configuration, objectFactory,
        Collections.emptyList(), Collections.emptyList());

    byte[] serializedBytes = serialize((Serializable) proxyObject);
    Serializable deserializedProxy = deserialize(serializedBytes);

    assertNotNull(deserializedProxy);
    assertNotSame("The deserialized proxy should not have the same class type as the original proxy.",
        proxyObject.getClass(), deserializedProxy.getClass());
    assertTrue(deserializedProxy instanceof Author, "The deserialized proxy should be an instance of Author");
    assertEquals(author, deserializedProxy, "Deserialized object should have the same properties as the original.");
  }

  // Mini
  // @Test
  // public void testCreateProxy() throws Exception {
  // // Given
  // Author originalAuthor = new Author(1, "username", "password", "email@example.com", "bio", null);
  // ResultLoaderMap lazyLoader = new ResultLoaderMap();
  // Configuration configuration = new Configuration();
  // ObjectFactory objectFactory = new DefaultObjectFactory();
  //
  // // When
  // JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
  // Object proxy = proxyFactory.createProxy(originalAuthor, lazyLoader, configuration, objectFactory, null, null);
  //
  // // Then
  // assertNotNull(proxy);
  // assertTrue(proxy instanceof Proxy); // assuming Proxy is the expected proxy type
  // }

  // @Test
  // public void testUnloadedPropertyAccessThrowsException() throws Exception {
  // // Given
  // Author originalAuthor = new Author(1, "username", "password", "email@example.com", "bio", null);
  // ResultLoaderMap lazyLoader = new ResultLoaderMap();
  // lazyLoader.addLoader("bio", null, null); // BIO not loaded
  // Configuration configuration = new Configuration();
  // ObjectFactory objectFactory = new DefaultObjectFactory();
  //
  // // When
  // JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
  // Object proxy = proxyFactory.createProxy(originalAuthor, lazyLoader, configuration, objectFactory, null, null);
  // Throwable exception = assertThrows(ExecutorException.class, () -> {
  // // Assuming there's a method on the proxy to access the bio property
  // String bio = ((Author) proxy).getBio();
  // });
  //
  // // Then
  // assertEquals("Property [bio] cannot be loaded", exception.getMessage());
  // }
  //
  // @Test
  // public void testLoadedPropertyReturnsValue() throws Exception {
  // // Given
  // Author originalAuthor = new Author(1, "username", "password", "email@example.com", "bio", null);
  // ResultLoaderMap lazyLoader = new ResultLoaderMap();
  // Configuration configuration = new Configuration();
  // ObjectFactory objectFactory = new DefaultObjectFactory();
  //
  // // When
  // JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
  // Object proxy = proxyFactory.createProxy(originalAuthor, lazyLoader, configuration, objectFactory, null, null);
  //
  // // Assuming the `bio` property is already loaded
  // originalAuthor.setBio("Loaded Bio");
  //
  // // Then
  // assertEquals("Loaded Bio", ((Author) proxy).getBio());
  // }

  // @Test
  // public void testSerializationAndDeserializationOfProxy() throws Exception {
  // // Given
  // Author originalAuthor = new Author(1, "username", "password", "email@example.com", "bio", null);
  // ResultLoaderMap lazyLoader = new ResultLoaderMap();
  // Configuration configuration = new Configuration();
  // ObjectFactory objectFactory = new DefaultObjectFactory();
  //
  // // Create proxy
  // JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
  // Object proxy = proxyFactory.createProxy(originalAuthor, lazyLoader, configuration, objectFactory, null, null);
  //
  // // Serialize
  // byte[] serializedProxy = serialize((Serializable) proxy);
  //
  // // Deserialize
  // Author deserializedProxy = (Author) deserialize(serializedProxy);
  //
  // // Then
  // assertNotSame(originalAuthor, deserializedProxy); // Ensure different instances
  // assertEquals(originalAuthor.getId(), deserializedProxy.getId());
  // assertEquals(originalAuthor.getUsername(), deserializedProxy.getUsername());
  // assertEquals(originalAuthor.getEmail(), deserializedProxy.getEmail());
  // assertNull(deserializedProxy.getBio()); // Assuming bio was not loaded in the proxy
  // }

}

// KItest: 1-3 4/4; Mini: 0/4
