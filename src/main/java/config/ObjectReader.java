package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {

    Properties po;
    FileInputStream fis;

    public ObjectReader() throws IOException {
        po = new Properties();
        fis = new FileInputStream("C:\\Users\\2478589\\Documents\\Tripcost\\ObjectRepository\\object.properties");
        po.load(fis);
    }

    public String getUrl() {
        return po.getProperty("baseUrl");
    }

    public String getCheckIn() {
        return po.getProperty("checkIn");
    }

    public String getCheckOut() {
        return po.getProperty("checkOut");
    }

    public String getCruiseLine() {
        return po.getProperty("cruiseLine");
    }

    public String getShipId() {
        return po.getProperty("shipId");
    }


}
