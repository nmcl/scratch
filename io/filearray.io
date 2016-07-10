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

TwoDArray save := method(fileName, outputFile := File open(fileName); outputFile write(self serialized); outputFile close)

TwoDArray load := method(fileName, temp := doFile(fileName);
	       
	       if (temp type == "List",
	       	  x := temp size;
		  y := temp at(0) size;

		  newArray := TwoDArray dim(x, y);

	       	  for (i, 0, x-1, 1,
		      for (j, 0, y-1, 1,
		      	  newArray set(i, j, temp at(i) at(j))));

		return newArray);

		return nil)
		  
matrix := TwoDArray dim( 3, 3 );

matrix set( 0, 0, "A" );
matrix set( 1, 1, "B" );
matrix set( 2, 2, "C" );
 
matrix println

("0x0 : " .. matrix get( 0, 0 )) println;
("1x1 : " .. matrix get( 1, 1 )) println;
("2x2 : " .. matrix get( 2, 2 )) println;

filePath := "matrix.txt"

matrix save(filePath)

newMatrix := TwoDArray load(filePath)

newMatrix println

newMatrix type println
