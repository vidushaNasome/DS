/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemperatureMonitor_Code;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vnaso
 * This is email class.This doesnt have a main method
 * this is call inside desktop to simulate the email and msg behaviour
 */
public class Emal_msg extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form Emal_msg
     */
    private String loc;
    public Emal_msg() {
       initComponents();
    }
    public Emal_msg(String loc){
         initComponents();
        this.loc=loc;
        this.setVisible(true);
        String x="Attention!!!\n You have recieved 1 msg and 1 email \n regarding fire alarm."+"\n"+loc+" :has exceeded the fire limits\n\n";
        location.setText(x);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        location = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        location.setEditable(false);
        location.setBackground(new java.awt.Color(255, 153, 153));
        location.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("INBOX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                    .addComponent(location))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(location, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField location;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("dispose jframe");
            this.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(Emal_msg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}