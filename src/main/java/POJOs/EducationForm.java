package POJOs;

public class EducationForm {
    private String degreeType;
    private String degreeDiscipline;
    private int yearsAchieved;

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
