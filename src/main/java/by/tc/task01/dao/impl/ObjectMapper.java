package by.tc.task01.dao.impl;

import by.tc.task01.entity.Appliance;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class ObjectMapper {

    public Map<Class<? extends Appliance>, List<Appliance>> createMapping(NodeList childNode){

        Map<String, Class<? extends Appliance>> mapping = scanEntities();
        Map<Class<? extends Appliance>, List<Appliance>> result = new HashMap<>();

        NodeList childNodes = childNode.item(0).getChildNodes();

        int length = childNodes.getLength();


        for(int index = 0; index < length; ++index){
            Node item = childNodes.item(index);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;

                NodeList groupName = element.getElementsByTagName("group_name");
                String groupNameValue = groupName.item(1).getNodeValue();

                Class<? extends Appliance> entityClass = mapping.get(groupNameValue);
                if(entityClass == null){
                    continue;
                }

                Appliance appliance = createEntityInstance(entityClass);
                initEntity(entityClass, appliance, element.getAttributes());

                if(result.containsKey(entityClass)){
                    result.get(entityClass).add(appliance);
                }
                else{
                    result.put(entityClass, List.of(appliance));
                }
            }


        }

        return result;
    }

    private Map<String, Class<? extends Appliance>> scanEntities(){
        Reflections reflections = new Reflections("by.tc.task01.entity");
        Set<Class<? extends Appliance>> subtypes = reflections.getSubTypesOf(Appliance.class);

        Map<String, Class<? extends Appliance>> map = new HashMap<>();
        for (Class<? extends Appliance> applianceClass : subtypes) {
            map.put(applianceClass.getSimpleName(), applianceClass);
        }

        return map;
    }

    private Appliance createEntityInstance(Class<? extends Appliance> entityClass){
        Set<Constructor> constructors = ReflectionUtils.getConstructors(entityClass);
        for (Constructor constructor : constructors) {
            int parameterCount = constructor.getParameterCount();
            if(parameterCount == 0) {
                try {
                    constructor.setAccessible(true);
                    return (Appliance) constructor.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Appliance initEntity(Class<? extends Appliance> entityClass, Appliance instance, NamedNodeMap attributes){
        Field[] declaredFields = entityClass.getDeclaredFields();
        for (Field field : declaredFields){
            Object value = attributes.getNamedItem(field.getName());
            field.setAccessible(true);
            try {
                field.set(instance, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }
}
