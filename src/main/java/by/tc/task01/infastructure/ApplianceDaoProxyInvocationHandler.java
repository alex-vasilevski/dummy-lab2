package by.tc.task01.infastructure;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.infastructure.matchers.Criteria2ApplianceMatcher;
import by.tc.task01.infastructure.matchers.Criteria2ApplianceMatcherImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class ApplianceDaoProxyInvocationHandler implements InvocationHandler{

    private final EntityScanner scanner;
    private final StringTransformer stringTransformer;
    private final Criteria2ApplianceMatcher criteria2ApplianceMatcher;

    private boolean isInitialised;
    private List<Appliance> appliances;

    public ApplianceDaoProxyInvocationHandler() {
        this.stringTransformer = new StringTransformer();
        this.scanner = new EntityScanner();
        this.isInitialised = false;
        this.criteria2ApplianceMatcher = new Criteria2ApplianceMatcherImpl(stringTransformer);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!isInitialised){
            init();
        }
        if(method.getName().equalsIgnoreCase("find")){
            Criteria criteria = (Criteria) args[0];
            String groupName = criteria.getGroupSearchName();

            return appliances
                    .stream()
                    .filter(appliance -> appliance.getClass().getSimpleName().equalsIgnoreCase(groupName))
                    .filter(appliance -> criteria2ApplianceMatcher.isMatching(criteria, appliance))
                    .findFirst();


        }

        return Optional.empty();
    }

    private void init(){
        Set<Class<? extends Appliance>> entityClasses = scanner.scan();
        EntityFactory entityFactory = new EntityFactory(entityClasses, stringTransformer);
        entityFactory.init();
        Xml2PojoParser parser = new Xml2PojoParser(entityFactory, entityClasses);

        this.appliances = parser.parse("src/main/resources/datasource.xml");
        this.isInitialised = true;
    }

}
