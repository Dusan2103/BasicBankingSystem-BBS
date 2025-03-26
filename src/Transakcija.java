package pkg2022240874_dusan_petrovic;
/**
 *
 * @author Dušan
 */
public class Transakcija {
    public static void izvrsiTransakciju(Racun saRacuna, Racun naRacun, double iznos) {
        if (saRacuna.valuta.equals(naRacun.valuta)) {
            if (saRacuna.proveriSredstva(iznos)) {
                saRacuna.skiniSredstva(iznos);
                naRacun.dodajSredstva(iznos);
                System.out.println("Transakcija od " + iznos + " " + saRacuna.valuta + " uspesno izvrsena.");
            } else {
                System.out.println("Nema dovoljno sredstava za transakciju.");
            }
        } else {
            System.out.println("Transakcija nije moguca između različitih valuta.");
        }
    }
}