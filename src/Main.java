import java.util.*;

public class Main {

    public static void main(String[] args) {
        //входной текст
        String inputText = "В траве сидел кузнечик, в траве сидел кузнечик, \n" +
                "Совсем как огуречик зелененький он был. \n" +
                "Представьте себе, представьте себе \n" +
                "Совсем как огуречик! \n" +
                "Представьте себе, представьте себе \n" +
                "Зелененький он был!";

        //удаляем лишние символы:
        String inputText1 = inputText.replaceAll("[,.!]"," ").replaceAll("\\s+"," ");

        //Создаем массив из слов:
        String[] array = inputText1.split(" ");




        //пишем массив в коллекцию
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(array));

        //Задание 1 подсчитать количество различных слов в файле:
        Set<String> set = new LinkedHashSet<>(arrayList);
        System.out.println("Задание 1. Количество слов: " + arrayList.size()  + ", Количество уникальных слов: " + set.size());

        //Задание 2 Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор ро длние слова, потом по тексту)
        //создаем объект компаратора...
        Comparator<String> comparator1 = new Comparator<String>() {
            public int compare(String o1, String o2) {
                //сначала сверяем по длине
                if (o1.length() == o2.length()){
                    //если равны то сверяем по строке
                    return o1.compareTo(o2);
                }
                //если не равны то только по длине
                else
                {
                    return o1.length() - o2.length();
                }
            }
        };
        Collections.sort(arrayList,comparator1);
        System.out.println("Задание 2. Слова по возрастанию: " + new LinkedHashSet<>(arrayList));

        //Задание 3 Подсчитать сколько каждое слово встречается раз:
        Map<String,Integer> hashMap = new HashMap<>();
        //пишем слова в мапу
        for(String s : arrayList){
            //Если есть то плюсуем если нет то добавляем новый элемент
            if (hashMap.containsKey(s)) {
                hashMap.put(s, hashMap.get(s) + 1);
            } else {
                hashMap.put(s, 1);
            }
        }
        System.out.println("Задание 3. Количество повторений слов: " + hashMap);

        //Задание 4 Вывести все строки файла в обратном порядке:
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(inputText.split("\n")));
        System.out.println("Задание 4. Строки из входящего файла в обратном порядке: ");
        for (int i = arrayList2.size()-1; i >= 0; i--){
            System.out.println(arrayList2.get(i));
        }

        //Задание 5. Реализуйте свой итератор для обхода списка в обратном порядке:
        System.out.println("Задание 5. Обратный итератор: ");
        ReverseIterator ri = new ReverseIterator(arrayList);
        ListIterator<String> li = arrayList.listIterator();
        while (ri.hasNext()){
            String s = ri.next().toString();
            if (s.equals("В")){
                ri.remove();
            }
            else
            {
                System.out.println(s);
            }
        }
        System.out.println();

        //Задание 6. Вывести строки в порядке заданном пользователем
        System.out.println("Задание 6. Вывести строки в порядке заданном пользователем");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер строки: ");
        if (scanner.hasNextInt()){
            int num = scanner.nextInt();
            try{
                System.out.println(arrayList2.get(num-1));
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("нет такой строки");
            }
        }
        else {
            System.out.println("Некорректный номер, необходимо воодить в целочисленном формате");
        }
    }
    //метод использования итератора в обратном порядке, в параметры метода передается список и
    // , при необходимости, элементы для удаления
    //Реализовано через класс
/*    private static void reverseIterator(List list, String... stringsToRemove){
        Collections.reverse(list);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            String s = iterator.next().toString();
            if (Arrays.asList(stringsToRemove).contains(s)){
                iterator.remove();
                System.out.print(s + "(удален) ");
            }
            else{
                System.out.print(s + " ");
            }
        }
        Collections.reverse(list);
    }*/
}

//Реалзиация компаратора в отдельном классе
class StringComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        //сначала сверяем по длине
        if (o1.toString().length() == o2.toString().length()){
            //если равны то сверяем по строке
            return o1.toString().compareTo(o2.toString());
        }
        //если не равны то только по длине
        else
        {
            return o1.toString().length() - o2.toString().length();
        }
    }
}

class ReverseIterator implements Iterator{

    private List list;
    private int counter;

    ReverseIterator(List list){
        this.list = list;
        counter = list.size();
    }

    @Override
    public boolean hasNext() {
        return counter > 0 && counter <= list.size();
    }

    @Override
    public Object next() {
      counter--;
      return list.get(counter);
    }

    @Override
    public void remove() {

        list.remove(counter);
    }

}