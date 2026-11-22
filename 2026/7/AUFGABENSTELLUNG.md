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

Bei „Opala Light" spielen zwei Spieler auf einem quadratischen Spielbrett mit 6 Zeilen
und 6 Spalten. **Jeder Spieler besitzt sechs eigene Spielfiguren.**

**Spieler 1** steht unten (Zeile 5) und zieht nach oben, **Spieler 2** steht oben (Zeile 0)
und zieht nach unten. Spieler 1 beginnt. Es wird abwechselnd gezogen.

### Die Spielfiguren

Es gibt drei Formen (Quadrat, Dreieck, Kreis) in je zwei Groessen. Daraus ergeben sich
sechs Figurtypen:

| Typ | Figur | Zugrichtung | Reichweite |
|-----|-------|-------------|------------|
| 1 | kleines Quadrat | waagerecht oder senkrecht | genau 1 Feld |
| 2 | grosses Quadrat | waagerecht oder senkrecht | beliebig viele Felder |
| 3 | kleines Dreieck | diagonal | genau 1 Feld |
| 4 | grosses Dreieck | diagonal | beliebig viele Felder |
| 5 | kleiner Kreis | waagerecht, senkrecht oder diagonal | genau 1 Feld |
| 6 | grosser Kreis | waagerecht, senkrecht oder diagonal | beliebig viele Felder |

**Jede Figur gehoert genau einem Spieler.** Jeder Spieler besitzt von jedem Typ genau
eine Figur.

### Grundaufstellung

Spieler 1 stellt seine sechs Figuren in Zeile 5 auf, Spieler 2 seine sechs Figuren in
Zeile 0. In beiden Faellen steht in Spalte 0 die Figur vom Typ 1, in Spalte 1 die Figur
vom Typ 2 und so weiter. Alle anderen Felder sind leer.

In der Anzeige steht die erste Ziffer fuer die Spielernummer, die zweite fuer den Typ
der Figur. `24` ist also das grosse Dreieck von Spieler 2:

```
      0   1   2   3   4   5
----+------------------------
  0 |  21  22  23  24  25  26
  1 |   -   -   -   -   -   -
  2 |   -   -   -   -   -   -
  3 |   -   -   -   -   -   -
  4 |   -   -   -   -   -   -
  5 |  11  12  13  14  15  16
```

### Regeln fuer einen Zug

Ein Zug besteht aus einem Startfeld (Zeile, Spalte) und einem Zielfeld (Zeile, Spalte).
Ein Zug ist genau dann gueltig, wenn **alle** folgenden Bedingungen erfuellt sind:

1. Start- und Zielfeld liegen im Spielbrett.
2. Start- und Zielfeld sind nicht dasselbe Feld.
3. Auf dem Startfeld steht eine Figur, die dem ziehenden Spieler gehoert.
4. Auf dem Zielfeld steht keine Figur des ziehenden Spielers (es ist leer oder dort
   steht eine gegnerische Figur).
5. Die Zugrichtung passt zum Figurtyp: waagerecht oder senkrecht bedeutet, dass sich
   entweder die Zeile oder die Spalte nicht aendert; diagonal bedeutet, dass sich Zeile
   und Spalte um denselben Betrag aendern. Jede andere Richtung (etwa wie ein Springer
   beim Schach) ist nie erlaubt.
6. Die Reichweite passt zum Figurtyp: kleine Figuren ziehen genau ein Feld weit.
7. Der Weg ist frei: zwischen Start- und Zielfeld darf keine andere Figur stehen.
   Das Zielfeld selbst zaehlt nicht zum Weg.

Steht auf dem Zielfeld eine gegnerische Figur, so wird diese geschlagen: sie wird vom
Brett entfernt und ist nicht mehr im Spiel.

### Spielende

Das Spiel endet,

- wenn ein Spieler mit einer eigenen Figur die gegenueberliegende Grundlinie erreicht hat
  (Spieler 1 die Zeile 0, Spieler 2 die Zeile 5). Dieser Spieler hat gewonnen; oder
- wenn ein Spieler keine Figuren mehr im Spiel hat. Dann hat sein Gegner gewonnen.

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
  - Ist der Zug nicht gueltig, wird eine Meldung ausgegeben und erneut gefragt. Dies
    erfolgt so lange, bis ein gueltiger Zug eingegeben wurde.
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

## Konsolenein- und -ausgaben

- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

- Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielbrett` und `Figur`
  enthalten.
- In der Projektvorlage sind diese Klassen bereits angelegt. Kommentare in den Dateien
  beschreiben, was jeweils zu tun ist. Fuegen Sie Code hinzu, der die gewuenschten
  Funktionen implementiert, und aendern Sie dazu gegebenenfalls auch die
  `return`-Anweisungen. Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

### Die Beziehungen zwischen den Klassen

Achten Sie auf den objektorientierten Aufbau des Programms:

- Ein Objekt der Klasse **`Figur`** kennt seinen **Typ**, seinen **Besitzer** (ein Objekt
  der Klasse `Spieler`) und seine **Position** auf dem Brett.
- Ein Objekt der Klasse **`Spieler`** verwaltet ein **Feld mit seinen sechs Figuren**.
  Der Spieler erzeugt diese Figuren in seinem Konstruktor.
- Ein Objekt der Klasse **`Spielbrett`** verwaltet ein zweidimensionales Feld, in dem an
  jeder Stelle entweder eine **Referenz auf ein Figur-Objekt** oder `null` steht.
  Dieselben Figur-Objekte stehen also sowohl im Feld des Spielers als auch auf dem Brett.

Die Frage „gehoert diese Figur mir?" beantworten Sie also nicht ueber eine Zahl, sondern
ueber einen Vergleich der Objekte: `figur.getBesitzer() == spieler`.

### Klasse `Spielbrett`

Zwei Hilfsmethoden sind bereits vorgegeben, da die Klasse `Math` nicht verwendet werden darf:
`betrag(int zahl)` und `vorzeichen(int zahl)`. Die Methode `toString()` ist ebenfalls vorgegeben.

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
  0 |  21  22  23  24  25  26
  1 |   -   -   -   -   -   -
  2 |   -   -   -   -   -   -
  3 |   -   -   -   -   -   -
  4 |   -   -   -   -   -   -
  5 |  11  12  13  14  15  16

Startfeld - Zeile: > 5
Startfeld - Spalte: > 1
Zielfeld - Zeile: > 2
Zielfeld - Spalte: > 1
Der Zug wurde ausgefuehrt.
--------------------------------
Toni ist an der Reihe.
[... Spielbrett ...]

Startfeld - Zeile: > 2
Startfeld - Spalte: > 1
Dieser Zug ist nicht gueltig.

Startfeld - Zeile: > 0
Startfeld - Spalte: > 5
Zielfeld - Zeile: > 3
Zielfeld - Spalte: > 5
Der Zug wurde ausgefuehrt.
--------------------------------
[...]

Die Figur 22 wurde geschlagen.
Der Zug wurde ausgefuehrt.

Alex hat die Grundlinie erreicht und gewonnen!

Opala Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
