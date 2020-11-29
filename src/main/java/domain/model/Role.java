package domain.model;

public enum Role {
    ADMIN("Admin"), CUSTOMER("Customer");

    private String stringValue;

    private Role(String stringValue) {this.stringValue = stringValue;}

    public String getStringValue() {return stringValue;}
}
