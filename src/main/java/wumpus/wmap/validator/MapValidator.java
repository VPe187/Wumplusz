package wumpus.wmap.validator;

import wumpus.exceptions.MapValidationException;
import wumpus.wmap.WMap;

/**
 * Validates the given Map.
 */
public interface MapValidator {
        void validate(WMap wmap) throws MapValidationException;
}
