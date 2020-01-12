package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() < o2.length()) {
                return -1;
            }
            return o1.compareTo(o2);
        }
    }
    public static  void main(String[] args) throws IOException {
	// write your code here
        int count = 0;
        List<String> lines = Files.readAllLines(Paths.get("input.txt"), StandardCharsets.UTF_8);
        for(String line: lines){
            System.out.println(line);
        }

        Set<String> uniqueSet = new HashSet<String>(lines);
        System.out.println("количество различных слов в файле="+ uniqueSet.size());

        List<String> sortList=new ArrayList<>(uniqueSet);
        Collections.sort(sortList, new MyComparator());
        System.out.println("Сортировка уникальных слов по длине, затем по алфавиту ");
        System.out.println(sortList);

        HashMap<String, Integer> wordToCount = new HashMap<>();
        for (String word : lines) {
            if (!wordToCount.containsKey(word)) {
                wordToCount.put(word, 0);
            }
            wordToCount.put(word, wordToCount.get(word) + 1);
        }
        System.out.println("Количество повторений слов: ");
        for (String word : wordToCount.keySet()) {
            System.out.println(word + " " + wordToCount.get(word));
        }
        System.out.println("Обратный порядок слов: ");
        Stack  sList = new Stack();
        for (String word : lines) {
            sList.push(word);
        }
        while (!sList.empty()) {
            System.out.println(sList.pop());
        }
        System.out.println("Итератор в обратном порядке");
        for (String s : new ReverseIterator<String>(lines)) {
            System.out.println(s);
        }
        System.out.println("Введите номер элемента:");
        Scanner scanner=new Scanner(System.in);
        int index=scanner.nextInt();
        if (index>=0 && index< lines.size()) {
            System.out.println(index);
            //System.out.println(lines(0));
            //for(int i=0; i<lines.size();i++){
            //    System.out.println(lines(i));
            //}
        }


    }
    public static class ReverseIterator<T> implements Iterator<T>, Iterable<T> {

        private final List<T> list;
        private int position;

        public ReverseIterator(List<T> list) {
            this.list = list;
            this.position = list.size() - 1;
        }

        @Override
        public Iterator<T> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            return position >= 0;
        }

        @Override
        public T next() {
            return list.get(position--);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
