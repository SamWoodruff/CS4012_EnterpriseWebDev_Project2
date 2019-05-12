package POJOs;

public class WorkHistory {
    private int workHistoryId;
    private int usersId;
    private String jobTitle;
    private String companyName;
    private int yearsOfService;

    public void setWorkHistoryId(int workHistoryId){this.workHistoryId=workHistoryId;}

    public int getWorkHistoryId(){return workHistoryId;}

    public void setUsersId(int usersId){this.usersId=usersId;}

    public int getUsersId(){return usersId;}

    public void setJobTitle(String jobTitle){this.jobTitle=jobTitle;}

    public String getJobTitle(){return jobTitle;}

    public void setCompanyName(String companyName){this.companyName=companyName;}

    public String getCompanyName(){return companyName;}

    public void setYearsOfService(int yearsOfService){this.yearsOfService = yearsOfService;}

    public int getYearsOfService(){return yearsOfService;}

}
