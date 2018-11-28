package swagger;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Base;

import exception.PropertyNotFoundException;

public class ObjectDefinition extends Base {

    private JSONObject specification;

    public ObjectDefinition(String objectName) {
        // TODO This would load the expanded JSON Spec
        // Find the object in the files
    }

    public ObjectDefinition(JSONObject specification) {
        this.specification = specification;
    }

    public void validateObject(JSONObject responseObject) throws Exception {
        JSONObject properties = getProperties();
        for (String propertyName : properties.keySet()) {
            try {
                JSONObject property = properties.getJSONObject(propertyName);
                ObjectDefinition propertyDefinition = new ObjectDefinition(property);
                propertyDefinition.validate(propertyName, responseObject);
                validate(propertyName, responseObject);
            } catch (Exception e) {
                if (isMandatory(propertyName)) {
                    throw e;
                } else {
                    logger.warn("Property " + propertyName + " validation failed. Details: " + e.getMessage());
                }
            }
        }
        // TODO Inform about additional properties
    }

    private List<String> getMandatoryProperties() {
        List<String> mandatoryProperties = new ArrayList<String>();
        if (null != this.specification.getJSONArray("required")) {
            mandatoryProperties = JSONHelper.stringArray(this.specification.getJSONArray("required"));
        }
        return mandatoryProperties;
    }

    private boolean isMandatory(String property) {
        return getMandatoryProperties().contains(property);
    }

    private String getDataType() {
        return this.specification.getString("type");
    }

    private JSONObject getProperties() {
        return this.specification.getJSONObject("properties");
    }

    public void validate(String property,JSONObject parentData) throws Exception {
        if (parentData.get(property) == null) {
            throw new PropertyNotFoundException(property);
        }
        switch (getDataType()) {
        case "string":
            validateString(parentData.getString(property));
            break;
        case "number":
            validateNumber(parentData.getNumber(property));
            break;
        case "integer":
            validateInteger(parentData.getInt(property));
        case "object":
            validateObject(parentData.getJSONObject(property));
            break;
        case "array":
            validateArray(parentData.getJSONArray(property));
            break;
        case "boolean":
            validateBoolean(parentData.getBoolean(property));
        default:
            throw new Exception("Unknown data type");
        }
    }

//	private void validateDataType(String expected, Object data) throws TypeMismatchException {
//		
//		if (!data.getClass().toString().equalsIgnoreCase(expected)) {
//			throw new TypeMismatchException(expected, data.getClass().toString());
//		}	
//	}
    private void validateBoolean(Boolean value) {
        // TODO See if there's any validation required here
    }

    private void validateInteger(Integer value) {
        // TODO get rules for int and validate
    }

    private void validateString(String string) {
        // TODO get rules for the string and validate
    }

    private void validateNumber(Number number) {
        // TODO get rules for the number and validate
    }

    private void validateArray(JSONArray array) throws Exception {
        // TODO Handle anyOf
        // Get item type for array elements
        ObjectDefinition definition = new ObjectDefinition(getElementType());
        Integer validElementCount = 0;
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject temp = new JSONObject();
                temp.append(String.valueOf(i), array.get(i));
                definition.validate(String.valueOf(i), temp);
                validElementCount++;
            } catch (Exception e) {
                logger.warn("Element at index %2d failed validation", i);
            }
        }
        validateLimits(validElementCount);
    }

    private JSONObject getElementType() {
        return this.specification.getJSONObject("items");
    }

    private int getMinimumElements() {
        int minimum = Integer.MIN_VALUE;
        try {
            minimum = this.specification.getInt("minItems");
        } catch (Exception e) {
            logger.info("No maximum defined");
        }
        return minimum;
    }

    private int getMaximumElements() {
        int maximum = Integer.MAX_VALUE;
        try {
            maximum = this.specification.getInt("maxItems");
        } catch (Exception e) {
            logger.info("No maximum defined");
        }
        return maximum;
    }

    private void validateLimits(int validElementCount) throws Exception {
        if (validElementCount < getMinimumElements()) {
            throw new Exception("Validation failed");
        }
        if (validElementCount > getMaximumElements()) {
            throw new Exception("Validation failed");
        }
    }
}
