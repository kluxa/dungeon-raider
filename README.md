# supertryhards-dungeon

## TODO
### Back-End
- [x] Level completion checking (KEVIN)
- [ ] Enemy movement (Strategist, Hound, Coward) (JAMES)
- [ ] Writing and reading mazes to and from files (MATTHEW)

### Front-End
Some of these will require changing the back-end,
NOTIFY everyone if you are about to modify the
back-end.

- [x] Choosing sprites
- [x] Main menu (KEVIN)
- [x] Level selection menu
- [x] Level playing view
- [x] Level complete menu
- [x] Pause menu
- [x] Game over menu
- [x] Level editor
  - [x] Designing the level
  - [ ] Loading a saved level
  - [ ] Saving a custom level

## Details
### Maze File Format
See examples

### Main Menu
#### Relevant Keys: W (up), S (down), Enter (select)
- Game Title
- Play
- Level Editor
- Options (optional)
- Quit

### Level Selection Menu
#### Relevant Keys: Up, Down, Left, Right, Enter (select), Esc (return to main menu)
Contains a grid of squares containing level numbers or a small picture of the level.

### Level Playing View
#### Relevant Keys: Up, Down, Left, Right, Esc (pause), D (drop bomb), A + Direction (fire arrow)
Player inventory is located at the top of the screen. One square-shaped slot for each
unique item. The number of duplicates of an item (such as treasures) is indicated by a
number at the bottom right corner of the square for that item. Indicators for active
effects (such as invincibility/flying) are located to the right of the inventory.

### Level Complete Menu
#### Relevant Keys: W, S, Enter
- Next Level
- Replay Level
- Level Select

### Pause Menu
#### Relevant Keys: W, S, Enter, P (unpause)
- Resume
- Restart Level
- Level Select

### Game Over Menu
#### Relevant Keys: W, S, Enter
- Restart Level
- Level Select

### Level Editor
#### Relevant Keys: W, A, S, D, Space, Esc, Del

## Possible Extensions
- Key Rebinding
- Different Level Themes
- New Enemies
- Restricted Vision and Torch
- Replays
- Customisable Player
- Scores
- Music
- Sound FX
- Animations (TOO HARD)

