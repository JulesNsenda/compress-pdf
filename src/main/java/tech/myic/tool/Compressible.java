package tech.myic.tool;

import java.io.File;
import java.io.IOException;

public interface Compressible
{
    void compress(File in, File out)
            throws IOException;
}
