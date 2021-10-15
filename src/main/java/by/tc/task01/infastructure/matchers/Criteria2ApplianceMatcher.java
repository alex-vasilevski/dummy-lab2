package by.tc.task01.infastructure.matchers;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;

public interface Criteria2ApplianceMatcher {
    Boolean isMatching(Criteria criteria, Appliance appliance);
}
