package edu.jabs.contactsList.domain;

import java.util.ArrayList;

/**
 * Class that represents one contact
 */
public class Contact
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Contact first name
     */
    private String firstName;

    /**
     * Contact last name
     */
    private String lastName;

    /**
     * Contact address
     */
    private String address;

    /**
     * Contact email address
     */
    private String email;

    /**
     * List of phone numbers for this contact
     */
    private ArrayList phoneNumbers;

    /**
     * List of keywords for this contact. these keywords include first and last names
     */
    private ArrayList keywords;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a new contact without phone numbers or keywords<br>
     * <b>post: </b>The contact is created with the data provided and the list of phone numbers and keywords empty.
     * @param aFirstName the contact's first name. aFirstName !=null
     * @param aLastName the contact's last name. aLastName !=null
     * @param anAddress the contact's address. anAddress !=null
     * @param anEmail - the contact's email. anEmail !=null
     */
    public Contact( String aFirstName, String aLastName, String anAddress, String anEmail )
    {
        firstName = aFirstName;
        lastName = aLastName;
        address = anAddress;
        email = anEmail;
        phoneNumbers = new ArrayList( );
        keywords = new ArrayList( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns the contact's first name
     * @return contact's first name
     */
    public String getFirstName( )
    {
        return firstName;
    }

    /**
     * Returns the contact's last name
     * @return contact's last name
     */
    public String getLastName( )
    {
        return lastName;
    }

    /**
     * Returns the contact's address
     * @return contact's address
     */
    public String getAddress( )
    {
        return address;
    }

    /**
     * Returns the contact's email
     * @return contact's email
     */
    public String getEmail( )
    {
        return email;
    }

    /**
     * Returns the contact's list of phone numbers
     * @return contact's list of phone numbers
     */
    public ArrayList getPhoneNumbers( )
    {
        return phoneNumbers;
    }

    /**
     * Returns the contact's list of keywords
     * @return contact's list of keywords
     */
    public ArrayList getKeywords( )
    {
        return keywords;
    }

    /**
     * Changes the address for this contact
     * @param newAddress the new address. newAddress !=null
     */
    public void setAddress( String newAddress )
    {
        address = newAddress;
    }

    /**
     * Changes the email for this contact
     * @param newEmail the new email. newEmail !=null
     */
    public void setEmail( String newEmail )
    {
        email = newEmail;
    }

    /**
     * Adds a new phone number to the list of phone numbers if it doesn't exist already <br>
     * <b>post: </b> if its a new phone number, it is added to the list of phone numbers
     * @param newPhoneNumber new phone number to the added to the list. nuevoTelefono != null
     */
    public void addPhoneNumber( String newPhoneNumber )
    {
        //this cycle verifies that the number didnt already exist on the list
        int counter = 0;
        boolean found = false;
        while( counter < phoneNumbers.size( ) && !found )
        {
            String phoneNumber = ( String )phoneNumbers.get( counter );
            if( phoneNumber.equals( newPhoneNumber ) )
            {
                found = true;
            }
            else
                counter++;
        }

        //If the phone number isnt found on the list, it is added to the list
        if( found == false )
        {
            phoneNumbers.add( newPhoneNumber );
        }
    }

    /**
     * Delete's a phone number from the list of phone numbers<br>
     * <b>post: </b> if the phone number was in the list of phone numbers, it is eliminated <br>
     * @param phoneToDelete phone number that will be deleted from the list. phoneToDelete != null
     */
    public void deletePhoneNumber( String phoneToDelete )
    {
        //this cycle verifies that the number didnt already exist on the list
        int counter = 0;
        boolean found = false;
        while( counter < phoneNumbers.size( ) && !found )
        {
            String phoneNumber = ( String )phoneNumbers.get( counter );
            if( phoneNumber.equals( phoneToDelete ) )
            {
                found = true;
            }
            else
            {
                counter++;
            }
        }

        //if the phone is on the list it is eliminated
        if( found )
        {
            phoneNumbers.remove( counter );
        }
    }

    /**
     * Adds a new keyword to the list of keywords for this contact <br>
     * <b>post: </b> if the keyword isn't already on the list, it is added. <br>
     * @param newKeyword the keyword to be added on the list. newKeyword !=null
     */
    public void addKeyword( String newKeyword )
    {
        //checks to see if the keyword is already on the list
        if( !hasKeyword( newKeyword ) )
        {
            keywords.add( newKeyword );
        }
    }

    /**
     * Deletes a keyword from the list of keywords for this contact <br>
     * <b>post: </b> if the keywords is on the list, it is deleted <br>
     * @param keywordToDelete keyword that will be deleted from the list of keywords keywordToDelete != null.
     */
    public void deleteKeyword( String keywordToDelete )
    {
        //verifies that the keyword exists on the list
        if( hasKeyword( keywordToDelete ) )
        {
            keywords.remove( keywordToDelete );
        }
    }

    /**
     * Determines whether or not the contact has the keyword in his list of keywords
     * @param keyword keyword that were looking for. keyword != null
     * @return true if the contact already has that keyword on his list, false otherwise
     */
    public boolean hasKeyword( String keyword )
    {
        boolean found = false;
        int counter = 0;
        while( counter < keywords.size( ) && !found )
        {
            String theKeyword = ( String )keywords.get( counter );
            if( keyword.equals( theKeyword ) )
            {
                found = true;
            }
            counter++;
        }
        return found;
    }

}
