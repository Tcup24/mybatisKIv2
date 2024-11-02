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
package org.apache.ibatis.parsing;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyParserTest {

  // @Test
  // void replaceToVariableValue() {
  // Properties props = new Properties();
  // props.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
  // props.setProperty("key", "value");
  // props.setProperty("tableName", "members");
  // props.setProperty("orderColumn", "member_id");
  // props.setProperty("a:b", "c");
  // Assertions.assertThat(PropertyParser.parse("${key}", props)).isEqualTo("value");
  // Assertions.assertThat(PropertyParser.parse("${key:aaaa}", props)).isEqualTo("value");
  // Assertions.assertThat(PropertyParser.parse("SELECT * FROM ${tableName:users} ORDER BY ${orderColumn:id}", props))
  // .isEqualTo("SELECT * FROM members ORDER BY member_id");
  //
  // props.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "false");
  // Assertions.assertThat(PropertyParser.parse("${a:b}", props)).isEqualTo("c");
  //
  // props.remove(PropertyParser.KEY_ENABLE_DEFAULT_VALUE);
  // Assertions.assertThat(PropertyParser.parse("${a:b}", props)).isEqualTo("c");
  //
  // }
  //
  // @Test
  // void notReplace() {
  // Properties props = new Properties();
  // props.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
  // Assertions.assertThat(PropertyParser.parse("${key}", props)).isEqualTo("${key}");
  // Assertions.assertThat(PropertyParser.parse("${key}", null)).isEqualTo("${key}");
  //
  // props.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "false");
  // Assertions.assertThat(PropertyParser.parse("${a:b}", props)).isEqualTo("${a:b}");
  //
  // props.remove(PropertyParser.KEY_ENABLE_DEFAULT_VALUE);
  // Assertions.assertThat(PropertyParser.parse("${a:b}", props)).isEqualTo("${a:b}");
  //
  // }
  //
  // @Test
  // void applyDefaultValue() {
  // Properties props = new Properties();
  // props.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
  // Assertions.assertThat(PropertyParser.parse("${key:default}", props)).isEqualTo("default");
  // Assertions.assertThat(PropertyParser.parse("SELECT * FROM ${tableName:users} ORDER BY ${orderColumn:id}", props))
  // .isEqualTo("SELECT * FROM users ORDER BY id");
  // Assertions.assertThat(PropertyParser.parse("${key:}", props)).isEmpty();
  // Assertions.assertThat(PropertyParser.parse("${key: }", props)).isEqualTo(" ");
  // Assertions.assertThat(PropertyParser.parse("${key::}", props)).isEqualTo(":");
  // }
  //
  // @Test
  // void applyCustomSeparator() {
  // Properties props = new Properties();
  // props.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
  // props.setProperty(PropertyParser.KEY_DEFAULT_VALUE_SEPARATOR, "?:");
  // Assertions.assertThat(PropertyParser.parse("${key?:default}", props)).isEqualTo("default");
  // Assertions
  // .assertThat(PropertyParser.parse(
  // "SELECT * FROM ${schema?:prod}.${tableName == null ? 'users' : tableName} ORDER BY ${orderColumn}", props))
  // .isEqualTo("SELECT * FROM prod.${tableName == null ? 'users' : tableName} ORDER BY ${orderColumn}");
  // Assertions.assertThat(PropertyParser.parse("${key?:}", props)).isEmpty();
  // Assertions.assertThat(PropertyParser.parse("${key?: }", props)).isEqualTo(" ");
  // Assertions.assertThat(PropertyParser.parse("${key?::}", props)).isEqualTo(":");
  // }

  // KItest
  @Test
  void testReplaceToVariableValue() {
    Properties properties = new Properties();
    properties.setProperty("username", "testuser");

    // Test ohne Standardwert
    String input = "Welcome, ${username}!";
    assertEquals("Welcome, testuser!", PropertyParser.parse(input, properties));

    // Test mit aktiviertem Standardwert
    properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
    String inputWithDefault = "Welcome, ${username:defaultuser}!";
    assertEquals("Welcome, testuser!", PropertyParser.parse(inputWithDefault, properties));

    // Test ohne passenden Wert aber mit Standardwert
    String inputWithOnlyDefault = "Welcome, ${user:defaultuser}!";
    assertEquals("Welcome, defaultuser!", PropertyParser.parse(inputWithOnlyDefault, properties));
  }

  @Test
  void testApplyDefaultValue() {
    Properties properties = new Properties();
    properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");

    // Test mit Standardwert
    String inputWithDefault = "DB User: ${db.user:postgres}";
    assertEquals("DB User: postgres", PropertyParser.parse(inputWithDefault, properties));

    // Test ohne Standard und gesetztem Wert
    properties.setProperty("db.user", "mysql");
    assertEquals("DB User: mysql", PropertyParser.parse(inputWithDefault, properties));
  }

  @Test
  void testApplyCustomSeparator() {
    Properties properties = new Properties();
    properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
    properties.setProperty(PropertyParser.KEY_DEFAULT_VALUE_SEPARATOR, "|");

    // Test mit benutzerdefiniertem Separator
    String inputWithCustomSeparator = "Path: ${path|/default/path}";
    assertEquals("Path: /default/path", PropertyParser.parse(inputWithCustomSeparator, properties));

    // Test mit gesetztem Wert und benutzerdefiniertem Separator
    properties.setProperty("path", "/custom/path");
    assertEquals("Path: /custom/path", PropertyParser.parse(inputWithCustomSeparator, properties));
  }

  // mini
//   private Properties properties;
//
//   @BeforeEach
//   public void setup() {
//   properties = new Properties();
//   }
//
//   // Test für replaceToVariableValue
//   @Test
//   public void testReplaceToVariableValue_withProperties() {
//   properties.setProperty("username", "admin");
//   properties.setProperty("password", "secret");
//   properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "false");
//
//   String result = PropertyParser.parse("User: ${username}, Password: ${password}", properties);
//   assertEquals("User: admin, Password: secret", result);
//   }
//
//   @Test
//   public void testReplaceToVariableValue_defaultEnabled() {
//   properties.setProperty("username", "admin");
//   properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
//   properties.setProperty("username.defaultUser", "defaultUser"); // hier muss ein Default-Wert gesetzt werden
//
//   String result = PropertyParser.parse("User: ${username:defaultUser}", properties);
//   assertEquals("User: admin", result);
//   }
//
//   // Test für notReplace
//   @Test
//   public void testNotReplace_withoutMatchingProperties() {
//   String result = PropertyParser.parse("User: ${username}", properties);
//   assertEquals("User: ${username}", result);
//   }
//
//   @Test
//   public void testNotReplace_whenPropertyReplacementDisabled() {
//   properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "false");
//   String result = PropertyParser.parse("User: ${username}", properties);
//   assertEquals("User: ${username}", result);
//   }
//
//   // Test für applyDefaultValue
//   @Test
//   public void testApplyDefaultValue_noValue() {
//   properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
//   String result = PropertyParser.parse("User: ${username:defaultUser}", properties);
//   assertEquals("User: defaultUser", result); // sollte den Standardwert einsetzen
//   }
//
//   @Test
//   public void testApplyDefaultValue_withEmptyDefault() {
//   properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
//   String result = PropertyParser.parse("User: ${username:}", properties);
//   assertEquals("User: ", result); // sollte einen leeren Wert zurückgeben
//   }
//
//   // Test für applyCustomSeparator
//   @Test
//   public void testApplyCustomSeparator_customSeparator() {
//   properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
//   properties.setProperty(PropertyParser.KEY_DEFAULT_VALUE_SEPARATOR, "|");
//   properties.setProperty("username", "admin");
//
//   String result = PropertyParser.parse("User: ${username|defaultUser}", properties);
//   assertEquals("User: admin", result); // sollte den Wert ersetzen
//   }
//
//   @Test
//   public void testApplyCustomSeparator_defaultValueNotPresent() {
//   properties.setProperty(PropertyParser.KEY_ENABLE_DEFAULT_VALUE, "true");
//   properties.setProperty(PropertyParser.KEY_DEFAULT_VALUE_SEPARATOR, "|");
//
//   String result = PropertyParser.parse("User: ${username|defaultUser}", properties);
//   assertEquals("User: defaultUser", result); // sollte den Standardwert einsetzen
//   }
}

// KItest: 3-4 3/4; Mini: 3-4 4/4
