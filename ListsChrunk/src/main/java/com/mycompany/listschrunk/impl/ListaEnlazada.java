/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listschrunk.impl;

import com.mycompany.listschrunk.modelos.Nodo;
import com.mycompany.listschrunk.interfaces.Lista;

import java.util.Collection;
import java.util.NoSuchElementException;
/**
 *
 * @author daniel
 */
public class ListaEnlazada<T> implements Lista<T> {
    
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamanio;

    public ListaEnlazada() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    @Override
    public boolean agregar(T elemento) {
        return agregarAlFinal(elemento);
    }

    private boolean agregarAlFinal(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
        tamanio++;
        return true;
    }

    @Override
    public void agregarInicio(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }
        tamanio++;
    }

    @Override
    public void agregarFinal(T elemento) {
        agregarAlFinal(elemento);
    }

    @Override
    public void insertarEn(int indice, T elemento) {
        if (indice < 0 || indice > tamanio) throw new IndexOutOfBoundsException();

        if (indice == 0) {
            agregarInicio(elemento);
            return;
        }

        if (indice == tamanio) {
            agregarFinal(elemento);
            return;
        }

        Nodo<T> nuevo = new Nodo<>(elemento);
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.siguiente;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        tamanio++;
    }

    @Override
    public boolean agregarTodos(Collection<? extends T> c) {
        boolean cambiado = false;
        for (T elemento : c) {
            agregarFinal(elemento);
            cambiado = true;
        }
        return cambiado;
    }

    @Override
    public boolean agregarTodosEn(int indice, Collection<? extends T> c) {
        if (indice < 0 || indice > tamanio) throw new IndexOutOfBoundsException();

        boolean cambiado = false;
        for (T elemento : c) {
            insertarEn(indice++, elemento);
            cambiado = true;
        }
        return cambiado;
    }

    @Override
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) throw new IndexOutOfBoundsException();

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    @Override
    public T obtenerPrimero() {
        if (cabeza == null) throw new NoSuchElementException();
        return cabeza.dato;
    }

    @Override
    public T obtenerUltimo() {
        if (cola == null) throw new NoSuchElementException();
        return cola.dato;
    }

    @Override
    public boolean contiene(T elemento) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(elemento)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public void limpiar() {
        cabeza = cola = null;
        tamanio = 0;
    }
    
    @Override
    public void listar() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo<T> actual = cabeza;
        System.out.print("Elementos: ");
        while (actual != null) {
            System.out.print(actual.dato + " → ");
            actual = actual.siguiente;
        }
        System.out.println("NULL");
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void ordenar() {
        if (cabeza == null || cabeza.siguiente == null) return;

        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                Comparable<T> datoActual = (Comparable<T>) actual.dato;
                if (datoActual.compareTo(actual.siguiente.dato) > 0) {
                    T temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    cambiado = true;
                }
                actual = actual.siguiente;
            }
        } while (cambiado);
    }
    
    @Override
    public boolean eliminar(T elemento) {
        if (cabeza == null) return false;

        if (cabeza.dato.equals(elemento)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            if (cabeza == null) cola = null;
            return true;
        }

        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;

        while (actual != null && !actual.dato.equals(elemento)) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual == null) return false;

        anterior.siguiente = actual.siguiente;
        if (actual == cola) cola = anterior;

        tamanio--;
        return true;
    }
}
