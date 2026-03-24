package com.core.practice.strings;

import java.util.Arrays;

public class App01 {
    public static void main(String[] args) {
     // Reverse a String
        String str = "Rohit";
        String reverseStr = "";
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            reverseStr =c+reverseStr;
        }
        System.out.println("Approach 1:"+reverseStr);

        //Using 2 pointer SWAPPING logic
        char[] arr = str.toCharArray();//{R,o,h,i,,t}
        int left = 0;
        int right = arr.length - 1;//5-1=4
        while (left < right) {// will run till 0<4
            char temp = arr[left];
            arr[left] = arr[right];  // SWAP th indexes
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.println("Approch 2: "+new String(arr));
    }
}
