package fr.isima.EJBContainer.interceptors.log;

public interface ILogger {
	public boolean contains(String s);
	public void log(String s);
}
