randomNumber := (Random value(99) +1) floor

lastGuess := 0

for (i, 0, 10, 1,

    input := File standardInput readLine asNumber;

    if (input == randomNumber, "You got it right!" println; return);

    if (lastGuess > 0, 
        if ((lastGuess < randomNumber) and (input > lastGuess), "hotter" println);

	    if ((lastGuess < randomNumber) and (input < lastGuess), "colder" println);

	        if ((lastGuess > randomNumber) and (input < lastGuess), "hotter" println);

		    if ((lastGuess > randomNumber) and (input > lastGuess), "colder" println))

    lastGuess := input);

    "You didn't guess right. The answer was " print; randomNumber println
