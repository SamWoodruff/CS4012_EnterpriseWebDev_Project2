package POJOs;

public class RegisterForm {
    private String logonId;
    private String password;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
    private String method;

    public void setMethod(String method){this.method=method;}

    public String getMethod(){return method;}

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

    public void setLogonId(String logonId){this.logonId = logonId; }
    public String getLogonId(){return logonId;}

    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}


}

