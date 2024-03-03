import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths; 
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
            iconImage = ImageIO.read(main.class.getResource("jigpad.png"));
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
        	String name = Paths.get(args[0]).getFileName().toString();
        	try {
        		if (!name.substring(3).equals(".txt")) {
        		name = name+".txt";
        		}
        	} catch (Exception e) {
        		name = name+".txt";
        	}
        	tabs.addTab(args[0],name);
        	tabs.tabbedPane.setSelectedIndex(1);
        }
        
        
        frame.setSize(1000, 500);
        frame.setVisible(true);
       
        
    }
}
