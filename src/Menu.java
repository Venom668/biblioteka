import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {

	private static void clear() {
		for (int i = 0; i < 100; ++i) System.out.println();
		return;
	}

	public static void menu(Biblioteka b) {
		Scanner input = new Scanner(System.in);
		String wybor;
		do {
			clear();
			System.out.println("+--------------------------------+");
			System.out.println("|-----------Biblioteka-----------|");
			System.out.println("+--------------------------------+");
			System.out.println("|                                |");
			System.out.println("|  1. Wypożyczanie książek       |");
			System.out.println("|  2. Zwrot książek              |");
			System.out.println("|  3. Zarządzanie książkami      |");
			System.out.println("|  4. Zarządzanie czytelnikami   |");
			System.out.println("|  5. Zarządzanie wypożyczeniami |");
			System.out.println("|  6. Wyszukiwanie               |");
			System.out.println("|                                |");
			System.out.println("|  0. Wyjście                    |");
			System.out.println("+--------------------------------+");
			System.out.print("Wybierz opcję pomiędzy 0 a 6: ");
			wybor = input.nextLine();
		
			switch (wybor)
			{
				case "1" :
					do {
						clear();
						System.out.println("+--------------------------------+");
						System.out.println("|-----------Biblioteka-----------|");
						System.out.println("+--------------------------------+");
						System.out.println("|                                |");
						System.out.println("|  1. Wypożycz książkę           |");
						System.out.println("|  2. Wyświetl dostępne książki  |");
						System.out.println("|  3. Wyświetl listę czytelników |");
						System.out.println("|                                |");
						System.out.println("|  0. Powrót                     |");
						System.out.println("+--------------------------------+");
						System.out.print("Wybierz opcję pomiędzy 0 a 3: ");
						wybor = input.nextLine();

						switch (wybor)
						{
							case "1" :
								Long id_czytelnika, id_ksiazki;
								try {
									clear();
									System.out.print("Wprowadź ID czytelnika: ");
									id_czytelnika = Long.parseLong(input.nextLine());
									
									System.out.printf("%nWprowadź ID ksiazki: ");
									id_ksiazki = Long.parseLong(input.nextLine());
									clear();
									if (b.wypozyczKsiazke(id_czytelnika, id_ksiazki)) {
										b.saveWypozyczeniaDB(b);
										System.out.println("Wypożyczono książkę " + b.getKsiazki().get(id_ksiazki).getTytul() + " czytelnikowi " + b.getCzytelnicy().get(id_czytelnika).getImieINazwisko());
										System.out.println("Wciśnij enter aby kontynuować ");
										input.nextLine();
									} else {
										System.out.println("Nie udało się wypozyczyć książki.");
										System.out.println("Wciśnij enter aby kontynuować ");
										input.nextLine();
									}
								} catch (NumberFormatException e) {
									clear();
									System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
									input.nextLine();
								}
								wybor = "";
								break;
							case "2" :
								clear();
								b.wyswietlDostepneKsiazki();
								System.out.print("Wciśnij enter aby kontynuować ");
								input.nextLine();
								wybor = "";
								break;
							case "3" :
								clear();
								b.wyswietlCzytelnikowIDLOW();
								System.out.print("Wciśnij enter aby kontynuować ");
								input.nextLine();
								wybor = "";
								break;
							case "0" :
								break;
						}
					} while (!wybor.contentEquals("0"));
					wybor = "";
					break;

				case "2" : 
					do {
						clear();
						System.out.println("+--------------------------------------+");
						System.out.println("|-----------Biblioteka-----------------|");
						System.out.println("+--------------------------------------+");
						System.out.println("|                                      |");
						System.out.println("|  1. Zwróć książkę                    |");
						System.out.println("|  2. Wyświetl wypożyczenia czytelnika |");
						System.out.println("|  3. Wyświetl wszystkie wypożyczenia  |");
						System.out.println("|                                      |");
						System.out.println("|  0. Powrót                           |");
						System.out.println("+--------------------------------------+");
						System.out.print("Wybierz opcję pomiędzy 0 a 3: ");
						wybor = input.nextLine();

						switch (wybor)
						{
							case "1" :
								Long id_wypozyczenia;
								try {
									clear();
									System.out.print("Wprowadź ID wypożyczenia: ");
									id_wypozyczenia = Long.parseLong(input.nextLine());
									clear();
									
									if (b.zwrocKsiazke(id_wypozyczenia)) {
										b.saveWypozyczeniaDB(b);
										System.out.println("Zwrócono książkę.");
										System.out.println("Wciśnij enter aby kontynuować ");
										input.nextLine();
									} else {
										System.out.println("Nie udało się zwrócić książki.");
										System.out.println("Wciśnij enter aby kontynuować ");
										input.nextLine();
									}
								} catch (NumberFormatException e) {
									System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
									input.nextLine();
								}
								wybor = "";
								break;
							case "2" :
								Long id_czytelnika;
								try {
									clear();
									System.out.print("Wprowadź ID czytelnika: ");
									id_czytelnika = Long.parseLong(input.nextLine());
									clear();
									b.wyswietlWypozyczeniaCzytelnika(id_czytelnika);
									System.out.println("Wciśnij enter aby kontynuować ");
									input.nextLine();
								} catch (NumberFormatException e) {
									clear();
									System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
									input.nextLine();
								}
								wybor = "";
								break;
							case "3" :
								clear();
								b.wyswietlWypozyczenia();
								System.out.print("Wciśnij enter aby kontynuować ");
								input.nextLine();
								wybor = "";
								break;
							case "0" :
								break;
						}
					} while (!wybor.contentEquals("0"));
					wybor = "";
					break;

				case "3" : 
					do {
						clear();
						System.out.println("+--------------------------------+");
						System.out.println("|-----------Biblioteka-----------|");
						System.out.println("+--------------------------------+");
						System.out.println("|                                |");
						System.out.println("|  1. Dodaj książkę              |");
						System.out.println("|  2. Edytuj książkę             |");
						System.out.println("|  3. Usuń książkę               |");
						System.out.println("|  4. Wyświetl wszystkie książki |");
						System.out.println("|                                |");
						System.out.println("|  0. Powrót                     |");
						System.out.println("+--------------------------------+");
						System.out.print("Wybierz opcję pomiędzy 0 a 4: ");
						wybor = input.nextLine();

						switch (wybor)
						{
							case "1" :
								String autor, tytul, isbn;
								int ilosc_egzemplarzy;
								try {
									clear();
									System.out.print("Podaj autora książki: ");
									autor = input.nextLine();
									
									System.out.printf("%nPodaj tytuł ksiazki: ");
									tytul = input.nextLine();

									System.out.printf("%nPodaj ISBN ksiazki: ");
									isbn = input.nextLine();

									System.out.printf("%nPodaj ilość egzemplarzy: ");
									ilosc_egzemplarzy = Integer.parseInt(input.nextLine());
									try {
									b.dodajKsiazke(b.ksiazki.lastKey()+1, new Ksiazka(autor, tytul, isbn, ilosc_egzemplarzy, b.ksiazki.lastKey()+1));
									} catch (NoSuchElementException e) {
										b.dodajKsiazke((long) 0, new Ksiazka(autor, tytul, isbn, ilosc_egzemplarzy, (long) 0)); //pierwszy element
									}
									b.saveKsiazkiDB(b);
									clear();
									System.out.println("Dodano nową książkę.");
									System.out.print("Wciśnij enter aby kontynuować ");
									input.nextLine();
								} catch (NumberFormatException e) {
									clear();
									System.out.println("Niepoprawny numer egzemplarzy, naciśnij enter aby powrócić."); 
									input.nextLine();
								}
								wybor = "";
								break;
							case "2" :
								Long id_ksiazki = null;
								try {
									clear();
									System.out.printf("%nWprowadź ID ksiazki: ");
									id_ksiazki = Long.parseLong(input.nextLine());
									if (b.getKsiazki().get(id_ksiazki) == null) throw new NumberFormatException();
								} catch (NumberFormatException e) {
									clear();
									System.out.println("Niepoprawne ID, naciśnij enter aby powrócić.");
									input.nextLine();
									wybor = "0";
								}
								while (!wybor.contentEquals("0")) {
									clear();
									System.out.println("+--------------------------------+");
									System.out.println("|-----------Biblioteka-----------|");
									System.out.println("+--------------------------------+");
									System.out.println("|                                |");
									System.out.println("|  1. Zmień autora               |");
									System.out.println("|  2. Zmień tytuł                |");
									System.out.println("|  3. Zmień ISBN                 |");
									System.out.println("|  4. Zmień ilość egzemplarzy    |");
									System.out.println("|                                |");
									System.out.println("|  0. Powrót                     |");
									System.out.println("+--------------------------------+");
									System.out.print("Wybierz opcję pomiędzy 0 a 4: ");
									wybor = input.nextLine();
				
									switch (wybor)
									{
										case "1" :
											clear();
											System.out.print("Podaj nowego autora książki: ");
											autor = input.nextLine();
											clear();
											b.ksiazki.get(id_ksiazki).setAutor(autor);
											b.saveKsiazkiDB(b);
											System.out.println("Zmieniono autora.");
											System.out.print("Wciśnij enter aby kontynuować ");
											input.nextLine();
											wybor = "";
											break;
										case "2" :
											clear();
											System.out.print("Podaj nowy tytuł książki: ");
											tytul = input.nextLine();
											clear();
											b.ksiazki.get(id_ksiazki).setTytul(tytul);
											b.saveKsiazkiDB(b);
											System.out.println("Zmieniono tytuł.");
											System.out.print("Wciśnij enter aby kontynuować ");
											input.nextLine();
											wybor = "";
											break;
										case "3" :
											clear();
											System.out.print("Podaj nowy ISBN: ");
											isbn = input.nextLine();
											clear();
											b.ksiazki.get(id_ksiazki).setIsbn(isbn);
											b.saveKsiazkiDB(b);
											System.out.println("Zmieniono ISBN.");
											System.out.print("Wciśnij enter aby kontynuować ");
											input.nextLine();
											wybor = "";
											break;
										case "4" :
											try {
												clear();
												System.out.printf("%nPodaj nową ilość egzemplarzy: ");
												ilosc_egzemplarzy = Integer.parseInt(input.nextLine());
												clear();
												b.ksiazki.get(id_ksiazki).setIlosc_egzemplarzy(ilosc_egzemplarzy);
												b.saveKsiazkiDB(b);
												System.out.println("Zmieniono ilość egzemplarzy.");
												System.out.print("Wciśnij enter aby kontynuować ");
												input.nextLine();
											} catch (NumberFormatException e) {
												clear();
												System.out.println("Niepoprawny numer egzemplarzy, naciśnij enter aby powrócić."); 
												input.nextLine();
											}
											wybor = "";
											break;
										case "0" :
											break;
									}
									wybor = "";
									break;
								}
								wybor = "";
								break;
						case "3" :
							try {
								clear();
								System.out.printf("%nWprowadź ID ksiazki: ");
								id_ksiazki = Long.parseLong(input.nextLine());
								clear();
								if (b.getKsiazki().get(id_ksiazki) == null) throw new NumberFormatException();
								b.usunKsiazke(id_ksiazki);
								b.saveKsiazkiDB(b);
								System.out.println("Usunięto książkę.");
								System.out.print("Wciśnij enter aby kontynuować ");
								input.nextLine();
							} catch (NumberFormatException e) {
								System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
								input.nextLine();
							}
							wybor = "";
							break;
						case "4" :
                            do {
                                clear();
                                System.out.println("+--------------------------------+");
                                System.out.println("|-----------Biblioteka-----------|");
                                System.out.println("+--------------------------------+");
                                System.out.println("|                                |");
                                System.out.println("|  1. Sortuj po autorze A-Z      |");
                                System.out.println("|  2. Sortuj po autorze Z-A      |");
                                System.out.println("|  3. Sortuj po tytule A-Z       |");
                                System.out.println("|  4. Sortuj po tytule Z-A       |");
                                System.out.println("|  5. Sortuj po egz. rosnąco     |");
                                System.out.println("|  6. Sortuj po egz. malejąco    |");
                                System.out.println("|                                |");
                                System.out.println("|  0. Powrót                     |");
                                System.out.println("+--------------------------------+");
                                System.out.print("Wybierz opcję pomiędzy 0 a 6: ");
                                wybor = input.nextLine();
								switch (wybor)
								{
									case "1" :
                                                                        	b.wyswietlKsiazkiAutorAZ();
                                                                        	System.out.print("Wciśnij enter aby kontynuować ");
                                                                        	input.nextLine();
                                                                        	wybor = "";
                                                                        	break;
                                    case "2" :
                                                                        	b.wyswietlKsiazkiAutorZA();
                                                                        	System.out.print("Wciśnij enter aby kontynuować ");
                                                                        	input.nextLine();
                                                                        	wybor = "";
                                                                        	break;
									case "3" :
                                                                        	b.wyswietlKsiazkiTytulAZ();
                                                                        	System.out.print("Wciśnij enter aby kontynuować ");
                                                                        	input.nextLine();
                                                                        	wybor = "";
                                                                        	break;
									case "4" :
                                                                        	b.wyswietlKsiazkiTytulZA();
                                                                        	System.out.print("Wciśnij enter aby kontynuować ");
                                                                        	input.nextLine();
                                                                        	wybor = "";
                                                                        	break;
                                    case "5" :
                                                                        	b.wyswietlKsiazkiEgzemplarzeLOW();
                                                                        	System.out.print("Wciśnij enter aby kontynuować ");
                                                                        	input.nextLine();
                                                                        	wybor = "";
                                                                        	break;
                                    case "6" :
                                                                        	b.wyswietlKsiazkiEgzemplarzeHIGH();
                                                                        	System.out.print("Wciśnij enter aby kontynuować ");
                                                                        	input.nextLine();
                                                                        	wybor = "";
                                                                        	break;
									case "0" :
										break;
								}
							} while (!wybor.contentEquals("0"));
							wybor = "";
							break;
						case "0" :
							break;
						}
					} while (!wybor.contentEquals("0"));
					wybor = "";
					break;

				case "4" : 
					do {
						clear();
						System.out.println("+--------------------------------+");
						System.out.println("|-----------Biblioteka-----------|");
						System.out.println("+--------------------------------+");
						System.out.println("|                                |");
						System.out.println("|  1. Dodaj czytelnika           |");
						System.out.println("|  2. Edytuj czytelnika          |");
						System.out.println("|  3. Usuń czytelnika            |");
						System.out.println("|  4. Wyświetl czytelników       |");
						System.out.println("|  5. Zablokuj czytelnika        |");
						System.out.println("|  6. Odblokuj czytelnika        |");
						System.out.println("|                                |");
						System.out.println("|  0. Powrót                     |");
						System.out.println("+--------------------------------+");
						System.out.print("Wybierz opcję pomiędzy 0 a 6: ");
						wybor = input.nextLine();

						switch (wybor)
						{
							case "1" :
								String imie, nazwisko;
								System.out.print("Podaj imię czytelnika: ");
								imie = input.nextLine();
									
								System.out.printf("%nPodaj nazwisko: ");
								nazwisko = input.nextLine();
								try {
								b.dodajCzytelnika(b.czytelnicy.lastKey()+1, new Czytelnik(imie, nazwisko, b.czytelnicy.lastKey()+1));
								} catch (NoSuchElementException e) {
									b.dodajCzytelnika((long) 0, new Czytelnik(imie, nazwisko, (long) 0)); //pierwszy element
								}
								b.saveCzytelnicyDB(b);
								System.out.println("Dodano nowego czytelnika.");
								System.out.print("Wciśnij enter aby kontynuować ");
								input.nextLine();
								wybor = "";
								break;
							case "2" :
								Long id_czytelnika = null;
								try {
									System.out.printf("%nWprowadź ID czytelnika: ");
									id_czytelnika = Long.parseLong(input.nextLine());
								} catch (NumberFormatException e) {
									System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
									input.nextLine();
									wybor = "0";
								}
								while (!wybor.contentEquals("0")) {
									clear();
									System.out.println("+--------------------------------+");
									System.out.println("|-----------Biblioteka-----------|");
									System.out.println("+--------------------------------+");
									System.out.println("|                                |");
									System.out.println("|  1. Zmień imię                 |");
									System.out.println("|  2. Zmień nazwisko             |");
									System.out.println("|                                |");
									System.out.println("|  0. Powrót                     |");
									System.out.println("+--------------------------------+");
									System.out.print("Wybierz opcję pomiędzy 0 a 2: ");
									wybor = input.nextLine();
				
									switch (wybor)
									{
										case "1" :
											System.out.print("Podaj nowego imię: ");
											imie = input.nextLine();

											b.czytelnicy.get(id_czytelnika).setImie(imie);
											b.saveCzytelnicyDB(b);
											System.out.println("Zmieniono imię.");
											System.out.print("Wciśnij enter aby kontynuować ");
											input.nextLine();
											wybor = "";
											break;
										case "2" :
											System.out.print("Podaj nowe nazwisko: ");
											nazwisko = input.nextLine();
											
											b.czytelnicy.get(id_czytelnika).setNazwisko(nazwisko);
											b.saveCzytelnicyDB(b);
											System.out.println("Zmieniono nazwisko.");
											System.out.print("Wciśnij enter aby kontynuować ");
											input.nextLine();
											wybor = "";
											break;
										case "0" :
											break;
									}
									wybor = "";
									break;
								}
								wybor = "";
								break;
							case "3" :
								try {
									System.out.printf("%nWprowadź ID czytelnika: ");
									id_czytelnika = Long.parseLong(input.nextLine());
									if (b.getCzytelnicy().get(id_czytelnika) == null) throw new NumberFormatException();
									b.usunCzytelnika(id_czytelnika);
									b.saveCzytelnicyDB(b);
								} catch (NumberFormatException e) {
									System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
									input.nextLine();
								}
								System.out.println("Usunięto czytelnika.");
								System.out.print("Wciśnij enter aby kontynuować ");
								input.nextLine();
								wybor = "";
								break;
							case "4" :
                                do {
                                                        clear();
                                                        System.out.println("+--------------------------------+");
                                                        System.out.println("|-----------Biblioteka-----------|");
                                                        System.out.println("+--------------------------------+");
                                                        System.out.println("|                                |");
                                                        System.out.println("|  1. Sortuj po imieniu A-Z      |");
                                                        System.out.println("|  2. Sortuj po imieniu Z-A      |");
                                                        System.out.println("|  3. Sortuj po nazwisku A-Z     |");
                                                        System.out.println("|  4. Sortuj po nazwisku Z-A     |");
                                                        System.out.println("|  5. Sortuj po id rosnąco       |");
                                                        System.out.println("|  6. Sortuj po id malejąco      |");
                                                        System.out.println("|                                |");
                                                        System.out.println("|  0. Powrót                     |");
                                                        System.out.println("+--------------------------------+");
                                                        System.out.print("Wybierz opcję pomiędzy 0 a 6: ");
                                                        wybor = input.nextLine();
									switch (wybor)
									{
										case "1" :
																				b.wyswietlCzytelnikowImieAZ();
																				System.out.print("Wciśnij enter aby kontynuować ");
																				input.nextLine();
																				wybor = "";
																				break;
										case "2" :
																				b.wyswietlCzytelnikowImieZA();
																				System.out.print("Wciśnij enter aby kontynuować ");
																				input.nextLine();
																				wybor = "";
																				break;
										case "3" :
																				b.wyswietlCzytelnikowNazwiskoAZ();
																				System.out.print("Wciśnij enter aby kontynuować ");
																				input.nextLine();
																				wybor = "";
																				break;
										case "4" :
																				b.wyswietlCzytelnikowNazwiskoZA();
																				System.out.print("Wciśnij enter aby kontynuować ");
																				input.nextLine();
																				wybor = "";
																				break;
										case "5" :
																				b.wyswietlCzytelnikowIDLOW();
																				System.out.print("Wciśnij enter aby kontynuować ");
																				input.nextLine();
																				wybor = "";
																				break;
										case "6" :
																				b.wyswietlCzytelnikowIDHIGH();
																				System.out.print("Wciśnij enter aby kontynuować ");
																				input.nextLine();
																				wybor = "";
																				break;
										case "0" :
											break;
									}
								} while (!wybor.contentEquals("0"));
							wybor = "";
							break;
						case "5" :
							try {
								System.out.printf("%nWprowadź ID czytelnika: ");
								id_czytelnika = Long.parseLong(input.nextLine());

								b.czytelnicy.get(id_czytelnika).zablokuj();
								b.saveCzytelnicyDB(b);
							} catch (NumberFormatException e) {
								System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
								input.nextLine();
							}
							System.out.println("Zablokowano czytelnika.");
							System.out.print("Wciśnij enter aby kontynuować ");
							input.nextLine();
							wybor = "";
							break;
						case "6" :
							try {
								System.out.printf("%nWprowadź ID czytelnika: ");
								id_czytelnika = Long.parseLong(input.nextLine());

								b.czytelnicy.get(id_czytelnika).odblokuj();
								b.saveCzytelnicyDB(b);
							} catch (NumberFormatException e) {
								System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
								input.nextLine();
							}
							System.out.println("Odblokowano czytelnika.");
							System.out.print("Wciśnij enter aby kontynuować ");
							input.nextLine();
							wybor = "";
							break;
						case "0" :
							break;
						}
					} while (!wybor.contentEquals("0"));
					wybor = "";
					break;
				case "5" : 
					do {
						clear();
						System.out.println("+--------------------------------+");
						System.out.println("|-----------Biblioteka-----------|");
						System.out.println("+--------------------------------+");
						System.out.println("|                                |");
                        System.out.println("|  1. Wyświetl wyp. czytelnika   |");
                        System.out.println("|  2. Wyświetl wszystkie wyp.    |");
						System.out.println("|                                |");
						System.out.println("|  0. Powrót                     |");
						System.out.println("+--------------------------------+");
						System.out.print("Wybierz opcję pomiędzy 0 a 2: ");
						wybor = input.nextLine();

						switch (wybor)
						{
                                                    case "1" :
														Long id_czytelnika;
														try {
															clear();
															System.out.print("Wprowadź ID czytelnika: ");
															id_czytelnika = Long.parseLong(input.nextLine());
															if (b.getKsiazki().get(id_czytelnika) == null) throw new NumberFormatException();
															clear();
															b.wyswietlWypozyczeniaCzytelnika(id_czytelnika);
															System.out.println("Wciśnij enter aby kontynuować ");
															input.nextLine();
														} catch (NumberFormatException e) {
															clear();
															System.out.println("Niepoprawne ID, naciśnij enter aby powrócić."); 
															input.nextLine();
														}
                                                            wybor = "";
                                                            break;
                                                    case "2" :
                                                            b.wyswietlWypozyczenia();
                                                            System.out.print("Wciśnij enter aby kontynuować ");
                                                            input.nextLine();
                                                            wybor = "";
                                                            break;
													case "0" :
                                                            break;
						}
					} while (!wybor.contentEquals("0"));
					wybor = "";
					break;
				case "6" : 
					do {
					clear();
					System.out.println("+--------------------------------+");
					System.out.println("|----------Szukaj według---------|");
					System.out.println("+--------------------------------+");
                    System.out.println("|                                |");
					System.out.println("|  1. Imienia autora             |");
					System.out.println("|  2. Tytułu książki             |");
					System.out.println("|  3. Ilości egzemplarzy         |");
					System.out.println("|  4. Kodu ISBN                  |");
                    System.out.println("|                                |");
					System.out.println("|  0. Powrót                     |");
					System.out.println("+--------------------------------+");
					System.out.print("Wybierz opcję pomiędzy 0 a 4: ");

					wybor = input.nextLine();
                    String parametr;
                                        
					switch (wybor)
					{
						case "1" :
						
							System.out.print("Podaj imię autora: ");	
							parametr = input.nextLine();
						
							b.wyszukajKsiazki(parametr,wybor);
							System.out.print("Wciśnij enter aby kontynuować ");
							input.nextLine();
							break;
							
							case "2" :
							System.out.print("Podaj tytuł książki: ");
							parametr = input.nextLine();
						
							b.wyszukajKsiazki(parametr,wybor);
							System.out.print("Wciśnij enter aby kontynuować ");
							input.nextLine();
							break;

						case "3" :
							try {	

							System.out.print("Podaj ilość egzemplarzy: ");
							parametr = input.nextLine();
							Integer.parseInt(parametr);
						
							b.wyszukajKsiazki(parametr,wybor);
							System.out.print("Wciśnij enter aby kontynuować ");
							input.nextLine();

							} 
							catch (NumberFormatException e) {
								System.out.println("Niepoprawny parametr. ");
								System.out.println("Wciśnij enter aby kontynuować ");
								input.nextLine();
							}
							break;
					
						case "4" :
							System.out.print("Podaj kod ISBN: ");
							parametr = input.nextLine();
						
							b.wyszukajKsiazki(parametr,wybor);
							
							System.out.print("Wciśnij enter aby kontynuować ");
							input.nextLine();
							break;

						case "0" :
							break;


					}

				} while (!wybor.contentEquals("0"));
					wybor = "";
					break;

				case "0" : 
					break;

				default:
					System.out.println("Niepoprawny wybór.");
					break;
			}
			
		} while (!wybor.contentEquals("0"));
		input.close();
	}
}
