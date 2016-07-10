List myAverage := method( theAverage := 0;

     flattenedArray := self flatten;
     total := 0;

     flattenedArray foreach (index, value,

     	     if ((value type != "Number"),
	     	Exception raise(
			  "NaN",
				("A non-numeric value [" .. value .. "] was encountered during the " .. (call message() name()) .. " operation.")
				 ));

	     total = total + value);

     if (flattenedArray size == 0, return 0);

     return total / flattenedArray size)	

nestedList := list( list( 1, 2, 3 ), list( 10, 20, 30 ), list( 100, 200, 300 ) );

"The average of " print; nestedList print; "is ..." println

nestedList myAverage println

nestedList := list( list( "foobar", 2, 3 ), list( 10, 20, 30 ), list( 100, 200, 300 ) );

nestedList myAverage println
