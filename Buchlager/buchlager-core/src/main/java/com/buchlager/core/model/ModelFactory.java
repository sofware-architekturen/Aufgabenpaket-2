package com.buchlager.core.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ModelFactory
{
	static private ModelFactory instance = null;

	static public ModelFactory getInstance()
	{
		if (ModelFactory.instance == null)
		{
			synchronized (ModelFactory.class)
			{
				if (ModelFactory.instance == null)
				{
					ModelFactory.instance = new ModelFactory();
				}
			}
		}

		return ModelFactory.instance;
	}
	
	
	private List<Buch> buecher = null;

	private ModelFactory()
	{
		super();
		this.buecher = Arrays.stream( this.init() ).collect( Collectors.toList() );
	}
	
	public Collection<Buch> getBuecher()
	{
		return this.buecher;
	}

	private Buch[] init()
	{
		Autor[] autoren  = new Autor[] {
			  new Autor(0, "Andrew S.", "Tanenbaum"),
				new Autor(1, "Marten", "van Steen"),
				new Autor(2, "G�nther", "Bengel"),
				new Autor(3, "Richard", "Monson-Haefel"),
				new Autor(4, "Jason", "Hunter"),
				new Autor(5, "William", "Crawford"),
				new Autor(6, "Doug", "Lea"),
				new Autor(7, "Phillip A.", "Bernstein"),
				new Autor(8, "Eric", "Newcomer"),
				new Autor(9, "Martin", "Fowler"),
				new Autor(10, "Clemens", "Szyperski"),
				new Autor(11, "Erich", "Gamma"),
				new Autor(12, "Richard", "Helm"),
				new Autor(13, "Ralph", "Johnson"),
				new Autor(14, "John", "Vlissides"),
				new Autor(15, "Andreas", "Heuer"),
				new Autor(16, "Gunter", "Saake"),
				new Autor(17, "Robert", "Orfali"),
				new Autor(18, "Dan", "Harkey"),
				new Autor(19, "Jeri", "Edwards"),
				new Autor(20, "Grady", "Booch"),
				new Autor(21, "David A.", "Chappell"),
				new Autor(22, "Ed", "Roman"),
				new Autor(23, "Scott", "Ambler"),
				new Autor(24, "Tyler", "Jewell"),
				new Autor(25, "Floyed", "Marinescu"),
				new Autor(26, "Len", "Bass"),
			  new Autor(27, "Paul", "Clements"),
			  new Autor(28, "Rick", "Kazman"),
			  new Autor(29, "Robert", "Martin")
				};

		Adresse[] adressen =
			new Adresse[] {
				new Adresse(0, "Braunschweig/Wiesbaden"),
				new Adresse(1, "New Jersey"),
				new Adresse(2, "Farnham"),
				new Adresse(3, "Reading, Massachusetts"),
				new Adresse(4, "Landsberg"),
				new Adresse(5, "New York"),
				new Adresse(6, "San Francisco"),
				new Adresse(7, "Berlin"),
				new Adresse(8, "M�nchen"),
				};

		Verlag[] verlage =
			new Verlag[] {
				new Verlag(0, "Vieweg", adressen[0]),
				new Verlag(1, "Prentice Hall", adressen[1]),
				new Verlag(2, "O'Reilly", adressen[2]),
				new Verlag(3, "Addison-Wesley", adressen[3]),
				new Verlag(4, "mitp", adressen[4]),
				new Verlag(5, "Wiley", adressen[5]),
				new Verlag(6, "Morgan Kaufmann", adressen[6])
				};

		Buch[] buecher = 
			new Buch[] { 
				new Buch(0, "Distributed Systems", verlage[1]),
				new Buch(1, "Verteilte Systeme", verlage[0]),
				new Buch(2, "The Essential Client/Server Survival Guide", verlage[5]),
				new Buch(3, "Datenbanken: Konzepte und Sprachen", verlage[4]),
				new Buch(4, "Datenbanken: Implementierungstechniken", verlage[4]),
				new Buch(5, "Enterprise JavaBeans", verlage[2]),
				new Buch(6, "Java Message Service", verlage[2]),
				new Buch(7, "Java Servlet Programming", verlage[2]),
				new Buch(8, "Concurrent Programming in Java", verlage[3]),
				new Buch(9, "Principles of Transaction Processing", verlage[6]),
				new Buch(10, "Modern Operating Systems", verlage[1]),
				new Buch(11, "Objektorientierte Analyse und Design", verlage[3]),
				new Buch(12, "The Essential Distributed Objects Survival Guide", verlage[5]),
				new Buch(13, "Refactoring", verlage[3]),
				new Buch(14, "UML Distilled", verlage[3]),
				new Buch(15, "Analysis Patterns", verlage[3]),
				new Buch(16, "Design Patterns", verlage[3]),
				new Buch(17, "Component Software", verlage[3]),
				new Buch(18, "Mastering Enterprise JavaBeans", verlage[5]),
				new Buch(19, "EJB Design Patterns", verlage[5]),
				new Buch(20, "Software Architecture in Practice", verlage[3]),
				new Buch(21, "Agil Software Development", verlage[1])
				};
		
		buecher[0].addAutor( autoren[0] );
		buecher[0].addAutor( autoren[1] );
		
		buecher[1].addAutor( autoren[2] );
		
		buecher[2].addAutor( autoren[17] );
		buecher[2].addAutor( autoren[18] );
		buecher[2].addAutor( autoren[19] );
		
		buecher[3].addAutor( autoren[15] );
		buecher[3].addAutor( autoren[16] );
		
		buecher[4].addAutor( autoren[15] );
		buecher[4].addAutor( autoren[16] );
		
		buecher[5].addAutor( autoren[3] );
		
		buecher[6].addAutor( autoren[3] );
		buecher[6].addAutor( autoren[21] );
		
		buecher[7].addAutor( autoren[4] );
		buecher[7].addAutor( autoren[5] );
		
		buecher[8].addAutor( autoren[6] );
		
		buecher[9].addAutor( autoren[7] );
		buecher[9].addAutor( autoren[8] );
		
		buecher[10].addAutor( autoren[0] );
		
		buecher[11].addAutor( autoren[20] );		

		buecher[12].addAutor( autoren[17] );
		buecher[12].addAutor( autoren[18] );
		buecher[12].addAutor( autoren[19] );

		buecher[13].addAutor( autoren[9] );		
		buecher[14].addAutor( autoren[9] );		
		buecher[15].addAutor( autoren[9] );		

		buecher[16].addAutor( autoren[11] );
		buecher[16].addAutor( autoren[12] );
		buecher[16].addAutor( autoren[13] );
		buecher[16].addAutor( autoren[14] );
		
		buecher[17].addAutor( autoren[10] );
		
		buecher[18].addAutor( autoren[22] );		
		buecher[18].addAutor( autoren[23] );		
		buecher[18].addAutor( autoren[24] );
		
		buecher[19].addAutor( autoren[25] );	
		
		buecher[20].addAutor( autoren[26] );  
		buecher[20].addAutor( autoren[27] );  
		buecher[20].addAutor( autoren[28] );  
		
		buecher[21].addAutor( autoren[29] );  
		
		return buecher;
	}
}
