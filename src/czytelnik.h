#ifndef CZYTELNIK_H
#define CZYTELNIK_H

#include <QString>

class Czytelnik
{
public:
    Czytelnik(quint32 id, QString imie, QString nazwisko, QString pesel);
    Czytelnik(quint32 id, QString imie, QString nazwisko, QString pesel, bool aktywny);
    quint32 getID();
    QString getImie();
    QString getNazwisko();
    QString getPesel();
    int setImie(QString imie);
    int setNazwisko(QString nazwisko);
    int setPesel(QString pesel);
    int usun();
    bool aktywny = true;
private:
    quint32 id;
    QString imie;
    QString nazwisko;
    QString pesel;
};



#endif // CZYTELNIK_H
