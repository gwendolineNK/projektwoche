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

- Ihr Code mindestens **9 von 12 Unit-Tests**, darunter unbedingt **`testKannAnwerben()`**, besteht, und
- Ihr Spiel bei korrekten Nutzereingaben spielbar ist und
- Sie alle anderen Vorgaben zum Code einhalten.

---

# Prüfungsaufgabe: „Soeldnerkrieg Light"

## Spielablauf

Zwei Feldherren kaempfen auf einem quadratischen Schlachtfeld mit n Zeilen und n Spalten
(n ist mindestens 4) um dieselben Soeldner. **Ein Soeldner kaempft fuer den, der ihn
bezahlt** – und er wechselt die Seite, sobald ein staerkerer Soeldner ihn anwirbt.
Spieler 1 hat das Zeichen `X`, Spieler 2 das Zeichen `O`.

### Grundaufstellung

Zu Beginn stehen alle Soeldner in den beiden Randzeilen: Zeile `0` gehoert Spieler 1,
Zeile `n-1` gehoert Spieler 2. Der Soeldner in der Spalte `c` hat die Staerke
`c % 3 + 1`, also der Reihe nach 1, 2, 3, 1, 2, 3, ... Alle uebrigen Felder sind frei.

Bei n = 5 ergibt das:

```
      0   1   2   3   4
  0 | X1  X2  X3  X1  X2
  1 |  .   .   .   .   .
  2 |  .   .   .   .   .
  3 |  .   .   .   .   .
  4 | O1  O2  O3  O1  O2
```

### Ein Zug

Wer an der Reihe ist, nennt zuerst das Feld eines **eigenen** Soeldners und danach ein
**Nachbarfeld** (waagerecht oder senkrecht, niemals diagonal). Je nachdem, was auf dem
Nachbarfeld steht, geschieht eines von zwei Dingen:

- **Ziehen**: Ist das Nachbarfeld frei, so zieht der Soeldner dorthin. Es ist derselbe
  Soeldner, er steht nur woanders.
- **Anwerben**: Steht dort ein Soeldner des Gegners, der **echt schwaecher** ist als der
  eigene, so wechselt dieser gegnerische Soeldner die Seite. Er bleibt dabei auf seinem
  Feld stehen und behaelt seine Staerke – nur sein Dienstherr ist ein anderer. Der
  eigene Soeldner bleibt ebenfalls stehen.

Jede andere Eingabe ist ungueltig: ein fremder oder leerer Startpunkt, ein Feld, das kein
Nachbarfeld ist, ein gegnerischer Soeldner, der gleich stark oder staerker ist, oder ein
Feld ausserhalb des Schlachtfeldes.

### Spielende

Das Spiel endet, sobald **alle** Soeldner auf dem Schlachtfeld demselben Spieler dienen;
dieser Spieler gewinnt. Sind nach `MAX_RUNDEN` Runden noch beide Seiten vertreten, so
gewinnt, wer mehr Soeldner besitzt; bei Gleichstand endet das Spiel unentschieden.

---

## Programmablauf

```
Soeldnerkrieg Light
1 - Spiel starten
2 - Programm beenden
```

Dieses Menue wird so lange ausgegeben, bis eine der Zahlen 1 oder 2 eingegeben wird.

- Bei `1` werden die Namen der beiden Spieler und die Groesse des Schlachtfeldes
  eingelesen. Die Groesse wird so lange eingelesen, bis eine Zahl von mindestens 4
  eingegeben wurde.
  - Vor jedem Zug werden das Schlachtfeld und der Stand ausgegeben.
  - Es wird so lange nach Zeile und Spalte des eigenen Soeldners und danach nach Zeile
    und Spalte des Zielfeldes gefragt, bis ein gueltiger Zug genannt wird.
  - Nach jedem Zug wird gemeldet, ob gezogen oder angeworben wurde.
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

Ihr Programm muss mindestens die Klassen `Spiel`, `Spieler`, `Schlachtfeld` und `Soeldner`
enthalten. In der Projektvorlage sind diese Klassen bereits angelegt. Fuegen Sie Code
hinzu und aendern Sie dazu gegebenenfalls auch die `return`-Anweisungen. Ansonsten
duerfen Sie den vorhandenen Code nicht aendern oder loeschen.

### Die Beziehungen zwischen den Klassen

Dieses Programm verwendet ein ganz bestimmtes Modell:

> **Ein `Soeldner` kennt seinen `Spieler` – ein `Spieler` kennt seine Soeldner NICHT.**

- Ein Objekt der Klasse **`Soeldner`** hat ein Attribut `dienstherr` vom Typ `Spieler`.
  Beim Anwerben wird **kein neuer Soeldner erzeugt**; es wird nur dieses Attribut
  geaendert. Position und Staerke bleiben unveraendert.
- Ein Objekt der Klasse **`Spieler`** hat **kein** Feld mit Soeldnern. Es kennt seinen
  Namen, sein Zeichen, die Anzahl seiner bisherigen Anwerbungen und – ebenfalls als
  Objektbeziehung – seinen **Gegner**.
- Ein Objekt der Klasse **`Schlachtfeld`** verwaltet ein zweidimensionales Feld vom Typ
  `Soeldner`. Ein freies Feld wird durch `null` dargestellt.

Das hat eine wichtige Folge fuer Ihren Code: Auf die Frage „wie viele Soeldner habe ich?"
kann ein `Spieler` nicht aus eigener Kraft antworten. Die Antwort liefert das
**Schlachtfeld**, indem es alle Felder durchlaeuft und jeden Soeldner nach seinem
Dienstherrn fragt. Genau das leistet `Schlachtfeld.zaehleSoeldner(Spieler)`.

Dieses Modell passt zu den Spielregeln: Ein Soeldner **wechselt staendig die Seite**.
Eine feste Liste beim Spieler waere nach dem ersten Anwerben falsch – die Information
gehoert deshalb an den Soeldner.

### Die vier Richtungen

Legen Sie sich in `Schlachtfeld` zwei Felder mit den vier Richtungsvektoren an (sie sind
in der Vorlage bereits vorhanden) und durchlaufen Sie diese mit einer Schleife, statt den
Code viermal abzuschreiben.

---

## Beispiel für einen Spielablauf (Nutzereingaben nach `>`)

```
Herzlich Willkommen zu Soeldnerkrieg Light

Soeldnerkrieg Light
1 - Spiel starten
2 - Programm beenden
> 1
Gib den Namen von Spieler 1 ein:
> Alex
Gib den Namen von Spieler 2 ein:
> Toni
Wie gross soll das Schlachtfeld sein?
> 5
--------------------------------
      0   1   2   3   4
  0 | X1  X2  X3  X1  X2
  1 |  .   .   .   .   .
  2 |  .   .   .   .   .
  3 |  .   .   .   .   .
  4 | O1  O2  O3  O1  O2

Alex (X): 5   Toni (O): 5
Alex ist an der Reihe.
Zeile des eigenen Soeldners: > 0
Spalte des eigenen Soeldners: > 2
Zeile des Zielfeldes: > 1
Spalte des Zielfeldes: > 2
Alex zieht auf (1,2).
--------------------------------
      0   1   2   3   4
  0 | X1  X2   .  X1  X2
  1 |  .   .  X3   .   .
  2 |  .   .   .   .   .
  3 |  .   .   .   .   .
  4 | O1  O2  O3  O1  O2

Alex (X): 5   Toni (O): 5
Toni ist an der Reihe.
[...]
Zeile des eigenen Soeldners: > 3
Spalte des eigenen Soeldners: > 2
Zeile des Zielfeldes: > 2
Spalte des Zielfeldes: > 2
Dieser Zug ist nicht gueltig.
[...]
Alex wirbt den Soeldner auf (3,2) an.

Spielende!
Alex (X): 10   Toni (O): 0
Alex hat gewonnen!

Soeldnerkrieg Light
1 - Spiel starten
2 - Programm beenden
> 2
Programm beendet.
```
