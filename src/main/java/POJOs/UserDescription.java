package POJOs;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;

public class UserDescription {
    private int usersId;
    private Date birthday;
    private String phone;
    private String cellPhone;
    private String timeZone;
    private Blob pic;
    private byte[] picData;
    private InputStream inputStream;
    private String fileType;

    public String getFileType(){return fileType;}

    public void setFileType(String fileType){this.fileType=fileType;}

    public void setInputStream(InputStream inputStream){this.inputStream = inputStream;}

    public InputStream getInputStream(){return inputStream;}


    public byte[] getPicData() {
        return picData;
    }

    public void setPicData(byte[] picData) {
        this.picData = picData;
    }

    public void setUsersId(int usersId){this.usersId=usersId;}

    public int getUsersId(){return usersId;}

    public void setBirthday(Date birthday){this.birthday=birthday;}

    public Date getBirthday(){return birthday;}

    public void setPhone(String phone){this.phone = phone;}

    public String getPhone(){return cellPhone;}

    public void setcellPhone(String cellPhone){this.cellPhone=cellPhone;}

    public String getCellPhone(){return cellPhone;}

    public void setTimeZone(String timeZone){this.timeZone=timeZone;}

    public String getTimeZone(){return timeZone;}

    public void setPic(Blob pic){this.pic = pic;}

    public Blob getPic(){return pic;}


}
