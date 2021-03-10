package pointToOffer;

import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.util.Arrays;

public class MultipleThreadProcess {
    private static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private static final int threadCount = 3;

    public static int fun(){
        return Integer.valueOf("1")- Integer.valueOf("12");
    }
    public static void main(String[] args) {
        System.out.println(1<<1);
    }

    public static void main1(String[] args) {
        int length = array.length;
        int start = 0;
        int divide = length / threadCount;
        for (int i = 0; i < threadCount; i++) {
            int[] subArray;
            if (i < threadCount - 1) {
                subArray = Arrays.copyOfRange(array, start + i * divide, start + (i + 1) * divide);
                System.out.println(i + ":" + Arrays.stream(subArray).toArray().toString());
            } else {
                subArray = Arrays.copyOfRange(array, start + i * divide, length - 1);
                System.out.println(i + ":" + Arrays.stream(subArray).toArray().toString());
            }

            new Thread(() -> {
                for (int j = 0; j < subArray.length; j++) {
                    if (subArray[j] % 15 == 0) {
                        System.out.println(Thread.currentThread().getName() + "nums:" + subArray[j] + " A ");
                    } else if (subArray[j] % 5 == 0) {
                        System.out.println(Thread.currentThread().getName() + "nums:" + subArray[j] + " B ");
                    } else if (subArray[j] % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + "nums:" + subArray[j] + " C ");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "nums:" + subArray[j] + " D ");
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
