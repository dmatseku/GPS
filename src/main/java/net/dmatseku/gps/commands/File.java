package net.dmatseku.gps.commands;

import net.dmatseku.gps.command_manager.ArgumentsManager;
import net.dmatseku.gps.command_manager.Command;
import net.dmatseku.gps.command_manager.ArgumentsManager.Arguments;
import net.dmatseku.gps.command_manager.ArgumentsManager.Require;
import net.dmatseku.gps.command_manager.CommandChat;
import net.dmatseku.gps.command_manager.UsageBuilder;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class File extends Command {
    @Override
    protected String setCommandName() {
        return "file";
    }

    @Override
    protected ArgumentsManager setArguments() {
        return new ArgumentsManager().addArgument(Arguments.STRING, Require.TRUE);
    }

    @Override
    public String getUsage() {
        return UsageBuilder
                .createCommandClarification(this.getCommandName())
                .addRequiredArgument("path to file")
                .build();
    }

    @Override
    public String getArgumentsClarification() {
        return UsageBuilder
                .createArgumentsClarification()
                .addRequiredArgumentClarification("path to file", "regarding the root of the game")
                .build();
    }

    @Override
    public void execute() {
        try {
            Scanner file = new Scanner(new java.io.File(this.getArgument(0)));

            while (file.hasNextLine()) {
                CommandChat.sendMessage(file.nextLine());
            }
            file.close();
        } catch (FileNotFoundException e) {
            CommandChat.argumentError("File not found", this.getCommandName(), 1);
        }
    }
}
