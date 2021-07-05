package com.mobile.template.utils;

import com.automation.platform.config.Configvariable;
import com.automation.platform.filehandling.CsvUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class HelperMethods {

    private static final Logger logger = LoggerFactory.getLogger(HelperMethods.class);

    private Configvariable configvariable = new Configvariable();
    private CsvUtils csvUtils = new CsvUtils();

    public void loadLocalizationFile(String lbu, String language, String platform) {
        String filePath = "/locators/" + lbu + "/" + language + "_" + platform.toLowerCase() + ".csv";
        loadCSVDataIntoGlobalMap(filePath, '=');
//        InputStream initialStream = getClass().getResourceAsStream("/locators/" + lbu + "/" + language + "_" + platform.toLowerCase() + ".properties");
//        configvariable.propertiesFileLoad(initialStream);
    }

    public void loadCSVDataIntoGlobalMap(String fileName, char separator) {
        InputStream initialStream = getClass().getResourceAsStream(fileName);
        List<String[]> allCsvData = csvUtils.csvInputStreamReader(initialStream, separator);
        // print Data
        for (String[] row : allCsvData) {
            configvariable.setStringVariable(row[1], row[0]);
        }
    }

}
