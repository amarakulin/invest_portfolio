package ru.akapich.invest_portfolio.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Scheduling task for update price assets automatically
 *
 * @author Aleksandr Marakulin
 **/

@Configuration
@EnableScheduling
public class SchedulingConfiguration {
}
