#include "dialogczytelnik.h"
#include "ui_dialogczytelnik.h"
#include "dialogwypozyczenie.h"
#include "ui_dialogwypozyczenie.h"

DialogCzytelnik::DialogCzytelnik(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::DialogCzytelnik)
{
    ui->setupUi(this);
}

DialogCzytelnik::DialogCzytelnik(const QModelIndex &index, QMap<quint32, Czytelnik*> *mapCzytelnicy,
                                 QMap<quint32, Wypozyczenie*> *mapWypozyczenia, QMap<quint32, Ksiazka*> *mapKsiazki, QWidget *parent) :
    QDialog(parent),
    ui(new Ui::DialogCzytelnik),
    id(index.model()->data(index.model()->index(index.row(), 0)).toUInt()),
    map(mapCzytelnicy),
    mapWypozyczenia(mapWypozyczenia),
    mapKsiazki(mapKsiazki)
{
    ui->setupUi(this);
    setAttribute(Qt::WA_DeleteOnClose);
    ui->lineEdit_imie->setText(map->value(id)->getImie());
    ui->lineEdit_nazwisko->setText(map->value(id)->getNazwisko());
    ui->lineEdit_pesel->setText(map->value(id)->getPesel());

    ui->wypozyczeniaTable->setSelectionBehavior(QAbstractItemView::SelectRows);
    modelWypozyczenia = new MapModelWypozyczenie(ui->wypozyczeniaTable);
    modelWypozyczenia->setMap(mapWypozyczenia, mapKsiazki, mapCzytelnicy);
    QSortFilterProxyModel *proxyModelWypozyczenia = new QSortFilterProxyModel;
    proxyModelWypozyczenia->setSourceModel(modelWypozyczenia);
    proxyModelWypozyczenia->setFilterKeyColumn(6);
    proxyModelWypozyczenia->setFilterRegExp(QString("^%1$").arg(id));

    ui->wypozyczeniaTable->setModel(proxyModelWypozyczenia);
}

DialogCzytelnik::DialogCzytelnik(QMap<quint32, Czytelnik*> *mapCzytelnicy, QWidget *parent, bool nowy) :
    QDialog(parent),
    ui(new Ui::DialogCzytelnik),
    map(mapCzytelnicy),
    nowy(true)
{
    ui->setupUi(this);
    ui->pushButton_wypozycz->hide();
    ui->pushButton_zwroc->hide();
    ui->pushButton_usun->hide();
    ui->wypozyczeniaTable->hide();
    ui->label_4->hide();
    setAttribute(Qt::WA_DeleteOnClose);
}

DialogCzytelnik::~DialogCzytelnik()
{
    delete ui;
}

void DialogCzytelnik::on_pushButton_usun_clicked()
{
    for (quint32 i=0; i <= mapWypozyczenia->lastKey(); i++) {
        if (mapWypozyczenia->value(i)->getCzytelnikID() == id) {
            QMessageBox::warning(this, "Błąd", "Nie można usunąć czytelnika, który ma wypożyczone książki.", QMessageBox::Ok);
            return;
        }
    }
    this->map->value(id)->usun();
    emit updateView();
}

void DialogCzytelnik::on_buttonBox_accepted()
{
    QString imie = ui->lineEdit_imie->text();
    QString nazwisko = ui->lineEdit_nazwisko->text();
    QString pesel = ui->lineEdit_pesel->text();
    if (nowy) {
        if (imie.isEmpty()) {
            QMessageBox::warning(this, "Błąd", "Imię nie może być puste.", QMessageBox::Ok);
        }
        else if (nazwisko.isEmpty()) {
            QMessageBox::warning(this, "Błąd", "Nazwisko nie może być puste.", QMessageBox::Ok);
        }
        else if (pesel.isEmpty()) {
            QMessageBox::warning(this, "Błąd", "PESEL nie może być pusty.", QMessageBox::Ok);
        } else {
            this->map->insert(map->lastKey()+1, new Czytelnik(map->lastKey()+1, imie, nazwisko, pesel));
        }
    } else {
        if (this->ui->lineEdit_imie->isModified()) {
            if (imie.isEmpty()) {
                QMessageBox::warning(this, "Błąd", "Imię nie może być puste.", QMessageBox::Ok);
            } else {
                this->map->value(id)->setImie(imie);
            }
        }
        if (this->ui->lineEdit_nazwisko->isModified()) {
            if (nazwisko.isEmpty()) {
                QMessageBox::warning(this, "Błąd", "Nazwisko nie może być puste.", QMessageBox::Ok);
            } else {
                this->map->value(id)->setNazwisko(nazwisko);
            }
        }
        if (this->ui->lineEdit_pesel->isModified()) {
            if (pesel.isEmpty()) {
                QMessageBox::warning(this, "Błąd", "PESEL nie może być pusty.", QMessageBox::Ok);
            } else {
                this->map->value(id)->setPesel(pesel);
            }
        }
    }
    emit updateView();
}

void DialogCzytelnik::on_buttonBox_rejected()
{
    emit updateView();
}

void DialogCzytelnik::on_pushButton_wypozycz_clicked()
{
    DialogWypozyczenie *dialog = new DialogWypozyczenie(id, this->mapWypozyczenia, this->mapKsiazki, this);
    dialog->show();
    connect(dialog, &DialogWypozyczenie::updateView, this, &DialogCzytelnik::updateView);
    emit updateView();
    this->modelWypozyczenia->setMap(this->mapWypozyczenia);
}

void DialogCzytelnik::on_pushButton_zwroc_clicked()
{
    QItemSelectionModel *index = ui->wypozyczeniaTable->selectionModel();
    if (index->selectedRows().size() == 1) {
        quint32 id = index->model()->data(index->model()->index(index->selectedRows().first().row(), 0)).toUInt();
        if ((this->mapWypozyczenia->value(id)->zwroc() == 1) & (this->mapKsiazki->value(this->mapWypozyczenia->value(id)->getKsiazkaID())->oddaj() == 1)) {
            QMessageBox::warning(this, "Błąd", "Ta książka została już zwrócona.", QMessageBox::Ok);
        }
    }
    emit updateView();
    this->modelWypozyczenia->setMap(this->mapWypozyczenia);
}


