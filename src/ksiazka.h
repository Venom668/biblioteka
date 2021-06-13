#ifndef KSIAZKA_H
#define KSIAZKA_H

#include <QString>

class Ksiazka
{
public:
    Ksiazka(quint32 id, QString autor, QString tytul, QString isbn);
    Ksiazka(quint32 id, QString autor, QString tytul, QString isbn, bool aktywna, bool dostepna);
    quint32 getID();
    QString getAutor();
    QString getTytul();
    QString getISBN();
    int setAutor(QString autor);
    int setTytul(QString tytul);
    int setISBN(QString isbn);
    int usun();
    int wypozycz();
    int oddaj();
    bool aktywna = true;
    bool dostepna = true;
private:
    quint32 id;
    QString autor;
    QString tytul;
    QString isbn;
};

#endif // KSIAZKA_H
