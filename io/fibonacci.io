fib := method(param,

    if (((param == 1) or (param == 2) or (param < 1)), return 1);

    firstNumber := 1;
    secondNumber := 1;

    sum := 0;

    for (i, 3, param, 1, sum = firstNumber + secondNumber; firstNumber = secondNumber; secondNumber = sum);

    return sum)

"Trying for-loop version"

fib(0) println
fib(1) println
fib(4) println
fib(5) println
fib(6) println
fib(7) println
fib(8) println

summation := method(first, second, maxDepth, currentDepth,
	  
	  if ((currentDepth == maxDepth), return(first+second));

	  return summation(second, (first+second), maxDepth, currentDepth+1))

fibng := method(param,
      
      if (((param == 1) or (param == 2) or (param < 1)), return 1);

      return summation(1, 1, param, 3))

"" println
"Trying recursive version" println

fibng(0) println
fibng(1) println
fibng(4) println
fibng(5) println
fibng(6) println
fibng(7) println
fibng(8) println