import java.util.ArrayList;
import java.util.List;

/**
 * Contains static methods for running the Next Fit and First Fit Truck Loading Problems.
 */
public class Algorithms
{
    private static List<Truck> trucks;
    private static List<Box> boxes;

    public static void main(String[] args)
    {
        System.out.println("start");
        FFTLP();
        System.out.println("end");
    }

    public static void NFTLP()
    {

    }

    public static void FFTLP()
    {
        // Initialise list.
        trucks = new ArrayList<Truck>();
        // Create and add 50 trucks.
        for (int i = 0; i < 3; i++)
        {
            trucks.add(new Truck()); // Alternatively, could have as many trucks as boxes to ensure enough trucks.
        }

        // Initialise and generate list of boxes.
        boxes = new ArrayList<Box>();
        boxes.add(new Box(300, 100));
        boxes.add(new Box(500, 20));
        boxes.add(new Box(50, 10));
        boxes.add(new Box(1200, 500));
        boxes.add(new Box(600, 300));
        boxes.add(new Box(400, 200));
        boxes.add(new Box(550, 200));
        boxes.add(new Box(525, 100));
        boxes.add(new Box(225, 700));
        boxes.add(new Box(20, 70));
        boxes.add(new Box(30, 70));
        boxes.add(new Box(300, 700));
        boxes.add(new Box(300, 700));

        BoxGenerator.generate();

        // Possibly too much nesting going on here...
        // For each generated box...
        for (Box b : boxes)
        {
            System.out.println("\n\nCurrent box: width: " + b.getWidth() + "; height: " + b.getHeight());
            int truckCount = 0;
            // and for each truck in the list...
            truckloop:
            for (Truck t : trucks)
            {
                truckCount++;
                System.out.println("\nCurrent truck " + truckCount + ": remWidth: " + t.getRemainingWidth() + "; maxHeight: " + Truck.TRUCK_HEIGHT);

                // If the truck has no other boxes...
                if (t.getBoxes().size() == 0)
                {
                    System.out.println("No other boxes in truck " + truckCount);
                    // Add the box to the truck, break out of the inner loop and continue to the next box.
                    t.addBox(b);
                    break truckloop;
                }

                int pileCount = 0;
                // If the truck has other boxes (piles)...
                for (Box p : t.getBoxes())
                {
                    System.out.println("Check pile " + pileCount);
                    // Check if they can each fit the new box above them...
                    if (p.canFitBox(b))
                    {
                        System.out.println("Box fits in pile " + pileCount);
                        // and set it above the pile if so.
                        p.setAboveBox(b);
                        // Found a place for the box so break out of the truck for-each and continue to next box.
                        break truckloop;
                    }
                    pileCount++;
                } // If there is no space in any of the piles, check if we can make a new pile.

                // Check if there is enough remaining room to create a new pile with the box.
                if (t.canFit(b))
                {
                    // If enough room, add the box as a new pile in the truck and continue to the next box.
                    t.addBox(b);
                    break truckloop;
                }

                // If all the above have failed, the box cannot fit in this truck.
                System.out.println("Can't fit in truck " + truckCount);
            } // End of Truck loop
        } // End of Box loop

        for (Truck t : trucks)
        {
            System.out.println(t.toString());
            System.out.println("--------------------------------");
        }

    }
}
