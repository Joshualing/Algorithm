package pointToOffer;

public class Sort {
    static boolean duplicate(int[] nums) {
        if (nums.length <= 1)
            return false;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    System.out.println("duplicate:" + nums[i]);
                    return false;
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        duplicate(nums);
    }
}
