package com.buchlager.server.data.facade;

import static java.util.stream.Collectors.toList;

import java.util.*;

import com.buchlager.core.model.Buch;
import com.buchlager.core.model.ModelFactory;

/**
 * Die Klasse <source>BuchlagerFacade</source> repraesentiert die Schnittstelle
 * zu einem einfachen Buchlager. Die Klasse entspricht dem Singleton-Muster. Das
 * Objekt der Klasse muss mit <source>getInstance()</source> instanziiert
 * werden.
 * </p>
 * Beispiel für die Verwendung:
 * 
 * <pre>
 *
 * BuchlagerFacade lager = BuchlagerFacade.getInstance();
 *
 * Collection<Buch> buecher = lager.findAlleBuecher();
 * Iterator<Buch> itr = buecher.iterator();
 *
 * while (itr.hasNext())
 * {
 *   Buch buch = itr.next();
 *   System.out.println("Titel   : " + buch.getTitel());
 *   System.out.println("Autoren : " + buch.getAutoren());
 *   System.out.println();
 * }
 * </pre>
 *
 * </p>
 * --------------------------------------------------------------------------------
 * 
 * @author Joerg Hettel
 * @version 1.2
 *
 */

public class BuchlagerFacade{
  static private BuchlagerFacade instance = null;

  static private class LagerEintrag
  {
    private Buch buch = null;
    private int bestand = 0;

    private LagerEintrag(Buch buch, int bestand)
    {
      super();
      this.buch = buch;
      this.bestand = bestand;
    }

    private Buch getBuch()
    {
      return this.buch;
    }

    private int getBestand()
    {
      return this.bestand;
    }

    private void addBestand(int differenz)
    {
      this.bestand += differenz;
    }
  }
  
  /**
   * Erzeugt eine neue Instanz der Klasse <code>BuchlagerFacade</code>. Die
   * Implementierung stellt sicher, dass nur eine Instanz erzeugt werden kann. Bei
   * der Instanziierung wird das <i>Double Check Locking</i>-Pattern benutzt.
   */

  static public BuchlagerFacade getInstance()
  {
    return FacadeHolder.instance;
  }

  private HashMap<Integer, LagerEintrag> lager = null;

  private BuchlagerFacade()
  {
    super();
    this.lager = new HashMap<Integer, LagerEintrag>();
    this.init();
  }

  private static class FacadeHolder{
    public static final BuchlagerFacade instance = new BuchlagerFacade();
  }

  // --- Objekt-Schnittstelle
  // -----------------------------------------------------------------

  /**
   * Die Methode liefert das Buchobjekt mit der angeforderten ID zurück. Wir kein
   * Buch gefunden wird <source>null</source> zurück geliefert.
   *
   * @param buchId
   *                 interne Inventarnummer (ID) des Buches
   * @return Buch
   */
  public Buch findBuchMitId(int buchId)
  {
    LagerEintrag eintrag = (LagerEintrag) this.lager.get(new Integer(buchId));
    return (eintrag != null) ? eintrag.getBuch() : null;
  }

  /**
   * Die Methode liefert alle Buecher zurück, die der Autor geschriebenen hat. Die
   * Suche unterstützt auch das Wildcard-Zeichen "*" am Ende des Suchnamens. <br>
   * Werden keine Buecher gefunden wird eine leere Collection zurück geliefert.
   *
   * @param nachname
   *                   Nachname des Autors ("*" am Ende zulaessig)
   * @return Sammlung von Buechern.
   */
  public Collection<Buch> findBuecherVonAutor(String nachname)
  {
    String suchPattern;
    if (nachname.indexOf('*') >= 0)
    {
      suchPattern = nachname.substring(0, nachname.indexOf('*'));
    }
    else
    {
      suchPattern = nachname;
    }
    
    return this.lager.values().stream()
                     .map( entry -> entry.getBuch() )
                     .filter( buch -> buch.getAutoren().stream().anyMatch( autor -> autor.getNachname().startsWith(suchPattern)) )
                     .collect( toList() );
  }

  /**
   * Die Methode liefert alle im Lager vorhandene Buecher zur�ck.
   *
   * @return Sammlung von Buechern.
   */
  public Collection<Buch> findAlleBuecher()
  {
    return this.lager.values().stream().map(entry -> entry.getBuch() ).collect( toList() );
  }

  // --- Daten-Schnittstelle
  // -------------------------------------------------------------------------

  /**
   * Die Methode liefert die Anzahl aller im Lager verfuegbarer Buecher.
   *
   * @return Anzahl aller Buecher.
   */
  public int getAnzahlAllerBuecher()
  {
    return this.lager.size();
  }

  /**
   * Über diese Methode können Buecher aus dem Lager entnommen werden.
   *
   * @param buchId
   *                 interne Inventarnummer (ID) des Buches
   * @param anzahl
   *                 Anzahl der zu entnehmenden Buecher
   * @exception BuchlagerFacadeException
   *                                       Bei der Ausbuchung ist ein Fehler
   *                                       aufgetreten
   */
  public void bestandAusbuchen(int buchId, int anzahl) throws BuchlagerFacadeException
  {
    LagerEintrag eintrag = this.lager.get(new Integer(buchId));
    if (eintrag == null)
      throw new BuchlagerFacadeException("Buch-Id " + buchId + " nicht im Lager vorhanden");
    if (eintrag.getBestand() < anzahl)
      throw new BuchlagerFacadeException("F�r Buch-Id " + buchId + " sind nicht gen�gend Exemplare vorhanden");

    eintrag.addBestand(-anzahl);
  }

  /**
   * Über diese Methode können Buecher dem Lager hinzugefuegt werden.
   *
   * @param buchId
   *                 interne Inventarnummer (ID) des Buches
   * @param anzahl
   *                 Anzahl der zu entnehmenden Buecher
   * @exception BuchlagerFacadeException
   *                                       Bei der Einbuchung ist ein Fehler
   *                                       aufgetreten
   */
  public void bestandEinbuchen(int buchId, int anzahl) throws BuchlagerFacadeException
  {
    LagerEintrag eintrag = this.lager.get(new Integer(buchId));
    if (eintrag == null)
      throw new BuchlagerFacadeException("Buch-Id " + buchId + " nicht im Lager vorhanden");

    eintrag.addBestand(anzahl);
  }

  /**
   * Die Methode liefert die Anzahl der Buchexemplare mit der angegebenen ID im
   * Lager verfuegbarer .
   *
   * @param buchId
   *                 interne Inventarnummer (ID) des Buches
   * @return Buchbestand.
   */
  public int getBestand(int buchId) throws BuchlagerFacadeException
  {
    LagerEintrag eintrag = this.lager.get(new Integer(buchId));
    if (eintrag == null)
      throw new BuchlagerFacadeException("Buch-Id " + buchId + " nicht im Lager vorhanden");

    return eintrag.getBestand();
  }

  // --- XML-Schnittstelle
  // -----------------------------------------------------------------

  /**
   * Die Methode liefert alle Buecher zurück, die der Autor geschriebenen hat. Die
   * Suche unterstützt auch das Wildcard-Zeichen "*" am Ende des Suchnamens. <br>
   * Werden keine Buecher gefunden wird eine leere Liste zurück geliefert.<br>
   * Die R�ckgabe hat folgendes Format:
   *
   * <pre>
   *    <buchliste>
   *       <buch>
   *          <titel> .... </titel>
   *          <verlag> .... </verlag>
   *       </buch>
   *       ...
   *    </buchliste>
   * </pre>
   *
   * @param nachname
   *                   Nachname des Autors ("*" am Ende zulaessig)
   * @return XML Datenstruktur.
   */
  public String findBuecherVonAutorAsXML(String nachname)
  {
    StringBuffer strBuf = new StringBuffer("");

    strBuf.append("<?xml version=\"1.0\"?>");
    strBuf.append("<buchliste>");
    Collection<Buch> buecher = this.findBuecherVonAutor(nachname);
    for(Buch buch : buecher )
    {
      strBuf.append("<buch>");
      strBuf.append("<titel>" + buch.getTitel() + "</titel>");
      strBuf.append("<verlag>" + buch.getVerlag() + "</verlag>");
      strBuf.append("</buch>");
    }

    strBuf.append("</buchliste>");
    return strBuf.toString();
  }

  /*
   * private Methoden
   */

  private void addNeuesBuch(Buch buch, int anzahl)
  {
    this.lager.put(new Integer(buch.getId()), new LagerEintrag(buch, anzahl));
  }

  private void init()
  {
    Random rand = new Random();

    ModelFactory factory = ModelFactory.getInstance();

    Collection<Buch> coll = factory.getBuecher();
    Iterator<Buch> itr = coll.iterator();
    while (itr.hasNext())
    {
      Buch buch = (Buch) itr.next();
      this.addNeuesBuch(buch, rand.nextInt(3) + 1); // Anzahl der Bücher von 1 bis 3
    }
  }
}
