//Класс для проверки вопроов возникших на лекции
package myAnswers;

import java.util.*;

public class Answers {
    public static void main(String[] args) {
        Car c1 = new Car("toyota", 1);
        Car c2 = new Car("mazda", 2);
        Car c3 = new Car("LADA", 3);
        Car c4 = new Car("MERS", 4);
        Car c5 = new Car("mazda", 5);

        LinkedList<Car> arrayList = new LinkedList<>();
        arrayList.add(c1);
        arrayList.add(c2);
        arrayList.add(c3);
        arrayList.add(c4);
        arrayList.add(c5);
        System.out.println(arrayList);
        Collections.sort(arrayList);

        System.out.println(c5.equals(c2));

        try {
            Car c6 = c5.clone();
            System.out.println(c6 == c5);
            System.out.println(c6.equals(c5));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Set<Car> set = new HashSet<>(arrayList);
        System.out.println(set.remove(c2));
        System.out.println(set);
    }
}

class Car implements Comparable<Car>,Cloneable {
    String name;
    int number;

    Car(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public int compareTo(Car o) {
        return number - o.number;
    }

    @Override
    protected Car clone() throws CloneNotSupportedException {
        return (Car) super.clone();
    }

    @Override
    public int hashCode() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Car)) {
            return false;
        } else {
            return name.equals(((Car) o).name);
        }
    }
    @Override
    public String toString(){
        return name + " " + number;
    }
}


