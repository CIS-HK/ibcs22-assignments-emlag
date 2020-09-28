package edu.cis.Model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This class declares what a Request can do within this server and it's clients.
 */
public class Request
{
    private String command;
    private Map<String, String> params;

    /**
     * Constructor
     *
     * @param cmd the command string with which to make this Request object.
     * @return none
     */
    public Request(String cmd)
    {
        if (cmd == null)
        {
            throw new NullPointerException("null command verb passed");
        } else
        {
            this.command = this.sanitize(cmd).replace("?", "_")
                    .replace(" ", "_")
                    .replace("&", "_");
            this.params = new LinkedHashMap();
        }
    }

    /**
     * take a string and create a URL string with it.
     *
     * @param str encoded URL string
     * @return none
     */
    private static String decode(String str)
    {
        try
        {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException excp)
        {
            throw new IllegalArgumentException(excp);
        }
    }

    /**
     * take a URL string and encode it so that decoder can read it.
     *
     * @param str a string to encode
     * @return none
     */
    private static String encode(String str)
    {
        try
        {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException excp)
        {
            throw new IllegalArgumentException(excp);
        }
    }

    /**
     * Create a request object from a URL string.
     *
     * @param url the URl string to use
     * @return a Request object build from a string
     */
    public static Request fromUrl(String url)
    {
        String[] requestParts = url.split("\\?");
        //find where the "\\" happens and get the first occurrence
        String command = requestParts[0];

        //create a Request method with that command.
        Request request = new Request(command);
        if (requestParts.length == 2)
        {
            //get the parameters for the Request
            String paramStr = requestParts[1];
            String[] paramParts = paramStr.split("&");
            String[] parts = paramParts;
            int len = paramParts.length;

            //get key and values for each part found
            for (int i = 0; i < len; ++i)
            {
                String paramPart = parts[i];
                String key = paramPart.split("=")[0];
                String value = "";
                if (paramPart.split("=").length == 2)
                {
                    value = paramPart.split("=")[1];
                }

                request.addRaw(key, value);
            }
        }

        //return the whole Request object
        return request;
    }

    public void addParam(String key, String value)
    {
        this.params.put(this.sanitize(key), encode(value));
    }

    private void addRaw(String key, String value)
    {
        this.params.put(this.sanitize(key), value);
    }

    public String getCommand()
    {
        return this.command;
    }

    public String getParam(String key)
    {
        return this.hasParam(key) ? decode((String) this.params.get(this.sanitize(key))) : null;
    }

    public Iterator<String> getParamNames()
    {
        return this.params.keySet().iterator();
    }

    public boolean hasParam(String key)
    {
        return this.params.containsKey(this.sanitize(key));
    }

    public void removeParam(String key)
    {
        this.params.remove(this.sanitize(key));
    }

    private String sanitize(String key)
    {
        return String.valueOf(key).trim();
    }

    /**
     * create a GET request from this Request object. The GET method is used to retrieve information
     * from the given server using a given URI. Requests using GET should only retrieve data and
     * should have no other effect on the data.
     *
     * @param
     * @return GET string
     */
    public String toGetRequest()
    {
        String getRequest = this.command;
        if (!this.params.isEmpty())
        {
            boolean isFirst = true;
            Iterator itr = this.params.keySet().iterator();

            while (itr.hasNext())
            {
                String key = (String) itr.next();
                if (isFirst)
                {
                    getRequest = getRequest + "?" + key + "=" + (String) this.params.get(key);
                    isFirst = false;
                } else
                {
                    getRequest = getRequest + "&" + key + "=" + (String) this.params.get(key);
                }
            }
        }

        return getRequest;
    }

    public String toString()
    {
        String str = this.command + " (";
        boolean isFirst = true;

        for (Iterator itr = this.params.keySet().iterator(); itr.hasNext(); isFirst = false)
        {
            String p = (String) itr.next();
            if (!isFirst)
            {
                str = str + ", ";
            }

            str = str + p + "=" + this.getParam(p);
        }

        str = str + ")";
        return str;
    }
}
