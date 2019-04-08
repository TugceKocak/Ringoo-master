
package game;

/**
 *
 * @author Tugce Kocak
 */

import java.awt.Image;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import ringooclient.Client;


public class JRingoo extends javax.swing.JFrame {

    public static JRingoo ThisGame;
    //karşı tarafın seçimi seçim -1 deyse seçilmemiş
    public int RivalSelection = -1;
    //benim seçimim seçim -1 deyse seçilmemiş
    public int myselection = -1;
    //her seçimde ekrandaki resim değişimi için timer yerine thread
    public Thread tmr_slider;
    Random rand; // kartlar dizindeki kartlwarı rastgele açmak için kullanılan değişken.
    ImageIcon icons[]; // oyun kartları haricindeki bekleme,smiley sonuç resimlerini içerir
    ImageIcon kartlarRaw[]; // oyun kartlarını içerir.
    ImageDescription kartlar[]; //oyun kartlarının bilgisine ulaşmak için oluşturulmuştur.
    // kartlaarRaw ile aynı index sırasında görüntüler içerir.
    public ImageIcon ziller[]; // zil görüntülerini içeren dizi.
    public int pointcounter=0; // ortada açılan kart sayısını poinntcounter ile tutarız.
    public int mypoint=0; //sonuc puanı yazılır.
    public int rivalpoint=0; //rakip puanı yazılır.
    
    
    public JRingoo() {
        try {
            initComponents();
            ThisGame = this;
            rand = new Random();
            
            kartlarRaw=new ImageIcon[11];
            kartlarRaw[0]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart1.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[1]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart2.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[2]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart3.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[3]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart4.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[4]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart5.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[5]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart6.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[6]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart7.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[7]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart8.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[8]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart9.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[9]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart10.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            kartlarRaw[10]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/kart11.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            
            ziller=new ImageIcon[3];
            ziller[0]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/zilorta.JPG"))).getImage().getScaledInstance(jzil.getWidth(),jzil.getHeight(), Image.SCALE_DEFAULT));
            ziller[1]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/zilsag.JPG"))).getImage().getScaledInstance(jzil.getWidth(), jzil.getHeight(), Image.SCALE_DEFAULT));
            ziller[2]= new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/zilsol.JPG"))).getImage().getScaledInstance(jzil.getWidth(), jzil.getHeight(), Image.SCALE_DEFAULT));
            
            kartlar=new ImageDescription[11];
            kartlar[0]=new ImageDescription(kartlarRaw[0],"kahve","bulut", 1, false);
            kartlar[1]=new ImageDescription(kartlarRaw[1],"kirmizi","ucgen", 5, false);
            kartlar[2]=new ImageDescription(kartlarRaw[2],"kahve","altigen", 5, false);
            kartlar[3]=new ImageDescription(kartlarRaw[3],"kirmizi","bayrak", 3, false);
            kartlar[4]=new ImageDescription(kartlarRaw[4],"mavi","dörtgen", 9, false);
            kartlar[5]=new ImageDescription(kartlarRaw[5],"sari","bulut", 7, false);
            kartlar[6]=new ImageDescription(kartlarRaw[6],"mor","yamuk", 6, false);
            kartlar[7]=new ImageDescription(kartlarRaw[7],"pembe","yamuk", 8, false);
            kartlar[8]=new ImageDescription(kartlarRaw[8],"mavi","kutu", 2, false);
            kartlar[9]=new ImageDescription(kartlarRaw[9],"yesil","ucgen", 6, false);
            kartlar[10]=new ImageDescription(kartlarRaw[10],"yesil","ucgen", 6, true);
            
            icons = new ImageIcon[4];
            icons[0] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/wait.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons[1] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/lose.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons[2] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/win.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons[3] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("ringooclient/images/tie.jpeg"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            
            
            lbl_gamer1.setIcon(icons[0]);
            lbl_gamer2.setIcon(icons[0]);
            jzil.setIcon(ziller[0]);
        } catch (IOException ex) {
            Logger.getLogger(JRingoo.class.getName()).log(Level.SEVERE, null, ex);
        }
        tmr_slider = new Thread(() -> {
            //soket bağlıysa dönsün
            while (Client.socket.isConnected()) {
                try {
                    Thread.sleep(100);
                    // eğer iki seçim yapılmışsa sonuç gösterilebilir. 
                    if (RivalSelection != -1 && myselection != -1) {
                        lbl_gamer2.setIcon(kartlarRaw[RivalSelection]);
                        jzil.setEnabled(true);
                    } 
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(JRingoo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_gamer3 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        btn_connect = new javax.swing.JButton();
        btn_open = new javax.swing.JButton();
        txt_receive = new java.awt.TextArea();
        btn_send_message = new javax.swing.JButton();
        txt_send = new java.awt.TextArea();
        pnl_gamer2 = new javax.swing.JPanel();
        lbl_gamer2 = new javax.swing.JLabel();
        pnl_gamer1 = new javax.swing.JPanel();
        lbl_gamer1 = new javax.swing.JLabel();
        txt_rival_name = new javax.swing.JTextField();
        zil_panel = new javax.swing.JPanel();
        jzil = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_next = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        lbl_gamer3.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_name.setText("Name");
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 73, -1));

        btn_connect.setText("Connect");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });
        getContentPane().add(btn_connect, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 160, -1));

        btn_open.setText("Open");
        btn_open.setEnabled(false);
        btn_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_openActionPerformed(evt);
            }
        });
        getContentPane().add(btn_open, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 70, 90));
        getContentPane().add(txt_receive, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, 250, 128));

        btn_send_message.setText("Send");
        btn_send_message.setEnabled(false);
        btn_send_message.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_send_messageActionPerformed(evt);
            }
        });
        getContentPane().add(btn_send_message, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, 70, -1));
        getContentPane().add(txt_send, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 250, 128));

        pnl_gamer2.setBackground(new java.awt.Color(255, 153, 153));
        pnl_gamer2.setForeground(new java.awt.Color(51, 255, 0));
        pnl_gamer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_gamer2.setText("jLabel2");
        pnl_gamer2.add(lbl_gamer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 260));

        getContentPane().add(pnl_gamer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 251, 259));

        pnl_gamer1.setBackground(new java.awt.Color(255, 153, 153));
        pnl_gamer1.setForeground(new java.awt.Color(51, 255, 0));
        pnl_gamer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_gamer1.setText("jLabel1");
        pnl_gamer1.add(lbl_gamer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 260));

        getContentPane().add(pnl_gamer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 259));

        txt_rival_name.setEditable(false);
        txt_rival_name.setText("Rival");
        txt_rival_name.setEnabled(false);
        getContentPane().add(txt_rival_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 110, -1));

        zil_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jzil.setBackground(new java.awt.Color(0, 51, 153));
        jzil.setText("jLabel1");
        jzil.setEnabled(false);
        jzil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jzilMouseClicked(evt);
            }
        });
        zil_panel.add(jzil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 140));
        jzil.getAccessibleContext().setAccessibleName("izil");

        getContentPane().add(zil_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText(" 0");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 500, 30, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("0");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 30, -1));

        btn_next.setText("Next");
        btn_next.setEnabled(false);
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        getContentPane().add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 60, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Puan:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Puan:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed

        //bağlanılacak server ve portu veriyoruz
        Client.Start("127.0.0.1", 2000);
        //başlangıç durumları
        lbl_gamer1.setIcon(icons[0]);
        btn_connect.setEnabled(false);
        txt_name.setEnabled(false);
        btn_open.setEnabled(false);
        btn_send_message.setEnabled(false);
        btn_next.setEnabled(false);

    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openActionPerformed
        //seçim yapıyoruz
        
        Message msg = new Message(Message.Message_Type.Selected);
        pointcounter=pointcounter+2; // iki kart açıldıgı için 2 artırılır.
        int k;
        while(true){
            k=0;
            int i=rand.nextInt(11); // [0-10] arasındaki kart numaralarından random sayı seçilir.
             // eger kart açılmamışsa ve kart numarası rakibin secimiyle aynı değilse kart açılsın.
            if(kartlar[i].acildiMi==false && i!=RivalSelection){
                lbl_gamer1.setIcon(kartlarRaw[i]); //kart görüntüsü arayüze yerleştirilir.
                kartlar[i].acildiMi=true; // kartın açılıdıgı belli edilir.
                myselection=i;
                msg.content = myselection; // seçim bilgisi rakibe gönderilir.
                Client.Send(msg);
                break;
            }
            //  eger açılmamış kart varsa döngünün devam etmesi sağlanır.
            for (int j = 0; j < 11; j++) {
                if(kartlar[j].acildiMi==false){
                    k=-1;
                    break;
                }
            }
            // eger bütün kartlar açıldıysa sonuc calisir.
            if(k==0){
                JOptionPane.showMessageDialog(null, "Kartlar Bitti", "Ringoo- Uyarı!", -1);
               
                //smiley sonuç resimleri
                        if (mypoint > rivalpoint) {        //kazanan bensem 
                            lbl_gamer1.setIcon(icons[2]);
                            lbl_gamer2.setIcon(icons[1]);
                        } else if (mypoint < rivalpoint) { // kazanan rakipse
                            lbl_gamer1.setIcon(icons[1]);
                            lbl_gamer2.setIcon(icons[2]);
                        }
                         else {                             // esitlik durumu
                            lbl_gamer1.setIcon(icons[3]);
                            lbl_gamer2.setIcon(icons[3]);
                        }
                        // oyun bitmiştir son koşullar yapılrı ve thread sonlandırılır.
                        btn_open.setEnabled(false);
                        btn_next.setEnabled(false);
                        btn_send_message.setEnabled(false);
                        jzil.setEnabled(false);
                        tmr_slider.stop();
                break;
            }
        }
    }//GEN-LAST:event_btn_openActionPerformed

    private void btn_send_messageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send_messageActionPerformed

        //metin mesajı gönder
        Message msg = new Message(Message.Message_Type.Text);
        msg.content = txt_send.getText();
        Client.Send(msg);
        txt_send.setText("");
    }//GEN-LAST:event_btn_send_messageActionPerformed

    private void jzilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jzilMouseClicked
            // zil görüntüsüne basıldıgında çalısır.
            Message msg = new Message(Message.Message_Type.zilNumber);
            
            jzil.setIcon(ziller[1]);
            msg.content = 1;
            Client.Send(msg);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(JRingoo.class.getName()).log(Level.SEVERE, null, ex);
        }
            Message msg3 = new Message(Message.Message_Type.zilNumber);
              
            jzil.setIcon(ziller[2]);
            msg3.content = 2;
            Client.Send(msg3);
            
            if (RivalSelection==-1 || myselection==-1){
            }
            else if (kartlar[RivalSelection].bomba==true || kartlar[myselection].bomba==true) {
                rivalpoint=rivalpoint+pointcounter;
                pointcounter=0;
                jLabel1.setText(String.valueOf(rivalpoint));
                Message msg1 = new Message(Message.Message_Type.Rpoint);
                msg1.content = rivalpoint;
                Client.Send(msg1);
                
                Message msg2 = new Message(Message.Message_Type.point);
                msg2.content = 0;
                Client.Send(msg2);
            }
            else if(kartlar[RivalSelection].renk == kartlar[myselection].renk){
                mypoint=mypoint+pointcounter;
                pointcounter=0;
                jLabel2.setText(String.valueOf(mypoint));
                Message msg1 = new Message(Message.Message_Type.mypoint);
                msg1.content = mypoint;
                Client.Send(msg1);
                
                Message msg2 = new Message(Message.Message_Type.point);
                msg2.content = 0;
                Client.Send(msg2);
            }
            else if(kartlar[RivalSelection].sayi == kartlar[myselection].sayi){
                mypoint=mypoint+pointcounter;
                pointcounter=0;
                jLabel2.setText(String.valueOf(mypoint));
                Message msg1 = new Message(Message.Message_Type.mypoint);
                msg1.content = mypoint;
                Client.Send(msg1);
                
                Message msg2 = new Message(Message.Message_Type.point);
                msg2.content = 0;
                Client.Send(msg2);
            }
            else if(kartlar[RivalSelection].sekil == kartlar[myselection].sekil){
                mypoint=mypoint+pointcounter;
                pointcounter=0;
                jLabel2.setText(String.valueOf(mypoint));
                Message msg1 = new Message(Message.Message_Type.mypoint);
                msg1.content = mypoint;
                Client.Send(msg1);
                
                Message msg2 = new Message(Message.Message_Type.point);
                msg2.content = 0;
                Client.Send(msg2);
            }
        
               
    }//GEN-LAST:event_jzilMouseClicked

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        // TODO add your handling code here:
       
        lbl_gamer1.setIcon(icons[0]);
        lbl_gamer2.setIcon(icons[0]);
        RivalSelection=-1;
        myselection=-1;
       jzil.setIcon(ziller[0]);
    }//GEN-LAST:event_btn_nextActionPerformed
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JRingoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JRingoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JRingoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JRingoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JRingoo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_connect;
    public javax.swing.JButton btn_next;
    public javax.swing.JButton btn_open;
    public javax.swing.JButton btn_send_message;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jzil;
    public javax.swing.JLabel lbl_gamer1;
    public javax.swing.JLabel lbl_gamer2;
    public javax.swing.JLabel lbl_gamer3;
    private javax.swing.JPanel pnl_gamer1;
    private javax.swing.JPanel pnl_gamer2;
    public javax.swing.JTextField txt_name;
    public java.awt.TextArea txt_receive;
    public javax.swing.JTextField txt_rival_name;
    public java.awt.TextArea txt_send;
    private javax.swing.JPanel zil_panel;
    // End of variables declaration//GEN-END:variables
}
