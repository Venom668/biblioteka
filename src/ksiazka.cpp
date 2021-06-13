#include "ksiazka.h"

Ksiazka::Ksiazka(quint32 id, QString autor, QString tytul, QString isbn) :
    id(id),
    autor(autor),
    tytul(tytul),
    isbn(isbn)
{
}

Ksiazka::Ksiazka(quint32 id, QString autor, QString tytul, QString isbn, bool aktywna, bool dostepna) :
    aktywna(aktywna),
    dostepna(dostepna),
    id(id),
    autor(autor),
    tytul(tytul),
    isbn(isbn)
{
}


quint32 Ksiazka::getID() {
    return id;
}

QString Ksiazka::getAutor() {
    return autor;
}

QString Ksiazka::getTytul() {
    return tytul;
}

QString Ksiazka::getISBN() {
    return isbn;
}

int Ksiazka::setAutor(QString autor) {
    if (!autor.isEmpty()) {
        this->autor = autor;
        return 0;
    } else {
        return 1;
    }
}

int Ksiazka::setTytul(QString tytul) {
    if (!tytul.isEmpty()) {
        this->tytul = tytul;
        return 0;
    } else {
        return 1;
    }
}

int Ksiazka::setISBN(QString isbn) {
    if (!isbn.isEmpty()) {
        this->isbn = isbn;
        return 0;
    } else {
        return 1;
    }
}

int Ksiazka::usun() {
    if (aktywna) {
        aktywna = false;
        dostepna = false;
        return 0;
    } else {
        return 1;
    }
}

int Ksiazka::wypozycz() {
    if (dostepna) {
        dostepna = false;
        return 0;
    } else {
        return 1;
    }
}

int Ksiazka::oddaj() {
    if (!dostepna) {
        dostepna = true;
        return 0;
    } else {
        return 1;
    }
}
