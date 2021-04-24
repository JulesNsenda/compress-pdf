package tech.myic.tool;

import java.util.LinkedList;
import java.util.List;
import tech.myic.lib.AppParameter;
import tech.myic.lib.CmdOption;
import tech.myic.lib.Helper;

public class AppHelper
        implements Helper
{
    private final int numberOfParameters;
    private final String applicationName;
    private final String applicationDescription;

    public AppHelper(Builder builder)
    {
        this.applicationDescription = builder.applicationDescription;
        this.applicationName = builder.applicationName;
        this.numberOfParameters = builder.numberOfParameters;
    }

    public void showHelpMessage()
    {
        Helper.displayHelpMessage(getApplicationParameter(), getApplicationName(), getApplicationDescription());
    }

    @Override
    public String getApplicationDescription()
    {
        return this.applicationDescription;
    }

    @Override
    public AppParameter getApplicationParameter()
    {
        return new AppParameter.Builder()
                .NumberOfParameters(getNumberOfParameters())
                .CmdOptions(getCmdOptions())
                .build();
    }

    @Override
    public String getApplicationName()
    {
        return this.applicationName;
    }

    @Override
    public List<CmdOption> getCmdOptions()
    {
        List<CmdOption> cmdOptions = new LinkedList<>();
        cmdOptions.add(CmdOption.createCmdOption("-in=", "Input file location", "Compulsory - Specify input file location"));
        cmdOptions.add(CmdOption.createCmdOption("-out=", "Output file location", "Compulsory - Specify output file location"));
        cmdOptions.add(CmdOption.createCmdOption("-dpi=", "Dot per inch", "Compulsory - Resolution of the image on the page"));

        return cmdOptions;
    }

    @Override
    public int getNumberOfParameters()
    {
        return this.numberOfParameters;
    }

    public static class Builder
    {
        private int numberOfParameters;
        private String applicationName;
        private String applicationDescription;

        public Builder numberOfParameters(int numberOfParameters)
        {
            this.numberOfParameters = numberOfParameters;
            return this;
        }

        public Builder applicationName(String applicationName)
        {
            this.applicationName = applicationName;
            return this;
        }

        public Builder applicationDescription(String applicationDescription)
        {
            this.applicationDescription = applicationDescription;
            return this;
        }

        public AppHelper build()
        {
            return new AppHelper(this);
        }
    }
}
