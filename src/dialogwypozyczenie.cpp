#include "dialogwypozyczenie.h"
#include "ui_dialogwypozyczenie.h"

DialogWypozyczenie::DialogWypozyczenie(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::DialogWypozyczenie)
{
    ui->setupUi(this);
}

DialogWypozyczenie::DialogWypozyczenie(const quint32 &index, QMap<quint32, Wypozyczenie*> *mapWypozyczenia, QMap<quint32, Ksiazka*> *mapKsiazki, QWidget *parent) :
    QDialog(parent),
    ui(new Ui::DialogWypozyczenie),
    id_czyt(index),
    mapWypozyczenia(mapWypozyczenia),
    mapKsiazki(mapKsiazki)
{
    ui->setupUi(this);
    setAttribute(Qt::WA_DeleteOnClose);

    modelKsiazki = new MapModelKsiazka(ui->ksiazkiTable);
    modelKsiazki->setMap(mapKsiazki);
    QSortFilterProxyModel *proxyModelKsiazki = new QSortFilterProxyModel;
    proxyModelKsiazki->setSourceModel(modelKsiazki);
    proxyModelKsiazki->setFilterKeyColumn(4);
    proxyModelKsiazki->setFilterFixedString("true");

    ui->ksiazkiTable->setModel(proxyModelKsiazki);
}

DialogWypozyczenie::~DialogWypozyczenie()
{
    delete ui;
}

void DialogWypozyczenie::on_ksiazkiTable_doubleClicked(const QModelIndex &index)
{
    quint32 id_ksiazka = index.model()->data(index.model()->index(index.row(), 0)).toUInt();
    quint32 id_czytelnik = this->id_czyt;
    QDate data_wypozyczenia = QDate::currentDate();
    this->mapWypozyczenia->insert(mapWypozyczenia->lastKey()+1, new Wypozyczenie(mapWypozyczenia->lastKey()+1, id_ksiazka, id_czytelnik, data_wypozyczenia));
    this->mapKsiazki->value(id_ksiazka)->wypozycz();
    emit updateView();
}

