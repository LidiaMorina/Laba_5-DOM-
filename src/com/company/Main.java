package com.company;
import java.util.ArrayList;

/*Программа предназначенная для разбора XML-документа и инициализации коллекции
объектов информацией из XML-файла с сортировки объектов c использованием DOM-парсера
 на выходе: консольный вывод  XML-документа и создание заполненного html файла по xls*/

public class Main {

    public static void main(String[] args) {
        //вывод в консоль
        Tariff domTariff = new Tariff();
        domTariff.tariffBuilder("tariff.xml");
        ArrayList<Tariffs> ttt = domTariff.getTariff();
        System.out.println(ttt);

        //преобразоавние в HTML документ
        Transform transform = new Transform();
        transform.transform();
    }
}
