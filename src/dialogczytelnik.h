#ifndef DIALOGCZYTELNIK_H
#define DIALOGCZYTELNIK_H

#include <QDialog>
#include <QSortFilterProxyModel>
#include <QMessageBox>
#include "czytelnik.h"
#include "mapmodelWypozyczenie.h"

namespace Ui {
class DialogCzytelnik;
}

class DialogCzytelnik : public QDialog
{
    Q_OBJECT

public:
    explicit DialogCzytelnik(QWidget *parent = nullptr);
    DialogCzytelnik(const QModelIndex &index, QMap<quint32, Czytelnik*> *mapCzytelnicy,
                    QMap<quint32, Wypozyczenie*> *mapWypozyczenia, QMap<quint32, Ksiazka*> *mapKsiazki, QWidget *parent = nullptr);
    DialogCzytelnik(QMap<quint32, Czytelnik*> *mapCzytelnicy, QWidget *parent = nullptr, bool nowy = true);
    ~DialogCzytelnik();

private slots:
    void on_pushButton_usun_clicked();

    void on_buttonBox_accepted();

    void on_pushButton_wypozycz_clicked();

    void on_pushButton_zwroc_clicked();
    void on_buttonBox_rejected();

signals:
    void updateView();

private:
    Ui::DialogCzytelnik *ui;
    quint32 id;
    QMap<quint32, Czytelnik*> *map = nullptr;
    QMap<quint32, Wypozyczenie*> *mapWypozyczenia = nullptr;
    QMap<quint32, Ksiazka*> *mapKsiazki;
    bool nowy = false;
    MapModelWypozyczenie *modelWypozyczenia;
    quint32 zazn;
};

#endif // DIALOGCZYTELNIK_H
