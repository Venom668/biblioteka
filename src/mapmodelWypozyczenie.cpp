#include "mapmodelWypozyczenie.h"

MapModelWypozyczenie::MapModelWypozyczenie(QObject *parent) :
    QAbstractTableModel(parent),
    _map(nullptr)
{
}

void MapModelWypozyczenie::setMap(QMap<quint32, Wypozyczenie*> *map, QMap<quint32, Ksiazka*> *mapKsiazki, QMap<quint32, Czytelnik*> *mapCzytelnicy)
{
    beginResetModel();
    _map = map;
    _mapKsiazki = mapKsiazki;
    _mapCzytelnicy = mapCzytelnicy;
    endResetModel();
}

void MapModelWypozyczenie::setMap(QMap<quint32, Wypozyczenie*> *map)
{
    beginResetModel();
    _map = map;
    endResetModel();
}


int MapModelWypozyczenie::rowCount(const QModelIndex &parent) const
{
    if (_map && !_map->isEmpty())
        return _map->lastKey();
    return 0;
}

int MapModelWypozyczenie::columnCount(const QModelIndex &parent) const
{
    return 9;
}

QVariant MapModelWypozyczenie::data(const QModelIndex &index, int role) const
{
    if (!_map)
        return QVariant();
    if (index.row() < 0 ||
        index.row() >= _map->count() ||
        role != Qt::DisplayRole) {
        return QVariant();
    }
    if (index.column() == 0)
        return _map->value(index.row()+1)->getID();
    if (index.column() == 1)
        return _mapKsiazki->value(_map->value(index.row()+1)->getKsiazkaID())->getID();
    if (index.column() == 2)
        return _mapKsiazki->value(_map->value(index.row()+1)->getKsiazkaID())->getTytul();
    if (index.column() == 3)
        return _mapKsiazki->value(_map->value(index.row()+1)->getKsiazkaID())->getAutor();
    if (index.column() == 4)
        return _map->value(index.row()+1)->getDataWypozyczenia();
    if (index.column() == 5)
        return _map->value(index.row()+1)->getDataOddania();
    if (index.column() == 6)
        return _mapCzytelnicy->value(_map->value(index.row()+1)->getCzytelnikID())->getID();
    if (index.column() == 7)
        return _mapCzytelnicy->value(_map->value(index.row()+1)->getCzytelnikID())->getImie();
    if (index.column() == 8)
        return _mapCzytelnicy->value(_map->value(index.row()+1)->getCzytelnikID())->getNazwisko();
    return QVariant();
}

QVariant MapModelWypozyczenie::headerData(int section, Qt::Orientation orientation, int role) const
{
    if (role == Qt::DisplayRole && orientation == Qt::Horizontal) {
        switch (section) {
        case 0:
            return QString("ID wypożyczenia");
        case 1:
            return QString("ID Książki");
        case 2:
            return QString("Tytuł");
        case 3:
            return QString("Autor");
        case 4:
            return QString("Data wypożyczenia");
        case 5:
            return QString("Data oddania");
        case 6:
            return QString("ID Czytelnika");
        case 7:
            return QString("Imię");
        case 8:
            return QString("Nazwisko");
        }
    }
    return QVariant();
}
