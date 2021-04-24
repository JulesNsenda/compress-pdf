package tech.myic.tool;

public class FileCompressException
        extends Exception
{
    public FileCompressException()
    {
    }

    public FileCompressException(String message)
    {
        super(message);
    }

    public FileCompressException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
