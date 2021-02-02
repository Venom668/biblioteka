import java.io.Serializable;

public class Wypozyczenie implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private long id;
	Ksiazka ksiazka;
	Czytelnik czytelnik;

	public Wypozyczenie(Ksiazka ksiazka, Czytelnik czytelnik, long id) {
		this.ksiazka = ksiazka;
		this.czytelnik = czytelnik;
		this.id = id;
	}

	public Ksiazka getKsiazka() {
		return ksiazka;
	}

	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}

	public Czytelnik getCzytelnik() {
		return czytelnik;
	}

	public Long getCzytelnikID() {
		return czytelnik.getId();
	}

	public void setCzytelnik(Czytelnik czytelnik) {
		this.czytelnik = czytelnik;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Long.toString(id) + "	" + ksiazka.getAutor() + "	" + ksiazka.getTytul() + "	" + czytelnik.getImieINazwisko();
	}
	
	public boolean wypozyczona() {
		return true;
	}

}
