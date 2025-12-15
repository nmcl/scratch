import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CrabSubmarineAlignment {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java CrabSubmarineAlignment <input-file>");
            System.exit(1);
        }

        // Read file and parse positions
        String content = Files.readString(Path.of(args[0])).trim();
        int[] positions = Arrays.stream(content.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        // Sort positions to find median
        Arrays.sort(positions);

        // Median minimizes sum of absolute distances
        int median = positions[positions.length / 2];

        // Calculate total fuel cost
        int totalFuel = 0;
        for (int position : positions) {
            totalFuel += Math.abs(position - median);
        }

        // Output result
        System.out.println("Optimal alignment position: " + median);
        System.out.println("Total fuel required: " + totalFuel);
    }
}