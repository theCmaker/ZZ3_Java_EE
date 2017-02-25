package fr.isima.EJBContainer.testclasses.log;

import fr.isima.EJBContainer.interceptors.log.Log;

public interface IMyLogService {
	@Log
	public void m();
}
