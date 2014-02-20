
package lpfastsolution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * do all the I/O
 */
public class FileProcessor {
    
    public static boolean writeFile(String content, String filename){
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
            fout.write(content);
            fout.close();
        } catch (IOException ex) {
            System.err.println("Error: IO error");
            return false;
        }
        return true;
    }
    
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
        Scanner sc=null;
        int[][] matrix=null;
        try{
            sc = new Scanner(new File(filename));
        }catch(FileNotFoundException e){
            System.err.printf("file not found\n");
            return null;
        }
 
        matrix = new int[LPFastSolution.numOfNodes][LPFastSolution.numOfNodes];
        
        for(int ix=0;ix<matrix.length;ix++){
            for(int jx=0;jx<matrix[ix].length;jx++){
                matrix[ix][jx] = sc.nextInt();
            }
            
        }
        return matrix;
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
            fout.write("  Creator \"yxl122130\"\r\n");
            fout.write("  graph [\r\n");
            fout.write("    directed 1\r\n");
            fout.write("    comment \"yxl122130\"\r\n");
            //node
            for(int ix=0;ix<matrix.length;ix++){
                fout.write("    node [\r\n");
                fout.write(String.format("      id %d\r\n",ix));
                fout.write(String.format("      label \"%d\"\r\n",ix));
                fout.write("    ]\r\n");
            }
            
            //edge
            for(int ix=0;ix<matrix.length;ix++){
                for(int jx=0;jx<matrix[ix].length;jx++){
                    if(matrix[ix][jx] != 0){
                        fout.write("    edge [\r\n");
                        fout.write(String.format("      source %d\r\n",ix));
                        fout.write(String.format("      target %d\r\n",jx));
                        fout.write(String.format("      value \"%d\"\r\n",matrix[ix][jx]));
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
