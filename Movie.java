import java.sql.Time;
import java.time.Year;
import java.util.Comparator;

public class Movie implements Comparable<Movie>{
	
	private String title;
	private Year year;
	private String location;
	private String director;
	private String productionCompany;
	private String writer;
	private String leadActor;
	
	public final static Comparator<Movie> MOVIE_TITLE_COMPARATOR = new MovieTitleComparator();
//	
//	private static final String DEFAULT_DISTRIBUTOR = "no info about distributor";	
//	private static final String DEFAULT_FACTS = "no facts";
//	private static final String DEFAULT_LOCATION = "no facts";
//	private static final String DEFAULT_ACTOR = "no info";
	
	public Movie(String title, Year year, String location, String director, String productionCompany,  String writer,
			String leadActor) {
		super();
		this.title = title;
		this.year = year;
		this.location = location;
		this.productionCompany = productionCompany;
		this.director = director;
		this.writer = writer;
		this.leadActor = leadActor;
		
	}

	public String getTitle() {
		return title;
	}

	public Year getYear() {
		return year;
	}

	public String getLocation() {
		return location;
	}

	public String getProductionCompany() {
		return productionCompany;
	}

	public String getDirector() {
		return director;
	}

	public String getWriter() {
		return writer;
	}

	public String getLeadActor() {
		return leadActor;
	}

	@Override
	public String toString(){
		return "Title: " + this.title + "; " +
				"Year: " +this.year + "; " +
				"Production Company: " + this.productionCompany + "; " +
				"Director: " + this.director + "; " +
				"Writer: " + this.writer + "; " +
				"Lead Actress/Actor: " + this.leadActor;
				
				
	}
	
	//equals method
		@Override
		public boolean equals(Object obj){
			if (obj instanceof Movie){
				Movie otherMovie = (Movie) obj;
				
				return otherMovie.title.equalsIgnoreCase(this.title) &&
						otherMovie.director.equalsIgnoreCase(this.director) &&
						otherMovie.writer.equalsIgnoreCase(this.writer) &&
						otherMovie.year.equals(this.year);
						
			}else{
				return false;
			}
		}
	
	@Override
	public int compareTo(Movie obj) {
		
		if(this.title.compareTo(obj.title) != 0){
			return this.title.compareTo(obj.title);
		}else{
			if(this.year.compareTo(obj.year) != 0){
				return this.year.compareTo(obj.year);
			}else{
				
					if(this.director.compareTo(obj.director) > 0){
						return +1;
					}else if (this.director.compareTo(obj.director) < 0){
						return -1;
					}else{
						return 0;
					}
				
			}
		}
	}
	
	private static class MovieTitleComparator implements Comparator<Movie>{
		@Override
		public int compare(Movie s1, Movie s2)
	    {
			return s1.title.compareTo(s2.title);
	       
	    }
	}
}
