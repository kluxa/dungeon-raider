# supertryhards-dungeon

## TODO
### Back-End
- Level completion checking
- Enemy movement (Strategist, Hound, Coward)
- Writing and reading mazes to and from files

### Front-End
Some of these will require changing the back-end,
NOTIFY everyone if you are about to modify the
back-end.

- Choosing sprites
- Main menu
- Level selection menu
- Level playing view
- Level complete menu
- Pause menu
- Game over menu
- Level editor
(Designing the level)
(Loading a saved level)
(Saving a custom level)

## Details
### Maze File Format

<Height> <Width>

Player <Starting Y> <Starting X>

<Entity-1> <Y1> <X1>, <Y2> <X2>...

<Entity-2> <Y1> <X1>, <Y2> <X2>...

...

Objectives <Objective 1> <Objective 2>...
\# Comment

If the entity is a door or key, a color must also be stated.
Path tiles are the default tile and do NOT need to be listed.
Possible objectives are Exit, Treasures, Enemies, and Switches

#### Example
`
7 7
Player 1 1
Wall 0 0, 0 1, 0 2, 0 3, 0 4, 0 5, 0 6, 1 0, 2 0, 3 0, 4 0, 5 0, 6 0, 1 6, 2 6, 3 6, 4 6, 5 6, 6 6, 6 1, 6 2, 6 3, 6 4, 6 5, 5 4, 4 4, 3 4
Boulder 3 1, 4 5
Switch 5 1, 5 5
Key Red 2 1
Door Red 3 5
Objectives Switches
`

### Main Menu
#### Relevant Keys: W (up), S (down), Enter (select)
Game Title

Play

Level Editor

Options (optional)

Quit

### Level Selection Menu
#### Relevant Keys: W (up), A (left), S (down), D (right), Enter (select),
####                Space (switch between viewing pre-built/custom levels),
####                Esc (return to main menu)
Contains a grid of squares containing level numbers or a small picture of the level.

### Level Playing View
#### Relevant Keys: W, A, S, D, P (pause), O (place bomb), I + Direction (fire arrow)
Player inventory is located at the top of the screen. One square-shaped slot for each
unique item. The number of duplicates of an item (such as treasures) is indicated by a
number at the bottom right corner of the square for that item. Indicators for active
effects (such as invincibility/flying) are located to the right of the inventory.

### Level Complete Menu
#### Relevant Keys: W, S, Enter
Next Level

Replay Level

Level Select

### Pause Menu
#### Relevant Keys: W, S, Enter, P (unpause)
Resume

Restart Level

Level Select

### Game Over Menu
#### Relevant Keys: W, S, Enter
Restart Level

Level Select

### Level Editor
#### Relevant Keys: W, A, S, D, Enter, Esc, Del

## Possible Extensions
Different Level Themes

More Interesting Enemies

Replays

Customisable Player

Scores

Story Mode

Music

Sound FX

Animations

