import static java.lang.System.out;

import java.util.Scanner;

public class Test {
    private static Scanner input = new Scanner(System.in);

    private static int getOpzione() {
	int op;
	while (true) {
	    try {
		out.print("cosa vuoi fare? ");
		op = input.nextInt();
		if ((op < 1 || op > 3) && op != 0)
		    throw new IllegalArgumentException();
		return op;
	    } catch (IllegalArgumentException e) {
		out.println("numero invalido");
	    }
	}
    }

    public static void main(String[] args) {
	Campionato campionato = new Campionato("Premier League", "2020", "league2020.csv");

	int op = -1;
	while (op != 0) {
	    Menu.menu();
	    op = getOpzione();
	    switch (op) {
	    case 1:
		out.print("inserisci la giornata: ");
		campionato.partitePerGiornata(input.nextInt());
		break;
	    case 2:
		out.println("inserisci il nome della squadra: ");
		input.nextLine(); // per scartare '/n' creato dal println
		campionato.partitePerSquadra(input.nextLine());
		break;
	    case 3:
		out.println("la classifica delle squadre");
		campionato.stampaClassifica();
		break;
	    }
	}
    }
}
