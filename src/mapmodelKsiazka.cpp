#include "mapmodelKsiazka.h"

MapModelKsiazka::MapModelKsiazka(QObject *parent) :
    QAbstractTableModel(parent),
    _map(nullptr)
{
}

void MapModelKsiazka::setMap(QMap<quint32, Ksiazka*> *map)
{
    beginResetModel();
    _map = map;
    endResetModel();
}

int MapModelKsiazka::rowCount(const QModelIndex &parent) const
{
    if (_map && !_map->isEmpty())
        return _map->lastKey();
    return 0;
}

int MapModelKsiazka::columnCount(const QModelIndex &parent) const
{
    return 6;
}

QVariant MapModelKsiazka::data(const QModelIndex &index, int role) const
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
        return _map->value(index.row()+1)->getTytul();
    if (index.column() == 2)
        return _map->value(index.row()+1)->getAutor();
    if (index.column() == 3)
        return _map->value(index.row()+1)->getISBN();
    if (index.column() == 4)
        return _map->value(index.row()+1)->dostepna;
    if (index.column() == 5)
        return _map->value(index.row()+1)->aktywna;
    return QVariant();
}

QVariant MapModelKsiazka::headerData(int section, Qt::Orientation orientation, int role) const
{
    if (role == Qt::DisplayRole && orientation == Qt::Horizontal) {
        switch (section) {
        case 0:
            return QString("ID");
        case 1:
            return QString("Tytuł");
        case 2:
            return QString("Autor");
        case 3:
            return QString("ISBN");
        case 4:
            return QString("Dostępna");
        case 5:
            return QString("Aktywna");
        }
    }
    return QVariant();
}
