
public class MaximumSubArray {
    static class Index{
        private int start;
        private int end;

        public Index() {
            start=0;
            end=0;
        }
    }
    public static void main(String args[]){
        int a[] = { -2, -3, 4, -1, 2, 1, 1, -3 };
        Index k = new Index();
        int sum = maxSubArray(a, k);
        System.out.println("Sum: " + sum + ", start: " + k.start + ", end: " + k.end);
    }

    // Code for just the sum
    /*
    public int maxSubArray(int[] nums) {
        // Kadane's algorithm
        int maxSoFar=nums[0], maxEndingHere=nums[0];
        for (int i=1;i<nums.length;++i){
            maxEndingHere= Math.max(maxEndingHere+nums[i],nums[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
    */

    public static int maxSubArray(int[] nums, Index k) {
        // Kadane's algorithm
        int maxSoFar=nums[0], maxEndingHere=nums[0];
        for (int i=1;i<nums.length;++i){

            maxEndingHere= Math.max(maxEndingHere+nums[i],nums[i]);
            if(maxSoFar < maxEndingHere){
                maxSoFar = maxEndingHere;
                if(maxEndingHere==nums[i]){
                    k.start = i;
                    k.end = i;
                }else{
                    k.end = i;
                }
            }

        }
        return maxSoFar;
    }
}
