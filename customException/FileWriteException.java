package customException;

import java.io.IOException;

public class FileWriteException extends IOException {
    public FileWriteException(String msg) {
        super(msg);
    }

}
