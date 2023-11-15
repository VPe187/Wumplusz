package wumpus.map.validator;

import java.util.List;

import wumpus.exceptions.MapValidationException;
import wumpus.map.Map;

/**
 * Ties multiple map validations together and validates a map through them.
 */
public class MapValidatorComposer implements MapValidator {

    private final List<MapValidator> mapValidatorList;

    public MapValidatorComposer(List<MapValidator> mapValidatorList) {
        this.mapValidatorList = mapValidatorList;
    }

    @Override
    public void validate(Map map) throws MapValidationException {
        for (MapValidator mapValidator : mapValidatorList) {
            mapValidator.validate(map);
        }
    }

}