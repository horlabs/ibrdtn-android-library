package de.tubs.ibr.dtn.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Sets an upper bound of bytes to be able to read from a stream.
*/
public class TruncatedInputStream extends FilterInputStream {
    /**
     * The maximum size, in bytes.
     */
    private long sizeMax;

    /**
     * The current number of bytes.
     */
    private long count;

    /**
     * Whether this stream is already closed.
     */
    private boolean closed;

    /**
     * Wraps an InputStream to not read more than pSizeMax bytes.
     * @param pIn stream to limit reading from
     * @param pSizeMax maximum number of bytes to be able to read
    */
    public TruncatedInputStream(InputStream pIn, long pSizeMax) {
        super(pIn);
        sizeMax = pSizeMax;
    }

    private boolean checkLimit() {
        return (count >= sizeMax);
    }

    public int read() throws IOException {
        if (checkLimit()) return -1;

        int res = super.read();
        if (res != -1) {
            count++;
        }
        return res;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (checkLimit()) return -1;

        int res = super.read(b, off, len);
        if (res > 0) {
            count += res;
        }
        return res;
    }

    public boolean isClosed() throws IOException {
        return closed;
    }

    public void close() throws IOException {
        closed = true;
        super.close();
    }
}
