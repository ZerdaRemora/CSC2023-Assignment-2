import java.util.ArrayList;
import java.util.List;

/**
 * Represents a truck in the Truck Loading Problem.
 * Boxes lying on the Truck bed are stored in the Box ArrayList in this
 * object, whereas a higher box is stored in the {@link Box Box} object that is directly underneath
 * it.
 */
public class Truck
{
    public static final int TRUCK_WIDTH = 2000;
    public static final int TRUCK_HEIGHT = 1000;
    public static final int MAX_NUMBER_OF_BOXES = 10;
    private int remainingWidth; // Represents the amount of width space on the truck bed.
    private List<Box> boxes;    // Box List to hold all bottom-level boxes.

    public Truck()
    {
        // Initialise Truck so that it's remaining space is it's default capacity.
        this.remainingWidth = TRUCK_WIDTH;
        boxes = new ArrayList<Box>();
    }

    public int getRemainingWidth()
    {
        return remainingWidth;
    }

    public List<Box> getBoxes()
    {
        return boxes;
    }

    /**
     * Checks if a {@link Box} can be added to the bottom of the Truck, and if so, adds it.
     * @param box The Box to add to the bottom of the Truck.
     * @return True if Box was added to the bottom of the Truck successfully, or False if the Box cannot fit in the Truck.
     */
    public boolean addBox(Box box)
    {
        // If box is too big for the truck, return false.
        if (!canFit(box))
            return false;

        // If box fits at the bottom of the truck fine,
        // update remaining width using dimensions of new box,
        // add the box to the truck, and return true.
        this.remainingWidth -= box.getWidth();
        this.boxes.add(box);
        return true;
    }

    /**
     * Calculates the total number of Boxes in the Truck, including Boxes on top of other Boxes.
     * @return The number of Boxes in the Truck.
     */
    public int getBoxCount()
    {
        int count = 0;
        for (Box b : boxes)
        {
            count += b.numberOfBoxesInPile();
        }

        return count;
    }

    /**
     * Checks if a Box will fit in the Truck. Only checks if the Box's dimensions
     * are correct. To find an appropriate fit on a pile or as a separate pile, boxes in
     * the truck should be checked separately using {@link Box#canFitBox(Box)}.
     * @param box The Box to check.
     * @return True if the Box is small enough to fit in the truck, false if otherwise.
     */
    private boolean canFit(Box box)
    {
        // Box must fit within truck's remaining width and maximum height.
        if (box.getWidth() > remainingWidth)
            return false;
        if (box.getHeight() > TRUCK_HEIGHT)
            return false;

        // If box fits, return true. Separate piles of boxes should be checked manually.
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Truck{" +
                "remainingWidth=" + remainingWidth +
                ", maxHeight=" + TRUCK_HEIGHT +
                ", boxes={");

        for (Box b : boxes)
        {
            sb.append("\n\t" + b.toString());
        }

        sb.append("\n\t}\n} ");
        sb.append("Total number of boxes: " + getBoxCount() + "\n");
        return sb.toString();
    }
}