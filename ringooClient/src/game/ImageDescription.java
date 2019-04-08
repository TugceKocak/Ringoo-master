
package game;

import javax.swing.ImageIcon;

/**
 *
 * @author Tugce Kocak
 */
// görüntü özelliiklerinii tanımlamak için oluşturulmuş sınıftır.
public class ImageDescription {
    String renk;
    String sekil;
    int sayi;
    boolean bomba;
    ImageIcon imgc;
    boolean acildiMi;
    public ImageDescription(ImageIcon img,String renk,String sekil,int sayi,boolean bomba){
        this.imgc=img;
        this.sekil=sekil;
        this.renk=renk;
        this.sayi=sayi;
        this.bomba=bomba;
        this.acildiMi=false;
    }
}
