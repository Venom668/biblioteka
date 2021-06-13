#ifndef MAPMODELKSIAZKA_H
#define MAPMODELKSIAZKA_H

#include <QAbstractTableModel>
#include <QMap>

#include "ksiazka.h"

class MapModelKsiazka : public QAbstractTableModel
{
    Q_OBJECT
public:

    enum MapRoles {
        KeyRole = Qt::UserRole + 1,
        ValueRole
    };

    explicit MapModelKsiazka(QObject *parent = nullptr);
    int rowCount(const QModelIndex& parent = QModelIndex()) const override;
    int columnCount(const QModelIndex& parent = QModelIndex()) const override;
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;
    QVariant headerData(int section, Qt::Orientation orientation, int role) const override;
    void setMap(QMap<quint32, Ksiazka*> *map);

private:
    QMap<quint32, Ksiazka*> *_map;
};

#endif // MAPMODELKSIAZKA_H
