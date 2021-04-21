package tech.myic.tool;

import java.io.File;
import java.io.IOException;

public class AppProcessor
{
    private static final FileCompressor fileCompressor = new FileCompressor();

    public static void main(String[] args)
    {
        try {
            if (args.length == 1 && (args[0].equals("?")) || (args[0].equals("--help")) || args[0].equals("-h")){
                AppHelper.showHelpMessage();
                return;
            }else if (args.length < AppHelper.getAppParameter().getNumberOfParameters()){
                AppHelper.showHelpMessage();
                System.exit(1);
            }

            File in = getInputFile(args);

            File out = getOutputFile(args);

            System.out.println("Start compression...");
            compressPdf(in, out);
            System.out.println("File compressed successfully");
        }catch (CompressPdfException e){
            System.err.println("Error compressing file " + e);
            System.exit(1);
        }
    }

    private static File getOutputFile(String[] args)
    {
        String outFileLocation = args[1];
        return new File(outFileLocation);
    }

    private static File getInputFile(String[] args)
            throws CompressPdfException
    {
        String inFileLocation = args[0];
        File in = new File(inFileLocation);
        if (!in.exists()){
            throw new CompressPdfException("No such file found: " + inFileLocation);
        }
        return in;
    }

    private static void compressPdf(File in, File out)
            throws CompressPdfException
    {
        try {
            fileCompressor.compress(in, out);
        }catch (IOException e){
            throw new CompressPdfException("Error compressing file: " + in.getAbsolutePath());
        }
    }

}
