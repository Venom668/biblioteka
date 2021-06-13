#ifndef MAPMODELCZYTELNIK_H
#define MAPMODELCZYTELNIK_H

#include <QAbstractTableModel>
#include <QMap>

#include "czytelnik.h"

class MapModelCzytelnik : public QAbstractTableModel
{
    Q_OBJECT
public:

    enum MapRoles {
        KeyRole = Qt::UserRole + 1,
        ValueRole
    };

    explicit MapModelCzytelnik(QObject *parent = nullptr);
    int rowCount(const QModelIndex& parent = QModelIndex()) const override;
    int columnCount(const QModelIndex& parent = QModelIndex()) const override;
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;
    QVariant headerData(int section, Qt::Orientation orientation, int role) const override;
    void setMap(QMap<quint32, Czytelnik*> *map);

private:
    QMap<quint32, Czytelnik*> *_map;
};

#endif // MAPMODELCZYTELNIK_H
