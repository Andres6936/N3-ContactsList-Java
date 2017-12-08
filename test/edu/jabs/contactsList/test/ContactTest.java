package edu.jabs.contactsList.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import edu.jabs.contactsList.domain.Contact;

/**
 * testing class for the contact
 */
public class ContactTest extends TestCase
{

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Contact used for testing
     */
    private Contact contact;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Initializes the first testing scenario
     */
    private void setUpScenario1( )
    {
        contact = new Contact( "Carolina", "Rodríguez", "Trv 25 No. 43 - 45", "crodriguez@uniandes.edu.co" );
        contact.addPhoneNumber( "6556850" );
        contact.addPhoneNumber( "4859527" );
        contact.addKeyword( "Friends" );
        contact.addKeyword( "Party" );

    }

    /**
     * tests the changing of attributes
     */
    public void testChangeData( )
    {
        setUpScenario1( );

        //tests that the contact was created correctly
        String firstName = contact.getFirstName( );
        String lastName = contact.getLastName( );
        String address = contact.getAddress( );
        String email = contact.getEmail( );
        assertTrue( "The first name is incorrect", firstName.equals( "Carolina" ) );
        assertTrue( "The last name is incorrect", lastName.equals( "Rodríguez" ) );
        assertTrue( "The address is incorrect", address.equals( "Trv 25 No. 43 - 45" ) );
        assertTrue( "the email is incorrect", email.equals( "crodriguez@uniandes.edu.co" ) );

        //tests the modification of the data
        contact.setAddress( "Cll 45 a No 45 - 23" );
        contact.setEmail( "carito2005@hotmail.com" );

        firstName = contact.getFirstName( );
        lastName = contact.getLastName( );
        address = contact.getAddress( );
        email = contact.getEmail( );
        assertTrue( "The first name is incorrect", firstName.equals( "Carolina" ) );
        assertTrue( "The last name is incorrect", lastName.equals( "Rodríguez" ) );
        assertTrue( "The address is incorrect", address.equals( "Cll 45 a No 45 - 23" ) );
        assertTrue( "the email is incorrect", email.equals( "carito2005@hotmail.com" ) );
    }

    /**
     * tests that the add method is working correctly
     */
    public void testAddPhoneNumber( )
    {
        setUpScenario1( );
        contact.addPhoneNumber( "22140732" );
        ArrayList phoneNumbers = contact.getPhoneNumbers( );
        String tel1 = ( String )phoneNumbers.get( 0 );
        String tel2 = ( String )phoneNumbers.get( 1 );
        String tel3 = ( String )phoneNumbers.get( 2 );
        assertTrue( "The phone number wasnt added correctly", tel1.equals( "6556850" ) && tel2.equals( "4859527" ) && tel3.equals( "22140732" ) );

    }

    /**
     * tests that the add keyword method is working correctly
     */
    public void testAddKeyword( )
    {
        setUpScenario1( );
        contact.addKeyword( "Carito" );
        ArrayList palabras = contact.getKeywords( );
        String pal1 = ( String )palabras.get( 0 );
        String pal2 = ( String )palabras.get( 1 );
        String pal3 = ( String )palabras.get( 2 );
        assertTrue( "The keyword wasnt added correctly", pal1.equals( "Amigos" ) && pal2.equals( "Fiesta" ) && pal3.equals( "Carito" ) );
    }

    /**
     * test that the delete phone number works correctly
     */
    public void testDeletePhoneNumber( )
    {
        setUpScenario1( );
        contact.deletePhoneNumber( "6556850" );
        ArrayList phones = contact.getPhoneNumbers( );
        String tel1 = ( String )phones.get( 0 );
        assertTrue( "The phone number wasnt deleted correctly", tel1.equals( "4859527" ) && phones.size( ) == 1 );

    }

    /**
     * test that the delete keyword works correctly
     */
    public void deleteKeyword( )
    {
        setUpScenario1( );
        contact.deleteKeyword( "Party" );
        ArrayList palabras = contact.getKeywords( );
        String pal1 = ( String )palabras.get( 0 );
        assertTrue( "the keyword wasnt added correctly", pal1.equals( "Friends" ) && palabras.size( ) == 1 );

    }

    /**
     * tests whether a particular keyword exists for a contact
     */
    public void testCheckKeyword( )
    {
        setUpScenario1( );
        assertTrue( contact.hasKeyword( "Party" ) );
        assertFalse( contact.hasKeyword( "Parteey" ) );
    }

}