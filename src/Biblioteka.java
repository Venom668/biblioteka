import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Collections;


public class Biblioteka implements Serializable {

	private static final long serialVersionUID = 1L;
	HashMap<Long, Ksiazka> ksiazki;
	HashMap<Long, Czytelnik> czytelnicy;
	HashMap<Long, Wypozyczenie> wypozyczenia;
	long id_czytelnika, id_ksiazki, id_wypozyczenia;
    
	public Biblioteka() {
		this.ksiazki = new HashMap<Long, Ksiazka>();
		this.czytelnicy = new HashMap<Long, Czytelnik>();
		this.wypozyczenia = new HashMap<Long, Wypozyczenie>();
		this.id_czytelnika = 0;
		this.id_ksiazki = 0;
		this.id_wypozyczenia = 0;

		try {
        FileInputStream fis = new FileInputStream("ksiazki.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		this.ksiazki = (HashMap<Long, Ksiazka>) ois.readObject();
		ois.close();
		fis.close();
		
		fis = new FileInputStream("czytelnicy.ser");
		ois = new ObjectInputStream(fis);
		this.czytelnicy = (HashMap<Long, Czytelnik>) ois.readObject();
		ois.close();
		fis.close();

		fis = new FileInputStream("wypozyczenia.ser");
		ois = new ObjectInputStream(fis);
		this.wypozyczenia = (HashMap<Long, Wypozyczenie>) ois.readObject();
		ois.close();
		fis.close();

		this.id_czytelnika = Collections.max(czytelnicy.keySet());
		this.id_ksiazki = Collections.max(ksiazki.keySet());
		this.id_wypozyczenia = Collections.max(wypozyczenia.keySet());
		} catch (IOException | ClassNotFoundException | ClassCastException | NoSuchElementException e) {
			System.out.println("Nie udało się odczytać bazy.");
		}

    }
    
	public void wyswietlCzytelnikow () {
		String format = "| %-2s | %-12s | %-20s|%n";
		System.out.println("+----+--------------+---------------------+");
		System.out.println("| ID |     Imię     |     Nazwisko        |");
		System.out.println("+----+--------------+---------------------+");
		for (Czytelnik c: czytelnicy.values()) {
			System.out.printf(format, (Object[]) c.toString().split("	"));
		}
		System.out.println("+----+--------------+---------------------+");
	}

	public void wyswietlKsiazki() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytuł         |         ISBN      | Egzemplarze,  wypożyczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		for (Ksiazka k: ksiazki.values()) {
			System.out.printf(format, (Object[]) k.toString().split("	"));
		}
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}	

	public void wyswietlDostepneKsiazki() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytuł         |         ISBN      | Egzemplarze,  wypożyczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		for (Ksiazka k: ksiazki.values()) {
			if (k.dostepna()) {
				System.out.printf(format, (Object[]) k.toString().split("	"));
			}
		}
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}	

	public void wyswietlWypozyczenia() {
		String format = "| %-2s | %-21s | %-25s | %-21s |%n";
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
		System.out.println("| ID |         Autor         |           Tytuł           |       Czytelnik       |");
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
		for (Wypozyczenie w: wypozyczenia.values()) {
			System.out.printf(format, (Object[]) w.toString().split("	"));
		}
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
	}

	public HashMap<Long, Ksiazka> getKsiazki() {
		return ksiazki;
	}

	public void setKsiazki(HashMap<Long, Ksiazka> ksiazki) { //remove
		this.ksiazki = ksiazki;
	}

	public HashMap<Long, Czytelnik> getCzytelnicy() {
		return czytelnicy;
	}

	//public Czytelnik getCzytelnik(long id) {
//
	//	return false;
	//}

	public void setCzytelnicy(HashMap<Long, Czytelnik> czytelnicy) { //remove
		this.czytelnicy = czytelnicy;
	}

	public HashMap<Long, Wypozyczenie> getWypozyczenia() {
		return wypozyczenia;
	}

	public void setWypozyczenia(HashMap<Long, Wypozyczenie> wypozyczenia) { //remove
		this.wypozyczenia = wypozyczenia;
	}
	
	public long getNumer_czytelnika() {
		return id_czytelnika;
	}

	public void setNumer_czytelnika(long id_czytelnika) {
		this.id_czytelnika = id_czytelnika;
	}

	public long getNumer_ksiazki() {
		return id_czytelnika;
	}

	public void setNumer_ksiazki(long id_ksiazki) {
		this.id_ksiazki = id_ksiazki;
	}

	public long getNumer_wypozyczenia() {
		return id_wypozyczenia;
	}

	public void setNumer_wypozyczenia(long id_wypozyczenia) {
		this.id_wypozyczenia = id_wypozyczenia;
	}
	
	public long kolejny_numer_czytelnika() {
		return id_czytelnika++;
	}

	public long kolejny_numer_ksiazki() {
		return id_ksiazki++;
	}

	public long kolejny_numer_wypozyczenia() {
		return id_wypozyczenia++;
	}
	
	public void dodajKsiazke(Long id, Ksiazka k) {
		ksiazki.put(id ,k);
	}

	public void dodajCzytelnika(Long id, Czytelnik c) {
		czytelnicy.put(id, c);
	}

	public void dodajWypozyczenie(Long id, Wypozyczenie w) {
		wypozyczenia.put(id, w);
	}

	public void usunKsiazke(Long id) { //TODO verification
		ksiazki.remove(id);
	}

	public void usunCzytelnika(Long id) {
		czytelnicy.remove(id);
	}

	public void usunWypozyczenie(Long id) {
		wypozyczenia.remove(id);
	}
	
	public boolean wypozyczKsiazke(Long id_czytelnika, Long id_ksiazki) {
		if (ksiazki.get(id_ksiazki).wypozycz() && !czytelnicy.get(id_czytelnika).zablokowany) {
			dodajWypozyczenie(getNumer_wypozyczenia(), new Wypozyczenie(ksiazki.get(id_ksiazki), czytelnicy.get(id_czytelnika), kolejny_numer_wypozyczenia()));
			return true;
		} else 
			return false;
	}

	public boolean zwrocKsiazke(Long id_wypozyczenia) {
		try {
			if (wypozyczenia.get(id_wypozyczenia).wypozyczona()) {
				wypozyczenia.get(id_wypozyczenia).getKsiazka().oddaj();
				usunWypozyczenie(id_wypozyczenia);
				return true;
			} else 
				return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public void wyswietlWypozyczeniaCzytelnika (Long id_czytelnika) {
		String format = "| %-2s | %-21s | %-25s | %-21s |%n";
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
		System.out.println("| ID |         Autor         |           Tytuł           |       Czytelnik       |");
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
		for (Wypozyczenie w: wypozyczenia.values()) {
			if (w.getCzytelnikID() == id_czytelnika) {
				System.out.printf(format, (Object[]) w.toString().split("	"));
			}
		}
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
	}
	
	public void saveKsiazkiDB(Biblioteka b) {
		try {
			FileOutputStream fos = new FileOutputStream("ksiazki.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(b.ksiazki);
			oos.close();
			fos.close();
			} catch (IOException e) {
				System.out.println("Nie udało się zapisać bazy.");
			}
	}

	public void saveCzytelnicyDB(Biblioteka b) {
		try {
			FileOutputStream fos = new FileOutputStream("czytelnicy.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(b.czytelnicy);
			oos.close();
			fos.close();
			} catch (IOException e) {
				System.out.println("Nie udało się zapisać bazy.");
			}
	}

	public void saveWypozyczeniaDB(Biblioteka b) {
		try {
			FileOutputStream fos = new FileOutputStream("wypozyczenia.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(b.wypozyczenia);
			oos.close();
			fos.close();
			} catch (IOException e) {
				System.out.println("Nie udało się zapisać bazy.");
			}
	}

	private void wyswietlWypozyczenie(Wypozyczenie w) { //remove
		// TODO	
	}

	public static void main(String[] args) {
		Biblioteka b = new Biblioteka();

		Menu.menu(b);

		b.saveCzytelnicyDB(b);
		b.saveKsiazkiDB(b);
		b.saveWypozyczeniaDB(b);
    }
}
