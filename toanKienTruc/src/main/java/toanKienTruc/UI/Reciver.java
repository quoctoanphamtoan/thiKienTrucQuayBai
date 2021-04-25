package toanKienTruc.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import toanKienTruc.app.ActiveMQConfig;
import toanKienTruc.entity.BenhNhan;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Reciver extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel de;
	private JButton btnCall;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reciver frame = new Reciver();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reciver() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 396, 479);
		contentPane.add(scrollPane);

		table = new JTable();
		de = new DefaultTableModel(new Object[][] {}, new String[] { "id", "ten", "dia chi", "benh", "ghi chu" });
		table.setModel(de);
		scrollPane.setViewportView(table);
		
		btnCall = new JButton("Call");
		btnCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ActiveMQConfig().reciver().setMessageListener(new MessageListener() {
						@Override
						public void onMessage(Message message) {
							if(message instanceof TextMessage) {
								
								try {
									TextMessage tm = (TextMessage) message;
//									"id", "ten", "dia chi", "benh", "khac" 
									BenhNhan benhNhan = new Gson().fromJson(tm.getText(),BenhNhan.class);
									Vector<String> vt = new Vector<String>();
									vt.addElement(String.valueOf(benhNhan.getId()));
									vt.addElement(benhNhan.getTen());
									vt.addElement(benhNhan.getDiaChi());
									vt.addElement(benhNhan.getBenh());
									vt.addElement(benhNhan.getGhiChu());
									de.addRow(vt);
									
									
								} catch (JsonSyntaxException | JMSException e) {
									e.printStackTrace();
								}
								
							}
						}
					});
				} catch (JMSException e1) {
					 
					e1.printStackTrace();
				}
			}
		});
		btnCall.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCall.setBounds(88, 501, 239, 49);
		contentPane.add(btnCall);
	}
}
