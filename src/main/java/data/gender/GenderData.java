package data.gender;


public enum GenderData {
    MALE("m");
    private String name;
    GenderData(String  name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}