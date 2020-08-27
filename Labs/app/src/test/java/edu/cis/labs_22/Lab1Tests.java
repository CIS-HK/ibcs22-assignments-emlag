package edu.cis.labs_22;

import org.junit.Test;

import edu.cis.labs_22.Lab1.CISArrayList;
import edu.cis.labs_22.Lab1.CISHashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Lab1Tests {
    @Test
    public void arrayListTestInit()
    {
        CISArrayList list1 = new CISArrayList();
        CISArrayList list2 = new CISArrayList();
        assertTrue(list1.length() == 0);
        assertTrue(list2.length() == 0);
        assertNotSame(list1, list2);
    }

    @Test
    public void arrayListTestSingleAdd()
    {
        CISArrayList list1 = new CISArrayList();
        CISArrayList list2 = new CISArrayList();
        list1.add("Hello");
        list2.add("WOW");

        assertTrue(list1.length() == 1);
        assertTrue(list2.length() == 1);
    }

    @Test
    public void arrayListTestManyAdd()
    {
        CISArrayList list1 = new CISArrayList();
        CISArrayList list2 = new CISArrayList();

        for (int num = 0; num < 20; num++) {
            list1.add(num);
        }

        for (int num = 0; num < 46; num++) {
            list2.add(num);
        }

        assertTrue(list1.length() == 20);
        assertTrue(list2.length() == 46);
    }

    @Test
    public void arrayListTestGet()
    {
        CISArrayList list1 = new CISArrayList();
        CISArrayList list2 = new CISArrayList();

        for (int num = 0; num < 225; num++) {
            list1.add("number: " + num);
        }

        for (int num = 0; num < 85; num++) {
            list2.add("number: " + num);
        }

        assertTrue(list1.getElement(85).equals("number: 85"));
        assertTrue(list1.getElement(33).equals("number: 33"));
        assertTrue(list1.getElement(0).equals("number: 0"));

        assertTrue(list2.getElement(90) == null);
        assertTrue(list2.getElement(-555) == null);
    }

    @Test
    public void arrayListTestSet()
    {
        CISArrayList list1 = new CISArrayList();
        CISArrayList list2 = new CISArrayList();

        String testString = "0xffffff";
        String testString2 = "0xbeef";

        for (int num = 0; num < 85; num++) {
            list1.add("number: " + num);
        }

        for (int num = 0; num < 13; num++) {
            list2.add("number: " + num);
        }

        list1.setElement(5, testString);
        list2.setElement(13, testString2);

        assertTrue(list1.getElement(5).equals(testString));
        assertTrue(list2.getElement(13).equals(testString2));
    }

    @Test
    public void hashMapTestInit()
    {
        CISHashMap map1 = new CISHashMap();
        CISHashMap map2 = new CISHashMap();

        assertTrue(map1.length() == 0);
        assertTrue(map1.length() == 0);
        assertNotSame(map1, map2);
    }

    @Test
    public void hashMapTestSimpleAdd()
    {
        CISHashMap map1 = new CISHashMap();
        CISHashMap map2 = new CISHashMap();

        map1.add("Class", "IBCS");
        map2.add("Class", "APCS");
        map1.add("City", "HK");
        map2.add("City", "DC");
        map1.add("School", "CIS");
        map2.add("School", "Hangzhou");

        assertTrue(map1.length() == 3);
        assertTrue(map2.length() == 3);
    }

    @Test
    public void hashMapTestGet()
    {
        CISHashMap map1 = new CISHashMap();
        CISHashMap map2 = new CISHashMap();

        map1.add("Class", "IBCS");
        map2.add("Class", "APCS");
        map1.add("City", "HK");
        map2.add("City", "DC");
        map1.add("School", "CIS");
        map2.add("School", "Hangzhou");

        assertTrue(map1.getElement("Class").equals("IBCS"));
        assertTrue(map1.getElement("City").equals("HK"));
        assertTrue(map1.getElement("School").equals("CIS"));
        assertTrue(map2.getElement("Class").equals("APCS"));
        assertTrue(map2.getElement("City").equals("DC"));
        assertTrue(map2.getElement("School").equals("Hangzhou"));
    }
}