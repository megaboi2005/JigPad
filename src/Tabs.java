import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Tabs {
	public JTabbedPane tabbedPane = new JTabbedPane();
	public ArrayList<String> paths = new ArrayList<String>();
	
	public Tabs(JFrame frame) {
		JPanel containerPanel = new JPanel(new BorderLayout());
		
		JPanel TabsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		containerPanel.add(TabsPanel);
		containerPanel.add(TabsPanel, BorderLayout.CENTER);
		
		TabsPanel.add(tabbedPane);
		frame.getContentPane().add(containerPanel);
		addTab();

		
		
		
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getContentPane().getWidth();
                Dimension preferredSize = new Dimension(width, tabbedPane.getPreferredSize().height);
                tabbedPane.setPreferredSize(preferredSize);
                tabbedPane.revalidate();
            }
        });
	}
	
	public void addTab() {
		JPanel tab = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
		JTextArea textArea = new JTextArea(35,20);
		textArea.setBorder(BorderFactory.createEmptyBorder());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
        tab.add(new JScrollPane(textArea),gbc);
        tabbedPane.addTab("New", tab);
        paths.add("");
	}
	
	public void addTab(String string, String name) {
		JPanel tab = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
		JTextArea textArea = new JTextArea(35,20);
		textArea.setBorder(BorderFactory.createEmptyBorder());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
        tab.add(new JScrollPane(textArea),gbc);
        tabbedPane.addTab(name, tab);
        paths.add(string);
        Path filePath = Paths.get(string);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            textArea.setText(String.join("\n", lines));
            
        } catch (NoSuchFileException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        
	}
	
	// honestly fucking help this is so gay
	public String getTabText() {
	    int selectedIndex = tabbedPane.getSelectedIndex();
	    if (selectedIndex != -1) {
	        Component selectedComponent = tabbedPane.getComponentAt(selectedIndex);
	        if (selectedComponent instanceof JPanel) {
	            JPanel selectedPanel = (JPanel) selectedComponent;
	            for (Component component : selectedPanel.getComponents()) {
	                if (component instanceof JScrollPane) {
	                    JScrollPane scrollPane = (JScrollPane) component;
	                    Component viewportView = scrollPane.getViewport().getView();
	                    if (viewportView instanceof JTextArea) {
	                        JTextArea textArea = (JTextArea) viewportView;
	                        return textArea.getText();
	                    }
	                }
	            }
	        }
	    }
	    return null; 
	}

	
	public String getPath() {
		return paths.get(tabbedPane.getSelectedIndex());
	}
}
