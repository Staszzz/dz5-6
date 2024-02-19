package data.fieldData;

public enum InputFieldData {

    FNAME("fname"),
    FNAMELATIN("fname_latin"),
    LNAME("lname"),
    LNAMELATIN("lname_latin"),
    BLOGNAME("blog_name"),
    DATEOFBRTH("date_of_birth"),
    COMPANY("company"),
    POSITION("work"),
   TELEGRAM("Тelegram"),
    HABR("Habr");


    private String name;

    InputFieldData (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
