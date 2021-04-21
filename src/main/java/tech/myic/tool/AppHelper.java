package tech.myic.tool;

import java.util.LinkedList;
import java.util.List;
import tech.myic.lib.AppParameter;
import tech.myic.lib.CmdOption;
import tech.myic.lib.Helper;

public class AppHelper
{

    public static void showHelpMessage()
    {
        Helper.displayHelpMessage(getAppParameter(), "compress-pdf");
    }

    public static AppParameter getAppParameter()
    {
        return new AppParameter.Builder()
                .NumberOfParameters(2)
                .CmdOptions(getCmdOptions())
                .build();
    }

    private static List<CmdOption> getCmdOptions()
    {
        List<CmdOption> cmdOptions = new LinkedList<>();
        cmdOptions.add(CmdOption.createCmdOption("-in=", "Input file location"));
        cmdOptions.add(CmdOption.createCmdOption("-out=", "Output file location"));
        return cmdOptions;
    }
}
