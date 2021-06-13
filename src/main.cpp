#include "mainwindow.h"
#include "czytelnik.h"
#include "ksiazka.h"
#include "wypozyczenie.h"

#include <QApplication>
#include <QLocale>
#include <QTranslator>
#include <QDataStream>
#include <QFile>
#include <QDebug>

void writeCzytelnicy(QMap<quint32, Czytelnik*> *mapCzytelnicy) {
    QFile file("czytelnicy.bin");
    if (!file.open(QIODevice::WriteOnly)) {
        qDebug() << "Nie udało się zapisać bazy.";
        return;
    }
    QDataStream out(&file);
    QMap<quint32, Czytelnik*>::iterator i;
    for (i = mapCzytelnicy->begin(); i != mapCzytelnicy->end(); ++i) {
    out << i.value()->getID();
    out << i.value()->getImie();
    out << i.value()->getNazwisko();
    out << i.value()->getPesel();
    out << i.value()->aktywny;
    }
    file.close();
}

QMap<quint32, Czytelnik*>* readCzytelnicy() {
    QFile file("czytelnicy.bin");
    if (!file.open(QIODevice::ReadOnly)) {
        qDebug() << "Nie udało się otworzyć bazy.";
        QMap<quint32, Czytelnik*> *mapCzytelnicy = new QMap<quint32, Czytelnik*>;
        mapCzytelnicy->insert(0, new Czytelnik(0, "", "", ""));
        return mapCzytelnicy;
    }
    QDataStream in(&file);
    QMap<quint32, Czytelnik*> *mapCzytelnicy = new QMap<quint32, Czytelnik*>;
    mapCzytelnicy->insert(0, new Czytelnik(0, "", "", ""));
    while (in.atEnd() == false) {
    quint32 id;
    QString imie;
    QString nazwisko;
    QString pesel;
    bool aktywny;
    in >> id >> imie >> nazwisko >> pesel >> aktywny;
    Czytelnik *czytelnik = new Czytelnik(id, imie, nazwisko, pesel, aktywny);
    mapCzytelnicy->insert(czytelnik->getID(), czytelnik);
    }
    file.close();
    return mapCzytelnicy;
}

void writeKsiazki(QMap<quint32, Ksiazka*> *mapKsiazki) {
    QFile file("ksiazki.bin");
    if (!file.open(QIODevice::WriteOnly)) {
        qDebug() << "Nie udało się zapisać bazy.";
        return;
    }
    QDataStream out(&file);
    QMap<quint32, Ksiazka*>::iterator i;
    for (i = mapKsiazki->begin(); i != mapKsiazki->end(); ++i) {
    out << i.value()->getID();
    out << i.value()->getAutor();
    out << i.value()->getTytul();
    out << i.value()->getISBN();
    out << i.value()->aktywna;
    out << i.value()->dostepna;
    }
}

QMap<quint32, Ksiazka*>* readKsiazki() {
    QFile file("ksiazki.bin");
    if (!file.open(QIODevice::ReadOnly)) {
        qDebug() << "Nie udało się otworzyć bazy.";
        QMap<quint32, Ksiazka*> *mapKsiazki = new QMap<quint32, Ksiazka*>;
        mapKsiazki->insert(0, new Ksiazka(0, "", "", ""));
        return mapKsiazki;
    }
    QDataStream in(&file);
    QMap<quint32, Ksiazka*> *mapKsiazki = new QMap<quint32, Ksiazka*>;
    mapKsiazki->insert(0, new Ksiazka(0, "", "", ""));
    while (in.atEnd() == false) {
    quint32 id;
    QString autor;
    QString tytul;
    QString isbn;
    bool aktywna;
    bool dostepna;
    in >> id >> autor >> tytul >> isbn >> aktywna >> dostepna;
    Ksiazka *ksiazka = new Ksiazka(id, autor, tytul, isbn, aktywna, dostepna);
    mapKsiazki->insert(ksiazka->getID(), ksiazka);
    }
    return mapKsiazki;
}

void writeWypozyczenia(QMap<quint32, Wypozyczenie*> *mapWypozyczenia) {
    QFile file("wypozyczenia.bin");
    if (!file.open(QIODevice::WriteOnly)) {
        qDebug() << "Nie udało się zapisać bazy.";
        return;
    }
    QDataStream out(&file);
    QMap<quint32, Wypozyczenie*>::iterator i;
    for (i = mapWypozyczenia->begin(); i != mapWypozyczenia->end(); ++i) {
    out << i.value()->getID();
    out << i.value()->getKsiazkaID();
    out << i.value()->getCzytelnikID();
    out << i.value()->getDataWypozyczenia();
    out << i.value()->getDataOddania();
    out << i.value()->oddane;
    }
}

QMap<quint32, Wypozyczenie*>* readWypozyczenia() {
    QFile file("wypozyczenia.bin");
    if (!file.open(QIODevice::ReadOnly)) {
        qDebug() << "Nie udało się otworzyć bazy.";
        QMap<quint32, Wypozyczenie*> *mapWypozyczenia = new QMap<quint32, Wypozyczenie*>;
        mapWypozyczenia->insert(0, new Wypozyczenie(0, 0, 0, QDate()));
        return mapWypozyczenia;
    }
    QDataStream in(&file);
    QMap<quint32, Wypozyczenie*> *mapWypozyczenia = new QMap<quint32, Wypozyczenie*>;
    mapWypozyczenia->insert(0, new Wypozyczenie(0, 0, 0, QDate()));
    while (in.atEnd() == false) {
    quint32 id;
    quint32 id_ksiazka;
    quint32 id_czytelnik;
    QDate data_wypozyczenia;
    QDate data_oddania;
    bool oddane;
    in >> id >> id_ksiazka >> id_czytelnik >> data_wypozyczenia >> data_oddania >> oddane;
    Wypozyczenie *wypozyczenie = new Wypozyczenie(id, id_ksiazka, id_czytelnik, data_wypozyczenia, data_oddania, oddane);
    mapWypozyczenia->insert(wypozyczenie->getID(), wypozyczenie);
    }
    return mapWypozyczenia;
}

int main(int argc, char *argv[]) {
    QApplication a(argc, argv);

    QTranslator translator;
    const QStringList uiLanguages = QLocale::system().uiLanguages();
    for (const QString &locale : uiLanguages) {
        const QString baseName = "Biblioteka_" + QLocale(locale).name();
        if (translator.load(":/i18n/" + baseName)) {
            a.installTranslator(&translator);
            break;
        }
    }

    QMap<quint32, Czytelnik*> *mapCzytelnicy = readCzytelnicy();
    //QMap<quint32, Czytelnik*> *mapCzytelnicy = new QMap<quint32, Czytelnik*>;
    //mapCzytelnicy->insert(1, new Czytelnik(1, "Władysław", "Ostrowski", "61081436636"));
    //mapCzytelnicy->insert(2, new Czytelnik(2, "Katarzyna", "Borkowska", "77021255366"));
    //mapCzytelnicy->insert(3, new Czytelnik(3, "Michalina", "Majewska", "91091198701"));
    //mapCzytelnicy->insert(4, new Czytelnik(4, "Henryk", "Zawadzki", "44040511953"));
    //mapCzytelnicy->insert(5, new Czytelnik(5, "Jakub", "Nowicki", "67092285287"));
    //mapCzytelnicy->insert(6, new Czytelnik(6, "Stanisław", "Kucharski", "45091599529"));
    //mapCzytelnicy->insert(7, new Czytelnik(7, "Michał", "Zając", "42041521395"));


    QMap<quint32, Ksiazka*> *mapKsiazki = readKsiazki();
    //QMap<quint32, Ksiazka*> *mapKsiazki = new QMap<quint32, Ksiazka*>;
    //mapKsiazki->insert(1, new Ksiazka(1, "Andrzej Sapkowski", "Ostatnie życzenie", "9788375780635"));
    //mapKsiazki->insert(2, new Ksiazka(2, "Andrzej Sapkowski", "Krew elfów", "9788375780659"));
    //mapKsiazki->insert(3, new Ksiazka(3, "Andrzej Sapkowski", "Czas pogardy", "9788375780666"));
    //mapKsiazki->insert(4, new Ksiazka(4, "Andrzej Sapkowski", "Chrzest ognia", "9788375780673"));
    //mapKsiazki->insert(5, new Ksiazka(5, "Andrzej Sapkowski", "Wieża Jaskółki", "9788375780680"));
    //mapKsiazki->insert(6, new Ksiazka(6, "Andrzej Sapkowski", "Pani Jeziora", "9788375780697"));
    //mapKsiazki->insert(7, new Ksiazka(7, "Andrzej Sapkowski", "Coś się kończy, coś się zaczyna", "9788370541446"));
    //mapKsiazki->insert(8, new Ksiazka(8, "Arkadij Strugacki", "Piknik na skraju drogi", "9788376487212"));
    //mapKsiazki->insert(9, new Ksiazka(9, "Frank Herbert", "Diuna", "9788373017238"));
    //mapKsiazki->insert(10, new Ksiazka(10, "George Orwell", "Rok 1984", "9788328708419"));
    //mapKsiazki->insert(11, new Ksiazka(11, "J.R.R. Tolkien", "Drużyna Pierścienia", "9788324144242"));
    //mapKsiazki->insert(12, new Ksiazka(12, "J.R.R. Tolkien", "Dwie wieże", "9788377856819"));
    //mapKsiazki->insert(13, new Ksiazka(13, "J.R.R. Tolkien", "Powrót Króla", "9788377856826"));
    //mapKsiazki->insert(14, new Ksiazka(14, "Juliusz Verne", "20 000 mil podmorskiej żeglugi", "9788381542760"));
    //mapKsiazki->insert(15, new Ksiazka(15, "Orson Scott Card", "Gra Endera", "9788376482514"));

    //QMap<quint32, Wypozyczenie*> *mapWypozyczenia = new QMap<quint32, Wypozyczenie*>;
    QMap<quint32, Wypozyczenie*> *mapWypozyczenia = readWypozyczenia();

    MainWindow w(mapCzytelnicy, mapKsiazki, mapWypozyczenia);
    w.show();
    //return a.exec();
    int ret = a.exec();

    writeCzytelnicy(mapCzytelnicy);
    writeKsiazki(mapKsiazki);
    writeWypozyczenia(mapWypozyczenia);

    delete mapKsiazki;
    delete mapCzytelnicy;
    delete mapWypozyczenia;
    return ret;
}
