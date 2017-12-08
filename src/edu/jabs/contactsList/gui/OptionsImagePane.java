package edu.jabs.contactsList.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Image pane for the extension buttons on this exercise
 */
public class OptionsImagePane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Constant OPTION_1, used to execute the option for extension 1
     */
    private final static String OPTION_1 = "OPTION_1";

    /**
     * Constant OPTION_2, used to execute the option for extension 2
     */
    private final static String OPTION_2 = "OPTION_2";

    //-----------------------------------------------------------------
    // Attributes
    //-----------------------------------------------------------------

    /**
     * Main window for the application
     */
    private ContactListGUI mainWindow;

    //-----------------------------------------------------------------
    // GUI Attributes
    //-----------------------------------------------------------------

    /**
     * Button to execute extension1
     */
    private JButton option1;

    /**
     * Button to execute extension2
     */
    private JButton option2;

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Creates a new image pane with the buttons for the extension points
     * @param gui main window for the application. gui != null
     */
    public OptionsImagePane( ContactListGUI gui )
    {
        mainWindow = gui;

        setBorder( new TitledBorder( "Extensions" ) );

        option1 = new JButton( );
        option1.setText( "Option 1" );
        option1.setActionCommand( OPTION_1 );
        option1.addActionListener( this );

        option2 = new JButton( );
        option2.setText( "Option 2" );
        option2.setActionCommand( OPTION_2 );
        option2.addActionListener( this );

        add( option1 );
        add( option2 );
    }

    /**
     * handles the actions to be taken depending on the user's commands <br>
     * <b>post: </b> one of the two extension points is executed
     * @param event event that was generated. event != null
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        if( command.equals( OPTION_1 ) )
        {
            mainWindow.funcReqOption1( );
        }
        else if( command.equals( OPTION_2 ) )
        {
            mainWindow.reqFuncOpcion2( );
        }
    }
}