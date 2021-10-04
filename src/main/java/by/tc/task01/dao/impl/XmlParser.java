package by.tc.task01.dao.impl;

import by.tc.task01.entity.Appliance;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class XmlParser {

    private Logger logger = Logger.getLogger(XmlParser.class.getSimpleName());
    private ObjectMapper mapper;

    public XmlParser() {
        mapper = new ObjectMapper();
    }

    Map<Class<? extends Appliance>, List<Appliance>> parse(File xmlFile){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            return mapper.createMapping(doc.getChildNodes());

        }
        catch (ParserConfigurationException e) {
            logger.info("cannot parse xml file");
            e.printStackTrace();
        }
        catch (IOException e) {
            logger.info("cannot parse xml file");
            e.printStackTrace();
        }
        catch (SAXException e) {
            logger.info("cannot parse xml file");
            e.printStackTrace();
        }

        return null;

    }
}
