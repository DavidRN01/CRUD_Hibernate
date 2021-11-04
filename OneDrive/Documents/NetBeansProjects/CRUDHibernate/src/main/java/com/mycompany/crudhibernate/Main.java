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
        boolean menuActivo = true;
        Scanner input = new Scanner(System.in);
        
        while(menuActivo) {
            System.out.println("1- Listar carta disponible");
            System.out.println("2- Listar todos los pedidos");
            System.out.println("3- Listar los pedidos de hoy");
            System.out.println("4- Crear un pedido");
            System.out.println("5- Borrar un pedido");
            System.out.println("6- Marcar pedido como recogido");
            System.out.println("7- Salir");
            
            seleccionMenu = input.nextInt();
            switch(seleccionMenu) {
                case 1: listarCarta();
                break;
                case 2: listarPedidos();
                break;
                case 3: listarPedidosPendientes();
                break;
                case 4: crearPedido();
                break;
                case 5: borrarPedido();
                break;
                case 6: marcarRecogido();
                break;
                case 7: menuActivo = false;
                break;
                default: System.out.println("Elija una opción válida");
            }
        }
        
    }
    
    public static void listarCarta() {
        
        //Creo la query y lo guardo en un arraylist
        Query q = s.createQuery("FROM Carta", Carta.class);
        ArrayList<Carta> resultado = (ArrayList<Carta>) q.list();
        
        //Lo enseño por pantalla
        resultado.forEach((c) -> System.out.println(c));
        
    }
    
    public static void listarPedidos() {
        
        //Creo la query y lo guardo en un arraylist
        Query q = s.createQuery("FROM Pedido", Pedido.class);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();
        
        //Lo enseño por pantalla
        resultado.forEach((p) -> System.out.println(p));
        
    }
    
    public static void listarPedidosPendientes() {
        
        //Creo la fecha actual
        java.util.Date ahora = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(ahora.getTime());
        
        //Creo la query con las variables y lo guardo en un arraylist
        Query q = s.createQuery("FROM Pedido p WHERE p.estado = 'SIN ENTREGAR' AND p.fecha = :fecha", Pedido.class);
        q.setParameter("fecha", fechaActual);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();
        
        //Lo enseño por pantalla
        resultado.forEach((p) -> System.out.println(p));
        
    }
    
    public static void crearPedido() {
        
        //Inicializo el objeto pedido y los scanners
        Pedido pedido = new Pedido();
        Scanner input = new Scanner(System.in);
        Scanner id_input = new Scanner(System.in);
        
        //Listo la carta y pido el Id del producto que se quiera pedir
        //y un nombre para el pedido
        listarCarta();
        Long id = id_input.nextLong();
        String nombre = input.nextLine();
        
        //Saco el producto de la carta segun la id escogida
        Carta producto = s.load(Carta.class, id);
        //Creo la fecha
        java.util.Date ahora = new java.util.Date();
        java.sql.Date fecha = new java.sql.Date(ahora.getTime());
        
        //Añado todos los datos a la clase
        pedido.setId(0L);
        pedido.setProducto_id(id);
        pedido.setNombre(nombre);
        pedido.setEstado("SIN ENTREGAR");
        pedido.setPrecio(producto.getPrecio());
        pedido.setFecha(fecha);
        
        //Hago la transacción y el commit
        Transaction t = s.beginTransaction();
        s.save(pedido);
        t.commit();
        
    }
    
    public static void borrarPedido() {

        //Creo el scanner
        Scanner id_input = new Scanner(System.in);
        
        //Listo los pedidos y selecciono un id
        listarPedidos();
        Long id = id_input.nextLong();
        
        //Hago la transacción y borro el pedido con el id seleccionado
        Transaction t = s.beginTransaction();
        Pedido pedido = s.load(Pedido.class, id);
        s.remove(pedido);
        t.commit();
        
    }

    private static void marcarRecogido() {
        
        //Creo el scanner
        Scanner id_input = new Scanner(System.in);
        
        //Listo los pedidos y selecciono un id
        listarPedidos();
        Long id = id_input.nextLong();
        
        //Hago la transacción y marco como recogido el pedido con el id seleccionado
        Transaction t = s.beginTransaction();
        Pedido pedido = s.load(Pedido.class, id);
        pedido.setEstado("RECOGIDO");
        s.update(pedido);
        t.commit();
        
    }
    
}
