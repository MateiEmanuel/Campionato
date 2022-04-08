public class Partita {
	private int giornata;
	private String data;
	private String squadra_casa;
	private int[] punteggio;
	private String squadra_ospite;

	public Partita(int giornata, String data, String squadra_casa, String punteggio, String squadra_ospite) {
		this.giornata = giornata;
		this.data = data;
		this.squadra_casa = squadra_casa;
		this.punteggio = makePunteggio(punteggio, "-");
		this.squadra_ospite = squadra_ospite;
	}

	// costruttore per la lettura del file
	public Partita(String line, String del) {
		String[] dati = line.split(del);

		giornata = Integer.parseInt(dati[0]);
		data = dati[1];
		squadra_casa = dati[2];
		punteggio = makePunteggio(dati[3], "-");
		squadra_ospite = dati[4];
	}

	// punteggio[0]: punti per la squadra casa
	// punteggio[1]: punti per la squadra ospite
	private int[] makePunteggio(String str, String del) {
		// "4-1" -> "4" "1"
		String[] temp = str.split(del);
		return new int[] { Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) };
	}

	public Partita(Partita partita) {
		this.giornata = partita.giornata;
		this.data = partita.data;
		this.squadra_casa = partita.squadra_casa;
		this.punteggio = partita.punteggio;
		this.squadra_ospite = partita.squadra_ospite;
	}

	public int getGiornata() {
		return giornata;
	}

	public String getData() {
		return data;
	}

	public String getSquadra_casa() {
		return squadra_casa;
	}

	public int[] getPunteggio() {
		return punteggio;
	}

	public String getSquadra_ospite() {
		return squadra_ospite;
	}

	@Override
	public String toString() {
		return giornata + "," + data + "," + squadra_casa + "," + punteggio[0] + "-" + punteggio[1] + ","
				+ squadra_ospite;
	}

}