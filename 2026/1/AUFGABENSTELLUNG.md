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

# Prüfungsaufgabe: „Opala Light"

## Spielablauf

Bei „Opala Light" spielen zwei Spieler gegeneinander auf einem quadratischen Spielbrett
mit 6 Zeilen und 6 Spalten. Jeder Spieler besitzt zu Beginn sechs Spielfiguren.

**Spieler 1** steht unten (Zeile 5) und zieht nach oben, **Spieler 2** steht oben (Zeile 0)
und zieht nach unten. Spieler 1 beginnt. Es wird abwechselnd gezogen.

### Die Spielfiguren

Es gibt drei Formen (Quadrat, Dreieck, Kreis) in je zwei Groessen. Daraus ergeben sich
sechs Figurtypen. Im Spielbrett werden die Figuren als ganze Zahlen gespeichert:
**positive Zahlen gehoeren Spieler 1, negative Zahlen gehoeren Spieler 2**, die `0`
bezeichnet ein leeres Feld.

| Typ | Figur | Zugrichtung | Reichweite |
|-----|-------|-------------|------------|
| 1 | kleines Quadrat | waagerecht oder senkrecht | genau 1 Feld |
| 2 | grosses Quadrat | waagerecht oder senkrecht | beliebig viele Felder |
| 3 | kleines Dreieck | diagonal | genau 1 Feld |
| 4 | grosses Dreieck | diagonal | beliebig viele Felder |
| 5 | kleiner Kreis | waagerecht, senkrecht oder diagonal | genau 1 Feld |
| 6 | grosser Kreis | waagerecht, senkrecht oder diagonal | beliebig viele Felder |

Die Figur mit dem Wert `4` ist also ein grosses Dreieck von Spieler 1, die Figur mit dem
Wert `-4` ein grosses Dreieck von Spieler 2.

### Grundaufstellung

Spieler 1 erhaelt in Zeile 5 die Figuren 1 bis 6 (in dieser Reihenfolge von Spalte 0 bis
Spalte 5), Spieler 2 in Zeile 0 die Figuren -1 bis -6. Alle anderen Felder sind leer:

```
      0   1   2   3   4   5
----+------------------------
  0 |  -1  -2  -3  -4  -5  -6
  1 |   -   -   -   -   -   -
  2 |   -   -   -   -   -   -
  3 |   -   -   -   -   -   -
  4 |   -   -   -   -   -   -
  5 |   1   2   3   4   5   6
```

### Regeln fuer einen Zug

Ein Zug besteht aus einem Startfeld (Zeile, Spalte) und einem Zielfeld (Zeile, Spalte).
Ein Zug ist genau dann gueltig, wenn **alle** folgenden Bedingungen erfuellt sind:

1. Start- und Zielfeld liegen im Spielbrett.
2. Start- und Zielfeld sind nicht dasselbe Feld.
3. Auf dem Startfeld steht eine **eigene** Figur des ziehenden Spielers.
4. Auf dem Zielfeld steht **keine eigene** Figur (es ist leer oder eine gegnerische Figur).
5. Die Zugrichtung passt zum Figurtyp: waagerecht oder senkrecht bedeutet, dass sich
   entweder die Zeile oder die Spalte nicht aendert; diagonal bedeutet, dass sich Zeile
   und Spalte um denselben Betrag aendern. Jede andere Richtung (etwa wie ein Springer
   beim Schach) ist nie erlaubt.
6. Die Reichweite passt zum Figurtyp: kleine Figuren ziehen genau ein Feld weit.
7. Der Weg ist frei: zwischen Start- und Zielfeld darf keine andere Figur stehen.
   Das Zielfeld selbst zaehlt nicht zum Weg.

Steht auf dem Zielfeld eine gegnerische Figur, so wird diese geschlagen, also vom
Spielbrett entfernt.

### Spielende

Das Spiel endet,

- wenn ein Spieler mit einer eigenen Figur die gegenueberliegende Grundlinie erreicht hat
  (Spieler 1 die Zeile 0, Spieler 2 die Zeile 5). Dieser Spieler hat gewonnen; oder
- wenn ein Spieler keine Figuren mehr besitzt. In diesem Fall hat sein Gegner gewonnen.

---

## Programmablauf

Das Programm startet mit dem folgenden Auswahlmenue:

```
Opala Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Wird „1 – Spiel starten" ausgewaehlt, werden die Namen der beiden Spieler eingelesen
  und das Spiel gestartet.
  - Im laufenden Spiel wird abwechselnd das Spielbrett ausgegeben und der aktive Spieler
    nach Start- und Zielfeld seines Zuges gefragt.
  - Ist der Zug nicht gueltig, wird eine Meldung ausgegeben und erneut nach Start- und
    Zielfeld gefragt. Dies erfolgt so lange, bis ein gueltiger Zug eingegeben wurde.
  - Wird beim Zug eine gegnerische Figur geschlagen, wird dies gemeldet.
  - Nach jedem Zug wird geprueft, ob das Spiel beendet ist.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Wird „2 – Programm beenden" ausgewaehlt, so wird das Programm beendet.

## Umgang mit falschen Eingaben

- Im Auswahlmenue sowie bei der Eingabe von Zeilen und Spalten duerfen Sie davon
  ausgehen, dass nur ganze Zahlen eingegeben werden.
- Bei der Eingabe der Spielernamen duerfen Sie von korrekten Eingaben ausgehen.
- Sie muessen bei der Eingabe eines Zuges nur pruefen, ob der Zug nach den oben
  beschriebenen Regeln gueltig ist. Sie muessen **nicht** pruefen, ob der Zug sinnvoll ist.
  Insbesondere darf ein Spieler einen Zug machen, durch den er im naechsten Zug
  seinerseits geschlagen wird.

## Konsolenein- und -ausgaben

- Am Ende der Aufgabenstellung finden Sie einen beispielhaften Programmdurchlauf.
  Ihre Konsolenausgaben muessen dem Schema dieses Beispiels folgen.
- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

- Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielbrett` und `Figur`
  enthalten.
- In der Projektvorlage sind diese Klassen bereits angelegt. Kommentare in den Dateien
  beschreiben, was jeweils zu tun ist. Fuegen Sie Code hinzu, der die gewuenschten
  Funktionen implementiert, und aendern Sie dazu gegebenenfalls auch die
  `return`-Anweisungen. Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.
- Sie duerfen weitere Klassen und in den vorhandenen Klassen weitere Attribute und
  Methoden ergaenzen, wo Ihnen dies sinnvoll erscheint.

### Klasse `Figur`

Die Klasse `Figur` enthaelt ausschliesslich **statische** Methoden, die den Zahlenwert einer
Figur auswerten. Sie besitzt keine Attribute.

Beachten Sie, dass der Typ einer Figur der Betrag ihres Zahlenwertes ist: sowohl `4` als
auch `-4` haben den Typ 4. Eine Aufzaehlung aller Faelle ist bei `typ` nicht erlaubt;
berechnen Sie den Wert.

### Klasse `Spielbrett`

Die Klasse `Spielbrett` hat das Attribut `int[][] feld`, in dem die Figuren gespeichert
werden, sowie das Attribut `int n` fuer die Groesse des Brettes.

Zwei Hilfsmethoden sind bereits vorgegeben, da die Klasse `Math` nicht verwendet werden darf:

- `betrag(int zahl)` liefert den Betrag einer Zahl.
- `vorzeichen(int zahl)` liefert `-1`, `0` oder `1`.

Die Methode `toString()` ist ebenfalls vorgegeben.

### Klasse `Zugpruefung`

Die eigentliche Zugpruefung findet in `Spielbrett.istZugGueltig(...)` statt. Die Methode
ist in der Vorlage in Teilschritte zerlegt; arbeiten Sie diese der Reihe nach ab.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Opala Light

Opala Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
--------------------------------
Alex ist an der Reihe.
      0   1   2   3   4   5
----+------------------------
  0 |  -1  -2  -3  -4  -5  -6
  1 |   -   -   -   -   -   -
  2 |   -   -   -   -   -   -
  3 |   -   -   -   -   -   -
  4 |   -   -   -   -   -   -
  5 |   1   2   3   4   5   6

Startfeld - Zeile: > 5
Startfeld - Spalte: > 1
Zielfeld - Zeile: > 2
Zielfeld - Spalte: > 1
Der Zug wurde ausgefuehrt.
--------------------------------
Toni ist an der Reihe.
      0   1   2   3   4   5
----+------------------------
  0 |  -1  -2  -3  -4  -5  -6
  1 |   -   -   -   -   -   -
  2 |   -   2   -   -   -   -
  3 |   -   -   -   -   -   -
  4 |   -   -   -   -   -   -
  5 |   1   -   3   4   5   6

Startfeld - Zeile: > 0
Startfeld - Spalte: > 5
Zielfeld - Zeile: > 3
Zielfeld - Spalte: > 5
Der Zug wurde ausgefuehrt.
--------------------------------
Alex ist an der Reihe.
[... Spielbrett ...]

Startfeld - Zeile: > 2
Startfeld - Spalte: > 1
Zielfeld - Zeile: > 3
Zielfeld - Spalte: > 2
Dieser Zug ist nicht gueltig.

Startfeld - Zeile: > 2
Startfeld - Spalte: > 1
Zielfeld - Zeile: > 0
Zielfeld - Spalte: > 1
Die Figur -2 wurde geschlagen.
Der Zug wurde ausgefuehrt.

Alex hat die Grundlinie erreicht und gewonnen!

Opala Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
