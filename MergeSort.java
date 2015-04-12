import java.util.Scanner;
import java.util.Random;

/* Merge Sort : Algorithm invented by Jon von Neumannn in 1945 */
public class MergeSort {

	/* interactive main class */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int maxValue = 0, arraySize = 0;
		String response = "";

		/* loop de loop */
		while (true) {

			System.out.println("How many numbers would you like in your array?");

			try {
				arraySize = input.nextInt();
			} catch (Exception e) { // bad input
				System.err.println("Invalid input!");
				break;
			}

			int[] theArray = new int[arraySize]; // initialize array

			System.out.println("How large would you like values to go up to?");

			try {
				maxValue = input.nextInt();
			} catch (Exception e) { // bad input
				System.err.println("Invalid input!");
				break;
			}

			theArray = fillRandom(theArray, maxValue); // fill random values

			System.out.println("Here is your array:");
			display(theArray); // print

			System.out.println("Are you ready to be bedazzled? (type something)");
			response = input.next(); // calm before the storm

			final long startTime = System.currentTimeMillis();
			theArray = mergeSort(theArray); // actual sorting here
			final long endTime = System.currentTimeMillis();

			System.out.println("BAM!!! Your array is now sorted!");
			display(theArray); // print
			System.out.println("It only took " + (endTime - startTime) + "ms!");
			System.out.println("Do you want to go again? (y = yes | n = no)");
			response = input.next();

			if (response.equals("y")) {
				System.out.println("Great! Currently resetting..");
				continue;
			} else {
				break; // end
			}
		}
	}

	/* fill the array with random values from 1 to max */
	public static int[] fillRandom(int[] arr, int max) {
		System.out.println("Filling array with values up to " + max + "..");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ((new Random()).nextInt(max) + 1);
		}

		return arr;
	}

	/* print array */
	public static void display(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("[" + arr[i] + "]");
		}
		System.out.println("");
	}

	/* recursive divide & conquer sort algorithm */
	public static int[] mergeSort(int[] arr) {
		if (arr.length <= 1) return arr;

		int[] left = new int[arr.length/2];
		int[] right = new int[arr.length - left.length];
		System.arraycopy(arr, 0, left, 0, left.length);
		System.arraycopy(arr, left.length, right, 0, right.length);

		mergeSort(left);
		mergeSort(right);

		arr = merge(left, right, arr);
		return arr;
	}

	/* 'merge' part : combine two arrays into a final array */
	public static int[] merge(int[] first, int[] second, int[] result) {
		int iLeft = 0;
		int iRight = 0;
		int iAns = 0;

		while (iLeft < first.length && iRight < second.length) {
			if (first[iLeft] < second[iRight]) {
				result[iAns] = first[iLeft];
				iLeft++;
			}

			else {
				result[iAns] = second[iRight];
				iRight++;
			}

			iAns++;
		}

		// pick up whatever is remaining
		System.arraycopy(first, iLeft, result, iAns, first.length - iLeft);
		System.arraycopy(second, iRight, result, iAns, second.length - iRight);

		return result;
	}
}