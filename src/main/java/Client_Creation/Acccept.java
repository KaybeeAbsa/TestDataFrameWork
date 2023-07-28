package Client_Creation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Acccept {


public static void main(String[] args){

    String Paragraph = "Karabo serope karabo. Karabo.";

    String[] outPut = Paragraph.split("\\.");

    System.out.println(outPut.length);


    for(int x = 0; x < outPut.length; x ++)
    {
        System.out.println("Words: " + x + outPut[x]);
    }

    ArrayList<String> map = new ArrayList<String>();
    ArrayList<String> words = new ArrayList<String>(Arrays.asList(outPut));
    int count = 0;
    for (String word : words) {
        for (String temp : outPut) {
            if (word.equals(temp)) {
                ++count;
            }
        }
        map.add(word);
        count = 0;
    }

    System.out.println(map);

}
}
