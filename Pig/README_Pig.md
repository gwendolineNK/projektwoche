# Uebungs-Klausur — Pig (Wuerfel, Push-your-luck)

Konsolen-Spiel im Klausur-Stil. Das Geruest ist vorgegeben — du fuellst die 10 `// TODO`s.
Ein leichteres Spiel: kein Spielfeld, dafuer Wuerfel-, Runden- und Punktelogik.

> Erlaubt: nur `Scanner`, `Random`, `System.out.print/println`.
> Kein `ä ö ü ß` im Code. Ein-/Ausgabe nur in der Klasse `Spiel`.

## Das Spiel

Zwei Spieler. Wer dran ist, wuerfelt beliebig oft:
- Jeder Wurf (2-6) wird zum **Rundenpunktestand** addiert.
- Bei einer **1** verfaellt der ganze Rundenpunktestand und der Zug endet sofort.
- **Haelt** der Spieler, werden die Rundenpunkte dem Gesamtstand gutgeschrieben.
Wer zuerst **100** Punkte erreicht, gewinnt.

## Projektstruktur

```
Examen_Pig/
└── src/
    ├── Spieler.java     <- Name, Gesamtpunkte
    ├── Spiel.java       <- Menue + Wuerfel-/Rundenlogik + Ablauf
    ├── UnitTests.java   <- 7 JUnit-5-Tests (nicht veraendern)
    └── TestRunner.java  <- Test ohne JUnit (Run As -> Java Application)
```

## Empfohlene Reihenfolge

| TODO | Datei | Methode | Schwierigkeit |
|------|-------|---------|---------------|
| 1 | Spieler | Konstruktor | leicht |
| 2 | Spieler | getName | leicht |
| 3 | Spieler | getPunkte | leicht |
| 4 | Spieler | punkteDazu | leicht |
| 5 | Spiel | checkEingabeMenu | leicht |
| 6 | Spiel | wuerfeln | leicht |
| 7 | Spiel | istPech | leicht |
| 8 | Spiel | neuerRundenScore | mittel |
| 9 | Spiel | hatGewonnen | leicht |
| 10 | Spiel | menu | mittel |

## Deine Merkpunkte

- **wuerfeln (TODO 6)**: `rd.nextInt(6) + 1` gibt 1-6.
- **neuerRundenScore (TODO 8)**: bei einer 1 -> `return 0;` (alles weg), sonst
  `return rundenScore + wurf;`. Du kannst `istPech(wurf)` benutzen.
- **hatGewonnen (TODO 9)**: einfacher Vergleich `s.getPunkte() >= ziel`.
- **punkteDazu (TODO 4)**: `punkte = punkte + p;` (nicht ueberschreiben, ADDIEREN).

## Testen

- JUnit: Rechtsklick auf `UnitTests.java` → Run As → JUnit Test (7 Tests).
- Ohne JUnit: Rechtsklick auf `TestRunner.java` → Run As → **Java Application**.

Viel Erfolg!
