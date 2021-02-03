import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Czytelnik implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String imie, nazwisko, pesel;
	private long id;
	private boolean zablokowany = false;
	private ArrayList<String> historia;

	public Czytelnik(String imie, String nazwisko, String pesel, long id) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.id = id;
		this.pesel = pesel;
		this.historia = new ArrayList<String>();
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getImieINazwisko() {
		return imie + " " + nazwisko;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean zablokowany() {
		return zablokowany;
	}

	public void zablokuj() {
		this.zablokowany = true;
	}

	public void odblokuj() {
		this.zablokowany = false;
	}

	public ArrayList<String> getHistoria() {
		return historia;
	}

	public void addHistoria(Ksiazka ksiazka, LocalDate data_wypozyczenia, LocalDate data_zwrotu) {
		historia.add(ksiazka.getAutor() + "	" + ksiazka.getTytul() + "	" + data_wypozyczenia.toString() + "	" + data_zwrotu.toString());
	}

	@Override
	public String toString() {
		return id + "	" + imie + "	" + nazwisko + "	" + pesel;
	}
}
