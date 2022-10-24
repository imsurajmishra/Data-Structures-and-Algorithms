package Leetcode_Solutions.TwoPointer;

public class Leetcode_845 {

        public int longestMountain(int[] arr) {

            return sol2(arr);
        }

        private int sol2(int[] arr){

            /**  [2,1,4,7,3,2,5] ->
             { we find peak element -> 4<-7->3}
             go outward and measure the length,
             go left from peak until arr[left] > arr[left+1]
             go right from peak until arr[right] > arr[right+1]

             then measure = left_index + right_index + 1;
             **/
            int res = 0;
            if(arr.length < 3)  return res;

            for(int i=1;i<arr.length-1;i++){

                // find peak
                if(arr[i-1] < arr[i] && arr[i] > arr[i+1]){

                    int left = i;
                    int count = 1;
                    // go left
                    while(left>0 && arr[left] > arr[left-1]){
                        left--;
                        count++;
                    }

                    int right = i;
                    // go right
                    while(right < arr.length-1 && arr[right] > arr[right+1]){
                        right++;
                        count++;
                    }

                    res = Math.max(res, count);
                }
            }

            return res;

        }


        private int sol1(int[] arr){
            if(arr.length < 3) return 0;

            int longestMountLen = 0;
            for(int i=1;i<arr.length;i++){
                int mountainLen = 1;
                int j=i;
                boolean consistDownhill = false;

                // increasing subsequence
                while(j<arr.length && arr[j] > arr[j-1]){
                    j++;
                    mountainLen++;
                }

                // decreasing subsequence
                while(j!=i && j<arr.length && arr[j] < arr[j-1]){
                    j++;
                    mountainLen++;
                    consistDownhill=true;
                }


                if(i!=j && consistDownhill && mountainLen>2){
                    longestMountLen = Math.max(longestMountLen,mountainLen);
                    j--;
                }

                i=j;
            }

            return longestMountLen;
        }
}
