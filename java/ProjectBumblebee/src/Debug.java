
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Debug {
  
   public static void printFilesInFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            System.out.println("DIR" + fileEntry.getName());
        } else {
            System.out.println(fileEntry.getName());
        }
            System.out.println();
    }
   }
       public static void printArr(String[] arr){
    for(int i=0;i<arr.length;i++){
                System.out.println(i +": " + arr[i]);
                }
}

}

