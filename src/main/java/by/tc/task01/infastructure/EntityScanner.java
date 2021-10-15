package by.tc.task01.infastructure;

import by.tc.task01.entity.Appliance;
import org.reflections.Reflections;

import java.util.Set;

public class EntityScanner {
    public Set<Class<? extends Appliance>> scan(){
        Reflections reflections = new Reflections("by.tc.task01");
        return reflections.getSubTypesOf(Appliance.class);
    }
}
