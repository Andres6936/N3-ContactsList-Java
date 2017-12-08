package edu.jabs.contactsList.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.jabs.contactsList.domain.Contact;
import edu.jabs.contactsList.domain.ContactsList;

/**
 * Main window for the list of contacts
 */
public class ContactListGUI extends JFrame
{
    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------
    /**
     * Contact list
     */
    private ContactsList list;
    /**
     * Image pane used to display the contact list
     */
    private ListImagePane listImagePane;
    /**
     * Image pane used to display the contact info
     */
    private ContactImagePane contactImagePane;
    /**
     * Option's image pane
     */
    private OptionsImagePane optionsImagePane;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates the main window for the application
     */
    public ContactListGUI( )
    {
        setLayout( new BorderLayout( ) );
        setSize( 550, 530 );
        setResizable( false );

        listImagePane = new ListImagePane( this );
        contactImagePane = new ContactImagePane( this );
        optionsImagePane = new OptionsImagePane( this );

        setTitle( "Contacts List" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        add( listImagePane, BorderLayout.NORTH );
        add( contactImagePane, BorderLayout.CENTER );
        add( optionsImagePane, BorderLayout.SOUTH );

        list = new ContactsList( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Returns the list of all the contacts' names
     * @return list with first and last names for the contacts.
     */
    public ArrayList getContacts( )
    {
        return list.getContactNames( );
    }

    /**
     * Displays the info for the selected contact
     */
    public void viewContact( )
    {
        String firstName = listImagePane.getSelectedName( );
        String lastName = listImagePane.getLastName( );
        if( firstName == null )
        {
            JOptionPane.showMessageDialog( this, "You have not selected any contact", "Contacts' list", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            Contact c = list.findContact( firstName, lastName );
            if( c != null )
                contactImagePane.displayContact( c );
            else
                JOptionPane.showMessageDialog( this, "That contact does note exist", "Contacts' list", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Deletes the selected contact <b>post: </b> if it exist, the contact is deleted from the list<br>
     */
    public void deleteContact( )
    {
        String firstName = listImagePane.getSelectedName( );
        String lastName = listImagePane.getLastName( );
        if( firstName == null )
        {
            JOptionPane.showMessageDialog( this, "You have not selected any contact", "Contacts' list", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            boolean action = list.deleteContact( firstName, lastName );

            if( action )
            {
                listImagePane.updateList( list.getContactNames( ) );
                contactImagePane.clearFields( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "The contact can not be deleted", "Contacts' list", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     *Looks for and then displays all contacts that contain a particular keyword
     */
    public void findContactsKeyword( )
    {
        String word = JOptionPane.showInputDialog( this, "Please type the keyword that is to be used in the search", "Contacts' list", JOptionPane.PLAIN_MESSAGE );
        if( word == null )
            return;
        else if( word.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "you must type in a keyword", "Contacts' list", JOptionPane.INFORMATION_MESSAGE );
            return;
        }
        ArrayList contactL = list.findContactsWithKeyword( word );
        if( contactL.size( ) == 0 )
            JOptionPane.showMessageDialog( this, "No contacts containing that keyword were found", "Contacts' list", JOptionPane.INFORMATION_MESSAGE );
        else
            listImagePane.updateList( contactL );
    }

    /**
     * Adds a new contact to the list if there isn't already a contact with those same first and last names <br>
     * <b>post: </b> a new contact is added to the list if it wasn't already on it<br>
     */
    public void addContact( )
    {
        String firstName = contactImagePane.getFirstName( );
        String lastName = contactImagePane.getLastName( );
        if( firstName.equals( "" ) || lastName.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "The first and last names of the contact are required", "Contacts' list", JOptionPane.ERROR_MESSAGE );
            return;
        }

        String address = contactImagePane.getAddress( );
        String email = contactImagePane.getEmail( );
        ArrayList words = contactImagePane.getKeywords( );
        ArrayList phoneNumbers = contactImagePane.getPhoneNumbers( );

        boolean action = list.addContact( firstName, lastName, address, email, phoneNumbers, words );
        if( !action )
        {
            JOptionPane.showMessageDialog( this, "A contact with that first and last name already exists", "Contacts' list", JOptionPane.ERROR_MESSAGE );
            return;
        }
        listImagePane.updateList( list.getContactNames( ) );
        contactImagePane.clearFields( );
    }

    /**
     * Modifies the information for a particular contact with the exception of the first and last names <br>
     * <b>post: </b> the information is updated if the contact exists <br>
     */
    public void modifyContact( )
    {
        String firstName = contactImagePane.getFirstName( );
        String lastName = contactImagePane.getLastName( );
        if( firstName.equals( "" ) || lastName.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "The first and last names of the contact are required", "Contacts' list", JOptionPane.ERROR_MESSAGE );
            return;
        }

        String address = contactImagePane.getAddress( );
        String email = contactImagePane.getEmail( );
        ArrayList words = contactImagePane.getKeywords( );
        ArrayList phoneNumbers = contactImagePane.getPhoneNumbers( );

        boolean action = list.modifyContact( firstName, lastName, address, email, phoneNumbers, words );
        if( !action )
        {
            JOptionPane.showMessageDialog( this, "El contacto no existe. Verifique el nombre y apellido", "Contacts' list", JOptionPane.ERROR_MESSAGE );
            return;
        }
        listImagePane.updateList( list.getContactNames( ) );
        contactImagePane.clearFields( );
    }

    //-----------------------------------------------------------------
    // Extension points
    //-----------------------------------------------------------------

    /**
     * calls the domain method1 to obtain extension 1
     */
    public void funcReqOption1( )
    {
        String answer = list.method1( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * calls the domain method2 to obtain extension 2
     */
    public void reqFuncOpcion2( )
    {
        String answer = list.method2( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Execution
    //-----------------------------------------------------------------

    /**
     * Initializes the application
     * @param args Application initial arguments, none are required
     */
    public static void main( String[] args )
    {
        ContactListGUI i = new ContactListGUI( );
        i.setVisible( true );
    }
}