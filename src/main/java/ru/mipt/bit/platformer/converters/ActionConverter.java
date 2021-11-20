package ru.mipt.bit.platformer.converters;

import ru.mipt.bit.platformer.actions.Action;

public class ActionConverter implements Converter<Action, org.awesome.ai.Action> {

    @Override
    public Action convert(org.awesome.ai.Action action) {
        switch (action) {
            case MoveNorth:
                return Action.MoveNorth;
            case MoveSouth:
                return Action.MoveSouth;
            case MoveEast:
                return Action.MoveEast;
            case MoveWest:
                return Action.MoveWest;
            case Shoot:
                return Action.Shoot;
            default:
                throw new RuntimeException("Unexpected action: " + action);
        }
    }
}
