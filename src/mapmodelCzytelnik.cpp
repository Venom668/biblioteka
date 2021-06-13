#include "mapmodelCzytelnik.h"

MapModelCzytelnik::MapModelCzytelnik(QObject *parent) :
    QAbstractTableModel(parent),
    _map(nullptr)
{
}

void MapModelCzytelnik::setMap(QMap<quint32, Czytelnik*> *map)
{
    beginResetModel();
    _map = map;
    endResetModel();
}

int MapModelCzytelnik::rowCount(const QModelIndex &parent) const
{
    if (_map && !_map->isEmpty())
        return _map->lastKey();
    return 0;
}

int MapModelCzytelnik::columnCount(const QModelIndex &parent) const
{
    return 5;
}

QVariant MapModelCzytelnik::data(const QModelIndex &index, int role) const
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
        return _map->value(index.row()+1)->getImie();
    if (index.column() == 2)
        return _map->value(index.row()+1)->getNazwisko();
    if (index.column() == 3)
        return _map->value(index.row()+1)->getPesel();
    if (index.column() == 4)
        return _map->value(index.row()+1)->aktywny;
    return QVariant();
}

QVariant MapModelCzytelnik::headerData(int section, Qt::Orientation orientation, int role) const
{
    if (role == Qt::DisplayRole && orientation == Qt::Horizontal) {
        switch (section) {
        case 0:
            return QString("ID");
        case 1:
            return QString("Imię");
        case 2:
            return QString("Nazwisko");
        case 3:
            return QString("PESEL");
        case 4:
            return QString("Aktywny");
        }
    }
    return QVariant();
}
