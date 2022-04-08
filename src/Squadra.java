public class Squadra implements Comparable<Squadra> {
    private String nome;
    private int punti;
    private int vittorie;
    private int pareggi;
    private int sconfitte;

    public Squadra(String nome, int punti, int vittorie, int pareggi, int sconfitte) {
        this.nome = nome;
        this.punti = punti;
        this.vittorie = vittorie;
        this.pareggi = pareggi;
        this.sconfitte = sconfitte;
    }

    public String getNome() {
        return nome;
    }

    public int getVittorie() {
        return vittorie;
    }

    public int getPareggi() {
        return pareggi;
    }

    public int getSconfitte() {
        return sconfitte;
    }

    public int getPunti() {
        return punti;
    }

    @Override
    public String toString() {
        return nome + ",punti:" + punti + ",vittorie:" + vittorie + ",pareggi:" + pareggi + ",sconfitte:" + sconfitte;
    }
 
    public String toStringClassifica() {
        return nome +"   " + punti + "   " + vittorie + "   " + pareggi + "   " + sconfitte;
    }

    @Override
    public int compareTo(Squadra o) {
        // ORDINAMENTO DECRESCENTE PER LA CLASSIFICA
        return o.getPunti() - this.getPunti();
    }

}
