Convert DVDHunter html output to csv for input to a spreadsheet via csv import.

Note, this is written to convert my data and isn't meant to be a generic
converter. I also had to manually edit some of the output files to re-order
fields as they weren't always consistently written out by the original program! Another
result of the inconsistency is that sometimes data is ignored which could have
been converted. However, it's easier to just add that back by hand than have so many
different if/then/else clauses for one-off situations!

It worked for most items but there were a few where the output was null. Manual checking
added the data.
