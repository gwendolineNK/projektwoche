# Einführung in die Programmierung – Projektwoche

## Prüfungsbedingungen

- Es sind keine Hilfsmittel erlaubt.
- Aus der Java-Klassenbibliothek dürfen ausschließlich die folgenden Elemente verwendet werden:
  - Klassen `Scanner` und `Random`
  - Aus der Klasse `String` ausschließlich die Methode `equals()`.
  - Methoden `System.out.print()` und `System.out.println()`
- Verwenden Sie in Ihrem Code nicht die Zeichen ä, ö, ü oder ß, auch nicht in String-Literalen.
- Ihr Code wird teilweise automatisiert getestet. Halten Sie sich unbedingt an alle technischen Vorgaben!
- Die Unit-Tests sind Teil Ihrer Vorlage. Sie können Ihren Code damit jederzeit selbst überprüfen.
  Beachten Sie aber, dass das Bestehen der Tests nicht automatisch bedeutet, dass die Prüfung bestanden ist.

Sie haben die Prüfung sicher bestanden, wenn

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testVerschiebe()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code (etwa zur Verwendung der Java-Bibliothek) einhalten.

---

# Prüfungsaufgabe: „2048 Light"

## Spielablauf

„2048 Light" ist ein Spiel fuer einen einzelnen Spieler. Gespielt wird auf einem
quadratischen Spielfeld mit n Zeilen und n Spalten. Auf dem Spielfeld liegen Kacheln,
die mit Zweierpotenzen (2, 4, 8, 16, ...) beschriftet sind. Der Wert `0` bezeichnet ein
leeres Feld.

Zu Beginn liegen auf zwei zufaellig gewaehlten Feldern jeweils Kacheln mit der Zahl 2.

Der Spieler waehlt in jedem Zug eine der vier Richtungen **links, rechts, oben, unten**.
Alle Kacheln rutschen dann so weit wie moeglich in diese Richtung. Stossen dabei zwei
Kacheln mit derselben Zahl aneinander, so verschmelzen sie zu einer einzigen Kachel mit
der Summe beider Zahlen. Der Spieler erhaelt den Wert der neu entstandenen Kachel als
Punkte gutgeschrieben.

Nach jedem Zug, **aber nur wenn sich dabei tatsaechlich etwas veraendert hat**, erscheint
auf einem zufaellig gewaehlten leeren Feld eine neue Kachel mit der Zahl 2.

### Das Verschieben einer Reihe

Das Verschieben nach links laeuft fuer jede Zeile in drei Schritten ab:

1. **Zusammenschieben:** Alle Kacheln rutschen nach links, Luecken verschwinden.
   Die Reihenfolge der Kacheln bleibt erhalten.
2. **Verschmelzen:** Von links nach rechts wird geprueft, ob zwei benachbarte Kacheln
   denselben Wert haben. Ist das der Fall, verschmelzen sie: die linke Kachel erhaelt die
   Summe, die rechte wird leer. **Jede Kachel darf pro Zug hoechstens einmal verschmelzen.**
3. **Zusammenschieben:** Die durch das Verschmelzen entstandenen Luecken werden
   wieder geschlossen.

Beispiele (Verschieben nach links):

```
2 2 0 4   ->   4 4 0 0      (die beiden 2 verschmelzen zu 4, Punkte: 4)
4 4 4 4   ->   8 8 0 0      (zwei Paare verschmelzen, Punkte: 8 + 8 = 16)
2 0 2 0   ->   4 0 0 0      (Punkte: 4)
2 4 0 0   ->   2 4 0 0      (keine Veraenderung)
```

Beachten Sie das zweite Beispiel: `4 4 4 4` ergibt `8 8 0 0` und **nicht** `16 0 0 0`,
weil jede Kachel pro Zug nur einmal verschmelzen darf.

Die anderen Richtungen lassen sich auf das Verschieben nach links zurueckfuehren:
Fuer „rechts" wird die Reihe vor und nach der Bearbeitung umgedreht. Fuer „oben" und
„unten" werden statt der Zeilen die Spalten bearbeitet.

### Spielende

Das Spiel endet,

- wenn auf dem Spielfeld eine Kachel mit der Zahl **2048** entstanden ist. Der Spieler hat
  gewonnen; oder
- wenn kein Zug mehr moeglich ist. Ein Zug ist moeglich, solange es mindestens ein leeres
  Feld gibt oder zwei waagerecht oder senkrecht benachbarte Kacheln denselben Wert
  haben. In diesem Fall hat der Spieler verloren.

---

## Programmablauf

Das Programm startet mit dem folgenden Auswahlmenue:

```
2048 Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Wird „1 – Spiel starten" ausgewaehlt, werden der Name des Spielers sowie die
  gewuenschte Spielfeldgroesse eingelesen und das Spiel gestartet.
  - Im laufenden Spiel werden das Spielfeld und der Punktestand ausgegeben, danach wird
    nach der Richtung gefragt.
  - Erlaubt sind die Eingaben `l` (links), `r` (rechts), `o` (oben) und `u` (unten).
    Die Frage wird so lange wiederholt, bis eine dieser Eingaben erfolgt ist.
  - Hat sich durch den Zug nichts veraendert, wird dies gemeldet und es erscheint
    **keine** neue Kachel.
  - Nach jedem Zug wird geprueft, ob das Spiel beendet ist.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Wird „2 – Programm beenden" ausgewaehlt, so wird das Programm beendet.

## Umgang mit falschen Eingaben

- Im Auswahlmenue sowie bei der Eingabe der Spielfeldgroesse duerfen Sie davon ausgehen,
  dass nur ganze Zahlen eingegeben werden.
- Bei der Eingabe des Spielernamens duerfen Sie von korrekten Eingaben ausgehen.
- Bei der Eingabe der Richtung werden nur die oben beschriebenen Eingaben akzeptiert.

## Konsolenein- und -ausgaben

- Am Ende der Aufgabenstellung finden Sie einen beispielhaften Programmdurchlauf.
  Ihre Konsolenausgaben muessen dem Schema dieses Beispiels folgen.
- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

- Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielfeld` und `Reihe`
  enthalten.
- In der Projektvorlage sind diese Klassen bereits angelegt. Kommentare in den Dateien
  beschreiben, was jeweils zu tun ist. Fuegen Sie Code hinzu, der die gewuenschten
  Funktionen implementiert, und aendern Sie dazu gegebenenfalls auch die
  `return`-Anweisungen. Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.
- Sie duerfen weitere Klassen und in den vorhandenen Klassen weitere Attribute und
  Methoden ergaenzen, wo Ihnen dies sinnvoll erscheint.

### Klasse `Reihe`

Die Klasse `Reihe` enthaelt ausschliesslich **statische** Methoden, die auf einer einzelnen
Reihe (einem eindimensionalen int-Feld) arbeiten. Sie besitzt keine Attribute.
Diese Methoden bilden die drei Schritte des Verschiebens ab und werden von der Klasse
`Spielfeld` verwendet.

### Klasse `Spielfeld`

Die Klasse `Spielfeld` hat die Attribute `int[][] feld`, `int n`, `int punkte` sowie
`Random rd`.

Fuer die vier Richtungen sind die Konstanten `LINKS`, `RECHTS`, `OBEN` und `UNTEN`
vorgegeben. Die Methode `toString()` ist ebenfalls vorgegeben.

Die Methode `verschiebe(int richtung)` fuehrt einen kompletten Zug auf dem Spielfeld aus
und gibt zurueck, ob sich dabei etwas veraendert hat. Sie erzeugt **keine** neue Kachel;
das uebernimmt die Klasse `Spiel`.

### Klasse `Spieler`

Die Klasse `Spieler` hat die Attribute `String name` und `Spielfeld sf`.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu 2048 Light

2048 Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib deinen Namen ein:
> Alex
Wie gross soll das Spielfeld sein?
> 4
--------------------------------
Alex, dein Spielfeld:
       0     1     2     3
----+------------------------
  0 |  .     .     2     .
  1 |  .     .     .     .
  2 |  .     2     .     .
  3 |  .     .     .     .

Punkte: 0
Richtung (l/r/o/u)?
> l
--------------------------------
Alex, dein Spielfeld:
       0     1     2     3
----+------------------------
  0 |  2     .     .     .
  1 |  .     .     .     2
  2 |  2     .     .     .
  3 |  .     .     .     .

Punkte: 0
Richtung (l/r/o/u)?
> o
--------------------------------
Alex, dein Spielfeld:
       0     1     2     3
----+------------------------
  0 |  4     .     .     2
  1 |  .     .     2     .
  2 |  .     .     .     .
  3 |  .     .     .     .

Punkte: 4
Richtung (l/r/o/u)?
> x
Richtung (l/r/o/u)?
> r
--------------------------------
[...]

Keine Veraenderung. Waehle eine andere Richtung.

[...]

Glueckwunsch Alex! Du hast 2048 erreicht!
Endpunktestand: 20348

2048 Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
