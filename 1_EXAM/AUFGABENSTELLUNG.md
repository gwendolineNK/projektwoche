# Einfuehrung in die Programmierung — Pruefungsaufgabe „Runenkampf"

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
- Verwenden Sie in den Methoden der Klasse `Spielfeld` die **Laengen der Felder**
  (`runen.length`, `runen[0].length`), nicht die Konstante `GROESSE`.

---

## 1. Das Spiel

Auf einem quadratischen Spielfeld der Groesse 8x8 liegen **Runen**. Eine Rune ist einer
der Buchstaben `A` bis `H`. Jeder dieser 8 Buchstaben kommt genau 8 Mal vor
(8 x 8 = 64 Felder). Die Runen liegen in zufaelliger Reihenfolge.

Zwei Spieler spielen gegeneinander (oder ein Spieler gegen den Computer). Zusaetzlich zu
den Runen speichert das Spielfeld fuer jedes Feld einen **Besitzer**: `0` = neutral
(noch niemandem gehoerend), `1` = Spieler 1, `2` = Spieler 2.

### Gebiete

Ein **Gebiet** ist eine Gruppe von Feldern, die
1. alle **dieselbe Rune** tragen,
2. alle **neutral** sind (Besitzer `0`) und
3. ueber waagerechte und senkrechte Nachbarschaft (oben, unten, links, rechts —
   **nicht** diagonal) miteinander verbunden sind.

Beispiel (Ausschnitt, alle Felder neutral):

```
A A B B
A C C B
D D C B
D A A A
```

- Das Gebiet um (0,0) besteht aus (0,0), (0,1), (1,0) — Groesse 3.
- Das Gebiet um (0,2) besteht aus (0,2), (0,3), (1,3), (2,3) — Groesse 4.
- Das Gebiet um (3,1) besteht aus (3,1), (3,2), (3,3) — Groesse 3.
  Das Feld (3,0) gehoert nicht dazu, es traegt die Rune D.

### Zug: Gebiet beanspruchen

Wer an der Reihe ist, nennt die Koordinaten eines **neutralen** Feldes. Der Spieler
erhaelt daraufhin das **gesamte Gebiet** dieses Feldes: alle Felder des Gebietes
bekommen ihn als Besitzer. Bereits beanspruchte Felder sind nicht mehr neutral und
koennen ein Gebiet dadurch in mehrere kleinere Gebiete zerschneiden.

### Punkte

Jede Rune hat einen **Runenwert**: `A` = 1, `B` = 2, `C` = 3, ... , `H` = 8.
Die Punktzahl eines Spielers ist die **Summe der Runenwerte aller Felder**, die ihm
gehoeren. (Ein Gebiet aus 4 Feldern mit der Rune `B` bringt also 4 x 2 = 8 Punkte.)

### Sonderzug: Zeile rotieren

Jeder Spieler darf **genau einmal pro Spiel** anstelle eines normalen Zuges eine Zeile
seiner Wahl **zyklisch um eine Position nach rechts rotieren**. Dabei wandern sowohl die
Runen als auch die Besitzer der Zeile mit; das Element ganz rechts wandert nach ganz links.
Danach ist der Gegner an der Reihe. Der Computer nutzt den Sonderzug nie.

### Spielende

Das Spiel endet, wenn kein neutrales Feld mehr existiert. Anschliessend werden die
Punktzahlen verglichen und das Ergebnis ausgegeben (Sieg Spieler 1, Sieg Spieler 2 oder
unentschieden). Danach wird erneut das Menue angezeigt.

### Der Computer-Gegner

Der Computer waehlt immer das neutrale Feld, dessen Beanspruchung ihm den **groessten
Punktgewinn** bringt. Der Punktgewinn eines Feldes ist `Gebietsgroesse x Runenwert`.
Gibt es mehrere Felder mit gleichem Gewinn, waehlt er das mit der **kleinsten Zeile**,
bei Gleichstand das mit der **kleinsten Spalte**.

---

## 2. Menuefuehrung

Bei Programmstart und nach jedem beendeten Spiel wird folgendes Menue ausgegeben:

```
Runenkampf
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
- bei der Wahl der Zugart (1 oder 2),
- bei den Koordinaten eines Zuges: es wird so lange nach Zeile und Spalte gefragt, bis
  ein existierendes, **neutrales** Feld angegeben wird,
- bei der Zeilennummer des Sonderzuges: es wird so lange gefragt, bis eine existierende
  Zeile angegeben wird.

---

## 4. Aufbau des Programms

Ihr Programm besteht aus den Klassen `Spieler`, `Computer`, `Spielfeld` und `Spiel`.
Die Klassen sind in der Vorlage angelegt. Ergaenzen Sie den Code an den mit `TODO`
markierten Stellen und aendern Sie dazu gegebenenfalls auch die `return`-Anweisung.
Ansonsten duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

---

### 4.1 Klasse `Spieler`

Objektattribute: Name, Spielernummer (1 oder 2) und die Information, ob der Sonderzug
noch verfuegbar ist.

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 1 | `Spieler(String name, int nummer)` | Setzt Name und Spielernummer aus den Parametern. Der Sonderzug ist zu Beginn verfuegbar. |
| 2 | `String getName()` | Gibt den Namen zurueck. |
| 3 | `int getNummer()` | Gibt die Spielernummer zurueck. |
| 4 | `boolean hatRotation()` | Gibt zurueck, ob der Sonderzug noch verfuegbar ist. |
| 5 | `void rotationVerbrauchen()` | Sorgt dafuer, dass der Sonderzug nicht mehr verfuegbar ist. |

### 4.2 Klasse `Computer`

Die Klasse `Computer` ist eine Unterklasse von `Spieler`.

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 6 | `Computer(int nummer)` | Erzeugt einen Computer-Spieler mit dem Namen `Computer` und der uebergebenen Spielernummer. |
| 7 | `int[] waehleZug(Spielfeld feld)` | Gibt die Koordinaten des Feldes zurueck, das dem Computer den groessten Punktgewinn bringt (siehe Abschnitt „Der Computer-Gegner"), als Array `{zeile, spalte}`. Existiert kein neutrales Feld, wird `{-1, -1}` zurueckgegeben. |

### 4.3 Klasse `Spielfeld`

Objektattribute (genau wie vorgegeben verwenden):
- `char[][] runen` — die Runen des Spielfeldes
- `int[][] besitzer` — `0` neutral, `1` Spieler 1, `2` Spieler 2
- `Random rd` — Zufallsgenerator

Der Konstruktor ist vorgegeben. Er legt die Felder an und ruft
`initialisiereRunen()` und `mischeRunen()` auf.

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 8 | `void initialisiereRunen()` | Belegt `runen` **zeilenweise** mit den Runen: Zeile 0 enthaelt acht Mal `A`, Zeile 1 acht Mal `B`, ... , Zeile 7 acht Mal `H`. Verwenden Sie dazu Schleifen und die ASCII-Zahlen der Buchstaben (`A` entspricht der 65). Eine grosse Aufzaehlung der Buchstaben ist nicht erlaubt. |
| 9 | `void mischeRunen()` | Bringt die Runen in eine zufaellige Reihenfolge. Wenden Sie genau folgenden Algorithmus an: *Fuer alle Zeilen z, angefangen bei der letzten bis zur 0.: Fuer alle Spalten s, angefangen bei der letzten bis zur 0.: Erstelle eine Zufallszahl rd1 zwischen 0 und z. Erstelle eine Zufallszahl rd2 zwischen 0 und s. Vertausche die Runen der Stellen (z,s) und (rd1,rd2).* Eine Zufallszahl zwischen 0 und x erhalten Sie mit `rd.nextInt(x + 1)`. |
| 10 | `char getRune(int z, int s)` | Gibt die Rune an der Stelle (z,s) zurueck. |
| 11 | `int getBesitzer(int z, int s)` | Gibt den Besitzer der Stelle (z,s) zurueck. |
| 12 | `boolean imFeld(int z, int s)` | Gibt `true` zurueck, wenn (z,s) existierende Koordinaten des Spielfeldes sind, sonst `false`. |
| 13 | `int gegner(int nummer)` | Gibt die Spielernummer des Gegners zurueck (zu 1 gehoert 2, zu 2 gehoert 1). |
| 14 | `int runenWert(char rune)` | Gibt den Runenwert zurueck: `A` = 1, `B` = 2, ... , `H` = 8. Berechnen Sie den Wert aus der ASCII-Zahl des Buchstaben; eine Aufzaehlung aller Faelle ist nicht erlaubt. |
| 15 | `boolean istNeutral(int z, int s)` | Gibt `true` zurueck, wenn (z,s) im Spielfeld liegt **und** dort der Besitzer `0` ist, sonst `false`. |
| 16 | `int zaehleNeutrale()` | Gibt die Anzahl der neutralen Felder des gesamten Spielfeldes zurueck. |
| 17 | `boolean istSpielEnde()` | Gibt `true` zurueck, wenn es kein neutrales Feld mehr gibt, sonst `false`. |
| 18 | `int gebietGroesse(int z, int s)` | Gibt die Anzahl der Felder des Gebietes zurueck, zu dem (z,s) gehoert (Definition siehe Abschnitt „Gebiete"). Ist (z,s) kein neutrales Feld des Spielfeldes, wird `0` zurueckgegeben. Verwenden Sie **keine** Rekursion: Markieren Sie in einem Hilfsfeld zunaechst nur (z,s) und durchlaufen Sie danach das Spielfeld so lange wiederholt, bis in einem kompletten Durchlauf kein weiteres Feld mehr markiert wurde; dabei wird ein Feld markiert, wenn es neutral ist, dieselbe Rune traegt und einen bereits markierten waagerechten oder senkrechten Nachbarn hat. |
| 19 | `int beansprucheGebiet(int z, int s, int nummer)` | Setzt bei allen Feldern des Gebietes von (z,s) den Besitzer auf `nummer` und gibt die Anzahl der so beanspruchten Felder zurueck. Ist (z,s) kein neutrales Feld des Spielfeldes, wird nichts geaendert und `0` zurueckgegeben. |
| 20 | `int punkte(int nummer)` | Gibt die Punktzahl des Spielers `nummer` zurueck: die Summe der Runenwerte aller Felder, deren Besitzer `nummer` ist. |
| 21 | `void rotiereZeile(int z)` | Rotiert die Zeile `z` zyklisch um eine Position nach rechts: der Inhalt der Spalte s wandert nach Spalte s+1, der Inhalt der letzten Spalte wandert in die Spalte 0. Runen **und** Besitzer werden gemeinsam rotiert. Liegt `z` nicht im Spielfeld, wird nichts geaendert. |
| 22 | `String toString()` | Gibt einen String zurueck, der das Spielfeld gemaess den Vorgaben aus Abschnitt 5 fuer die Konsolenausgabe darstellt. |

### 4.4 Klasse `Spiel`

| TODO | Methode | Was die Methode tun soll |
|------|---------|--------------------------|
| 23 | `boolean checkEingabeMenu(int eingabe)` | Gibt `true` zurueck, wenn eine gueltige Menue-Zahl (1, 2 oder 3) eingegeben wurde, sonst `false`. |
| 24 | `int menu()` | Gibt das Menue so lange aus, bis eine erlaubte Zahl eingegeben wird. Diese Zahl ist der Rueckgabewert. |
| 25 | `String auswertung(Spieler s1, Spieler s2, Spielfeld feld)` | Gibt einen String zurueck, der das Spielergebnis beschreibt: hat Spieler 1 mehr Punkte, `"<Name1> hat gewonnen!"`; hat Spieler 2 mehr Punkte, `"<Name2> hat gewonnen!"`; bei Gleichstand `"Unentschieden."` |
| 26 | `void main(String[] args)` | Setzt den kompletten Programmablauf um (siehe Abschnitt 6). |

---

## 5. Darstellung des Spielfeldes

`toString()` liefert eine Zeile mit den Spaltennummern und danach fuer jede Zeile des
Spielfeldes eine Zeile, die mit der Zeilennummer beginnt. Jedes Feld wird durch **zwei
Zeichen** dargestellt: zuerst die Rune, danach ein Besitzer-Zeichen:

| Besitzer | 0 | 1 | 2 |
|----------|---|---|---|
| Zeichen  | `-` | `1` | `2` |

Die Felder einer Zeile werden durch ein Leerzeichen getrennt, die Zeilen durch einen
Zeilenumbruch. Das Zeichen `-` darf an keiner anderen Stelle der Darstellung vorkommen.

Beispiel eines frischen Spielfeldes (Ausschnitt, 4 Zeilen):

```
   0  1  2  3  4  5  6  7
0  C- A- F- B- H- D- A- G-
1  E- B- C- H- D- F- A- B-
2  G- A- D- C- E- H- F- B-
3  B- H- G- E- C- A- D- F-
```

Nach einigen Zuegen (Spieler 1 hat das Gebiet um (0,1) beansprucht, Spieler 2 das um (2,4)):

```
   0  1  2  3  4  5  6  7
0  C- A1 F- B- H- D- A- G-
1  E- B- C- H- D- F- A- B-
2  G- A1 D- C- E2 H- F- B-
3  B- H- G- E2 C- A- D- F-
```

---

## 6. Programmablauf und Konsolenausgabe

Ihre Konsolenausgaben muessen dem Schema des folgenden Beispiels folgen
(Nutzereingaben sind mit `>` markiert; sie werden nicht ausgegeben).

```
Runenkampf
1 - Ein Spieler
2 - Zwei Spieler
3 - Programm beenden
> 1
Gib deinen Namen ein:
> Alex

   0  1  2  3  4  5  6  7
0  C- A- F- B- H- D- A- G-
1  E- B- C- H- D- F- A- B-
2  G- A- D- C- E- H- F- B-
3  B- H- G- E- C- A- D- F-
4  A- D- B- G- F- C- H- E-
5  H- F- E- A- B- G- C- D-
6  D- C- H- F- G- B- E- A-
7  F- G- A- D- E- H- B- C-

Alex ist an der Reihe.
1 - Gebiet beanspruchen
2 - Zeile rotieren
> 1
Alex, gib eine Zeile an:
> 0
Alex, gib eine Spalte an:
> 4
Alex beansprucht 1 Feld(er) mit der Rune H.
Der aktuelle Punktestand lautet:
Alex: 8 Punkte
Computer: 0 Punkte

[... Spielfeld ...]

Computer ist an der Reihe.
Computer beansprucht 3 Feld(er) mit der Rune G.
Der aktuelle Punktestand lautet:
Alex: 8 Punkte
Computer: 21 Punkte

[... Spielfeld ...]

Alex ist an der Reihe.
1 - Gebiet beanspruchen
2 - Zeile rotieren
> 2
Alex, welche Zeile soll rotiert werden?
> 3
Zeile 3 wurde rotiert.

[...]

Das Spiel ist zu Ende.
Der aktuelle Punktestand lautet:
Alex: 148 Punkte
Computer: 140 Punkte
Alex hat gewonnen!

Runenkampf
1 - Ein Spieler
2 - Zwei Spieler
3 - Programm beenden
> 3
```

### Anforderungen an `main`

`main` muss folgenden Ablauf umsetzen:

1. Menue anzeigen und Auswahl einlesen (`menu()`).
2. Bei Auswahl 3: Programm beenden.
3. Bei Auswahl 1: einen Namen einlesen, `Spieler` mit Nummer 1 und `Computer` mit
   Nummer 2 erzeugen. Bei Auswahl 2: zwei Namen einlesen und zwei `Spieler` erzeugen.
4. Ein neues `Spielfeld` erzeugen. Spieler 1 beginnt.
5. Solange das Spiel nicht zu Ende ist:
   - Spielfeld ausgeben, ansagen, wer an der Reihe ist.
   - Ist der aktuelle Spieler ein `Computer`: seinen Zug ermitteln und das Gebiet
     beanspruchen.
   - Sonst: die Zugart erfragen. Bei „Gebiet beanspruchen" Zeile und Spalte einlesen
     (nur existierende, neutrale Felder zulassen) und das Gebiet beanspruchen. Bei
     „Zeile rotieren" (nur zulassen, wenn der Sonderzug noch verfuegbar ist) die
     Zeilennummer einlesen, die Zeile rotieren und den Sonderzug verbrauchen.
   - Anzahl der beanspruchten Felder und die betroffene Rune ausgeben bzw. melden,
     dass rotiert wurde.
   - Punktestand beider Spieler ausgeben.
   - Der andere Spieler ist an der Reihe.
6. Am Spielende: Spielfeld, Endpunktestand und das Ergebnis (`auswertung(...)`) ausgeben.
7. Erneut das Menue anzeigen.

---

## 7. Selbstkontrolle

Die Klasse `UnitTests` ist Teil Ihrer Vorlage (19 Tests). Sie koennen sich damit
jederzeit selbst ueberpruefen. Alternativ steht `TestRunner` bereit
(`Run As` -> `Java Application`), falls JUnit nicht verfuegbar ist.

Beachten Sie, dass ein Bestehen der UnitTests noch nicht gleichbedeutend mit dem
Bestehen der Pruefung ist: `main` und die Konsolenausgaben werden zusaetzlich von Hand
bewertet. Halten Sie sich an alle Vorgaben dieser Aufgabenstellung.

**Viel Erfolg!**
