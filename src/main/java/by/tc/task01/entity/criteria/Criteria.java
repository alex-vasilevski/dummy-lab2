package by.tc.task01.entity.criteria;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Criteria {

	private String groupSearchName;
	private Map<String, Object> criteria = new HashMap<String, Object>();

	public Criteria(String groupSearchName) {
		this.groupSearchName = groupSearchName;
	}
	
	public String getGroupSearchName() {
		return groupSearchName;
	}

	public Map<String, Object> getCriteria() {
		return criteria;
	}

	public void add(String searchCriteria, Object value) {
		this.criteria.put(searchCriteria, value);
	}

	public Set<String> getCriteriaFieldNames(){
		return this.getCriteria().keySet();
	}

	public Object getValueByCriteriaField( String criteriaField){
		return this.criteria.get(criteriaField);
	}


}
