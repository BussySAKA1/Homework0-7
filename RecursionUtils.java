/**
 * This class contains utility methods for use in HW07.
 *
 * @author CS1331 TAs
 * @version 13.31
 */
public class RecursionUtils {
    /**
     * Merges two non-arrays which do not contain null elements and are sorted in ascending-order.
     *
     * @param arr1 the first array, sorted in ascending order
     * @param arr2 the second array, sorted in ascending order
     * @return an array containing all the elements of arr1 and arr2, sorted in ascending order
     * @throws NullPointerException if either arr1 or arr2 is null
     *                              or contains null elements
     */
    public static SushiRoll[] merge(SushiRoll[] arr1, SushiRoll[] arr2) {
        SushiRoll[] merged = new SushiRoll[arr1.length + arr2.length];

        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].compareTo(arr2[j]) <= 0) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            merged[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }

    /**
     * Shallow copies the specified range of the original array into a new array.
     *
     * The initial index of the range (start) must lie in the range [0, original.length].
     * The final index of the range (end) must lie in the range [start, original.length].
     *
     * The value at original[start] is placed into the initial element of the copy
     * (unless start == original.length or start == end). Values from subsequent elements
     * in the original array are placed into subsequent elements in the copy.
     *
     * The length of the returned array will be (end - start).
     *
     * @param original the array from which a range is to be copied
     * @param start    the initial index of the range to be copied, inclusive
     * @param end      the final index of the range to be copied, exclusive
     * @return a new array containing the specified range from the original array
     * @throws ArrayIndexOutOfBoundsException if start < 0, start > original.length, end < 0, or end > original.length
     * @throws IllegalArgumentException       if start > end
     * @throws NullPointerException           if original is null
     */
    public static SushiRoll[] copyOfRange(SushiRoll[] original, int start, int end) {
        if (original == null) {
            throw new NullPointerException("Array reference \"original\" is null.");
        } else if (start < 0 || start > original.length) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("Start index %d is out of bounds for array of length %d.", start, original.length));
        } else if (end > original.length) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("End index %d is out of bounds for array of length %d.", end, original.length));
        } else if (start > end) {
            throw new IllegalArgumentException(
                    String.format("Start index %d is greater than end index %d.", start, end));
        }

        SushiRoll[] copy = new SushiRoll[end - start];
        for (int i = start; i < end; ++i) {
            copy[i - start] = original[i];
        }
        return copy;
    }
}