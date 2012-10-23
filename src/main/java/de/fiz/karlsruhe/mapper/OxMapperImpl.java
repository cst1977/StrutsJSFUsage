package de.fiz.karlsruhe.mapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

public class OxMapperImpl implements OxMapper {

    private static final Log LOG = LogFactory.getLog(OxMapperImpl.class);

    private Marshaller marshaller;

    private Unmarshaller unmarshaller;

    public void writeObjectToXml(Object object, String filename) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            marshaller.marshal(object, new StreamResult(fos));
        } catch (XmlMappingException e) {
            LOG.error("Xml-Serialization failed due to an XmlMappingException.", e);
        } catch (IOException e) {
            LOG.error("Xml-Serialization failed due to an IOException.", e);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    public Object readObjectFromXml(InputStream in) throws IOException {
        try {
            return unmarshaller.unmarshal(new StreamSource(in));
        } catch (IOException e) {
            LOG.error("Xml-Deserialization failed due to an IOException.", e);
        } 
        return null;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
    
}
