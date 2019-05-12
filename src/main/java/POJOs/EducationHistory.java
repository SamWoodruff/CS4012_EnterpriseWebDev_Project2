package POJOs;

public class EducationHistory {
    private int educationHistoryId;
    private int usersId;
    private String degreeType;
    private String degreeDiscipline;
    private int yearsAchieved;

    public void setEducationHistoryId(int educationHistoryId){
        this.educationHistoryId=educationHistoryId;
    }

    public int getEducationHistoryId(){return educationHistoryId;}

    public void setUsersId(int UsersId){
        this.usersId=UsersId;
    }

    public int getUsersId(){return usersId;}

    public void setDegreeType(String degreeType){
        this.degreeType=degreeType;
    }

    public String getDegreeType(){return  degreeType;}

    public void setDegreeDiscipline(String degreeDiscipline){
        this.degreeDiscipline=degreeDiscipline;
    }

    public String getDegreeDiscipline(){return  degreeDiscipline;}

    public void setYearsAchieved(int yearsAchieved){
        this.yearsAchieved=yearsAchieved;
    }

    public int getYearsAchieved(){return yearsAchieved;}
}
