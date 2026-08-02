# Wang Tile Turing Machine Simulator

Final year dissertation project — Swansea University, BSc Computer Science (submitted May 2026).

## Overview
Wang tiles are unit squares with labelled edges that can only be placed adjacent to one another
if the touching edges match, with no rotation or reflection allowed. In 1966, Robert Berger showed
that the behaviour of a Turing machine can be encoded into a set of Wang tiles, proving along the
way that the general "can this tile set tile the plane?" question is undecidable.

This project builds on that link between geometric tiling and symbolic computation. It takes an
arbitrary deterministic Turing machine (defined by the user via a CSV transition table) and
generates a corresponding set of Wang tiles, then tiles a two-dimensional space-time grid where
each column is a computational step and each row is a tape cell. The machine's step-by-step
execution — including halting — emerges entirely from local tile-matching constraints, with no
explicit halting check anywhere in the tiling logic.

## Why this project
Both Wang tiles and Turing machines rely on simple local rules that produce complex global
behaviour, and both give rise to undecidable problems. I wanted to explore that connection
practically rather than just abstractly, building the simulator meant seeing undecidability
"in action" rather than only proving it on paper. There was also an educational motivation:
Turing machines are usually taught through transition tables, which can be hard to visualise, and
representing computation as a tiling gives a more spatial, intuitive way to see a machine evolve.

## How it works
1. **Define a Turing machine** via a CSV transition table (state, read symbol, write symbol,
   move direction, next state).
2. **Generate a tile set** — the system builds four distinct families of Wang tiles from the
   machine's transition function:
   - *Unchanging tiles* — for tape cells the head never visits
   - *Initial tiles* — encode the starting tape contents and head position
   - *Head-entering tiles* — capture the head arriving at a cell
   - *Head-in-left tiles* — capture a transition rule being applied at the head's current cell
3. **Tile the space-time grid**, one column (step) at a time, using only local edge-matching —
   vertical edges carry head-movement information, horizontal edges carry tape configuration.
4. **Render** the evolving tile plane as ASCII output in the console. Halting the machine
   corresponds to no valid tile being placeable — the tiling simply stops on its own.

The system also supports dynamic tape extension (the "infinite tape" is only as large as it needs
to be at any point) and session-level saving of machines and in-progress tilings.

## Tech stack
- Java (9 classes: `TextUI`, `MachineBuilder`, `Tiling`, `TuringMachine`, `State`, `Rule`,
  `TileSetGenerator`, `WangTile`, `TilePlane`)

## Running it
//TODO stil figuring out best running instructions

## Testing
Tested incrementally with three purpose-built Turing machines:
- A **binary-plus-one** machine (main test case — enough complexity to exercise multiple states,
  tape extension and natural halting, while still verifiable by hand)
- A **simple one-state** machine, for early sanity checks
- A **non-halting** machine, to confirm the step-limit safeguard works correctly on both the
  machine-runner and the tiling side independently

Correctness was verified by hand — tracing expected machine behaviour against the tile labels
in the ASCII output at each step — rather than automated assertions, since the output is
fundamentally a visual/logical structure rather than a single computed value.

## Key challenges / what I'd improve
- **Graphical UI** — the current interface is text/ASCII-based; a proper GUI with coloured tile
  rendering (matching the traditional colour-based Wang tile representation) would make the
  computation much easier to follow, especially for longer-running machines.
- **Persistent saves** — machines and tilings can currently only be saved within a single
  session; saving to disk would let tilings be resumed, compared side-by-side across different
  inputs, or shared as a small teaching library.

## Dissertation
Full write-up available on request / [link if I want to host the PDF somewhere].
