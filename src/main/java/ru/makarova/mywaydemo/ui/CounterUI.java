package ru.makarova.mywaydemo.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.makarova.mywaydemo.back.Counter;
import ru.makarova.mywaydemo.back.CounterRepository;

@Route("")
public class CounterUI extends VerticalLayout {

    private final CounterRepository counterRepository;

    private final TextField counterField = new TextField("Поставь значение");
    private final Button incrementButton = new Button("Нажми меня");

    private final Binder<Counter> binder = new Binder<>(Counter.class);

    @Autowired
    public CounterUI(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;

        add(counterField, incrementButton);
        binder.bind(counterField, Counter::getValueAsString, Counter::setValueFromString);

        Counter counter = counterRepository.findById(1L).orElse(new Counter());
        binder.setBean(counter);

        incrementButton.addClickListener(e -> {
            try {
                Counter currentCounter = binder.getBean();
                currentCounter.incrementValue();
                counterRepository.save(currentCounter);
                binder.readBean(currentCounter);
            } catch (Exception ex) {
                Notification.show("Error updating counter value");
            }
        });

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }


}