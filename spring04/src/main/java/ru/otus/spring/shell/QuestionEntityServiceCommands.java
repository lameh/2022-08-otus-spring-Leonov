package ru.otus.spring.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.LocalizationService;
import ru.otus.spring.service.TestingService;

@ShellComponent
@AllArgsConstructor
public class QuestionEntityServiceCommands {
    private final TestingService testingService;
    private final LocalizationService localizationService;

    @ShellMethod(value = "Start testing", key = {"s", "start"})
    public String startTesting() {
        testingService.run();
        return localizationService.makeLocalized("end.testing");
    }
}
