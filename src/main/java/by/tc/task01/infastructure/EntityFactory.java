package by.tc.task01.infastructure;

import by.tc.task01.entity.Appliance;
import org.w3c.dom.Element;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityFactory {

    private final Set<Class<? extends Appliance>> entityClasses;
    private final StringTransformer transformer;
    private final Map<Class<? extends Appliance>, Map<String, Method>> class2XmlProps;

    public EntityFactory(Set<Class<? extends Appliance>> entityClasses, StringTransformer transformer) {
        this.entityClasses = entityClasses;
        this.transformer = transformer;
        class2XmlProps = new HashMap<>();
    }

    public void init(){
        for (Class<? extends  Appliance> entityClass : entityClasses){
            Set<Method> methods = Arrays.stream(entityClass.getMethods()).filter(method -> method.getName().contains("set")).collect(Collectors.toSet());
            Map<String, Method> xmlProps2setterMethods = new HashMap<>();
            for (Method method : methods){
                String preparedName = method.getName().substring(3, method.getName().length());
                xmlProps2setterMethods.put(transformer.transformUpperCamel2LowerUnderscore(preparedName), method);
            }
            class2XmlProps.put(entityClass, xmlProps2setterMethods);
        }
    }

    public Appliance createEntity(Element element, Class<? extends Appliance> aClass){
        Map<String, Method> xmlProps2setterMethods = class2XmlProps.get(aClass);

        try {
            Appliance appliance = aClass.getConstructor().newInstance();

            for(String key : xmlProps2setterMethods.keySet()){
                String valString = element.getElementsByTagName(key).item(0).getTextContent();
                Method setterMethod = xmlProps2setterMethods.get(key);
                setterMethod.setAccessible(true);
                if(setterMethod.getParameterTypes()[0].isAssignableFrom(Double.class)){
                     setterMethod.invoke(appliance, Double.parseDouble(valString));
                }
                else if(setterMethod.getParameterTypes()[0].isAssignableFrom(Integer.class)){
                    setterMethod.invoke(appliance, Integer.parseInt(valString));
                }
                else {
                    setterMethod.invoke(appliance, valString);
                }
            }

            return appliance;
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
