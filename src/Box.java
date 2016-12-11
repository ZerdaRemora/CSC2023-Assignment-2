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

    public Box getAboveBox()
    {
        return aboveBox;
    }

    public void setAboveBox(Box aboveBox)
    {
        // Validate before setting.
        // Also, bear in mind that the above box cannot be changed
        // so maybe have a boolean flag that checks if it has been changed previously.
        this.aboveBox = aboveBox;
    }

    public boolean canFitBox(Box box)
    {
        // Check if a box can fit on this box.
        // i.e. length/width less/equal to this
        // and no other boxes on this box.
        return false;
    }
}
