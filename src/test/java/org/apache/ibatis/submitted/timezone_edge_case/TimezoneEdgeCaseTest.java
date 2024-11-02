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
package org.apache.ibatis.submitted.timezone_edge_case;

import static org.apache.ibatis.io.Resources.getResourceAsReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimezoneEdgeCaseTest {

  private static SqlSessionFactory sqlSessionFactory;
  private TimeZone timeZone;

  @BeforeAll
  static void setUp() throws Exception {
    try (Reader reader = getResourceAsReader("org/apache/ibatis/submitted/timezone_edge_case/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
    BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
        "org/apache/ibatis/submitted/timezone_edge_case/CreateDB.sql");
  }

  @BeforeEach
  void saveTimeZone() {
    timeZone = TimeZone.getDefault();
  }

  @AfterEach
  void restoreTimeZone() {
    TimeZone.setDefault(timeZone);
  }

  // @Test
  // void shouldSelectNonExistentLocalTimestampAsIs() {
  // // Newer hsqldb requires we use a bogus timezone as timezone now works
  // TimeZone.setDefault(TimeZone.getTimeZone("Bad/Zone"));
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // Mapper mapper = sqlSession.getMapper(Mapper.class);
  // Record record = mapper.selectById(1);
  // assertEquals(LocalDateTime.of(LocalDate.of(2019, 3, 10), LocalTime.of(2, 30)), record.getTs());
  // }
  // }
  //
  // @Test
  // void shouldInsertNonExistentLocalTimestampAsIs() throws Exception {
  // // Newer hsqldb requires we use a bogus timezone as timezone now works
  // TimeZone.setDefault(TimeZone.getTimeZone("Bad/Zone"));
  // LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2019, 3, 10), LocalTime.of(2, 30));
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // Mapper mapper = sqlSession.getMapper(Mapper.class);
  // Record record = new Record();
  // record.setId(2);
  // record.setTs(localDateTime);
  // mapper.insert(record);
  // sqlSession.commit();
  // }
  // try (SqlSession sqlSession = sqlSessionFactory.openSession(); Connection con = sqlSession.getConnection();
  // Statement stmt = con.createStatement();
  // ResultSet rs = stmt.executeQuery("select count(*) from records where id = 2 and ts = '2019-03-10 02:30:00'")) {
  // rs.next();
  // assertEquals(1, rs.getInt(1));
  // }
  // }
  //
  // @Test
  // void shouldSelectNonExistentLocalDateAsIs() {
  // // Newer hsqldb requires we use a bogus timezone as timezone now works
  // TimeZone.setDefault(TimeZone.getTimeZone("Bad/Zone"));
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // Mapper mapper = sqlSession.getMapper(Mapper.class);
  // Record record = mapper.selectById(1);
  // assertEquals(LocalDate.of(2011, 12, 30), record.getD());
  // }
  // }
  //
  // @Test
  // void shouldInsertNonExistentLocalDateAsIs() throws Exception {
  // // Newer hsqldb requires we use a bogus timezone as timezone now works
  // TimeZone.setDefault(TimeZone.getTimeZone("Bad/Zone"));
  // LocalDate localDate = LocalDate.of(2011, 12, 30);
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
  // Mapper mapper = sqlSession.getMapper(Mapper.class);
  // Record record = new Record();
  // record.setId(3);
  // record.setD(localDate);
  // mapper.insert(record);
  // sqlSession.commit();
  // }
  // try (SqlSession sqlSession = sqlSessionFactory.openSession(); Connection con = sqlSession.getConnection();
  // Statement stmt = con.createStatement();
  // ResultSet rs = stmt.executeQuery("select count(*) from records where id = 3 and d = '2011-12-30'")) {
  // rs.next();
  // assertEquals(1, rs.getInt(1));
  // }
  // }

  // KItest

//  @Test
//  void shouldSelectNonExistentLocalTimestampAsIs() {
//    try (SqlSession session = sqlSessionFactory.openSession()) {
//      Mapper mapper = session.getMapper(Mapper.class);
//      Record record = mapper.selectById(1);
//
//      // Angenommen, der nicht existierende Zeitstempel ist am 29. März 2020 um 02:30 Uhr
//      LocalDateTime expectedTimestamp = LocalDateTime.of(2019, 3, 10, 2, 30);
//      assertEquals(expectedTimestamp, record.getTs());
//    }
//  }
//
//  @Test
//  void shouldInsertNonExistentLocalTimestampAsIs() {
//    try (SqlSession session = sqlSessionFactory.openSession()) {
//      Mapper mapper = session.getMapper(Mapper.class);
//
//      // Angenommen, der nicht existierende Zeitstempel ist am 29. März 2020 um 02:30 Uhr
//      LocalDateTime nonExistentTimestamp = LocalDateTime.of(2020, 3, 29, 2, 30);
//
//      Record record = new Record();
//      record.setId(2);
//      record.setTs(nonExistentTimestamp);
//
//      mapper.insert(record);
//      session.commit();
//
//      Record retrievedRecord = mapper.selectById(2);
//      assertEquals(nonExistentTimestamp, retrievedRecord.getTs());
//    }
//  }
//
//  @Test
//  void shouldSelectNonExistentLocalDateAsIs() {
//    try (SqlSession session = sqlSessionFactory.openSession()) {
//      Mapper mapper = session.getMapper(Mapper.class);
//
//      // Erstellen und Einfügen des Datensatzes, falls nicht vorhanden
//      Record record = new Record();
//      record.setId(3);
//
//      // Angenommen, das problematische Datum ist der 28. Februar 2019
//      LocalDate problematicDate = LocalDate.of(2019, 2, 28);
//      record.setD(problematicDate);
//
//      // Ein Datensatz wird nur eingefügt, wenn er nicht existiert
//      // Hier muss sichergestellt werden, dass die ID nicht bereits in der DB vorhanden ist
//      // Diese Logik ist nur ein Beispiel. In der Realität sollten Sie ggf. prüfen, ob der Eintrag bereits existiert.
//      try {
//        mapper.insert(record);
//        session.commit();
//      } catch (Exception e) {
//        session.rollback();
//        // Logik, um zu verhindern, dass doppelte Einträge scheitern.
//        // Im Produktionscode könnte man prüfen, ob der Eintrag bereits existiert.
//      }
//
//      Record retrievedRecord = mapper.selectById(3);
//      assertEquals(problematicDate, retrievedRecord.getD());
//    }
//  }
//
//  @Test
//  void shouldInsertNonExistentLocalDateAsIs() {
//    try (SqlSession session = sqlSessionFactory.openSession()) {
//      Mapper mapper = session.getMapper(Mapper.class);
//
//      // Angenommen, das problematische Datum ist der 29. Februar 2019, ein nicht existierender Tag
//      LocalDate problematicDate = LocalDate.of(2019, 2, 28); // Beispielwert
//
//      Record record = new Record();
//      record.setId(4);
//      record.setD(problematicDate);
//
//      mapper.insert(record);
//      session.commit();
//
//      Record retrievedRecord = mapper.selectById(4);
//      assertEquals(problematicDate, retrievedRecord.getD());
//    }
//  }

  // Mini

//   @Test
//   void shouldSelectNonExistentLocalTimestampAsIs() {
//   // Arrange
//   try (SqlSession session = sqlSessionFactory.openSession()) {
//   Mapper mapper = session.getMapper(Mapper.class);
//   Integer id = 1; // Vorhandene ID, die verwendet wird
//   LocalDateTime expectedTimestamp = LocalDateTime.of(2023, 3, 26, 2, 30); // Beispiel eines nicht existierenden
//   // Zeitstempels in bestimmten Zeitzonen
//
//   // Löschen aller Datensätze mit der ID 1, um sicherzustellen, dass wir keine
//   // Konflikte haben. Da Sie keine separate Löschmethode verwenden möchten,
//   // müssten Sie sicherstellen, dass die Datenbank in einem sauberen Zustand ist.
//   session.delete("deleteRecord", id); // Dies geht davon aus, dass die Tabelle zu Beginn leer ist.
//
//   // Erstellen und Einfügen des Datensatzes
//   Record record = new Record();
//   record.setId(id);
//   record.setTs(expectedTimestamp);
//   record.setD(LocalDate.of(2023, 3, 26));
//   mapper.insert(record);
//
//   // Act
//   Record retrievedRecord = mapper.selectById(id);
//
//   // Assert
//   assertEquals(expectedTimestamp, retrievedRecord.getTs());
//   }
//   }

   @Test
   void shouldInsertNonExistentLocalTimestampAsIs() {
   // Arrange
   try (SqlSession session = sqlSessionFactory.openSession()) {
   Mapper mapper = session.getMapper(Mapper.class);
   Record record = new Record();
   record.setId(2); // Verwendung einer eindeutigen ID
   LocalDateTime timestampToInsert = LocalDateTime.of(2023, 3, 26, 2, 30); // Nicht existierender Zeitstempel in
   // bestimmten Zeitzonen
   record.setTs(timestampToInsert);
   record.setD(LocalDate.of(2023, 3, 26));

   // Act
   int rowsAffected = mapper.insert(record);
   session.commit(); // Um sicherzustellen, dass die Einfügung abgeschlossen ist

   // Assert
   assertEquals(1, rowsAffected);

   // Überprüfung
   Record insertedRecord = mapper.selectById(2);
   assertEquals(timestampToInsert, insertedRecord.getTs());
   }
   }

   @Test
   void shouldSelectNonExistentLocalDateAsIs() {
   // Arrange
   try (SqlSession session = sqlSessionFactory.openSession()) {
   Mapper mapper = session.getMapper(Mapper.class);
   Integer id = 3; // Vorhandene ID
   LocalDate expectedDate = LocalDate.of(2023, 3, 26);

   Record record = new Record();
   record.setId(id);
   record.setTs(LocalDateTime.now());
   record.setD(expectedDate);
   mapper.insert(record);

   // Act
   Record retrievedRecord = mapper.selectById(id);

   // Assert
   assertEquals(expectedDate, retrievedRecord.getD());
   }
   }

   @Test
   void shouldInsertNonExistentLocalDateAsIs() {
   // Arrange
   try (SqlSession session = sqlSessionFactory.openSession()) {
   Mapper mapper = session.getMapper(Mapper.class);
   Record record = new Record();
   record.setId(4); // Neue eindeutige ID
   LocalDate dateToInsert = LocalDate.of(2023, 3, 26); // Problematisches Datum in einigen Zeitzonen

   record.setD(dateToInsert);
   record.setTs(LocalDateTime.now()); // Setze aktuelle Zeit für den Zeitstempel

   // Act
   int rowsAffected = mapper.insert(record);
   session.commit(); // Um sicherzustellen, dass die Einfügung abgeschlossen ist

   // Assert
   assertEquals(1, rowsAffected);

   // Überprüfung
   Record insertedRecord = mapper.selectById(4);
   assertEquals(dateToInsert, insertedRecord.getD());
   }
   }

}
// KItest: 3-4 4/4; Mini: 1-4 3/4
