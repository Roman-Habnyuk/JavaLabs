package Lab2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
public class XmlSerializer<T> implements Serializer<T> {

    private final Class<T> type;

    public XmlSerializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public void serialize(T object, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(object, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T deserialize(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return type.cast(unmarshaller.unmarshal(file));
        } catch (JAXBException e) {
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