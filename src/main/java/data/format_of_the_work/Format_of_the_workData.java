package data.format_of_the_work;

public enum Format_of_the_workData {

    FLEXIBLE("Гибкий график"),
    REMOTE("Удаленно");

    private String name;

    Format_of_the_workData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
