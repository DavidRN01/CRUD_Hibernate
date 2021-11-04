/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudhibernate;

import java.util.ArrayList;
import java.util.Scanner;
import models.Carta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author david
 */
public class Main {

    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    private static Session s = sf.openSession();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("X-- Aplicación para comandas --X");
        int seleccionMenu = 0;
        Scanner input = new Scanner(System.in);
        
        while(seleccionMenu != 7) {
            System.out.println("1- Listar carta disponible");
            
            seleccionMenu = input.nextInt();
            switch(seleccionMenu) {
                case 1: listarCarta();
                default: System.out.println("Elija una opción válida");
            }
        }
        
    }
    
    public static void listarCarta() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        Query q = s.createQuery("FROM Carta", Carta.class);
        ArrayList<Carta> resultado = (ArrayList<Carta>) q.list();
        
        resultado.forEach((c) -> System.out.println(c));
    }
    
    public static void anhadirPedido() {
        
    }
    
}
