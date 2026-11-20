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

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testStellungKamSchonVor()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code (etwa zur Verwendung der Java-Bibliothek) einhalten.

---

# Prüfungsaufgabe: „Puan-Puan Light"

## Spielablauf

Bei „Puan-Puan Light" spielen zwei Spieler gegeneinander auf einem gemeinsamen
quadratischen Spielbrett mit n Zeilen und n Spalten. Auf jedem Feld liegt entweder ein
Stein oder nicht. Zu Beginn ist das Brett leer.

Die Spieler ziehen abwechselnd, Spieler 1 beginnt. **Ein Zug besteht darin, ein Feld zu
nennen:** Liegt dort kein Stein, wird einer gesetzt. Liegt dort bereits ein Stein, wird er
weggenommen. Jedes existierende Feld darf genannt werden.

### Die Verlustbedingung

Nach jedem Zug entsteht eine neue **Stellung**, also eine bestimmte Verteilung der Steine
auf dem Brett. Alle bisher aufgetretenen Stellungen werden gemerkt.

**Wer eine Stellung erzeugt, die im bisherigen Spielverlauf schon einmal aufgetreten ist,
verliert sofort.**

Dabei zaehlen zwei Stellungen auch dann als gleich, wenn sie durch **Drehung des
gesamten Brettes um 90, 180 oder 270 Grad** auseinander hervorgehen. Man nennt das
**Puan-Gleichheit**.

Beispiel (n = 3): Die folgenden vier Stellungen sind alle puan-gleich, denn sie gehen
durch Drehung auseinander hervor:

```
  X . .      . . X      . . .      . . .
  . . .      . . .      . . .      . . .
  . . .      . . .      . . X      X . .
```

Liegt dagegen ein einzelner Stein in der Mitte einer Kante, etwa so:

```
  . X .
  . . .
  . . .
```

so ist diese Stellung zu keiner der obigen puan-gleich.

### Spielende

Das Spiel endet,

- sobald ein Spieler eine bereits aufgetretene Stellung erzeugt hat. Dieser Spieler hat
  verloren, sein Gegner hat gewonnen; oder
- wenn `Spiel.MAX_ZUEGE` Zuege gespielt wurden, ohne dass dies geschehen ist.
  In diesem Fall endet das Spiel unentschieden.

---

## Programmablauf

Das Programm startet mit dem folgenden Auswahlmenue:

```
Puan-Puan Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Wird „1 – Spiel starten" ausgewaehlt, werden die Namen der beiden Spieler sowie die
  Spielfeldgroesse eingelesen und das Spiel gestartet.
  - Vor dem ersten Zug wird die leere Anfangsstellung gemerkt.
  - Im laufenden Spiel werden das Brett und die Nummer des Zuges ausgegeben, danach
    wird der aktive Spieler nach Zeile und Spalte gefragt.
  - Eine Eingabe ist genau dann gueltig, wenn sie ein existierendes Feld bezeichnet.
    Bei einer ungueltigen Eingabe wird erneut gefragt.
  - Nach dem Zug wird geprueft, ob die entstandene Stellung schon einmal aufgetreten ist.
    Ist das der Fall, hat der ziehende Spieler verloren und das Spiel ist zu Ende.
    Sonst wird die neue Stellung gemerkt und der andere Spieler ist an der Reihe.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Wird „2 – Programm beenden" ausgewaehlt, so wird das Programm beendet.

## Umgang mit falschen Eingaben

- Im Auswahlmenue, bei der Spielfeldgroesse sowie bei Zeile und Spalte duerfen Sie davon
  ausgehen, dass nur ganze Zahlen eingegeben werden.
- Bei der Eingabe der Spielernamen duerfen Sie von korrekten Eingaben ausgehen.

## Konsolenein- und -ausgaben

- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

- Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielfeld` und `Stellung`
  enthalten.
- In der Projektvorlage sind diese Klassen bereits angelegt. Kommentare in den Dateien
  beschreiben, was jeweils zu tun ist. Fuegen Sie Code hinzu, der die gewuenschten
  Funktionen implementiert, und aendern Sie dazu gegebenenfalls auch die
  `return`-Anweisungen. Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

### Klasse `Stellung`

Die Klasse `Stellung` enthaelt ausschliesslich **statische** Methoden und besitzt keine
Attribute. Sie kuemmert sich um das Drehen und das Vergleichen von Stellungen.

Ueberlegen Sie sich die Drehung an einem kleinen Beispiel auf Papier: Bei einer Drehung
um 90 Grad im Uhrzeigersinn wandert das Feld der Zeile z und Spalte s auf die Zeile s
und die Spalte n-1-z. Die obere linke Ecke landet also in der oberen rechten Ecke.

Eine Drehung um 180 Grad erreichen Sie, indem Sie zweimal um 90 Grad drehen, eine
Drehung um 270 Grad durch dreimaliges Drehen.

### Klasse `Spielfeld`

Die Klasse `Spielfeld` hat folgende Attribute:

- `boolean[][] feld` speichert die aktuelle Stellung (`true` bedeutet „Stein liegt hier"),
- `int n` die Groesse des Brettes,
- `boolean[][][] verlauf` speichert alle bisher gemerkten Stellungen,
- `int anzahlVerlauf` gibt an, wie viele Stellungen bereits gemerkt wurden.

Die Methode `toString()` ist vorgegeben.

### Klasse `Spieler`

Die Klasse `Spieler` hat die Attribute `String name` und `int nummer`.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Puan-Puan Light

Puan-Puan Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie gross soll das Spielbrett sein?
> 3
--------------------------------
Zug 1 - Alex ist an der Reihe.
      0   1   2
  0 | .   .   .
  1 | .   .   .
  2 | .   .   .

Zeile: > 0
Spalte: > 0
Alex setzt einen Stein auf (0, 0).
--------------------------------
Zug 2 - Toni ist an der Reihe.
      0   1   2
  0 | X   .   .
  1 | .   .   .
  2 | .   .   .

Zeile: > 1
Spalte: > 1
Toni setzt einen Stein auf (1, 1).
--------------------------------
Zug 3 - Alex ist an der Reihe.
      0   1   2
  0 | X   .   .
  1 | .   X   .
  2 | .   .   .

Zeile: > 0
Spalte: > 0
Alex nimmt den Stein von (0, 0) weg.
--------------------------------
Zug 4 - Toni ist an der Reihe.
      0   1   2
  0 | .   .   .
  1 | .   X   .
  2 | .   .   .

Zeile: > 0
Spalte: > 2
Toni setzt einen Stein auf (0, 2).
Diese Stellung gab es schon einmal!
Toni hat verloren. Alex hat gewonnen!

Puan-Puan Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```

Im letzten Zug entsteht eine Stellung mit einem Stein in der Mitte und einem Stein in
einer Ecke. Genau diese Stellung gab es nach Zug 2 schon einmal – damals lag der
Eckstein in der oberen linken, jetzt in der oberen rechten Ecke. Die beiden Stellungen
sind puan-gleich, also hat Toni verloren.
