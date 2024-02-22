package data.cities;

public enum CountriesData {

    RUSSIA("Россия");

    private String nameCountry;

    CountriesData(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getCountry() {
        return  this.nameCountry;
    }
}
