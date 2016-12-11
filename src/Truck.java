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
    private static final int TRUCK_WIDTH = 2000;
    private static final int TRUCK_HEIGHT = 1000;
    private int remainingWidth;
    private int remainingHeight;
    private List<Box> boxes;    // Box List to hold all bottom-level boxes.

    public Truck()
    {
        // Initialise Truck so that it's remaining space is it's default capacity.
        this.remainingWidth = TRUCK_WIDTH;
        this.remainingHeight = TRUCK_HEIGHT;
        boxes = new ArrayList<Box>();
    }

    public int getRemainingWidth()
    {
        return remainingWidth;
    }

    public int getRemainingHeight()
    {
        return remainingHeight;
    }

    public List<Box> getBoxes()
    {
        return boxes;
    }

    public void addBox(Box box)
    {
        this.boxes.add(box);
    }

    public boolean canFit(Box box)
    {
        // Check if passed box can fit in the truck.
        // Check last accessed pile in NFTLP or every pile
        // in FFTLP.
        return false;
    }
}