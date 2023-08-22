import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class CS_483 {
    /**
     * Array shuffles elements in an array randomly using the fisher yates algorithm
     * 
     * @param array
     */
    public static void fisherYates(int[] array) {
        int n = array.length - 1;
        Random rand = new Random();
        for (int i = n; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            if (j == i)
                continue;
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
    }

    /**
     * function checks whether the array still has any breakpoints that need to be
     * removed
     * 
     * @param array
     * @return whether there are breakpoints left
     */
    public static boolean breakPoints(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (Math.abs(array[i] - array[i + 1]) != 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Copies an array into another array
     * 
     * @param copy array to be copied
     * @param old  array to be updated
     */
    public static void copy(int[] copy, int[] old) {
        for (int i = 0; i < copy.length; i++) {
            old[i] = copy[i];
        }
    }

    /**
     * This function checks whether an array still has breakpoints and if so, it
     * continues to check for the consecutive term and the postion of the
     * breakpoints to decide which scenario to
     * use. There are 4 scenarios and 1 and 2 check for if there is not a one at the
     * 1st index and whether the breakpoint comes before or after. The second 2
     * scenarios would occur if there is a 1 at the 1st index. Once the array is
     * sorted, there are no longer problematic breakpoints and the sorting can stop.
     * The array should be seen as a stack unsorted different size pancakes that are
     * being sorted using a combination of different movements to the pancake stack.
     * 
     * @param array stack of diameter of pancakes
     */
    public static void threeApproximationAlg(int[] array) {
        int n = array.length;
        int count = 0;
        while (breakPoints(array)) {
            if (array[1] != 1) {
                for (int i = 2; i < n; i++) {
                    if (Math.abs(array[1] - array[i]) == 1 && i != 2) {
                        int num = array[i];
                        if (i != n - 1 && Math.abs(num - array[i + 1]) != 1) {
                            // TYPE 1 SCENARIO
                            int end = 1;
                            while (Math.abs(array[end] - array[end + 1]) == 1) {
                                end += 1;
                            }

                            // START OF CODE FOR THE PREFIX TRANSPOSITION
                            int[] transposition = Arrays.copyOfRange(array, 1, end + 1);
                            int[] newArray = new int[n];
                            int currIndex = 1;
                            for (int j = end + 1; j < n; j++) {
                                newArray[currIndex] = array[j];
                                currIndex += 1;
                                if (array[j] == num) {
                                    for (int k = 0; k < transposition.length; k++) {
                                        newArray[currIndex] = transposition[k];
                                        currIndex += 1;
                                    }
                                }
                            }
                            copy(newArray, array);
                            count += 1;
                            // System.out.println("Type 1");
                            // printArray(array);
                            // System.out.println("Count = " + count);
                            // System.out.println();
                            break;
                        } else if (Math.abs(num - array[i - 1]) != 1) {
                            // TYPE 2 SCENARIO
                            int end = i - 1;
                            for (int k = 1; k < end; k++) {
                                int temp = array[k];
                                array[k] = array[end];
                                array[end] = temp;
                                end -= 1;

                            }
                            count += 1;
                            // System.out.println("Type 2");
                            // printArray(array);
                            // System.out.println("Count = " + count);
                            // System.out.println();
                            break;
                        }
                    }
                }

            } else {
                for (int i = 1; i < n - 1; i++) {
                    if (Math.abs(array[i] - array[i + 1]) != 1) {
                        int num1 = array[i];
                        for (int j = i + 1; j < n - 1; j++) {
                            if (Math.abs(num1 - array[j]) == 1 && array[1] == 1) {
                                int num2 = array[j]; // 2
                                if (Math.abs(array[j] - array[j - 1]) != 1) {
                                    // TYPE 3 SCENARIO
                                    int end = 1;
                                    while (Math.abs(array[end] - array[end + 1]) == 1) {
                                        end += 1;
                                    }
                                    int[] transposition = Arrays.copyOfRange(array, 1, end + 1);
                                    int[] newArray = new int[n];
                                    int currIndex = 1;
                                    for (int l = end + 1; l < n; l++) {
                                        if (array[l] == num2) {
                                            for (int k = 0; k < transposition.length; k++) {
                                                newArray[currIndex] = transposition[k];
                                                currIndex += 1;
                                            }
                                        }
                                        newArray[currIndex] = array[l];
                                        currIndex += 1;
                                    }
                                    count += 1;
                                    copy(newArray, array);
                                    // System.out.println("Type 3");
                                    // printArray(array);
                                    // System.out.println("Count = " + count);
                                    // System.out.println();
                                    break;
                                } else if (Math.abs(array[j] - array[j + 1]) != 1 && array[1] == 1) {
                                    // TYPE 4 SCENARIO
                                    int end = j;
                                    for (int k = 1; k <= end; k++) {
                                        int temp = array[k];
                                        array[k] = array[end];
                                        array[end] = temp;
                                        end -= 1;
                                    }
                                    count += 1;
                                    // System.out.println("Type 4");
                                    // printArray(array);
                                    // System.out.println("Count = " + count);
                                    // System.out.println();
                                    break;
                                }
                            }
                        }
                    }
                }

            }
        }
        System.out.println("3-Approximation : " + count);
    }

    /**
     * This function checks whether an array still has breakpoints and if so, it
     * continues to check for the consecutive term and the postion of the
     * breakpoints to decide which scenario to
     * use. There are 4 scenarios and 1 and 2 check for if there is not a one at the
     * 1st index and whether the breakpoint comes before or after. The second 2
     * scenarios would occur if there is a 1 at the 1st index, this algorithm is
     * simlar to the three approximation but the difference that the tye 4 scenario
     * includes a prefix transreversal which not only reverses but also does a
     * transreversal. Once the array is
     * sorted, there are no longer problematic breakpoints and the sorting can stop.
     * The array should be seen as a stack unsorted different size pancakes that are
     * being sorted using a combination of different movements to the pancake stack
     * 
     * @param array stack of diameter of different pancakes
     */
    public static void twoApproximationAlg(int[] array) {
        int n = array.length;
        int count = 0;
        while (breakPoints(array)) {
            if (array[1] != 1) {
                for (int i = 2; i < n; i++) {
                    if (Math.abs(array[1] - array[i]) == 1 && i != 2) {
                        int num = array[i];
                        if (i != n - 1 && Math.abs(num - array[i + 1]) != 1) {
                            // TYPE 1 SCENARIO
                            int end = 1;
                            while (Math.abs(array[end] - array[end + 1]) == 1) {
                                end += 1;
                            }

                            // START OF CODE FOR THE PREFIX TRANSPOSITION
                            int[] transposition = Arrays.copyOfRange(array, 1, end + 1);
                            int[] newArray = new int[n];
                            int currIndex = 1;
                            for (int j = end + 1; j < n; j++) {
                                newArray[currIndex] = array[j];
                                currIndex += 1;
                                if (array[j] == num) {
                                    for (int k = 0; k < transposition.length; k++) {
                                        newArray[currIndex] = transposition[k];
                                        currIndex += 1;
                                    }
                                }
                            }
                            copy(newArray, array);
                            count += 1;
                            // System.out.println("Type 1");
                            // printArray(array);
                            // System.out.println("Count = " + count);
                            // System.out.println();
                            break;
                        } else if (Math.abs(num - array[i - 1]) != 1) {
                            // TYPE 2 SCENARIO
                            int end = i - 1;
                            for (int k = 1; k < end; k++) {
                                int temp = array[k];
                                array[k] = array[end];
                                array[end] = temp;
                                end -= 1;

                            }
                            count += 1;
                            // System.out.println("Type 2");
                            // printArray(array);
                            // System.out.println("Count = " + count);
                            // System.out.println();
                            break;
                        }
                    }
                }

            } else {
                for (int i = 1; i < n - 1; i++) {
                    if (Math.abs(array[i] - array[i + 1]) != 1) {
                        int num1 = array[i];
                        for (int j = i + 1; j < n - 1; j++) {
                            if (Math.abs(num1 - array[j]) == 1 && array[1] == 1) {
                                int num2 = array[j];
                                if (Math.abs(array[j] - array[j - 1]) != 1) {
                                    // TYPE 3 SCENARIO

                                    int end = 1;
                                    while (Math.abs(array[end] - array[end + 1]) == 1) {
                                        end += 1;
                                    }
                                    int[] transposition = Arrays.copyOfRange(array, 1, end + 1);
                                    int[] newArray = new int[n];
                                    int currIndex = 1;
                                    for (int l = end + 1; l < n; l++) {
                                        if (array[l] == num2) {
                                            for (int k = 0; k < transposition.length; k++) {
                                                newArray[currIndex] = transposition[k];
                                                currIndex += 1;
                                            }
                                        }
                                        newArray[currIndex] = array[l];
                                        currIndex += 1;
                                    }
                                    copy(newArray, array);
                                    count += 1;
                                    // System.out.println("Type 3");
                                    // printArray(array);
                                    // System.out.println("Count = " + count);
                                    // System.out.println();
                                    break;
                                } else if (Math.abs(array[j] - array[j + 1]) != 1 && array[1] == 1) {
                                    // TYPE 4 SCENARIO
                                    count += 1;
                                    int end = i;
                                    for (int k = 1; k <= end; k++) {
                                        int temp = array[k];
                                        array[k] = array[end];
                                        array[end] = temp;
                                        end -= 1;
                                    }

                                    int[] transposition = Arrays.copyOfRange(array, 1, i + 1);
                                    int insert = 0;
                                    for (int k = j; k < array.length; k++) { // find where the subarray should go
                                        if (Math.abs(transposition[0] - array[k]) == 1) {
                                            insert = array[k];
                                            break;
                                        }
                                    }
                                    int[] newArray = new int[n];
                                    int index = 1;
                                    for (int l = i + 1; l <= j; l++) { // copy up until the first breakpoint
                                        newArray[index] = array[l];
                                        index += 1;
                                    }
                                    int mark = 0; // 0
                                    int indexCopy = index; // 5
                                    for (int l = index; l < indexCopy + transposition.length; l++) { // l < 7
                                        newArray[index] = transposition[mark];
                                        mark += 1;
                                        index += 1;
                                    }
                                    for (int l = index; l < n; l++) {
                                        newArray[l] = array[l];
                                    }

                                    copy(newArray, array);
                                    // AT THIS POINT, TRANSPOSITION PART OF TRANSREVERSAL HAS BEEN DONE

                                    // System.out.println("Type 4");
                                    // printArray(array);
                                    // System.out.println("Count = " + count);
                                    // System.out.println();
                                    break;
                                }
                            }
                        }
                    }
                }

            }
        }
        System.out.println("2-Approximation : " + count);
    }

    /**
     * This function starts by finding the first black edge, which we cann the
     * starting black edge. Each time we loop and attempt to get rid of some break
     * points, we will have to loop again and find the new starting edge. In terms
     * of pancakes, with a forward march approach we will make sure to start from
     * the pancake that is not in the sorted portion of the stack.
     * 
     * @param array
     */
    public static void forwardMarch(int[] array) {
        int n = array.length;
        boolean visited = false;
        int count = 0;
        while (breakPoints(array)) {
            visited = false;
            for (int i = 0; i < n - 1; i++) {
                if (Math.abs(array[i] - array[i + 1]) != 1) { /// find the starting edge
                    for (int j = i + 1; j < n - 1; j++) {
                        if (Math.abs(array[i] - array[j]) == 1) { // find the number consecutive to i
                            if (Math.abs(array[j] - array[j - 1]) != 1) { // this can either be a type 1 or type 3
                                for (int k = j; k < n - 1; k++) {
                                    if (Math.abs(array[k] - array[k + 1]) != 1
                                            && Math.abs(array[j - 1] - array[k + 1]) == 1) {
                                        // SCENARIO 1
                                        visited = true;
                                        count += 1;
                                        int[] newArray = new int[n];
                                        int[] subarray1 = Arrays.copyOfRange(array, i + 1, j);
                                        int[] subarray2 = Arrays.copyOfRange(array, j, k + 1);
                                        int index = 0;
                                        for (int l = 0; l <= i; l++) {
                                            newArray[l] = array[l];
                                            index += 1;
                                        }
                                        int subCount = 0;
                                        for (int l = index; l < subarray2.length + index; l++) {
                                            newArray[l] = subarray2[subCount];
                                            subCount += 1;
                                        }
                                        subCount = 0;
                                        index += subarray2.length;
                                        for (int l = index; l < subarray1.length + index; l++) {
                                            newArray[l] = subarray1[subCount];
                                            subCount += 1;
                                        }
                                        index += subarray1.length;
                                        for (int l = index; l < n; l++) {
                                            newArray[l] = array[l];
                                        }
                                        copy(newArray, array);
                                        // System.out.println("Type 1");
                                        // printArray(array);
                                        // System.out.println("Count = " + count);
                                        // System.out.println();
                                    }
                                    if (visited == true) {
                                        break;
                                    }
                                }
                            }
                        }
                        if (visited == true) {
                            break;
                        }
                    }

                }
                if (visited == true) {
                    break;
                }
            }
            if (visited == true) {
                continue;
            }
            for (int i = 0; i < n - 1; i++) {
                if (Math.abs(array[i] - array[i + 1]) != 1) { /// find the starting edge
                    for (int j = i + 2; j < n - 1; j++) {
                        if (Math.abs(array[i + 1] - array[j]) == 1 && Math.abs(array[j] - array[j + 1]) != 1
                                && j != i + 2) {
                            for (int k = i + 1; k < j; k++) {
                                if (Math.abs(array[j + 1] - array[k]) == 1 && Math.abs(array[k] - array[k + 1]) != 1) {
                                    // SCENARIO 2
                                    visited = true;
                                    count += 1;
                                    int[] newArray = new int[n];
                                    int[] subarray1 = Arrays.copyOfRange(array, i + 1, k + 1);
                                    int[] subarray2 = Arrays.copyOfRange(array, k + 1, j + 1);
                                    int index = 0;
                                    for (int l = 0; l <= i; l++) {
                                        newArray[l] = array[l];
                                        index += 1;
                                    }
                                    int subCount = 0;
                                    for (int l = index; l < subarray2.length + index; l++) {
                                        newArray[l] = subarray2[subCount];
                                        subCount += 1;
                                    }
                                    subCount = 0;
                                    index += subarray2.length;
                                    for (int l = index; l < subarray1.length + index; l++) {
                                        newArray[l] = subarray1[subCount];
                                        subCount += 1;
                                    }
                                    index += subarray1.length;
                                    for (int l = index; l < n; l++) {
                                        newArray[l] = array[l];
                                    }
                                    copy(newArray, array);
                                    // System.out.println("Type 2");
                                    // printArray(array);
                                    // System.out.println("Count = " + count);
                                    // System.out.println();

                                }
                            }
                        }
                    }
                }
            }
            if (visited == true) {
                continue;
            }
            for (int i = 0; i < n - 1; i++) {
                if (Math.abs(array[i] - array[i + 1]) != 1) { /// find the starting edge
                    for (int j = i + 1; j < n - 1; j++) {
                        if (Math.abs(array[i] - array[j]) == 1) { // find the number consecutive to i
                            if (Math.abs(array[j] - array[j - 1]) != 1) { // this can either be a type 1 or type 3
                                for (int k = j; k < n - 1; k++) {
                                    if (Math.abs(array[k] - array[i + 1]) == 1
                                            && Math.abs(array[k] - array[k + 1]) != 1 & k != i + 2) {
                                        // SCENARIO 3
                                        visited = true;
                                        count += 1;
                                        int[] newArray = new int[n];
                                        int[] subarray1 = Arrays.copyOfRange(array, i + 1, j);
                                        int[] subarray2 = Arrays.copyOfRange(array, j, k + 1);
                                        int index = 0;
                                        for (int l = 0; l <= i; l++) {
                                            newArray[l] = array[l];
                                            index += 1;
                                        }
                                        int subCount = 0;
                                        for (int l = index; l < subarray2.length + index; l++) {
                                            newArray[l] = subarray2[subCount];
                                            subCount += 1;
                                        }
                                        subCount = 0;
                                        index += subarray2.length;
                                        for (int l = index; l < subarray1.length + index; l++) {
                                            newArray[l] = subarray1[subCount];
                                            subCount += 1;
                                        }
                                        index += subarray1.length;
                                        for (int l = index; l < n; l++) {
                                            newArray[l] = array[l];
                                        }
                                        copy(newArray, array);
                                        // System.out.println("Type 3");
                                        // printArray(array);
                                        // System.out.println("Count = " + count);
                                        // System.out.println();
                                    }
                                    if (visited == true) {
                                        break;
                                    }
                                }
                            }
                        }
                        if (visited == true) {
                            break;
                        }
                    }

                }
                if (visited == true) {
                    break;
                }
            }
            if (visited == true) {
                continue;
            }
            for (int i = 0; i < n - 1; i++) {
                if (Math.abs(array[i] - array[i + 1]) != 1) { // find the first breakpoint
                    for (int j = i + 1; j < n - 1; j++) {
                        if (Math.abs(array[j] - array[i + 1]) == 1 && j != i + 2) {
                            int num = array[j];
                            if (Math.abs(num - array[j + 1]) != 1) {
                                visited = true;
                                count += 1;
                                int[] newArray = new int[n];
                                int end = i + 1;
                                while (Math.abs(array[end] - array[end + 1]) == 1) {
                                    end += 1;
                                }
                                int[] subarray1 = Arrays.copyOfRange(array, i + 1, end + 1);
                                int[] subarray2 = Arrays.copyOfRange(array, end + 1, j + 1);
                                int index = 0;
                                for (int l = 0; l <= i; l++) {
                                    newArray[l] = array[l];
                                    index += 1;
                                }
                                int subCount = 0;
                                for (int l = index; l < subarray2.length + index; l++) {
                                    newArray[l] = subarray2[subCount];
                                    subCount += 1;
                                }
                                subCount = 0;
                                index += subarray2.length;
                                for (int l = index; l < subarray1.length + index; l++) {
                                    newArray[l] = subarray1[subCount];
                                    subCount += 1;
                                }
                                index += subarray1.length;
                                for (int l = index; l < n; l++) {
                                    newArray[l] = array[l];
                                }
                                copy(newArray, array);
                                // System.out.println("Type 4");
                                // printArray(array);
                                // System.out.println("Count = " + count);
                                // System.out.println();
                            }
                        }
                    }
                    if (visited == true) {
                        break;
                    }
                }
                if (visited == true) {
                    break;
                }
            }
            if (visited == true) {
                continue;
            }
            for (int i = 0; i < n - 1; i++) {
                if (Math.abs(array[i] - array[i + 1]) != 1) {
                    for (int j = i + 1; j < n; j++) {
                        if (Math.abs(array[i + 1] - array[j]) == 1 && j != i + 2) {
                            if (Math.abs(array[j] - array[j - 1]) != 1) {
                                count += 1;
                                visited = true;
                                int start = i + 1;
                                int end = j - 1;
                                for (int k = start; k < end; k++) {
                                    int temp = array[k];
                                    array[k] = array[end];
                                    array[end] = temp;
                                    end -= 1;
                                }
                                // System.out.println("Type 5");
                                // printArray(array);
                                // System.out.println("Count = " + count);
                                // System.out.println();
                            }
                        }
                    }
                    if (visited == true) {
                        break;
                    }
                }
                if (visited == true) {
                    break;
                }
            }
        }
        System.out.println("Forward March : " + count);
    }

    /**
     * Prints the array
     * 
     * @param arr array to be printed
     */
    public static void printArray(int[] arr) {
        int n = arr.length;
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i != n - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    public static int numBreakpoints(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (Math.abs(array[i] - array[i + 1]) != 1)
                count += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // scanner to take in input for the size of the array
        System.out.print("Please enter a value for the size of the list: "); // promtthe user for the size of the
        int n = input.nextInt(); // take in the input as an int

        int[] list = new int[n]; // create an array which is 0-n and unshuffled
        for (int i = 1; i < n + 1; i++) { // set each index equal to the values 1-n
            list[i - 1] = i;
        }
        System.out.println("Unshuffled Array");
        printArray(list); // print the elements of the unshuffled array
        fisherYates(list); // shuffles the arrray using fisher yates algorithm
        int[] sortList = new int[n + 2]; /// creates the array and makes place for the 0 and the n + 1 element
        sortList[0] = 0;
        sortList[n + 1] = n + 1;
        for (int i = 1; i < n + 1; i++) {
            sortList[i] = list[i - 1];
        }
        System.out.println();
        System.out.println("Shuffled Array");
        printArray(list); // print the new shuffled array
        System.out.println();
        System.out.println("Array ready for sorting");
        printArray(sortList);
        System.out.println();
        System.out.println("Number of Breakpoints: " + numBreakpoints(sortList));
        int[] c1 = new int[n + 2];
        int[] c2 = new int[n + 2];
        int[] c3 = new int[n + 2];
        copy(sortList, c1);
        copy(sortList, c2);
        copy(sortList, c3);
        threeApproximationAlg(c1);
        twoApproximationAlg(c2);
        forwardMarch(c3);
        // int choice = 0;
        // while (choice != 4) {

        // System.out.print("Please enter a value for the size of the list: "); //
        // promtthe user for the size of the
        // int n = input.nextInt(); // take in the input as an int

        // int[] list = new int[n]; // create an array which is 0-n and unshuffled
        // for (int i = 1; i < n + 1; i++) { // set each index equal to the values 1-n
        // list[i - 1] = i;
        // }
        // System.out.println("Unshuffled Array");
        // printArray(list); // print the elements of the unshuffled array
        // fisherYates(list); // shuffles the arrray using fisher yates algorithm
        // int[] sortList = new int[n + 2]; /// creates the array and makes place for
        // the 0 and the n + 1 element
        // sortList[0] = 0;
        // sortList[n + 1] = n + 1;
        // for (int i = 1; i < n + 1; i++) {
        // sortList[i] = list[i - 1];
        // }
        // System.out.println();
        // System.out.println("Shuffled Array");
        // printArray(list); // print the new shuffled array
        // System.out.println();
        // System.out.println("Array ready for sorting");
        // printArray(sortList);
        // System.out.println();
        // System.out.println("Number of Breakpoints: " + numBreakpoints(sortList));

        // System.out.println();
        // System.out.println();
        // System.out.println();
        // System.out.println(
        // "OPTIONS \n--------------\n1 - 3 Approximation\n2 - 2-Approximation\n3 -
        // Forward March\n4 - Exit\n--------------\n");
        // System.out.println("Please enter an option for the algorithm you would like
        // to run, or 4 to exit: ");
        // choice = input.nextInt();
        // if (choice == 1) {
        // System.out.println("Starting 3 approximation method");
        // System.out.println("-------------------------");
        // threeApproximationAlg(sortList);
        // System.out.println("-------------------------");
        // System.out.println("Result of 3 approximation algorithm");
        // printArray(sortList);
        // }
        // if (choice == 2) {
        // System.out.println("Starting 2 approximation method");
        // System.out.println("-------------------------");
        // twoApproximationAlg(sortList);
        // System.out.println("-------------------------");
        // System.out.println("Result of 2 approximation algorithm");
        // printArray(sortList);
        // }
        // if (choice == 3) {
        // System.out.println("Starting Forward March method");
        // System.out.println("-------------------------");
        // threeApproximationAlg(sortList);
        // System.out.println("-------------------------");
        // System.out.println("Result of Forward march algorithm");
        // printArray(sortList);
        // }
        // System.out.println("\n");
        // }
        input.close();
    }
}
