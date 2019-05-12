package POJOs;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;

public class DAO {

    private static DAO instance = null;

    //use getinstance to see if there and if is will return the current instance
    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    private static String ADD_USER = "INSERT INTO USERS (LOGON_ID,PASSWORD ,ADDRESS_ID) VALUES (?,?,?)";
    private static String ADD_ADDRESS = "INSERT INTO ADDRESS (STREET_1,STREET_2,CITY,STATE,ZIP_CODE) VALUES (?,?,?,?,?)";
    private static String ADD_EDUCATION_HISTORY = "INSERT INTO EDUCATIONHISTORY (USERS_ID,DEGREE_TYPE,DEGREE_DISCIPLINE,YEARS_ACHEIVED) VALUES (?,?,?,?)";
    private static String ADD_WORK_HISTORY = "INSERT INTO WORKHISTORY (USERS_ID,JOB_TITLE,COMPANY_NAME,YEARS_OF_SERVICE) VALUES (?,?,?,?)";
    private static String ADD_USER_DESCRIPTION ="INSERT INTO USERDESC(USERS_ID,BIRTHDAY,PHONE,CELL_PHONE,TIME_ZONE,PROFILE_IMAGE) VALUES (?,?,?,?,?,?)";
    private static String UPDATE_ADDRESS = "UPDATE ADDRESS SET STREET_1=?, STREET_2=?, CITY=?, STATE=?, ZIP_CODE=? WHERE ADDRESS_ID=?";
    private static String UPDATE_USER_DESCRIPTION ="UPDATE USERDESC SET BIRTHDAY=?, PHONE=?, CELL_PHONE=?, TIME_ZONE=?,PROFILE_IMAGE=? WHERE USERS_ID=?";
    private static String GET_ALL_USERS = "SELECT * FROM USERS";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/enterprise";
    private static String USERNAME = "root";
    private static String PASSWORD = "";

    //select courseid from schedule where student id is student_id
    //select coursename from schedule where courseid is courseid

    Connection connection = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public Boolean verifyUser(User user){
        boolean isUser = false;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_USERS);
            while(resultSet.next()){
                if(resultSet.getString("PASSWORD").equals(user.getPassword())&&resultSet.getString("PASSWORD").equals(user.getPassword())){
                    isUser = true;
                }
            }
            statement.close();
            connection.close();
            resultSet.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUser;
    }

    public void addUser(User user,Address address) {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(ADD_USER,Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, user.getLogonId());
            prepStatement.setString(2, user.getPassword());
            prepStatement.setInt(3, address.getAddressId());
            prepStatement.executeUpdate();

            //get the generated id for the user
            resultSet = prepStatement.getGeneratedKeys();
            resultSet.next();
            user.setUserId(resultSet.getInt(1));
            user.setAddressId(address.getAddressId());
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAddress(Address address) {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(ADD_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, address.getStreet1());
            prepStatement.setString(2, address.getStreet2());
            prepStatement.setString(3, address.getCity());
            prepStatement.setString(4, address.getState());
            prepStatement.setString(5, address.getZipCode());
            prepStatement.executeUpdate();

            //get the generated id for the user
            resultSet = prepStatement.getGeneratedKeys();
            resultSet.next();
            address.setAddressId(resultSet.getInt(1));
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEducationHistory(EducationHistory educationHistory,User user){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(ADD_EDUCATION_HISTORY, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setInt(1, user.getUserId());
            prepStatement.setString(2, educationHistory.getDegreeType());
            prepStatement.setString(3, educationHistory.getDegreeDiscipline());
            prepStatement.setInt(4, educationHistory.getYearsAchieved());
            prepStatement.executeUpdate();

            //get the generated id
            resultSet = prepStatement.getGeneratedKeys();
            resultSet.next();
            educationHistory.setEducationHistoryId(resultSet.getInt(1));
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addWorkHistory(WorkHistory workHistory, User user){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(ADD_WORK_HISTORY, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setInt(1, user.getUserId());
            prepStatement.setString(2, workHistory.getJobTitle());
            prepStatement.setString(3, workHistory.getCompanyName());
            prepStatement.setInt(4, workHistory.getYearsOfService());
            prepStatement.executeUpdate();
            //get the generated id
            resultSet = prepStatement.getGeneratedKeys();
            resultSet.next();
            workHistory.setWorkHistoryId(resultSet.getInt(1));
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addUserDescription(UserDescription userDescription, User user){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(ADD_USER_DESCRIPTION, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setInt(1, user.getUserId());
            prepStatement.setDate(2, userDescription.getBirthday());
            prepStatement.setString(3, userDescription.getPhone());
            prepStatement.setString(4,userDescription.getCellPhone());
            prepStatement.setString(5,userDescription.getTimeZone());
            prepStatement.setBlob(6,userDescription.getPic());
            prepStatement.executeUpdate();

            userDescription.setUsersId(user.getUserId());
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateUserDescription(UserDescription userDescription, User user){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(UPDATE_USER_DESCRIPTION);
            prepStatement.setDate(1, userDescription.getBirthday());
            prepStatement.setString(2, userDescription.getPhone());
            prepStatement.setString(3,userDescription.getCellPhone());
            prepStatement.setString(4,userDescription.getTimeZone());
            prepStatement.setBlob(5,userDescription.getInputStream());
            prepStatement.setInt(6,user.getUserId());
            prepStatement.executeUpdate();

            userDescription.setUsersId(user.getUserId());
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(Address address,User user){

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(UPDATE_ADDRESS);
            prepStatement.setString(1, address.getStreet1());
            prepStatement.setString(2, address.getStreet2());
            prepStatement.setString(3, address.getCity());
            prepStatement.setString(4, address.getState());
            prepStatement.setString(5, address.getZipCode());
            prepStatement.setInt(6,user.getAddressId());
            prepStatement.executeUpdate();
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<EducationHistory> getAllEducation(User user){
        ArrayList<EducationHistory> allEducationHistory = new ArrayList<EducationHistory>();
        ArrayList<Integer> historyIds = new ArrayList<Integer>();
        String sql = "SELECT EDUCATIONHISTORY_ID FROM EDUCATIONHISTORY WHERE USERS_ID = " + user.getUserId();

        try{
            Class.forName(DRIVER);

            //get ids from database
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery(sql);

            //store ids in arraylist
            while(resultSet.next()){
                historyIds.add(resultSet.getInt(1));
            }

            resultSet.close();

            for(int i = 0; i < historyIds.size(); i++) {
                String sql2 = "SELECT * FROM EDUCATIONHISTORY WHERE EDUCATIONHISTORY_ID = " + historyIds.get(i);
                prepStatement = connection.prepareStatement(sql2);
                ResultSet resultSet2 = prepStatement.executeQuery(sql2);

                resultSet2.next();

                EducationHistory history = new EducationHistory();
                history.setEducationHistoryId(resultSet2.getInt(1));
                history.setUsersId(resultSet2.getInt(2));
                history.setDegreeType(resultSet2.getString(3));
                history.setDegreeDiscipline(resultSet2.getString(4));
                history.setYearsAchieved(resultSet2.getInt(5));

                allEducationHistory.add(history);
            }

            resultSet.close();
            prepStatement.close();
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allEducationHistory;

    }

    public ArrayList<WorkHistory> getAllWork(User user) {
        ArrayList<WorkHistory> allWorkHistory = new ArrayList<WorkHistory>();
        ArrayList<Integer> workIds = new ArrayList<Integer>();
        String sql = "SELECT WORKHISTORY_ID FROM WORKHISTORY WHERE USERS_ID = " + user.getUserId();

        try{
            Class.forName(DRIVER);

            //get ids from database
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery(sql);

            //store ids in arraylist
            while(resultSet.next()){
                workIds.add(resultSet.getInt(1));
            }

            resultSet.close();

            for(int i = 0; i < workIds.size(); i++) {
                String sql2 = "SELECT * FROM WORKHISTORY WHERE WORKHISTORY_ID = " + workIds.get(i);
                prepStatement = connection.prepareStatement(sql2);
                ResultSet resultSet2 = prepStatement.executeQuery(sql2);

                resultSet2.next();

                WorkHistory history = new WorkHistory();
                history.setWorkHistoryId(resultSet2.getInt(1));
                history.setUsersId(resultSet2.getInt(2));
                history.setJobTitle(resultSet2.getString(3));
                history.setCompanyName(resultSet2.getString(4));
                history.setYearsOfService(resultSet2.getInt(5));

                allWorkHistory.add(history);
            }

            resultSet.close();
            prepStatement.close();
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allWorkHistory;

    }

    public User getCurrentUser(User user){
        String sql = "SELECT * FROM USERS WHERE (LOGON_ID = " + user.getLogonId() + " AND PASSWORD = " +user.getPassword() +")";

        User user1 = new User();
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_USERS);
            while(resultSet.next()){
                if(resultSet.getString("PASSWORD").equals(user.getPassword())&&resultSet.getString("PASSWORD").equals(user.getPassword())){
                    user1.setUserId(resultSet.getInt(1));
                    user1.setLogonId(resultSet.getString(2));
                    user1.setPassword(resultSet.getString(3));
                    user1.setAddressId(resultSet.getInt(4));
                }
            }
            statement.close();
            connection.close();
            resultSet.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user1;
    }

    public Address getCurrentAddress(User user){
        String sql = "SELECT ADDRESS_ID FROM USERS WHERE USERS_ID=" + user.getUserId();
        Address address= new Address();

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery(sql);
            resultSet.next();
            //This is the addressid from the user table
            int addressId = resultSet.getInt(1);
            //find address with it
            String sql2 = "SELECT * FROM ADDRESS WHERE ADDRESS_ID=" + addressId;
            prepStatement = connection.prepareStatement(sql2);
            ResultSet resultSet2 = prepStatement.executeQuery(sql2);
            resultSet2.next();
            address.setAddressId(resultSet2.getInt(1));
            address.setStreet1(resultSet2.getString(2));
            address.setStreet2(resultSet2.getString(3));
            address.setCity(resultSet2.getString(4));
            address.setState(resultSet2.getString(5));
            address.setZipCode(resultSet2.getString(6));
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return address;
    }

    public UserDescription getCurrentDescription(User user){
        String sql = "SELECT * FROM USERDESC WHERE USERS_ID =" + user.getUserId();
        UserDescription desc = new UserDescription();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery(sql);
            resultSet.next();
            desc.setUsersId(resultSet.getInt(1));
            desc.setBirthday(resultSet.getDate(2));
            desc.setPhone(resultSet.getString(3));
            desc.setcellPhone(resultSet.getString(4));
            desc.setTimeZone(resultSet.getString(5));
            desc.setPic(resultSet.getBlob(6));
            if(desc.getPic()!=null) {
                int length = (int) desc.getPic().length();
                byte[] arr = desc.getPic().getBytes(1, length);
                desc.setPicData(arr);
            }
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return desc;
    }

    public WorkHistory getWorkById(int id){
        String sql = "SELECT * FROM WORKHISTORY WHERE WORKHISTORY_ID =" + id;
        WorkHistory work = new WorkHistory();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery(sql);
            resultSet.next();

            work.setWorkHistoryId(resultSet.getInt(1));
            work.setUsersId(resultSet.getInt(2));
            work.setJobTitle(resultSet.getString(3));
            work.setCompanyName(resultSet.getString(4));
            work.setYearsOfService(resultSet.getInt(5));

            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return work;
    }

    public EducationHistory getEducationById(int id){
        String sql = "SELECT * FROM EDUCATIONHISTORY WHERE EDUCATIONHISTORY_ID =" + id;
        EducationHistory education = new EducationHistory();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery(sql);
            resultSet.next();

            education.setEducationHistoryId(resultSet.getInt(1));
            education.setUsersId(resultSet.getInt(2));
            education.setDegreeType(resultSet.getString(3));
            education.setDegreeDiscipline(resultSet.getString(4));
            education.setYearsAchieved(resultSet.getInt(4));

            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return education;
    }

    public void updateWork(WorkHistory work, int id){
        String sql ="UPDATE WORKHISTORY SET JOB_TITLE=?, COMPANY_NAME=?, YEARS_OF_SERVICE=? WHERE WORKHISTORY_ID = " + id ;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, work.getJobTitle());
            prepStatement.setString(2, work.getCompanyName());
            prepStatement.setInt(3, work.getYearsOfService());
            prepStatement.executeUpdate();
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEducation(EducationHistory edu, int id){
        String sql ="UPDATE EDUCATIONHISTORY SET DEGREE_TYPE=?, DEGREE_DISCIPLINE=?, YEARS_ACHEIVED=? WHERE EDUCATIONHISTORY_ID= " + id;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1,edu.getDegreeType());
            prepStatement.setString(2,edu.getDegreeDiscipline());
            prepStatement.setInt(3,edu.getYearsAchieved());
            prepStatement.executeUpdate();
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWork(int id){
        String sql="DELETE FROM WORKHISTORY WHERE WORKHISTORY_ID = " + id;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            prepStatement.executeUpdate();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEducation(int id){
        String sql="DELETE FROM EDUCATIONHISTORY WHERE EDUCATIONHISTORY_ID = " + id;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            prepStatement = connection.prepareStatement(sql);
            prepStatement.executeUpdate();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
