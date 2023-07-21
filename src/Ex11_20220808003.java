import java.util.ArrayList;
import java.util.List;

public class Ex11_20220808003 {
    public static void main(String[] args) {

    }

    public static int numOfTriplets(int[] arr, int sum) {
        int count = 0;
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int tripletSum = arr[i] + arr[left] + arr[right];
                if (tripletSum < sum) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    public static int kthSmallest(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid Argument");
        }

        buildMaxHeap(arr);

        int kthElement = 0;
        for (int i = 0; i < k; i++) {
            kthElement = extractMax(arr, arr.length - i);
        }

        return kthElement;
    }

    private static void buildMaxHeap(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static int extractMax(int[] arr, int heapSize) {
        int max = arr[0];
        arr[0] = arr[heapSize - 1];
        heapify(arr, heapSize - 1, 0);
        return max;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static String subSequence(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        int maxLength = 1;
        int startIndex = 0;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (str.charAt(j) > str.charAt(j - 1) && dp[i][j - 1] + 1 > dp[i][j]) {
                    dp[i][j] = dp[i][j - 1] + 1;

                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        startIndex = i;
                    }
                }
            }
        }

        System.out.println("Time complexity: O(n^2)");

        return str.substring(startIndex, startIndex + maxLength);
    }

    public static int isSubString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int i = 0, j = 0;

        while (i < n && j < m) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == m) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void findRepeats(int[] arr, int n) {
        List<Integer> repeatedElements = new ArrayList<>();

        heapSort(arr);

        int currentCount = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                currentCount++;
            } else {
                if (currentCount > n) {
                    repeatedElements.add(arr[i - 1]);
                }
                currentCount = 1;
            }
        }

        if (currentCount > n) {
            repeatedElements.add(arr[arr.length - 1]);
        }

        if (repeatedElements.isEmpty()) {
            System.out.println("None of the elements repeat more than " + n + " times.");
        } else {
            System.out.println("Elements that repeat more than " + n + " times: ");
            for (int num : repeatedElements) {
                System.out.println(num);
            }
        }
    }

    private static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }
}