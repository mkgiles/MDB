package model;

import java.time.LocalDate;

/**
 * The Class Movie.
 */
public class Movie {
	
	/** The title. */
	private String title;
	
	/** The running time. */
	private int runningTime;
	
	/** The dor. */
	private LocalDate dor;
	
	/** The genre. */
	private String genre;
	
	/** The description. */
	private String description;
	
	/** The poster URL. */
	private String posterURL;

	/**
	 * Instantiates a new movie.
	 *
	 * @param title
	 *            the title
	 * @param runningTime
	 *            the running time
	 * @param genre
	 *            the genre
	 * @param description
	 *            the description
	 * @param posterURL
	 *            the poster URL
	 * @param yearNum
	 *            the year num
	 * @param monthNum
	 *            the month num
	 * @param dayNum
	 *            the day num
	 */
	public Movie(String title, int runningTime, String genre, String description, String posterURL, int yearNum,
			int monthNum, int dayNum) {
		setTitle(title);
		setRunningTime(runningTime);
		setGenre(genre);
		setDescription(description);
		setPosterURL(posterURL);
		setDor(yearNum, monthNum, dayNum);
	}

	/**
	 * View movie.
	 */
	public void viewMovie() {
		System.out.print("Showing movies");
	}

	/**
	 * Sets the poster URL.
	 *
	 * @param posterURL
	 *            the new poster URL
	 */
	// SETTERS//
	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the genre.
	 *
	 * @param genre
	 *            the new genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Sets the running time.
	 *
	 * @param runningTime
	 *            the new running time
	 */
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the dor.
	 *
	 * @param yearNum
	 *            the year num
	 * @param monthNum
	 *            the month num
	 * @param dayNum
	 *            the day num
	 */
	public void setDor(int yearNum, int monthNum, int dayNum) {
		this.dor = LocalDate.of(yearNum, monthNum, dayNum);
	}

	/**
	 * Gets the poster URL.
	 *
	 * @return the poster URL
	 */
	// GETTERS//
	public String getPosterURL() {
		return posterURL;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the genre.
	 *
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Gets the running time.
	 *
	 * @return the running time
	 */
	public int getRunningTime() {
		return runningTime;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the dor.
	 *
	 * @return the dor
	 */
	public LocalDate getDor() {
		return dor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return title;
	}
}
