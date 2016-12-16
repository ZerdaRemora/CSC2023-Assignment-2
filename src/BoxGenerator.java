import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Used for generating sequences of boxes for testing each algorithm
 * for the Truck Loading Problem.
 * Populates the public static ArrayLists in {@link Algorithms} to ensure that
 * both ArrayLists have the exact same contents.
 */
public class BoxGenerator
{
    /**
     * Generates a set amount of Boxes with random width/height values and adds them
     * to Lists to be used by the two algorithms.
     */
    public static void generate()
    {
        List<Box> boxes = new ArrayList<Box>();

        for (int i = 0; i < Algorithms.NUMBER_OF_BOXES; i++)
        {
            Random r = new Random();
            int width = r.nextInt(2000) + 1;    // Generates between 0 and 1999 so add 1 to give numbers between 1 and 2000.
            int height = r.nextInt(1000) + 1;   // Generates between 0 and 999 so add 1 to give numbers between 1 and 1000.

            // Add the same box to both algorithm box lists.
            Algorithms.firstFitBoxes.add(new Box(width, height));
            Algorithms.nextFitBoxes.add(new Box(width, height));
        }

    }
}
