package com.funnelsensai.core.domain.funnel;


import jakarta.persistence.*;

@Entity
@Table(name = "pages_count")
public class FunnelPagesCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }
}
