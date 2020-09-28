package edu.cis.Model;

public class CISConstants
{
    //return strings
    public static final String LIST_EXISTS_ERR = "Error: listing exists with that id.";
    public static final String BID_ERR = "Error: that bid is not valid.";
    public static final String INVALID_LISTING_ERR = "Error: can't find listing";
    public static final String LISTING_OPEN_ERR = "Error: the listing is already open";
    public static final String LISTING_CLOSE_ERR = "Error: the listing is already closed";
    public static final String ZERO_LISTINGS = "Error: there are no listings in the server";
    public static final String SUCCESS = "success";
    public static final String TRUE_RET = "true";
    public static final String FALSE_RET = "false";

    //Parameters for requests
    public static final String TITLE_PARAM = "title";
    public static final String PRICE_PARAM = "price";
    public static final String ID_PARAM = "id";
    public static final String DESC_PARAM = "description";
    public static final String BID_AMOUNT = "amount";
    public static final String ACTIVE = "active";
    public static final String STATUS = "status";
    public static final String IMAGE_STR = "imageString";

    //Commands
    public static final String PING = "ping";
    public static final String ADD_LISTING = "addListing";
    public static final String CONTAINS_LISTING = "containsListing";
    public static final String DELETE_LISTING = "deleteListing";
    public static final String OPEN_LISTING = "openListing";
    public static final String CLOSE_LISTING = "closeListing";
    public static final String PLACE_BID = "placeBid";
    public static final String GET_LISTING = "getListing";
    public static final String SET_IMG = "setImage";
    public static final String GET_IMG = "getImage";
    public static final String GET_ALL_LIST = "getListings";

    //Errors
    public static final String PORT_UNAVAIL = "is not available, likely because \nit's already being used by another " +
            "Java program running. \nClose all your server windows and try again.";
}
