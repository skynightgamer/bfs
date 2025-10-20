public class GRAPH
{
    private KNOTEN[] knotennamen;
    private boolean[][] adja;    
    private int aktAnzahl, maxAnzahl;

    //Deklariere ein Feld, um zu markieren, ob der Knoten schon besucht wurde
    boolean[] besucht;
    int [] warteschlange;
    //Deklariere ein Feld, welches die abzuarbeitende Warteschlange darstellt
    int[] reihenfolge; //Feld für die Ausgabe der Reihenfolge
    
     public GRAPH(int anzKnoten)
    {
        adja = new boolean[anzKnoten][anzKnoten];
        knotennamen = new KNOTEN[anzKnoten];
        maxAnzahl = anzKnoten;
        //Initialisierung des Feldes besucht
        besucht = new boolean[maxAnzahl];
        warteschlange = new int[maxAnzahl];
        //Initialisierung des Feldes warteschlange
        reihenfolge = new int[maxAnzahl];
        
    }

    private void warteschlangeVorbereiten() {
    //Alle Plätze der Warteschlange werden mit -1 initalisiert, um zu kennzeichnen, dass sie nicht benutzt werden.
        for (int i = 0; i < warteschlange.length; i ++) {
            warteschlange[i] = -1;
        }
    }
    
    public void breitensuche(int start) {   
        warteschlangeVorbereiten();
        //Der Startknoten wird als besucht markiert.
        besucht[start] = true;
        //Der Start-Knoten wird als 1. abzuarbeitender Knoten abgespeichert.
        warteschlange[0] = start;
        int wartePos=1; //Variable gibt die Anzahl der Elemente in der Warteschlange an.
        int besKnoten=0; //Variable, die die Anzahl der besuchten Knoten angibt.
        
        //Schleife überprüft, ob noch mind. ein Element in der Schlange ist.
        while(warteschlange[0] != -1) {
            // aktueller knoten wird in die reihenfolge liste eingefügt -> für finale ausgabe
            reihenfolge[besKnoten] = warteschlange[0];
            besKnoten++;
            for (int i = 0; i < maxAnzahl; i++) {
                // suche nach unbesuchten nachbarn von diesem knoten aus
                if (adja[i][warteschlange[0]] == true && besucht[i] == false) {
                    // markiere den knoten als besucht
                    besucht[i] = true;
                    // speichere den nachbarn in die warteschlange
                    warteschlange[wartePos] = i;
                    // warte pos wird um 1 erhöht -> für jeden noch nichtregistrieten knoten
                    wartePos++;
                }
            }
            // entferne ersten (gerade bearbeiteten) knoten aus der liste
            // liste wird neu generiert
            warteschlange = entfernen(warteschlange);
            // wartePos wird um 1 verringert, weil erstes element wurde entfernt
            wartePos--;
        }
        // liste mit allen besuchten knoten ausgeben
         reihenfolgeAusgeben(reihenfolge);
    }
    
    private int[] entfernen(int[] feld) {
        for(int i=1; i<feld.length; i++) {
            feld[i-1]=feld[i]; 
        }
        feld[feld.length-1]=-1;
        return feld;
    }
    
    
    //Die folgenden Methoden sind für dich nicht relevant.
    
    private void reihenfolgeAusgeben(int[] feld) {
        System.out.println("Der Durchlauf der Breitensuche ergibt die Reihenfolge: ");
        System.out.print(knotennamen[feld[0]].bezeichnerGeben()); //feld[0]
        for(int i=1; i< feld.length; i++) {
            System.out.print("  -  " + knotennamen[feld[i]].bezeichnerGeben());
        }
    }
    
    public void knotenHinzufuegen(KNOTEN k) {
        if(aktAnzahl < maxAnzahl) {
            knotennamen[aktAnzahl] = k;
            aktAnzahl++;
        } else {
            System.out.println("Ein Einfügen ist leider nicht möglich.");
        }
    }
     
    public void kanteHinzufuegen(int start, int ende) {
        if((start > maxAnzahl) || (ende > maxAnzahl)){
            System.out.println("Ein Einfügen ist leider nicht möglich.");
        } else {
            adja[start][ende]=true;
            adja[ende][start]=true;
        }        
    }
    
    public void adjaAusgeben() {
        System.out.print("      ");
        for(int i=0; i<maxAnzahl;i++) {
            System.out.print(knotennamen[i].kuerzelGeben() + "     ");
        }
        System.out.println(" ");
        for(int i=0; i<maxAnzahl;i++) {
            System.out.print(knotennamen[i].kuerzelGeben() + "     ");
            for(int j=0; j<maxAnzahl;j++) {
               if(adja[i][j]) {
                   System.out.print("x     ");
               } else {
                   System.out.print("-     ");
               }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
    public int knotenNummerGeben(String wert) {
        int position = -1;
        for(int i=0;i< aktAnzahl; i++) {
            if(knotennamen[i].bezeichnerIstGleich(wert));
            { 
                position = i;
            }
        }
        
        return position;
    }
    
    public void alleKantenEntfernen() {
        for(int i=0; i<maxAnzahl;i++) {
            for(int j=0; j<maxAnzahl;j++) {
                adja[i][j]=false;
            }
        }
    }
       
}
