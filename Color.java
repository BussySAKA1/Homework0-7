/**
 * This enum represents the possible colors of the plate a sushi roll can be
 * served on: RED, GREEN, and BLUE. In addition to the textual enum name,
 * each color has a price associated with it.
 *
 * @author CS1331 TAs
 * @version 13.31
 */
public enum Color {
    /**
     * The singleton instance for the red plate color.
     * Sushi rolls served on a red plate cost $2.50.
     */
    RED(2.5),

    /**
     * The singleton instance for the green plate color.
     * Sushi rolls served on a green plate cost $3.00.
     */
    GREEN(3.0),

    /**
     * The singleton instance for the blue plate color.
     * Sushi rolls served on a blue plate cost $3.50.
     */
    BLUE(3.5);


    /**
     * The price of the sushi roll served on a plate of this color.
     */
    private final double price;

    Color(double price) {
        this.price = price;
    }


    /**
     * Returns the price of this plate.
     *
     * @return the price of this plate
     */
    public double getPrice() {
        return price;
    }
}