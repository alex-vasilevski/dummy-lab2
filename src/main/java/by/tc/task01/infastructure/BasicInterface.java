package by.tc.task01.infastructure;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;

import java.util.Optional;

public interface BasicInterface {
    Optional<Appliance> find(Criteria criteria);
}
