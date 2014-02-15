
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
    
    public static boolean writeGML(int[][] matrix, String filename){
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
            fout.write("  Creator \"WorldFS\"\r\n");
            fout.write("  graph [\r\n");
            fout.write("    directed 0\r\n");
            fout.write("    comment \"WorldFS\"\r\n");
            //node
            for(int ix=0;ix<matrix.length;ix++){
                fout.write("    node [\r\n");
                fout.write(String.format("      id %d\r\n",ix));
                fout.write(String.format("      label \"%d\"\r\n",ix));
                fout.write("    ]\r\n");
            }
            
            //edge
            for(int ix=0;ix<matrix.length;ix++){
                for(int jx=ix;jx<matrix[ix].length;jx++){
                    if(matrix[ix][jx] != 0){
                        fout.write("    edge [\r\n");
                        fout.write(String.format("      source %d\r\n",ix));
                        fout.write(String.format("      target %d\r\n",jx));
                        fout.write(String.format("      label \"%d\"\r\n",matrix[ix][jx]));
                        fout.write("    ]\r\n");
                    }
                }
            }
            fout.write("  ]\r\n");
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        return true;
    }
}
