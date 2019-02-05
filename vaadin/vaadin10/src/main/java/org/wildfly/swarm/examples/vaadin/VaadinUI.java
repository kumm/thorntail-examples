package org.wildfly.swarm.examples.vaadin;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;


/**
 * @author Sven Ruppert
 */

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class VaadinUI extends Composite<Div> {
    @Inject
    BeanManager bm;

    @PostConstruct
    private void init() {
        getContent()
                .add(new Button() {
                         {
                             setText("click me!");
                             addClickListener(event -> {
                                 final Notification notification = new Notification();
                                 notification.add(
                                         new VerticalLayout(
                                                 new Label(bm + ""),
                                                 new Button() {
                                                     {
                                                         setText("Bye!");
                                                         addClickListener(e -> notification.close());
                                                     }
                                                 }
                                         ));
                                 notification.setPosition(Notification.Position.MIDDLE);
                                 notification.open();
                             });
                         }
                     }
                );
    }
}
