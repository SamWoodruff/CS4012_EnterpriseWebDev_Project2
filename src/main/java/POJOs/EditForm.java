package POJOs;

import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class EditForm {
    private String birthday;
    private String cellPhone;
    private String phone;
    private String timeZone;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
    private MultipartFile picture;
    Date date;

    public String getZipCode(){return zipCode;}
    public void setZipCode(String zipCode){this.zipCode=zipCode;}

    public String getState(){return state;}
    public void setState(String state){this.state=state;}

    public String getCity(){return city;}
    public void setCity(String city){this.city =city;}

    public void setStreet2(String street2){this.street2=street2;}
    public String getStreet2(){return street2;}

    public String getStreet1(){return street1;}
    public void setStreet1(String street1){
        this.street1 = street1;
    }

    public void setPicture(MultipartFile picture){this.picture = picture;}

    public MultipartFile getPicture(){return picture;}

    public void setTimeZone(String timeZone){
        this.timeZone = timeZone;
    }

    public String getTimeZone(){
        return timeZone;
    }

    public void setBirthday(String birthday){
        this.birthday=birthday;

    }

    public String getBirthday(){

        return birthday;}

    public void setPhone(String phone){this.phone = phone;}

    public String getPhone(){return cellPhone;}

    public void setCellPhone(String cellPhone){this.cellPhone=cellPhone;}

    public String getCellPhone(){return cellPhone;}

    public Date getDate(){
        return date;
    }

}
