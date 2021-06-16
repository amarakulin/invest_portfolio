package ru.akapich.invest_portfolio.model.forms.assets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean object to collect {@link NewAssetsForm}
 *
 * @author Aleksandr Marakulin
 **/


@Component
public class NewAssetsFormList extends ArrayList<NewAssetsForm> {
}
