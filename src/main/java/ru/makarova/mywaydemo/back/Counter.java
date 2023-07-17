package ru.makarova.mywaydemo.back;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "counter_id_generator")
    @SequenceGenerator(name = "counter_id_generator", sequenceName = "counter_id_seq", allocationSize = 0)
    @Column(name = "id")
    private Long id;

    @Column(name = "valu")
    private Long value;

    public String getValueAsString() {
        return String.valueOf(value);
    }

    public void setValueFromString(String valueAsString) {
        this.value = Long.valueOf(valueAsString);
    }

    public void incrementValue() {
        this.value++;
    }

    public Counter() {
        this.value = 0L;
    }
}


