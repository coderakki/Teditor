package texteditor;
import javax.swing.JFrame;
import javax.swing.text.*;

import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.Formatter;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import java.util.*;
import java.io.File;

import javax.swing.*;

import java.awt.Dimension;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
public class guieditor extends JFrame{
	
	private JMenuBar menu = new JMenuBar();
	private JMenu filemenu= new JMenu("File");
	private JMenu editmenu = new JMenu("Edit");
	private JMenu formatmenu = new JMenu("Format");
	private JMenu viewmenu = new JMenu("View");
	private JMenu helpmenu = new JMenu("Help");
	private JMenuItem New = new JMenuItem("New");
	private JMenuItem open = new JMenuItem("Open");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem print = new JMenuItem("Print");
	private JMenuItem saveas = new JMenuItem("Save As");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem cut = new JMenuItem("Cut");
	private JMenuItem copy = new JMenuItem("Copy");
	private JMenuItem paste = new JMenuItem("Paste");
	private JMenuItem find = new JMenuItem("Find");
	private JMenuItem replace = new JMenuItem("Replace");
	private JMenuItem  selectall= new JMenuItem("Select All");
	private JMenuItem font = new JMenuItem("Font");
	private JMenuItem word = new JMenuItem("WORD WRAP");
	private JLabel sized = new JLabel("FONT SIZE");
	private JCheckBox bold = new JCheckBox("B");
	private JCheckBox italic = new JCheckBox("I");
	private JPanel yo = new JPanel();
	private JMenuItem statusbar = new JMenuItem("Status Bar");
	private JMenuItem help = new JMenuItem("View Help");
	private JMenuItem about= new JMenuItem("About AkkiPad");
	private String data;
	private JTextArea edit = new JTextArea(790,800);
	private JTextPane name = new JTextPane();
	private static String filenames[] = new String[100];
	private String [] formats ={".txt",".pdf",".c",".cpp",".java",".py",".html"};
	private int i,k,l,z;
	private boolean exitpressed = false;
	private PrintWriter filename;
	private String s;
	private boolean flag = false;
	private Highlighter h = edit.getHighlighter();
	 GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	   private Font[] fonts = e.getAllFonts(); // Get the fonts
	   private String fontname[]= new String[fonts.length];
	   private String typelist[] ={"None","Bold","Italic","Bold+Italic"};
	   private String size[] = new String[25];
	   private JComboBox fontlist;
	   private JComboBox sizelist;
	   private JComboBox typeslist = new JComboBox(typelist);
	public guieditor(){
		super("Welcome to Notepad");
		setJMenuBar(menu);
		menu.add(filemenu);
		menu.add(editmenu);
		menu.add(formatmenu);
		menu.add(viewmenu);
		menu.add(helpmenu);
		filemenu.add(New);
		filemenu.add(open);
		filemenu.add(save);
		filemenu.add(saveas);
		filemenu.add(print);
		filemenu.add(exit);
		editmenu.add(cut);
		editmenu.add(copy);
		editmenu.add(paste);
		editmenu.add(find);
		editmenu.add(replace);
		editmenu.add(selectall);
		formatmenu.add(font);
		formatmenu.add(word);
		viewmenu.add(statusbar);
		helpmenu.add(help);
		helpmenu.add(about);
		edit.setEditable(false);
		add(edit);
		yo.add(bold);
		yo.add(italic);
		add(yo,BorderLayout.NORTH);
		handler handle =new handler();
		New.addActionListener(handle);
		bold.addActionListener(handle);
		italic.addActionListener(handle);
		open.addActionListener(handle);
		save.addActionListener(handle);
		saveas.addActionListener(handle);
		print.addActionListener(handle);
		exit.addActionListener(handle);
		cut.addActionListener(handle);
		copy.addActionListener(handle);
	    paste.addActionListener(handle);
		find.addActionListener(handle);
		replace.addActionListener(handle);
		selectall.addActionListener(handle);
		font.addActionListener(handle);
		word.addActionListener(handle);
		statusbar.addActionListener(handle);
		help.addActionListener(handle);
		about.addActionListener(handle);
		try{Scanner x= new Scanner(new File("names.txt"));
		while(x.hasNext()){filenames[i++] = x.next();}
		x.close();
		}
		catch(IOException e){}
		   for(int j=0;j<50;j+=2){
			   size[j/2] = ""+j;
		   }
		   sizelist = new JComboBox(size);
		   sizelist.setSelectedIndex(7);
		   sizelist.addItemListener(
				   new ItemListener(){
					   public void itemStateChanged(ItemEvent e){
						   if(e.getStateChange()==ItemEvent.SELECTED){
							//System.out.println(sizelist.getSelectedIndex());
							   edit.setFont(new Font(edit.getFont().getFontName(),edit.getFont().getStyle(),2*sizelist.getSelectedIndex()));
							   
						   }
						   }
				   }
				   );
		   handler1 handled =new handler1();
		   bold.addItemListener(handled);
		   italic.addItemListener(handled);
		   yo.add(sized);
		 yo.add(sizelist);
	}
	private class handler1 implements ItemListener {
		public void itemStateChanged(ItemEvent e){
			if(bold.isSelected()){
				 edit.setFont(new Font(edit.getFont().getFontName(),Font.BOLD,edit.getFont().getSize()));
			}
			if(italic.isSelected()){
				 edit.setFont(new Font(edit.getFont().getFontName(),Font.ITALIC,edit.getFont().getSize()));
			}
			if(italic.isSelected() && bold.isSelected()){
				 edit.setFont(new Font(edit.getFont().getFontName(),Font.ITALIC+Font.BOLD,edit.getFont().getSize()));
			}
			
		}
	}
	 private class handler implements ActionListener
	 {
		 
		 public void actionPerformed(ActionEvent event){
			 if(event.getSource()==New){
				 edit.setEditable(true);
				 setTitle("Untitled-Akkipad");
				 edit.setText("");
			 }
		
				
				 
				 
			 //name.setPreferredSize(new Dimension(50, name.getPreferredSize().height))
               if(event.getSource()==open){   
            	   String s= (String)JOptionPane.showInputDialog(
            			   null,"Enter the file to be Opened", "Open File", JOptionPane.PLAIN_MESSAGE);
            	   
            	   edit.setEditable(true);
            	   setTitle(s+"-Akkipad");
            	   try{Scanner x= new Scanner(new File(s+".txt"));
      			 while(x.hasNext()){
      				 data = x.next();
      				edit.setText(edit.getText()+" "+data);
      			 }
      			x.close();
          		
          		
          		//setTitle(box.getSelectedIndex()+"-Akkipad");
      			}
      			catch(IOException e){
      				JOptionPane.showMessageDialog(null, "Invalid File name","ERROR!!!",JOptionPane.ERROR_MESSAGE);
      				this.actionPerformed( event);}
      			}
            	   /*box.addItemListener(new ItemListener(){
                	public void itemStateChanged(ItemEvent event){
                		if(event.getStateChange()==ItemEvent.SELECTED){
                			int j =box.getSelectedIndex();
                			try{Scanner x= new Scanner(new File(filenames[box.getSelectedIndex()]+".txt"));
                			 data = x.next();
                			x.close();
                    		edit.setText(data);
                    		setTitle(box.getSelectedIndex()+"-Akkipad");
                			}
                			catch(IOException e){edit.setText("ERROR!!!!");}
                			}
                  }	
				// File x = new File(name.getText()+".txt");
				 //setTitle(name.getText()+"-Akkipad");
		 });*/
			 
			 if(event.getSource()==save){
				 data = edit.getText();
				 name.setSize(200, 200);
				 JOptionPane.showMessageDialog(null,name,"Save File",JOptionPane.PLAIN_MESSAGE);
				 /* Formatter f; 
					try{ 
					 f= new Formatter(name.getText()+".txt");}
					catch(Exception e){
						System.out.println("ERROR");
					}*/
				    filenames[i++]=name.getText();
				    try{PrintWriter f = new PrintWriter("names.txt","utf-8");
				    for(int x=0;x<i;++x)
				    	f.write(filenames[x]+"\n");
					f.close();}
					catch(IOException  e){}
					try{PrintWriter f = new PrintWriter(name.getText()+".txt","utf-8");
					f.write(data);
					f.close();}
					catch(IOException  e){}
					setTitle(filenames[i-1]+"-Akkipad");
					for(int x=0;x<i-1;++x)
						if(filenames[x]==filenames[i-1])
							filenames[i-1]=null;
			 }
			 if(event.getSource()==saveas){
				 data = edit.getText();
				 String s = (String)JOptionPane.showInputDialog(null,"Enter the name with extension","Save As",JOptionPane.PLAIN_MESSAGE );
				 int index=s.indexOf(".");
				 String frmt = s.substring(index,s.length());
				 int flag=0;
				 for(int x=0;x<7;++x)
					 if(frmt.equalsIgnoreCase(formats[x])){
						 flag=1;
						 break;
					 }
				 if(frmt.equals(".txt")){
					 filenames[i++]=s.substring(0,index);
					 try{PrintWriter f = new PrintWriter("names.txt","utf-8");
					    for(int x=0;x<i;++x)
					    	f.println(filenames[x]);
						f.close();}
					 catch(IOException e){}	
				 }
				 if(flag==1){
					 try{File f = new File(s);
					     FileOutputStream f1 = new FileOutputStream(f);
					     byte[] y = data.getBytes();
					     f1.write(y);
					     f1.close();}
						catch(IOException  e){}
				 }
				 else{
				
					 JOptionPane.showMessageDialog(null, "ERROR:INVALID FORMAT","ERROR!!",JOptionPane.ERROR_MESSAGE);
					 this.actionPerformed(event);
				 }
					 
			 }
			 
			 if(event.getSource()==cut){
				  s= edit.getSelectedText();
				  String s1 = edit.getText();
				 s1=s1.replace(s, "");
				 edit.setText(s1);
				 flag = true;
			 }
			 if(event.getSource()==copy){
				 s= edit.getSelectedText();
				 flag = true;
			 }
			 if(event.getSource()==paste){
				 if(flag){
					 edit.insert(s,edit.getCaretPosition());
				 }
				 flag=false;
			 }
			 if(event.getSource()==find){
				 String s1=(String)JOptionPane.showInputDialog(null,"Enter the word to be found","FIND!!!",JOptionPane.PLAIN_MESSAGE);
				 String s2=edit.getText();
				 int i=0;
				 while(i!=-1){
				 i = s2.indexOf(s1,i+1);
				 System.out.println(i);
				 try{h.addHighlight(i, i+s1.length(),DefaultHighlighter.DefaultPainter);}
				 catch(Exception e){}}
				 edit.addMouseListener(
						 new MouseListener(){
							 public void mouseClicked(MouseEvent e){
								 h.removeAllHighlights();
							 }

							public void mouseEntered(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseExited(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mousePressed(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseReleased(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
						 }
						 );
			 }
			 if(event.getSource()==replace){
				 JTextField f1 = new JTextField();
				 JTextField f2 = new JTextField();
				 Object[] fields = {"Enter the word to be replaced",f1,
						            "Enter the word with which it is to be replaced",f2
				 };
				// JOptionPane.showMessageDialog(null,fields,"Replace",JOptionPane.PLAIN_MESSAGE);
				 String s1 = f1.getText();
				 String s2 = f2.getText();
				 String s3= edit.getText();
				 s3=s3.replaceAll(s1, s2);
				 System.out.println(s1);
				 System.out.println(s2);
				 System.out.println(s3);
				 edit.setText(s3);
				 int i=0;
				 while(i!=-1){
				 i = s3.indexOf(s2,i+1);
				 //System.out.println(i);
				 try{h.addHighlight(i, i+s2.length(),DefaultHighlighter.DefaultPainter);}
				 catch(Exception e){}}
				 edit.addMouseListener(
						 new MouseListener(){
							 public void mouseClicked(MouseEvent e){
								 h.removeAllHighlights();
							 }

							public void mouseEntered(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseExited(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mousePressed(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseReleased(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
						 }
						 );

			 }
			 if(event.getSource()==selectall){
				 edit.setSelectionStart(0);
				 String s1 = edit.getText();
				 edit.setSelectionEnd(s1.length());
			 }
			 if(event.getSource()==font){
				 //edit.setFont(getFont());
				 
				   for(int i=0;i<fonts.length;++i){
					   fontname[i] = fonts[i].getFontName(); 
				   }
			
				  fontlist= new JComboBox(fontname);
				  sizelist = new JComboBox(size);
				  String f = (String)JOptionPane.showInputDialog(null, "Please Select the font", "Font Name", JOptionPane.PLAIN_MESSAGE, null, fontname, edit.getFont().getName());
				/*   Object[] input ={"Please Select the font",fontlist,"Select the font size",sizelist,"Select Type",typeslist}; 
				  JOptionPane.showMessageDialog(null,input,"Font",JOptionPane.PLAIN_MESSAGE);
				   fontlist.addItemListener(
						   new ItemListener(){
							   public void itemStateChanged(ItemEvent e){
								   if(e.getStateChange()==ItemEvent.SELECTED)
									   z= fontlist.getSelectedIndex();
								   System.out.println(z);
								   System.out.println("yooooooo");
							   }
						   }
						   );
				sizelist.addItemListener(
						   new ItemListener(){
							   public void itemStateChanged(ItemEvent e){
								   if(e.getStateChange()==ItemEvent.SELECTED && e.getSource()==sizelist)
									   k= sizelist.getSelectedIndex();
							   }
						   }
						   );
				   typeslist.addItemListener(
						   new ItemListener(){
							   public void itemStateChanged(ItemEvent e){
								   if(e.getStateChange()==ItemEvent.SELECTED && e.getSource()==typeslist)
									  l = typeslist.getSelectedIndex();
							   }
						   }
						   );
				   JOptionPane.showMessageDialog(null,input,"Font",JOptionPane.PLAIN_MESSAGE);
				   if(l==0)
				   edit.setFont(new Font(fontname[z],Font.PLAIN,20));
				   if(l==1)
					   edit.setFont(new Font(fontname[z],Font.BOLD,20));
				   if(l==2)
					   edit.setFont(new Font(fontname[z],Font.ITALIC,20));
				   if(l==3)
					   edit.setFont(new Font(fontname[z],Font.BOLD+Font.ITALIC,20));
			  System.out.println(z);
			  System.out.println(k);
			 System.out.println(l);*/
				  /*String g =(String)JOptionPane.showInputDialog(null, "Please Select the font size", "Font Size", JOptionPane.PLAIN_MESSAGE, null, size,size[0] );
				  char [] b = g.toCharArray();
				  int y = b[1]-48;
				  y+=((b[0]-48)*10);*/
				  edit.setFont(new Font(f,Font.PLAIN,edit.getFont().getSize()));
			 }
			 if(event.getSource()==word){
				 edit.setWrapStyleWord(true);
			 }
			 }
			 
		 }
	 }


