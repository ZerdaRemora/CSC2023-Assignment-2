/**
 * Represents a box to be loaded into a {@link Truck Truck}.
 * Each Box stores a pointer to the Box directly on top of it,
 * much like a linked list.
 */
public class Box
{
    private int width;
    private int height;
    private Box aboveBox;

    public Box(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.aboveBox = null;   // By default, has no box above it.
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getPileHeight()
    {
        // If there is a box above this one, sum up the total height
        // of the pile of boxes.
        // This works recursively to add up the height of every box above this one in the pile.
        if (aboveBox != null)
        {
            return height + aboveBox.getPileHeight();
        }
        else
        {
            return height;
        }

    }

    public Box getAboveBox()
    {
        return aboveBox;
    }

    public int numberOfBoxesInPile()
    {
        if (aboveBox != null)
        {
            return 1 + aboveBox.numberOfBoxesInPile();
        }
        else
        {
            return 1;
        }
    }

    public boolean setAboveBox(Box box)
    {
        /* If the incoming box won't fit on this box, return from the method.
           Ideally would throw an exception here or return a boolean however as the user
           of the code is myself, I can ensure only boxes that fit will be passed to this method.
        */
        if (!canFitBox(box))
        {
            return false;
        }

        // If we have a box above this, check if that can fit the new box and if so, set it to be above that box.
        // This will work recursively upwards.
        if (this.aboveBox != null)
        {
            if (this.aboveBox.canFitBox(box))
            {
                this.aboveBox.setAboveBox(box);
                return true;
            }
        }
        // If there is nothing above this box and it will fit okay, set it to be above this box.
        this.aboveBox = box;
        return true;
    }

    private boolean canFitBox(Box box)
    {
        // Check if a box can fit on this box. It must have a width that is less than or equal
        // to the current width.
        if (box.getWidth() > this.getWidth())
            return false;

        // If the new box causes a pile to be created that is higher than the truck itself, return false.
        if (getPileHeight() + box.getHeight() > Truck.TRUCK_HEIGHT)
            return false;

        // Check if the above box can fit the new box, if so, return true.
        // This will work recursively upwards.
        if (aboveBox != null)
        {
            return aboveBox.canFitBox(box);
        }

        // If aboveBox is null AND the box is small enough, it will fit on top of this box.
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Box{" +
                "width=" + width +
                ", height=" + height);

        if (aboveBox != null)
            sb.append(", aboveBox=" + aboveBox.toString());

        sb.append("} pileHeight=" + getPileHeight());

        return sb.toString();
    }
}
