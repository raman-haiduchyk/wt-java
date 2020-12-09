package by.bundesliga.fan.controller.command;

import by.bundesliga.fan.controller.CommandName;
import by.bundesliga.fan.controller.command.impl.*;
import com.sun.org.apache.bcel.internal.generic.GotoInstruction;

import java.util.HashMap;

public final class CommandProvider {
    private final HashMap<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGNIN, new SignInCommand());
        repository.put(CommandName.SIGNOUT, new SignOutCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
        repository.put(CommandName.GO_TO_REGISTER, new GoToRegistrationCommand());
        repository.put(CommandName.GO_TO_CATALOG, new GoToCatalogCommand());
        repository.put(CommandName.GO_TO_LOGIN, new GoToLoginCommand());
        repository.put(CommandName.GO_TO_MAIN, new GoToMainPageCommand());
    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }
        catch (IllegalArgumentException | NullPointerException e) {
            //log
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return  command;
    }
}
