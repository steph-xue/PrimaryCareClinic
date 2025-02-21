package persistence;

import org.json.JSONObject;

// Writable code modeled from the JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
