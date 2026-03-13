import java.util.*;
//Zeigt einen Knoten im Graphen
public class Knoten implements Comparable<Knoten>
{
    private String name;
    private ArrayList<Kanten> nachbarn; //Liste aller ausgehenden Kanten
    private int kuerzesterWeg; //Aktuell bekannte kürzeste Distanz
    private Knoten vorgaenger; //Vorgänger im kürzesten Pfad

    public Knoten(String pName)
    {
        name = pName;
        nachbarn = new ArrayList<Kanten>();
        kuerzesterWeg = Integer.MAX_VALUE; //Anfangswert: Unendlich (noch kein Weg bekannt)
        vorgaenger = null;
    }

    // Fügt eine gerichtete Kante hinzu
    public void addKante(Knoten ziel, int gewicht)
    {
        nachbarn.add(new Kanten(ziel, gewicht));
    }

    public ArrayList<Kanten> getNachbarn()
    {
        return nachbarn;
    }

    public String getName()
    {
        return name;
    }

    public int getKuerzesterWeg()
    {
        return kuerzesterWeg;
    }

    public void setKuerzesterWeg(int wert)
    {
        kuerzesterWeg = wert;
    }

    public Knoten getVorgaenger()
    {
        return vorgaenger;
    }

    public void setVorgaenger(Knoten v)
    {
        vorgaenger = v;
    }

    // Wichtig für PriorityQueue:
    // Knoten werden nach ihrer aktuellen Distanz sortiert
    public int compareTo(Knoten anderer)
    {
        return this.getKuerzesterWeg() - anderer.getKuerzesterWeg();
    }
}
