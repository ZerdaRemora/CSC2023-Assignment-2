import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Used for generating sequences of boxes for testing each algorithm
 * for the Truck Loading Problem.
 */
public class BoxGenerator
{
    public static List<Box> generate()
    {
        List<Box> boxes = new ArrayList<Box>();

        for (int i = 0; i < Algorithms.NUMBER_OF_BOXES; i++)
        {
            Random r = new Random();
            int width = r.nextInt(2000) + 1;    // Generates between 0 and 1999 so add 1 to give numbers between 1 and 2000.
            int height = r.nextInt(1000) + 1;   // Generates between 0 and 999 so add 1 to give numbers between 1 and 1000.
            boxes.add(new Box(width, height));
        }

//        boxes.add(new Box(300, 100));
//        boxes.add(new Box(500, 20));
//        boxes.add(new Box(50, 10));
//        boxes.add(new Box(1200, 500));
//        boxes.add(new Box(600, 300));
//        boxes.add(new Box(400, 200));
//        boxes.add(new Box(550, 200));
//        boxes.add(new Box(525, 100));
//        boxes.add(new Box(225, 700));
//        boxes.add(new Box(20, 70));
//        boxes.add(new Box(30, 70));
//        boxes.add(new Box(300, 700));
//        boxes.add(new Box(300, 700));
//        boxes.add(new Box(2000, 1000));

        return boxes;
    }
}
