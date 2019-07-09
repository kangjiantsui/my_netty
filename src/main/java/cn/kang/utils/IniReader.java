package cn.kang.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class IniReader {
    public Properties ini = null;

    public IniReader(String fileString) {
        File file = new File(fileString);
        try {
            ini = new Properties();
            ini.load(new FileInputStream(file));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIniKey(String key) {
        if (!ini.containsKey(key)) {
            return null;
        }
        return ini.get(key).toString();
    }
}
