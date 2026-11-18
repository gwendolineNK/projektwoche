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

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testIstZugGueltig()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code (etwa zur Verwendung der Java-Bibliothek) einhalten.

---

# Prüfungsaufgabe: „Sowing Light"

## Spielablauf

Bei „Sowing Light" spielen zwei Spieler gegeneinander. Gespielt wird auf einem Brett mit
einer Reihe von n Mulden, die von 0 bis n-1 durchnummeriert sind. In jeder Mulde liegen
zu Beginn m Samen.

**Spieler 1 spielt von links nach rechts**, seine Zugrichtung ist also `+1`.
**Spieler 2 spielt von rechts nach links**, seine Zugrichtung ist `-1`.
Spieler 1 beginnt, danach wird abwechselnd gezogen.

### Der Zug

Wer an der Reihe ist, nennt die Nummer einer Mulde. Der **gesamte Inhalt** dieser Mulde
wird herausgenommen und einzeln auf die in Zugrichtung folgenden Mulden verteilt: in
jede dieser Mulden faellt genau ein Samen, bis alle Samen verteilt sind. Die gewaehlte
Mulde ist danach leer.

Ein Zug ist genau dann **gueltig**, wenn **alle** folgenden Bedingungen erfuellt sind:

1. Die genannte Muldennummer existiert.
2. In der gewaehlten Mulde liegt mindestens ein Samen.
3. In Zugrichtung liegen genug Mulden, damit alle Samen verteilt werden koennen.
   Es darf also kein Samen ueber den Rand des Brettes hinausfallen.
4. Der **letzte** Samen faellt in eine Mulde, die **vor dem Zug nicht leer** war.

Beispiel (n = 6):

```
Mulde:   0   1   2   3   4   5
Samen:   3   1   0   2   4   0
```

- Spieler 1 waehlt Mulde 0: die 3 Samen fallen in die Mulden 1, 2 und 3. Der letzte
  Samen landet in Mulde 3, die mit 2 Samen nicht leer war. Der Zug ist **gueltig**.
  Danach sieht das Brett so aus:

```
Mulde:   0   1   2   3   4   5
Samen:   0   2   1   3   4   0
```

- Spieler 1 waehlt Mulde 1: der eine Samen faellt in Mulde 2, die leer ist.
  Der Zug ist **ungueltig** (Bedingung 4).
- Spieler 1 waehlt Mulde 4: die 4 Samen muessten in die Mulden 5, 6, 7 und 8 fallen.
  Die Mulden 6, 7 und 8 gibt es nicht. Der Zug ist **ungueltig** (Bedingung 3).
- Spieler 2 waehlt Mulde 4: die 4 Samen fallen in die Mulden 3, 2, 1 und 0. Der letzte
  Samen landet in Mulde 0, die nicht leer war. Der Zug ist **gueltig**.

### Spielende

Ist ein Spieler an der Reihe und hat **keinen einzigen gueltigen Zug** mehr, so hat er
verloren und sein Gegner gewonnen. Ein Unentschieden ist nicht moeglich.

---

## Programmablauf

Das Programm startet mit dem folgenden Auswahlmenue:

```
Sowing Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Wird „1 – Spiel starten" ausgewaehlt, werden die Namen der beiden Spieler, die Anzahl
  der Mulden sowie die Anzahl der Samen pro Mulde eingelesen und das Spiel gestartet.
  - Im laufenden Spiel wird das Brett ausgegeben und der aktive Spieler nach einer
    Muldennummer gefragt.
  - Ist der Zug ungueltig, wird eine Meldung ausgegeben und erneut gefragt. Dies erfolgt
    so lange, bis ein gueltiger Zug eingegeben wurde.
  - Bevor ein Spieler gefragt wird, wird geprueft, ob er ueberhaupt noch einen gueltigen
    Zug hat. Ist das nicht der Fall, ist das Spiel zu Ende.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Wird „2 – Programm beenden" ausgewaehlt, so wird das Programm beendet.

## Umgang mit falschen Eingaben

- Im Auswahlmenue, bei der Anzahl der Mulden und der Samen sowie bei der Eingabe der
  Muldennummer duerfen Sie davon ausgehen, dass nur ganze Zahlen eingegeben werden.
- Bei der Eingabe der Spielernamen duerfen Sie von korrekten Eingaben ausgehen.

## Konsolenein- und -ausgaben

- Am Ende der Aufgabenstellung finden Sie einen beispielhaften Programmdurchlauf.
  Ihre Konsolenausgaben muessen dem Schema dieses Beispiels folgen.
- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

- Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielfeld` und `Zug`
  enthalten.
- In der Projektvorlage sind diese Klassen bereits angelegt. Kommentare in den Dateien
  beschreiben, was jeweils zu tun ist. Fuegen Sie Code hinzu, der die gewuenschten
  Funktionen implementiert, und aendern Sie dazu gegebenenfalls auch die
  `return`-Anweisungen. Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.
- Sie duerfen weitere Klassen und in den vorhandenen Klassen weitere Attribute und
  Methoden ergaenzen, wo Ihnen dies sinnvoll erscheint.

### Klasse `Zug`

Die Klasse `Zug` enthaelt ausschliesslich **statische** Methoden, die mit Muldennummern
und Zugrichtungen rechnen. Sie besitzt keine Attribute.

Ueberlegen Sie sich genau, welche Mulde der **letzte** Samen trifft, wenn ab der Mulde
`start` in Richtung `richtung` genau `anzahl` Samen verteilt werden. Der erste Samen
faellt in die Mulde `start + richtung`, der zweite in `start + 2 * richtung` und so weiter.

### Klasse `Spielfeld`

Die Klasse `Spielfeld` hat das Attribut `int[] mulden`, in dem die Anzahl der Samen jeder
Mulde gespeichert wird, sowie das Attribut `int n` fuer die Anzahl der Mulden.
Die Methode `toString()` ist vorgegeben.

### Klasse `Spieler`

Die Klasse `Spieler` hat die Attribute `String name` und `int nummer`.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Sowing Light

Sowing Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie viele Mulden soll das Brett haben?
> 6
Wie viele Samen liegen anfangs in jeder Mulde?
> 2
--------------------------------
Alex ist an der Reihe. Zugrichtung: nach rechts
Mulde:   0   1   2   3   4   5
Samen:   2   2   2   2   2   2

Muldennummer: > 5
Dieser Zug ist nicht gueltig.
Muldennummer: > 1
Alex verteilt 2 Samen.
--------------------------------
Toni ist an der Reihe. Zugrichtung: nach links
Mulde:   0   1   2   3   4   5
Samen:   2   0   3   3   2   2

Muldennummer: > 0
Dieser Zug ist nicht gueltig.
Muldennummer: > 3
Toni verteilt 3 Samen.
--------------------------------
Alex ist an der Reihe. Zugrichtung: nach rechts
Mulde:   0   1   2   3   4   5
Samen:   3   1   4   0   2   2

[...]

--------------------------------
Toni ist an der Reihe. Zugrichtung: nach links
Mulde:   0   1   2   3   4   5
Samen:   0   0   0   0   0   5

Toni hat keinen gueltigen Zug mehr.
Alex hat gewonnen!

Sowing Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
