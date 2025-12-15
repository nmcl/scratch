import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CrabSubmarineAligner {

    public static void main(String[] args) {
        String filename = "crab_positions.txt"; // Change this to your actual file path
        int[] positions = readPositionsFromFile(filename);

        if (positions != null && positions.length > 0) {
            int optimalPosition = findOptimalPosition(positions);
            int totalFuel = calculateTotalFuel(positions, optimalPosition);
            System.out.println("Optimal position: " + optimalPosition);
            System.out.println("Total fuel required: " + totalFuel);
        } else {
            System.out.println("No positions found or the file could not be read.");
        }
    }

    private static int[] readPositionsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                return Arrays.stream(parts)
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int findOptimalPosition(int[] positions) {
        int minFuel = Integer.MAX_VALUE;
        int optimalPosition = -1;

        // Check fuel cost for each position between the minimum and maximum crab
        // positions
        for (int target = Arrays.stream(positions).min().getAsInt(); target <= Arrays.stream(positions).max()
                .getAsInt(); target++) {
            int fuelCost = calculateTotalFuel(positions, target);
            if (fuelCost < minFuel) {
                minFuel = fuelCost;
                optimalPosition = target;
            }
        }
        return optimalPosition;
    }

    private static int calculateTotalFuel(int[] positions, int target) {
        return Arrays.stream(positions)
                .map(pos -> Math.abs(pos - target))
                .sum();
    }
}
