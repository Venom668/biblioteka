#include "czytelnik.h"

Czytelnik::Czytelnik(quint32 id, QString imie, QString nazwisko, QString pesel) :
    id(id),
    imie(imie),
    nazwisko(nazwisko),
    pesel(pesel)
{
}

Czytelnik::Czytelnik(quint32 id, QString imie, QString nazwisko, QString pesel, bool aktywny) :
    aktywny(aktywny),
    id(id),
    imie(imie),
    nazwisko(nazwisko),
    pesel(pesel)
{
}

quint32 Czytelnik::getID() {
    return id;
}

QString Czytelnik::getImie() {
    return imie;
}

QString Czytelnik::getNazwisko() {
    return nazwisko;
}

QString Czytelnik::getPesel() {
    return pesel;
}

int Czytelnik::setImie(QString imie) {
    if (!imie.isEmpty()) {
        this->imie = imie;
        return 0;
    } else {
        return 1;
    }
}

int Czytelnik::setNazwisko(QString nazwisko) {
    if (!nazwisko.isEmpty()) {
        this->nazwisko = nazwisko;
        return 0;
    } else {
        return 1;
    }
}

int Czytelnik::setPesel(QString pesel) {
    if (!pesel.isEmpty()) {
        this->pesel = pesel;
        return 0;
    } else {
        return 1;
    }
}

int Czytelnik::usun() {
    if (aktywny) {
        aktywny = false;
        return 0;
    } else {
        return 1;
    }
}

