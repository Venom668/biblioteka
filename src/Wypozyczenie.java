import java.io.Serializable;
import java.time.LocalDate;

public class Wypozyczenie implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private long id;
	private Ksiazka ksiazka;
	private Czytelnik czytelnik;
	private LocalDate data_wypozyczenia, termin_zwrotu;

	public Wypozyczenie(Ksiazka ksiazka, Czytelnik czytelnik, long id) {
		this.ksiazka = ksiazka;
		this.czytelnik = czytelnik;
		this.id = id;
		this.data_wypozyczenia = LocalDate.now();
		this.termin_zwrotu = LocalDate.now().plusMonths(1);
	}

	public Ksiazka getKsiazka() {
		return ksiazka;
	}

	public Long getKsiazkaID() {
		return ksiazka.getId();
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

	public LocalDate getDataWypozyczenia() {
		return data_wypozyczenia;
	}

	public LocalDate getTerminZwrotu() {
		return termin_zwrotu;
	}

	public boolean przeterminowane() {
		if (LocalDate.now().isAfter(termin_zwrotu)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return Long.toString(id) + "	" + ksiazka.getAutor() + "	" + ksiazka.getTytul() + "	" + czytelnik.getImieINazwisko() + "	" + getDataWypozyczenia().toString() + "	" + getTerminZwrotu().toString();
	}
}
