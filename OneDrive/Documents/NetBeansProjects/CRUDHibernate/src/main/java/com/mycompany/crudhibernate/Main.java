/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudhibernate;

import java.util.ArrayList;
import java.util.Scanner;
import models.Carta;
import models.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
            System.out.println("2- Crear un pedido");
            
            seleccionMenu = input.nextInt();
            switch(seleccionMenu) {
                case 1: listarCarta();
                break;
                case 2: crearPedido();
                break;
                default: System.out.println("Elija una opción válida");
            }
        }
        
    }
    
    public static void listarCarta() {
        
        Query q = s.createQuery("FROM Carta", Carta.class);
        ArrayList<Carta> resultado = (ArrayList<Carta>) q.list();
        
        resultado.forEach((c) -> System.out.println(c));
        
    }
    
    public static void crearPedido() {
        
        Pedido pedido = new Pedido();
        Scanner input = new Scanner(System.in);
        Scanner id_input = new Scanner(System.in);
        
        listarCarta();
        System.out.print("Introduce el id del producto que desea: ");
        Long id = id_input.nextLong();
        System.out.print("Introduce un nombre para el pedido: ");
        String nombre = input.nextLine();
        
        Carta producto = s.load(Carta.class, id);
        java.util.Date ahora = new java.util.Date();
        java.sql.Date fecha = new java.sql.Date(ahora.getTime());
        
        pedido.setId(0L);
        pedido.setProducto_id(id);
        pedido.setNombre(nombre);
        pedido.setEstado("SIN ENTREGAR");
        pedido.setPrecio(producto.getPrecio());
        pedido.setFecha(fecha);
        
        Transaction t = s.beginTransaction();
        s.save(pedido);
        t.commit();
        
    }
    
}
