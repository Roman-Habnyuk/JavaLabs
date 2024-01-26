package Lab2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class JsonSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> type;

    public JsonSerializer(Class<T> type) {
        this.type = type;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void serialize(T object, File file) {
        try {
            objectMapper.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T deserialize(File file) {
        try {
            return objectMapper.readValue(file, objectMapper.constructType(type));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeToFile(T object, File file) {
        serialize(object, file);
    }

    @Override
    public T readFromFile(File file) {
        return deserialize(file);
    }
}