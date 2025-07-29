# Screen

The HACK screen is `512 by 256 pixels`. The pixels on the screen are represented by bits in certain RAM addresses. In the HACK computer, each RAM address stores 16 bits, and therefore each RAM address represents `16 pixels`. Since there are 512 pixels in each row of the screen, there are **32 RAM addresses controlling each of their 16 pixels on each row**. There 256 rows in total and hence the number of RAM addresses allocated towards the HACK screen equal to `8192`. This was crucial in implementing `fill.asm` in `Projects/4/fill/` as it allowed us to either shade or un-shade certain pixels on the screen.

# Keyboard

Hack architecture has also assigned the keyboard a RAM address, which is as follows: RAM[24576]. Whenever any key is pressed on the keyboard, this specific RAM address contains a value corresponding to the pressed key. In fill.asm we used this to shade the screen when a key was pressed, and to un-shade the screen when no key was pressed.

# Challenges

A challenge of this project was figuring out how to loop in assembly to check that processing of each keyboard input was complete.