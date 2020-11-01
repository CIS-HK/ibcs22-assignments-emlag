package edu.cis.ibcs_app.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.R;
import edu.cis.ibcs_app.Utils.CISConstants;
import edu.cis.ibcs_app.Models.SimpleClient;

public class ListingsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);
    }

    public void pingTheServer(View v)
    {
        try
        {
            //create a request with command ping
            Request exampleRequest = new Request("ping");

            // send the request to a URL
            String resultFromServer = SimpleClient.makeRequest(CISConstants.HOST, exampleRequest);

            System.out.println("result from server for ping command: " + resultFromServer);

        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
    }

    public void addListing(View v)
    {
        try
        {
            // make a request
            Request addListingRequest = new Request(CISConstants.ADD_LISTING);

            //send the request to the host
            String resultFromServerForAddListing = SimpleClient.makeRequest(CISConstants.HOST, addListingRequest);

            //check the return message from the server
            Toast messageToUser = Toast.makeText(this, "Message from Marketplace: " + resultFromServerForAddListing, Toast.LENGTH_LONG);
            //give appropriate message to user
            messageToUser.show();
        }
        catch (Exception err)
        {
            //let the user know something went awfully wrong
            Toast messageToUser = Toast.makeText(this, "OH NO! " + err.toString(), Toast.LENGTH_LONG);
            messageToUser.show();
        }

    }

}