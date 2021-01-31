import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Collections;
import static java.util.Collections.reverseOrder;
import java.util.Comparator;
import java.util.List;

///Zawiera metody wykorzystywane w menu.
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
			System.out.println("Nie uda³‚o siê odczytaæ bazy.");
		}

    }
        ///Wyswietlanie czytelnikow wedlug alfabetycznej kolejnosci imion.
	public void wyswietlCzytelnikowImieAZ() {
		String format = "| %-2s | %-12s | %-20s|%n";
		System.out.println("+----+--------------+---------------------+");
		System.out.println("| ID |     Imiê     |     Nazwisko        |");
		System.out.println("+----+--------------+---------------------+");
                   // for (Czytelnik c: czytelnicy.values()) {
                    List<Czytelnik> czytelnicyImie = new ArrayList<>(czytelnicy.values());
                    Collections.sort(czytelnicyImie, Comparator.comparing(Czytelnik::getImie));
                        for (Czytelnik p : czytelnicyImie) {
                        //System.out.println(c.getId() + "\t" + c.getImie() + "\t" + c.getNazwisko());
                        System.out.printf(format, (Object[]) p.toString().split("	"));
                        }
			//System.out.printf(format, (Object[]) c.toString().split("	"));
                    //}
		System.out.println("+----+--------------+---------------------+");
	}
        
        ///Wyswietlanie czytelnikow wedlug odwrotnej alfabetycznej kolejnosci imion.
	public void wyswietlCzytelnikowImieZA() {
		String format = "| %-2s | %-12s | %-20s|%n";
		System.out.println("+----+--------------+---------------------+");
		System.out.println("| ID |     Imiê     |     Nazwisko        |");
		System.out.println("+----+--------------+---------------------+");
                   // for (Czytelnik c: czytelnicy.values()) {
                    List<Czytelnik> czytelnicyImie = new ArrayList<>(czytelnicy.values());
                    Collections.sort(czytelnicyImie, Comparator.comparing(Czytelnik::getImie, reverseOrder()));
                        for (Czytelnik p : czytelnicyImie) {
                        //System.out.println(c.getId() + "\t" + c.getImie() + "\t" + c.getNazwisko());
                        System.out.printf(format, (Object[]) p.toString().split("	"));
                        }
			//System.out.printf(format, (Object[]) c.toString().split("	"));
                    //}
		System.out.println("+----+--------------+---------------------+");
	}        
        
        ///Wyswietlanie czytelnikow wedlug alfabetycznej kolejnosci nazwisk.
        public void wyswietlCzytelnikowNazwiskoAZ(){
		String format = "| %-2s | %-12s | %-20s|%n";
		System.out.println("+----+--------------+---------------------+");
		System.out.println("| ID |     Imiê     |     Nazwisko        |");
		System.out.println("+----+--------------+---------------------+");
                    List<Czytelnik> czytelnicyNazwisko = new ArrayList<>(czytelnicy.values());
                    Collections.sort(czytelnicyNazwisko, Comparator.comparing(Czytelnik::getNazwisko));
                        for (Czytelnik p : czytelnicyNazwisko) {
                        System.out.printf(format, (Object[]) p.toString().split("	"));
                        }
		System.out.println("+----+--------------+---------------------+");
	}
        
        ///Wyswietlanie czytelnikow wedlug odwrotnej alfabetycznej kolejnosci nazwisk.
        public void wyswietlCzytelnikowNazwiskoZA(){
		String format = "| %-2s | %-12s | %-20s|%n";
		System.out.println("+----+--------------+---------------------+");
		System.out.println("| ID |     Imiê     |     Nazwisko        |");
		System.out.println("+----+--------------+---------------------+");
                    List<Czytelnik> czytelnicyNazwisko = new ArrayList<>(czytelnicy.values());
                    Collections.sort(czytelnicyNazwisko, Comparator.comparing(Czytelnik::getNazwisko, reverseOrder()));
                        for (Czytelnik p : czytelnicyNazwisko) {
                        System.out.printf(format, (Object[]) p.toString().split("	"));
                        }
		System.out.println("+----+--------------+---------------------+");
	}        

        ///Wyswietlanie czytelnikow wedlug rosnacej kolejnosci ID.
	public void wyswietlCzytelnikowIDLOW() {
		String format = "| %-2s | %-12s | %-20s|%n";
		System.out.println("+----+--------------+---------------------+");
		System.out.println("| ID |     Imiê     |     Nazwisko        |");
		System.out.println("+----+--------------+---------------------+");
                    List<Czytelnik> czytelnicyID = new ArrayList<>(czytelnicy.values());
                    Collections.sort(czytelnicyID, Comparator.comparing(Czytelnik::getId));
                        for (Czytelnik c : czytelnicyID) {
                        System.out.printf(format, (Object[]) c.toString().split("	"));
                        }
		System.out.println("+----+--------------+---------------------+");
	}        

        ///Wyswietlanie czytelnikow wedlug malejacej kolejnosci ID.
	public void wyswietlCzytelnikowIDHIGH() {
		String format = "| %-2s | %-12s | %-20s|%n";
		System.out.println("+----+--------------+---------------------+");
		System.out.println("| ID |     Imiê     |     Nazwisko        |");
		System.out.println("+----+--------------+---------------------+");
                    List<Czytelnik> czytelnicyID = new ArrayList<>(czytelnicy.values());
                    Collections.sort(czytelnicyID, Comparator.comparing(Czytelnik::getId, reverseOrder()));
                        for (Czytelnik c : czytelnicyID) {
                        System.out.printf(format, (Object[]) c.toString().split("	"));
                        }
		System.out.println("+----+--------------+---------------------+");
	}            
                
        ///Wyswietlanie ksiazek wedlug odwrotnej alfabetycznej kolejnosci autorow.
	public void wyswietlKsiazkiAutorAZ() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytu³‚         |         ISBN      | Egzemplarze,  wypo¿yczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
                    List<Ksiazka> ksiazkiAutor = new ArrayList<>(ksiazki.values());
                    Collections.sort(ksiazkiAutor, Comparator.comparing(Ksiazka::getAutor));
                        for (Ksiazka k : ksiazkiAutor) {
                        System.out.printf(format, (Object[]) k.toString().split("	"));
                        }
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}
        
        ///Wyswietlanie ksiazek wedlug odwrotnej alfabetycznej kolejnosci autorow.
        public void wyswietlKsiazkiAutorZA() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytu³‚         |         ISBN      | Egzemplarze,  wypo¿yczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
                    List<Ksiazka> ksiazkiAutor = new ArrayList<>(ksiazki.values());
                    Collections.sort(ksiazkiAutor, Comparator.comparing(Ksiazka::getAutor, reverseOrder()));
                        for (Ksiazka k : ksiazkiAutor) {
                        System.out.printf(format, (Object[]) k.toString().split("	"));
                        }
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}

        ///Wyswietlanie ksiazek wedlug alfabetycznej kolejnosci tytulow.
	public void wyswietlKsiazkiTytulAZ() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytu³‚         |         ISBN      | Egzemplarze,  wypo¿yczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
                    List<Ksiazka> ksiazkiTytul = new ArrayList<>(ksiazki.values());
                    Collections.sort(ksiazkiTytul, Comparator.comparing(Ksiazka::getTytul));
                        for (Ksiazka k : ksiazkiTytul) {
                        System.out.printf(format, (Object[]) k.toString().split("	"));
                        }
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}	

        ///Wyswietlanie ksiazek wedlug odwrotnej alfabetycznej kolejnosci tytulow.
	public void wyswietlKsiazkiTytulZA() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytu³‚         |         ISBN      | Egzemplarze,  wypo¿yczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
                    List<Ksiazka> ksiazkiTytul = new ArrayList<>(ksiazki.values());
                    Collections.sort(ksiazkiTytul, Comparator.comparing(Ksiazka::getTytul, reverseOrder()));
                        for (Ksiazka k : ksiazkiTytul) {
                        System.out.printf(format, (Object[]) k.toString().split("	"));
                        }
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}        
        
        ///Wyswietlanie ksiazek wedlug rosnacej kolejnosci liczby egzemplarzy.
	public void wyswietlKsiazkiEgzemplarzeLOW() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytu³‚         |         ISBN      | Egzemplarze,  wypo¿yczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
                    List<Ksiazka> ksiazkiEgzemplarze = new ArrayList<>(ksiazki.values());
                    Collections.sort(ksiazkiEgzemplarze, Comparator.comparing(Ksiazka::getIlosc_egzemplarzy));
                        for (Ksiazka k : ksiazkiEgzemplarze) {
                        System.out.printf(format, (Object[]) k.toString().split("	"));
                        }
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}	   
        
        ///Wyswietlanie ksiazek wedlug malejacej kolejnosci liczby egzemplarzy.
	public void wyswietlKsiazkiEgzemplarzeHIGH() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytu³‚         |         ISBN      | Egzemplarze,  wypo¿yczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
                    List<Ksiazka> ksiazkiEgzemplarze = new ArrayList<>(ksiazki.values());
                    Collections.sort(ksiazkiEgzemplarze, Comparator.comparing(Ksiazka::getIlosc_egzemplarzy, reverseOrder()));
                        for (Ksiazka k : ksiazkiEgzemplarze) {
                        System.out.printf(format, (Object[]) k.toString().split("	"));
                        }
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}        
        
        ///Wyswietlanie dostepnych ksiazek.
	public void wyswietlDostepneKsiazki() {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		System.out.println("|         Autor         |         Tytu³‚         |         ISBN      | Egzemplarze,  wypo¿yczone  |");
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
		for (Ksiazka k: ksiazki.values()) {
			if (k.dostepna()) {
				System.out.printf(format, (Object[]) k.toString().split("	"));
			}
		}
		System.out.println("+-----------------------+-----------------------+-------------------+----------------------------+");
	}	

        ///Wyswietlanie wszystkich wypozyczen ksiazek.
	public void wyswietlWypozyczenia() {
		String format = "| %-2s | %-21s | %-25s | %-21s |%n";
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
		System.out.println("| ID |         Autor         |           Tytu³‚           |       Czytelnik       |");
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
	
        ///Wupozyczenie ksiazki.
	public boolean wypozyczKsiazke(Long id_czytelnika, Long id_ksiazki) {
		if (ksiazki.get(id_ksiazki).wypozycz() && !czytelnicy.get(id_czytelnika).zablokowany) {
			dodajWypozyczenie(getNumer_wypozyczenia(), new Wypozyczenie(ksiazki.get(id_ksiazki), czytelnicy.get(id_czytelnika), kolejny_numer_wypozyczenia()));
			return true;
		} else 
			return false;
	}

        ///Zwracanie ksiazki.
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

        ///Wyswietlanie wypozyczen wybranego czytelnika.
	public void wyswietlWypozyczeniaCzytelnika (Long id_czytelnika) {
		String format = "| %-2s | %-21s | %-25s | %-21s |%n";
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
		System.out.println("| ID |         Autor         |           Tytu³‚           |       Czytelnik       |");
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
		for (Wypozyczenie w: wypozyczenia.values()) {
			if (w.getCzytelnikID() == id_czytelnika) {
				System.out.printf(format, (Object[]) w.toString().split("	"));
			}
		}
		System.out.println("+----+-----------------------+---------------------------+-----------------------+");
	}
	
        ///Zapisywanie ksiazki do pliku
	public void saveKsiazkiDB(Biblioteka b) {
		try {
			FileOutputStream fos = new FileOutputStream("ksiazki.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(b.ksiazki);
			oos.close();
			fos.close();
			} catch (IOException e) {
				System.out.println("Nie uda³o siê zapisaæ bazy.");
			}
	}

        ///Zapisywanie czytelnika do pliku
	public void saveCzytelnicyDB(Biblioteka b) {
		try {
			FileOutputStream fos = new FileOutputStream("czytelnicy.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(b.czytelnicy);
			oos.close();
			fos.close();
			} catch (IOException e) {
				System.out.println("Nie uda³o siê zapisaæ bazy.");
			}
	}

        ///Zapisywanie wypozyczenia do pliku
	public void saveWypozyczeniaDB(Biblioteka b) {
		try {
			FileOutputStream fos = new FileOutputStream("wypozyczenia.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(b.wypozyczenia);
			oos.close();
			fos.close();
			} catch (IOException e) {
				System.out.println("Nie uda³o siê zapisaæ bazy.");
			}
	}

	///Wyszukiwanie ksiazki po wskazanym parametrze.
	public void wyszukajKsiazki(String parametr, String wybor) {
		String format = "| %-21s | %-21s | %-17s | %-26s |%n";
		boolean czyZnalazl = false;
		
		
		switch(wybor)
		{
			

			case "1" :
		
				for (Ksiazka k: ksiazki.values()) {
					if (k.getAutor().contains(parametr)) {
						czyZnalazl = true;
						break;
					}
				}
				if(czyZnalazl)
				{
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
					System.out.println("|         Autor         |         Tytu³‚         |         ISBN      |    Ilosc egzemplarzy    |");
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
					for (Ksiazka k: ksiazki.values()) {
						if (k.getAutor().contains(parametr)) {
							
							System.out.printf("| %-21s ", k.getAutor());
							System.out.printf("| %-21s ", k.getTytul());
							System.out.printf("| %-17s ", k.getIsbn());
							System.out.printf("| %12d ",k.getIlosc_egzemplarzy());
							System.out.printf(" %9s |%n","");



						}
					}
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
				}
				else
				{
					System.out.println("Nic nie znaleziono.");
				}
			break;
			case "2" :
				
				for (Ksiazka k: ksiazki.values()) {
					if (k.getTytul().contains(parametr)) {
						czyZnalazl = true;
						break;
					}
				}

				if(czyZnalazl)
				{
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
					System.out.println("|         Autor         |         Tytu³‚         |         ISBN      |    Ilosc egzemplarzy    |");
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
					for (Ksiazka k: ksiazki.values()) {
						if (k.getTytul().contains(parametr)) {
							System.out.printf("| %-21s ", k.getAutor());
							System.out.printf("| %-21s ", k.getTytul());
							System.out.printf("| %-17s ", k.getIsbn());
							System.out.printf("| %12d ",k.getIlosc_egzemplarzy());
							System.out.printf(" %9s |%n","");
						}
					}
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
				}
				else
				{
					System.out.println("Nic nie znaleziono.");
				}
			break;
			case "3" :	
				for (Ksiazka k: ksiazki.values()) {
					if (k.getIlosc_egzemplarzy() == Integer.parseInt(parametr)) {
						czyZnalazl = true;
						break;
					}
				}	
				
				if(czyZnalazl)
				{
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
					System.out.println("|         Autor         |         Tytu³‚         |         ISBN      |    Ilosc egzemplarzy    |");
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
					for (Ksiazka k: ksiazki.values()) {
						if (k.getIlosc_egzemplarzy() == Integer.parseInt(parametr)) {
							System.out.printf("| %-21s ", k.getAutor());
							System.out.printf("| %-21s ", k.getTytul());
							System.out.printf("| %-17s ", k.getIsbn());
							System.out.printf("| %12d ",k.getIlosc_egzemplarzy() );
							System.out.printf(" %9s |%n","");

						}
					}	
					System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
				}
				else
				{
					System.out.println("Nic nie znaleziono.");
				}
				
				
			break;
			case "4" :
				for (Ksiazka k: ksiazki.values()) {
					
					if (k.getIsbn().contains(parametr)) {
						czyZnalazl = true;
						break;
					}
				}

			if(czyZnalazl)
			{
				System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
				System.out.println("|         Autor         |         Tytu³‚         |         ISBN      |    Ilosc egzemplarzy    |");
				System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
				for (Ksiazka k: ksiazki.values()) {
				
					if (k.getIsbn().contains(parametr)) {
						System.out.printf("| %-21s ", k.getAutor());
						System.out.printf("| %-21s ", k.getTytul());
						System.out.printf("| %-17s ", k.getIsbn());
						System.out.printf("| %12d ",k.getIlosc_egzemplarzy() );
						System.out.printf(" %9s |%n","");
						


					}
				}
				System.out.println("+-----------------------+-----------------------+-------------------+-------------------------+");
			}
			else
			{
				System.out.println("Nic nie znaleziono.");
			}
				
			break;
		}
		
		
	}	

	public static void main(String[] args) {
		Biblioteka b = new Biblioteka();

		Menu.menu(b);

		b.saveCzytelnicyDB(b);
		b.saveKsiazkiDB(b);
		b.saveWypozyczeniaDB(b);
    }
}
