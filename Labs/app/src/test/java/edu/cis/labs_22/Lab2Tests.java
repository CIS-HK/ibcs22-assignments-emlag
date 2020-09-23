package edu.cis.labs_22;

import org.junit.Test;

import java.util.ArrayList;

import edu.cis.labs_22.Lab2.Club;
import edu.cis.labs_22.Lab2.StringCreator;
import edu.cis.labs_22.Lab2.StringInfo;
import edu.cis.labs_22.Lab2.StringModifier;
import edu.cis.labs_22.Lab2.StringSerializer;
import edu.cis.labs_22.Lab2.StringSplitter;
import edu.cis.labs_22.Lab2.Student;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Lab2Tests {
    @Test
    public void stringInfoLength()
    {
        String toTest = "virjoyClassic";
        StringInfo si = new StringInfo();
        int answer = si.lengthOfString(toTest);
        assertEquals(toTest.length(), answer); //length should be the same

        String toTestEmpty = "";
        int answerEmpty = si.lengthOfString(toTestEmpty);
        assertEquals(toTestEmpty.length(), answerEmpty); //length on an empty string
    }

    @Test
    public void stringInfoEquals()
    {
        String toTest = "virjoyClassic";
        String badString = "heyo";
        String stringEmpty = "";
        StringInfo si = new StringInfo();

        boolean answerGood = si.stringIsEqual(toTest, toTest);
        assertTrue(answerGood); //strings are equal

        boolean answerBad = si.stringIsEqual(toTest, badString);
        assertFalse(answerBad); //strings are not equal

        boolean answerBadTwo = si.stringIsEqual(toTest, stringEmpty);
        assertTrue(answerBadTwo); //one of the strings is empty
    }

    @Test
    public void stringModifierUpper()
    {
        StringModifier sm = new StringModifier();
        String expected = "AEIOU";
        String answer = sm.stringToUpperCase("aeiou");
        assertEquals(expected, answer);
    }

    @Test
    public void stringModifierLower()
    {
        StringModifier sm = new StringModifier();
        String expected = "aeiou";
        String answer = sm.stringToLowerCase("AEIOU");
        assertEquals(expected, answer);
    }

    @Test
    public void stringModifierAE()
    {
        StringModifier sm = new StringModifier();
        String expected = "aeiou";
        String answer = sm.asToEs("eaiou");
        assertEquals(expected, answer);


        String expectedTwo = "aaeeaaee";
        String answerTwo = sm.asToEs("eeaaeeaa");
        assertEquals(expectedTwo, answerTwo);

        String expectedEmpty = "";
        String answerEmpty = sm.asToEs("");
        assertEquals(expectedEmpty, answerEmpty); //empty string should return empty
    }

    @Test
    public void stringModifierCombineTwo()
    {
        StringModifier sm = new StringModifier();
        String one = "united";
        String two = "states";
        String answer = sm.combineStrings(one, two);
        String expected = "unitedstates";
        assertEquals(expected, answer); //both strings should be combined

        String answerOneEmpty = sm.combineStrings(one, "");
        assertEquals(one, answerOneEmpty); //one string is empty
    }

    @Test
    public void stringModifierYoink()
    {
        StringModifier sm = new StringModifier();
        String toYoink = "TThere It is";
        String expected = "yoinkThere It is";
        String answer = sm.yoink(toYoink);
        assertEquals(expected, answer);

        String spaceString = " s";
        String newExpected = "yoinks";
        String newAnswer = sm.yoink(spaceString);
        assertEquals(newExpected, newAnswer);
    }

    @Test
    public void creatorOneArray()
    {
        StringCreator sc = new StringCreator();
        char[] arr = {'a', ' ', 'l', 'o', 't'};
        String answer = sc.fromArrayToString(arr);
        String expected = "a lot";
        assertEquals(expected, answer);
    }

    @Test
    public void creatorTwoArray()
    {
        StringCreator sc = new StringCreator();
        char[] arr = {'a', ' ', 'l', 'o', 't', ' '};
        char[] arrTwo = {'c', 'r', 'e', 'a', 't', 'e'};
        String answer = sc.fromTwoArraysToString(arr, arrTwo);
        String expected = "a lot create";
        assertEquals(expected, answer);
    }

    @Test
    public void stringSplitThis()
    {
        StringSplitter sp = new StringSplitter();
        String inString = "single";
        String[] answer = sp.splitThis(inString);
        String expected = "i";
        if(answer != null && answer.length > 1)
        {
            assertEquals(expected, answer[1]);
        }
        else
        {
            assertTrue(false); //will not pass the test if returned null or empty array
        }
    }

    @Test
    public void serializeArray()
    {
        StringSplitter sp = new StringSplitter();
        int[] arr = {55, 66, 77, 99};
        String answer = sp.serializeArray(arr);
        String expected = "[55, 66, 77, 99]";
        assertEquals(expected, answer); //check an array of ints

        int[] emptyArr = {};
        String answerTwo = sp.serializeArray(emptyArr);
        String expectedTwo = "[]";
        assertEquals(expectedTwo, answerTwo); //check an empty array
    }

    @Test
    public void deserializeArray()
    {
        StringSplitter sp = new StringSplitter();
        String serArr = "[9, 3, 2, 1]";
        int[] arr = sp.deserializeArray(serArr);
        if(arr != null && arr.length > 0)
        {
            assertEquals(arr[0], 9); //check the value of the first iint
        }
        else
        {
            assertTrue(false);
        }
    }

    @Test
    public void serializeStudent()
    {
        ArrayList<Club> clubs =  new ArrayList<>();
        clubs.add(new Club("create"));
        clubs.add(new Club("hkoi"));
        clubs.add(new Club("swimming"));
        Student ss = new Student("99", 16, "year 12", clubs);
        String serializedStudent = StringSerializer.serialize(ss);
        String expected = "id : 99, age : 16, yearLevel : year 12, clubs : [create, hkoi, swimming]";

        assertEquals(expected, serializedStudent);
    }

    @Test
    public void deserializeStudent()
    {
        String serializedStudent = "id : 99, age : 16, yearLevel : year 12, clubs : [create, hkoi, swimming]";
        Student ss = StringSerializer.deserialize(serializedStudent);
        if (ss != null)
        {
            assertEquals(ss.getYearLevel(), "year 12");
            assertEquals(ss.getId(), "99");
            assertTrue(ss.getClubs() != null);
            assertEquals(ss.getClubs().get(0).getName(), "create");
        }
        else
        {
            assertTrue(false);
        }
    }
}
