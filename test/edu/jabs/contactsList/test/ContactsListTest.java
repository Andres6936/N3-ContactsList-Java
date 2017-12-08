package edu.jabs.contactsList.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import edu.jabs.contactsList.domain.Contact;
import edu.jabs.contactsList.domain.ContactsList;

/**
 * test class for the contact list
 */
public class ContactsListTest extends TestCase
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Contact1 to run tests
     */
    private Contact contact1;

    /**
     * Contact2 to run tests
     */
    private Contact contact2;

    /**
     * Contact3 to run tests
     */
    private Contact contact3;

    /**
     * Contact list to run tests
     */
    private ContactsList list;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * creates the test scenario
     */
    public void setUpScenario1( )
    {
        contact1 = new Contact( "Carolina", "Rodríguez", "Trv 25 No. 43 - 45", "crodriguez@uniandes.edu.co" );
        contact1.addPhoneNumber( "6556850" );
        contact1.addPhoneNumber( "4859527" );
        contact1.addKeyword( "Guys" );
        contact1.addKeyword( "Party" );

        contact2 = new Contact( "Camila", "Borrero", "Cll 56 No. 67 - 76", "cborrero@uniandes.edu.co" );
        contact2.addPhoneNumber( "6456787" );
        contact2.addPhoneNumber( "5678765" );
        contact2.addKeyword( "Girls" );
        contact2.addKeyword( "Party" );

        contact3 = new Contact( "Mauricio", "Sánchez", "Av 24 No. 6 - 32", "msanchez@msanchez.com" );
        contact3.addPhoneNumber( "6785465" );
        contact3.addKeyword( "Dad" );
        contact3.addKeyword( "Family" );

        list = new ContactsList( );
        list.addContact( contact1.getFirstName( ), contact1.getLastName( ), contact1.getAddress( ), contact1.getEmail( ), contact1.getPhoneNumbers( ), contact1.getKeywords( ) );
        list.addContact( contact2.getFirstName( ), contact2.getLastName( ), contact2.getAddress( ), contact2.getEmail( ), contact2.getPhoneNumbers( ), contact2.getKeywords( ) );
        list.addContact( contact3.getFirstName( ), contact3.getLastName( ), contact3.getAddress( ), contact3.getEmail( ), contact3.getPhoneNumbers( ), contact3.getKeywords( ) );
    }

    /**
     * test the get contacts method
     */
    public void testGetContacts( )
    {
        setUpScenario1( );
        ArrayList contacts = list.getContactNames( );
        String strCon1 = ( String )contacts.get( 0 );
        String strCon2 = ( String )contacts.get( 1 );
        String strCon3 = ( String )contacts.get( 2 );

        assertTrue( "The list wasnt built correctly", strCon1.equals( "Carolina Rodríguez" ) && strCon2.equals( "Camila Borrero" ) && strCon3.equals( "Mauricio Sánchez" ) );

    }

    /**
     * test the searching thru keywords method
     */
    public void testGetContactsKeyword( )
    {
        setUpScenario1( );

        // search for more than one element
        ArrayList contacts = list.findContactsWithKeyword( "Party" );
        String strCon1 = ( String )contacts.get( 0 );
        String strCon2 = ( String )contacts.get( 1 );

        assertTrue( "The list wasnt built correctly", strCon1.equals( "Carolina Rodríguez" ) && strCon2.equals( "Camila Borrero" ) && contacts.size( ) == 2 );

        //Seach one element
        contacts = list.findContactsWithKeyword( "Family" );
        strCon1 = ( String )contacts.get( 0 );

        assertTrue( "The list wasnt built correctly", strCon1.equals( "Mauricio Sánchez" ) && contacts.size( ) == 1 );

        //Search by name
        contacts = list.findContactsWithKeyword( "Camila" );
        strCon1 = ( String )contacts.get( 0 );

        assertTrue( "The list wasnt built correctly", strCon1.equals( "Camila Borrero" ) && contacts.size( ) == 1 );
    }

    /**
     * tests that the application gets all the contacts
     */
    public void testFindContact( )
    {
        setUpScenario1( );
        Contact c = list.findContact( "Camila", "Borrero" );
        assertNotNull( c );
        assertEquals( "Camila", c.getFirstName( ) );
        assertEquals( "Borrero", c.getLastName( ) );
        ArrayList telefonos = c.getPhoneNumbers( );
        assertEquals( 2, telefonos.size( ) );
        ArrayList palabras = c.getKeywords( );
        assertEquals( 4, palabras.size( ) );
        assertEquals( "6456787", ( String )telefonos.get( 0 ) );
        assertEquals( "5678765", ( String )telefonos.get( 1 ) );
        assertEquals( "Girls", ( String )palabras.get( 0 ) );
        assertEquals( "Party", ( String )palabras.get( 1 ) );
    }

    /**
     * test that the search for a contact not on the list returns null
     */
    public void testFindIntexistentContact( )
    {
        setUpScenario1( );
        Contact c = list.findContact( "Pedro", "Pérez" );
        assertNull( c );
    }

    /**
     * tests that a contact already on the list isn't added again
     */
    public void testAgregarContacto( )
    {
        setUpScenario1( );

        contact1 = new Contact( "Mauricio", "Sánchez", "Av 24 No. 6 - 34", "msanchez1@msanchez.com" );
        boolean accion = list.addContact( contact1.getFirstName( ), contact1.getLastName( ), contact1.getAddress( ), contact1.getEmail( ), contact1.getPhoneNumbers( ), contact1.getKeywords( ) );
        assertFalse( "if the contact already exists it shouldn't be added on the list", accion );

    }

    /**
     * tests the contact delete method
     */
    public void testDeleteContact( )
    {
        setUpScenario1( );

        //tests the elimination of a contact that isn't on the list
        boolean accion = list.deleteContact( "Diana", "Puentes" );
        assertTrue( "you cant delete a contact that isn't on the list", accion == false );

        //tests the elimination of a contact that is on the list
        list.deleteContact( "Mauricio", "Sánchez" );
        ArrayList conts = list.findContactsWithKeyword( "Familia" );
        assertTrue( "The contact wasn't correctly deleted", conts.size( ) == 0 );

    }

    /**
     * tests the modify contact method
     */
    public void testModifyContact( )
    {
        setUpScenario1( );

        //tests the modification of a contact that isn't on the list
        contact1 = new Contact( "Pedro", "Sánchez", "Av 24 No. 6 - 34", "msanchez1@msanchez.com" );

        boolean action = list.modifyContact( contact1.getFirstName( ), contact1.getLastName( ), contact1.getAddress( ), contact1.getEmail( ), contact1.getPhoneNumbers( ), contact1.getKeywords( ) );
        assertFalse( "The contact doesn't exists", action );

        //tests the modification of a contact that is on the list
        ArrayList phones1 = new ArrayList( );
        ArrayList keywords1 = new ArrayList( );

        list.modifyContact( "Mauricio", "Sánchez", "Av 24 No. 6 - 44", "msanchez1@msanchez.com", phones1, keywords1 );

        Contact c = list.findContact( "Mauricio", "Sánchez" );
        assertEquals( "Av 24 No. 6 - 44", c.getAddress( ) );
        assertEquals( "the contact wasn't modified correctly, (the keywords remain unchanged)", 0, c.getKeywords( ).size( ) );
        assertEquals( "the contact wasn't modified correctly, (the keywords remain unchanged)", 0, c.getPhoneNumbers( ).size( ) );

    }

}
