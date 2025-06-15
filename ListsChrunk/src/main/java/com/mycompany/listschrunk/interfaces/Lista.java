/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.listschrunk.interfaces;
import java.util.Collection;
/**
 *
 * @author daniel
 */
public interface Lista<T> {
    boolean agregar(T elemento);              
    void agregarInicio(T elemento);                
    void agregarFinal(T elemento);               
    void insertarEn(int indice, T elemento);       
    boolean agregarTodos(Collection<? extends T> c);               
    boolean agregarTodosEn(int indice, Collection<? extends T> c);                             

    T obtener(int indice);                        
    T obtenerPrimero();                            
    T obtenerUltimo();                             
    boolean contiene(T elemento);                  
    void limpiar();   
    
    boolean eliminar(T elemento);                 
    void ordenar();                                 
    void listar();    
}