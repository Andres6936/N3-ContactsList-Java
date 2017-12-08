package edu.jabs.contactsList.domain;

import java.util.ArrayList;

/**
 * Class that represents a list of contacts
 */
public class ContactsList
{

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------
    /**
     * Contact list
     */
    private ArrayList contacts;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates a new, empty contact list
     */
    public ContactsList( )
    {
        contacts = new ArrayList( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns a list with all of the contacts' names.
     * @return list of contact names. if there are no contacts yet, then an empty list is returned.
     */
    public ArrayList getContactNames( )
    {
        ArrayList list = new ArrayList( );
        for( int i = 0; i < contacts.size( ); i++ )
        {
            Contact c = ( Contact )contacts.get( i );
            list.add( c.getFirstName( ) + " " + c.getLastName( ) );
        }
        return list;
    }

    /**
     * Returns a list of contact names with the names of contacts that have a particular keyword in their list<br>
     * @param keyword the keyword used to search for contacts. keyword != null
     * @return list of contact names that contain the keyword. If no contacts have that keyword, then an empty list is returned
     */
    public ArrayList findContactsWithKeyword( String keyword )
    {
        ArrayList names = new ArrayList( );

        for( int i = 0; i < contacts.size( ); i++ )
        {
            //For each contact, the method checks to see if the
            // keyword is on his list of keywords so as to add him to the list.
            Contact c = ( Contact )contacts.get( i );
            if( c.hasKeyword( keyword ) )
            {
                names.add( c.getFirstName( ) + " " + c.getLastName( ) );
            }
        }
        return names;
    }

    /**
     * Adds a new contact to the list if there isnt already a contact with that first and last name <br>
     * <b>post: </b> if the contact is new, it is added to the list <br>
     * @param firstName contact's first name. firstName != null
     * @param lastName contact's last name. lastName != null
     * @param address contact's address. address != null
     * @param email contact's email. email != null
     * @param phoneNumbers contact's list of phone numbers. phoneNumbers != null
     * @param keywords contact's list of keywords. keywords != null
     * @return true if the new contact was added to the list, false otherwise
     */
    public boolean addContact( String firstName, String lastName, String address, String email, ArrayList phoneNumbers, ArrayList keywords )
    {
        Contact con = findContact( firstName, lastName );
        if( con == null ) // the contact can be added to the list
        {
            keywords.add( firstName );
            keywords.add( lastName );
            Contact newC = new Contact( firstName, lastName, address, email );
            //Adds the list of phone numbers
            for( int i = 0; i < phoneNumbers.size( ); i++ )
            {
                newC.addPhoneNumber( ( String )phoneNumbers.get( i ) );
            }
            //Adds the list of keywords
            for( int i = 0; i < keywords.size( ); i++ )
            {
                newC.addKeyword( ( String )keywords.get( i ) );
            }
            contacts.add( newC );
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Deletes a contact given his first and last names <br>
     * <b>post: </b> if the contact appears on the list, it is deleted
     * @param firstName contact's first name. firstName != null
     * @param lastName contact's last name. lastName != null
     * @return true if the contact was eliminated, false otherwise
     */
    public boolean deleteContact( String firstName, String lastName )
    {
        Contact con = findContact( firstName, lastName );
        if( con != null )
        {
            contacts.remove( con );
            return true;
        }
        else
            return false;

    }

    /**
     * Modifies a contact's information given his first and last names <br>
     * <b>post: </b> if the contact exists on the list, his information is modified
     * @param firstName contact's first name. firstName != null
     * @param lastName contact's last name. lastName != null
     * @param address contact's new address. address != null
     * @param email contact's new email address. email !=null
     * @param phoneNumbers contact's new list of phone numbers
     * @param keywords contact's new list of keywords
     * @return true if the contact's information was successfully modified, false otherwise
     */
    public boolean modifyContact( String firstName, String lastName, String address, String email, ArrayList phoneNumbers, ArrayList keywords )
    {
        //checks if the contact exists
        Contact con = findContact( firstName, lastName );
        if( con == null )
        {
            return false;
        }
        //Modifies the information for the contact
        con.setAddress( address );
        con.setEmail( email );
        updateKeywords( keywords, con );
        updatePhoneNumbers( phoneNumbers, con );
        return true;
    }

    /**
     * Updates the list of phone numbers for the given contact
     * @param phoneNumbers current list of phone numbers. phoneNumbers != null
     * @param contact Contact whose list of phone numbers is to be updated.
     */
    private void updatePhoneNumbers( ArrayList phoneNumbers, Contact contact )
    {
        ArrayList oldList = contact.getPhoneNumbers( );
        ArrayList eliminate = new ArrayList( );

        //gets the phones from the old list that aren't on the new list
        for( int i = 0; i < oldList.size( ); i++ )
        {
            String oldPhone = ( String )oldList.get( i );
            boolean found = false;
            for( int j = 0; j < phoneNumbers.size( ) && !found; j++ )
            {
                String newPhone = ( String )phoneNumbers.get( j );
                if( oldPhone.equals( newPhone ) )
                {
                    found = true;
                }
            }
            if( !found )
                eliminate.add( oldPhone );

        }
        //deletes the phone numbers
        for( int i = 0; i < eliminate.size( ); i++ )
        {
            contact.deletePhoneNumber( ( String )eliminate.get( i ) );
        }

        //tries to add all the new phone numbers to the list. the method
        // makes sure they're not already on the list
        for( int i = 0; i < phoneNumbers.size( ); i++ )
        {
            String phoneNumber = ( String )phoneNumbers.get( i );
            contact.addPhoneNumber( phoneNumber );
        }
    }

    /**
     * Updates the list of keywords for the contact <br>
     * <b>post: </b> the keywords that aren't on the list are eliminated and the new ones are added.
     * @param keywords final list of keywords
     * @param contact contact whose list of keywords is to be modified
     */
    private void updateKeywords( ArrayList keywords, Contact contact )
    {
        ArrayList oldList = contact.getKeywords( );
        ArrayList eliminate = new ArrayList( );

        //takes all the keywords that aren't on the list so as to delete them
        int oldListSize = oldList.size( );
        for( int i = 0; i < oldListSize; i++ )
        {
            String oldKeyword = ( String )oldList.get( i );
            boolean found = false;
            for( int j = 0; j < keywords.size( ) && !found; j++ )
            {
                String newKeyword = ( String )keywords.get( j );
                if( oldKeyword.equals( newKeyword ) )
                {
                    found = true;
                }
            }
            if( !found )
                eliminate.add( oldKeyword );
        }
        //deletes the keywords
        for( int i = 0; i < eliminate.size( ); i++ )
        {
            contact.deleteKeyword( ( String )eliminate.get( i ) );
        }

        //tries to add all the new keywords to the list. the method
        // checks to make sure thay aren't already on it
        for( int i = 0; i < keywords.size( ); i++ )
        {
            String keyword = ( String )keywords.get( i );
            contact.addKeyword( keyword );
        }

    }

    /**
     * Searches for a contact given his first and last names
     * @param firstName contact's first name. firstName != null
     * @param lastName contact's last name. lastName != null
     * @return the contact if it is found, null otherwise
     */
    public Contact findContact( String firstName, String lastName )
    {
        int counter = 0;
        Contact con = null;
        boolean found = false;
        while( counter < contacts.size( ) && !found )
        {
            con = ( Contact )contacts.get( counter );
            if( firstName.equals( con.getFirstName( ) ) && lastName.equals( con.getLastName( ) ) )
            {
                found = true;
            }
            counter++;
        }
        if( found )
            return con;
        else
            return null;
    }

    //-----------------------------------------------------------------
    // Extension points
    //-----------------------------------------------------------------

    /**
     * returns the answer for the extension method 1
     * @return answer for the extension method 1
     */
    public String method1( )
    {
        return "Answer 1";
    }

    /**
     * returns the answer for the extension method 2
     * @return answer for the extension method 2
     */
    public String method2( )
    {
        return "Answer 2";
    }

}