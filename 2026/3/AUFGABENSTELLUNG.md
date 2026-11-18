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

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testZaehleSOSMit()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code (etwa zur Verwendung der Java-Bibliothek) einhalten.

---

# Prüfungsaufgabe: „SOS Light"

## Spielablauf

Bei „SOS Light" spielen zwei Spieler gegeneinander. Das Spielfeld besteht aus n
nebeneinander angeordneten Feldern (n > 2), die zu Beginn alle leer sind. Ein leeres Feld
wird durch den Punkt `.` dargestellt.

Die Spieler sind abwechselnd an der Reihe. Wer an der Reihe ist, nennt

- die **Nummer** eines noch leeren Feldes (zwischen 0 und n-1) und
- einen **Buchstaben**, entweder `S` oder `O`.

Der Buchstabe wird an die genannte Stelle geschrieben.

### Punkte

Entsteht durch diesen Zug die Teilfolge **`SOS`** auf drei nebeneinander liegenden Feldern,
so erhaelt der Spieler dafuer **einen Punkt** und ist **noch einmal** an der Reihe.

Wichtig: Ein einziger Zug kann **mehrere** SOS gleichzeitig erzeugen. In diesem Fall erhaelt
der Spieler fuer jedes entstandene SOS einen Punkt.

Beispiel: Das Feld sieht so aus (n = 5):

```
Index:   0   1   2   3   4
Feld :   S   O   .   O   S
```

Setzt ein Spieler nun ein `S` auf das Feld 2, so entsteht `S O S O S`. Darin stecken
**zwei** SOS (auf den Feldern 0-1-2 und auf den Feldern 2-3-4). Der Spieler erhaelt
also 2 Punkte.

Entsteht durch den Zug kein SOS, so ist der andere Spieler an der Reihe.

### Spielende

Das Spiel endet, sobald alle Felder belegt sind. Es gewinnt der Spieler mit den meisten
Punkten. Haben beide Spieler gleich viele Punkte, endet das Spiel unentschieden.

---

## Programmablauf

Das Programm startet mit dem folgenden Auswahlmenue:

```
SOS Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Wird „1 – Spiel starten" ausgewaehlt, werden die Namen der beiden Spieler sowie die
  gewuenschte Spielfeldgroesse eingelesen und das Spiel gestartet.
  - Im laufenden Spiel werden das Spielfeld und der Punktestand ausgegeben, danach wird
    der aktive Spieler nach der Feldnummer und dem Buchstaben gefragt.
  - Ist die Feldnummer ungueltig, wird eine Meldung ausgegeben und erneut gefragt.
    Dies erfolgt so lange, bis eine gueltige Feldnummer eingegeben wurde.
  - Beim Buchstaben sind nur die Eingaben `S`, `s`, `O` und `o` erlaubt. Die Frage wird so
    lange wiederholt, bis eine dieser Eingaben erfolgt ist.
  - Entstehen durch den Zug ein oder mehrere SOS, wird dies gemeldet, der Spieler erhaelt
    die Punkte und ist erneut an der Reihe. Sonst ist der andere Spieler an der Reihe.
  - Nach dem Spielende wird wieder das Auswahlmenue ausgegeben.
- Wird „2 – Programm beenden" ausgewaehlt, so wird das Programm beendet.

## Umgang mit falschen Eingaben

- Im Auswahlmenue, bei der Eingabe der Spielfeldgroesse sowie bei der Eingabe der
  Feldnummer duerfen Sie davon ausgehen, dass nur ganze Zahlen eingegeben werden.
- Bei der Eingabe der Spielernamen duerfen Sie von korrekten Eingaben ausgehen.
- Eine Feldnummer ist genau dann gueltig, wenn sie ein existierendes und noch leeres Feld
  bezeichnet.

## Konsolenein- und -ausgaben

- Am Ende der Aufgabenstellung finden Sie einen beispielhaften Programmdurchlauf.
  Ihre Konsolenausgaben muessen dem Schema dieses Beispiels folgen.
- Konsolenein- und -ausgaben duerfen nur durch Programmcode in der Klasse `Spiel` erfolgen.

---

## Aufbau des Programmcodes

- Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Spielfeld` und `Muster`
  enthalten.
- In der Projektvorlage sind diese Klassen bereits angelegt. Kommentare in den Dateien
  beschreiben, was jeweils zu tun ist. Fuegen Sie Code hinzu, der die gewuenschten
  Funktionen implementiert, und aendern Sie dazu gegebenenfalls auch die
  `return`-Anweisungen. Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.
- Sie duerfen weitere Klassen und in den vorhandenen Klassen weitere Attribute und
  Methoden ergaenzen, wo Ihnen dies sinnvoll erscheint.

### Klasse `Muster`

Die Klasse `Muster` enthaelt ausschliesslich **statische** Methoden, die ein char-Feld nach
der Teilfolge `SOS` durchsuchen. Sie besitzt keine Attribute, aber die vorgegebenen
Konstanten `LEER`, `S` und `O`.

Beachten Sie bei allen Methoden die Randfaelle: Ein SOS beginnt bei einem Index `start`
und belegt die Felder `start`, `start+1` und `start+2`. Diese drei Felder muessen alle
existieren.

### Klasse `Spielfeld`

Die Klasse `Spielfeld` hat das Attribut `char[] feld` sowie das Attribut `int n` fuer die
Anzahl der Felder. Die Methode `toString()` ist vorgegeben.

### Klasse `Spieler`

Die Klasse `Spieler` hat die Attribute `String name` und `int punkte`.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu SOS Light

SOS Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie viele Felder soll das Spielfeld haben?
> 5
--------------------------------
Alex ist an der Reihe.
Index:   0   1   2   3   4
Feld :   .   .   .   .   .

Punkte: Alex 0 - Toni 0
Feldnummer: > 0
Buchstabe (S/O): > S
--------------------------------
Toni ist an der Reihe.
Index:   0   1   2   3   4
Feld :   S   .   .   .   .

Punkte: Alex 0 - Toni 0
Feldnummer: > 1
Buchstabe (S/O): > O
--------------------------------
Alex ist an der Reihe.
Index:   0   1   2   3   4
Feld :   S   O   .   .   .

Punkte: Alex 0 - Toni 0
Feldnummer: > 4
Buchstabe (S/O): > S
--------------------------------
Toni ist an der Reihe.
Index:   0   1   2   3   4
Feld :   S   O   .   .   S

Punkte: Alex 0 - Toni 0
Feldnummer: > 3
Buchstabe (S/O): > O
--------------------------------
Alex ist an der Reihe.
Index:   0   1   2   3   4
Feld :   S   O   .   O   S

Punkte: Alex 0 - Toni 0
Feldnummer: > 1
Diese Feldnummer ist nicht gueltig.
Feldnummer: > 2
Buchstabe (S/O): > x
Buchstabe (S/O): > s
2 mal SOS! Alex erhaelt 2 Punkte und ist noch einmal an der Reihe.
--------------------------------
Alex ist an der Reihe.
Index:   0   1   2   3   4
Feld :   S   O   S   O   S

Punkte: Alex 2 - Toni 0

Das Spielfeld ist voll.
Endstand: Alex 2 - Toni 0
Alex hat gewonnen!

SOS Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
