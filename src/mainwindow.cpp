#include "mainwindow.h"
#include "./ui_mainwindow.h"

#include "dialogczytelnik.h"
#include "dialogksiazka.h"
#include "mapmodelCzytelnik.h"
#include "mapmodelKsiazka.h"

MainWindow::MainWindow(QMap<quint32, Czytelnik*> *mapCzytelnicy, QMap<quint32, Ksiazka*> *mapKsiazki,
                       QMap<quint32, Wypozyczenie*> *mapWypozyczenia, QWidget *parent)
    : QMainWindow(parent),
      ui(new Ui::MainWindow),
      mapCzytelnicy(mapCzytelnicy),
      mapKsiazki(mapKsiazki),
      mapWypozyczenia(mapWypozyczenia)
{
    ui->setupUi(this);

    modelCzytelnicy = new MapModelCzytelnik(ui->czytelnicyTable);
    modelCzytelnicy->setMap(mapCzytelnicy);
    QSortFilterProxyModel *proxyModelCzytelnicy = new QSortFilterProxyModel;
    searchModelCzytelnicy = new QSortFilterProxyModel;
    proxyModelCzytelnicy->setSourceModel(modelCzytelnicy);
    proxyModelCzytelnicy->setFilterKeyColumn(4);
    proxyModelCzytelnicy->setFilterFixedString("true");
    searchModelCzytelnicy->setSourceModel(proxyModelCzytelnicy);
    searchModelCzytelnicy->setFilterKeyColumn(-1);
    searchModelCzytelnicy->setFilterCaseSensitivity(Qt::CaseInsensitive);


    modelKsiazki = new MapModelKsiazka(ui->ksiazkiTable);
    modelKsiazki->setMap(mapKsiazki);
    QSortFilterProxyModel *proxyModelKsiazki = new QSortFilterProxyModel;
    searchModelKsiazki = new QSortFilterProxyModel;
    proxyModelKsiazki->setSourceModel(modelKsiazki);
    proxyModelKsiazki->setFilterKeyColumn(5);
    proxyModelKsiazki->setFilterFixedString("true");
    searchModelKsiazki->setSourceModel(proxyModelKsiazki);
    searchModelKsiazki->setFilterKeyColumn(-1);
    searchModelKsiazki->setFilterCaseSensitivity(Qt::CaseInsensitive);


    ui->czytelnicyTable->setModel(searchModelCzytelnicy);
    ui->ksiazkiTable->setModel(searchModelKsiazki);

}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::updateView()
{
    this->modelCzytelnicy->setMap(this->mapCzytelnicy);
    this->modelKsiazki->setMap(this->mapKsiazki);
    //this->modelWypozyczenia->setMap(this->mapWypozyczenia);
}

void MainWindow::on_czytelnicyTable_doubleClicked(const QModelIndex &index)
{
    DialogCzytelnik *dialog = new DialogCzytelnik(index, this->mapCzytelnicy,this->mapWypozyczenia, this->mapKsiazki, this);
    dialog->show();
    connect(dialog, &DialogCzytelnik::updateView, this, &MainWindow::updateView);
}

void MainWindow::on_actionDodaj_czytelnika_triggered()
{
    DialogCzytelnik *dialog = new DialogCzytelnik(this->mapCzytelnicy, this);
    dialog->show();
    connect(dialog, &DialogCzytelnik::updateView, this, &MainWindow::updateView);
}

void MainWindow::on_ksiazkiTable_doubleClicked(const QModelIndex &index)
{
    DialogKsiazka *dialog = new DialogKsiazka(index, this->mapKsiazki, this->mapWypozyczenia, this->mapCzytelnicy, this);
    dialog->show();
    connect(dialog, &DialogKsiazka::updateView, this, &MainWindow::updateView);
}

void MainWindow::on_actionDodaj_ksiazke_triggered()
{
    DialogKsiazka *dialog = new DialogKsiazka(this->mapKsiazki, this);
    dialog->show();
    connect(dialog, &DialogKsiazka::updateView, this, &MainWindow::updateView);
}

void MainWindow::on_searchbar_textChanged(const QString &arg1)
{
    searchModelCzytelnicy->setFilterFixedString(arg1);
    searchModelKsiazki->setFilterFixedString(arg1);
}

