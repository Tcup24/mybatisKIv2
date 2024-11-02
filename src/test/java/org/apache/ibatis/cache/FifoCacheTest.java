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
package org.apache.ibatis.cache;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.ibatis.cache.decorators.FifoCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FifoCacheTest {

  // @Test
  // void shouldRemoveFirstItemInBeyondFiveEntries() {
  // FifoCache cache = new FifoCache(new PerpetualCache("default"));
  // cache.setSize(5);
  // for (int i = 0; i < 5; i++) {
  // cache.putObject(i, i);
  // }
  // assertEquals(0, cache.getObject(0));
  // cache.putObject(5, 5);
  // assertNull(cache.getObject(0));
  // assertEquals(5, cache.getSize());
  // }
  //
  // @Test
  // void shouldRemoveItemOnDemand() {
  // FifoCache cache = new FifoCache(new PerpetualCache("default"));
  // cache.putObject(0, 0);
  // assertNotNull(cache.getObject(0));
  // cache.removeObject(0);
  // assertNull(cache.getObject(0));
  // }
  //
  // @Test
  // void shouldFlushAllItemsOnDemand() {
  // FifoCache cache = new FifoCache(new PerpetualCache("default"));
  // for (int i = 0; i < 5; i++) {
  // cache.putObject(i, i);
  // }
  // assertNotNull(cache.getObject(0));
  // assertNotNull(cache.getObject(4));
  // cache.clear();
  // assertNull(cache.getObject(0));
  // assertNull(cache.getObject(4));
  // }
  //
  // @Test
  // void shouldRiseConflictInBeyondFiveEntries() {
  // FifoCache cache = new FifoCache(new PerpetualCache("default"));
  // cache.setSize(5);
  // for (int i = 0; i < 5; i++) {
  // cache.putObject(i, i);
  // }
  // cache.removeObject(1);
  // cache.putObject(1, 1);
  // assertNotNull(cache.getObject(0));
  // }

  // KItest
  private FifoCache cache;

  @BeforeEach
  public void setUp() {
    Cache perpetualCache = new PerpetualCache("testCache");
    cache = new FifoCache(perpetualCache);
    cache.setSize(5); // Setze die Größe des Caches auf 5
  }

  @Test
  public void shouldRemoveFirstItemInBeyondFiveEntriesTwo() {
    // Fülle den Cache mit 5 Einträgen
    cache.putObject(1, "value1");
    cache.putObject(2, "value2");
    cache.putObject(3, "value3");
    cache.putObject(4, "value4");
    cache.putObject(5, "value5");

    // Füge einen weiteren Eintrag hinzu, sodass der älteste Eintrag entfernt wird
    cache.putObject(6, "value6");

    // Überprüfe, ob der erste Eintrag entfernt wurde
    assertNull(cache.getObject(1)); // Der Eintrag mit Schlüssel 1 sollte entfernt worden sein
    assertNotNull(cache.getObject(2)); // Der Eintrag mit Schlüssel 2 sollte noch existieren
  }

  @Test
  public void shouldRemoveItemOnDemandTwo() {
    // Füge einen Eintrag hinzu und entferne diesen
    cache.putObject(1, "value1");
    cache.removeObject(1);

    // Überprüfe, ob der Eintrag entfernt wurde
    assertNull(cache.getObject(1)); // Der Eintrag mit Schlüssel 1 sollte entfernt worden sein
  }

  @Test
  public void shouldFlushAllItemsOnDemandTwo() {
    // Fülle den Cache mit mehreren Einträgen
    cache.putObject(1, "value1");
    cache.putObject(2, "value2");
    cache.putObject(3, "value3");

    // Leere den Cache
    cache.clear();

    // Überprüfe, ob alle Einträge entfernt wurden
    assertNull(cache.getObject(1));
    assertNull(cache.getObject(2));
    assertNull(cache.getObject(3));
    assertEquals(0, cache.getSize());
  }

  @Test
  public void shouldRiseConflictInBeyondFiveEntriesTwo() {
    // Fülle den Cache mit 5 Einträgen
    cache.putObject(1, "value1");
    cache.putObject(2, "value2");
    cache.putObject(3, "value3");
    cache.putObject(4, "value4");
    cache.putObject(5, "value5");

    // Entferne und füge einen bestimmten Eintrag erneut hinzu
    cache.removeObject(3);
    assertNull(cache.getObject(3)); // Stelle sicher, dass der Eintrag entfernt wurde
    cache.putObject(3, "newValue3");

    // Füge einen weiteren Eintrag hinzu, um zu testen, dass keine unerwarteten Konflikte auftreten
    cache.putObject(6, "value6");

    // Bestätige, dass die Einträge korrekt angepasst sind
    assertNull(cache.getObject(1)); // Erster Eintrag sollte entfernt sein
    assertNotNull(cache.getObject(2)); // Die anderen Einträge sollten existieren
    assertNotNull(cache.getObject(3)); // Der neu eingefügte Eintrag sollte existieren
    assertNotNull(cache.getObject(4));
    assertNotNull(cache.getObject(5));
    assertNotNull(cache.getObject(6));
  }

  // Mini

//   private Cache fifoCache;
//   private Cache perpetualCache;
//
//   @BeforeEach
//   void setUp() {
//   perpetualCache = new PerpetualCache("testCache");
//   fifoCache = new FifoCache(perpetualCache);
//   // Setze die maximale Anzahl der Einträge direkt im Konstruktor
//   // (Hier/Reiter im Code muss in der FifoCache-Klasse entsprechend implementiert werden)
//   }

   //durchgefallen nach 4 Versuch
//   @Test
//   void shouldRemoveFirstItemInBeyondFiveEntriesTwoMini() {
//   // Füge 6 Einträge hinzu
//   for (int i = 1; i <= 6; i++) {
//   fifoCache.putObject(i, "value" + i);
//   }
//
//   // Überprüfe die Größe des Caches (soll 5 sein)
//   assertEquals(5, fifoCache.getSize());
//
//   // Überprüfe, dass der erste Eintrag entfernt wurde
//   assertNull(fifoCache.getObject(1)); // 1 sollte entfernt worden sein
//   assertEquals("value2", fifoCache.getObject(2)); // 2 sollte noch vorhanden sein
//   assertEquals("value3", fifoCache.getObject(3)); // 3 sollte noch vorhanden sein
//   assertEquals("value4", fifoCache.getObject(4)); // 4 sollte noch vorhanden sein
//   assertEquals("value5", fifoCache.getObject(5)); // 5 sollte noch vorhanden sein
//   assertEquals("value6", fifoCache.getObject(6)); // 6 sollte noch vorhanden sein
//   }

//   @Test
//   void shouldRemoveItemOnDemandTwoMini() {
//   fifoCache.putObject(1, "value1");
//   fifoCache.putObject(2, "value2");
//
//   // Entferne explizit ein Item
//   fifoCache.removeObject(1);
//
//   // Überprüfe, dass das entfernte Item nicht mehr im Cache ist
//   assertNull(fifoCache.getObject(1));
//   assertEquals("value2", fifoCache.getObject(2));
//   }
//
//   @Test
//   void shouldFlushAllItemsOnDemandTwoMini() {
//   fifoCache.putObject(1, "value1");
//   fifoCache.putObject(2, "value2");
//
//   // Alle Einträge löschen
//   fifoCache.clear();
//
//   // Überprüfe, dass keine Einträge mehr vorhanden sind
//   assertEquals(0, fifoCache.getSize());
//   assertNull(fifoCache.getObject(1));
//   assertNull(fifoCache.getObject(2));
//   }
//
//   @Test
//   void shouldRiseConflictInBeyondFiveEntriesTwoMini() {
//   // Füge 5 Einträge hinzu
//   for (int i = 1; i <= 5; i++) {
//   fifoCache.putObject(i, "value" + i);
//   }
//
//   // Entferne ein Element (z.B. 3)
//   fifoCache.removeObject(3);
//
//   // Füge das entfernte Element wieder hinzu
//   fifoCache.putObject(3, "value3_new");
//
//   // Überprüfe, dass das neu hinzugefügte Element vorhanden ist
//   assertEquals("value3_new", fifoCache.getObject(3));
//
//   // Überprüfe, dass andere Elemente noch vorhanden sind
//   assertEquals("value1", fifoCache.getObject(1));
//   assertEquals("value2", fifoCache.getObject(2));
//   assertEquals("value4", fifoCache.getObject(4));
//   assertEquals("value5", fifoCache.getObject(5));
//
//   // Überprüfe die Größe des Caches (soll immer noch 5 sein)
//   assertEquals(5, fifoCache.getSize());
//   }

  // KItest: 4/4 1; Mini 1-4 3/4

}
