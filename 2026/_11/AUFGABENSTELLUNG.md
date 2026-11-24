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

Sie haben die Prüfung sicher bestanden, wenn

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testTierAn()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code einhalten.

---

# Prüfungsaufgabe: „Dschungelkampf Light"

## Spielablauf

Zwei Spieler spielen auf einem quadratischen Spielfeld mit n Zeilen und n Spalten
(n ist mindestens 5). **Jeder Spieler besitzt drei Tiere** mit den Staerken 1, 2 und 3.
Ein Tier wechselt nie den Besitzer: Es gehoert von Anfang bis Ende demselben Spieler.

### Aufstellung

Spieler 1 hat die Heimatzeile 0 und das Zeichen `A`, Spieler 2 die Heimatzeile n-1 und
das Zeichen `B`. Jeder Spieler stellt seine Tiere zu Beginn in seine Heimatzeile auf die
Spalten 0, 1 und 2:

- Spalte 0: Tier der Staerke 1 (Maus)
- Spalte 1: Tier der Staerke 2 (Wolf)
- Spalte 2: Tier der Staerke 3 (Elefant)

Das Zielfeld eines Spielers ist die **Heimatzeile des Gegners**.

### Der Fluss

Mitten durch das Spielfeld fliesst ein Fluss. Er liegt in der Zeile `n/2` und belegt dort
die Spalten `1` bis `n-2`. Bei n = 5 ist das die Zeile 2 mit den Spalten 1, 2 und 3:

```
      0   1   2   3   4
  0 | A1  A2  A3   .   .
  1 |  .   .   .   .   .
  2 |  .   ~   ~   ~   .
  3 |  .   .   .   .   .
  4 | B1  B2  B3   .   .
```

**Nur ein Tier der Staerke 1 darf ein Flussfeld betreten.**

### Ein Zug

Wer an der Reihe ist, waehlt eines seiner Tiere und eine Richtung (`n`, `o`, `s`, `w`).
Das Tier zieht genau ein Feld waagerecht oder senkrecht - niemals diagonal.

Der Zug ist genau dann **erlaubt**, wenn

1. das gewaehlte Tier noch im Spiel ist, und
2. das Zielfeld ein Nachbarfeld des Tieres ist, und
3. das Zielfeld betreten werden darf (es liegt im Spielfeld, und ist es ein Flussfeld,
   so hat das Tier die Staerke 1), und
4. auf dem Zielfeld **kein eigenes** Tier steht, und
5. auf dem Zielfeld entweder gar kein gegnerisches Tier steht **oder** ein gegnerisches
   Tier, das geschlagen werden darf.

### Wer schlaegt wen?

Ein Tier darf ein gegnerisches Tier schlagen, wenn seine Staerke **groesser oder gleich**
der Staerke des Gegners ist. Dazu kommt eine einzige Ausnahme:

> Die **Maus** (Staerke 1) darf den **Elefanten** (Staerke 3) schlagen.

Ein geschlagenes Tier ist aus dem Spiel: Es steht auf keinem Feld mehr und zaehlt nicht
mehr mit.

### Spielende

Ein Spieler gewinnt, sobald

- alle Tiere des Gegners geschlagen sind, **oder**
- eines seiner Tiere seine Zielzeile erreicht hat.

---

## Programmablauf

```
Dschungelkampf Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Bei `1` werden die Namen der beiden Spieler und die Spielfeldgroesse eingelesen. Die
  Groesse wird so lange eingelesen, bis eine Zahl von mindestens 5 eingegeben wurde.
  - Vor jedem Zug wird das Spielfeld ausgegeben.
  - Es wird so lange nach der Nummer des Tieres (0, 1 oder 2) und nach der Richtung
    gefragt, bis ein erlaubter Zug genannt wird.
  - Nach Spielende wird der Gewinner ausgegeben, danach erscheint wieder das Menue.
- Bei `2` wird das Programm beendet.

### Richtung einlesen

Fuer die Richtung sind nur die Eingaben `n`, `o`, `s` und `w` erlaubt. Es wird so lange
gefragt, bis eine davon eingegeben wurde.

## Umgang mit falschen Eingaben

- Bei allen Zahleneingaben duerfen Sie davon ausgehen, dass nur ganze Zahlen eingegeben
  werden. Bei den Spielernamen duerfen Sie von korrekten Eingaben ausgehen.

## Konsolenein- und -ausgaben

- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielfeld` und `Tier`
enthalten. In der Projektvorlage sind diese Klassen bereits angelegt. Fuegen Sie Code
hinzu und aendern Sie dazu gegebenenfalls auch die `return`-Anweisungen. Ansonsten
duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

### Die Beziehungen zwischen den Klassen

Dieses Programm verwendet ein ganz bestimmtes Modell:

> **Der `Spieler` kennt seine `Tier`e – ein `Tier` kennt seinen Spieler NICHT.**

- Ein Objekt der Klasse **`Spieler`** verwaltet ein **Feld mit seinen drei Tieren** und
  erzeugt diese in seinem Konstruktor.
- Ein Objekt der Klasse **`Tier`** kennt nur seine **Staerke**, seine **Position** und ob
  es noch **im Spiel** ist. Es hat **kein** Attribut fuer einen Besitzer.
- Ein Objekt der Klasse **`Spielfeld`** kennt nur die **Landschaft** (Groesse und Fluss).
  Es weiss **nicht**, welche Tiere wo stehen.

Das hat zwei wichtige Folgen fuer Ihren Code:

1. Auf die Frage „welches Tier steht auf (z,s)?" kann weder das `Tier` noch das
   `Spielfeld` antworten. Nur der **Spieler** kann sie beantworten, indem er **seine
   eigenen Tiere durchsucht**. Genau das leistet `Spieler.tierAn(...)`.
2. Die Pruefung eines Zuges braucht **alle vier Klassen**: das eigene Tier, das
   Spielfeld (Fluss und Rand), den eigenen Bestand und den Gegner. Deshalb bekommt
   `Spieler.zugErlaubt(...)` das Spielfeld und den Gegner als Parameter uebergeben.

Dieses Modell passt zu den Spielregeln: Ein Tier wechselt nie die Seite. Es muss deshalb
nie wissen, wem es gehoert.

### Die Ausgabe des Spielfeldes

Die Methode `Spielfeld.zeichne(Spieler, Spieler)` ist vorgegeben. Beachten Sie, dass das
Spielfeld darin die beiden **Spieler fragen** muss, welches Tier wo steht - es weiss es
selbst nicht.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Dschungelkampf Light

Dschungelkampf Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie gross soll das Spielfeld sein?
> 5
--------------------------------
      0   1   2   3   4
  0 | A1  A2  A3   .   .
  1 |  .   .   .   .   .
  2 |  .   ~   ~   ~   .
  3 |  .   .   .   .   .
  4 | B1  B2  B3   .   .

Alex ist an der Reihe.
Nummer des Tieres (0-2): > 2
Richtung (n/o/s/w): > s
--------------------------------
      0   1   2   3   4
  0 | A1  A2   .   .   .
  1 |  .   .  A3   .   .
  2 |  .   ~   ~   ~   .
  3 |  .   .   .   .   .
  4 | B1  B2  B3   .   .

Toni ist an der Reihe.
Nummer des Tieres (0-2): > 2
Richtung (n/o/s/w): > n
[...]
Nummer des Tieres (0-2): > 0
Richtung (n/o/s/w): > n
Dieser Zug ist nicht erlaubt.
[...]

Alle Tiere von Toni sind geschlagen.
Alex hat gewonnen!

Dschungelkampf Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
