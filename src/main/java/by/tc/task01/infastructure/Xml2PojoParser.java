package by.tc.task01.infastructure;

import by.tc.task01.entity.Appliance;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Xml2PojoParser {

    private EntityFactory entityFactory;
    private Set<Class<? extends Appliance>> entityClasses;

    public Xml2PojoParser(EntityFactory entityFactory, Set<Class<? extends Appliance>> entityClasses) {
        this.entityFactory = entityFactory;
        this.entityClasses = entityClasses;
    }

    public List<Appliance> parse(String xmlFileName){
        List<Appliance> appliances = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFileName));

            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList nList = document.getElementsByTagName("appliance");
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node node = nList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    Stream<Class<? extends Appliance>> classStream = entityClasses
                            .stream()
                            .filter(aClass -> aClass.getSimpleName().equalsIgnoreCase(name));

                    classStream.findFirst().ifPresent(aClass -> {
                        Appliance appliance = entityFactory.createEntity(eElement, aClass);
                        appliances.add(appliance);
                    });
                }
            }
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }

        return appliances;
    }
}
