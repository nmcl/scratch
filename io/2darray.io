TwoDArray := List clone

TwoDArray dim := method(x, y,

	  if ((self proto type == "List"), return TwoDArray clone dim(x, y));

	  self setSize(x);

	  for( i, 0, (x - 1), 1,

	       self atPut(i, (list() setSize( y ))));

	return self)

TwoDArray set := method(x, y, value,

	  if (at(x) == nil, atPut(x, List clone))

	  self at(x) atPut(y, value);

	  return self)

TwoDArray get := method(x, y,

	  return self at(x) at(y))

matrix := TwoDArray dim( 3, 3 );

matrix set( 0, 0, "A" );
matrix set( 1, 1, "B" );
matrix set( 2, 2, "C" );
 
matrix println;
 
("0x0 : " .. matrix get( 0, 0 )) println;
("1x1 : " .. matrix get( 1, 1 )) println;
("2x2 : " .. matrix get( 2, 2 )) println;



     