package chatti;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class server extends JFrame implements ActionListener{
	JPanel p1;
	JTextField t1;
	static JTextArea a1;
	JButton b1;
	
	static ServerSocket skt;
	static Socket s; 
	static DataInputStream din;
	static DataOutputStream dout;
	
	server(){
		
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,400,70);
		add(p1);
		
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatti/icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(25,22, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(5,24,25,25);
		p1.add(l1);
		l1.addMouseListener(new MouseAdapter(){
	           public void mouseClicked(MouseEvent ae){
	               System.exit(0);
	           }
	       });
		
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatti/icons/1.png"));
		Image i5 = i4.getImage().getScaledInstance(58,58, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40,8,58,58);
		p1.add(l2);
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatti/icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(24,24, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l3 = new JLabel(i9);
		l3.setBounds(250,24,24,24);
		p1.add(l3);
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatti/icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(24,24, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l4 = new JLabel(i12);
		l4.setBounds(300,24,24,24);
		p1.add(l4);
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatti/icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(20,24, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l5 = new JLabel(i15);
		l5.setBounds(350,24,4,24);
		p1.add(l5);
		
		JLabel l6 = new JLabel("Captain");
		l6.setLayout(null);
		l6.setBounds(120,10,150,30);
		l6.setFont(new Font("SAN_SARIF",Font.BOLD,20));
		l6.setForeground(Color.WHITE);
		p1.add(l6);
		
		t1 = new JTextField();
	    t1.setBounds(5,598,290, 45);
	    t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	    add(t1);
	    
	    a1 = new JTextArea();
	    a1.setBounds(5,75,390,520);
	    a1.setFont(new Font("SAN_SERIF" , Font.PLAIN ,18));

	    a1.setEditable(false);
	    a1.setLineWrap(true);
	    a1.setWrapStyleWord(true);
	    add(a1);
	    
	    b1 = new JButton("Send");
	    b1.setBounds(300, 598, 95, 45);
	    b1.setBackground(new Color(7, 94, 84));
	    b1.setForeground(Color.WHITE);
	    b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
	    b1.addActionListener(this);
	    add(b1);
		
		JLabel l7 = new JLabel("Active Now");
		l7.setLayout(null);
		l7.setBounds(120,34,150,30);
		l7.setFont(new Font("SAN_SARIF",Font.PLAIN,15));
		l7.setForeground(Color.WHITE);
		p1.add(l7);
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setSize(400,650);
		setLocation(50,20);
		setUndecorated(true);
		setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		try {
		String out = t1.getText();
		a1.setText(a1.getText() + "\n\t\t" + out);
		t1.setText("");
		dout.writeUTF(out);
		
		}catch(Exception e) {
			
		}
		}
	
	public static void main(String[] args) {
		server u = new server();
		u.setVisible(true);

		try{
			skt = new ServerSocket(6001); //who you want to connect
			s = skt.accept(); //will accept data
			String msginput = "";
			din = new DataInputStream(s.getInputStream()); //tacck msg of client based on data from client
			dout = new DataOutputStream(s.getOutputStream()); //data we will send
			msginput = din.readUTF();
			a1.setText(a1.getText() +"\n"+ msginput);
			
			skt.close();
			s.close();
			
			
		}catch(Exception e) {
			
		}
	}

}
