import java.io.*;
import java.util.*;

public class MeteoriteAnalysis {
    public static void main(String[] args) {
        String filePath = "meteorites.csv";
        List<Double> masses = new ArrayList<>();
        Map<Integer, Integer> yearCounts = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    if (values[5].equals("Fell")) { 
                        if (!values[4].isEmpty()) {
                            masses.add(Double.parseDouble(values[4]));
                        }
                        if (!values[6].isEmpty()) {
                            int year = (int) Double.parseDouble(values[6]);
                            yearCounts.put(year, yearCounts.getOrDefault(year, 0) + 1);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Skipping line due to error: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
        
       
        double meanMass = masses.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        String meanMassString = String.format("%.2f", meanMass);
        
       
        int maxYear = Collections.max(yearCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
        int minYear = Collections.min(yearCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
        
        
        System.out.println("Mean mass of meteorites: " + meanMassString + " g");
        System.out.println("Year with most meteorite falls: " + maxYear);
        System.out.println("Year with least meteorite falls: " + minYear);
    }
}
