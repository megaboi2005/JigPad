import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class main {
	public static JFrame frame = new JFrame("JigPad 1.0");
	public static Tabs tabs;
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        BufferedImage iconImage = null;
        try {
            iconImage = ImageIO.read(main.class.getResource("jigpad.png")); // Adjust the path as needed
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (iconImage != null) {
            frame.setIconImage(iconImage);
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        tabs = new Tabs(frame);
        Topbar top = new Topbar(frame);
        
        if (args.length > 0) {
        	String[] splittedarg = args[0].split("\\");
        	String[] splittedarg2 = args[0].split("/");
        	if (splittedarg.length < splittedarg2.length) {
        		splittedarg = splittedarg2;
        	}
        	
        	tabs.addTab(args[0],splittedarg[splittedarg.length-1]);
        
        }
        
        
        frame.setSize(1000, 500);
        frame.setVisible(true);
       
        
    }
}
