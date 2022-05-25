package it.polito.tdp.borders.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO bordersDAO;
	 Map<Integer,Country> mappaCountries;
	 List<Country> listaCountries;
	private Graph<Country, DefaultEdge> grafo;
	//private Map<Country,Integer> mappaCountriesNumber ;
	List<Border> listaConfini;
	 
	
	
	

	public Model() {
		
		
		this.bordersDAO = new BordersDAO();
		//this.mappaCountriesNumber = new TreeMap<Country,Integer>();
		
			
		}
	
	public void getCountries(int anno){
		

			listaCountries = new LinkedList<Country>(bordersDAO.loadAllCountries(anno));
			mappaCountries = new TreeMap<Integer,Country>();
			
			for(Country c : listaCountries) {
				
				this.mappaCountries.put(c.getCCod(), c);
			
		}
		
		
		
		}
	
	public String creaGrafo(int anno) {
		
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		
		getCountries(anno);
		//Graphs.addAllVertices(this.grafo, countries);
		listaConfini = bordersDAO.getCountryPairs(anno,mappaCountries);
		//System.out.println(listaConfini.toString());
		//System.out.println(mappaCountries.toString());
		
		for(Border b : listaConfini) {
			
			//Graphs.addEdgeWithVertices(this.grafo,
			//		this.mappaCountries.get(b.getState1ab()), 
			//		this.mappaCountries.get(b.getState2ab()));
			//Graphs.addEdgeWithVertices(this.grafo, mappaCountries.get(b.getState1ab()), mappaCountries.get(b.getState2ab()));
			this.grafo.addVertex(mappaCountries.get(b.getState1no()));
			this.grafo.addVertex(mappaCountries.get(b.getState2no()));
			this.grafo.addEdge(mappaCountries.get(b.getState1no()), mappaCountries.get(b.getState2no()));
			
			
		}
		
		String s="";
		for(Country c : this.grafo.vertexSet()) {
			
			List<Country> vicini = Graphs.neighborListOf(this.grafo, c);
			s+= ""+c.getStateName()+" "+vicini.size()+"\n";
			
		} 
		
		return s;
		
		
		
	}
		
		public int nVertici() {
			return this.grafo.vertexSet().size();
			
		}
		
		public int nArchi() {
			return this.grafo.edgeSet().size();
			
		}

		public Graph<Country, DefaultEdge> getGrafo() {
			return grafo;
		}

		public void setGrafo(Graph<Country, DefaultEdge> grafo) {
			this.grafo = grafo;
		}
		
		public List<Country> getNodi(Country c){
			
			List<Country> visitati = new LinkedList<Country>();
			DepthFirstIterator<Country, DefaultEdge> it = new DepthFirstIterator<>(this.grafo, c);
			while(it.hasNext()) {
				visitati.add(it.next());			
			}
			
			return visitati;
		}
		
		
		//public Map<Country, Integer> nAdiacenze() {
			
			//for(Country c : mappaCountries.values()) {
			//mappaCountriesNumber.put(c, this.grafo.degreeOf(c));}
			
			//return mappaCountriesNumber;
			
		
		}
		
		
		
	
	
	
		
		
		
	//}


