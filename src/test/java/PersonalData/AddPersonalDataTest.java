package PersonalData;

import com.github.javafaker.Faker;
import components.Header;
import components.PersonalDataPage;
import components.popups.AuthPopups;
import data.cities.ICityData;
import data.cities.RussiaCityData;
import data.english.EnglishLevelData;
import data.fieldData.InputFieldData;
import data.formatOfTheWork.FormatOfTheWorkData;
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

public class AddPersonalDataTest {

    private Logger logger = (Logger) LogManager.getLogger(AddPersonalDataTest.class);

    private WebDriver driver;
    protected Faker faker = new Faker();
    String fakerName;
    String fakerNamelatin;
    String fakerLname;
    String fakerLnamelatin;

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

        personalData.switchWorkFormat(true, FormatOfTheWorkData.REMOTE);
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

        new MainPage(driver).open("/");
        logger.info("Waiting marker tel");

        header.waitMarkerTelNumber();
        header.waitSignInBtnIsPresent();
        header.waitSignInBtnToBeClicable();

        logger.info("Check status auth popup");
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

        personalData.assertFieldsNameData("fname", fakerName);
        personalData.assertFieldsNameData("fname_latin", fakerNamelatin);
        personalData.assertFieldsNameData("lname", fakerLname);
        personalData.assertFieldsNameData("lname_latin", fakerLnamelatin);
        personalData.assertFieldsData();

          logger.info("data checked");
        }
    }


