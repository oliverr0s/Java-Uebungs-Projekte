// Zeigt eine gerichtete Kante im Graphen
public class Kanten
{
    private Knoten ziel; // Zielknoten dieser Kante
    private int gewicht; // Gewicht der Kante

    public Kanten(Knoten pZiel, int pGewicht)
    {
        ziel = pZiel;
        gewicht = pGewicht;
    }

    public Knoten getZiel()
    {
        return ziel;
    }

    public int getGewicht()
    {
        return gewicht;
    }
}
