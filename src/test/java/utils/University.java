package utils;

import javax.annotation.Nullable;

public class University  {
    private String name;
    private String[] domains;
    private String[] web_pages;
    private String country;
    private String alpha_two_code;
    private @Nullable String state_province;

    public University() {};

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

}
