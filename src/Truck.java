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
        if (!canFit(box))
            return;

//        for (Box b : boxes)
//        {
//            // If the first pile we come across can fit the new box, add it above that and return from the method.
//            if (b.canFitBox(box))
//            {
//                b.setAboveBox(box);
//
//                return;
//            }
//        }

        // Update remaining height/width using dimensions of new box.
        this.remainingHeight -= box.getHeight();
        this.remainingWidth -= box.getWidth();

        this.boxes.add(box);
    }

    public void addBoxNFTLP(Box box)
    {
        this.boxes.add(box);
    }

    /**
     * Checks if a box will fit in the Truck. Only checks if the box's dimensions
     * are correct. To find an appropriate fit on a pile or as a separate pile, boxes in
     * the truck should be checked separately using {@link Box#canFitBox(Box)}.
     * @param box The box to check fits.
     * @return True if the box is small enough to fit in the truck, false if otherwise.
     */
    public boolean canFit(Box box)
    {
        // Box must fit in remaining width and height.
        if (box.getWidth() > remainingWidth)
            return false;
        if (box.getHeight() > remainingHeight)
            return false;
        // If there are no boxes/piles yet, then there is space.
//        if (boxes.size() == 0)
//            return true;

        // If box fits, return true. Separate piles of boxes should be checked manually.
        return true;
    }

    public boolean canFitNFTLP(Box box)
    {
        // Box must fit in remaining width and height.
        if (box.getWidth() > remainingWidth)
            return false;
        if (box.getHeight() > remainingHeight)
            return false;
        // If there are no boxes/piles yet, then there is space.
        if (boxes.size() == 0)
            return true;

        // Check last box/pile in the list to check if the new box fits (this is Next-Fit).
        if (boxes.get(boxes.size() - 1).canFitBox(box))
            return true;

        // Otherwise, return false as no fits.
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Truck{" +
                "remainingWidth=" + remainingWidth +
                ", remainingHeight=" + remainingHeight +
                ", boxes={");

        for (Box b : boxes)
        {
            sb.append("\n\t" + b.toString());
        }

        sb.append("\n\t}\n}");
        return sb.toString();
    }
}