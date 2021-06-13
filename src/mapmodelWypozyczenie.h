#ifndef MAPMODELWYPOZYCZENIE_H
#define MAPMODELWYPOZYCZENIE_H

#include <QAbstractTableModel>
#include <QMap>

#include "wypozyczenie.h"

class MapModelWypozyczenie : public QAbstractTableModel
{
    Q_OBJECT
public:

    enum MapRoles {
        KeyRole = Qt::UserRole + 1,
        ValueRole
    };

    explicit MapModelWypozyczenie(QObject *parent = nullptr);
    int rowCount(const QModelIndex& parent = QModelIndex()) const override;
    int columnCount(const QModelIndex& parent = QModelIndex()) const override;
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;
    QVariant headerData(int section, Qt::Orientation orientation, int role) const override;
    void setMap(QMap<quint32, Wypozyczenie*> *map, QMap<quint32, Ksiazka*> *mapKsiazki, QMap<quint32, Czytelnik*> *mapCzytelnicy);
    void setMap(QMap<quint32, Wypozyczenie*> *map);

private:
    QMap<quint32, Wypozyczenie*> *_map;
    QMap<quint32, Ksiazka*> *_mapKsiazki;
    QMap<quint32, Czytelnik*> *_mapCzytelnicy;
};

#endif // MAPMODELWYPOZYCZENIE_H
