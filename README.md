# paintball

## Anforderungen

- Hotelgäste können Reservierungen vornehmen
- Hotelgäste können einchecken, dabei wird, wenn vorhanden, aus einer
Reservierung eine Buchung. Ansonsten wird das Zimmer direkt ausgewählt und gebucht.
- es gibt die Funktion „Check-out“, mit der Gäste erfasst werden, die das Hotel verlassen (optional: nicht alle
Personen eines Zimmers checken aus!). Die Funktion gibt die entsprechenden Zimmer frei und
erstellt eine Abrechnung (auf dem Bildschirm, optional: Abrechnung als PDF, noch optionaler:
Ausdruck des PDFs, extrem optional: per Email). Dabei sind die zugebuchten Services berücksichtigt.
- die Software ermöglicht die Erstellung einer Lohnabrechnung. Dazu gibt der User
(Mitarbeiter) einen Monat ein und erhält eine Übersicht aller in diesem Monat beschäftigen
Mitarbeiter, deren Gehaltsstufe, den Monatslohn pro Mitarbeiter und die gesamten Lohnkosten  für  den angegebenen Monat. Dazu muss für jeden Mitarbeiter Einstellungs- und Entlassungsdatum gespeichert werden (optional: „reale“ Abrechnung mit Sozialleistungen und Steuer. Optional: Gehaltserhöhungen)
-es gibt einen Eingabedialog/Oberflächenbestandteil, mit dem die Daten eines Gastes oder Mitarbeiter
(Ihr hab die Wahl) verändert werden können (Familienname, Adresse etc)
- bei Programmstart prüft das Programm auf Mitarbeiter die vor mehr als drei Jahren
entlassen wurden und löscht deren Daten aus der Datenbank

## Projektordner erstellen

### Zum rendern der UML-Diagramme muss [Graphviz](http://www.graphviz.org/) installiert sein!

### Zum erstellen einiger Dokumente als PDF müssen alle LibreOffice-Anwendungen geschlossen sein!

./build-projec-folder.sh \[Zielordner\]

Weitere Optionen:

--install                   Installiert die Datenbank in postgresql

--desktop-link \[Linkname\] Erstellt eine Verknüpfung auf dem Desktop

## Kontakt

- dedda102@gmail.com
- metalerfreak@web.de