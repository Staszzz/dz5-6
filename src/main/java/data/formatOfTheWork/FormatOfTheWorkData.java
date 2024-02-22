package data.formatOfTheWork;

public enum FormatOfTheWorkData {

    FLEXIBLE("Гибкий график"),
    REMOTE("Удаленно");

    private String name;

    FormatOfTheWorkData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
