package pkg2022240874_dusan_petrovic;

import java.util.ArrayList;

interface StedniPlan {
    void izracunajKamatu(int meseci);
}

public class Main {
    public static void main(String[] args) {
        Korisnik klijentA = new Korisnik("Petar", "Petrovic");
        Korisnik klijentB = new Korisnik("Jovan", "Jovanovic");

        DinarskiRacun dinarskiRacunA = new DinarskiRacun(50000);
        DevizniRacun devizniRacunA = new DevizniRacun(1000, 0.05); 

        DinarskiRacun dinarskiRacunB = new DinarskiRacun(30000);
        DevizniRacun devizniRacunB = new DevizniRacun(800, 0.05); 

        klijentA.dodajRacun(dinarskiRacunA);
        klijentA.dodajRacun(devizniRacunA);

        klijentB.dodajRacun(dinarskiRacunB);
        klijentB.dodajRacun(devizniRacunB);

        klijentA.prikaziRacune();
        klijentB.prikaziRacune();

        System.out.println("\nIzvrsavanje transakcije...");

        Transakcija.izvrsiTransakciju(dinarskiRacunA, dinarskiRacunB, 5000);

        klijentA.prikaziRacune();
        klijentB.prikaziRacune();

        System.out.println("\nRacunanje kamate za devizni racun:");
        devizniRacunA.izracunajKamatu(6);
        devizniRacunB.izracunajKamatu(6);
    }
}

class Racun {
    protected double stanje;
    protected String tipRacuna;
    protected String valuta;

    public Racun(double pocetnoStanje, String tipRacuna, String valuta) {
        this.stanje = pocetnoStanje;
        this.tipRacuna = tipRacuna;
        this.valuta = valuta;
    }

    public void prikaziStanje() {
        System.out.println(tipRacuna + ": " + stanje + " " + valuta);
    }

    public boolean proveriSredstva(double iznos) {
        return stanje >= iznos;
    }

    public void dodajSredstva(double iznos) {
        stanje += iznos;
    }

    public void skiniSredstva(double iznos) {
        stanje -= iznos;
    }
}

class DinarskiRacun extends Racun {
    public DinarskiRacun(double pocetnoStanje) {
        super(pocetnoStanje, "Dinarski racun", "RSD");
    }
}

class DevizniRacun extends Racun implements StedniPlan {
    private double kamatnaStopa;

    public DevizniRacun(double pocetnoStanje, double kamatnaStopa) {
        super(pocetnoStanje, "Devizni racun", "EUR");
        this.kamatnaStopa = kamatnaStopa;
    }

    @Override
    public void izracunajKamatu(int meseci) {
        double kamata = stanje * kamatnaStopa * meseci / 12;
        dodajSredstva(kamata);
        System.out.println("Kamata za " + meseci + " meseci: " + kamata + " EUR");
    }
}

class Korisnik {
    private String ime;
    private String prezime;
    private ArrayList<Racun> racuni;

    public Korisnik(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
        this.racuni = new ArrayList<>();
    }

    public void dodajRacun(Racun racun) {
        racuni.add(racun);
    }

    public void prikaziRacune() {
        System.out.println("Racuni za korisnika: " + ime + " " + prezime);
        for (Racun racun : racuni) {
            racun.prikaziStanje();
        }
    }

    public Racun getRacun(int indeks) {
        if (indeks >= 0 && indeks < racuni.size()) {
            return racuni.get(indeks);
        }
        return null;
    }
}