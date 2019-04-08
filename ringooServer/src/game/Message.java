
package game;

/**
 * @author Tugce Kocak
 */
public class Message implements java.io.Serializable {
    
    //zilNumber: zil görüntüsünü iletmek için kullanılan tiptir.
    //point: her kart acıldıgında ortadaki kart sayısını belirtir.
    //Rpoint: rakip oyuncunun puanında bir değişiklik olduğunda buu iletmek için kullanılan tip değişkenidir.
    //mypoint: oyuncunun puanındaki değişiklikleri rakibin arayüzünde göstermek için kullanılan tiptir.
    //text: oyuncu diger oyuncuya mesaj göndermek istediğinde kullanılan tiptir.
    public static enum Message_Type {None, Name, Disconnect,RivalConnected, Text, Selected, Bitis,Start,zilNumber,mypoint,Rpoint,point,}
  
    public Message_Type type;
    //mesajın içeriği obje tipinde ki istenilen tip içerik yüklenebilsin
    public Object content;
    public Message(Message_Type t)
    {
        this.type=t;
    }
}
