import java.util.*;

public class Dijkstra
{
    // Führt den Dijkstra-Algorithmus schrittweise aus
    public void kuerzesterWegSchrittweise(Knoten start)
    {
        PriorityQueue<Knoten> queue = new PriorityQueue<Knoten>(); // PriorityQueue speichert Knoten sortiert nach kürzester Distanz
        start.setKuerzesterWeg(0); // Startknoten bekommt Distanz 0
        queue.add(start);

        System.out.println("Startknoten: " + start.getName());
        System.out.println("----------------------------------");
        
        // Solange es noch Knoten zu bearbeiten gibt
        while(!queue.isEmpty())
        {
            Knoten aktuell = queue.poll(); // Knoten mit aktuell kleinster Distanz holen
            System.out.println("Bearbeite Knoten: " + aktuell.getName() +" (Distanz: " + aktuell.getKuerzesterWeg() + ")");

            for (int i = 0; i < aktuell.getNachbarn().size(); i++)  // Alle Nachbarn des aktuellen Knotens durchgehen
            {
                Kanten k = aktuell.getNachbarn().get(i);
                Knoten ziel = k.getZiel();
                int neueDistanz = aktuell.getKuerzesterWeg() + k.getGewicht(); // Neue mögliche Distanz berechnen

                System.out.print("  Prüfe Kante zu " + ziel.getName() +" (Gewicht " + k.getGewicht() + "): ");

                if (neueDistanz < ziel.getKuerzesterWeg()) // Wenn ein kürzerer Weg gefunden wurde soll er aktualisiert werden
                {
                    ziel.setKuerzesterWeg(neueDistanz);
                    ziel.setVorgaenger(aktuell);  // Vorgänger speichern für spätere Wegrekonstruktion
                    queue.add(ziel); // Zielknoten wieder in Queue einfügen (neue Priorität)
                    System.out.println("neuer kürzester Weg = " + neueDistanz);
                } 
                else 
                {
                    System.out.println("keine Verbesserung");
                }
            }
            System.out.println("----------------------------------");
        }
    }

    // Berechnet den kürzesten Weg vom Startknoten bis zu einem bestimmten Zielknoten
    // Der Algorithmus stoppt, sobald der Zielknoten endgültig berechnet wurde
    public void kuerzesterWegBisZiel(Knoten start, Knoten ziel)
    {
        PriorityQueue<Knoten> queue = new PriorityQueue<Knoten>(); // PriorityQueue speichert Knoten sortiert nach ihrer aktuellen Distanz

        start.setKuerzesterWeg(0); //Startknoten bekommt Distanz 0 (Ausgangspunkt)
        queue.add(start);

        System.out.println("Start: " + start.getName());
        System.out.println("Ziel: " + ziel.getName());
        System.out.println("----------------------------------");
        // Solange noch Knoten in der Queue sind
        while (!queue.isEmpty())
        {
            Knoten aktuell = queue.poll(); // Knoten mit der kleinsten Distanz wird entnommen

            System.out.println("Bearbeite: " + aktuell.getName() +" (Distanz: " + aktuell.getKuerzesterWeg() + ")");
            
            //Wenn der Zielknoten erreicht wurde, ist seine Distanz endgültig dadurch kann der Algorithmus beendet werden
            if (aktuell == ziel)
            {
                System.out.println("Zielknoten erreicht!");
                System.out.println("Kürzeste Distanz: " + ziel.getKuerzesterWeg());

                // Pfad direkt hier zusammensetzen
                ArrayList<Knoten> pfad = new ArrayList<>();
                Knoten temp = ziel;
                
                // Über die gespeicherten Vorgänger zurücklaufen
                while (temp != null)
                {
                    pfad.add(0, temp); // Am Anfang der Liste einfügen, damit die Reihenfolge Start zu Ziel stimmt
                    temp = temp.getVorgaenger();
                }

                System.out.print("Pfad: ");
                for (int i = 0; i < pfad.size(); i++)
                {
                    System.out.print(pfad.get(i).getName() + " ");
                }

                System.out.println();
                return;
            }

            // Nachbarn prüfen
            for (int i = 0; i < aktuell.getNachbarn().size(); i++)
            {
                Kanten k = aktuell.getNachbarn().get(i); // Eine ausgehende Kante holen
                Knoten nachbar = k.getZiel(); // Zielknoten dieser Kante
                int neueDistanz = aktuell.getKuerzesterWeg() + k.getGewicht(); //Neue mögliche Distanz berechnen
                
                 // Wenn ein kürzerer Weg gefunden wurde dann aktualisieren
                if (neueDistanz < nachbar.getKuerzesterWeg())
                {
                    nachbar.setKuerzesterWeg(neueDistanz);
                    nachbar.setVorgaenger(aktuell);  // Vorgänger speichern für spätere Pfad-Rekonstruktion
                    queue.add(nachbar); // Nachbar erneut in die Queue einfügen, damit er mit neuer Priorität berücksichtigt wird
                }
            }
        }

        // Falls kein Weg existiert
        System.out.println("Kein Weg zum Ziel gefunden.");
    }
}
