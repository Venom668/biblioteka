#ifndef WYPOZYCZENIE_H
#define WYPOZYCZENIE_H

#include "ksiazka.h"
#include "czytelnik.h"
#include <QDate>

class Wypozyczenie
{
public:
    Wypozyczenie(quint32 id, quint32 id_ksiazka, quint32 id_czytelnik,
                 QDate data_wypozyczenia);
    Wypozyczenie(quint32 id, quint32 id_ksiazka, quint32 id_czytelnik,
                 QDate data_wypozyczenia, QDate data_oddanie, bool oddane);
    quint32 getID();
    quint32 getKsiazkaID();
    quint32 getCzytelnikID();
    QDate getDataWypozyczenia();
    QDate getDataOddania();
    int zwroc();
    bool oddane = false;
private:
    quint32 id;
    quint32 id_ksiazka;
    quint32 id_czytelnik;
    QDate data_wypozyczenia;
    QDate data_oddania;
};

#endif // WYPOZYCZENIE_H
