import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

public class Topbar {
	
	public Topbar(JFrame frame) {
		JPopupMenu[] menus = {new JPopupMenu(),new JPopupMenu(),new JPopupMenu()};
		JButton[] buttons = {new JButton("File"),new JButton("Edit"),new JButton("Help")};
		String[][] elements = {
				{"New - ctrl+t","Open ctrl+o","Save - ctrl+s","Save as - ctrl+shift+s","Close Tab ctrl+w","Exit - alt+f4"},
				{"Undo","Redo","Copy","Paste"},
				{"Github"},
		};
		
		Runnable[][] methods = {
			    {EditorFunctions::newtab,EditorFunctions::open, EditorFunctions::save, EditorFunctions::saveas, EditorFunctions::closetab ,EditorFunctions::exit},
			    {EditorFunctions::undo, EditorFunctions::redo, EditorFunctions::copy, EditorFunctions::paste},
			    {EditorFunctions::github}
		};
		
		int[][] shortcuts = {
				{KeyEvent.VK_T,KeyEvent.VK_O,KeyEvent.VK_S,KeyEvent.VK_S,KeyEvent.VK_W,-1},
				{KeyEvent.VK_Z,KeyEvent.VK_Y,-1,-1},
				{KeyEvent.VK_H}
		};
		
		int[][] modifiers = {
				{InputEvent.CTRL_DOWN_MASK,InputEvent.CTRL_DOWN_MASK,InputEvent.CTRL_DOWN_MASK,InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK,InputEvent.CTRL_DOWN_MASK,InputEvent.CTRL_DOWN_MASK},
				{InputEvent.CTRL_DOWN_MASK,InputEvent.CTRL_DOWN_MASK,InputEvent.CTRL_DOWN_MASK,InputEvent.CTRL_DOWN_MASK},
				{InputEvent.CTRL_DOWN_MASK}
		};
		
		//InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		frame.add(buttonPanel, BorderLayout.NORTH);

		
		for (int i=0;i<elements.length;i++) {
			for (int i2=0;i2<elements[i].length;i2++) {
				final Runnable function = methods[i][i2];
				JMenuItem item =new JMenuItem(elements[i][i2]);

		        Action action = new AbstractAction() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	function.run();
		            }
		        };
		        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortcuts[i][i2], modifiers[i][i2]), elements[i][i2]);
		        frame.getRootPane().getActionMap().put(elements[i][i2], action);
				menus[i].add(item);

		        item.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                function.run();
		            }
		        });
			}
		}
		
		
		for (int i=0;i<buttons.length;i++) {
			final int index = i;
			//buttons[i].setBorder(BorderFactory.createEmptyBorder());
			buttonPanel.add(buttons[i]);
			buttons[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                menus[index].show(buttons[index], 0, buttons[index].getHeight());
	            }
	        });
		}
	}
	

}
