import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Campionato {
	private final String PATH;
	// DATI DEL CAMPIONATO: serie e stagione
	private final String serie;
	private final String stagione;

	// BufferedReader br per la lettura dei dati da file
	private BufferedReader reader;

	// HASHMAP partite_giornate(K,V)
	// K = numero giornata V = Elenco partite giocata in quella giornata
	private Map<Integer, List<Partita>> partite_giornate;

	// TREEMAP partite_squadre(K,V)
	// K = nome squadra V = Elenco partite giocate da quella squadra
	private Map<String, List<Partita>> partite_squadre;

	// ARRAYLIST classifica ( del campionato ordinata per punteggio decrescente )
	private List<Squadra> classifica;

	public Campionato() {
		this.PATH = "league2020.csv";
		this.serie = "Premier League";
		this.stagione = "2020";
		this.partite_giornate = new HashMap<>();
		this.partite_squadre = new HashMap<>();
		this.classifica = new ArrayList<>();
		carica_dati();
		aggiornaClassifica();
	}

	public Campionato(String serie, String stagione) {
		this.PATH = "league2020.csv";
		this.serie = serie;
		this.stagione = stagione;
		this.partite_giornate = new HashMap<>();
		this.partite_squadre = new HashMap<>();
		this.classifica = new ArrayList<>();
		carica_dati();
		aggiornaClassifica();
	}

	public Campionato(String serie, String stagione, String filename) {
		this.PATH = filename;
		this.serie = serie;
		this.stagione = stagione;
		this.partite_giornate = new HashMap<>();
		this.partite_squadre = new HashMap<>();
		this.classifica = new ArrayList<>();
		carica_dati();
		aggiornaClassifica();
	}

	// inizializza partite_giornate e partite_squadre
	private void carica_dati() {
		try {
			reader = new BufferedReader(new FileReader(PATH));
			reader.readLine(); // salta la prima riga
			String line;
			// 1,Fri Aug 9 2019,Liverpool FC,4-1,Norwich City FC
			while ((line = reader.readLine()) != null) {
				Partita p = new Partita(line, ",");
				if (!partite_giornate.containsKey(p.getGiornata()))
					// 1 -> lista di partite
					partite_giornate.put(p.getGiornata(), new LinkedList<>());
				if (!partite_squadre.containsKey(p.getSquadra_casa()))
					// se nome della squadra_casa non c'e nel map -> si crea una lista di partite
					partite_squadre.put(p.getSquadra_casa(), new LinkedList<>());
				if (!partite_squadre.containsKey(p.getSquadra_ospite()))
					partite_squadre.put(p.getSquadra_ospite(), new LinkedList<>());

				// get(1) -> aggiungo la partita nella lista di partite di quel giorno(1)
				partite_giornate.get(p.getGiornata()).add(p);
				partite_squadre.get(p.getSquadra_casa()).add(p);
				partite_squadre.get(p.getSquadra_ospite()).add(p);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}

	// partite_squadre key: Liverpool, key: Norwich
	// inizializza List di Squadra
	private void aggiornaClassifica() {
		// 1,Fri Aug 9 2019,Liverpool FC,4-1,Norwich City FC
		for (String key : partite_squadre.keySet()) {
			int punti = 0, vittorie = 0, pareggi = 0, sconfitte = 0;
			// get(key) -> partite giocate liverpool
			for (Partita p : partite_squadre.get(key)) {
				int diff = key.equals(p.getSquadra_casa()) ? p.getPunteggio()[0] - p.getPunteggio()[1]
						: p.getPunteggio()[1] - p.getPunteggio()[0];

				if (diff > 0)
					vittorie++;
				else if (diff < 0)
					sconfitte++;
				else
					pareggi++;
			}
			punti = vittorie * 3 + pareggi;
			classifica.add(new Squadra(key, punti, vittorie, pareggi, sconfitte));
		}
		Collections.sort(classifica);
	}

	public void partitePerGiornata(int giornata) {
		Menu.stampaPartite(partite_giornate.get(giornata));
	}

	public void partitePerSquadra(String nome) {
		Menu.stampaPartite(partite_squadre.get(nome));
	}

	public void stampaClassifica() {
		Menu.stampaSquadre(classifica);
	}

}
