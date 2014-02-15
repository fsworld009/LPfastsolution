
package lpfastsolution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * do all the I/O
 */
public class FileProcessor {
    
    public static boolean writeMatrix(int[][] matrix, String filename){
        FileWriter fstream = null;
        try{
            fstream = new FileWriter(filename);
        }catch(java.io.FileNotFoundException e){
            //cannot create file or don't have permission to write
            System.err.println("Error: cannot create output file or don't have permission to write");
            return false;
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        
        BufferedWriter fout = new BufferedWriter(fstream);
        try {
            for(int ix=0;ix<matrix.length;ix++){
                for(int jx=0;jx<matrix[ix].length;jx++){
                    fout.write(String.format("%d ",matrix[ix][jx]));
                }
                fout.write("\r\n");
            }
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        return true;
    }
    
    public static int[][] readMatrix(String filename){
        return null;
    }
    
    public boolean writeGML(int[][] matrix, String filename){
        return true;
    }
}
