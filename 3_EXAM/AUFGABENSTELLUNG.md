# Einfuehrung in die Programmierung — Pruefungsaufgabe „Sternwanderung"

**Bearbeitungszeit: 3 Stunden — Schwierigkeitsgrad: sehr schwer**

---

## Pruefungsbedingungen

- Es sind keine Hilfsmittel erlaubt.
- Es duerfen ausschliesslich folgende Elemente der Java-Bibliothek benutzt werden:
  - Klassen `Scanner` und `Random`
  - Methoden `System.out.print` / `System.out.println`
- Insbesondere duerfen **keine** Methoden der Klasse `String` und **keine** Klasse `Math`
  verwendet werden.
- Verwenden Sie in Ihrem Code nicht die Buchstaben ä, ö, ü, ß (auch nicht in String-Literalen).
- Ihr Code wird automatisiert getestet. Halten Sie sich unbedingt an alle Vorgaben
  (Methodennamen, Signaturen, Rueckgabewerte).
- Konsolenein- und -ausgabe darf **nur** in der Klasse `Spiel` erfolgen.
- Verwenden Sie in den Methoden der Klasse `Sternenhimmel` die **Laengen der Felder**
  (`sterne.length`, `sterne[0].length`), nicht die Konstante `GROESSE`.

---

## 1. Das Spiel

Der Sternenhimmel ist ein quadratisches Feld der Groesse 8x8. Auf jedem Feld liegt ein
**Stern**. Ein Stern ist einer der Buchstaben `A` bis `H`; jeder dieser 8 Buchstaben kommt
genau 8 Mal vor (8 x 8 = 64 Felder). Die Sterne liegen in zufaelliger Reihenfolge.
Ein eingesammeltes Feld ist **leer** und enthaelt das Zeichen `' '` (Leerzeichen).

Jeder Stern hat einen **Sternwert**: `A` = 1, `B` = 2, `C` = 3, ... , `H` = 8.
Ein leeres Feld hat den Wert 0.

Zwei Spieler steuern je ein Schiff (oder ein Spieler gegen den Computer). Spieler 1
startet auf dem Feld (0,0), Spieler 2 auf dem Feld (7,7).

### Der Zug

Wer an der Reihe ist, nennt eine **Richtung** von 0 bis 7:

```
      0   1   2          0 = oben links     4 = rechts
        \ | /            1 = oben           5 = unten links
      3 - S - 4          2 = oben rechts    6 = unten
        / | \            3 = links          7 = unten rechts
      5   6   7
```

Die **Schrittweite** ist der Sternwert des Feldes, auf dem das Schiff **gerade steht**.
Steht das Schiff auf einem leeren Feld, betraegt die Schrittweite 1.

Das Schiff bewegt sich genau so viele Schritte in die gewaehlte Richtung. Der
Sternenhimmel ist dabei **rundherum geschlossen**: wer oben hinausfaehrt, kommt unten
wieder herein, wer links hinausfaehrt, kommt rechts wieder herein. Die Zielkoordinaten
werden also modulo der Feldgroesse berechnet. Achtung: In Java kann `%` bei negativen
Zahlen ein negatives Ergebnis liefern — das muss korrigiert werden (siehe TODO 19).

**Beispiel:** Das Schiff steht auf (0,0), dort liegt der Stern `C` (Wert 3). Der Spieler
waehlt Richtung 1 (oben). Neue Zeile: 0 − 3 = −3, also modulo 8 die Zeile 5. Das Schiff
landet auf (5,0).

### Einsammeln

Landet das Schiff auf einem Feld mit einem Stern, wird dieser eingesammelt:

- Der Spieler erhaelt den **Sternwert** des Feldes.
- Zusaetzlich erhaelt er **einen Bonuspunkt fuer jeden der 8 Nachbarn** dieses Feldes
  (waagerecht, senkrecht und diagonal), auf dem **derselbe Buchstabe** liegt. Die
  Nachbarn werden dabei **nicht** eingesammelt und der Rand wird **nicht** umschlossen
  (fuer die Nachbarschaft gilt das Feld also als begrenzt).
- Das Feld wird danach leer.

Landet das Schiff auf einem leeren Feld, erhaelt der Spieler nichts.

**Beispiel:** Das Schiff landet auf einem `D` (Wert 4). Zwei der acht Nachbarfelder
tragen ebenfalls ein `D`. Der Spieler erhaelt 4 + 2 = 6 Punkte.

### Verbotener Zug

Ein Zug ist ungueltig, wenn das Schiff auf dem Feld landen wuerde, auf dem das
**Schiff des Gegners** steht. In diesem Fall wird erneut nach einer Richtung gefragt.

### Spielende

Das Spiel endet, wenn kein Stern mehr uebrig ist oder wenn beide Spieler je
`Spiel.MAX_ZUEGE` Zuege gemacht haben. Anschliessend werden die Punktzahlen verglichen
und das Ergebnis ausgegeben (Sieg Spieler 1, Sieg Spieler 2 oder unentschieden).
Danach wird erneut das Menue angezeigt.

### Der Computer-Gegner

Der Computer waehlt immer die Richtung, die ihm den **groessten Punktgewinn** bringt
(Sternwert des Zielfeldes plus Nachbar-Bonus). Richtungen, die auf dem Schiff des
Gegners landen, kommen nicht in Frage. Gibt es mehrere Richtungen mit gleichem Gewinn,
waehlt er die mit der **kleinsten Richtungsnummer**.

---

## 2. Menuefuehrung

Bei Programmstart und nach jedem beendeten Spiel wird folgendes Menue ausgegeben:

```
Sternwanderung
1 - Ein Spieler
2 - Zwei Spieler
3 - Programm beenden
```

Es wird so lange ausgegeben, bis eine passende Zahl (1, 2 oder 3) eingegeben wird.
Sie duerfen davon ausgehen, dass nur ganze Zahlen eingegeben werden.

- Bei `1` wird der Name des Spielers eingelesen; Gegner ist der Computer.
- Bei `2` werden die Namen beider Spieler eingelesen.
- Bei `3` wird das Programm beendet.

## 3. Falsche Eingaben

Fangen Sie falsche Eingaben ab:
- im Menue (siehe oben),
- bei der Richtung: es wird so lange gefragt, bis eine Zahl von 0 bis 7 eingegeben wird,
  die nicht auf dem Schiff des Gegners landet.

---

## 4. Aufbau des Programms

Ihr Programm besteht aus den Klassen `Spieler`, `Computer`, `Sternenhimmel` und `Spiel`.
Die Klassen sind in der Vorlage angelegt. Ergaenzen Sie den Code an den mit `TODO`
markierten Stellen und aendern Sie dazu gegebenenfalls auch die `return`-Anweisung.
Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

---

### 4.1 Klasse `Spieler`

Objektattribute: Name, Spielernummer (1 oder 2), Zeile und Spalte des Schiffes, Punkte.

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 1 | `Spieler(String name, int nummer, int zeile, int spalte)` | Setzt Name, Spielernummer und die Startposition des Schiffes aus den Parametern. Die Punkte beginnen bei 0. |
| 2 | `String getName()` | Gibt den Namen zurueck. |
| 3 | `int getNummer()` | Gibt die Spielernummer zurueck. |
| 4 | `int getZeile()` | Gibt die Zeile des Schiffes zurueck. |
| 5 | `int getSpalte()` | Gibt die Spalte des Schiffes zurueck. |
| 6 | `int getPunkte()` | Gibt die Punkte zurueck. |
| 7 | `void setzePosition(int zeile, int spalte)` | Setzt das Schiff auf die uebergebene Position. |
| 8 | `void punkteDazu(int p)` | Addiert `p` zu den Punkten. |

### 4.2 Klasse `Computer`

Die Klasse `Computer` ist eine Unterklasse von `Spieler`.

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 9 | `Computer(int nummer, int zeile, int spalte)` | Erzeugt einen Computer-Spieler mit dem Namen `Computer`, der uebergebenen Spielernummer und der uebergebenen Startposition. |
| 10 | `int waehleRichtung(Sternenhimmel himmel, Spieler gegner)` | Gibt die Richtung (0 bis 7) mit dem groessten Punktgewinn zurueck (siehe Abschnitt „Der Computer-Gegner"). Kommt keine Richtung in Frage, wird `-1` zurueckgegeben. |

### 4.3 Klasse `Sternenhimmel`

Objektattribute (genau wie vorgegeben verwenden):
- `char[][] sterne` — die Sterne des Feldes, `LEER` fuer ein leeres Feld
- `Random rd` — Zufallsgenerator

Vorgegeben sind ausserdem die Konstanten `GROESSE`, `LEER` sowie die Felder `DZ` und `DS`,
die die 8 Richtungen beschreiben (`DZ[r]` ist die Zeilenaenderung, `DS[r]` die
Spaltenaenderung der Richtung `r`). Der Konstruktor ist vorgegeben; er legt das Feld an
und ruft `initialisiereSterne()` und `mischeSterne()` auf.

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 11 | `void initialisiereSterne()` | Belegt `sterne` **zeilenweise**: Zeile 0 enthaelt acht Mal `A`, Zeile 1 acht Mal `B`, ... , Zeile 7 acht Mal `H`. Verwenden Sie dazu Schleifen und die ASCII-Zahlen der Buchstaben (`A` entspricht der 65). Eine Aufzaehlung der Buchstaben ist nicht erlaubt. |
| 12 | `void mischeSterne()` | Bringt die Sterne in eine zufaellige Reihenfolge. Wenden Sie genau folgenden Algorithmus an: *Fuer alle Zeilen z, angefangen bei der letzten bis zur 0.: Fuer alle Spalten s, angefangen bei der letzten bis zur 0.: Erstelle eine Zufallszahl rd1 zwischen 0 und z. Erstelle eine Zufallszahl rd2 zwischen 0 und s. Vertausche die Sterne der Stellen (z,s) und (rd1,rd2).* Eine Zufallszahl zwischen 0 und x erhalten Sie mit `rd.nextInt(x + 1)`. |
| 13 | `char getStern(int z, int s)` | Gibt das Zeichen an der Stelle (z,s) zurueck. |
| 14 | `boolean imFeld(int z, int s)` | Gibt `true` zurueck, wenn (z,s) existierende Koordinaten des Feldes sind, sonst `false`. |
| 15 | `int wert(char stern)` | Gibt den Sternwert zurueck: `A` = 1, `B` = 2, ... , `H` = 8, `LEER` = 0. Berechnen Sie den Wert aus der ASCII-Zahl; eine Aufzaehlung aller Faelle ist nicht erlaubt. |
| 16 | `boolean istLeer(int z, int s)` | Gibt `true` zurueck, wenn (z,s) im Feld liegt und dort `LEER` steht, sonst `false`. |
| 17 | `int zaehleSterne()` | Gibt die Anzahl der Felder zurueck, die noch einen Stern tragen (also nicht leer sind). |
| 18 | `boolean istSpielEnde()` | Gibt `true` zurueck, wenn kein Stern mehr uebrig ist, sonst `false`. |
| 19 | `int normiere(int wert, int laenge)` | Gibt den Wert zurueck, der `wert` innerhalb des Bereiches 0 bis `laenge`−1 entspricht, wenn man das Feld als rundherum geschlossen betrachtet. Fuer `laenge` = 8 gilt zum Beispiel: −1 wird zu 7, 8 wird zu 0, −9 wird zu 7, 3 bleibt 3. Beachten Sie, dass `%` in Java bei negativen Zahlen ein negatives Ergebnis liefert. |
| 20 | `int zielZeile(int zeile, int richtung, int schritte)` | Gibt die Zeile zurueck, auf der ein Schiff landet, das von `zeile` aus `schritte` Schritte in die Richtung `richtung` faehrt. Nutzen Sie `DZ` und `normiere(...)`. |
| 21 | `int zielSpalte(int spalte, int richtung, int schritte)` | Gibt die Spalte zurueck, auf der ein Schiff landet, das von `spalte` aus `schritte` Schritte in die Richtung `richtung` faehrt. Nutzen Sie `DS` und `normiere(...)`. |
| 22 | `int schrittweite(int z, int s)` | Gibt die Schrittweite eines Schiffes zurueck, das auf (z,s) steht: den Sternwert des Feldes, oder 1, wenn das Feld leer ist. |
| 23 | `int zaehleGleicheNachbarn(int z, int s)` | Gibt zurueck, wie viele der **8 Nachbarn** von (z,s) (waagerecht, senkrecht und diagonal) **denselben Buchstaben** tragen wie (z,s) selbst. Das Feld (z,s) selbst zaehlt nicht mit; das Feld gilt hier als begrenzt (kein Umschliessen des Randes). Ist (z,s) leer oder nicht im Feld, wird 0 zurueckgegeben. |
| 24 | `int sammle(int z, int s)` | Sammelt den Stern auf (z,s) ein und gibt die erzielten Punkte zurueck: Sternwert plus Anzahl der gleichen Nachbarn. Das Feld wird danach `LEER`. Ist (z,s) leer oder nicht im Feld, wird nichts geaendert und 0 zurueckgegeben. |
| 25 | `String toString(Spieler s1, Spieler s2)` | Gibt einen String zurueck, der das Feld gemaess Abschnitt 5 fuer die Konsolenausgabe darstellt. |

### 4.4 Klasse `Spiel`

Vorgegeben ist die Konstante `MAX_ZUEGE`.

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 26 | `boolean checkEingabeMenu(int eingabe)` | Gibt `true` zurueck, wenn eine gueltige Menue-Zahl (1, 2 oder 3) eingegeben wurde, sonst `false`. |
| 27 | `boolean checkRichtung(int richtung)` | Gibt `true` zurueck, wenn `richtung` eine gueltige Richtung (0 bis 7) ist, sonst `false`. |
| 28 | `int menu()` | Gibt das Menue so lange aus, bis eine erlaubte Zahl eingegeben wird. Diese Zahl ist der Rueckgabewert. |
| 29 | `String auswertung(Spieler s1, Spieler s2)` | Gibt einen String zurueck, der das Spielergebnis beschreibt: hat Spieler 1 mehr Punkte, `"<Name1> hat gewonnen!"`; hat Spieler 2 mehr Punkte, `"<Name2> hat gewonnen!"`; bei Gleichstand `"Unentschieden."` |
| 30 | `void main(String[] args)` | Setzt den kompletten Programmablauf um (siehe Abschnitt 6). |

---

## 5. Darstellung des Sternenhimmels

`toString(Spieler s1, Spieler s2)` liefert eine Zeile mit den Spaltennummern und danach
fuer jede Zeile des Feldes eine Zeile, die mit der Zeilennummer beginnt. Jedes Feld wird
durch **ein Zeichen** dargestellt:

| Inhalt des Feldes | Zeichen |
|---|---|
| Schiff von Spieler 1 steht hier | `1` |
| Schiff von Spieler 2 steht hier | `2` |
| leeres Feld | `.` |
| sonst | der Buchstabe des Sterns |

Steht ein Schiff auf einem Feld, wird der Inhalt des Feldes also verdeckt. Die Felder
einer Zeile werden durch zwei Leerzeichen getrennt, die Zeilen durch einen
Zeilenumbruch. Das Zeichen `.` darf an keiner anderen Stelle der Darstellung vorkommen.

Beispiel eines frischen Feldes (Schiffe auf (0,0) und (7,7)):

```
    0  1  2  3  4  5  6  7
0   1  A  F  B  H  D  A  G
1   E  B  C  H  D  F  A  B
2   G  A  D  C  E  H  F  B
3   B  H  G  E  C  A  D  F
4   A  D  B  G  F  C  H  E
5   H  F  E  A  B  G  C  D
6   D  C  H  F  G  B  E  A
7   F  G  A  D  E  H  B  2
```

Nach einigen Zuegen (eingesammelte Felder sind leer):

```
    0  1  2  3  4  5  6  7
0   .  A  F  B  H  D  A  G
1   E  B  C  H  .  F  A  B
2   G  A  D  C  E  H  F  B
3   B  1  G  E  C  A  D  F
4   A  D  B  G  F  C  H  E
5   H  F  E  A  B  2  C  D
6   D  C  H  F  G  B  E  A
7   F  G  A  D  E  H  B  .
```

---

## 6. Programmablauf und Konsolenausgabe

Ihre Konsolenausgaben muessen dem Schema des folgenden Beispiels folgen
(Nutzereingaben sind mit `>` markiert; sie werden nicht ausgegeben).

```
Sternwanderung
1 - Ein Spieler
2 - Zwei Spieler
3 - Programm beenden
> 1
Gib deinen Namen ein:
> Alex

    0  1  2  3  4  5  6  7
0   1  A  F  B  H  D  A  G
1   E  B  C  H  D  F  A  B
2   G  A  D  C  E  H  F  B
3   B  H  G  E  C  A  D  F
4   A  D  B  G  F  C  H  E
5   H  F  E  A  B  G  C  D
6   D  C  H  F  G  B  E  A
7   F  G  A  D  E  H  B  2

Alex ist an der Reihe. Schiff auf (0,0), Schrittweite 3.
Welche Richtung (0-7)?
> 1
Alex landet auf (5,0) und erhaelt 8 Punkte.
Der aktuelle Punktestand lautet:
Alex: 8 Punkte
Computer: 0 Punkte

[... Sternenhimmel ...]

Computer ist an der Reihe. Schiff auf (7,7), Schrittweite 2.
Computer waehlt Richtung 0.
Computer landet auf (5,5) und erhaelt 7 Punkte.
Der aktuelle Punktestand lautet:
Alex: 8 Punkte
Computer: 7 Punkte

[...]

Alex ist an der Reihe. Schiff auf (5,0), Schrittweite 1.
Welche Richtung (0-7)?
> 9
Welche Richtung (0-7)?
> 4
Alex landet auf (5,1) und erhaelt 0 Punkte.

[...]

Das Spiel ist zu Ende.
Der aktuelle Punktestand lautet:
Alex: 173 Punkte
Computer: 168 Punkte
Alex hat gewonnen!

Sternwanderung
1 - Ein Spieler
2 - Zwei Spieler
3 - Programm beenden
> 3
```

### Anforderungen an `main`

`main` muss folgenden Ablauf umsetzen:

1. Menue anzeigen und Auswahl einlesen (`menu()`).
2. Bei Auswahl 3: Programm beenden.
3. Bei Auswahl 1: einen Namen einlesen, `Spieler` mit Nummer 1 auf Position (0,0) und
   `Computer` mit Nummer 2 auf Position (7,7) erzeugen. Bei Auswahl 2: zwei Namen
   einlesen und zwei `Spieler` auf diesen Positionen erzeugen.
4. Einen neuen `Sternenhimmel` erzeugen. Spieler 1 beginnt.
5. Solange das Spiel nicht zu Ende ist und noch nicht beide Spieler `MAX_ZUEGE` Zuege
   gemacht haben:
   - Sternenhimmel ausgeben; ansagen, wer an der Reihe ist, wo sein Schiff steht und
     welche Schrittweite gilt.
   - Ist der aktuelle Spieler ein `Computer`: seine Richtung ermitteln und ausgeben.
   - Sonst: so lange nach einer Richtung fragen, bis sie gueltig ist **und** nicht auf
     dem Schiff des Gegners landet.
   - Zielfeld berechnen, das Schiff dorthin setzen, den Stern einsammeln und die
     erhaltenen Punkte dem Spieler gutschreiben.
   - Zielposition und erhaltene Punkte ausgeben, danach den Punktestand beider Spieler.
   - Der andere Spieler ist an der Reihe.
6. Am Spielende: Sternenhimmel, Endpunktestand und das Ergebnis (`auswertung(...)`) ausgeben.
7. Erneut das Menue anzeigen.

---

## 7. Selbstkontrolle

Die Klasse `UnitTests` ist Teil Ihrer Vorlage (21 Tests). Sie koennen sich damit
jederzeit selbst ueberpruefen. Alternativ steht `TestRunner` bereit
(`Run As` -> `Java Application`), falls JUnit nicht verfuegbar ist.

Beachten Sie, dass ein Bestehen der UnitTests noch nicht gleichbedeutend mit dem
Bestehen der Pruefung ist: `main` und die Konsolenausgaben werden zusaetzlich von Hand
bewertet. Halten Sie sich an alle Vorgaben dieser Aufgabenstellung.

**Viel Erfolg!**
