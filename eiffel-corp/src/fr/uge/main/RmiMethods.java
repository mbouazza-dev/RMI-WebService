package fr.uge.main;

import java.rmi.RemoteException;

/**
 * Permet de pouvoir appeler les méthodes de IStore dans une lambda 
 * sans avoir besoin de mettre un try/catch.
 * 
 * @author Florian
 *
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface RmiMethods<T, U> {
	void accept(T t, U u) throws RemoteException;
}
