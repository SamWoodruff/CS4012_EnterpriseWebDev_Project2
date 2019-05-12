package POJOs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.inject.Scope;

public class User {

    private int userId;
    private String logonId;
    private String password;
    private int addressId;

    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){return userId;}

    public void setLogonId(String logonId){this.logonId = logonId; }
    public String getLogonId(){return logonId;}

    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}

    public void setAddressId(int addressId){this.addressId = addressId;}
    public int getAddressId(){return addressId;}

}
