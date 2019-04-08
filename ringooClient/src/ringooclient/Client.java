
package ringooclient;

import game.ImageDescription;
import game.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ringooclient.Client.sInput;
import game.JRingoo;

/**
 *
 * @author Tugce Kocak
 */
// serverdan gelecek mesajları dinleyen thread
class Listen extends Thread {

    public void run() {
        //soket bağlı olduğu sürece dön
        while (Client.socket.isConnected()) {
            try {
                //mesaj gelmesini bloking olarak dinyelen komut
                Message received = (Message) (sInput.readObject());
                //mesaj gelirse bu satıra geçer
                //mesaj tipine göre yapılacak işlemi ayır.
                switch (received.type) {
                    case Name:
                        break;
                    case RivalConnected:
                        String name = received.content.toString();
                        JRingoo.ThisGame.txt_rival_name.setText(name);
                        JRingoo.ThisGame.btn_open.setEnabled(true);
                        JRingoo.ThisGame.btn_next.setEnabled(true);
                        JRingoo.ThisGame.btn_send_message.setEnabled(true);
                        JRingoo.ThisGame.tmr_slider.start();
                        break;
                    case Disconnect:
                        break;
                    case Text:
                        // gelen mesaj metnini arayüze yazdırır.
                        JRingoo.ThisGame.txt_receive.setText(received.content.toString());
                        break;
                    case Selected:
                        // gelen secilmis kart bilgisini rivalselection'a aktarır.
                        JRingoo.ThisGame.RivalSelection = (int) received.content;
                        break;
                    case zilNumber:
                        // zile basılınca arayüzdeki zil görüntüsünü değiştirir.
                        JRingoo.ThisGame.jzil.setIcon(JRingoo.ThisGame.ziller[(int) received.content]);
                        break;
                    case mypoint:
                        // kazanılan puan karşı tarafın arayüzüne yazılır.
                        // mesaj rivalpointe yazılır.
                        JRingoo.ThisGame.jLabel1.setText(String.valueOf(received.content));
                        JRingoo.ThisGame.rivalpoint=(int) received.content;
                        break;
                    case point:
                        // ortada biriken kart sayısı point olarak belirtildi,
                        // bir oyuncu ortadaki kartları alınca point bilgisi 0 yapılır.
                        // Bu satır sıfır bilgisini okur.
                        JRingoo.ThisGame.pointcounter=(int) received.content;
                        break;
                    case Rpoint:
                        // yanlış hamle yapıp rakibe puan kazandırırsak bunu karşı tarafın arayüzüne yazarız.
                        JRingoo.ThisGame.jLabel2.setText(String.valueOf(received.content));
                        break;
                    case Bitis:
                        break;
                    
                }

            } catch (IOException ex) {

                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } 
        }
    }
}

public class Client {

    //her clientın bir soketi olmalı
    public static Socket socket;

    //verileri almak için gerekli nesne
    public static ObjectInputStream sInput;
    //verileri göndermek için gerekli nesne
    public static ObjectOutputStream sOutput;
    //serverı dinleme thredi 
    public static Listen listenMe;

    public static void Start(String ip, int port) {
        try {
            // Client Soket nesnesi
            Client.socket = new Socket(ip, port);
            Client.Display("Servera bağlandı");
            // input stream
            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            // output stream
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen();
            Client.listenMe.start();
            
            //ilk mesaj olarak isim gönderiyorum
            Message msg = new Message(Message.Message_Type.Name);
            msg.content = JRingoo.ThisGame.txt_name.getText();
            Client.Send(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //client durdurma fonksiyonu
    public static void Stop() {
        try {
            if (Client.socket != null) {
                Client.listenMe.stop();
                Client.socket.close();
                Client.sOutput.flush();
                Client.sOutput.close();

                Client.sInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Display(String msg) {
        System.out.println(msg);
    }

    //mesaj gönderme fonksiyonu
    public static void Send(Message msg) {
        try {
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}


