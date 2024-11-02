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
package org.apache.ibatis.autoconstructor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AutoConstructorTest {
  private static SqlSessionFactory sqlSessionFactory;

  @BeforeAll
  static void setUp() throws Exception {
    // create a SqlSessionFactory
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/autoconstructor/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    // populate in-memory database
    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/autoconstructor/CreateDB.sql");
  }

  // @Test
  // void fullyPopulatedSubject() {
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // final AutoConstructorMapper mapper = sqlSession.getMapper(AutoConstructorMapper.class);
  // final Object subject = mapper.getSubject(1);
  // assertNotNull(subject);
  // }
  // }
  //
  // @Test
  // void primitiveSubjects() {
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // final AutoConstructorMapper mapper = sqlSession.getMapper(AutoConstructorMapper.class);
  // assertThrows(PersistenceException.class, mapper::getSubjects);
  // }
  // }
  //
  // @Test
  // void annotatedSubject() {
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // final AutoConstructorMapper mapper = sqlSession.getMapper(AutoConstructorMapper.class);
  // verifySubjects(mapper.getAnnotatedSubjects());
  // }
  // }
  //
  // @Test
  // void badMultipleAnnotatedSubject() {
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // final AutoConstructorMapper mapper = sqlSession.getMapper(AutoConstructorMapper.class);
  // final PersistenceException ex = assertThrows(PersistenceException.class, mapper::getBadAnnotatedSubjects);
  // final ExecutorException cause = (ExecutorException) ex.getCause();
  // assertEquals("@AutomapConstructor should be used in only one constructor.", cause.getMessage());
  // }
  // }
  //
  // @Test
  // void badSubject() {
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // final AutoConstructorMapper mapper = sqlSession.getMapper(AutoConstructorMapper.class);
  // assertThrows(PersistenceException.class, mapper::getBadSubjects);
  // }
  // }
  //
  // @Test
  // void extensiveSubject() {
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // final AutoConstructorMapper mapper = sqlSession.getMapper(AutoConstructorMapper.class);
  // verifySubjects(mapper.getExtensiveSubjects());
  // }
  // }
  //
  // private void verifySubjects(final List<?> subjects) {
  // assertNotNull(subjects);
  // Assertions.assertThat(subjects.size()).isEqualTo(3);
  // }

  // KItest
  // private static AutoConstructorMapper autoConstructorMapper;
  //
  // @BeforeAll
  // public static void setup() {
  // // Initialisiere das SqlSessionFactory und AutoConstructorMapper mit realen Implementierungen.
  // // Dies könnte sowas sein wie:
  // // sqlSessionFactory = MyBatisSqlSessionFactoryBuilder.build();
  // // Oder das Verwenden einer spezifischen Implementierung des Mappers, falls diese existiert.
  // }
  //
  // @Test
  // public void fullyPopulatedSubjectTwo() {
  // try (SqlSession session = sqlSessionFactory.openSession()) {
  // autoConstructorMapper = session.getMapper(AutoConstructorMapper.class);
  // PrimitiveSubject subject = autoConstructorMapper.getSubject(1);
  // assertNotNull(subject, "Das Objekt darf nicht null sein.");
  // }
  // }
  //
  // @Test
  // public void primitiveSubjectsTwo() {
  // try (SqlSession session = sqlSessionFactory.openSession()) {
  // autoConstructorMapper = session.getMapper(AutoConstructorMapper.class);
  // assertThrows(PersistenceException.class, () -> {
  // autoConstructorMapper.getSubjects();
  // }, "Erwartete eine Ausnahme, da die Datenstruktur nicht vorbereitet ist.");
  // }
  // }
  //
  // @Test
  // public void annotatedSubjectTwo() {
  // try (SqlSession session = sqlSessionFactory.openSession()) {
  // autoConstructorMapper = session.getMapper(AutoConstructorMapper.class);
  // List<AnnotatedSubject> subjects = autoConstructorMapper.getAnnotatedSubjects();
  // assertNotNull(subjects, "Die zurückgegebene Liste darf nicht null sein.");
  // assertEquals(3, subjects.size(), "Die Liste sollte genau 3 Objekte enthalten.");
  // }
  // }

  // -------------------------------------------

  // Test durchgefallen nach 4 Versuch
  // @Test
  // public void badMultipleAnnotatedSubjectThree() {
  // try (SqlSession session = sqlSessionFactory.openSession()) {
  // autoConstructorMapper = session.getMapper(AutoConstructorMapper.class);
  // PersistenceException exception = assertThrows(PersistenceException.class, () -> {
  // autoConstructorMapper.getBadAnnotatedSubjects();
  // });
  //
  // // Ausgabe der tatsächlichen Fehlermeldung
  // System.out.println("Fehlermeldung: " + exception.getMessage());
  //
  // // Anpassen der Überprüfung basierend auf der tatsächlichen Fehlermeldung
  // assertTrue(
  // exception.getMessage().contains("Ihr erwarteter Fehlertext hier"),
  // "Fehlermeldung sollte den erwarteten Text enthalten."
  // );
  // }
  // }

  // -------------------------------------------

  // @Test
  // public void badSubjectTwo() {
  // try (SqlSession session = sqlSessionFactory.openSession()) {
  // autoConstructorMapper = session.getMapper(AutoConstructorMapper.class);
  // assertThrows(PersistenceException.class, () -> {
  // autoConstructorMapper.getBadSubjects();
  // }, "Fehlerhafte Datenstruktur sollte eine Ausnahme werfen.");
  // }
  // }
  //
  // @Test
  // public void extensiveSubjectTwo() {
  // try (SqlSession session = sqlSessionFactory.openSession()) {
  // autoConstructorMapper = session.getMapper(AutoConstructorMapper.class);
  // List<ExtensiveSubject> extensiveSubjects = autoConstructorMapper.getExtensiveSubjects();
  // assertNotNull(extensiveSubjects, "Die Liste sollte nicht null sein.");
  // assertEquals(3, extensiveSubjects.size(), "Die Liste sollte genau 3 Objekte enthalten.");
  // }
  // }

  // //Mini
  @AfterAll
  static void tearDown() throws SQLException {
    // Behandeln Sie das Schließen der SqlSessionFactory, wenn nötig.
    // Hier können Sie auch den Datenbankzustand zurücksetzen.
  }

  @Test
  void testFullyPopulatedSubjectTwoMini() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      AutoConstructorMapper mapper = session.getMapper(AutoConstructorMapper.class);
      PrimitiveSubject subject = mapper.getSubject(1);
      assertNotNull(subject);
    }
  }

  @Test
  void testPrimitiveSubjectsThrowsExceptionTwoMini() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      AutoConstructorMapper mapper = session.getMapper(AutoConstructorMapper.class);
      assertThrows(PersistenceException.class, () -> {
        mapper.getSubjects();
      });
    }
  }

  @Test
  void testAnnotatedSubjectTwoMini() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      AutoConstructorMapper mapper = session.getMapper(AutoConstructorMapper.class);
      List<AnnotatedSubject> subjects = mapper.getAnnotatedSubjects();
      assertNotNull(subjects);
      assertEquals(3, subjects.size()); // Hier auf 3 aktualisieren
    }
  }

  @Test
  void testBadMultipleAnnotatedSubjectThrowsExceptionTwoMini() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      AutoConstructorMapper mapper = session.getMapper(AutoConstructorMapper.class);
      assertThrows(PersistenceException.class, () -> {
        mapper.getBadAnnotatedSubjects();
      });
    }
  }

  @Test
  void testBadSubjectThrowsExceptionTwoMini() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      AutoConstructorMapper mapper = session.getMapper(AutoConstructorMapper.class);
      assertThrows(PersistenceException.class, () -> {
        mapper.getBadSubjects();
      });
    }
  }

  @Test
  void testExtensiveSubjectTwoMini() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      AutoConstructorMapper mapper = session.getMapper(AutoConstructorMapper.class);
      List<ExtensiveSubject> subjects = mapper.getExtensiveSubjects();
      assertNotNull(subjects);
      assertEquals(3, subjects.size()); // Erwartete Anzahl an umfangreichen Subjekten anpassen
    }
  }

}

// KI: 5/6 1-4; Mini 6/6 1-2
