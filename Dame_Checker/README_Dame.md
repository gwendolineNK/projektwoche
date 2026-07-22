# Uebungs-Klausur — Dame / Checkers (vereinfacht)

Konsolen-Spiel im Klausur-Stil. Das Geruest ist vorgegeben — du fuellst die 15 `// TODO`s.

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel (vereinfachte Regeln)

8x8-Brett. Spieler 1 (X) zieht nach UNTEN, Spieler 2 (O) nach OBEN.
- **Einfacher Zug**: ein Feld diagonal vorwaerts auf ein leeres Feld.
- **Schlagzug**: zwei Felder diagonal vorwaerts auf ein leeres Feld, wobei in der
  Mitte ein Gegnerstein liegt (der entfernt wird).
- Es gibt (in dieser Fassung) KEINE Dame-Umwandlung und KEINE Mehrfachspruenge.
- Wer keine Steine mehr hat, verliert.

`feld[z][s]`: `0` leer, `1` Spieler 1, `2` Spieler 2. Anzeige: `.`, `X`, `O`.

## Projektstruktur

```
Examen_Dame/
└── src/
    ├── Spieler.java     <- Name, Spielernummer
    ├── Spielfeld.java   <- Brett + Zug-Regeln
    ├── Spiel.java       <- Menue + Ablauf (Ein-/Ausgabe)
    ├── UnitTests.java   <- 14 JUnit-5-Tests (nicht veraendern)
    └── TestRunner.java  <- Test ohne JUnit (Run As -> Java Application)
```

## Empfohlene Reihenfolge

| TODO | Datei | Methode | Schwierigkeit |
|------|-------|---------|---------------|
| 1 | Spieler | Konstruktor | leicht |
| 2 | Spieler | getName | leicht |
| 3 | Spieler | getSpielernummer | leicht |
| 4 | Spielfeld | getZelle | leicht |
| 5 | Spielfeld | gegner | leicht |
| 6 | Spielfeld | imFeld | leicht |
| 7 | Spielfeld | richtung | leicht |
| 8 | Spielfeld | zaehleSteine | mittel |
| 9 | Spielfeld | istEinfacherZugGueltig | mittel |
| 10 | Spielfeld | istSchlagzugGueltig | schwer |
| 11 | Spielfeld | fuehreZug | schwer |
| 12 | Spielfeld | hatGewonnen | leicht |
| 13 | Spielfeld | toString | mittel |
| 14 | Spiel | checkEingabeMenu | leicht |
| 15 | Spiel | menu | mittel |

## Deine Merkpunkte

- **richtung (TODO 7)** ist der Schluessel: Spieler 1 -> +1 (nach unten),
  Spieler 2 -> -1 (nach oben). "Vorwaerts" wird ueber richtung(nummer) ausgedrueckt.
- **istEinfacherZugGueltig (TODO 9)**: `z2 - z1 == richtung(nummer)` und Spalte um 1
  (`s2 - s1 == 1` oder `== -1`). Start = eigener Stein, Ziel = leer, beide im Feld.
- **istSchlagzugGueltig (TODO 10)**: `z2 - z1 == 2 * richtung(nummer)`, Spalte um 2,
  und in der Mitte `feld[(z1+z2)/2][(s1+s2)/2] == gegner(nummer)`.
- **fuehreZug (TODO 11)**: Stein umsetzen; war die Distanz 2 (Schlagzug), den Stein
  in der Mitte auf 0 setzen.
- Ohne `Math`: statt `Math.abs(x)==1` schreibe `x == 1 || x == -1`.

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (14 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg!
