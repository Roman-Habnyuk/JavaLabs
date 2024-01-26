package Lab2;

import java.io.File;
import java.io.IOException;

public interface Serializer<T> {

    void serialize(T object, File file) throws IOException;

    T deserialize(File file);

    void writeToFile(T object, File file);

    T readFromFile(File file);
}