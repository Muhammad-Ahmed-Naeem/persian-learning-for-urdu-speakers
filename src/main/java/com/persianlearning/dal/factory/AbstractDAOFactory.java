package com.persianlearning.dal.factory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public abstract class AbstractDAOFactory implements IDAOFactory {
    private static final String CONFIG_FILE = "config.properties";
    private static final String FACTORY_KEY = "dal.factory";
    private static IDAOFactory instance;

    public static synchronized IDAOFactory getInstance() {
        if (instance == null) {
            try {
                Properties props = new Properties();
                InputStream in = AbstractDAOFactory.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
                if (in != null) {
                    props.load(new InputStreamReader(in, StandardCharsets.UTF_8));
                    in.close();
                }
                String className = props.getProperty(FACTORY_KEY);
                if (className != null && !className.trim().isEmpty()) {
                    Class<?> clazz = Class.forName(className.trim());
                    instance = (IDAOFactory) clazz.newInstance();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
