package com.job.nutriplayapp;

import java.util.ArrayList;
import java.util.List;

public class ModuloRepository {

    private static List<Modulo> modulos;

   /* static {
        modulos = new ArrayList<>();
        modulos.add(new Modulo(10,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(20,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(30,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(40,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(50,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(60,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(70,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(80,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(90,"Modulo 1", "Dieta balanceada","example"));
        modulos.add(new Modulo(100,"Modulo 1", "Dieta balanceada","example"));
    }*/

    public static List<Modulo>getModulos(){
        return modulos;
    }
}
