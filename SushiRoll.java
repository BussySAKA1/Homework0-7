/**
 * This class represents a sushi roll.
 * A sushi roll has a name and is served on a colored plate, where
 * the color of the plate indicates the price of the sushi roll.
 *
 * @author CS1331 TAs
 * @version 13.31
 */

public class SushiRoll implements Comparable<SushiRoll> {
    /**
     * The name of the sushi roll.
     */
    private final String name;

    /**
     * The color of the sushi roll's plate.
     */
    private final Color color;


    /**
     * Constructs a new SushiRoll instance with a name and color.
     *
     * @param name  the name of this sushi roll, must be non-null
     * @param color the color of this sushi roll's plate, must be non-null
     * @throws IllegalArgumentException if either name or color is null
     */
    public SushiRoll(String name, Color color) {
        if (name == null || color == null) {
            throw new IllegalArgumentException("name and color must be non-null!");
        }

        this.name = name;
        this.color = color;
    }

    /**
     * Creates a new SushiRoll instance with a name and a plate color of Color.GREEN.
     *
     * @param name the name of this sushi roll, must be non-null
     */
    public SushiRoll(String name) {
        this(name, Color.GREEN);
    }


    /**
     * Getter for color.
     *
     * @return the color of this sushi roll's plate
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns a String representation of this sushi roll in the format "{name} {color}".
     */
    @Override
    public String toString() {
        return String.format("%s %s", name, color.name());
    }

    /**
     * Compares this sushi roll with another sushi roll for order by comparing
     * the name of the two sushi rolls lexicographically. This method returns
     * an integer equivalent to the return value of name.compareTo(other.name).
     *
     * Note: Since this class does not override Object's implementation of equals,
     * this class has a natural ordering that is inconsistent with equals.
     *
     * @param other the other sushi roll to be compared
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object
     * @throws NullPointerException if the specified object is null
     */
    @Override
    public int compareTo(SushiRoll other) {
        return name.compareTo(other.name);
    }
}