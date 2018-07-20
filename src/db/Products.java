/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
class ButtonSensor extends JButton{
    public ButtonSensor(){
        this.setText("Hello");
        this.setSize(200, 200);
    }
}
public class Products extends JFrame{
    Products(MyDatabaseHandler md,int id) throws SQLException{
        this.setSize(600, 500);
		Toolkit tk = Toolkit.getDefaultToolkit();
				    System.out.println(id);
                               MyDatabaseHandler mh = new  MyDatabaseHandler();
                               mh.setConnection("online_food", "root", "");
                            int order_id =mh.maxOrder()+1;
                            mh.closeConnection();
				
		Dimension dim = tk.getScreenSize();
				
		// dim.width returns the width of the screen
		// this.getWidth returns the width of the frame you are making
				
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
                
               JPanel panel = new JPanel();// creating instance of JPanel
    this.setContentPane(panel);// add panel in ContentPane
panel.setLayout(null);// Set null
md.testQuery2("product");
JTable table = new JTable(md.getData(),md.getColumn());

table.setBounds(0, 0, 600, 250);
 JScrollPane spTable = new JScrollPane(table);
                panel.add(spTable);
panel.add(table);// add table in panel using add() method
JButton b1 = new JButton("Add to Cart");
b1.setBounds(100,300,100,50);
JButton b2 = new JButton("Proceed");
b2.setBounds(200,300,100,50);
JTextField quantity =new JTextField();
quantity.setBounds(100, 250, 100, 50);
panel.add(b1);
panel.add(b2);
panel.add(quantity);
b2.addActionListener((ActionEvent e) ->{
   Cart c = new Cart(md,order_id,id);
   this.dispose();
   c.setVisible(true);
});
b1.addActionListener((ActionEvent e) -> {
            // selectionButtonPressed();
            System.out.println(table.getValueAt(table.getSelectedRow(), 0));
            int p_id=(int) table.getValueAt(table.getSelectedRow(), 0);
            String query = "insert into orders values('"+0+"','"+order_id+"','"+p_id+"','"+id+"','"+quantity.getText()+"')";
            System.out.println(query);
            md.insertData(query);
            quantity.setText("");
            
        });

                this.setLocation(xPos, yPos);
		
                this.setVisible(true);
    }
    public static void main(String[] args){
      //  new Products();
    }
}
