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

TwoDArray copy := method(

	  x := size;

	  if (x == 0, return nil);

	  y := at(0) size;

	  newArray := TwoDArray dim(y, x);

	  for (i, 0, (x-1), 1,
	      for (j, 0, (y-1), 1,
	      	newArray set(x-1-i, y-1-j, self get(x-1-i, y-1-j))));

	  return newArray)

TwoDArray transpose := method(

	  x := size;

	  if (x == 0, return nil);

	  y := at(0) size;

	  newArray := TwoDArray dim(y, x);

	  for (i, 0, (y-1), 1,
	      for (j, 0, (x-1), 1,
	      	newArray set(i, j, self get(j, i))));

	  return newArray)

matrix := TwoDArray dim( 3, 3 );

matrix set( 0, 2, "TR" );
matrix set( 2, 0, "BL" );
 
matrix println;
 
reflectedMatrix := matrix transpose();
 
reflectedMatrix println();
 
(matrix get( 0, 2 ) == reflectedMatrix get( 2, 0 )) println();
(matrix get( 2, 0 ) == reflectedMatrix get( 0, 2 )) println();
 
(matrix get( 2, 0 ) != reflectedMatrix get( 2, 0 )) println();	  

     