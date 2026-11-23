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

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testSchiffAn()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code einhalten.

---

# Prüfungsaufgabe: „Schiffe versenken Light"

## Spielablauf

Zwei Spieler spielen gegeneinander. **Jeder Spieler besitzt eine eigene Flotte aus drei
Schiffen** mit den Laengen 3, 2 und 2 sowie ein eigenes quadratisches Spielfeld mit
n Zeilen und n Spalten, auf dem die **Schuesse des Gegners** festgehalten werden.

### Aufbauphase

Jeder Spieler platziert nacheinander seine drei Schiffe. Fuer jedes Schiff gibt er die
Zeile und die Spalte seines ersten Feldes an sowie die Ausrichtung (waagerecht oder
senkrecht). Ein waagerechtes Schiff der Laenge 3 ab (2,1) belegt die Felder (2,1), (2,2)
und (2,3). Ein senkrechtes Schiff der Laenge 3 ab (2,1) belegt (2,1), (3,1) und (4,1).

Eine Platzierung ist genau dann gueltig, wenn **alle** Felder des Schiffes im Spielfeld
liegen und **keines** davon bereits von einem eigenen Schiff belegt ist.

### Schussphase

Die Spieler schiessen abwechselnd. Wer an der Reihe ist, nennt Zeile und Spalte eines
Feldes **auf dem Spielfeld des Gegners**, auf das noch nicht geschossen wurde.

- Steht dort ein Schiff des Gegners, so ist es ein **Treffer**: das Schiff erhaelt einen
  Treffer, das Feld wird als Treffer markiert. Hat das Schiff so viele Treffer wie es
  lang ist, ist es **versenkt**.
- Sonst ist es ein **Fehlschuss** und das Feld wird als Wasser markiert.

Danach ist der andere Spieler an der Reihe.

### Spielende

Das Spiel endet, sobald alle Schiffe eines Spielers versenkt sind. Sein Gegner hat gewonnen.

---

## Programmablauf

```
Schiffe versenken Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Bei `1` werden die Namen der beiden Spieler und die Spielfeldgroesse eingelesen.
  - In der Aufbauphase wird jeder Spieler fuer jedes seiner Schiffe nach Zeile, Spalte
    und Ausrichtung gefragt. Bei einer ungueltigen Platzierung wird erneut gefragt.
  - In der Schussphase wird das Spielfeld des Gegners ausgegeben und nach Zeile und
    Spalte gefragt. Es wird so lange gefragt, bis ein Feld genannt wird, das im
    Spielfeld liegt und auf das noch nicht geschossen wurde.
  - Nach jedem Schuss wird gemeldet, ob es ein Treffer, ein Fehlschuss oder ein
    versenktes Schiff war.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Bei `2` wird das Programm beendet.

### Ausrichtung einlesen

Fuer die Ausrichtung sind nur die Eingaben `w` (waagerecht) und `s` (senkrecht) erlaubt.
Es wird so lange gefragt, bis eine davon eingegeben wurde.

## Umgang mit falschen Eingaben

- Bei allen Zahleneingaben duerfen Sie davon ausgehen, dass nur ganze Zahlen eingegeben
  werden. Bei den Spielernamen duerfen Sie von korrekten Eingaben ausgehen.

## Konsolenein- und -ausgaben

- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielfeld` und `Schiff`
enthalten. In der Projektvorlage sind diese Klassen bereits angelegt. Fuegen Sie Code
hinzu und aendern Sie dazu gegebenenfalls auch die `return`-Anweisungen. Ansonsten
duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

### Die Beziehungen zwischen den Klassen

Dieses Programm verwendet ein ganz bestimmtes Modell:

> **Der `Spieler` kennt seine `Schiff`e – ein `Schiff` kennt seinen Spieler NICHT.**

- Ein Objekt der Klasse **`Spieler`** verwaltet ein **Feld mit seinen drei Schiffen** und
  erzeugt diese in seinem Konstruktor. Ausserdem besitzt er sein eigenes `Spielfeld`.
- Ein Objekt der Klasse **`Schiff`** kennt nur seine **Laenge**, seine **Position**, seine
  **Ausrichtung** und die Anzahl seiner **Treffer**. Es hat **kein** Attribut fuer einen
  Besitzer.

Das hat eine wichtige Folge fuer Ihren Code: Auf die Frage „steht auf dem Feld (z,s) ein
Schiff?" kann ein `Schiff` nicht antworten, und das `Spielfeld` kennt die Schiffe gar
nicht. Nur der **Spieler** kann die Frage beantworten, indem er **seine eigene Flotte
durchsucht**. Genau das leistet die Methode `Spieler.schiffAn(...)`.

Dieses Modell passt zu den Spielregeln: Eine Flotte wechselt nie den Besitzer. Ein Schiff
muss deshalb nie wissen, wem es gehoert.

### Klasse `Spielfeld`

Das `Spielfeld` speichert ausschliesslich die **Schuesse**, nicht die Schiffe:
`0` = noch kein Schuss, `1` = Wasser, `2` = Treffer. Die Methode `toString()` ist vorgegeben.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Schiffe versenken Light

Schiffe versenken Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie gross soll das Spielfeld sein?
> 6
--------------------------------
Alex, platziere deine Schiffe.
Schiff der Laenge 3 - Zeile: > 0
Schiff der Laenge 3 - Spalte: > 0
Ausrichtung (w/s)? > w
Schiff der Laenge 2 - Zeile: > 2
Schiff der Laenge 2 - Spalte: > 0
Ausrichtung (w/s)? > w
Diese Platzierung ist nicht gueltig.
Schiff der Laenge 2 - Zeile: > 2
Schiff der Laenge 2 - Spalte: > 3
Ausrichtung (w/s)? > s
[...]
--------------------------------
Alex ist an der Reihe.
Spielfeld von Toni:
      0   1   2   3   4   5
  0 | .   .   .   .   .   .
  1 | .   .   .   .   .   .
  2 | .   .   .   .   .   .
  3 | .   .   .   .   .   .
  4 | .   .   .   .   .   .
  5 | .   .   .   .   .   .

Zeile: > 2
Spalte: > 3
Treffer!
--------------------------------
Toni ist an der Reihe.
[...]

Zeile: > 3
Spalte: > 3
Treffer! Schiff versenkt!
[...]

Alle Schiffe von Toni sind versenkt.
Alex hat gewonnen!

Schiffe versenken Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
