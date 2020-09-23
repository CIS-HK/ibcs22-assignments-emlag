package edu.cis.labs_22.Lab2;

import java.util.List;

public class Student
{
    private String id;
    private int age;
    private String yearLevel;
    private List<Club> clubs;

    public Student(String id, int age, String yearLevel, List<Club> clubs)
    {
        this.id = id;
        this.age = age;
        this.yearLevel = yearLevel;
        this.clubs = clubs;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getYearLevel()
    {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel)
    {
        this.yearLevel = yearLevel;
    }

    public List<Club> getClubs()
    {
        return clubs;
    }

    public void setClubs(List<Club> clubs)
    {
        this.clubs = clubs;
    }

    public void addClub(Club inputClub)
    {
        this.clubs.add(inputClub);
    }
}
