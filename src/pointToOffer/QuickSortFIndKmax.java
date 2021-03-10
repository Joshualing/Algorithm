package pointToOffer;

import java.util.Arrays;

public class QuickSortFIndKmax {

    //通过快速排序的枢值 如果枢值== arr.length-i 则返回
    //小于则往右边走 同时arr=右边的数组，num不变
    //大于则往左边走 同时arr=左边的数组，num=arr.length-num
    public static int quickSort(int arr[], int num) {
        int target = arr[0];
        int i = 1, j = arr.length - 1, tmp;
        while (i < j) {
            while (i < j) {
                if (arr[i] > target)
                    break;
                i++;
            }
            while (i < j) {
                if (arr[j] < target)
                    break;
                j--;
            }
            if (i < j) {
                tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }
        if (num == arr.length - i + 1)
            return target;
        if (num > arr.length - i + 1) {
            int[] sub = new int[i - 1];
            System.arraycopy(arr, 1, sub, 0, i - 1);
            return quickSort(sub, num - (arr.length - i) - 1);
        }
        if (num < arr.length - i + 1) {
            int[] sub = new int[arr.length - i];
            System.arraycopy(arr, i, sub, 0, arr.length - i);
            return quickSort(sub, num);
        }
        return 0;
    }

    public static void main(String[] args) {
        //int[] array={5,3,4,2,1,23,12,11,6};//第5大的数是5
        int[] array = {5, 1, 2, 3, 0, -1, 7, 6};//第5大的数是5
        System.out.println(quickSort(array, 4));
    }
}
