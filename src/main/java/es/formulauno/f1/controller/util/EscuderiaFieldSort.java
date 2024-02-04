package es.formulauno.f1.controller.util;

public enum EscuderiaFieldSort {
    NAME("name"),
    PRESUPUESTO("presupuesto"),
    POSCAMPEONATOCONSTRUCTORES("posCampeonatoConstructores");

    private String field;
    EscuderiaFieldSort(String field){
        this.field = field;
    }

    public String getField(){
        return field;
    }
}
