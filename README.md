# PuzzleRoom

This is the repository for the exercise "PuzzleRoom".
In the code, you can find additional comments that explain the functionality in detail.

## Setup

* Maven-Projekt sollte automatisch richtig konfiguriert werden
  * Falls nicht: Doppel-Shift -> "Add maven project" -> pom.xml auswählen
* In den src-Files zur `Main` gehen und ausführen
* Ausgabe sollte mit `=== Puzzle Room ===` starten
* Mit `load test.maze` kann eine Testdatei geladen werden, und das erste maze sollte angezeigt werden
* Mit `move down` kann der Spieler (`*`) nach unten bewegt werden
* Mit `move right` -> `move right` -> `move up` kann das Rätsel gelöst werden
* Danach kann man sich nicht mehr bewegen, bis ein neuer `load`-Befehl ausgeführt wurde
* Mit `show` kann jederzeit der aktuelle Status angezeigt werden
* Die vorhandenen Tests sollten ebenfalls alle erfolgreich (grün) sein

## Rätselraum-Aufbau

Ein Rätselraum ist als einfache Textdatei definiert, hier immer mit der Endung `.maze` gekennzeichnet.
In dieser Datei ist der Rätselraum definiert, wobei aktuell folgende Zeichen verwendet werden:

* `o` ist das Startfeld, wo der Spieler startet. Davon muss es genau eines geben.
* `x` ist das Zielfeld, das der Spieler erreichen muss. Davon muss es genau eines geben.
* `#` ist ein Mauerfeld, das nicht betreten werden kann
* ` ` (Leerzeichen) ist ein Wegfeld, das frei betreten werden kann
* Im laufenden Spiel wird der Spieler dann mit `*` dargestellt (kann nicht in der Datei verwendet werden)

Die Datei `test.maze` im Projekt kann geöffnet werden, und zeigt einen einfachen Rätselraum.
In den Tests im `resources`-Ordner gibt es ebenfalls bereits 2 Testdateien.

## Tasks

Für jeden erledigten Task müssen **mindestens 2 Tests** erstellt werden.

Die Task-Beschreibung sollte **komplett** gelesen werden, bevor die Implementation begonnen wird.
Am Ende von jedem Task gibt es eine hilfreiche "Tipps"-Sektion!

### Einzelaufgaben

Jede Person in der Gruppe muss mindestens folgende Tasks erledigen:
* Mindestens 1 Issue auf GitHub erstellt
* Mindestens 1 Pull Request erstellt
  * dieser muss mindestens 1 selbst erstellten Commit (ausgenommen Merge-Commits) beinhalten

### A - Neuer Feldtyp: Einbahn [10%]

Ein neuer "Einbahn"-Feldtyp (`FieldOneWay`) soll hinzugefügt werden.
Dieses Feld kann von jeder Richtung aus betreten werden, kann aber nur
in die angezeigte Richtung verlassen werden.

Es soll mindestens 1 positiver und 1 negativer Test hinzugefügt werden.
(Beim negativen Test wird hier nicht direkt eine Exception gethrowed, sondern einfach nur `false` zurückgegeben und die Bewegung verhindert)

#### Zeichen

| Zeichen | Beschreibung                          |
|---------|---------------------------------------|
| `^`     | kann nur nach oben verlassen werden   |
| `v`     | kann nur nach unten verlassen werden  |
| `<`     | kann nur nach links verlassen werden  |
| `>`     | kann nur nach rechts verlassen werden |

#### Beispiel
Die Lösung für den folgenden Puzzleraum:
```
########
###>v  #
##o^ #x#
###    #
########
```
ist `r u r d d r r u`.

### Tipps

* Unten in diesem README gibt es einen "Feld hinzufügen"-Guide
* Die `enterField`-Funktion kann gleich aussehen wie von einem leeren Pfad
* Die `leaveField`-Funktion muss entsprechend verändert werden
* Wenn `leaveField` `true` zurückgibt, dann kann ein Feld verlassen werden
* Wenn `leaveField` `false` zurückgibt, dann kann ein Feld nicht verlassen werden
* Innerhalb dieser Funktionen kannst du auf die `name`-Variable (vom `BaseField`) zugreifen
  * Diese beinhaltet das Zeichen von dem Feld als `char`, also z.B. `'^'` für ein Feld, das nur nach oben verlassen werden kann
  * Mit `if(name =='^')` kann z.B. abgefragt werden, ob das Feld nur nach oben verlassen werden kann
* Der `direction`-Parameter ist ein Enum vom Typ `Direction`
  * Mit `if(direction == Direction.Up)` kann z.B. abgefragt werden, ob der Spieler nach oben gehen möchte

### Task B - Neuer Feldtyp - Eis [20%]

Ein neuer Eis-Feldtyp soll hinzugefügt (`FieldIce`).
Wenn ein Spieler ein Eisfeld betritt, dann "rutscht" er in die Laufrichtung weiter,
bis er an einer Wand ansteht (und entsprechend nicht mehr weiterrutschen kann)
oder er auf einem nicht-Eisfeld steht (dann bleibt er entsprechend dort stehen).

Es sollen mindestens 2 Tests hinzugefügt werden.

#### Zeichen
Das Eisfeld wird mit einem `@`-Zeichen gekennzeichnet.

#### Beispiel
Die Lösung für den folgenden Puzzleraum:
```
#######
#o@@ x#
#######
```
ist `rr`. Nach einer einmaligen Bewegung nach rechts rutscht der Spieler über die Eisfläche
und landet auf dem Feld neben dem Ziel. Nach einer weiteren Bewegung nach rechts ist das Ziel erreicht.

Die Lösung für folgenden Puzzleraum:
```
#######
#o@@@@#
#@@@@##
#@@@@@#
##@@@@#
#@@@@x#
#######
```
ist `drd`. Nach der ersten Bewegung nach unten rutscht der Spieler bis zur Wand.
Anschließend rutscht er mit der Bewegung nach rechts bis zur rechten Wand, und kann
mit einer weiteren Bewegung nach unten ins Ziel rutschen.

#### Tipps
* Unten in diesem README gibt es einen "Feld hinzufügen"-Guide
* Die `leaveField`-Funktion kann gleich aussehen wie von einem leeren Pfad
* Die `enterField`-Funktion muss entsprechend erweitert werden
* Mit `gameBoard.getField(...)` können andere Felder geladen werden
  * `gameBoard.getField(row - 1, col)` gibt das nächste Feld in Richtung `Up` zurück
  * `gameBoard.getField(row + 1, col)` gibt das nächste Feld in Richtung `Down` zurück
  * `gameBoard.getField(row, col - 1)` gibt das nächste Feld in Richtung `Left` zurück
  * `gameBoard.getField(row, col + 1)` gibt das nächste Feld in Richtung `Right` zurück
* Das Eis-Feld kann nun `nextField.enterField(direction)` aufrufen, damit der Spieler in die Richtung weiterrutscht
  * Wenn das erfolgreich war (die Funktion gibt `true` zurück), dann ist diese Funktion fertig.
  * Das `enterField` vom nächsten Feld kümmert sich um alles Weitere.
  * Wenn das nicht erfolgreich war (die Funktion gibt `false` zurück), dann ist das nächste Feld eine Wand.
  * Der Spieler muss entsprechend auf dem aktuellen Eisfeld stehen bleiben, und `gameBoard.getPlayer().walkStep()` muss aufgerufen werden.
* Die `enterField`-Funktion gibt immer `true` zurück - sie ist immer erfolgreich!

### Task C - Neuer Command: fastmove [10%]

Ein neuer Command namens `fastmove` soll hinzugefügt werden. Damit ist es möglich,
mehrere move-Commands hintereinander auszuführen. Dabei sollen die Richtungen
`up`, `down`, `left` und `right` mit `u`, `d`, `l`, `r` abgekürzt werden.
Der Command `fastmove u d l r` bedeutet also, dass der Spieler zuerst nach oben,
dann nach unten, dann nach links und dann nach rechts bewegt werden soll.

Wenn eine Bewegung nicht möglich ist, soll eine `PuzzleRoomInvalidMoveException` gethrowed werden,
und keine weiteren Bewegungen ausgeführt werden.

Wenn alle Bewegungen erfolgreich ausgeführt worden sind, dann soll anschließend
einmalig automatisch ein `show`-Command ausgeführt werden, so wie aktuell schon im
`move`-Command.

Es sollen mindestens 1 positiver und 1 negativer Test hinzugefügt werden.

#### Tipps
* Unten in diesem README gibt es einen "Command hinzufügen"-Guide
* Im Konstruktor ist kein `arguments.length`-Check erforderlich - aber dafür muss das `String[] arguments` gespeichert werden
* Die `execute`-Funktion ist sehr ähnlich wie vom `move`-Command
* Mit `for(String argument : arguments) {}` kannst du durch alle `arguments` durch-iterieren
* Wenn das argument `u` ist, dann soll `player.moveUp()` aufgerufen werden, usw.
* Wenn eine `move...`-Funktion `false` zurückgibt, dann soll `throw new PuzzleRoomInvalidMoveException();` aufgerufen werden, bevor das nächste argument behandelt wird
* Nach der `for`-Schleife - wenn alles erfolgreich war - soll einmal ein `show`-Command ausgeführt werden, so wie in `CommandMove`

## Step-by-Step Guides

### Neuen Feldtyp hinzufügen

Um einen neuen Feldtyp hinzuzufügen, folge diesen Schritten.
In diesem Guide wird ein neues `Teleporter`-Feld hinzugefügt - passe also alle Referenzen
darauf an dein neues Feld an.

1. Erstelle eine neue Datei im `fields`-Verzeichnis mit Namen `FieldTeleporter`, dass das `BaseField` extended
   1. Am einfachsten ist es, `FieldPath` zu kopieren, und entsprechend anzupassen
   2. Der Konstruktor kann immer gleich bleiben (nur der Name muss entsprechend geändert werden)
   3. Die `initialize`-Funktion muss in der Übung nie verwendet werden, außer im Startfeld
   4. Wenn ein Spieler sich bewegt, wird immer zuerst die Funktion `leaveField` vom alten Feld, und dann die Funktion `enterField` vom neuen Feld aufgerufen
   5. Wenn eine der Funktionen `false` zurückgibt, ist die Bewegung nicht möglich
   6. Die `enterField`-Funktion muss immer `gameBoard.getPlayer().walkStep()` aufrufen, um den Schritt zu zählen
   7. Die `enterField`-Funktion muss immer `setPlayerPositionToField()` aufrufen, um den Spieler aufs aktuelle Feld zu setzen
2. Jedes Field extended das `BaseField` und hat daher Zugriff auf einige Variablen.
   1. In der Datei `BaseField` sind diese Variablen mit `protected` gekennzeichnet und dokumentiert
3. Erweitere nun die `FieldFactory`, um beim Einlesen der Datei den richtigen Feldtypen für das richtige Zeichen zu erzeugen.
   1. Wenn mehrere Zeichen erlaubt sein sollen, können diese mit `,` im `case` getrennt werden
   2. z.B. `case '1', '2', '3', '4' -> new FieldTeleporter(gameBoard, fieldName, row, col);`
4. Teste das neu hinzugefügt Feld, indem du eine Testdatei `testTeleporter.maze` direkt im Hauptverzeichnis (neben `test.maze`) erstellst
5. Diese kann dann mit `load testTeleporter.maze` im Programm geladen werden

### Neuen Command hinzufügen

Um einen neuen Command hinzuzufügen, folge diesen Schritten.

1. Erstelle eine neue Datei im `commands`-Verzeichnis mit Namen `CommandFastmove`, dass das `Command`-Interface implementiert
   1. Am einfachsten ist es, `CommandMove` zu kopieren und entsprechend anzupassen
   2. Der Konstruktor wird aufgerufen, wenn der Command erstellt wird, und muss alle relevanten Werte speichern
   3. Der `arguments`-Parameter ist ein Array aus allen Eingaben nach dem Command-Namen
   4. Die `execute`-Funktion wird anschließend aufgerufen, um den Command auszuführen
2. Erweitere die `CommandFactory`, um den neuen Command-String zur `CommandFastmove`-Klasse zu mappen
3. Teste nun den neuen Command, indem du das Programm startest, eine Testdatei lädst (z.B. `load test.maze`), und den Command eingibst