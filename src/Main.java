import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Main {

    public static class bookings {

        private String email;
        private String f_name;
        private String l_name;
        private String telefon;
        private String szul;
        private String profilkep;
        private int hotel_id;
        private String hotel_varos;
        private String hotel_nev;
        private String hotel_leiras;
        private int hotel_csillagok;
        private String hotel_tulaj_email;
        private String hotel_kep;
        private LocalDate foglalas_kezd;
        private LocalDate foglalas_vege;
        private int felnottek;
        private int gyerekek;
        private int szeretok;

        public bookings (String sor) {
            String [] s=sor.split(",");
            email=s[0];
            f_name=s[1];
            l_name=s[2];
            telefon=s[3];
            szul=s[4];
            profilkep=s[5];
            hotel_id= Integer.parseInt(s[6]);
            hotel_varos=s[7];
            hotel_nev=s[8];
            hotel_leiras=s[9];
            hotel_csillagok=Integer.parseInt(s[10]);
            hotel_tulaj_email=s[11];
            hotel_kep=s[12];
            foglalas_kezd=LocalDate.parse(s[13]);
            foglalas_vege=LocalDate.parse(s[14]);
            felnottek=Integer.parseInt(s[15]);
            gyerekek=Integer.parseInt(s[16]);
            szeretok=Integer.parseInt(s[17]);
        }

        public String getEmail() {return email;}
        public String getF_name() {return f_name;}
        public String getL_name() {return l_name;}
        public String getTelefon() {return telefon;}
        public String getSzul() {return szul;}
        public String getProfilkep() {return profilkep;}
        public int getHotel_id() {return hotel_id;}
        public String getHotel_varos() {return hotel_varos;}
        public String getHotel_nev() {return hotel_nev;}
        public String getHotel_leiras() {return hotel_leiras;}
        public int getHotel_csillagok() {return hotel_csillagok;}
        public String getHotel_tulaj_email() {return hotel_tulaj_email;}
        public String getHotel_kep() {return hotel_kep;}
        public LocalDate getFoglalas_kezd() {return foglalas_kezd;}
        public LocalDate getFoglalas_vege() {return foglalas_vege;}
        public int getFelnottek() {return felnottek;}
        public int getGyerekek() {return gyerekek;}
        public int getSzeretok() {return szeretok;}

    }

    public static void main(String[] args) {

        System.out.println("Hello world!");

        ArrayList<bookings> adatok = new ArrayList<>();
        ArrayList<bookings> foglalasok = new ArrayList<>();

        String sor="";

        try (BufferedReader br = new BufferedReader(new FileReader("bookings.csv"))) {

            br.readLine();
            while ((sor=br.readLine())!=null) {
                adatok.add(new bookings(sor));
            }

        }
        catch (Exception e) {
            System.out.println("Hiba a fájlbeolvasáskor.");
            e.printStackTrace();
        }

        foglalasok.addAll(adatok);

        System.out.println("1. Beolvaasott foglalások száma: " + foglalasok.size());

        //System.out.println(foglalasok.get(1).foglalas_vege);

        // 2.
        int leghoszabbindex = 0;
        int leghoszabb = 0;
        for (int i=0; i<foglalasok.size();i++) {
            int hossz = (int) ChronoUnit.DAYS.between(foglalasok.get(i).foglalas_kezd, foglalasok.get(i).foglalas_vege);
            if (hossz > leghoszabb) {
                leghoszabb = hossz;
                leghoszabbindex = i;
            }
        }

        System.out.println("\n2. Leghosszabb foglalás adatai: ");
        System.out.println("Vandég: " + foglalasok.get(leghoszabbindex).l_name + " " + foglalasok.get(leghoszabbindex).f_name);
        System.out.println("Szálloda: " + foglalasok.get(leghoszabbindex).hotel_nev + " (" + foglalasok.get(leghoszabbindex).hotel_varos + ") ");
        System.out.println("Időszak: " + foglalasok.get(leghoszabbindex).foglalas_kezd + " - " + foglalasok.get(leghoszabbindex).foglalas_vege + " (" + leghoszabb + " nap)");

    }
}