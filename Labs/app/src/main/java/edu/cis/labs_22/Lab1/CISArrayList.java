package edu.cis.labs_22.Lab1;

public class CISArrayList<SomeType> {

    private SomeType [] allTheObjects;
    private static final int INITIAL_CAPACITY = 10;
    private int totalObjects;
    private int currIndex;


    public CISArrayList()
    {
            /*
                TODO 0: This constructor will initialize allTheObjects
                to an array of size 10.
            */

        allTheObjects = (SomeType[])new Object[INITIAL_CAPACITY]; //Array of size 10
        totalObjects = 0;

    }

    public int length() //Change to numberOfObjectsInArray in the future
    {
            /*
                TODO 1: Method length, which will return the amount of
                objects that are actually IN the array.
            */
        return 0;
    }

    private void grow()
    {
            /*
                TODO 2: Create a private method called grow, which doubles
                the internal array's capacity.
            */

    }

    public void add(Object objectToStore)
    {
            /*
                TODO 3: Method add, which will takes in a Object and add
                to the next available index in the array. It returns nothing.
                If array is full, grow it.
            */
    }

    public Object getElement(int idx)
    {
            /*
                TODO 4: Method getElement, which takes in an int as the index,
                and returns the Object at that index.
                If index is out of bounds (less than 0 or >number of objects),
                return null.
            */
        return 0;
    }

    public Object setElement(int idx, Object objectToStore)
    {
            /* TODO 5: Method setElement, takes in an int as the index
               and an Object, and replaces the Object at that index in the
               allTheObjects array. This should return the old object, which
               was replaced as is no longer in the array. If index is out of
               bounds (less than 0 or >number of objects), return null.
            */
        return 0;
    }
}
