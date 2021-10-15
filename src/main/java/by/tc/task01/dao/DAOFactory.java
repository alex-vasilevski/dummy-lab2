package by.tc.task01.dao;



import by.tc.task01.infastructure.ApplianceDaoProxyInvocationHandler;

import java.lang.reflect.Proxy;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final ApplianceDAO applianceDAO =
			(ApplianceDAO) Proxy.newProxyInstance(ApplianceDAO.class.getClassLoader(), new Class[]{ApplianceDAO.class}, new ApplianceDaoProxyInvocationHandler());


	private DAOFactory() {
	}

	public ApplianceDAO getApplianceDAO() {
		return applianceDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
