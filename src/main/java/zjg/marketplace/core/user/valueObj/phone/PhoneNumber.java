package zjg.marketplace.core.user.valueObj.phone;

public class PhoneNumber {
    private String countryCode;
    /// DDD
    private String areaCode;
    /// Ex: 12345-6789
    private String number;

    // Getter
    public String getCountryCode() {
        return countryCode;
    }
    public String getAreaCode() {
        return areaCode;
    }
    public String getNumber() {
        return number;
    }

    // Setter
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
