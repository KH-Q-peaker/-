package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Test2 {

    static int time = 0; 
    static int shortCut = 0;
    public static void main(String[] args) throws IOException {
        
        Deque<Integer> circle = new ArrayDeque<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String path = br.readLine();

        char start = path.charAt(0);
        String paths = path.substring(1);

        for(int time = 26; time >= 1; time--){
            circle.addFirst(time);
            circle.addLast(time);
        }// for : 1~26,26~1

        if(start > 'M'){
            log.info("reverse");
            while(circle.peekLast() <= (91 - start)){
                circle.addFirst(circle.pollLast());
            }// while
        }
        else{
            while(circle.peekFirst() < (start - 64)){
                circle.addLast(circle.pollFirst());
            }// while
        }
        shortCutPath(circle, start, paths.toCharArray());
    }// main


    public static void shortCutPart(Deque<Integer> circle, char from, char to) {

        int d = Math.abs(to - from);
        shortCut = (d < circle.size() /4) ? d : (circle.size()/2 - d); // 최단경로
        time = 0;

        if((to < from && d < circle.size() /4) || (to > from && d > circle.size() /4)){
            for(int i = 0; i < shortCut; i++){
                time += circle.peekLast();
                circle.addFirst(circle.pollLast());
            }// for
        }
        else {
            for(int i = 0; i < shortCut; i++){
                circle.addLast(circle.pollFirst());
                time += circle.peekFirst();
            }// for
        }// if-else
    }// shortCutPart

    public static void shortCutPath(Deque<Integer> circle, char start, char[] paths){

        int times = 0;
        int shortCuts = 0;

        for (char p : paths) {

            shortCutPart(circle, start, p);
            times += time;
            shortCuts += shortCut;
            start = p; // 시작점 변경
        } // for : 최단경로

        log.info("shortcuts = {}\ttime = {}", shortCuts, times);
    }// sumShorCut : 최단 경로들 합
}// end class

