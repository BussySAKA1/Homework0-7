/**
 * The Restaurant class represents a collection of methods for manipulating and sorting
 * arrays of SushiRoll objects.
 * @author Mickey Paulus
 * @version 11.12
 */
public class Restaurant {
    /**
     * Sorts an array of SushiRoll objects using the merge sort algorithm.
     *
     * @param inputArray The array of SushiRoll objects to be sorted.
     * @return A sorted array of SushiRoll objects.
     */
    public static SushiRoll[] mergeSortRolls(SushiRoll[] inputArray) {
        int arrayLength = inputArray.length;

        if (arrayLength < 2) {
            return inputArray;
        }

        int midIndex = arrayLength / 2;

        SushiRoll[] leftHalf = RecursionUtils.copyOfRange(inputArray, 0, midIndex);
        SushiRoll[] rightHalf = RecursionUtils.copyOfRange(inputArray, midIndex, arrayLength);

        leftHalf = mergeSortRolls(leftHalf);
        rightHalf = mergeSortRolls(rightHalf);
        return RecursionUtils.merge(leftHalf, rightHalf);
    }

    /**
     * Merges multiple arrays of SushiRoll objects into a single sorted array.
     *
     * @param orders An array of arrays of SushiRoll objects to be merged.
     * @return A sorted array of SushiRoll objects.
     */
    public static SushiRoll[] mergeOrders(SushiRoll[][] orders) {
        if (orders.length == 0) {
            return new SushiRoll[0];
        }
        return mergeOrdersHelper(orders, 0);
    }

    /**
     * Filters an array of SushiRoll objects based on the desired color.
     *
     * @param input        The array of SushiRoll objects to be filtered.
     * @param desiredColor The desired color to filter by.
     * @return An array of SushiRoll objects with the specified color.
     */
    public static SushiRoll[] platesOfColor(SushiRoll[] input, Color desiredColor) {
        int length = input.length;
        if (length == 0) {
            return new SushiRoll[0];
        } else if (input[0].getColor() == desiredColor) {
            return RecursionUtils.merge(new SushiRoll[]{input[0]},
                platesOfColor(RecursionUtils.copyOfRange(input, 1, length), desiredColor));
        } else {
            return platesOfColor(RecursionUtils.copyOfRange(input, 1, length), desiredColor);
        }
    }

    /**
     * Calculates the total price of an array of SushiRoll objects.
     *
     * @param input The array of SushiRoll objects to calculate the total price.
     * @return The total price of the SushiRoll objects in the array.
     */
    public static double totalPrice(SushiRoll[] input) {
        int length = input.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return input[0].getColor().getPrice();
        } else {
            return input[length - 1].getColor().getPrice()
                + totalPrice(RecursionUtils.copyOfRange(input, 0, input.length - 1));
        }
    }

    /**
     * Reverses the order of elements in an array of SushiRoll objects.
     *
     * @param input The array of SushiRoll objects to be reversed.
     */
    public static void flip(SushiRoll[] input) {
        int length = input.length;
        flipHelper(input, 0, length - 1);
    }

    // Private helper method to merge orders recursively
    private static SushiRoll[] mergeOrdersHelper(SushiRoll[][] orders, int index) {
        if (orders.length == 1) {
            return orders[0];
        }
        if (index == orders.length) {
            return orders[index - 1];
        }
        return RecursionUtils.merge(orders[index], mergeOrdersHelper(orders, index + 1));
    }

    // Private helper method to flip elements in an array recursively
    private static void flipHelper(SushiRoll[] input, int index1, int index2) {
        if (index1 < index2) {
            SushiRoll temp = input[index1];
            input[index1] = input[index2];
            input[index2] = temp;

            flipHelper(input, index1 + 1, index2 - 1);
        }
    }
}
