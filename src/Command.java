/*
// Filip Garcia

ALTERNATIVE SOLUTION TO THE COMMANDLIST IN REGISTER.

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Command {
    // Enum instead of String literals because it's more typesafe
    REGISTER_NEW_DOG("register new dog"),
    LIST_DOGS("list dogs"),
    INCREASE_AGE("increase age"),
    REMOVE_DOG("remove dog"),
    REGISTER_NEW_OWNER("register new owner"),
    GIVE_DOG("assign dog to owner"),
    LIST_OWNER("list owners"),
    REMOVE_OWNER("remove owner"),
    START_AUCTION("start auction"),
    MAKE_BID("make bid"),
    LIST_BIDS("list bids"),
    LIST_AUCTIONS("list auctions"),
    CLOSE_AUCTION("close auction"),
    EXIT("exit");


    // Stream of all Enum values. Array.stream converts array to stream.
    private static final Map<String, Command> COMMAND_VALUE_MAP = Arrays.stream(values())
            // collect + collectors.toMap - collectes values in array and turns it into a map.
            // e -> e returns the value. string becomes key.
            .collect(Collectors.toMap(Command::getLabel, e -> e));
    private final String label;

    Command(String cmd) {
        this.label = cmd;
    }

    public String getLabel() {
        return this.label;
    }

    // get the Enum value from the map.
    public static Optional<Command> fromText(String input) {
        return Optional.ofNullable(COMMAND_VALUE_MAP.get(input));
    }
}*/
