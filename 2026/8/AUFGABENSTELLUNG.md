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

# Prüfungsaufgabe: „Metamorphose Light"

## Spielablauf

Bei „Metamorphose Light" spielen zwei Spieler auf einem quadratischen Spielbrett mit
7 Zeilen und 7 Spalten. Fuenf Felder des Brettes sind durch **Mauern** besetzt; auf diese
Felder darf keine Figur gezogen werden, und es darf auch nicht ueber sie hinweg gezogen
werden. Die Mauern liegen fest auf den Feldern (1,1), (1,5), (3,3), (5,1) und (5,5).

**Jeder Spieler besitzt sieben eigene Spielfiguren**, naemlich vier Quadrate und drei
Kreise. Spieler 1 stellt sie in Zeile 6 auf, Spieler 2 in Zeile 0. Spieler 1 beginnt.

### Die Spielfiguren

| Typ | Figur | Zugrichtung |
|-----|-------|-------------|
| 1 | Quadrat | waagerecht oder senkrecht, beliebig viele Felder |
| 2 | Kreis | diagonal, beliebig viele Felder |

### Die Verwandlung

Das Besondere an diesem Spiel: **Nach jedem Zug verwandelt sich die gezogene Figur.**
Aus einem Quadrat wird ein Kreis, aus einem Kreis wird ein Quadrat. Eine Figur, die
gerade noch waagerecht gezogen ist, zieht im naechsten Zug also diagonal.

### Regeln fuer einen Zug

Ein Zug besteht aus einem Startfeld (Zeile, Spalte) und einem Zielfeld (Zeile, Spalte).
Ein Zug ist genau dann gueltig, wenn **alle** folgenden Bedingungen erfuellt sind:

1. Start- und Zielfeld liegen im Spielbrett.
2. Start- und Zielfeld sind nicht dasselbe Feld.
3. Auf dem Startfeld steht eine Figur, die dem ziehenden Spieler gehoert.
4. Auf dem Zielfeld liegt **keine Mauer**.
5. Auf dem Zielfeld steht keine Figur des ziehenden Spielers (es ist leer oder dort
   steht eine gegnerische Figur).
6. Die Zugrichtung passt zum Typ der Figur: Quadrate ziehen waagerecht oder senkrecht,
   Kreise diagonal. Jede andere Richtung ist nie erlaubt.
7. Der Weg ist frei: Zwischen Start- und Zielfeld darf **weder eine Mauer noch eine
   andere Figur** stehen. Das Zielfeld selbst zaehlt nicht zum Weg.

Eine Reichweitenbeschraenkung gibt es nicht: Quadrate und Kreise duerfen beliebig weit
ziehen, solange der Weg frei ist.

Steht auf dem Zielfeld eine gegnerische Figur, so wird diese geschlagen: sie wird vom
Brett entfernt und ist nicht mehr im Spiel.

### Spielende

Das Spiel endet,

- wenn ein Spieler an der Reihe ist und **keinen gueltigen Zug** mehr hat. Dieser Spieler
  hat verloren; oder
- wenn ein Spieler keine Figuren mehr im Spiel hat. Dann hat sein Gegner gewonnen.

---

## Programmablauf

Das Programm startet mit dem folgenden Auswahlmenue:

```
Metamorphose Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Wird „1 – Spiel starten" ausgewaehlt, werden die Namen der beiden Spieler eingelesen
  und das Spiel gestartet.
  - Vor jedem Zug wird geprueft, ob der aktive Spieler ueberhaupt noch einen gueltigen
    Zug hat. Hat er keinen, ist das Spiel zu Ende.
  - Im laufenden Spiel wird das Spielbrett ausgegeben und der aktive Spieler nach Start-
    und Zielfeld seines Zuges gefragt.
  - Ist der Zug nicht gueltig, wird eine Meldung ausgegeben und erneut gefragt. Dies
    erfolgt so lange, bis ein gueltiger Zug eingegeben wurde.
  - Wird beim Zug eine gegnerische Figur geschlagen, wird dies gemeldet. Ausserdem wird
    gemeldet, in was sich die gezogene Figur verwandelt hat.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Wird „2 – Programm beenden" ausgewaehlt, so wird das Programm beendet.

## Umgang mit falschen Eingaben

- Im Auswahlmenue sowie bei der Eingabe von Zeilen und Spalten duerfen Sie davon
  ausgehen, dass nur ganze Zahlen eingegeben werden.
- Bei der Eingabe der Spielernamen duerfen Sie von korrekten Eingaben ausgehen.

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
  der Klasse `Spieler`) und seine **Position** auf dem Brett. Es kann sich **verwandeln**.
- Ein Objekt der Klasse **`Spieler`** verwaltet ein **Feld mit seinen sieben Figuren**.
  Der Spieler erzeugt diese Figuren in seinem Konstruktor.
- Ein Objekt der Klasse **`Spielbrett`** verwaltet ein zweidimensionales Feld, in dem an
  jeder Stelle entweder eine **Referenz auf ein Figur-Objekt** oder `null` steht, sowie
  ein zweites Feld fuer die Mauern.

Die Frage „gehoert diese Figur mir?" beantworten Sie also nicht ueber eine Zahl, sondern
ueber einen Vergleich der Objekte: `figur.getBesitzer() == spieler`.

### Anzeige des Brettes

In der Anzeige steht die erste Ziffer fuer die Spielernummer, danach `Q` fuer ein Quadrat
und `K` fuer einen Kreis. Mauern werden als `##` dargestellt, leere Felder als `-`:

```
      0   1   2   3   4   5   6
----+----------------------------
  0 |  2Q  2Q  2Q  2Q  2K  2K  2K
  1 |   -  ##   -   -   -  ##   -
  2 |   -   -   -   -   -   -   -
  3 |   -   -   -  ##   -   -   -
  4 |   -   -   -   -   -   -   -
  5 |   -  ##   -   -   -  ##   -
  6 |  1Q  1Q  1Q  1Q  1K  1K  1K
```

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Metamorphose Light

Metamorphose Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
--------------------------------
Alex ist an der Reihe.
[... Spielbrett ...]

Startfeld - Zeile: > 6
Startfeld - Spalte: > 2
Zielfeld - Zeile: > 2
Zielfeld - Spalte: > 2
Der Zug wurde ausgefuehrt.
Die Figur hat sich in einen Kreis verwandelt.
--------------------------------
Toni ist an der Reihe.
[... Spielbrett ...]

Startfeld - Zeile: > 0
Startfeld - Spalte: > 4
Zielfeld - Zeile: > 3
Zielfeld - Spalte: > 1
Der Zug wurde ausgefuehrt.
Die Figur hat sich in ein Quadrat verwandelt.
--------------------------------
Alex ist an der Reihe.
[...]

Startfeld - Zeile: > 2
Startfeld - Spalte: > 2
Zielfeld - Zeile: > 3
Zielfeld - Spalte: > 1
Die Figur 2Q wurde geschlagen.
Der Zug wurde ausgefuehrt.
Die Figur hat sich in ein Quadrat verwandelt.
--------------------------------
[...]

Toni hat keinen gueltigen Zug mehr.
Alex hat gewonnen!

Metamorphose Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
