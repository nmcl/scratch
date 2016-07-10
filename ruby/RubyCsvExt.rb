class ActsAsCsv
  def each (&block)
    @result.each_index { | rowIndex | block.call(@result[rowIndex]) }
  end

  def read
    file = File.new(self.class.to_s.downcase + '.txt')
    @headers = file.gets.chomp.split(', ')

    file.each do | row |
      @result << CsvRow.new(@headers, row.chomp.split(', '))
    end
  end

  def headers
    @headers
  end

  def csv_contents
    @result
  end

  def initialize
    @result = []
    read
  end
end

class CsvRow
  def method_missing name, *args
    i = 0
    found = false

    # Go through the header to see if the row is present

    @header.each do |row|
      if row.strip.eql? name.to_s.strip
        found = true
        break
      else
        i = i +1
      end
    end

    if found
      puts @value[i]
    else
      puts "Could not find method #{name}"
    end
  end

  def initialize (header, value)
    @header = header
    @value = value
  end
end

class RubyCsv < ActsAsCsv
end

m = RubyCsv.new

#puts m.headers.inspect
#puts m.csv_contents.inspect

m.each {|row| puts row.one}
