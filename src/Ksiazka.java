import java.io.Serializable;

public class Ksiazka implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String autor;
	private String tytul;
	private String isbn;
	private int ilosc_egzemplarzy;
	private int ilosc_wypozyczonych_egzemplarzy;
	private long id;
	
	public Ksiazka(String autor, String tytul, String isbn, int ilosc_egzemplarzy, long id) {
		this.autor = autor;
		this.tytul = tytul;
		this.isbn = isbn;
		this.ilosc_egzemplarzy = ilosc_egzemplarzy;
		this.ilosc_wypozyczonych_egzemplarzy = 0;
		this.id = id;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getIlosc_egzemplarzy() {
		return ilosc_egzemplarzy;
	}

	public void setIlosc_egzemplarzy(int ilosc_egzemplarzy) {
		this.ilosc_egzemplarzy = ilosc_egzemplarzy;
	}

	public int getIlosc_wypozyczonych_egzemplarzy() {
		return ilosc_wypozyczonych_egzemplarzy;
	}

	public void setIlosc_wypozyczonych_egzemplarzy(int ilosc_wypozyczonych_egzemplarzy) {
		this.ilosc_wypozyczonych_egzemplarzy = ilosc_wypozyczonych_egzemplarzy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	boolean wypozycz() {
		if (ilosc_egzemplarzy - ilosc_wypozyczonych_egzemplarzy > 0) {
			ilosc_wypozyczonych_egzemplarzy += 1;
			return true;
		}
		else
			return false;
	}

	boolean oddaj() {
			ilosc_wypozyczonych_egzemplarzy -= 1;
			return true;
	}

	boolean dostepna() {
		if (ilosc_egzemplarzy - ilosc_wypozyczonych_egzemplarzy > 0) {
			return true;
		}
		else
			return false;
	}

	@Override
	public String toString() {
		return autor + "	" + tytul + "	" + isbn + "	egz.: " + ilosc_egzemplarzy + ",      wyp.: " + ilosc_wypozyczonych_egzemplarzy + "	" + Long.toString(id);
	}
}
