package POJOs;

public class Address {
    private int addressId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;

    public void setAddressId(int addressId)
    {
        this.addressId = addressId;
    }

    public int getAddressId(){
        return addressId;
    }

    public void setStreet1(String street1){
        this.street1 = street1;
    }

    public String getStreet1(){
        return street1;
    }

    public void setStreet2(String street2){
        this.street2 = street2;
    }

    public String getStreet2(){
        return street2;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }

    public String getZipCode(){
        return zipCode;
    }


}
