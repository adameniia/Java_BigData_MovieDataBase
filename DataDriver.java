import java.io.*;
import java.time.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.SetChangeListener;

public class DataDriver {

	public static void main(String[] args) {
		
		Scanner fileIn = null;
		List<String> tempOneMoviePartsList = null; //holds parts for Movie object
		List<Movie> movieList = new LinkedList<Movie>(); 
		
		try{
			fileIn = new Scanner(new BufferedReader(new FileReader("BigMovieData.txt")));
			fileIn.nextLine();//skipping the first line (the header)
			while(fileIn.hasNext()){
				tempOneMoviePartsList = new ArrayList<>();
				Scanner scanLine = new Scanner(fileIn.nextLine());
				scanLine.useDelimiter("\t");
				String string = "";
				while(scanLine.hasNext()){
					string = scanLine.next();
					if(string.length() != 0){
						tempOneMoviePartsList.add(string);
					}
				}
				scanLine.close();
				
				//validate that there are 7 pieces of data necessary to create a Movie object(there are 6 somewhere)
				if(tempOneMoviePartsList.size() == 7){
					Movie newMovie = createMovie(tempOneMoviePartsList);
					movieList.add(newMovie);
					tempOneMoviePartsList.clear();
				}
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch (MovieCreationException ex){
			ex.printStackTrace();
		}
			finally{
			if(fileIn != null){
				fileIn.close();
			}
		}
		
		System.out.println("The total number of records: " + movieList.size());
		
		printTotalNumberOfMovies(movieList);
		
		String location = "dolores park";
		List <Movie> moviesByLocationList = findMoviesByLocation(movieList, location);
		System.out.println("The movies shot at " + location.toUpperCase());
		for(Movie movie : moviesByLocationList){
			System.out.println("\t" + movie.getTitle());
		}
		
	}
	
	private static void printTotalNumberOfMovies(List<Movie> list){
		Map<String, Integer> tempMap = new TreeMap<String, Integer>();
		
		Iterator<Movie> iterator = list.iterator();
		while(iterator.hasNext()){
			
			Movie m = iterator.next();
			String movieTitle = m.getTitle();
			if(tempMap.containsKey(movieTitle)){
				tempMap.replace(movieTitle, tempMap.get(movieTitle)+1);
			}else{
				tempMap.put(movieTitle, 1);
			}
		}
		System.out.println("There are " + tempMap.size() + " movies were shot in San Francisco");
		
		Set<String> listOfMovieTitles = tempMap.keySet();
		System.out.println("The list of the movies with 20+ locations: ");
		for(String string : listOfMovieTitles){
			if(tempMap.get(string) >= 20){
				System.out.println("\t" + string + "  (" +tempMap.get(string) +" locations)");
			}
		}
	}
	
	private static List<Movie> findMoviesByLocation(List<Movie> list, String location){
		List<Movie> moviesByLocation = new ArrayList<>();
		Iterator<Movie> iterator = list.iterator();
		while(iterator.hasNext()){
			Movie movie = iterator.next();

			if(movie.getLocation() != null){
				if(movie.getLocation().toLowerCase().equals(location.toLowerCase())){
					moviesByLocation.add(movie);
				}
			}
			
		}
		return moviesByLocation;
	}
	
	private static Movie createMovie(List<String> list) throws MovieCreationException{
		Movie newMovie = null;
		for(String s : list){
			if(s == null){
				throw new MovieCreationException();
			}
		}
				String title = list.get(0);
				Year year = Year.parse(list.get(1));
				String location = list.get(2);
				String director = list.get(3);
				String productionCompany = list.get(4);
				String writer = list.get(5);
				String leadActor = list.get(6);
				newMovie =  new Movie(title, year, location, director, productionCompany,  writer, leadActor);	
			return newMovie;
	}
}
