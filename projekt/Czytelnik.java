import java.io.Serializable;

public class Czytelnik implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String imie;
	String nazwisko;
	long id;
	boolean zablokowany = false;

	public Czytelnik(String imie, String nazwisko, long id) {
		//super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void zablokuj() {
		this.zablokowany = true;
	}

	public void odblokuj() {
		this.zablokowany = false;
	}

	@Override
	public String toString() {
		return id + "	" + imie + "	" + nazwisko;
	}
}
