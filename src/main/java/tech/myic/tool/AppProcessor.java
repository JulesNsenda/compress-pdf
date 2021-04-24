package tech.myic.tool;

import java.io.File;
import java.io.IOException;

public class AppProcessor
{
    private static final FileCompressor fileCompressor = new FileCompressor();

    public static void main(String[] args)
    {
        AppHelper appHelper = new AppHelper.Builder()
                .applicationDescription("This application allows you to compress a PDF file at 150 DPI per page to keep the quality of the document.")
                .applicationName("compress-pdf")
                .numberOfParameters(2)
                .build();

        try {
            if (args.length == 1 && (args[0].equals("?")) || (args[0].equals("--help")) || args[0].equals("-h")){
                appHelper.showHelpMessage();
                return;
            }else if (args.length < appHelper.getNumberOfParameters()){
                appHelper.showHelpMessage();
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
