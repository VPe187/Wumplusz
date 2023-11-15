package wumpus.map.validator;

import wumpus.exceptions.MapValidationException;
import wumpus.map.Map;

/**
 * Validates the given Map.
 */
public interface MapValidator {
        void validate(Map map) throws MapValidationException;
}
