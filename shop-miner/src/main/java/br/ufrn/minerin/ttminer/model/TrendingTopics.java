package br.ufrn.minerin.ttminer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trendingtopics")
public class TrendingTopics implements Serializable, Comparable<TrendingTopics> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="location")
	private String location;
	
	@Column(name="timestamp")
	@Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

	@OneToMany(mappedBy = "trendingtopics", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private List<Topic> topics;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTimestamp() {
		return (Date) timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public int compareTo(TrendingTopics o) {
		// TODO Auto-generated method stub
		if (timestamp.equals(o.timestamp))
			return 0;
		else if (timestamp.after(o.timestamp))
			return 1;
		else
			return -1;
	}

}
