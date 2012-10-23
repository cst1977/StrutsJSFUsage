package de.fiz.karlsruhe.mapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface OxMapper {

    /**
     * Serializes assigned Object into a file with the assigned name.
     * 
     * @param object
     *            - Object that should be serialized
     * @param filename
     *            - name of the XML-file
     * @throws IOException
     */
    public abstract void writeObjectToXml(Object object, String filename) throws IOException;

    /**
     * Deserializes an object from the assigned file.
     * 
     * @param filename
     *            - name of the file that should be deserialized
     * @return deserialized object
     * @throws IOException
     */
    public abstract Object readObjectFromXml(InputStream in) throws IOException;

}