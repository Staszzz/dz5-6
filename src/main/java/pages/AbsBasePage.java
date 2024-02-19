package pages;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;

import java.util.List;


    public abstract class AbsBasePage extends AbsCommon {
        private final String BASE_URL = System.getProperty("base.url");
        public AbsBasePage(WebDriver driver) {
            super(driver);
        }

        public void open() {
            driver.get(BASE_URL);
        }
        public void open(String path) {
            driver.get(BASE_URL + path);
        }
    }
