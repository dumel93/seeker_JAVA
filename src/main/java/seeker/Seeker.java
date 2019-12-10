package seeker;

import org.jsoup.Connection;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Seeker {
    public static void main(String[] args)  {
        Connection connect = Jsoup.connect("https://www.onet.pl/");

        String[] blockWords=new String[2];
        blockWords[0]="coraz";
        blockWords[1]="razem";
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");

                    try {
                        PrintWriter out = new PrintWriter("popular_words.txt");
                        for (Element elem : links) {
                            String[] sentence = elem.text().split(" ");

                            for (String word : sentence) {
                                if(word.length()>3)
                                out.append(word + "\n");

                            }

                        }out.close();

                    } catch (IOException ex) {
                        System.out.println("Błąd zapisu do pliku.");
                    }



            }
         catch (IOException e) {
            e.printStackTrace();

        }


        try {
            File file = new File("popular_words.txt");
            PrintWriter file2 = new PrintWriter("filtered_popular_words.txt");
            ArrayList<String> list = new ArrayList();
            ArrayList<String> toRemove = new ArrayList();

            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {

                list.add(scan.nextLine());

            }
            System.out.println(list);
            System.out.println(list.size());


            for (String forbid : blockWords) {
                for (String word : list) {
                    if (word.equals(forbid)) {
                        toRemove.add(word);
                    }
                }
            }

            list.removeAll(toRemove);

            for (String word : list) {
                file2.append(word + "\n");

            }
            System.out.println(list);
            System.out.println(list.size());



        }
        catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }



    }
    }


