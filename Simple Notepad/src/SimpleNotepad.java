import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TexturePaint;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Robot;

public class SimpleNotepad extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;

	public static void main(String[] args) {
		
				try {
					SimpleNotepad frame = new SimpleNotepad();
					frame.setSize(1366, 768);
					frame.setResizable(true);
					frame.setLocation(0, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	public SimpleNotepad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 390);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveFile();
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenFile();
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, 
							"Created by \nPravin Desai \n"+
							"https://github.com/pravindesai",
							"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		mnHelp.add(mntmAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 textPane = new JTextPane();
		 textPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textPane.setBounds(0, 0, 1366, 768);
		contentPane.add(textPane);
		
	}
	
	public void SaveFile() 
	{
		try {
			JFileChooser chooser=new JFileChooser();
			int op=chooser.showOpenDialog(null);
			
			if(op==JFileChooser.APPROVE_OPTION)
			{
				String FileName=chooser.getSelectedFile().getName();		
				String FilePath=chooser.getSelectedFile().getPath();
				
				File newfile=chooser.getSelectedFile();
				BufferedWriter writer=new BufferedWriter(new FileWriter(newfile));
				
				String Content=textPane.getText().toString();
				
				writer.write(Content);
				writer.close();
				fileSavedDialog(FileName,FilePath);
			}
		}catch(Exception E)
		{
			E.printStackTrace();
		}
		
	}
	
	public void OpenFile()
	{
		try {
			JFileChooser chooser=new JFileChooser();
			int op=chooser.showOpenDialog(null);
			
			if(op==JFileChooser.APPROVE_OPTION)
			{
				String FileName=chooser.getSelectedFile().getName();		
				String FilePath=chooser.getSelectedFile().getPath();
				
				File newfile=chooser.getSelectedFile();
				BufferedReader reader=new BufferedReader(new FileReader(newfile));
				
				int v=0;
				textPane.setText("");
				while((v=reader.read())!= -1)
						{
						char c=(char)v;
						textPane.setText(textPane.getText().toString()+c);
						}
			}
		}catch(Exception E)
		{
			E.printStackTrace();
		}
	}
	
	public void fileSavedDialog(String name,String path)
	{
	JOptionPane.showMessageDialog(null,"file "+name+"saved to "+path ,"Saved", JOptionPane.PLAIN_MESSAGE);
	}
}
