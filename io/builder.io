Builder := Object clone

Builder depth := 0

Builder whitespace := method (for (i, 0, depth, 1, " " print))

Builder forward := method (
	self depth = self depth +1;
	self whitespace;
	writeln("<", call message name, ">");

	call message arguments foreach(
	     arg,
	     content := self doMessage(arg)/Users/marklittle/Desktop/scratch/io/div.io;
	     if (content type == "Sequence", self whitespace; writeln(content)));

	self whitespace
	writeln("</", call message name, ">");
	self depth = self depth -1;
	)

Builder ul(li("IO"), li("Lua"), li("JavaScript"))