package edu.cis.Utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import edu.cis.Model.CISConstants;
import edu.cis.Model.SimpleServerListener;
import edu.cis.Model.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.concurrent.Executor;

/**
 * A class that will be used to create a connection to a port for the server.
 * Extends SimpleNetworking(found locally) and
 * HttpHandler(created by Sun Systems/Oracle) classes.
 */
public class SimpleServer extends SimpleNetworking implements HttpHandler
{
    private HttpServer server;
    private SimpleServerListener requestListener;
    private int port;


    /**
     * Constructor
     *
     * @param listener listener object from, conforms to SimpleServerListener Interface
     * @param port     number of the port where this server will be listening
     */
    public SimpleServer(SimpleServerListener listener, int port)
    {
        if (port > 0 && port <= 65535)
        {
            this.requestListener = listener;
            this.port = port;
        } else
        {
            throw new IllegalArgumentException("Port out of range: " + port);
        }
    }

    public int getPort()
    {
        return this.port;
    }

    /**
     * used to obtain the uri string of where the connection is happening for the server
     *
     * @param exchange this object uses HttpExchange class which provides methods
     *                 for examining the request from the client, and for building
     *                 and sending the response.
     * @return string with the URI
     * @see //docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec
     * /com/sun/net/httpserver/HttpExchange.html
     */
    private static String getUriString(HttpExchange exchange)
    {
        URI uri = exchange.getRequestURI();
        String uriStr = uri.toString();
        uriStr = uriStr.substring(1); //start at the second character
        return uriStr;
    }

    public boolean isRunning()
    {
        return this.server != null;
    }

    public boolean portIsAvailable()
    {
        return portIsAvailable(this.port);
    }

    /**
     * Open a socket at the port given as parameter, if no error is returned then that
     * port is available. Close the socket when done checking.
     *
     * @param port the number of the port to check
     * @return true if port is available
     */
    public static boolean portIsAvailable(int port)
    {
        Socket ignored = null;

        try
        {
            //create the socket with the given port number
            ignored = new Socket("localhost", port);
            return false;
        } catch (IOException excp)
        {
        } finally
        {
            //if opening of socket succeeded
            if (ignored != null)
            {
                try
                {
                    //close the socket when done
                    ignored.close();
                } catch (IOException excp)
                {
                }
            }

        }

        return true;
    }

    /**
     * start listening to the server at the port given through the constructor.
     *
     * @param
     * @return none
     */
    public void start()
    {
        if (!this.isRunning())
        {
            //if this server is running, check if the port to open is available, before creating a server at that port
            if (!portIsAvailable(this.port))
            {
                System.out.println("Port " + this.port + CISConstants.PORT_UNAVAIL);
                throw new IllegalArgumentException("Port " + this.port + CISConstants.PORT_UNAVAIL);
            } else
            {
                try
                {
                    //create an HttpServer object, which comes from Sun Systems/Oracle
                    //the server will be create at the port given.
                    this.server = HttpServer.create(new InetSocketAddress(this.port), 0);
                    this.server.createContext("/", this);
                    this.server.setExecutor((Executor) null);
                    this.server.start();
                } catch (IOException excp)
                {
                    throw new RuntimeException(excp);
                }
            }
        }
    }

    public void stop()
    {
        this.server.stop(0);
        this.server = null;
    }

    public String toString()
    {
        return this.getClass().getSimpleName() + "{" + "port=" + this.port +
                ", running=" + this.isRunning() +
                (this.requestListener == null ? "" : ", listener=\"" + this.requestListener + "\"") + "}";
    }

    /**
     * Method that declares how requests are handled.
     *
     * @param t the exchange location
     * @return none
     */
    public void handle(HttpExchange t) throws IOException
    {
        try
        {
            //get the string for the exchange location
            String uriStr = getUriString(t);
            Request request = Request.fromUrl(uriStr);
            String responseStr = null;
            if (this.requestListener != null)
            {
                //this listener is the CIServer instance that created the server.
                //call the requestMade method from the listener and pass in a string
                //get the response as a string, this response is created by you depending
                //on the type of request made.
                responseStr = this.requestListener.requestMade(request);
            }

            if (responseStr == null)
            {
                throw new NullPointerException("Server request returned null response.");
            } else
            {
                //record the return of the request so that the rest of the program can use it.
                t.sendResponseHeaders(200, (long) responseStr.length());
                OutputStream os = t.getResponseBody();
                os.write(responseStr.getBytes());
                os.close();
            }
        } catch (IOException excp)
        {
            excp.printStackTrace();
            throw new RuntimeException(excp);
        }
    }
}
