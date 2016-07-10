List flatSum := method( total := 0;

     flattenedArray := self flatten;

     flattenedArray foreach (index, value,
             if ((value type == "Number"),
		 total = total + value));
  
     return total)

nestedList := list( list( 1, 2, 3 ), list( 10, 20, 30 ), list( 100, 200, 300 ) );

nestedList flatSum println
     