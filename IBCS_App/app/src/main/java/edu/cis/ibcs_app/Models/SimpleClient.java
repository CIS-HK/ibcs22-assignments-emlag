package edu.cis.ibcs_app.Models;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.cis.ibcs_app.Utils.IOUtils;

public class SimpleClient
{
    public static final String ERROR_KEY = "Error:";

    private SimpleClient()
    {
    }

    public static String makeRequest(String host, Request request) throws IOException
    {
        if (!host.endsWith("/"))
        {
            host = host + "/";
        }

        if (request == null)
        {
            throw new NullPointerException("null request passed");
        } else
        {
            try
            {
                URL destination = new URL(host + request.toGetRequest());
                HttpURLConnection conn = (HttpURLConnection) destination.openConnection();
                conn.setRequestMethod("GET");
                String response = IOUtils.readEntireStream(conn.getInputStream());
                if (response.startsWith("Error:"))
                {
                    throw new IOException(response);
                } else
                {
                    return response;
                }
            } catch (ConnectException excp)
            {
                throw new IOException("Unable to connect to the server. Did you start it?");
            } catch (MalformedURLException excp)
            {
                throw new IllegalArgumentException("Invalid URL: " + host + request.toGetRequest());
            }
        }
    }
}