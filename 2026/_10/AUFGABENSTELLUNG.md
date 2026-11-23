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

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testIstZugGueltig()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code einhalten.

---

# Prüfungsaufgabe: „Reversi Light"

## Spielablauf

Zwei Spieler spielen auf einem quadratischen Spielbrett mit n Zeilen und n Spalten
(n ist gerade und mindestens 4). Spieler 1 spielt mit dem Zeichen `X`, Spieler 2 mit `O`.

### Grundaufstellung

Zu Beginn liegen vier Steine in der Mitte des Brettes. Bei einem Brett der Groesse n
sind das die Felder mit den Koordinaten `m = n/2 - 1` und `m+1`:

- `(m, m)` und `(m+1, m+1)` gehoeren Spieler 2,
- `(m, m+1)` und `(m+1, m)` gehoeren Spieler 1.

Bei n = 6 ergibt das:

```
      0   1   2   3   4   5
  0 | .   .   .   .   .   .
  1 | .   .   .   .   .   .
  2 | .   .   O   X   .   .
  3 | .   .   X   O   .   .
  4 | .   .   .   .   .   .
  5 | .   .   .   .   .   .
```

### Ein Zug

Spieler 1 beginnt. Wer an der Reihe ist, nennt ein **freies** Feld (z, s).

Ein Zug ist genau dann **gueltig**, wenn er in mindestens einer der **acht Richtungen**
(waagerecht, senkrecht, diagonal) Steine des Gegners einschliesst. Eine Richtung
schliesst ein, wenn

1. direkt neben (z, s) in dieser Richtung **mindestens ein** Stein des **Gegners** liegt, und
2. unmittelbar hinter dieser ununterbrochenen Reihe gegnerischer Steine ein **eigener**
   Stein liegt.

Ein freies Feld oder der Rand des Brettes beenden die Reihe, **ohne** einzuschliessen.

Beim Zug wird der neue Stein gesetzt und **alle eingeschlossenen Steine wechseln den
Besitzer**: Sie werden zu Steinen des ziehenden Spielers. Die Steine werden dabei
**nicht ersetzt** – es ist derselbe Stein, der einfach einem anderen Spieler gehoert.

### Spielende

Kann ein Spieler keinen gueltigen Zug machen, setzt er aus und der Gegner ist erneut an
der Reihe. Kann **keiner** der beiden Spieler ziehen, oder ist das Brett voll, endet das
Spiel. Es gewinnt, wer mehr Steine besitzt; bei Gleichstand endet das Spiel unentschieden.

---

## Programmablauf

```
Reversi Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Bei `1` werden die Namen der beiden Spieler und die Brettgroesse eingelesen. Die
  Brettgroesse wird so lange eingelesen, bis eine gerade Zahl von mindestens 4 eingegeben
  wurde.
  - Vor jedem Zug werden das Brett und der aktuelle Punktestand ausgegeben.
  - Kann der Spieler, der an der Reihe ist, nicht ziehen, wird das gemeldet und der
    Gegner ist an der Reihe.
  - Sonst werden so lange Zeile und Spalte eingelesen, bis ein gueltiger Zug genannt wird.
  - Nach Spielende werden der Endstand und der Gewinner ausgegeben, danach erscheint
    wieder das Auswahlmenue.
- Bei `2` wird das Programm beendet.

## Umgang mit falschen Eingaben

- Bei allen Zahleneingaben duerfen Sie davon ausgehen, dass nur ganze Zahlen eingegeben
  werden. Bei den Spielernamen duerfen Sie von korrekten Eingaben ausgehen.

## Konsolenein- und -ausgaben

- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielbrett` und `Stein`
enthalten. In der Projektvorlage sind diese Klassen bereits angelegt. Fuegen Sie Code
hinzu und aendern Sie dazu gegebenenfalls auch die `return`-Anweisungen. Ansonsten
duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

### Die Beziehungen zwischen den Klassen

Dieses Programm verwendet ein ganz bestimmtes Modell:

> **Ein `Stein` kennt seinen `Spieler` – ein `Spieler` kennt seine Steine NICHT.**

- Ein Objekt der Klasse **`Stein`** hat ein Attribut `besitzer` vom Typ `Spieler`.
  Beim Umdrehen wird **kein neuer Stein erzeugt**; es wird nur dieses Attribut geaendert.
- Ein Objekt der Klasse **`Spieler`** hat **kein** Feld mit Steinen. Es kennt seinen
  Namen, sein Zeichen und – ebenfalls als Objektbeziehung – seinen **Gegner**.
- Ein Objekt der Klasse **`Spielbrett`** verwaltet ein zweidimensionales Feld vom Typ
  `Stein`. Ein freies Feld wird durch `null` dargestellt.

Das hat eine wichtige Folge fuer Ihren Code: Auf die Frage „wie viele Steine habe ich?"
kann ein `Spieler` nicht aus eigener Kraft antworten. Die Antwort liefert das
**Spielbrett**, indem es alle Felder durchlaeuft und jeden Stein nach seinem Besitzer
fragt. Genau das leistet die Methode `Spielbrett.zaehleSteine(Spieler)`.

Dieses Modell passt zu den Spielregeln: Ein Stein **wechselt staendig den Besitzer**.
Eine feste Liste beim Spieler waere bei jedem Zug falsch – die Information gehoert
deshalb an den Stein.

### Die acht Richtungen

Legen Sie sich im Konstruktor von `Spielbrett` zwei Felder mit den acht
Richtungsvektoren an (zum Beispiel `dz` und `ds`), und durchlaufen Sie diese mit einer
Schleife. Schreiben Sie den Code **nicht** achtmal ab.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Reversi Light

Reversi Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie gross soll das Brett sein?
> 6
--------------------------------
      0   1   2   3   4   5
  0 | .   .   .   .   .   .
  1 | .   .   .   .   .   .
  2 | .   .   O   X   .   .
  3 | .   .   X   O   .   .
  4 | .   .   .   .   .   .
  5 | .   .   .   .   .   .

Alex (X): 2   Toni (O): 2
Alex ist an der Reihe.
Zeile: > 2
Spalte: > 1
Alex dreht 1 Stein um.
--------------------------------
      0   1   2   3   4   5
  0 | .   .   .   .   .   .
  1 | .   .   .   .   .   .
  2 | .   X   X   X   .   .
  3 | .   .   X   O   .   .
  4 | .   .   .   .   .   .
  5 | .   .   .   .   .   .

Alex (X): 4   Toni (O): 1
Toni ist an der Reihe.
Zeile: > 3
Spalte: > 3
Dieser Zug ist nicht gueltig.
Zeile: > 1
Spalte: > 1
Toni dreht 1 Stein um.
[...]

Spielende!
Alex (X): 21   Toni (O): 15
Alex hat gewonnen!

Reversi Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
