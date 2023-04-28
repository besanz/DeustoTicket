package data;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class DBConfig {

    private static PersistenceManagerFactory pmfInstance;

    public static PersistenceManagerFactory getPMF() {
        if (pmfInstance == null) {
            pmfInstance = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        }
        return pmfInstance;
    }

    public static PersistenceManager getPersistenceManager() {
        return getPMF().getPersistenceManager();
    }
}
