/*
 * File: CIServerTests.java
 * -----------------------
 * This program tests your server by sending a bunch of requests
 * and validating if the response is what it was expecting.
 */
package edu.cis.Tests;

import acm.program.*;
import edu.cis.Model.CISConstants;
import edu.cis.Model.Request;
import edu.cis.Utils.SimpleClient;

import java.io.*;


public class CIServerTests extends ConsoleProgram
{

    public static void main(String[] args)
    {
        CIServerTests f = new CIServerTests();
        f.start(args);
    }

    /* The internet address of the computer running the server */
    private static final String HOST = "http://localhost:8000/";

    /* Run all of the tests */
    public void run()
    {

        // a welcome message
        println("Running the CIServer tester");
        println("Make sure that the server has just been restarted");
        println("-------");
        println("");

        int passed = 0;
        int total = 0;

        println("=== PING ===\n");

        Request ping = new Request("ping");
        boolean success = runTest(ping, false, "Hello, internet");
        total++;
        if (success)
        {
            passed++;
        }

        println("=== ADD LISTINGS ===\n");

        String[] testTitles = {"CS Book", "Basketball", "Shorts", "CIS Hoodie", "Backpack"};
        int[] prices = {99, 88, 77, 66, 55};
        String[] ids = {"6a6a", "777", "89b89b", "33a", "90pt"};
        String[] description = {"brand new", "like new", "used", "old", "best ever"};

        // Adding first time should be successful
        for(int i = 0; i < testTitles.length; i++)
        {
            Request addRequest = new Request(CISConstants.ADD_LISTING);

            addRequest.addParam(CISConstants.TITLE_PARAM, testTitles[i]);
            addRequest.addParam(CISConstants.PRICE_PARAM, String.valueOf(prices[i]));
            addRequest.addParam(CISConstants.ID_PARAM, ids[i]);
            addRequest.addParam(CISConstants.DESC_PARAM, description[i]);
            addRequest.addParam(CISConstants.ACTIVE, CISConstants.TRUE_RET);

            success = runTest(addRequest, false, CISConstants.SUCCESS);
            total++;
            if (success)
            {
                passed++;
            }
        }

        // Adding a second time should give an error
        for(int i = 0; i < testTitles.length; i++)
        {
            Request addRequest = new Request(CISConstants.ADD_LISTING);

            addRequest.addParam(CISConstants.TITLE_PARAM, testTitles[i]);
            addRequest.addParam(CISConstants.PRICE_PARAM, String.valueOf(prices[i]));
            addRequest.addParam(CISConstants.ID_PARAM, ids[i]);
            addRequest.addParam(CISConstants.DESC_PARAM, description[i]);
            addRequest.addParam(CISConstants.ACTIVE, CISConstants.TRUE_RET);

            success = runTest(addRequest, true, null);
            total++;
            if (success)
            {
                passed++;
            }
        }

        println("=== CONTAINS LISTING ===\n");

        // All previously-added profiles should have response "true"
        for (String id : ids)
        {
            Request containsRequest = new Request(CISConstants.CONTAINS_LISTING);
            containsRequest.addParam(CISConstants.ID_PARAM, id);
            success = runTest(containsRequest, false, CISConstants.TRUE_RET);
            total++;
            if (success)
            {
                passed++;
            }
        }

        String[] notAddedIds = {"99p", "234eva", "16x16"};

        // All un-added profiles should have response "false"
        for (String id : notAddedIds)
        {
            Request containsRequest = new Request(CISConstants.CONTAINS_LISTING);
            containsRequest.addParam(CISConstants.ID_PARAM, id);
            success = runTest(containsRequest, false, CISConstants.FALSE_RET);
            total++;
            if (success)
            {
                passed++;
            }
        }

        println("=== DELETE LISTING ===\n");

        // Should receive error
        Request d1 = new Request(CISConstants.DELETE_LISTING);
        d1.addParam(CISConstants.ID_PARAM, "CIS33");
        success = runTest(d1, true, "");
        total++;
        if (success)
        {
            passed++;
        }

        // Should delete successfully
        Request d2 = new Request(CISConstants.DELETE_LISTING);
        d2.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(d2, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        // Should no longer contain profile
        Request d3 = new Request(CISConstants.CONTAINS_LISTING);
        d3.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(d3, false, CISConstants.FALSE_RET);
        total++;
        if (success)
        {
            passed++;
        }

        // Should receive error (cannot delete 2x)
        Request d4 = new Request(CISConstants.DELETE_LISTING);
        d4.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(d4, true, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        // Should be able to add again
        Request d5 = new Request(CISConstants.ADD_LISTING);
        d5.addParam(CISConstants.TITLE_PARAM, testTitles[0]);
        d5.addParam(CISConstants.PRICE_PARAM, String.valueOf(prices[0]));
        d5.addParam(CISConstants.ID_PARAM, ids[0]);
        d5.addParam(CISConstants.DESC_PARAM, description[0]);
        d5.addParam(CISConstants.ACTIVE, CISConstants.TRUE_RET);
        success = runTest(d5, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        println("=== OPEN/CLOSE LISTING ===\n");

        // Close an existing open listing
        Request s1 = new Request(CISConstants.CLOSE_LISTING);
        s1.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(s1, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        // Open Listing again
        Request s2 = new Request(CISConstants.OPEN_LISTING);
        s2.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(s2, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        // Can't open a listing twice
        Request s3 = new Request(CISConstants.OPEN_LISTING);
        s3.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(s3, true, null);
        total++;
        if (success)
        {
            passed++;
        }

        // Should get error for unknown listing
        Request s4 = new Request(CISConstants.OPEN_LISTING);
        s4.addParam(CISConstants.ID_PARAM, "voldemort");
        success = runTest(s4, true, null);
        total++;
        if (success)
        {
            passed++;
        }

        // Close an existing open listing
        Request s5 = new Request(CISConstants.CLOSE_LISTING);
        s5.addParam(CISConstants.ID_PARAM, ids[3]);
        success = runTest(s5, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        println("=== PLACE BIDS ===\n");

        //Place bid for one listing, listing id: 6a6a highest bid is now 155
        Request b1 = new Request(CISConstants.PLACE_BID);
        b1.addParam(CISConstants.BID_AMOUNT, "155");
        b1.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(b1, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        //Listing doesn't exist
        Request b2 = new Request(CISConstants.PLACE_BID);
        b2.addParam(CISConstants.BID_AMOUNT, "155");
        b2.addParam(CISConstants.ID_PARAM, "harry");
        success = runTest(b2, true, null);
        total++;
        if (success)
        {
            passed++;
        }

        //Place bid for one listing, listing id: 777 highest bid is now 100
        Request b3 = new Request(CISConstants.PLACE_BID);
        b3.addParam(CISConstants.BID_AMOUNT, "100");
        b3.addParam(CISConstants.ID_PARAM, ids[1]);
        success = runTest(b3, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        //Place bid for one listing, listing id: 777 highest bid is 100
        //Error expected because bid is too low
        Request b4 = new Request(CISConstants.PLACE_BID);
        b4.addParam(CISConstants.BID_AMOUNT, "10");
        b4.addParam(CISConstants.ID_PARAM, ids[1]);
        success = runTest(b4, true, null);
        total++;
        if (success)
        {
            passed++;
        }

        //Can't place bid on a closed listing
        Request b5 = new Request(CISConstants.PLACE_BID);
        b4.addParam(CISConstants.BID_AMOUNT, "200");
        b4.addParam(CISConstants.ID_PARAM, ids[3]);
        success = runTest(b4, true, null);
        total++;
        if (success)
        {
            passed++;
        }

        // Add a few bids
        //Place bid for one listing, listing id: 6a6a highest bid is now 200
        Request nb1 = new Request(CISConstants.PLACE_BID);
        nb1.addParam(CISConstants.BID_AMOUNT, "200");
        nb1.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(nb1, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }

        Request nb2 = new Request(CISConstants.PLACE_BID);
        nb2.addParam(CISConstants.BID_AMOUNT, "300");
        nb2.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(nb2, false, CISConstants.SUCCESS);
        total++;
        if (success)
        {
            passed++;
        }


        println("=== GET ONE LISTING ===\n");

        // Get listing as a string
        String expectedf1 = "title:" + testTitles[0] +
                ", active:" + "true" +
                ", price=" + "300" +
                ", bids:" + "[155, 200, 300]" +
                ", id:" + ids[0] +
                ", description:" + description[0];
        Request f1 = new Request(CISConstants.GET_LISTING);
        f1.addParam(CISConstants.ID_PARAM, ids[0]);
        success = runTest(f1, false, expectedf1);
        total++;
        if (success)
        {
            passed++;
        }

        //no bids on this listing
        String expectedf2 = "title:" + testTitles[2] +
                ", active:" + "true" +
                ", price=" + "77" +
                ", bids:" + "[]" +
                ", id:" + ids[2] +
                ", description:" + description[2];
        Request f2 = new Request(CISConstants.GET_LISTING);
        f2.addParam(CISConstants.ID_PARAM, ids[2]);
        success = runTest(f2, false, expectedf2);
        total++;
        if (success)
        {
            passed++;
        }

        println("=== GET ALL LISTINGS ===\n");

        Request g1 = new Request(CISConstants.GET_ALL_LIST);
        String expectedg1 = "[777, 89b89b, 33a, 90pt, 6a6a]";
        success = runTest(g1, false, expectedg1);
        total++;
        if (success)
        {
            passed++;
        }

        println("=== INVALID COMMANDS ===\n");

        // and what if we send a bad command?
        Request bad = new Request("badCommand");
        success = runTest(bad, true, "");
        total++;
        if (success)
        {
            passed++;
        }

        println("Passed: " + passed + "/" + total);
    }

    /**
     * Runs a request and checks if the result is what was expected (both whether we
     * expect an error and otherwise what String response is expected)
     */
    private boolean runTest(Request request, boolean expectError, String expectedSuccessOutput)
    {
        println(request.toString());
        try
        {
            String result = SimpleClient.makeRequest(HOST, request);
            if (expectError)
            {
                println("Test failed. Expected an error but didn't get one\n");
                return false;
            } else if (!result.equals(expectedSuccessOutput))
            {
                println("Test failed.");
                println("Expected response: " + expectedSuccessOutput);
                println("Actual response:  " + result + "\n");
                return false;
            } else
            {
                println("Test passed.\n");
                return true;
            }
        } catch (IOException e)
        {
            if (expectError && e.getMessage().startsWith(SimpleClient.ERROR_KEY))
            {
                println("Test passed.\n");
                return true;
            } else
            {
                println("Test failed. Received unknown error: " + e.getMessage() + "\n");
                return false;
            }
        }
    }
}
