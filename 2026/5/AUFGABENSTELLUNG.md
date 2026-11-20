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

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testNaechsteGeneration()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code (etwa zur Verwendung der Java-Bibliothek) einhalten.

---

# Prüfungsaufgabe: „Game of Life Light"

## Spielablauf

Bei „Game of Life Light" treten zwei Spieler gegeneinander an. Jeder Spieler besitzt ein
eigenes quadratisches Spielfeld mit n Zeilen und n Spalten. Jedes Feld ist eine **Zelle**,
die entweder **lebendig** oder **tot** ist. Zu Beginn sind alle Zellen tot.

Das Spiel laeuft in zwei Phasen ab:

1. **Aufbauphase:** Jeder Spieler setzt nacheinander genau k Zellen seines eigenen
   Spielfelds auf lebendig, indem er deren Zeile und Spalte nennt.
2. **Simulationsphase:** Beide Spielfelder entwickeln sich gleichzeitig ueber mehrere
   Generationen weiter. Es gewinnt der Spieler, dessen Spielfeld am Ende die meisten
   lebenden Zellen enthaelt.

### Die Regeln einer Generation

Jede Zelle hat bis zu **8 Nachbarzellen** (waagerecht, senkrecht und diagonal). Zellen am
Rand haben entsprechend weniger. Von einer Generation zur naechsten gilt:

- Eine **tote** Zelle wird **geboren**, wenn sie genau **3** lebende Nachbarn hat.
  Sonst bleibt sie tot.
- Eine **lebende** Zelle **ueberlebt**, wenn sie **2 oder 3** lebende Nachbarn hat.
  Bei weniger als 2 Nachbarn stirbt sie an Vereinsamung, bei mehr als 3 an
  Ueberbevoelkerung.

**Ganz wichtig:** Alle Veraenderungen geschehen **gleichzeitig**. Fuer die Berechnung
einer neuen Generation zaehlen ausschliesslich die Nachbarn der **alten** Generation.
Sie duerfen das Spielfeld also nicht Zelle fuer Zelle ueberschreiben, waehrend Sie noch
darin zaehlen. Berechnen Sie die neue Generation in einem **zweiten Feld** und
uebernehmen Sie dieses erst am Ende.

Beispiel (n = 5), drei lebende Zellen nebeneinander:

```
      0   1   2   3   4
  0 | .   .   .   .   .
  1 | .   .   .   .   .
  2 | .   X   X   X   .
  3 | .   .   .   .   .
  4 | .   .   .   .   .
```

Die naechste Generation sieht so aus:

```
      0   1   2   3   4
  0 | .   .   .   .   .
  1 | .   .   X   .   .
  2 | .   .   X   .   .
  3 | .   .   X   .   .
  4 | .   .   .   .   .
```

Die beiden aeusseren Zellen sterben (nur 1 Nachbar), die mittlere ueberlebt (2 Nachbarn),
und ober- und unterhalb der Mitte werden zwei Zellen geboren (je 3 Nachbarn).

### Spielende

Die Simulation endet,

- wenn `Spiel.MAX_GENERATIONEN` Generationen berechnet wurden, oder
- sobald sich **beide** Spielfelder nicht mehr veraendern.

Danach gewinnt der Spieler mit den meisten lebenden Zellen. Bei gleicher Anzahl endet
das Spiel unentschieden.

---

## Programmablauf

Das Programm startet mit dem folgenden Auswahlmenue:

```
Game of Life Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Wird „1 – Spiel starten" ausgewaehlt, werden die Namen der beiden Spieler, die
  Spielfeldgroesse sowie die Anzahl der zu setzenden Zellen eingelesen.
  - In der Aufbauphase wird jeder Spieler nacheinander nach Zeile und Spalte gefragt.
    Eine Eingabe ist genau dann gueltig, wenn sie eine existierende und noch **tote**
    Zelle bezeichnet. Bei einer ungueltigen Eingabe wird erneut gefragt.
  - In der Simulationsphase wird nach jeder Generation die Nummer der Generation,
    beide Spielfelder und die Anzahl der lebenden Zellen ausgegeben.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Wird „2 – Programm beenden" ausgewaehlt, so wird das Programm beendet.

## Umgang mit falschen Eingaben

- Im Auswahlmenue sowie bei allen Zahleneingaben duerfen Sie davon ausgehen, dass nur
  ganze Zahlen eingegeben werden.
- Bei der Eingabe der Spielernamen duerfen Sie von korrekten Eingaben ausgehen.

## Konsolenein- und -ausgaben

- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

- Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielfeld` und `Generation`
  enthalten.
- In der Projektvorlage sind diese Klassen bereits angelegt. Kommentare in den Dateien
  beschreiben, was jeweils zu tun ist. Fuegen Sie Code hinzu, der die gewuenschten
  Funktionen implementiert, und aendern Sie dazu gegebenenfalls auch die
  `return`-Anweisungen. Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

### Klasse `Generation`

Die Klasse `Generation` enthaelt ausschliesslich **statische** Methoden und besitzt keine
Attribute. Sie kapselt die Regeln einer einzelnen Generation.

### Klasse `Spielfeld`

Die Klasse `Spielfeld` hat das Attribut `boolean[][] feld` (`true` bedeutet „lebendig")
sowie das Attribut `int n`. Die Methode `toString()` ist vorgegeben.

### Klasse `Spieler`

Die Klasse `Spieler` hat die Attribute `String name` und `Spielfeld sf`.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Game of Life Light

Game of Life Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie gross soll das Spielfeld sein?
> 5
Wie viele Zellen darf jeder setzen?
> 3
--------------------------------
Alex, setze deine Zellen.
Zeile: > 2
Spalte: > 1
Zeile: > 2
Spalte: > 2
Zeile: > 2
Spalte: > 2
Diese Zelle lebt bereits.
Zeile: > 2
Spalte: > 3
--------------------------------
Toni, setze deine Zellen.
[...]
--------------------------------
Generation 1
Spielfeld von Alex:        Lebende Zellen: 3
      0   1   2   3   4
  0 | .   .   .   .   .
  1 | .   .   X   .   .
  2 | .   .   X   .   .
  3 | .   .   X   .   .
  4 | .   .   .   .   .

Spielfeld von Toni:        Lebende Zellen: 4
[...]

--------------------------------
Die Simulation ist beendet.
Alex: 3 lebende Zellen
Toni: 4 lebende Zellen
Toni hat gewonnen!

Game of Life Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
