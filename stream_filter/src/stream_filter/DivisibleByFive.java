package stream_filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivisibleByFive {
    public static void main(String[] args) {
        
        // Create a list of numbers (ArrayList) using Arrays.asList()
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 4, 5, 20, 30, 6));

        // Use Java Streams to filter numbers divisible by 5 and print them
        numbers.stream() // Convert the list into a stream (like a pipeline for data)
               .filter(n -> n % 5 == 0) // Keep only numbers divisible by 5
               .forEach(System.out::println); // Print each number that passed the filter
    }
}
