package com.houseBuilder;

import com.houseBuilder.GUI.Frame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Frame();

    }
    public static void conVers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Постройка дома");
        System.out.println("Введите количество этажей(max 3): ");
        int floors = sc.nextInt();
        System.out.println("Выберите материал крыши: ");
        int roof = sc.nextInt();
        System.out.println("Выберите материал стен: ");
        int walls = sc.nextInt();
        System.out.println("Выбериите материал фундамента: ");
        int foundation = sc.nextInt();
        System.out.println("Наличие подвала(да/нет)");
        int presence = sc.nextInt();
    }
}
