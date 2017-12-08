package edu.jabs.contactsList.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * Image pane that displays the contact list
 */
public class ListImagePane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Constant used to indicate that all the contacts should be displayed
     */
    private final static String ALL = "ALL";

    /**
     * Constant used to indicate that the contacts with a certain keyword are to be displayed
     */
    private final static String KEYWORD = "KEYWORD";

    /**
     * Constant used to indicate that the information for one contact is to be displayed
     */
    private final static String VIEW = "VIEW";

    /**
     * Constant used to indicate that a contact is to be deleted
     */
    private final static String DELETE = "DELETE";

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * The main window for the application
     */
    private ContactListGUI mainWindow;

    //-----------------------------------------------------------------
    // GUI Attributes
    //-----------------------------------------------------------------

    /**
     * List pane where the contacts are to be displayed
     */
    private JScrollPane listImagePane;

    /**
     * List for the contacts
     */
    private JList contactsList;

    /**
     * Button used to display the info for one particular contact
     */
    private JButton viewButton;

    /**
     * Button used to delete a contact
     */
    private JButton deleteButton;

    /**
     * Button used for the command to display all contacts
     */
    private JButton allButton;

    /**
     * Button used to search for contacts with a keyword
     */
    private JButton buttonKeyword;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates the image pane where the contact list is to be displayed
     * @param gui main window for the application. gui != null
     */
    public ListImagePane( ContactListGUI gui )
    {
        mainWindow = gui;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Contact list" ) );

        //Initializes the list
        contactsList = new JList( );
        listImagePane = new JScrollPane( );
        listImagePane.setViewportView( contactsList );
        listImagePane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        listImagePane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

        contactsList.setModel( new DefaultListModel( ) );

        //Initializes the buttons
        viewButton = new JButton( );
        viewButton.setText( "View" );
        viewButton.setActionCommand( VIEW );
        viewButton.addActionListener( this );

        deleteButton = new JButton( );
        deleteButton.setText( "Delete" );
        deleteButton.setActionCommand( DELETE );
        deleteButton.addActionListener( this );

        allButton = new JButton( );
        allButton.setText( "View all Contacts" );
        allButton.setActionCommand( ALL );
        allButton.addActionListener( this );

        buttonKeyword = new JButton( );
        buttonKeyword.setText( "Search by keyword" );
        buttonKeyword.setActionCommand( KEYWORD );
        buttonKeyword.addActionListener( this );

        //Sets the objects previously initialized
        add( listImagePane, BorderLayout.CENTER );

        JPanel panelBotones = new JPanel( );
        panelBotones.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
        panelBotones.setLayout( new GridLayout( 4, 1, 5, 5 ) );
        panelBotones.add( allButton );
        panelBotones.add( buttonKeyword );
        panelBotones.add( viewButton );
        panelBotones.add( deleteButton );

        add( panelBotones, BorderLayout.EAST );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Updates the list with the elements in the arraylist
     * @param list - the new list of names to display
     */
    public void updateList( ArrayList list )
    {
        contactsList.setListData( list.toArray( ) );
    }

    /**
     * Returns the first name of the selected contact
     * @return Contact's first name. if no contact is selected, return null
     */
    public String getSelectedName( )
    {
        String fullName = ( ( String )contactsList.getSelectedValue( ) );
        if( fullName == null )
            return null;

        String firstName = fullName.substring( 0, fullName.indexOf( " " ) );
        return firstName;
    }

    /**
     * Returns the last name of the selected contact
     * @return Last name. If there is no selected contact return null
     */
    public String getLastName( )
    {
        String fullName = ( ( String )contactsList.getSelectedValue( ) );
        if( fullName == null )
            return null;

        String lastName = fullName.substring( fullName.indexOf( " " ) + 1, fullName.length( ) );
        return lastName;
    }

    /**
     * Receives the events from the gui and executes the actions
     * @param event - the event that the user did. event != null
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );
        if( comando.equals( VIEW ) )
        {
            mainWindow.viewContact( );
        }
        else if( comando.equals( ALL ) )
        {
            updateList( mainWindow.getContacts( ) );
        }
        else if( comando.equals( KEYWORD ) )
        {
            mainWindow.findContactsKeyword( );
        }
        else if( comando.equals( DELETE ) )
        {
            mainWindow.deleteContact( );
        }

    }

}