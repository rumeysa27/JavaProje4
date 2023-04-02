package Modeller.Calisanlar;

import Modeller.Departmanlar.BilisimTeklonojileriDepartmani;
import Modeller.Departmanlar.Departman;
import Veritabani.Calisanlar;
import Veritabani.Departmanlar;

import java.util.ArrayList;

public class Calisan {

    private String calisanId;
    private String adSoyad;
    private int maas;
    private Departman Departman;
    private String isimKodu = "";

    public Calisan(String adSoyad, int maas, String departmanKodu) {
        this.adSoyad = adSoyad;
        this.maas = maas;
        setDepartman(departmanKodu);
        this.setCalisanId();
        Calisanlar.addACalisan(this);
    }

    // Kullanicinin departman koduna göre, gerekli departman set edilmelidir.
    private void setDepartman(String departman) {
       for (Departman Departman: Departmanlar.getDepartmanList())
           if (Departman.getDepartmanKodu().equals(departman)){
               this.Departman=Departman;
               break;
           }

        /*
            İpucu: Departman listesinin (Veritabani.Departmanlar.DepartmanList) içerisindeki departmanların kodları var,
        bu kodlari donguye tutmak ise yarayabilir.
       */
    }

    // Calisanin ID sinin kendisine özel olduğundan bahsetmistik, ID nin nasil kaydedileceği CalisanKaydetmeProjesiTanıtım.txt
    // içerisinde yer aliyor.
    private void setCalisanId() {
        String departmanKodu=Departman.getDepartmanKodu();
        int calisanSayisi=Calisanlar.getCalisanList().size()+1;
        String isimKodu=getCalisanIsimKodu();
        this.calisanId=departmanKodu+calisanSayisi+isimKodu;

        /*
            İpucu: Detayli anlatim CalisanKaydetmeProjesiTanıtım.txt içerisinde.
         */
    }

    // Calisanin ID sinin sonuna isim kodu eklenmesi için, ismi parçalayan bir method.
    private String getCalisanIsimKodu() {
       this.isimKodu=String.valueOf(adSoyad.charAt(0)).toUpperCase();
    this.isimKodu=this.isimKodu.concat(String.valueOf(adSoyad.charAt(adSoyad.lastIndexOf(" ")+1))).toUpperCase();
//concat eklemek için kullandım last indexof ola ki iki isimli kullanıcı sisteme girer diye
       return isimKodu;
    }

    // Calisanin id sini almak icin basit getter method
    public String getCalisanId() {
        return this.calisanId;
    }

    // Calisanin departmanini almak icin basit getter method
    public Departman getDepartman() {
        return this.Departman;
    }

    // Departman adini verebilmek için bir method
    public String getDepartmanAdi() { //buraya switch case de kullanabiliriz daha pratik

        String departman=" ";//returna eklemek için yaptım
        if (this.Departman.getDepartmanKodu().equals("YD")){
            return "Yönetim Departmanı";
        }
         if (this.Departman.getDepartmanKodu().equals("IKD"))
             return "İnsan Kaynakları Departmanı";

         if (this.Departman.getDepartmanKodu().equals("BTD"))
             return "Bilişim Teknolojileri Departmanı";
        /*
                İpucu: Departman Kodu YD ise departman adi Yonetim Departmani olarak kaydedilmelidir.
         */
        return departman;
    }

    // Calisana zam yapilmasi için gerekli bir method
    public static void zamYap(String calisanId) {
        for (Calisan calisan: Calisanlar.getCalisanList())
            if (calisan.getCalisanId().equals(calisanId)){
                double zamOrani=calisan.getDepartman().getZamOrani();
                calisan.maas+=(calisan.maas*(zamOrani/100));
                break;
            }
        /*
         İpucu: Calisan ID si kullanilarak yapilmalidir, diğer attributelarin aynilarindan 1 er tane daha
            olabilirdi.
         */
    }

    @Override
    public String toString() {


       return "Calisan ID : \'" +calisanId + "\'" +
                ", Isim Soyisim : \'"+ adSoyad +"\'"+
                ", Maas : \'" + maas +"\'" +
                ", Departman : \'" + Departman.getClass().getSimpleName() +"\'";


        //"Calisan{" +
//                "calisanId='" + calisanId + '\'' +
//                ", adSoyad='" + adSoyad + '\'' +
//                ", maas=" + maas +
//                ", Departman=" +this.getDepartman() +
//                ", isimKodu='" +this.getCalisanIsimKodu() + '\'' +
//                '}';

 }
}
