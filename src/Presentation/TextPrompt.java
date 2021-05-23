package Presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *  The TextPrompt class will display a prompt over top of a text component when
 *  the Document of the text field is empty. The Show property is used to
 *  determine the visibility of the prompt.
 *
 *  The Font and foreground Color of the prompt will default to those properties
 *  of the parent text component. You are free to change the properties after
 *  class construction.
 */
public class TextPrompt extends JLabel implements FocusListener, DocumentListener {
    public enum Show {
        ALWAYS,
        FOCUS_GAINED,
        FOCUS_LOST
    }

    /*
    Component of the TextPrompt
     */
    private final JTextComponent component;

    /*
    Document of the TextPrompt
     */
    private final Document document;

    /*
    Attribute that defines if shown or not
     */
    private Show show;

    private boolean showPromptOnce;

    /*
    int defining if the focus is lost or not
     */
    private int focusLost;

    /**
     * Constructor of the class TextPrompt
     * @param text Defines the text to be written
     * @param component Defines the component where the text is
     * @param show Defines the attribute show
     */
    public TextPrompt(String text, JTextComponent component, Show show)
    {
        this.component = component;
        setShow( show );
        document = component.getDocument();

        setText( text );
        setFont( component.getFont() );
        setForeground( component.getForeground() );
        setBorder( new EmptyBorder(component.getInsets()) );
        setHorizontalAlignment(JLabel.LEADING);

        component.addFocusListener( this );
        document.addDocumentListener( this );

        component.setLayout( new BorderLayout() );
        component.add( this );
        checkForPrompt();
    }

    /**
     *  Convenience method to change the alpha value of the current foreground
     *  Color to the specifice value.
     *
     *  @param alpha value in the range of 0 - 1.0.
     */
    public void changeAlpha(float alpha)
    {
        changeAlpha( (int)(alpha * 255) );
    }

    /**
     *  Convenience method to change the alpha value of the current foreground
     *  Color to the specifice value.
     *
     *  @param alpha value in the range of 0 - 255.
     */
    public void changeAlpha(int alpha)
    {
        alpha = alpha > 255 ? 255 : Math.max(alpha, 0);

        Color foreground = getForeground();
        int red = foreground.getRed();
        int green = foreground.getGreen();
        int blue = foreground.getBlue();

        Color withAlpha = new Color(red, green, blue, alpha);
        super.setForeground( withAlpha );
    }

    /**
     *  Convenience method to change the style of the current Font. The style
     *  values are found in the Font class. Common values might be:
     *  Font.BOLD, Font.ITALIC and Font.BOLD + Font.ITALIC.
     *
     *  @param style value representing the the new style of the Font.
     */
    public void changeStyle(int style)
    {
        setFont( getFont().deriveFont( style ) );
    }

    /**
     *  Get the Show property
     *
     *  @return the Show property.
     */
    public Show getShow()
    {
        return show;
    }

    /**
     *  Set the prompt Show property to control when the promt is shown.
     *  Valid values are:
     *
     *  Show.AWLAYS (default) - always show the prompt
     *  Show.Focus_GAINED - show the prompt when the component gains focus
     *      (and hide the prompt when focus is lost)
     *  Show.Focus_LOST - show the prompt when the component loses focus
     *      (and hide the prompt when focus is gained)
     *
     *  @param show a valid Show enum
     */
    public void setShow(Show show)
    {
        this.show = show;
    }

    /**
     *  Get the showPromptOnce property
     *
     *  @return the showPromptOnce property.
     */
    public boolean getShowPromptOnce()
    {
        return showPromptOnce;
    }

    /**
     *  Show the prompt once. Once the component has gained/lost focus
     *  once, the prompt will not be shown again.
     *
     *  @param showPromptOnce  when true the prompt will only be shown once,
     *                         otherwise it will be shown repeatedly.
     */
    public void setShowPromptOnce(boolean showPromptOnce)
    {
        this.showPromptOnce = showPromptOnce;
    }

    /**
     *	Check whether the prompt should be visible or not. The visibility
     *  will change on updates to the Document and on focus changes.
     */
    private void checkForPrompt()
    {
        //  Text has been entered, remove the prompt

        if (document.getLength() > 0)
        {
            setVisible( false );
            return;
        }

        //  Prompt has already been shown once, remove it

        if (showPromptOnce && focusLost > 0)
        {
            setVisible(false);
            return;
        }

        //  Check the Show property and component focus to determine if the
        //  prompt should be displayed.

        if (component.hasFocus())
        {
            setVisible(show == Show.ALWAYS
                    || show == Show.FOCUS_GAINED);
        }
        else
        {
            setVisible(show == Show.ALWAYS
                    || show == Show.FOCUS_LOST);
        }
    }

//  Implement FocusListener

    /**
     * Called when the component containing the caret gains
     * focus.  This is implemented to repaint the component
     * so the focus rectangle will be re-rendered, as well
     * as providing the superclass behavior.
     *
     * @param e the focus event
     */
    public void focusGained(FocusEvent e)
    {
        checkForPrompt();
    }

    /**
     * Called when the component containing the caret loses
     * focus.  This is implemented to set the caret to visibility
     * to false.
     *
     * @param e the focus event
     */
    public void focusLost(FocusEvent e)
    {
        focusLost++;
        checkForPrompt();
    }

//  Implement DocumentListener

    /**
     * Gives notification that there was an insert into the document. The range given by the DocumentEvent bounds the freshly inserted region.
     *
     * @param e the document event
     */
    public void insertUpdate(DocumentEvent e) { checkForPrompt(); }

    /**
     * The remove notification.  Gets sent to the root of the view structure
     * that represents the portion of the model being represented by the
     * editor.  The factory is added as an argument to the update so that
     * the views can update themselves in a dynamic (not hardcoded) way.
     *
     * @param e  The change notification from the currently associated
     *  document.
     */
    public void removeUpdate(DocumentEvent e) { checkForPrompt(); }

    /**
     * The change notification.  Gets sent to the root of the view structure
     * that represents the portion of the model being represented by the
     * editor.  The factory is added as an argument to the update so that
     * the views can update themselves in a dynamic (not hardcoded) way.
     *
     * @param e  The change notification from the currently associated
     *  document.
     */
    public void changedUpdate(DocumentEvent e) {}
}