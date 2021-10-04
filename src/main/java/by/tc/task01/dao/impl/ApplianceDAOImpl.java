package by.tc.task01.dao.impl;

import by.tc.task01.dao.ApplianceDAO;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplianceDAOImpl implements ApplianceDAO{

	private static final String DATASOURCE = "src/main/resources/datasource.xml";
	private Map<Class<? extends Appliance>, List<Appliance>> parsed;
	private XmlParser xmlParser;


	public ApplianceDAOImpl() {
		this.xmlParser = new XmlParser();
		parsed = xmlParser.parse(new File(DATASOURCE));
	}

	@Override
	public Appliance find(Criteria request) {
		Map<String, Object> searchCriteriaMapping = request.getCriteria();

		for (Class<? extends Appliance> entityClass : parsed.keySet()) {
			Field[] declaredFields = entityClass.getDeclaredFields();
			Set<String> fieldNames = Arrays
					.stream(declaredFields)
					.map(Field::getName)
					.collect(Collectors.toSet());

			Set<String> criteria = searchCriteriaMapping.keySet();

			if(fieldNames.containsAll(criteria)){
				Predicate<Appliance> predicate = appliance -> {
					boolean result = true;
					for (Field declaredField : declaredFields) {
						for(String c : criteria){
							try {
								if(declaredField.getName().equals(c))
									result = result & declaredField.get(appliance).equals(searchCriteriaMapping.get(c));
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}

					}
					return result;
				};


				Stream<Appliance> applianceStream = parsed.get(entityClass).stream().filter(predicate);
				return applianceStream.findFirst().get();

			}
		}

		return null;
	}


	// you may add your own code here

}


//you may add your own new classes