package Presentation.Ui_Views;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import static Presentation.DictionaryPiano.DELETE_SONG;

/**
 *  ButtonColumn
 *
 *  The "ButtonColumn" class provides a renderer and an editor that looks like a
 *  JButton. The renderer and editor will then be used for a specified column
 *  in the table. The TableModel will contain the String to be displayed on
 *  the button.
 *
 *  The button can be invoked by a mouse click or by pressing the space bar
 *  when the cell has focus. Optionally a mnemonic can be set to invoke the
 *  button. When the button is invoked the provided Action is invoked. The
 *  source of the Action will be the table. The action command will contain
 *  the model row number of the button that was clicked.
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private final JTable table;
    private final Action action;
    private final Border originalBorder;
    private final JButton renderButton;
    private final JButton editButton;
    private Border focusBorder;
    private Object editorValue;

    /**
     *  Create the ButtonColumn to be used as a renderer and editor. The
     *  renderer and editor will automatically be installed on the TableColumn
     *  of the specified column.
     *
     *  @param table the table containing the button renderer/editor
     *  @param action the Action to be invoked when the button is invoked
     *  @param column the column to which the button renderer/editor is added
     */
    public ButtonColumn(JTable table, Action action, int column) {
        this.table = table;

        this.action = action;

        renderButton = new JButton();
        renderButton.setForeground(Color.red);
        //renderButton.addActionListener(action);
        editButton = new JButton();
        editButton.setForeground(Color.red);
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );
        editButton.setActionCommand(DELETE_SONG);
        originalBorder = editButton.getBorder();
        //setFocusBorder( new LineBorder(Color.BLUE) );

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
        table.addMouseListener((MouseListener) action);
    }


    /**
     * Sets an initial value for the editor. This will cause the editor to stopEditing and lose any partially edited
     * value if the editor is editing when this method is called.
     * @param table the JTable that is asking the editor to edit; can be null
     * @param value the value of the cell to be edited; it is up to the specific editor to interpret and draw the value.
     *             For example, if value is the string "true", it could be rendered as a string or it could be rendered
     *              as a check box that is checked. null is a valid value
     * @param isSelected  true if the cell is to be rendered with highlighting
     * @param row the row of the cell being edited
     * @param column the column of the cell being edited
     * @return the component for editing
     */
    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
    {
        if (value == null)
        {
            editButton.setText( "" );
            editButton.setIcon( null );
        }
        else if (value instanceof Icon)
        {
            editButton.setText( "" );
            editButton.setIcon( (Icon)value );
        }
        else
        {
            editButton.setText( value.toString() );
            editButton.setIcon( null );
        }

        this.editorValue = value;
        return editButton;
    }

    /**
     * Method that gets the editor value
     * @return the value contained in the editor.
     */
    @Override
    public Object getCellEditorValue()
    {
        return editorValue;
    }


    /**
     * During a printing operation, this method will be called with isSelected and hasFocus values of false to prevent
     * selection and focus from appearing in the printed output. To do other customization based on whether or not the
     * table is being printed, check the return value from JComponent.isPaintingForPrint().
     * @param table the JTable that is asking the renderer to draw; can be null
     * @param value the value of the cell to be rendered. It is up to the specific renderer to interpret and draw the
     *              value. For example, if value is the string "true", it could be rendered as a string or it could be
     *              rendered as a check box that is checked. null is a valid value
     * @param isSelected true if the cell is to be rendered with the selection highlighted; otherwise false
     * @param hasFocus if true, render cell appropriately. For example, put a special border on the cell, if the cell
     *                 can be edited, render in the color used to indicate editing
     * @param row the row index of the cell being drawn. When drawing the header, the value of row is -1
     * @param column the column index of the cell being drawn
     * @return the component used for drawing the cell.
     */
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        //renderButton.addActionListener(action);
        if (isSelected)
        {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setForeground(Color.blue);
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }
        else
        {
            //renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
            renderButton.setForeground(Color.black);
            //renderButton.setBackground(Color.red);
        }

        if (hasFocus) {
            renderButton.setBorder( focusBorder );
        }
        else {
            renderButton.setBorder( originalBorder );
        }

//		renderButton.setText( (value == null) ? "" : value.toString() );
        if (value == null) {
            renderButton.setText( "" );
            renderButton.setIcon( null );
        }
        else if (value instanceof Icon) {
            renderButton.setText( "" );
            renderButton.setIcon( (Icon)value );
        }
        else {
            renderButton.setText( value.toString() );
            renderButton.setIcon( null );
        }

        return renderButton;
    }

    /**
     * Method invoked when an action occurs
     * @param e Defines the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel( table.getEditingRow() );
        fireEditingStopped();

        //  Invoke the Action

        ActionEvent event = new ActionEvent(
                table,
                ActionEvent.ACTION_PERFORMED,
                "" + row);
        action.actionPerformed(event);
    }

}