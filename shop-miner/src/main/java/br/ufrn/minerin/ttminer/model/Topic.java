package br.ufrn.minerin.ttminer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "topic")
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="subject")
    String subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "trendingtopics_id")
    TrendingTopics trendingtopics;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TrendingTopics getTrendingtopics() {
        return trendingtopics;
    }

    public void setTrendingtopics(TrendingTopics trendingtopics) {
        this.trendingtopics = trendingtopics;
    }
}
