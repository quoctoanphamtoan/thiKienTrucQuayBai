package toanKienTruc.UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.JMSException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import toanKienTruc.app.ActiveMQConfig;
import toanKienTruc.entity.BenhNhan;

public class Sender extends JFrame {

 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtBenh;
	private JTextField txtKhac;

	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sender frame = new Sender();
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
	public Sender() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(34, 27, 60, 28);
		contentPane.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(112, 31, 132, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblTen = new JLabel("Ten");
		lblTen.setBounds(34, 81, 60, 28);
		contentPane.add(lblTen);
		
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(112, 85, 132, 20);
		contentPane.add(txtTen);
		
		JLabel lblTuoi = new JLabel("Dia chi");
		lblTuoi.setBounds(34, 135, 60, 28);
		contentPane.add(lblTuoi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(112, 139, 132, 20);
		contentPane.add(txtDiaChi);
		
		JLabel lblBenh = new JLabel("Benh");
		lblBenh.setBounds(34, 186, 60, 28);
		contentPane.add(lblBenh);
		
		txtBenh = new JTextField();
		txtBenh.setColumns(10);
		txtBenh.setBounds(112, 190, 132, 20);
		contentPane.add(txtBenh);
		
		JLabel lblKhac = new JLabel("Khac");
		lblKhac.setBounds(34, 225, 60, 28);
		contentPane.add(lblKhac);
		
		txtKhac = new JTextField();
		txtKhac.setColumns(10);
		txtKhac.setBounds(112, 229, 132, 20);
		contentPane.add(txtKhac);
		
		JButton btnNewButton = new JButton("goi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText().trim());
				
				if(id<=0) {
					JOptionPane.showMessageDialog(null,"Nhap id lon hon khong");
					return;
				}
				
				try {
					new ActiveMQConfig().Sender(new BenhNhan(Integer.parseInt(txtId.getText().trim()),
							txtTen.getText().trim(),
							txtBenh.getText().trim(),
							txtKhac.getText().trim(),
							txtDiaChi.getText().trim()
							));
				} catch (NumberFormatException | JMSException e1) { 
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(265, 138, 125, 28);
		contentPane.add(btnNewButton);
	}
}
