import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains static methods for running the Next Fit and First Fit Truck Loading Problems.
 */
public class Algorithms
{
    public static final int NUMBER_OF_BOXES = 50;

    private static List<Truck> trucks;
    private static List<Box> generatedBoxes;
    public static List<Box> nextFitBoxes = new ArrayList<Box>();
    public static List<Box> firstFitBoxes = new ArrayList<Box>();

    public static void main(String[] args) throws InterruptedException
    {
        // Generate test data for both algorithms.
        BoxGenerator.generate();

        System.out.println("Next Fit Truck Loading Problem Start");

        long startTime = System.currentTimeMillis();
        List<Truck> outputTrucks = NFTLP();
        long endTime = System.currentTimeMillis();

        int usedTrucks = 0;
        for (Truck t : outputTrucks)
        {
            // Check each truck to see if it has any boxes, if it does, that Truck has been used.
            if (t.getBoxes().size() != 0)
                usedTrucks++;
        }

        System.out.println("Next Fit Truck Loading Problem End");
        System.out.println("Took " + (endTime - startTime) + " milliseconds");
        System.out.println("Used " + usedTrucks + " trucks");

        System.out.println("\n---------------------\n");


        System.out.println("First Fit Truck Loading Problem Start");

        startTime = System.currentTimeMillis();
        outputTrucks = FFTLP();
        endTime = System.currentTimeMillis();

        usedTrucks = 0;
        for (Truck t : outputTrucks)
        {
            // Check each truck to see if it has any boxes, if it does, that Truck has been used.
            if (t.getBoxes().size() != 0)
                usedTrucks++;
        }

        System.out.println("First Fit Truck Loading Problem End");
        System.out.println("Took " + (endTime - startTime) + " milliseconds");
        System.out.println("Used " + usedTrucks + " trucks");
    }

    public static List<Truck> NFTLP()
    {
        // Initialise truck list.
        trucks = new ArrayList<Truck>();
        // Create and add 50 trucks.
        for (int i = 0; i < NUMBER_OF_BOXES; i++)
        {
            trucks.add(new Truck()); // Alternatively, could have as many trucks as boxes to ensure enough trucks.
        }

        int truckCount = 0; // To keep track of current/last used truck.
        int pileCount = 0;  // To keep track of current/last used pile in current/last used truck.

        for (Box b : nextFitBoxes)
        {
            System.out.println("\n\nCurrent box: width: " + b.getWidth() + "; height: " + b.getHeight());
            Truck t = trucks.get(truckCount);
            System.out.println("\nCurrent truck " + truckCount + ": remWidth: " + t.getRemainingWidth() + "; maxHeight: " + Truck.TRUCK_HEIGHT);

            // Check if truck is empty...
            if (t.getBoxes().size() == 0)
            {
                System.out.println("No other boxes in truck " + truckCount);
                // Add the box to the truck and continue to the next box.
                if (!t.addBox(b))
                    // If box is too big for an empty truck, it's going to be too big for every truck so just skip it.
                    // Should never see this as generated boxes will always be smaller than the size of the truck.
                    System.out.println("Box couldn't be added as dimensions too big.");

                continue;
            }

            // Try and add the box to last accessed pile...
            if (t.getBoxes().get(pileCount).setAboveBox(b))
            {
                System.out.println("Box fits in pile " + pileCount);
                continue;
            }

            // Can't fit in last accessed pile, so check if we can create a new pile...
            if (t.addBox(b))
            {
                pileCount++;
                System.out.println("New pile created: " + pileCount);
                continue;   // ...If we can, increment the pileCounter to this new pile and continue to next box.
            }

            // At this stage, we need to add the box to the next truck along.
            // It is a safe assumption that the next truck will be empty so we can just add to it.
            truckCount++;
            pileCount = 0;
            if (truckCount < trucks.size()) // If we generate as many trucks as there are boxes, this shouldn't be an issue.
            {
                if (trucks.get(truckCount).addBox(b)) // (Try) and add the box to the new truck.
                {
                    System.out.println("Box added to new Truck " + truckCount);
                }
                else
                {
                    System.out.println("Box couldn't be added as dimensions too big.");
                }

            }
        }   // End of Box loop

        // Display each truck and it's contents.
//        for (Truck t : trucks)
//        {
//            System.out.println("\n--------------------------------");
//            System.out.println(t.toString());
//            System.out.println("--------------------------------");
//        }

        return trucks;
    }

    public static List<Truck> FFTLP()
    {
        // Initialise truck list.
        trucks = new ArrayList<Truck>();
        // Create and add 50 trucks.
        for (int i = 0; i < NUMBER_OF_BOXES; i++)
        {
            trucks.add(new Truck()); // Alternatively, could have as many trucks as boxes to ensure enough trucks.
        }

        // Possibly too much nesting going on here...
        // For each generated box...
        for (Box b : firstFitBoxes)
        {
            System.out.println("\n\nCurrent box: width: " + b.getWidth() + "; height: " + b.getHeight());
            int truckCount = 0;
            // and for each truck in the list...
            truckloop:  // Label to assist with breaking out of this loop while in a nested-for.
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
                    if (p.setAboveBox(b))
                    {
                        // If box can fit in the pile, box is added to the pile.
                        System.out.println("Box fits in pile " + pileCount);
                        // Found a place for the box so break out of the truck for-each and continue to next box.
                        break truckloop;
                    }
                    pileCount++;
                } // If there is no space in any of the piles, check if we can make a new pile.

                // Check if there is enough remaining room to create a new pile with the box.
                if (t.addBox(b))
                {
                    System.out.println("New pile created: " + pileCount);
                    // If enough room, box will be added as a new pile in the truck and continue to the next box.
                    break truckloop;
                }

                // If all the above have failed, the box cannot fit in this truck.
                System.out.println("Can't fit in truck " + truckCount);
            } // End of Truck loop
        } // End of Box loop

        // Display each truck and it's contents.
//        for (Truck t : trucks)
//        {
//            System.out.println("\n--------------------------------");
//            System.out.println(t.toString());
//            System.out.println("--------------------------------");
//        }

        return trucks;
    }
}
