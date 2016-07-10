-module(tut7a).
-export([format_temps/1]).

format_temps(List_of_cities) ->
			     Converted_List = convert_list_to_c(List_of_cities),
			     print_temp(Converted_List).

convert_list_to_c([{Name, {f, F}} | Rest]) ->
			  Converted_City = {Name, {c, (F -32)* 5 / 9}},
			  [Converted_City | convert_list_to_c(Rest)];

convert_list_to_c([City | Rest]) ->
			[City | convert_list_to_c(Rest)];

convert_list_to_c([]) -> [].

print_temp([{Name, {c, Temp}} | Rest]) ->
		   io:format("~-15w ~w c~n", [Name, Temp]),
		   print_temp(Rest);
print_temp([]) ->
	       ok.