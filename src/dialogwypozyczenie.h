#ifndef DIALOGWYPOZYCZENIE_H
#define DIALOGWYPOZYCZENIE_H

#include <QDialog>
#include <QSortFilterProxyModel>
#include "wypozyczenie.h"
#include "mapmodelKsiazka.h"

namespace Ui {
class DialogWypozyczenie;
}

class DialogWypozyczenie : public QDialog
{
    Q_OBJECT

public:
    explicit DialogWypozyczenie(QWidget *parent = nullptr);
    DialogWypozyczenie(const quint32 &index, QMap<quint32, Wypozyczenie*> *mapWypozyczenia,
                       QMap<quint32, Ksiazka*> *mapKsiazki, QWidget *parent = nullptr);
    ~DialogWypozyczenie();

private slots:
    void on_ksiazkiTable_doubleClicked(const QModelIndex &index);

signals:
    void updateView();

private:
    Ui::DialogWypozyczenie *ui;
    quint32 id_czyt;
    QMap<quint32, Wypozyczenie*> *mapWypozyczenia = nullptr;
    QMap<quint32, Ksiazka*> *mapKsiazki = nullptr;
    MapModelKsiazka *modelKsiazki;
};

#endif // DIALOGWYPOZYCZENIE_H
