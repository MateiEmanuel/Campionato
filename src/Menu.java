import java.util.List;

import static java.lang.System.out;

public class Menu {
    public static void menu() {
        out.println("1) partite per giornata");
        out.println("2) partite per squadra");
        out.println("3) classifica del campionato");
        out.println("0) fine programma");
    }

    public static void stampaPartite(List<Partita> lista) {
        if (lista != null && !lista.isEmpty()) {
            for (Partita p : lista)
                out.println(p);
            out.println();
        } else {
            out.println("lista vuota");
            out.println();
        }
    }

    public static void stampaSquadre(List<Squadra> lista) {
        if (lista != null && !lista.isEmpty()) {
            for (Squadra s : lista)
                out.println(s.toStringClassifica());
            out.println();
        } else {
            out.println("lista vuota");
            out.println();
        }
    }

}
