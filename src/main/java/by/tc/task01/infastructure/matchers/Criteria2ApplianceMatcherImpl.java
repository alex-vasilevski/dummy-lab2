package by.tc.task01.infastructure.matchers;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.infastructure.StringTransformer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Criteria2ApplianceMatcherImpl implements Criteria2ApplianceMatcher {

    private final StringTransformer stringTransformer;

    public Criteria2ApplianceMatcherImpl(StringTransformer stringTransformer) {
        this.stringTransformer = stringTransformer;
    }

    @Override
    public Boolean isMatching(Criteria criteria, Appliance appliance) {
        if (!isMatchable(criteria.getGroupSearchName(), appliance.getClass().getSimpleName())){
            return false;
        }

        Set<String> criteriaParams = criteria.getCriteriaFieldNames();
        Set<Method> getters = new HashSet<>();
        for(String criteriaParam : criteriaParams){
            Arrays
                    .stream(appliance.getClass().getDeclaredMethods())
                    .filter(method -> method.getName().contains("get".concat(stringTransformer.transformUpperUnderscore2UpperCamel(criteriaParam))))
                    .forEach(getters::add);
        }

        boolean result = true;

        for (Method getter : getters){
            try {
                Object actualValue = getter.invoke(appliance);
                String getterName = getter.getName();

                Object expectedValue = criteria.getValueByCriteriaField(stringTransformer.transformLowerCamel2UpperUnderscore(getterName.substring(3, getterName.length())));
                if (actualValue.getClass().isAssignableFrom(String.class) && expectedValue.getClass().isAssignableFrom(String.class)){
                    actualValue = ((String) actualValue).toLowerCase();
                    expectedValue = ((String) expectedValue).toLowerCase();
                }

                result &= expectedValue.equals(actualValue);


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    private boolean isMatchable(String sourceClassName, String targetClassName){
        return sourceClassName.equals(targetClassName);
    }
}
