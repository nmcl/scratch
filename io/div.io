"Before redefining / operator we get 4 / 0 to be ..." println

(4 / 0) println

Number setSlot("baseDivision", Number getSlot("/"))

Number setSlot("/", method(denominator,
       
       if ((denominator == 0), return 0);

       return self baseDivision(denominator)))

"Now we have ..." println

(4 / -1) println
(4 / 0) println

"And our original / operator still gives ..." println

4 baseDivision(0) println