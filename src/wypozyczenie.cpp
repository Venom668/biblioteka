#include "wypozyczenie.h"

Wypozyczenie::Wypozyczenie(quint32 id, quint32 id_ksiazka, quint32 id_czytelnik, QDate data_wypozyczenia) :
    id(id),
    id_ksiazka(id_ksiazka),
    id_czytelnik(id_czytelnik),
    data_wypozyczenia(data_wypozyczenia),
    data_oddania(QDate())
{
}

Wypozyczenie::Wypozyczenie(quint32 id, quint32 id_ksiazka, quint32 id_czytelnik, QDate data_wypozyczenia, QDate data_oddania, bool oddane) :
    oddane(oddane),
    id(id),
    id_ksiazka(id_ksiazka),
    id_czytelnik(id_czytelnik),
    data_wypozyczenia(data_wypozyczenia),
    data_oddania(data_oddania)
{
}

quint32 Wypozyczenie::getID() {
    return id;
}

quint32 Wypozyczenie::getKsiazkaID() {
    return id_ksiazka;
}

quint32 Wypozyczenie::getCzytelnikID() {
    return id_czytelnik;
}

QDate Wypozyczenie::getDataWypozyczenia() {
    return data_wypozyczenia;
}

QDate Wypozyczenie::getDataOddania() {
    return data_oddania;
}

int Wypozyczenie::zwroc() {
    if (!data_oddania.isValid()) {
        data_oddania = QDate::currentDate();
        oddane = true;
        return 0;
    } else {
        return 1;
    }

}
