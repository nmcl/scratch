i = rand(10)
guess = -1

puts "I am thinking of a number between 0 and 9 ..."

while guess != i

puts
puts "Input your guess."

theGuess = gets

guess = theGuess.to_i

end

puts "Well done!"