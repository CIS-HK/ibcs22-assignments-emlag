package edu.cis.labs_22.Lab1;

public class CISHashMap<SomeTypeKey, SomeTypeVal> {
    private SomeTypeVal [] allTheObjects;
    private static final int INITIAL_CAPACITY = 10;
    private int totalObjects;

    public CISHashMap()
    {
        allTheObjects = (SomeTypeVal[]) new Object [INITIAL_CAPACITY];
        totalObjects = 0;
    }

    public int length()
    {
        /*
            TODO 6: Method length, which will return the amount of
            objects that are actually IN the array.
        */
        return 0;
    }

    private void grow()
    {
       /*
            TODO 7: Create a private method called grow, which doubles the
            internal array's capacity.
       */

    }

    public void add(SomeTypeKey key, SomeTypeVal value)
    {
        /*
            TODO 8: Find the Value by using the Key. Return the value. Grow
            the array if full.
        */

    }

    public SomeTypeVal getElement(SomeTypeKey key)
    {
        /*
            TODO 9: Method getElement, which takes in an object as the key,
            find the corresponding hash for that key. Return the value for
            the given key. Use .hashCode() to find the Key's hashcode. This
            will sometimes return a negative hash, get the absolute value of it.
        */
        return null;
    }
}
