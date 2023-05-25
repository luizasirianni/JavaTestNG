package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Evidences {

    public void print(WebDriver driver, String name) throws IOException {
        File photo = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //System.out.println("dataHora: " + dataHora);
        FileUtils.copyFile(photo, new File("target/prints/" + name + ".png"));
    }
}
