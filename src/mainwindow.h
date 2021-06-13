#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QSortFilterProxyModel>

#include "czytelnik.h"
#include "ksiazka.h"
#include "wypozyczenie.h"
#include "mapmodelCzytelnik.h"
#include "mapmodelKsiazka.h"
#include "mapmodelWypozyczenie.h"

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QMap<quint32, Czytelnik*> *mapCzytelnicy, QMap<quint32, Ksiazka*> *mapKsiazki,
               QMap<quint32, Wypozyczenie*> *mapWypozyczenia, QWidget *parent = nullptr);
    ~MainWindow();
private slots:
    void on_czytelnicyTable_doubleClicked(const QModelIndex &index);
    void on_actionDodaj_czytelnika_triggered();
    void updateView();

    void on_actionDodaj_ksiazke_triggered();

    void on_ksiazkiTable_doubleClicked(const QModelIndex &index);

    void on_searchbar_textChanged(const QString &arg1);

private:
    Ui::MainWindow *ui;
    QMap<quint32, Czytelnik*> *mapCzytelnicy = nullptr;
    QMap<quint32, Ksiazka*> *mapKsiazki = nullptr;
    QMap<quint32, Wypozyczenie*> *mapWypozyczenia = nullptr;
    MapModelCzytelnik *modelCzytelnicy;
    MapModelKsiazka *modelKsiazki;
    MapModelWypozyczenie *modelWypozyczenia;
    QSortFilterProxyModel *searchModelKsiazki;
    QSortFilterProxyModel *searchModelCzytelnicy;
};
#endif // MAINWINDOW_H
