package com.example.sqltest.repository.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import org.json.JSONObject;

/**
 * A class with custom responseString messages and exception handling
 * @author Jfredricks
 * @version 1.0
 */
public class Response {

    public static final String RECORD_CREATED = "Record created successfully";
    public static final String NO_SUCH_RECORD = "No such record found";
    public static final String RECORD_EXISTS = "Record already exists";
    public static final String RECORD_DELETED = "Record deleted successfully";
    public static final String STATUS_CHECK = "Check your status";
    public static final String RECORD_UPDATED = "Record updated successfully";
    public static final String ERROR_RESPONSE = "Check your inputs again";

    String responseString;

    /**
     * Handles all exceptions thrown
     *
     * @param ex      is the exception passed
     * @param request is the webrequest passed
     * @return a responseString message and HttpStatus Ok
     */
    /*@ExceptionHandler(Exception.class)
    public static final ResponseEntity<String> handleAllExceptions(DbException ex, WebRequest request) {
        return new ResponseEntity<>(ERROR_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    /**
     * Returns responseString with HTTPStatus OK
     * @param resp the message to be shown to user
     * @return resp with HTTPStatus OK
     */
    public static ResponseEntity<Response> commonResponse(String resp) {
        Response res = new Response();
        res.setResponseString(resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Getter method for responseString
     * @return responseString message
     */
    public String getResponseString() {
        return responseString;
    }

    /**
     * Setter message for responseString
     * @param responseString is set to passed values
     */
    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }
}
