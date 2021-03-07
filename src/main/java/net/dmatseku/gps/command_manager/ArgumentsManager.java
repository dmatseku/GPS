package net.dmatseku.gps.command_manager;

import java.util.ArrayList;
import java.util.HashMap;

public class ArgumentsManager {

    public enum Arguments {
        STRING,
        INT
    }
    public enum Require {
        TRUE,
        FALSE
    }

    public static class ArgumentException extends RuntimeException {
        public ArgumentException(String error) {
            super(error);
        }
    }

    private interface Validator {
        boolean validate(String value);
    }

    private final HashMap<Arguments, Validator> validatorList = new HashMap<>();

    public ArgumentsManager() {
        validatorList.put(Arguments.STRING, ArgumentsManager::validateString);
        validatorList.put(Arguments.INT, ArgumentsManager::validateInt);
    }

    private static boolean validateString(String value) {
        return true;
    }

    private static boolean validateInt(String value) {
        try {
            int n = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }






    private final ArrayList<Arguments>  pattern = new ArrayList<>();
    private final ArrayList<Require>    requirement = new ArrayList<>();

    public ArgumentsManager addArgument(Arguments argument, Require requirement) {
        pattern.add(argument);
        this.requirement.add(requirement);
        return this;
    }

    public ArrayList<String> validateArguments(ArrayList<String> arguments) {
        ArrayList<String> result = new ArrayList<>();

        for (int argumentNumber = 0, patternNumber = 0; patternNumber < pattern.size(); patternNumber++) {
            Arguments argumentPattern = pattern.get(patternNumber);
            Require requirement = this.requirement.get(patternNumber);
            result.add(null);

            if (argumentNumber >= arguments.size()) {
                if (requirement == Require.TRUE) {
                    throw new ArgumentException("Too few arguments");
                } else {
                    continue;
                }
            }
            String argument = arguments.get(argumentNumber);

            if (!validatorList.get(argumentPattern).validate(argument)) {
                if (requirement == Require.TRUE) {
                    throw new ArgumentException("Invalid argument #" + argumentNumber + 1);
                }
            } else {
                result.set(result.size() - 1, argument);
                argumentNumber++;
            }
        }

        if (arguments.size() > pattern.size()) {
            throw new ArgumentException("Too many arguments");
        }

        return result;
    }
}
