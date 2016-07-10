puts "Enter file name"
fileName = gets
file = File.new(fileName.strip, "r")

puts "Enter string to search for"
searchString = gets

lineNumber = 1

while (line = file.gets)
  if line.include? searchString.strip
    puts "line #{lineNumber}: #{line}"
  end

  lineNumber = lineNumber +1
end

file.close
