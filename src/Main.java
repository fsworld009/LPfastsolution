

import lpfastsolution.LPFastSolution;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LPFastSolution lpf = new LPFastSolution();
        int kx;
        if(args.length >= 1){
            kx = Integer.parseInt(args[0]);
            lpf.run(kx,"","");
        }else{
            System.out.println("Usage: java Main [k]");
        }

        
    }
}
