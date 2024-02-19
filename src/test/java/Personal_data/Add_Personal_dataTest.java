package Personal_data;

import com.github.javafaker.Faker;
import components.Header;
import components.PersonalDataPage;
import components.popups.AuthPopups;
import data.cities.ICityData;
import data.cities.RussiaCityData;
import data.english.EnglishLevelData;
import data.fieldData.InputFieldData;
import data.format_of_the_work.Format_of_the_workData;
import data.gender.GenderData;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Add_Personal_dataTest {

    private Logger logger = (Logger) LogManager.getLogger(Add_Personal_dataTest.class);

    private WebDriver driver;
    protected Faker faker = new Faker();

    @BeforeEach

    public void init() {
        this.driver = new DriverFactory().create();
        logger.info("Start driver");
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Stop driver");
    }

    @Test

    public void savePersonalData() {
        new MainPage(driver).open("/");

        logger.info("Waiting marker tel");
        Header header = new Header(driver);
        header.waitMarkerTelNumber();
        header.waitSignInBtnIsPresent();
        header.waitSignInBtnToBeClicable();

        logger.info("Check status auth popup");
        AuthPopups authPopups = new AuthPopups(driver);
        authPopups.popupShouldNotBeVisible();

        logger.info("Start of test logic. Login in LK");
        header.clickSignInButton();
        authPopups.popupShouldBeVisible();

        authPopups.enterDataEmail();
        authPopups.enterDataPassword();
        authPopups.clickSignInBtnPopups();

        header.checkLogoUser();
        logger.info("Login in LK success. Switch personal data page");
        header.clickPersonalArea();

        PersonalDataPage personalData = new PersonalDataPage(driver);

        personalData.clearFieldsData(InputFieldData.FNAME);
        personalData.clearFieldsData(InputFieldData.FNAMELATIN);
        personalData.clearFieldsData(InputFieldData.LNAME);
        personalData.clearFieldsData(InputFieldData.LNAMELATIN);
        personalData.clearFieldsData(InputFieldData.BLOGNAME);
        personalData.clearFieldsData(InputFieldData.DATEOFBRTH);
        logger.info("Clear fields success");

        personalData.addNewDataFields(InputFieldData.FNAME, faker.name().firstName());
        personalData.addNewDataFields(InputFieldData.FNAMELATIN, faker.name().lastName());
        personalData.addNewDataFields(InputFieldData.LNAME, faker.name().firstName());
        personalData.addNewDataFields(InputFieldData.LNAMELATIN, faker.name().firstName());
        personalData.addNewDataFields(InputFieldData.BLOGNAME, faker.name().lastName());
        personalData.addNewDataFields(InputFieldData.DATEOFBRTH,
                faker.date().birthday().toInstant().atZone(ZoneId.
                        systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        logger.info("Add names and date birthday success");

       ICityData[] cityData = RussiaCityData.values();
        ICityData city = faker.options().nextElement(cityData);

        personalData.selectCountry(city);
        personalData.selectCity(city);
        logger.info("Add country and city success");

        personalData.selectEnglishLevel(EnglishLevelData.BEGINNER);
        logger.info("Add english level success");

        personalData.switchRelocate(true);
        logger.info("Relocate switch success");

        personalData.switchWorkFormat(true, Format_of_the_workData.REMOTE);
        logger.info("Add work format success");


        personalData.selectContactsOne("telegram", "5555");
        logger.info("Add 1 communication method success");
        personalData.selectContactsTwo("habr", "7777");
        logger.info("Add 2 communication method success");


        personalData.selectGender(GenderData.MALE);
        logger.info("Add gender success");

        personalData.addNewDataFields(InputFieldData.COMPANY, faker.company().name());
        personalData.addNewDataFields(InputFieldData.POSITION, faker.company().profession());
        logger.info("Add work information success");





        personalData.clickSavePersonalData();
        logger.info("save data success");
   }

   @Test
    public void checkDataIsEmpty() {
       new MainPage(driver).open("/");

       logger.info("Waiting marker tel");

       Header header = new Header(driver);
       header.waitMarkerTelNumber();
       header.waitSignInBtnIsPresent();
       header.waitSignInBtnToBeClicable();

       logger.info("Check status auth popup");
       AuthPopups authPopups = new AuthPopups(driver);
       authPopups.popupShouldNotBeVisible();

       logger.info("Start of test logic. Login in LK");
       header.clickSignInButton();
       authPopups.popupShouldBeVisible();

       authPopups.enterDataEmail();
       authPopups.enterDataPassword();
       authPopups.clickSignInBtnPopups();

       header.checkLogoUser();
       logger.info("Login in LK success. Switch personal data page");
       header.clickPersonalArea();

       PersonalDataPage personalData = new PersonalDataPage(driver);
       personalData.assertFieldsData(InputFieldData.FNAME);
       personalData.assertFieldsData(InputFieldData.FNAMELATIN);
       personalData.assertFieldsData(InputFieldData.LNAME);
       personalData.assertFieldsData(InputFieldData.LNAMELATIN);
       personalData.assertFieldsData(InputFieldData.BLOGNAME);
       personalData.assertFieldsData(InputFieldData.DATEOFBRTH);

       personalData.checkFieldsDataIsNotEmpty();
       logger.info("data checked");
   }
}


