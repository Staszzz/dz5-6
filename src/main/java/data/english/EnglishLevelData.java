package data.english;

public enum EnglishLevelData {


    BEGINNER("Начальный уровень (Beginner)");

    private String nameEnglishLevel;
    EnglishLevelData(String nameLevelEnglish) {
        this.nameEnglishLevel = nameLevelEnglish;
    }

    public String getEnglishLevel() {
        return nameEnglishLevel;
    }
}


