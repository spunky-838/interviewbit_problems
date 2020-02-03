package practice;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class DownToZero {

    /*
     * Complete the downToZero function below.
     */
     static int nums[] = new int[1000001];
     static int downToZero(int n) {
        /*
         * Write your code here.
         */
        // if(n==0)return 0;
        // if(n==1)return 1;
        // int r1=downToZero(n-1);
        // int r2=doMoveOne(n);
        // if(r1<r2) {
        //     return r1+1;
        // }
        // return r2+1;
        return nums[n];
    }
    
    static int doMoveOne(int n){
        if(isPrime(n))return Integer.MAX_VALUE;
        for(int i=(int) Math.sqrt(n);i>1;i--) {
            if(n%i==0)return downToZero(n/i);
        }
        return Integer.MAX_VALUE;
    }
    
    static boolean isPrime(int n) 
    { 
        if (n <= 1) return false; 
        for (int i = 2; i < n; i++) {
            if (n % i == 0) 
                return false; 
        }
        return true; 
    }
    
    private static final Scanner scanner = new Scanner(System.in);

        /*
         * Write your code here.
         */

    public static void main(String[] args) throws IOException {
        int max = 1000000;
        
        //Initialize array
        for(int i = 0; i < max; ++i) nums[i] = -1;
        nums[0] = 0; nums[1] = 1; nums[2] = 2; nums[3] = 3;
        
        //Precompute
        for(int i = 1; i < max; ++i){
            if(nums[i] == -1 || nums[i] > (nums[i - 1] + 1))
                nums[i] = nums[i - 1] + 1;
            for(int j = 1; j <= i && j * i < max; ++j)
                if(nums[j * i] == -1 || (nums[i] + 1) < nums[j * i])
                    nums[j * i] = nums[i] + 1;
        }
        
        
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            int result = downToZero(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}