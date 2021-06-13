#ifndef DIALOGKSIAZKA_H
#define DIALOGKSIAZKA_H

#include <QDialog>
#include <QSortFilterProxyModel>
#include <QMessageBox>
#include "ksiazka.h"
#include "mapmodelWypozyczenie.h"

namespace Ui {
class DialogKsiazka;
}

class DialogKsiazka : public QDialog
{
    Q_OBJECT

public:
    explicit DialogKsiazka(QWidget *parent = nullptr);
    DialogKsiazka(const QModelIndex &index, QMap<quint32, Ksiazka*> *mapKsiazki,
                  QMap<quint32, Wypozyczenie*> *mapWypozyczenia, QMap<quint32, Czytelnik*> *mapCzytelnicy, QWidget *parent = nullptr);
    DialogKsiazka(QMap<quint32, Ksiazka*> *mapKsiazki, QWidget *parent = nullptr, bool nowa = true);
    ~DialogKsiazka();

private slots:
    void on_pushButton_usun_clicked();

    void on_buttonBox_accepted();

    void on_buttonBox_rejected();

signals:
    void updateView();

private:
    Ui::DialogKsiazka *ui;
    quint32 id;
    QMap<quint32, Ksiazka*> *map = nullptr;
    bool nowa = false;
    MapModelWypozyczenie *modelWypozyczenia;
};

#endif // DIALOGKSIAZKA_H
