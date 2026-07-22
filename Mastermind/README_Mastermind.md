# Uebungs-Klausur — Mastermind / Bulls & Cows

Konsolen-Spiel im Klausur-Stil. Das Geruest ist vorgegeben — du fuellst die 12 `// TODO`s.

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel

Der Computer denkt sich einen geheimen Code aus **4 verschiedenen Ziffern** (0-9) aus.
Der Spieler raet den Code. Nach jedem Tipp bekommt er eine Rueckmeldung:
- **Bulls**: richtige Ziffer an richtiger Stelle.
- **Kuehe**: Ziffer kommt im Code vor, aber an einer anderen Stelle.

Wer den Code innerhalb von 10 Versuchen knackt (4 Bulls), gewinnt.

## Projektstruktur

```
Examen_Mastermind/
└── src/
    ├── Spieler.java     <- Name, Versuche
    ├── Code.java        <- geheimer Code + Auswertung
    ├── Spiel.java       <- Menue + Ablauf (Ein-/Ausgabe)
    ├── UnitTests.java   <- 10 JUnit-5-Tests (nicht veraendern)
    └── TestRunner.java  <- Test ohne JUnit (Run As -> Java Application)
```

## Empfohlene Reihenfolge

| TODO | Datei | Methode | Schwierigkeit |
|------|-------|---------|---------------|
| 1 | Spieler | Konstruktor | leicht |
| 2 | Spieler | getName | leicht |
| 3 | Spieler | getVersuche | leicht |
| 4 | Spieler | versuchDazu | leicht |
| 5 | Code | getLaenge | leicht |
| 6 | Code | enthaelt | mittel |
| 7 | Code | zaehleBulls | mittel |
| 8 | Code | istGeloest | leicht |
| 9 | Code | zaehleKuehe | schwer |
| 10 | Code | initialisiereCode | schwer |
| 11 | Spiel | checkEingabeMenu | leicht |
| 12 | Spiel | menu | mittel |

## Deine Merkpunkte

- **zaehleBulls (TODO 7)**: einfache Schleife, `tipp[i] == code[i]` zaehlen.
- **zaehleKuehe (TODO 9)**: nur zaehlen, wenn `tipp[i] != code[i]` UND `enthaelt(tipp[i])`.
  Also: kein Bull, aber die Ziffer ist irgendwo im Code.
- **initialisiereCode (TODO 10)**: verschiedene Ziffern -> merke mit einem `boolean[10]`,
  welche Ziffern schon benutzt sind (nicht die Default-Nullen des Arrays verwechseln!).
- **istGeloest (TODO 8)**: einfach `zaehleBulls(tipp) == getLaenge()`.

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (10 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg!
