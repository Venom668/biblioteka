#include "dialogksiazka.h"
#include "ui_dialogksiazka.h"

DialogKsiazka::DialogKsiazka(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::DialogKsiazka)
{
    ui->setupUi(this);
}

DialogKsiazka::DialogKsiazka(const QModelIndex &index, QMap<quint32, Ksiazka*> *mapKsiazki,
                             QMap<quint32, Wypozyczenie*> *mapWypozyczenia, QMap<quint32, Czytelnik*> *mapCzytelnicy, QWidget *parent) :
    QDialog(parent),
    ui(new Ui::DialogKsiazka),
    id(index.model()->data(index.model()->index(index.row(), 0)).toUInt()),
    map(mapKsiazki)
{
    ui->setupUi(this);
    setAttribute(Qt::WA_DeleteOnClose);
    ui->lineEdit_tytul->setText(map->value(id)->getTytul());
    ui->lineEdit_autor->setText(map->value(id)->getAutor());
    ui->lineEdit_isbn->setText(map->value(id)->getISBN());

    modelWypozyczenia = new MapModelWypozyczenie(ui->wypozyczeniaTable);
    modelWypozyczenia->setMap(mapWypozyczenia, mapKsiazki, mapCzytelnicy);
    QSortFilterProxyModel *proxyModelWypozyczenia = new QSortFilterProxyModel;
    proxyModelWypozyczenia->setSourceModel(modelWypozyczenia);
    proxyModelWypozyczenia->setFilterKeyColumn(1);
    proxyModelWypozyczenia->setFilterRegExp(QString("^%1$").arg(id));

    ui->wypozyczeniaTable->setModel(proxyModelWypozyczenia);
}

DialogKsiazka::DialogKsiazka(QMap<quint32, Ksiazka*> *mapKsiazki, QWidget *parent, bool nowa) :
    QDialog(parent),
    ui(new Ui::DialogKsiazka),
    map(mapKsiazki),
    nowa(true)
{
    ui->setupUi(this);
    ui->pushButton_usun->hide();
    ui->wypozyczeniaTable->hide();
    ui->label_4->hide();
    setAttribute(Qt::WA_DeleteOnClose);
}

DialogKsiazka::~DialogKsiazka()
{
    delete ui;
}

void DialogKsiazka::on_pushButton_usun_clicked()
{
    if (map->value(id)->dostepna) {
        this->map->value(id)->usun();
    } else {
        QMessageBox::warning(this, "Błąd", "Nie można usunąć wypożyczonej książki.", QMessageBox::Ok);
    }
    emit updateView();
}

void DialogKsiazka::on_buttonBox_accepted()
{
    QString tytul = ui->lineEdit_tytul->text();
    QString autor = ui->lineEdit_autor->text();
    QString isbn = ui->lineEdit_isbn->text();
    if (nowa) {
        this->map->insert(map->lastKey()+1, new Ksiazka(map->lastKey()+1, autor, tytul, isbn));
    } else {
        if (this->ui->lineEdit_tytul->isModified()) {
            QString tekst = ui->lineEdit_tytul->text();
            if (tekst.isEmpty()) {
                QMessageBox::warning(this, "Błąd", "Tytuł nie może być pusty.", QMessageBox::Ok);
            } else {
                this->map->value(id)->setTytul(tekst);
            }
        }
        if (this->ui->lineEdit_autor->isModified()) {
            QString tekst = ui->lineEdit_autor->text();
            if (tekst.isEmpty()) {
                QMessageBox::warning(this, "Błąd", "Autor nie może być pusty.", QMessageBox::Ok);
            } else {
                this->map->value(id)->setAutor(tekst);
            }
        }
        if (this->ui->lineEdit_isbn->isModified()) {
            QString tekst = ui->lineEdit_isbn->text();
            if (tekst.isEmpty()) {
                QMessageBox::warning(this, "Błąd", "ISBN nie może być pusty.", QMessageBox::Ok);
            } else {
                this->map->value(id)->setISBN(tekst);
            }
        }
    }
    emit updateView();
}


void DialogKsiazka::on_buttonBox_rejected()
{
    emit updateView();
}

