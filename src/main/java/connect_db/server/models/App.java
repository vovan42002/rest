package connect_db.server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class App implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_time_used")
    private Long last_time_used;

    @Column(name = "total_time")
    private Long total_time;

    @Column(name = "icon")
    @Lob
    private byte[] icon;

    @ManyToOne
    @JoinColumn(name = "child_id")
    @JsonBackReference
    private Child child;

    public App(){}

    public App(String name, Long last_time_used, Long total_time, byte[] icon ){
        this.name = name;
        this.last_time_used = last_time_used;
        this.total_time = total_time;
        this.icon = icon;
    }

    public Child getChild() {
        return child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLast_time_used() {
        return last_time_used;
    }

    public void setLast_time_used(Long last_time_used) {
        this.last_time_used = last_time_used;
    }

    public Long getTotal_time() {
        return total_time;
    }

    public void setTotal_time(Long total_time) {
        this.total_time = total_time;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
