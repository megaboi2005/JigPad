import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EditorFunctions {
	public EditorFunctions() {
		
	}
	
	public static void open() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // User selected a file
        	
            main.tabs.addTab(fileChooser.getSelectedFile().getAbsolutePath(),fileChooser.getSelectedFile().getName());
            main.tabs.tabbedPane.setSelectedIndex(main.tabs.tabbedPane.getTabCount()-1);
        }
        
	}
	
	public static void newtab() {
		main.tabs.addTab();
		main.tabs.tabbedPane.setSelectedIndex(main.tabs.tabbedPane.getTabCount()-1);
	}
	
	public static void closetab() {
		main.tabs.paths.remove(main.tabs.tabbedPane.getSelectedIndex());
		main.tabs.tabbedPane.remove(main.tabs.tabbedPane.getSelectedIndex());
	}
	
	public static void save() {
		if (main.tabs.paths.get(main.tabs.tabbedPane.getSelectedIndex())=="") {
			saveas();
			return;
		}
		
        try {
            FileWriter writer = new FileWriter(main.tabs.paths.get(main.tabs.tabbedPane.getSelectedIndex()));
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(main.tabs.getTabText());
            bufferedWriter.close();
            JOptionPane.showMessageDialog(main.frame, "File saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
	}
	
	public static void saveas() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try (FileWriter writer = new FileWriter(selectedFile)) {
                writer.write(main.tabs.getTabText());
                JOptionPane.showMessageDialog(main.frame, "File saved successfully!");
                main.tabs.paths.set(main.tabs.tabbedPane.getSelectedIndex(),selectedFile.getAbsolutePath());
                main.tabs.tabbedPane.setTitleAt(main.tabs.tabbedPane.getSelectedIndex(), selectedFile.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(main.frame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
	
	
	public static void exit() {
		System.exit(0);
	}
	
	public static void undo() {
		
	}
	
	public static void redo() {
		
	}
	
	public static void copy() {
		
	}
	
	public static void paste() {
		
	}
	
	public static void github() {
		
	}
}

