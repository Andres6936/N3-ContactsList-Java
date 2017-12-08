package edu.jabs.contactsList.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import edu.jabs.contactsList.domain.Contact;

/**
 * Image pane where all the contact's information is displayed
 */
public class ContactImagePane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Constant used to indicate that the user wants the screen cleaned
     */
    private final static String CLEAN = "CLEAN";

    /**
     * Constant used to indicate that a contact is to be added
     */
    private final static String ADD = "ADD";

    /**
     * Constant used to modify the information for a contact
     */
    private final static String MODIFY = "MODIFY";

    /**
     * Constant used to indicate that a phone number is to be added to the contact
     */
    private final static String ADD_PHONE_NUMBER = "ADD_PHONE_NUMBER";

    /**
     * Constant used to delete a contact from the list
     */
    private final static String DELETE_PHONE_NUMBER = "DELETE_PHONE_NUMBER";

    /**
     * Constant used to indicate that a keyword is to be added to the list
     */
    private final static String ADD_KEYWORD = "ADD_KEYWORD";

    /**
     * Constant used to delete a keyword from the list
     */
    private final static String DELETE_KEYWORD = "DELETE_KEYWORD";

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

    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField emailField;

    private JList phoneNumbersList;
    private JList keywordsList;

    private JButton cleanButton;
    private JButton addButton;
    private JButton modifyButton;

    private JButton addKeywordButton;
    private JButton deleteKeywordButton;
    private JButton addPhoneNumberButton;
    private JButton deletePhoneNumberButton;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * Creates the image pane to view, modify and create new contacts
     * @param gui main window for the application. gui != null
     */
    public ContactImagePane( ContactListGUI gui )
    {
        mainWindow = gui;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Contact's personal information" ) );

        //initializes and organizes the main information of the contact
        organizeData( );

        //Initializes and organizes the elements for the buttons
        organizeButtons( );

        //Organizes and initializes the set of keywords and phone numbers
        // for the contact
        organizeLists( );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * Initializes and organizes the buttons of the image pane
     */
    private void organizeButtons( )
    {
        JPanel buttonsImagePane = new JPanel( );

        addButton = new JButton( );
        addButton.setText( "Add Contact" );
        addButton.setActionCommand( ADD );
        addButton.addActionListener( this );

        modifyButton = new JButton( );
        modifyButton.setText( "Modify Contact" );
        modifyButton.setActionCommand( MODIFY );
        modifyButton.addActionListener( this );

        cleanButton = new JButton( );
        cleanButton.setText( "Clean" );
        cleanButton.setActionCommand( CLEAN );
        cleanButton.addActionListener( this );

        buttonsImagePane.add( addButton );
        buttonsImagePane.add( modifyButton );
        buttonsImagePane.add( cleanButton );

        add( buttonsImagePane, BorderLayout.SOUTH );
    }

    /**
     * Initializes and organizes the graphic elements that will display the contacts information
     */
    private void organizeData( )
    {
        JPanel dataImagePane = new JPanel( );
        dataImagePane.setBorder( BorderFactory.createEmptyBorder( 20, 5, 20, 5 ) );
        dataImagePane.setLayout( new GridLayout( 4, 2, 1, 10 ) );

        firstNameLabel = new JLabel( "First name" );
        lastNameLabel = new JLabel( "Last name" );
        addressLabel = new JLabel( "Address" );
        emailLabel = new JLabel( "Email" );

        firstNameField = new JTextField( 10 );
        lastNameField = new JTextField( 10 );
        addressField = new JTextField( 10 );
        emailField = new JTextField( 10 );

        dataImagePane.add( firstNameLabel );
        dataImagePane.add( firstNameField );
        dataImagePane.add( lastNameLabel );
        dataImagePane.add( lastNameField );
        dataImagePane.add( addressLabel );
        dataImagePane.add( addressField );
        dataImagePane.add( emailLabel );
        dataImagePane.add( emailField );

        add( dataImagePane, BorderLayout.CENTER );
    }

    /**
     * Organizes and initializes the contacts list of phone numbers and keywords
     */
    private void organizeLists( )
    {
        //List of phone numbers

        JPanel phoneImagePane = new JPanel( );
        phoneImagePane.setLayout( new BorderLayout( ) );
        phoneImagePane.setBorder( new TitledBorder( "Phone numbers" ) );

        JScrollPane deslizableTelefonos = new JScrollPane( );
        phoneNumbersList = new JList( );
        deslizableTelefonos.setPreferredSize( new Dimension( 150, 0 ) );
        deslizableTelefonos.setViewportView( phoneNumbersList );
        deslizableTelefonos.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        deslizableTelefonos.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        phoneNumbersList.setModel( new DefaultListModel( ) );

        addPhoneNumberButton = new JButton( "Add" );
        addPhoneNumberButton.setActionCommand( ADD_PHONE_NUMBER );
        addPhoneNumberButton.addActionListener( this );

        deletePhoneNumberButton = new JButton( "Delete" );
        deletePhoneNumberButton.setActionCommand( DELETE_PHONE_NUMBER );
        deletePhoneNumberButton.addActionListener( this );

        JPanel phoneButtonsImagePane = new JPanel( new GridLayout( 2, 1 ) );
        phoneButtonsImagePane.add( addPhoneNumberButton );
        phoneButtonsImagePane.add( deletePhoneNumberButton );

        phoneImagePane.add( deslizableTelefonos, BorderLayout.CENTER );
        phoneImagePane.add( phoneButtonsImagePane, BorderLayout.EAST );

        //list of keywords

        JPanel keywordsImagePane = new JPanel( );
        keywordsImagePane.setLayout( new BorderLayout( ) );
        keywordsImagePane.setBorder( new TitledBorder( "Keywords" ) );

        JScrollPane slidableWords = new JScrollPane( );
        keywordsList = new JList( );
        slidableWords.setPreferredSize( new Dimension( 150, 0 ) );
        slidableWords.setViewportView( keywordsList );
        slidableWords.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        slidableWords.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        keywordsList.setModel( new DefaultListModel( ) );

        addKeywordButton = new JButton( "Add" );
        addKeywordButton.setActionCommand( ADD_KEYWORD );
        addKeywordButton.addActionListener( this );

        deleteKeywordButton = new JButton( "Delete" );
        deleteKeywordButton.setActionCommand( DELETE_KEYWORD );
        deleteKeywordButton.addActionListener( this );

        JPanel buttonsImagePane = new JPanel( new GridLayout( 2, 1 ) );
        buttonsImagePane.add( addKeywordButton );
        buttonsImagePane.add( deleteKeywordButton );

        keywordsImagePane.add( slidableWords, BorderLayout.CENTER );
        keywordsImagePane.add( buttonsImagePane, BorderLayout.EAST );

        JPanel listsImagePane = new JPanel( new GridLayout( 2, 1 ) );
        listsImagePane.add( phoneImagePane );
        listsImagePane.add( keywordsImagePane );

        add( listsImagePane, BorderLayout.EAST );
    }

    /**
     * Returns the name in the form
     * @return first name
     */
    public String getFirstName( )
    {
        return firstNameField.getText( ).trim( );
    }
    /**
     * Returns the last name in the form
     * @return last name
     */
    public String getLastName( )
    {
        return lastNameField.getText( ).trim( );
    }
    /**
     * Returns the address in the form
     * @return address
     */
    public String getAddress( )
    {
        return addressField.getText( ).trim( );
    }
    /**
     * Returns the email in the form
     * @return email
     */
    public String getEmail( )
    {
        return emailField.getText( ).trim( );
    }
    /**
     * Returns the list of keywords for that contact
     * @return list of keywords
     */
    public ArrayList getKeywords( )
    {
        int counter = 0;
        ArrayList words = new ArrayList( );
        while( counter < keywordsList.getModel( ).getSize( ) )
        {
            words.add( keywordsList.getModel( ).getElementAt( counter ) );
            counter++;
        }
        return words;
    }

    /**
     * Returns the list of phone numbers for that contact
     * @return - arrayList
     */
    public ArrayList getPhoneNumbers( )
    {
        int counter = 0;
        ArrayList phones = new ArrayList( );
        while( counter < phoneNumbersList.getModel( ).getSize( ) )
        {
            phones.add( phoneNumbersList.getModel( ).getElementAt( counter ) );
            counter++;
        }
        return phones;
    }

    /**
     * Clears the info from the image pane
     */
    public void clearFields( )
    {
        phoneNumbersList.setModel( new DefaultListModel( ) );
        keywordsList.setModel( new DefaultListModel( ) );
        firstNameField.setText( "" );
        lastNameField.setText( "" );
        addressField.setText( "" );
        emailField.setText( "" );
    }

    /**
     * Adds a new phone number to the list
     */
    private void addPhoneNumber( )
    {
        String telephone = JOptionPane.showInputDialog( this, "Type the new phone number", "New Phone number", JOptionPane.PLAIN_MESSAGE );

        DefaultListModel dlf = new DefaultListModel( );
        int contador = 0;
        while( contador < phoneNumbersList.getModel( ).getSize( ) )
        {
            dlf.addElement( phoneNumbersList.getModel( ).getElementAt( contador ) );
            contador++;
        }
        dlf.addElement( telephone );
        phoneNumbersList.setModel( dlf );

    }

    /**
     * Adds a new keyword to the list
     */
    private void addKeyword( )
    {
        String word = JOptionPane.showInputDialog( this, "Type the new keyword", "New Keyword", JOptionPane.PLAIN_MESSAGE );
        DefaultListModel dlf = new DefaultListModel( );
        int counter = 0;
        while( counter < keywordsList.getModel( ).getSize( ) )
        {
            dlf.addElement( keywordsList.getModel( ).getElementAt( counter ) );
            counter++;
        }
        dlf.addElement( word );
        keywordsList.setModel( dlf );
    }

    /**
     * EDeletes a phone number from the list
     */
    private void deletePhoneNumber( )
    {
        String telephone = ( String )phoneNumbersList.getSelectedValue( );
        if( telephone == null )
        {
            JOptionPane.showMessageDialog( this, "You must select a phone number", "List of Contacts", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            DefaultListModel dlf = new DefaultListModel( );
            int counter = 0;
            while( counter < phoneNumbersList.getModel( ).getSize( ) )
            {
                String elemento = ( String )phoneNumbersList.getModel( ).getElementAt( counter );
                if( !elemento.equals( telephone ) )
                    dlf.addElement( phoneNumbersList.getModel( ).getElementAt( counter ) );
                counter++;
            }
            phoneNumbersList.setModel( dlf );
        }
    }

    /**
     * Deletes a keyword from the list
     */
    private void deleteKeyword( )
    {
        String word = ( String )keywordsList.getSelectedValue( );
        if( word == null )
        {
            JOptionPane.showMessageDialog( this, "You must select a keyword", "List of Contacts", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            DefaultListModel dlf = new DefaultListModel( );
            int counter = 0;
            while( counter < keywordsList.getModel( ).getSize( ) )
            {
                String elemento = ( String )keywordsList.getModel( ).getElementAt( counter );
                if( !elemento.equals( word ) )
                    dlf.addElement( keywordsList.getModel( ).getElementAt( counter ) );
                counter++;
            }
            keywordsList.setModel( dlf );
        }
    }

    /**
     * Updates the list of phone numbers with the new elements on the arraylist
     * @param list - the new list to be displayed
     */
    private void updatePhoneNumberList( ArrayList list )
    {
        DefaultListModel dlf = new DefaultListModel( );

        int counter = 0;
        while( counter < list.size( ) )
        {
            String tel = ( String )list.get( counter );
            dlf.addElement( tel );
            counter++;
        }

        phoneNumbersList.setModel( dlf );

    }

    /**
     * Updates the list of keywords with the new elements from the arrayList
     * @param list - the new list of keywords to display
     */
    private void updateKeywordList( ArrayList list )
    {
        DefaultListModel dlf = new DefaultListModel( );

        int counter = 0;
        while( counter < list.size( ) )
        {
            dlf.addElement( list.get( counter ) );
            counter++;
        }

        keywordsList.setModel( dlf );
    }

    /**
     * Displays the information for the given contact
     * @param contact Contact whose information is to be displayed
     */
    public void displayContact( Contact contact )
    {
        firstNameField.setText( contact.getFirstName( ) );
        lastNameField.setText( contact.getLastName( ) );
        addressField.setText( contact.getAddress( ) );
        emailField.setText( contact.getEmail( ) );
        updateKeywordList( contact.getKeywords( ) );
        updatePhoneNumberList( contact.getPhoneNumbers( ) );
    }

    /**
     * receives the events from the gui and executes the according actions
     * @param event the event the user did. event != null
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );
        if( comando.equals( ADD ) )
        {
            mainWindow.addContact( );
        }
        else if( comando.equals( MODIFY ) )
        {
            mainWindow.modifyContact( );
        }
        else if( comando.equals( CLEAN ) )
        {
            clearFields( );
        }
        else if( comando.equals( ADD_PHONE_NUMBER ) )
        {
            addPhoneNumber( );
        }
        else if( comando.equals( ADD_KEYWORD ) )
        {
            addKeyword( );
        }
        else if( comando.equals( DELETE_PHONE_NUMBER ) )
        {
            deletePhoneNumber( );
        }
        else if( comando.equals( DELETE_KEYWORD ) )
        {
            deleteKeyword( );
        }
    }
}
