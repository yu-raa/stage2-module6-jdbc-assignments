package jdbc;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Properties;

@Getter
@Setter
public class CustomDataSource {
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private CustomDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.name = name;
        this.password = password;
    }

    public static CustomDataSource getInstance() {
        if (instance == null) {
            Properties prop = new Properties();
            try {
                InputStream inputStream = new FileInputStream("src/main/resources/app.properties");
                prop.load(inputStream);
            } catch (IOException e) {
                return null;
            }
            instance = new CustomDataSource
                    (prop.getProperty("postgres.driver", "localhost"),
                            prop.getProperty("postgres.url", ""),
                            prop.getProperty("postgres.password", ""),
                            prop.getProperty("postgres.name", ""));
        }
        return instance;
    }
}
